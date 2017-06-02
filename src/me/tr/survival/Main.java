package me.tr.survival;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.tr.survival.commands.essentials.CommandSpawn;
import me.tr.survival.util.SurvivalAPI;
import me.tr.survival.util.punishments.BlacklistCommand;
import me.tr.survival.util.punishments.PunishCommand;

public class Main extends JavaPlugin{

	private static Main instance;
	
	public void onEnable(){
		
		instance = this;
		
		registerCommand("ban", new BlacklistCommand());
		registerCommand("punish", new PunishCommand());
		
		System.out.println("Plugin on nyt p‰‰ll‰!");
		
	}
	
	public void onDisable(){
		
		instance = null;
		
		System.out.println("Plugin on nyt pois p‰‰lt‰!");
		// Test
	}
	
	public static Main getInstance(){ return instance; }
	
	private void registerCommand(String cmd, CommandExecutor cmdClass){
		getCommand(cmd).setExecutor(cmdClass);
	}
	
	private void registerListener(Listener listener){
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
	public static SurvivalAPI getAPI(){
		return new SurvivalAPI();
	}
	
}
