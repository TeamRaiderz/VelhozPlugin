package me.tr.survival.util.punishments;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.tr.survival.Main;
import me.tr.survival.util.ChatUtils;
import me.tr.survival.util.SurvivalAPI;

public class PunishCommand implements CommandExecutor, Listener {
	
	String reason;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender.hasPermission("server.mod"))){
			ChatUtils.sendPermissionMessageMod(sender);
			return true;
		}
		
		if(args.length < 2){
			sender.sendMessage("�c/punish (player) (reason)");
			return true;
		}
		else if (args.length >= 2 && !args[0].equalsIgnoreCase("unban")){
			
			if(!(sender instanceof Player)) return true;
			
			StringBuilder sb = new StringBuilder();
			for (int i =1; i<args.length; i++){
				sb.append(args[i]).append(" ");
			}
			String allArgs = sb.toString().trim();
			
			OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
			
			openPunishmentMenu((Player) sender, target.getName(), allArgs);
			this.reason = allArgs;
			
		}
		else if (args.length == 2){
			
			if(args[0].equalsIgnoreCase("unban")){
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
				if(Main.getAPI().getPunishmentManager().isBanned(target.getName())){
					Main.getAPI().getPunishmentManager().unbanPlayer(target.getName());
				}
				sender.sendMessage("�7Unbanned �c" + target.getName() + "�7!");
			}
			
		}
		
		return true;
	}
	
	public void openPunishmentMenu(Player p, String target, String reason){
		
		Inventory inv = Bukkit.createInventory(null, 54, "Punish " + target);
		
		SurvivalAPI api = Main.getAPI();
		
		api.createItem(inv, 13, Material.STAINED_GLASS_PANE, 1, "�c�lPunishments", Arrays.asList("�7Here you can punish the player", "�a" + target + "�7!"));
		api.createItem(inv, 22, Material.BOOK, 1, "�b�lHistory", Arrays.asList("�7Click to see the history of", "�a" + target + "�7!"));
		
		api.createItem(inv, 10, Material.SIGN, 1, "�c�lBAN", Arrays.asList("�7Below there are the", "�7ban time options."));
		api.createWoolItem(inv, 19, DyeColor.GREEN, "�a�l1 Day", Arrays.asList("�7Click to ban �a" + target + " �7for �a1 Day�7 for the reason:", "�a" + reason));
		api.createWoolItem(inv, 28, DyeColor.GREEN, "�a�l1 Week", Arrays.asList("�7Click to ban �a" + target + " �7for �a1 Week�7 for the reason:", "�a" + reason));
		api.createWoolItem(inv, 37, DyeColor.ORANGE, "�6�l1 Month", Arrays.asList("�7Click to ban �a" + target + " �7for �61 Month�7 for the reason:", "�a" + reason));
		
		api.createItem(inv, 16, Material.SIGN, 1, "�c�lOTHER", Arrays.asList("�7Below there are the", "�7other punishments."));
		api.createItem(inv, 25, Material.REDSTONE, 1, "�c�lKick", Arrays.asList("�7Click to kick the player", "�a" + target + " �7for the reason:", "�a" + reason));
		api.createItem(inv, 34, Material.PAPER, 1, "�c�lWarn", Arrays.asList("�7Click to warn the player", "�a" + target + " �7for the reason:", "�a" + reason));
		
		this.reason = reason;
		
		p.openInventory(inv);
		
		
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e){
		
		Player p = (Player) e.getWhoClicked();
		
		if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
		
		ItemStack item = e.getCurrentItem();
		
		if(e.getInventory().getName().contains("Punish ")){
			
			OfflinePlayer off = Bukkit.getOfflinePlayer(e.getInventory().getName().replace("Punish ", ""));
			
			String reason = e.getInventory().getItem(34).getItemMeta().getLore().get(2).replace("�a", "");
			
			e.setCancelled(true);
			
			if(item.getType() == Material.WOOL && item.getData().getData() == DyeColor.GREEN.getData()){
				p.closeInventory();
				Main.getAPI().getPunishmentManager().tempBanDays(off.getName(), 1, p.getName(), reason, 86400000);
				p.sendMessage("�7The player �c" + off.getName() + " �7has been banned for �c1 Day�7!");
			}
			else if(item.getType() == Material.WOOL && item.getData().getData() == DyeColor.ORANGE.getData()){
				p.closeInventory();
				Main.getAPI().getPunishmentManager().tempBanDays(off.getName(), 30, p.getName(), reason, 86400000);
				p.sendMessage("�7The player �c" + off.getName() + " �7has been banned for �c30 days�7!");
			}
			else if(item.getType() == Material.WOOL && item.getData().getData() == DyeColor.RED.getData()){
				p.closeInventory();
				Main.getAPI().getPunishmentManager().tempBanDays(off.getName(), 90, p.getName(), reason, 86400000);
				p.sendMessage("�7The player �c" + off.getName() + " �7has been banned for �c3 Months�7!");
			}
			else if(item.getType() == Material.REDSTONE){
				if(off.isOnline()){
					p.closeInventory();
					Main.getAPI().getPunishmentManager().kickPlayer(off.getName(), reason);
					p.sendMessage("�7The player �c" + off.getName() + " �7has been kicked!");
				}
				else{
					p.closeInventory();
					p.sendMessage("�cThat player is not online!");
				}
			}
			else if(item.getType() == Material.PAPER){
				p.closeInventory();
				Main.getAPI().getPunishmentManager().warnPlayer(off.getName(), reason, p);
			}
			else if(item.getType() == Material.BOOK){
				p.closeInventory();
//				Main.getAPI().getPunishmentManager().getHistory(p, off.getName());
			}
			else{ return; }
			
			
		}
		
	}

}
