package me.thecodeninja.ninjakits;

import java.util.List;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommandExecutor implements CommandExecutor {
	KitController kitControl;
	NinjaKits plugin;

	public KitCommandExecutor(NinjaKits plugin) {
		this.plugin = plugin;
		this.kitControl = new KitController(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("kit") && args.length > 0) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				ArgProcess(p, args);
				return true;
			}
		}
		return false;
	}

//	private void ArgProcess(Player player, String[] args) {
//		Kit temp;
//		if (args.length > 0
//				&& !player.isOp()
//				&& (args[0].equalsIgnoreCase("add")
//						|| args[0].equalsIgnoreCase("pinfo")
//						|| args[0].equalsIgnoreCase("give") || args[0]
//							.equalsIgnoreCase("remove")))
//			player.sendMessage("You do not have access to those commands.");
//		if (args.length == 1) {
//			if (args[0].equalsIgnoreCase("help")) {
//				player.sendMessage("/kit [kit] to give yourself the kit.");
//				player.sendMessage("/kit list will show a list of all of your kits.");
//				player.sendMessage("/kit list all will show a list of all available kits.");
//				player.sendMessage("/kit info [kit] will show the cost, recipe and contents of a kit.");
//				player.sendMessage("/kit buy [kit] will buy the kit for the cost in levels.");
//				if (player.isOp()) {
//					player.sendMessage(ChatColor.AQUA + "[Op Commands]");
//					player.sendMessage("/kit add adds a kit to the player's kit list.");
//					player.sendMessage("/kit pinfo [player] will show a list of the player's kits.");
//					player.sendMessage("/kit give gives a kit to a player, or self if no player specified.");
//					player.sendMessage("/kit remove removes kits from a player's list.");
//				}
//			} else if (args[0].equalsIgnoreCase("add") && player.isOp()) {
//				player.sendMessage("/kit add [kit] to add a kit to your repertoire.");
//				player.sendMessage("/kit add [player] [kit] to add a kit to another player's repertoire.");
//			} else if (args[0].equalsIgnoreCase("pinfo") && player.isOp()) {
//				player.sendMessage("/kit pinfo [player] will show a list of the player's kits.");
//			} else if (args[0].equalsIgnoreCase("give") && player.isOp()) {
//				player.sendMessage("/kit give [kit] to give yourself a kit.");
//				player.sendMessage("/kit give [player] [kit] to give a kit to another player.");
//			} else if (args[0].equalsIgnoreCase("info")) {
//				player.sendMessage("/kit info [kit] will show the cost and contents of a kit.");
//			} else if (args[0].equalsIgnoreCase("buy")) {
//				player.sendMessage("/kit buy [kit] will add the kit to your repertoire for the kit's cost in levels.");
//			} else if (args[0].equals("list")) {
//				kitControl.playerInfo(player, player);
//			} else {
//				List<Kit> playerKitList = kitControl.getPlayerList(player);
//				boolean kitFound = false;
//				for (Kit k : playerKitList) {
//					if (args[0].equalsIgnoreCase(k.getName()) && !kitFound) {
//						kitControl.activateKit(player, args[0]);
//						kitFound = true;
//					}
//				}
//				if (!kitFound)
//					player.sendMessage("That isn't a kit that you have.  /kit list will show a list of your usable kits.");
//			}
//		}
//		if (args.length == 2) {
//			if (args[0].equalsIgnoreCase("add")) {
//				temp = kitControl.getMasterKit(args[1]);
//				if (temp != null) {
//					kitControl.addKit(player, temp);
//					player.sendMessage("You've added the " + temp.getName()
//							+ " kit to your repertoire.");
//				} else {
//					player.sendMessage("That is not a valid kit.");
//				}
//			} else if (args[0].equalsIgnoreCase("pinfo")) {
//				if (playerOnline(args[1])) {
//					kitControl.playerInfo(player, getPlayer(args[1]));
//				} else
//					player.sendMessage("That player is not online.");
//			} else if (args[0].equalsIgnoreCase("give")) {
//				kitControl.giveKit(player, kitControl.getMasterKit(args[1]));
//				player.sendMessage("You conjure a " + kitControl.getMasterKit(args[1]).getName() + " kit with no equivalency exchange.");
//			} else if (args[0].equalsIgnoreCase("info")) {
//				kitControl.kitInfo(player, args[1]);
//			}
//		} else if (args[0].equalsIgnoreCase("list")) {
//			kitControl.printMaster(player);
//		} else if (args[0].equalsIgnoreCase("buy")) {
//			kitControl.buyKit(player, args[1]);
//		}
//
//		if (args.length == 3) {
//			Player target = getPlayer(args[1]);
//			if (args[0].equalsIgnoreCase("add") && target != null) {
//				kitControl.addKit(target, kitControl.getMasterKit(args[2]));
//			} else if (args[0].equalsIgnoreCase("give") && target != null) {
//				kitControl.giveKit(target, kitControl.getMasterKit(args[2]));
//			} else if (args[0].equalsIgnoreCase("remove") && target != null) {
//				kitControl.removeKit(target, args[2]);
//			}
//		}
//	}
	
	private void ArgProcess(Player player, String[] args) {
		Kit temp;
		if (args.length > 0
				&& !player.isOp()
				&& (args[0].equalsIgnoreCase("add")
						|| args[0].equalsIgnoreCase("pinfo") || args[0]
							.equalsIgnoreCase("give")))
			player.sendMessage("You do not have access to those commands.");
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("help")) {
				player.sendMessage("/kit [kit] to give yourself the kit.");
				player.sendMessage("/kit list will show a list of all of your kits.");
				player.sendMessage("/kit list all will show a list of all available kits.");
				player.sendMessage("/kit info will show the cost, recipe and contents of a kit.");
				player.sendMessage("/kit buy will buy the kit for the cost in levels.");
				if (player.isOp()) {
					player.sendMessage(ChatColor.AQUA + "[Op Commands]");
					player.sendMessage("/kit add will add kits to a player's list.");
					player.sendMessage("/kit pinfo will show a list of the player's kits.");
					player.sendMessage("/kit give to give any kit from the master list.");
					}
			} else if (args[0].equalsIgnoreCase("add") && player.isOp()) {
				player.sendMessage("/kit add [kit] to add a kit to your repertoire.");
				player.sendMessage("/kit add [player] [kit] to add a kit to another player's repertoire.");
			} else if (args[0].equalsIgnoreCase("pinfo") && player.isOp()) {
				player.sendMessage("/kit pinfo [player] will show a list of the player's kits.");
			} else if (args[0].equalsIgnoreCase("give") && player.isOp()) {
				player.sendMessage("/kit give [kit] to give yourself a kit.");
				player.sendMessage("/kit give [player] [kit] to give a kit to another player.");
			} else if (args[0].equalsIgnoreCase("info")) {
				player.sendMessage("/kit info [kit] will show the cost and contents of a kit.");
			} else if (args[0].equalsIgnoreCase("buy")) {
				player.sendMessage("/kit buy [kit] will add the kit to your repertoire for the kit's cost in levels.");
			} else if (args[0].equals("list")) {
				kitControl.playerInfo(player, player);
			} else {
				List<Kit> playerKitList = kitControl.getPlayerList(player);
				boolean kitFound = false;
				for (Kit k : playerKitList) {
					if (args[0].equalsIgnoreCase(k.getName())) {
						kitControl.activateKit(player, k.getName());
						kitFound = true;
					}
				}
				if (!kitFound)
					player.sendMessage("That kit is not in your repertoire.");
			}
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("add")) {
				temp = kitControl.getMasterKit(args[1]);
				if (temp != null) {
					kitControl.addKit(player, temp);
					player.sendMessage("You've added the " + temp.getName()
							+ " kit to your repertoire.");
				} else {
					player.sendMessage("That is not a valid kit.");
				}
			} else if (args[0].equalsIgnoreCase("pinfo")) {
				if (playerOnline(args[1])) {
					kitControl.playerInfo(player, getPlayer(args[1]));
				} else
					player.sendMessage("That player is not online.");
			} else if (args[0].equalsIgnoreCase("give")) {
				temp = kitControl.getMasterKit(args[1]);
				if (temp != null) {
					kitControl.addKit(player, temp);
				} else {
					player.sendMessage("That is not a valid kit.");
				}
			} else if (args[0].equalsIgnoreCase("info")) {
				temp = kitControl.getMasterKit(args[1]);
				if (temp != null) {
					kitControl.kitInfo(player, args[1]);
				} else {
					player.sendMessage("That is not a valid kit.");
				}
			} else if (args[0].equalsIgnoreCase("list")) {
				if (args[1].equalsIgnoreCase("all"))
					kitControl.printMaster(player);
				else
					kitControl.playerInfo(player, player);
			} else if (args[0].equalsIgnoreCase("buy")) {
				if (kitControl.getMasterKit(args[1]) != null) {
					kitControl.buyKit(player, args[1]);
				} else
					player.sendMessage("That is not a valid kit.");
			}
		}
		if (args.length == 3) {
			Player target = getPlayer(args[1]);
			if (args[0].equalsIgnoreCase("add") && target != null) {
				kitControl.addKit(target, kitControl.getMasterKit(args[2]));
			} else if (args[0].equalsIgnoreCase("give") && target != null) {
				kitControl.giveKit(target, kitControl.getMasterKit(args[2]));
			}
		}
	}

	private Player getPlayer(String name) {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}

	private boolean playerOnline(String name) {
		for (Player target : Bukkit.getServer().getOnlinePlayers()) {
			if (name.equalsIgnoreCase(target.getName())) {
				return true;
			}
		}
		return false;
	}
}
