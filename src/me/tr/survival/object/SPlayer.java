package me.tr.survival.object;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class SPlayer {

	private Player p;
	
	public SPlayer(String name){
		this.p = Bukkit.getPlayer(name);
	}
	
	public SPlayer(CommandSender sender){
		this.p = Bukkit.getPlayer(sender.getName());
	}
	
	public SPlayer(Player p){
		this.p = p;
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public double getHealth(){
		double rawHealth = p.getHealth();
		double health = Math.round(rawHealth * 10.0D) / 10.0D;
		return health;
	}
	
	public Location getLocation(){
		return p.getLocation();
	}
	
	public void sendMessage(String msg){
		p.sendMessage("§7[§a!§7] " + ChatColor.translateAlternateColorCodes('&', msg));
	}
	
	public PlayerInventory getInventory(){
		return p.getInventory();
	}
	
	public void die(){
		if(p.getGameMode() != GameMode.SURVIVAL){
			p.setGameMode(GameMode.SURVIVAL);
		}
	}
	
	public String getName(){
		return p.getName();
	}
	
	public UUID getUniqueID(){
		return p.getUniqueId();
	}
	
	public double getBalance(){
		return 0.0D;
	}
	
}
