/*
* ExoahCoins
* Copyright (C) 2014 Exoah <http://www.exoah.com>
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/
package com.exoah.coins.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.exoah.coins.ExoahCoins;
import com.exoah.coins.api.events.PlayerReceiveCoinsEvent;
import com.exoah.coins.api.events.PlayerTakeCoinsEvent;

/**
 * @author Sebastiaan
 *
 */
public class CoinUtils {

	/**
	 * Returns if the player is inside the database
	 * 
	 * @param player The player that you want to check for
	 * @return Is player in database?
	 * @throws SQLException
	 */
	public boolean isInDatabase(Player player) throws SQLException {
		Statement statement = ExoahCoins.getExoahCoins().getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM coins WHERE UUID = '" + PlayerUtils.getUUID(player.getName()) + "';");
		resultSet.next();
		
		if (resultSet.getString("UUID") == null) {
		    PreparedStatement preparedStatement = ExoahCoins.getExoahCoins().getConnection().prepareStatement("INSERT INTO `coins` (UUID, amount) VALUES (?, ?);");
		    preparedStatement.setString(1, PlayerUtils.getUUID(player.getName()));
		    preparedStatement.setInt(2, 0);
		    preparedStatement.executeUpdate();
		    
		    preparedStatement.close();
		    resultSet.close();
		    statement.close();
		    
			return false;
		} else {
		    resultSet.close();
		    statement.close();
		    
			return true;
		}		
	}
	
	/**
	 * Returns the amount of coins that a player has
	 * 
	 * @param player The player that you want to know the amount of coins from.
	 * @return How many coins does the player has?
	 * @throws SQLException
	 */
	public int getCoins(Player player) throws SQLException {
		Statement statement = ExoahCoins.getExoahCoins().getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM coins WHERE UUID = '" + PlayerUtils.getUUID(player.getName()) + "';");
		resultSet.next();

		int coins;
		
		if (resultSet.getString("UUID") == null) {
			coins = 0;
		} else {
			coins = resultSet.getInt("coins");
		}
		
		resultSet.close();
		statement.close();
		
		return coins;
	}
	
	/**
	 * Give coins to a player
	 * 
	 * @param player The player that will receive the coins
	 * @param amount The amount of coins that the player will receive
	 * @throws SQLException
	 */
	public void giveCoins(Player player, int amount) throws SQLException {
		PlayerReceiveCoinsEvent event = new PlayerReceiveCoinsEvent(player, amount);
		ExoahCoins.getExoahCoins().getServer().getPluginManager().callEvent(event);
		
		if (!event.isCancelled()) {
			if (isInDatabase(player)) {
				PreparedStatement preparedStatement = ExoahCoins.getExoahCoins().getConnection().prepareStatement("UPDATE coins SET amount = ? WHERE UUID = ?");
				preparedStatement.setInt(1, (getCoins(player) + amount));
				preparedStatement.setString(2, PlayerUtils.getUUID(player.getName()));
				preparedStatement.executeUpdate();
				
				preparedStatement.close();
				
				player.sendMessage(ChatColor.GREEN + "You have received " + amount + " coins.");
			}
		} else {
			player.sendMessage(ChatColor.RED + "Sorry. You did not receive your coins.");
		}
	}
	
	/**
	 * Take coins from a player
	 * 
	 * @param player The player that will lose coins
	 * @param amount The amount of coins that the player will lose
	 * @throws SQLException
	 */
	public void takeCoins(Player player, int amount) throws SQLException {
		PlayerTakeCoinsEvent event = new PlayerTakeCoinsEvent(player, amount);
		ExoahCoins.getExoahCoins().getServer().getPluginManager().callEvent(event);
		
		if (!event.isCancelled()) {
			if (isInDatabase(player)) {
				PreparedStatement preparedStatement = ExoahCoins.getExoahCoins().getConnection().prepareStatement("UPDATE coins SET amount = ? WHERE UUID = ?");
				preparedStatement.setInt(1, (getCoins(player) - amount));
				preparedStatement.setString(2, PlayerUtils.getUUID(player.getName()));
				preparedStatement.executeUpdate();
				
				preparedStatement.close();
				
				player.sendMessage(ChatColor.RED + "You have lost " + amount + " coins.");
			}
		} else {
			player.sendMessage(ChatColor.GREEN + "You didn't lose any coins.");
		}
	}
	
}
