package com.gmail.thecarninja6.afk;

import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AfkSetters {
	static public HashMap<Player, AfkInfo> afkPlayers;

	// Test Server
	// private Location afkZone = new
	// Location(Bukkit.getServer().getWorld("world"), 75, 5, 334);

	// Ft Awesome
	private Location afkZone = new Location(Bukkit.getServer().getWorld(
			"03141980081179081084093081"), 75, 6, 336);

	private class AfkInfo {
		private Location prevLoc;
		private String reason;
		private Date time;

		public AfkInfo(Location prevLoc, String reason) {
			this.prevLoc = prevLoc;
			this.reason = reason;
			this.time = new Date();
		}

		public Location getPrevLocation() {
			return prevLoc;
		}

		public String getAfkReason() {
			return reason;
		}

		public String afkTimestamp() {
			return time.toString();
		}
	}

	public AfkSetters() {
		afkPlayers = new HashMap<Player, AfkInfo>();
	}

	public HashMap<Player, AfkInfo> getMap() {
		return afkPlayers;
	}

	public Location getPrevLocation(Player player) {
		return afkPlayers.get(player).getPrevLocation();
	}

	public void addToMap(Player player) {
		AfkInfo addedPlayer = new AfkInfo(player.getLocation(), "");
		afkPlayers.put(player, addedPlayer);
	}

	public String afkReason(Player player) {
		return afkPlayers.get(player).getAfkReason();
	}

	public String afkTimestamp(Player player) {
		return afkPlayers.get(player).afkTimestamp();
	}

	public void ToggleAfk(Player player, String[] args) {
		if (player.getDisplayName().startsWith("AFK")) {
			Location l = afkPlayers.get(player).getPrevLocation();
			if (!l.getChunk().isLoaded())
				l.getChunk().load();
			player.teleport(l);
			player.setDisplayName(player.getName());
			player.setPlayerListName(player.getName());
			Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName()
					+ " has returned to the keyboard.");
			afkPlayers.remove(player);
		} else
			SetAfk(player, args);
	}

	public void SetAfk(Player player, String[] args) {
		String reason = "";
		if (args.length > 0) {
			for (String s : args) {
				reason += s + " ";
			}
		}
		if (player.getName().equalsIgnoreCase("ramblingpariah")
				&& reason.equals("Idle ")) {
			Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName()
					+ " has fallen asleep at the keyboard.");
		} else if (!reason.equals("")) {
			Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName()
					+ " has left the keyboard: " + ChatColor.WHITE + reason);
		} else {
			Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName()
					+ " has left the keyboard.");
		}
		if (player.getName().length() <= 12)
			player.setDisplayName("AFK " + player.getName());
		else
			player.setDisplayName("AFK " + player.getName().substring(0, 12));
		//player.setPlayerListName(ChatColor.YELLOW + player.getName());
		afkPlayers.put(player, new AfkInfo(player.getLocation(), reason));
		if (!afkZone.getChunk().isLoaded()) {
			afkZone.getChunk().load();
		}
		player.teleport(afkZone);
	}
}
