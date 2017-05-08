package me.tr.survival.commands.essentials;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTop {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
	if (!p.hasPermission("minelogia.essentials.toptop")) {
		p.sendMessage(ChatColor.RED + "Ei oikeutta.");
	}
	if (label.equalsIgnoreCase("top")) {
		double xtop = p.getLocation().getX();
		double ztop = p.getLocation().getZ();
		double ytop = p.getWorld().getHighestBlockYAt(p.getLocation());
		World w = p.getWorld();
		p.teleport(new Location(w, xtop, ytop, ztop));
		p.sendMessage(ChatColor.GREEN + "Sinut teleportattiin ylimmälle palikalle!");
		
	}
	return false;
	}
}
