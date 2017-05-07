package me.tr.survival.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class FastKick {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("fastkick")) {
			if (!sender.hasPermission("fastkick.use")) {
				sender.sendMessage(ChatColor.RED + "Ei oikeutta.");
				return true;
			}
			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED + "Valitse pelaaja!");
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(ChatColor.RED + "Pelaajaa " + args[0] + " ei löydy");
				return true;
			}
			target.kickPlayer("Sinut on kickattu!");
			sender.sendMessage(ChatColor.GREEN + target.getName() + " potkittu onnistuneesti!");
		}
	return true;	
	}

}
