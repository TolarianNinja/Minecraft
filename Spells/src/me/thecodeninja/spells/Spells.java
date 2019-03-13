package me.thecodeninja.spells;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Spells extends JavaPlugin {
	public final SpellsListener spellListen = new SpellsListener();

	@Override
    public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		getLogger().info("Spells initiated.");
		
		pm.registerEvents(this.spellListen, this);
    }
 
    @Override
    public void onDisable() {
    	getLogger().info("Spells disabled.");
    }
}
