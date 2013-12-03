package com.charredgames.plugin.banneditems;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

public class BannedItems extends JavaPlugin{
public static ArrayList<BannedItem> bItems = new ArrayList();
private static Server _SERVER;
private static ItemReplacements _SCANNER;

	public void onLoad(){
		_SERVER = getServer();
		_SCANNER = new ItemReplacements();
		super.onLoad();
	}
	
	public void onEnable(){
		addBannedItems();
		removeRecipes();
		startReplacementScan();
		super.onEnable();
	}
	
	public void onDisable(){
		super.onDisable();
	}
	
	private void addBannedItems(){
		new BannedItem("Canvas Bag", 5258, 0);
		new BannedItem("Wrath Igniter", 9263, 0);
		new BannedItem("Dynamite", 29998, 6);
		new BannedItem("Stick Dynamite", 30214, 0);
		new BannedItem("Dynamite", 30215, 0);
		new BannedItem("Unifier", 3131, 8);
		new BannedItem("Material Emancipation Grid", 13462, 14);
		new BannedItem("Letter", 13385, 0);
		new BannedItem("World Anchor", 1053, 0);
		new BannedItem("Letter", 243, 1);
	}
	
	private void startReplacementScan(){
		if (ItemReplacements.scanStarted) {
			return;
		}
		_SERVER.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() 
		{
			public void run() 
			{
				_SCANNER.scan(_SERVER);
			}
		},100L, 150L);
		
		ItemReplacements.scanStarted = true;
	}
	
	private void removeRecipes(){
		int removed = 0;
		Iterator<Recipe> recipes = Bukkit.recipeIterator();
		while (recipes.hasNext()) {
			try{
				Recipe item = (Recipe)recipes.next();
				if (item.getResult().getData() != null){
					int itemId = item.getResult().getTypeId();
					int itemData = item.getResult().getData().getData();
					BannedItem bannedItem = BannedItem.getBannedItem(itemId + ":" + itemData);
					if (bannedItem != null){
						recipes.remove();
						removed++;
					}
				}
			}
			catch (Exception e){e.printStackTrace();}
		}
		System.out.println(removed + " item recipe[s] removed!");
	}
	
	public static void addBannedItem(BannedItem item){
		bItems.add(item);
	}
}