package me.tr.survival.commands.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		
		if(!(arg0 instanceof Player)){ return true; }
		
		if(arg3.length == 0){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "essentials:spawn " + arg0.getName());
		}
		else{
			return true;
		}
		
		return true;
	}
	
}
