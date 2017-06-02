package me.tr.survival.util;

import org.bukkit.command.CommandSender;

public class ChatUtils {
	
	public static void sendPermissionMessageAdmin(CommandSender sender) {
		sender.sendMessage("§7Tähän komentoon tarvitsen rankin §c§lADMIN§7!");
	}

	public static void sendPermissionMessageMod(CommandSender sender) {
		sender.sendMessage("§7Tähän komentoon tarvitsen rankin §9§lMOD§7!");
	}

	public static void sendPermissionMessageVIP(CommandSender sender) {
		sender.sendMessage("§7Tähän komentoon tarvitsen rankin §6§lVIP§7!");
	}

	public static void sendPermissionMessageYT(CommandSender sender) {
		sender.sendMessage("§7Tähän komentoon tarvitsen rankin §c§lYOUTUBE§7!");
	}

	public static void sendPlayerNotFoundMsg(CommandSender sender, String target) {
		sender.sendMessage("§7En löytänyt pelaajaa §c" + target + "§7...");
	}
}
