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
package com.exoah.coins.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author Sebastiaan
 *
 */
public final class PlayerReceiveCoinsEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
    
    private Player player;
    private int amount;
    
    private boolean cancelled;
 
    public PlayerReceiveCoinsEvent(Player player, int amount) {
    	this.player = player;
    }
    
    public Player getPlayer() {
    	return player;
    }
    
    public int getAmount() {
    	return amount;
    }
 
    public boolean isCancelled() {
        return cancelled;
    }
 
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
 
    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
