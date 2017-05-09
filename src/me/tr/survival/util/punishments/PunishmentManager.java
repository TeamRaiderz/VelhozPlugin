package me.tr.survival.util.punishments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import me.tr.survival.Main;
import me.tr.survival.util.files.FileManager;
import me.tr.survival.util.files.PlayerData;

public class PunishmentManager implements Listener{
	
	public void setBlacklisted(String target, boolean value, String punisher, String reason){
		FileManager dataFile = PlayerData.getPlayerFile(target);
		dataFile.set("blacklist.blacklisted", value);
		dataFile.set("blacklist.punisher", punisher);
		dataFile.set("blacklist.reasom", reason);
		PlayerData.savePlayerFile(target);
	}
	
	public boolean isBlacklisted(String target){
		return PlayerData.getPlayerFile(target).getBoolean("blacklist.blacklisted");
	}
	
	public void tempBanDays(String target, int time, String punisher, String reason, int multi) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(target);

		if (isBanned(p.getName())) {
			Bukkit.getPlayer(punisher).sendMessage("§7That player is already banned!");
			return;
		}

		long endOfBan = (System.currentTimeMillis() / 1000) + (24 * 60 * 60) * time;

		FileManager dataFile = PlayerData.getPlayerFile(target);

		dataFile.set("ban.punished", true);
		dataFile.set("ban.banTime", endOfBan);
		dataFile.set("ban.punisher", punisher);
		dataFile.set("ban.reason", reason);
		PlayerData.savePlayerFile(target);

		if (p.isOnline()) {
			p.getPlayer().kickPlayer("§cSinulla on väliaikainen porttikielto serverille! \n §7Syy: §c" + reason + " \n §7Porttikiellon antoi: §c" + punisher +
					"\n §7Porttikielto päättyy: §c" + getBanTimeLeft(p.getName().toString()));
		}

	}
	
	public void unbanPlayer(String target){
		FileManager dataFile = PlayerData.getPlayerFile(target);
		dataFile.set("ban.punished", false);
		dataFile.set("ban.banTime", 0);
		dataFile.set("ban.punisher", "");
		dataFile.set("ban.reason", "");
		PlayerData.savePlayerFile(target);
	}
	
	public boolean isBanned(String player){
		OfflinePlayer p = Bukkit.getOfflinePlayer(player);
		return PlayerData.getPlayerFile(player).getBoolean("ban.punished");
	}
	
	
	public String getBanTimeLeft(String player) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(player);
		
		long current = (System.currentTimeMillis()/1000);
	    long endOfban = getEndOfBan(p.getName()).longValue();
	    long millis = endOfban - current;

	    int seconds = 0;
	    int minutes = 0;
	    int hours = 0;
	    int days = 0;

	    while (millis > 1L) {
	      millis -= 1L;
	      seconds++;
	    }
	    while (seconds > 60) {
	      seconds -= 60;
	      minutes++;
	    }
	    while (minutes > 60) {
	      minutes -= 60;
	      hours++;
	    }
	    while (hours > 24) {
	      hours -= 24;
	      days++;
	    }
		    
		    return days + "d " + hours + "h " + minutes + "min " + seconds + "s";
	}
	
	public Long getEndOfBan(String player){
		return Long.valueOf(PlayerData.getPlayerFile(player).getString("ban.banTime")).longValue();
	}

	public String setReason(String[] args, int start) {
	    String reason = "";
	    for (int i = start; i < args.length; i++) {
	      reason = reason + args[i] + " ";
	    }

	    return reason;
	  }
	
	public void warnPlayer(String target, String reason, Player punisher){
		OfflinePlayer p = Bukkit.getOfflinePlayer(target);
		
		if(p.isOnline()){
			p.getPlayer().kickPlayer("§cYou have been warned! \n §7Warned by: §c" + punisher.getName() + " \n §7Reason: §c" + reason);
		}
		
	}
	public void kickPlayer(String target, String reason){
		Bukkit.getPlayer(target).kickPlayer("§cYou have been kicked! \n §7Reason: §c" + reason);
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		
		Player p = e.getPlayer();
		
		long current = System.currentTimeMillis() / 1000;
		long end = getEndOfBan(p.getName()).longValue();
		
		if(isBlacklisted(p.getName())){
			String punisher = PlayerData.getPlayerFile(p.getName()).getString("blacklist.punisher");
			String reason = PlayerData.getPlayerFile(p.getName()).getString("blacklist.reason");
			e.disallow(Result.KICK_BANNED, "§cSinulla on porttikielto serverille! \n §7Porttikiellon antoi: §c" + punisher + "§7! \n Syy: §c" + reason);
		}
		else if (isBanned(p.getName())){
			String punisher = PlayerData.getPlayerFile(p.getName()).getString("ban.punisher");
			String reason = PlayerData.getPlayerFile(p.getName()).getString("ban.reason");
			
			if(current < end){
				e.disallow(Result.KICK_BANNED, "§cSinulla on väliaikainen porttikielto serverille! \n §7Syy: §c" + reason + " \n §7Porttikiellon antoi: §c" + punisher +
						"\n §7Porttikielto päättyy: §c" + getBanTimeLeft(p.getName().toString()));
			}
			else if (end < current){
				unbanPlayer(p.getName());
			}
			
		}
		
	}

	public String getDateFormat(long millis) {
		Date date = new Date(millis);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(date);
	}
	
}
