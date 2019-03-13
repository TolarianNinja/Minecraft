package com.gmail.thecarninja6.deathcoordinates;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public final class DeathCoordinates extends JavaPlugin {
	public final DeathListener deathListener = new DeathListener();
	
	@Override
    public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		//getLogger().info("DeathCoordinates initiated.");
		
		pm.registerEvents(this.deathListener, this);
    }
 
    @Override
    public void onDisable() {
    	getLogger().info("DeathCoordinates disabled.");
    }

}
