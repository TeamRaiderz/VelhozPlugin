package me.tr.survival.customEvents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.tr.survival.object.SPlayer;

public class JoinEvent extends Event{

	private static final HandlerList handlers = new HandlerList();
	
	private SPlayer player;
	
	public JoinEvent(String name){
		player = new SPlayer(name);
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	public SPlayer getPlayer(){
		return player;
	}
	
}
