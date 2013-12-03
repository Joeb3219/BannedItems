package com.charredgames.plugin.banneditems;

import com.charredgames.plugin.banneditems.BannedItems;

public class BannedItem {

	private int id, data;
	private String name;
	
	public BannedItem(String name, int id, int data){
		this.name = name;
		this.id = id;
		this.data = data;
		BannedItems.addBannedItem(this);
	}
	
	public static BannedItem getBannedItem(String identifier){
		for(BannedItem item : BannedItems.bItems){
			String idString = item.id + ":" + item.data;
			if(idString.equalsIgnoreCase(identifier) || identifier.equalsIgnoreCase(item.id + ":" + "-1")) return item;
		}
		return null;
	}
	
	public String getName(){
		return name;
	}
	
}
