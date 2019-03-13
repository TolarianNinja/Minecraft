package com.gmail.thecarninja6.afk;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class PlayerIdleTracker implements Runnable {

	final Server server;

	PlayerIdleTracker(Server server) {
		this.server = server;
	}

	private static HashMap<String, Long> lastActiveMap = new HashMap<String, Long>();
	private static HashMap<String, Location> lastLocationMap = new HashMap<String, Location>();
	private AfkSetters setter = new AfkSetters();

	public void run() {
		for (Player player : server.getOnlinePlayers()) {
			long currentTime = System.currentTimeMillis();
			String playerName = player.getName();
			Location loc = player.getLocation();
			Location lastLocation = lastLocationMap.get(playerName);
			if (/* lastActiveMap.get(player) == null || */lastLocation == null
					|| !lastLocation.equals(player.getLocation())) {
				lastActiveMap.put(playerName, currentTime);
				lastLocationMap.put(playerName, loc);
			}
			if (getIdleTime(player) > 300
					&& !player.getDisplayName().startsWith("AFK")) {
				player.sendMessage(ChatColor.AQUA
						+ "The world around you swirls and pulls you into a limbo.");
				player.sendMessage("Type /afk to return to your previous location.");
				String[] idle = { "Idle" };
				setter.SetAfk(player, idle);
			}
		}
	}

	public void playerTookAnAction(Player player) {
		lastActiveMap.put(player.getName(), System.currentTimeMillis());
	}

	public Long getIdleTime(Player player) {
		Long lastActive = lastActiveMap.get(player.getName());
		if (lastActive == null) {
			return null;
		} else {
			return (System.currentTimeMillis() - lastActive) / 1000;
		}
	}

	public void returnIdle(Player player) {
		if (getIdleTime(player) != null && getIdleTime(player) > 300) {
			player.sendMessage("You went AFK at " + setter.afkTimestamp(player));
			player.sendMessage("Welcome back. You have been idle for "
					+ getIdleString(player));
			player.sendMessage("Type /afk to return to your previous location.");
		}
	}

	public String getIdleString(Player player) {
		long seconds = getIdleTime(player);
		String output = "";
		if (seconds / 3600 > 1) {
			output += "" + (seconds / 3600) + " hours, ";
		} else if (seconds / 3600 > 0) {
			output += "" + (seconds / 3600) + " hour, ";
		}
		seconds %= 3600;
		if (seconds / 60 > 1) {
			output += "" + (seconds / 60) + " minutes ";
		} else if (seconds / 60 > 0) {
			output += "" + (seconds / 60) + " minute ";
		}
		seconds %= 60;
		if (seconds > 1) {
			output += "and " + seconds + " seconds.";
		} else if (seconds > 0) {
			output += "and " + seconds + " second.";
		}
		return output;
	}
}