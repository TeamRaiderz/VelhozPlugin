package me.tr.survival.util.punishments;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.tr.survival.Main;
import me.tr.survival.util.ChatUtils;

public class BlacklistCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender.isOp())){
			ChatUtils.sendPermissionMessageAdmin(sender);
			return true;
		}
		
		if(label.equalsIgnoreCase("blacklist")){
			
			if(args.length == 1){
				
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				
				if(target.isOnline()){
					
					target.getPlayer().kickPlayer("§cYou have been blacklisted from this server! \n §7You were blacklisted by: §c" + sender.getName() + "§7!");
					Main.getAPI().getPunishmentManager().setBlacklisted(target.getName(), true, sender.getName());
					
				}else{
					
					Main.getAPI().getPunishmentManager().setBlacklisted(target.getName(), true, sender.getName());
					
				}
				
			}
			else{
				return true;
			}
			
		}
		else if(label.equalsIgnoreCase("unblacklist")){
			
			if(args.length == 1){
				
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				
				Main.getAPI().getPunishmentManager().setBlacklisted(target.getName(), false, sender.getName());
			}
			else{
				return true;
			}	
			
		}
		
		return true;
	}
	
}
