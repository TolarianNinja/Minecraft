package com.gmail.thecarninja6.deathcoordinates;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

	@EventHandler
	public void playerDeath(PlayerDeathEvent event) {
		event.getEntity().sendMessage(
				ChatColor.RED + "You died at " + locationCoordinates(event));
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (p.isOp() && !p.equals(event.getEntity().getPlayer()))
				p.sendMessage(ChatColor.RED + event.getEntity().getName()
						+ " died at " + locationCoordinates(event));
		}
	}

	public String locationCoordinates(PlayerDeathEvent event) {
		String output = "[";
		output += event.getEntity().getLocation().getBlockX() + ", ";
		output += event.getEntity().getLocation().getBlockY() + ", ";
		output += event.getEntity().getLocation().getBlockZ() + "].";
		return output;
	}
}
