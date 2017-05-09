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
		
		if(label.equalsIgnoreCase("ban")){
			
			if(args.length == 1){
				
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				
				StringBuilder sb = new StringBuilder();
				for (int i =1; i<args.length; i++){
					sb.append(args[i]).append(" ");
				}
				String allArgs = sb.toString().trim();
				
				if(target.isOnline()){
					
					target.getPlayer().kickPlayer("§cSinulla on porttikielto serverille! \n §7Porttikiellon antoi: §c" + sender.getName() + "§7! \n Syy: §c" + allArgs);
					Main.getAPI().getPunishmentManager().setBlacklisted(target.getName(), true, sender.getName(), allArgs);
					
				}else{
					
					Main.getAPI().getPunishmentManager().setBlacklisted(target.getName(), true, sender.getName(), allArgs);
					
				}
				
			}
			else{
				return true;
			}
			
		}
		else if(label.equalsIgnoreCase("unban")){
			
			if(args.length == 1){
				
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				
				if(Main.getAPI().getPunishmentManager().isBlacklisted(target.getName())){
					Main.getAPI().getPunishmentManager().setBlacklisted(target.getName(), false, sender.getName());
				}
				else if(Main.getAPI().getPunishmentManager().isBanned(target.getName())){
					Main.getAPI().getPunishmentManager().unbanPlayer(target.getName());
				}
				
			}
			else{
				return true;
			}	
			
		}
		
		return true;
	}
	
}
