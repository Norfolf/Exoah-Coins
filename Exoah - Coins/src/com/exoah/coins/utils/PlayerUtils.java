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

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author Sebastiaan
 *
 */
public class PlayerUtils {

	
	/**
	 * Returns the Universally Unique Identifier of a player
	 * 
	 * @param player What is the player's Universally Unique Identifier?
	 * @return
	 */
	public static String getUUID(String player) {
		String uuid = null;
		
		try {
			URL url = new URL("https://uuid.swordpvp.com/uuid/" + player);
			URLConnection uc = url.openConnection();
			uc.setUseCaches(false);
			uc.setDefaultUseCaches(false);
			uc.addRequestProperty("User-Agent", "Mozilla/5.0");
			uc.addRequestProperty("Cache-Control", "no-cache, no-store, must-revalidate");
			uc.addRequestProperty("Pragma", "no-cache");

			String json = new Scanner(uc.getInputStream(), "UTF-8").useDelimiter("\\A").next();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			uuid = (String) ((JSONObject) ((JSONArray) ((JSONObject) obj).get("profiles")).get(0)).get("id");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return uuid;
	}
	
}