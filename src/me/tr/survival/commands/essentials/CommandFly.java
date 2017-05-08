package me.tr.survival.commands.essentials;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandFly {
	ArrayList<Player> lento = new ArrayList<Player>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (!p.hasPermission("minelogia.essentials.fly")) {
			p.sendMessage(ChatColor.RED + "Ei oikeutta.");
			return true;
		}
		if (!lento.contains(p)) {
			p.setFlying(true);
			p.sendMessage(ChatColor.GREEN + "Lento on nyt p‰‰ll‰!");
			lento.add(p);
			return true;
		}
		
		if (lento.contains(p)) {
			p.setFlying(false);
			p.sendMessage(ChatColor.RED + "Lento on nyt pois p‰‰lt‰!");
			lento.remove(p);
			
		}
		return false;
		
	}

}
