package me.tr.survival.util.punishments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.scheduler.BukkitRunnable;

public class PunishmentManager implements Listener{
	
	private HashMap<String, Integer> bans = new HashMap<String, Integer>();
	private HashMap<String, Integer> warnings = new HashMap<String, Integer>();
	private HashMap<String, Integer> kicks = new HashMap<String, Integer>();
	
	public void setBlacklisted(String target, boolean value, String punisher){
		OfflinePlayer p = Bukkit.getOfflinePlayer(target);
//		Main.getDataFile().set(p.getUniqueId().toString() + ".blacklist.blacklisted", value);
//		Main.getDataFile().set(p.getUniqueId().toString() + ".blacklist.punisher", punisher);
//		Main.saveDataFile();
	}
	
	public boolean isBlacklisted(String target){
		OfflinePlayer p = Bukkit.getOfflinePlayer(target);
		return false;
	}
	
	public void tempBanDays(String target, int time, String punisher, String reason, int multi){
		OfflinePlayer p = Bukkit.getOfflinePlayer(target);
		
		if(isBanned(p.getName())){
			Bukkit.getPlayer(punisher).sendMessage("§7That player is already banned!");
			return;
		}
		
		    long endOfBan = (System.currentTimeMillis()/1000) + (24 * 60 * 60) * time;
		    
//		Main.getDataFile().set(p.getUniqueId().toString() + ".ban.punished", true);
//		Main.getDataFile().set(p.getUniqueId().toString() + ".ban.banTime", endOfBan);
//		Main.getDataFile().set(p.getUniqueId().toString() + ".ban.punisher", punisher);
//		Main.getDataFile().set(p.getUniqueId().toString() + ".ban.reason", reason);
//		Main.saveDataFile();
		
		if(p.isOnline()){
			p.getPlayer().kickPlayer("§cYou have been banned from this server! \n §7Reason: §c" + reason + " \n §7Banned by: §c" + punisher +
					"\n §7You will be unbanned in: §c" + getBanTimeLeft(target));
		}
		
	}
	
	public void unbanPlayer(String target){
		OfflinePlayer p = Bukkit.getOfflinePlayer(target);
//		Main.getDataFile().set(p.getUniqueId().toString() + ".ban.punished", false);
//		Main.getDataFile().set(p.getUniqueId().toString() + ".ban.banTime", 0);
//		Main.getDataFile().set(p.getUniqueId().toString() + ".ban.punisher", "");
//		Main.getDataFile().set(p.getUniqueId().toString() + ".ban.reason", "");
//		Main.saveDataFile();
	}
	
	public boolean isBanned(String player){
		OfflinePlayer p = Bukkit.getOfflinePlayer(player);
		String uuid = p.getUniqueId().toString();
		return false;
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
		OfflinePlayer p = Bukkit.getOfflinePlayer(player);
		return Long.valueOf(0);
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
			String punisher = "";
			e.disallow(Result.KICK_BANNED, "§cYou have been blacklisted from this server! \n §7You were blacklisted by: §c" + punisher + "§7!");
		}
		else if (isBanned(p.getName())){
			String punisher = "";
			String reason = "";
			
			if(current < end){
				e.disallow(Result.KICK_BANNED, "§cYou have been banned from this server! \n §7Reason: §c" + reason + " \n §7Banned by: §c" + punisher +
						"\n §7You will be unbanned in: §c" + getBanTimeLeft(p.getName().toString()));
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
