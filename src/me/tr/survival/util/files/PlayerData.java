package me.tr.survival.util.files;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerData {

	static String player;
	
	public static File playerFile = null;
	public static YamlConfiguration pData = null;
	
	@SuppressWarnings("deprecation")
	public static void reloadFile(String player){
		
		FileManager.getPlayerDataFile(Bukkit.getOfflinePlayer(player)).reload();
	
	}
	
	public static FileManager getPlayerFile(String player){
		return FileManager.getPlayerDataFile(Bukkit.getOfflinePlayer(player));
	}
	
	public static void savePlayerFile(String player){
		
		PlayerData.player = player;
		
		if(pData == null || playerFile == null){
			return;
		}
		FileManager.getPlayerDataFile(Bukkit.getOfflinePlayer(player)).save();
		
	}
	
}
