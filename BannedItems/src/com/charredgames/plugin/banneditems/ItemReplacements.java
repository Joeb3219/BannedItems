package com.charredgames.plugin.banneditems;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ItemReplacements {

	public static boolean scanStarted = false;
	
	@SuppressWarnings("deprecation")
	public void scan(Server server){
		Player[] players = Bukkit.getOnlinePlayers();
		for(Player player : players){
			if(player.isOp()) continue;
			
			PlayerInventory inv = player.getInventory();
			Iterator<ItemStack> iterator = inv.iterator();
			while(iterator.hasNext()){
				try{
					ItemStack item = iterator.next();
					int itemId = item.getTypeId();
					int itemData = item.getData().getData();
					BannedItem bannedItem = BannedItem.getBannedItem(itemId + ":" + itemData);
					if(bannedItem != null){
						inv.removeItem(item);
						player.updateInventory();
						for(Player op : Bukkit.getOnlinePlayers()){
							if(op.isOp()) op.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "BannedItems" + ChatColor.WHITE + "] " + ChatColor.GRAY + "Contraband " + ChatColor.RED + bannedItem.getName() + ChatColor.GRAY + " removed from player " + ChatColor.RED + player.getName());
						}
						player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "BannedItems" + ChatColor.WHITE + "] " + ChatColor.GRAY + "Contraband " + ChatColor.RED + bannedItem.getName() + ChatColor.GRAY + " removed!");
					}
					
				}catch(Exception e){/* That wasn't supposed to happen! :( */}
			}
			
		}
	}
	
}
