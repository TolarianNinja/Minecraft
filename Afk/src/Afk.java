package com.gmail.thecarninja6.afk;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.thecarninja6.afk.AfkCommandExecutor;
import com.gmail.thecarninja6.afk.PlayerIdleTracker;
import com.gmail.thecarninja6.afk.AfkListener;

public class Afk extends JavaPlugin {
	AfkSetters afkSet;
    
	@Override
	public void onEnable(){
		PlayerIdleTracker idleTrack = new PlayerIdleTracker(Bukkit.getServer());
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("afk").setExecutor(new AfkCommandExecutor(this));
		//getLogger().info("AFK initiated.");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, idleTrack, 0, 20);
		PluginManager pm = Bukkit.getServer().getPluginManager();
		AfkListener afkListen = new AfkListener();
		pm.registerEvents(afkListen, this);
		afkSet = new AfkSetters();	
    }
 
    @Override
    public void onDisable() {
    	for (Player p : Bukkit.getServer().getOnlinePlayers()) {
    		if (p.getDisplayName().startsWith("AFK")) {
    			afkSet.ToggleAfk(p, null);
    		}
    	}
    	getLogger().info("AFK disabled.");
    }
}
