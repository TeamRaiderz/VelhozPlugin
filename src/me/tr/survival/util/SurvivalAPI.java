package me.tr.survival.util;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.tr.survival.util.punishments.PunishmentManager;
import net.md_5.bungee.api.ChatColor;

public class SurvivalAPI {

	public static long getFreeMemory(){
    	Runtime r = Runtime.getRuntime();
    	return r.freeMemory() / 1024L / 1024L;
    }
    public static long getMaxMemory(){
    	Runtime r = Runtime.getRuntime();
    	return r.maxMemory() / 1024L / 1024L;
    }
    public static long getTotalMemory(){
    	Runtime r = Runtime.getRuntime();
    	return r.totalMemory() / 1024L / 1024L;
    }
	
    public static int getBannedPlayers(){
    	return Bukkit.getServer().getBannedPlayers().size() + Bukkit.getServer().getIPBans().size();
    }
    
    public PunishmentManager getPunishmentManager(){
    	return new PunishmentManager();
    }
    
    public void createItem(Inventory inv, int pos, Material mat, int amount, String displayName, List<String> lore){
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		meta.setLore(lore);
		item.setAmount(amount);
		item.setItemMeta(meta);
		inv.setItem(pos, item);
	}
	
	public void createWoolItem(Inventory inv, int pos, DyeColor color, String displayName, List<String> lore){
		ItemStack wool = new ItemStack(Material.WOOL, 1, color.getData());
		ItemMeta meta = wool.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		meta.setLore(lore);
		wool.setItemMeta(meta);
		inv.setItem(pos, wool);
	}
	
	public ItemStack makeWoolItem(DyeColor color, String displayName){
		ItemStack wool = new ItemStack(Material.WOOL, 1, color.getData());
		ItemMeta meta = wool.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		wool.setItemMeta(meta);
		return wool;
	}
	
	public ItemStack makeWoolItem(DyeColor color, String displayName, List<String> lore){
		ItemStack wool = new ItemStack(Material.WOOL, 1, color.getData());
		ItemMeta meta = wool.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		meta.setLore(lore);
		wool.setItemMeta(meta);
		return wool;
	}
    
}
