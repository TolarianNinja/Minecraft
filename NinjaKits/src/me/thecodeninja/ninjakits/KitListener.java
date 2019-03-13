package me.thecodeninja.ninjakits;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class KitListener implements Listener {

	KitController kitControl;
	NinjaKits plugin;

	KitListener(NinjaKits plugin) {
		this.plugin = plugin;
		this.kitControl = new KitController(plugin);
	}

	@EventHandler
	public void itemCrafted(CraftItemEvent event) {
		Player p = (Player) event.getWhoClicked();
		for (Kit k : kitControl.getMainList()) {
			if (k.getCrafter().equals(event.getCurrentItem())) {
				if (kitControl.getPlayerKit(p, k.getName()) == null)
					p.sendMessage("You've unlocked the " + k.getName()
							+ " kit.");
				else
					p.sendMessage("You added another " + k.getName()
							+ " kit to your total.");
				kitControl.addKit(p, k);
			}
		}
	}

	@EventHandler
	public void PlayerLogin(PlayerJoinEvent event) {
		PlayerLoad(event.getPlayer());
	}

	@EventHandler
	public void PlayerLogout(PlayerQuitEvent event) {
		PlayerSave(event.getPlayer());
	}
	
	public void PlayerLoad(Player player) {
		File playerKit = new File(plugin.getDataFolder(), player.getName()
				+ "-kit.txt");
		List<Kit> playerKits = new ArrayList<Kit>();
		Kit input;
		String fileInput;
		String inputName;
		int inputCount;
		if (!playerKit.exists()) {
			try {
				if (playerKit.createNewFile()) {
					List<Kit> blank = new ArrayList<Kit>();
					kitControl.getKitMap().put(player, blank);
					kitControl.addDefaults(player);
				}
			} catch (Exception e) {
				Bukkit.getServer().broadcastMessage("Exception Error: " + e);
				Bukkit.getServer().broadcastMessage("Inform TheCodeNinja.");
			}
		} else {
			try {
				Scanner fileStream = new Scanner(playerKit);
				while (fileStream.hasNextLine()) {
					fileInput = fileStream.nextLine();
					inputName = fileInput.substring(0, fileInput.indexOf(' '));
					inputCount = Integer.parseInt(fileInput.substring(fileInput
							.indexOf(' ') + 1));
					if (kitControl.getMasterKit(inputName) != null) {
						input = kitControl.getMasterKit(inputName);
						input.setCount(inputCount);
						playerKits.add(input);
					}
				}
				KitController.kitMap.put(player, playerKits);
				fileStream.close();

			} catch (FileNotFoundException e1) {
				Bukkit.getServer().broadcastMessage("Exception Error: " + e1);
				Bukkit.getServer().broadcastMessage("Inform TheCodeNinja.");
			}
		}
	}

	public void PlayerSave(Player player) {
		File playerKit = new File(plugin.getDataFolder(), player.getName()
				+ "-kit.txt");
		List<Kit> playerKits = kitControl.getPlayerList(player);
		String output;
		if (!playerKit.exists()) {
			try {
				if (playerKit.createNewFile()) {
					List<Kit> blank = new ArrayList<Kit>();
					kitControl.getKitMap().put(player, blank);
					kitControl.addDefaults(player);
				}
			} catch (Exception e) {
				Bukkit.getServer().broadcastMessage("Exception Error: " + e);
				Bukkit.getServer().broadcastMessage("Inform TheCodeNinja.");
			}
		} else {
			try {
				PrintWriter filePrint = new PrintWriter(playerKit);
				for (Kit k : playerKits) {
					output = "";
					output += k.getName() + " ";
					output += k.getCountCurrent();
					filePrint.println(output);
				}
				filePrint.close();
			} catch (FileNotFoundException e) {
				Bukkit.getServer().broadcastMessage("Exception Error: " + e);
				Bukkit.getServer().broadcastMessage("Inform TheCodeNinja.");
			}
		}
	}

}
