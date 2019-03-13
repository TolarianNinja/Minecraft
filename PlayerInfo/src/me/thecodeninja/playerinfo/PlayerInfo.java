package me.thecodeninja.playerinfo;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class PlayerInfo extends JavaPlugin {
	@Override
    public void onEnable(){
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		PluginManager pm = getServer().getPluginManager();
		getCommand("pinfo").setExecutor(new PlayerInfoCommandExecutor(this));
		//getLogger().info("SurvivalFlight initiated.");
		pm.enablePlugin(this);
    }
 
    @Override
    public void onDisable() {
    	getLogger().info("PlayerInfo disabled.");
    }
}
