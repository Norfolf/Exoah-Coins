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
package com.exoah.coins;

import java.sql.Connection;

import org.bukkit.plugin.java.JavaPlugin;

import com.exoah.coins.database.MySQL;

/**
 * @author Sebastiaan
 *
 */
public class ExoahCoins extends JavaPlugin {

	private static ExoahCoins instance;
	
	private MySQL mySQL;
	private Connection connection;
	
	public void onEnable() {
		instance = this;
	
		mySQL = new MySQL(this, "host.name", "port", "database", "user", "pass");
		connection = mySQL.openConnection();
	}
	
	public void onDisable() {}
	
	/**
	 * Returns the ExoahCoins instance
	 * 
	 * @return The ExoahCoins instance
	 */
	public static ExoahCoins getExoahCoins() {
		return instance;
	}
	
	/**
	 * Returns the MySQL instance
	 * 
	 * @return The MySQL instance
	 */
	public MySQL getMySQL() {
		return mySQL;
	}
	
	/**
	 * Returns the database connection
	 * 
	 * @return The database connection
	 */
	public Connection getConnection() {
		return connection;
	}
	
}
