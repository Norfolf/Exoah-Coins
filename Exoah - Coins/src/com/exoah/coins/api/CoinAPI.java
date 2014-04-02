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
package com.exoah.coins.api;

import java.sql.SQLException;

import org.bukkit.entity.Player;

import com.exoah.coins.utils.CoinUtils;

/**
 * @author Sebastiaan
 *
 */
public final class CoinAPI {
	
	/**
	 * This 
	 */
	
	private CoinUtils coinUtils = new CoinUtils();
	
	public boolean isInDatabase(Player player) throws SQLException {
		return coinUtils.isInDatabase(player);
	}
	
	public int getCoins(Player player) throws SQLException {
		return coinUtils.getCoins(player);
	}
	
	public void giveCoins(Player player, int amount) throws SQLException {
		coinUtils.giveCoins(player, amount);
	}
	
	public void takeCoins(Player player, int amount) throws SQLException {
		coinUtils.takeCoins(player, amount);
	}
	
}
