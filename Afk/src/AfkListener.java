package com.gmail.thecarninja6.afk;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AfkListener implements Listener {
	PlayerIdleTracker idleTrack = new PlayerIdleTracker(Bukkit.getServer());
	AfkSetters afkMap = new AfkSetters();
	
	@EventHandler
	public void PlayerAction(PlayerMoveEvent event) {
		idleTrack.returnIdle(event.getPlayer());
		idleTrack.playerTookAnAction(event.getPlayer());
	}
	@EventHandler
	public void PlayerAction(PlayerInteractEvent event) {
		idleTrack.returnIdle(event.getPlayer());
		idleTrack.playerTookAnAction(event.getPlayer());
	}
	@EventHandler
	public void PlayerAction(PlayerCommandPreprocessEvent event) {
		idleTrack.returnIdle(event.getPlayer());
		idleTrack.playerTookAnAction(event.getPlayer());
	}
	@EventHandler
	public void PlayerAction(AsyncPlayerChatEvent event) {
		idleTrack.returnIdle(event.getPlayer());
		idleTrack.playerTookAnAction(event.getPlayer());
	}
	@EventHandler
	public void PlayerAction(PlayerLoginEvent event) {
		afkMap.addToMap(event.getPlayer());
		idleTrack.playerTookAnAction(event.getPlayer());
	}
	@EventHandler
	public void PlayerLogout(PlayerQuitEvent event) {
		if (event.getPlayer() != null && event.getPlayer().getDisplayName().startsWith("AFK")) {
			event.getPlayer().teleport(afkMap.getPrevLocation(event.getPlayer()));
		}
		if (afkMap.getMap().get(event.getPlayer()) != null) {
			afkMap.getMap().remove(event.getPlayer());			
		}
	}
}