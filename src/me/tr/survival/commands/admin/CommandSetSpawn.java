package me.tr.survival.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.tr.survival.util.ChatUtils;

public class CommandSetSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender.isOp())){
			ChatUtils.sendPermissionMessageAdmin(sender);
		}
		
		return true;
	}

}
