package me.thecodeninja.playerinfo;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerInfoCommandExecutor implements CommandExecutor {

	@SuppressWarnings("unused")
	private PlayerInfo plugin;
	private Date current;

	public PlayerInfoCommandExecutor(PlayerInfo plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("pinfo")) {
			if (args.length < 1) {
				return false;
			}
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (p.getName().equalsIgnoreCase(args[0])) {
					PrintInfo(sender, p);
					return true;
				}
			}
			for (OfflinePlayer p : Bukkit.getServer().getOfflinePlayers()) {
				if (p.getName().equalsIgnoreCase(args[0])) {
					PrintInfo(sender, p);
					return true;
				}
			}
			sender.sendMessage(ChatColor.AQUA
					+ "That player has never logged into this server.");
			return true;
		}
		return false;
	}

	private void PrintInfo(CommandSender sender, OfflinePlayer p) {
		OfflinePlayer target = p;
		sender.sendMessage(ChatColor.AQUA + "Name: " + ChatColor.WHITE
				+ target.getName());
		if (sender.isOp()) {
//			sender.sendMessage(ChatColor.AQUA + "Location of logout: "
//					+ ChatColor.WHITE + coordinates(target) + ChatColor.AQUA
//					+ ".");
		}
		sender.sendMessage(ChatColor.AQUA + "Logged out "
				+ ChatColor.WHITE + lastPlayed(p));
		sender.sendMessage(ChatColor.AQUA + "First logged onto Minecraftington "
				+ ChatColor.WHITE + firstPlayed(p));
	}

	public String firstPlayed(OfflinePlayer p) {
		long timeLength = 0;
		current = new Date();
		timeLength = (current.getTime() - p.getFirstPlayed()) / 1000;
		return "" + (timeLength / 3600) + " hours and "
				+ ((timeLength % 3600) / 60) + " minutes ago.";
	}

	public String firstPlayed(Player p) {
		long timeLength = 0;
		current = new Date();
		timeLength = (current.getTime() - p.getFirstPlayed()) / 1000;
		return "" + (timeLength / 3600) + " hours and "
				+ ((timeLength % 3600) / 60) + " minutes ago.";
	}

	public String lastPlayed(OfflinePlayer p) {
		long timeLength = 0;
		current = new Date();
		timeLength = (current.getTime() - p.getLastPlayed()) / 1000;
		return "" + (timeLength / 3600) + " hours and "
				+ ((timeLength % 3600) / 60) + " minutes ago.";
	}

	private String coordinates(Player target) {
		String output = "(";
		output += target.getWorld().getName() + ")[";
		output += target.getLocation().getBlockX() + ", ";
		output += target.getLocation().getBlockY() + ", ";
		output += target.getLocation().getBlockZ() + "]";
		return output;
	}

	private void PrintInfo(CommandSender sender, Player p) {
		Player target = p.getPlayer();
		sender.sendMessage(ChatColor.AQUA + "Name: " + ChatColor.WHITE
				+ target.getName());
		if (sender.isOp()) {
			sender.sendMessage(ChatColor.AQUA + "Current location: "
					+ ChatColor.WHITE + coordinates(target) + ChatColor.AQUA
					+ ".");
			sender.sendMessage(ChatColor.AQUA + "Health: " + p.getHealth()
					+ ChatColor.WHITE + "/" + ChatColor.AQUA + p.getMaxHealth()
					+ " Level: " + ChatColor.WHITE + p.getLevel());
		}
		sender.sendMessage(ChatColor.AQUA + "First logged into Minecraftington "
				+ ChatColor.WHITE + firstPlayed(p));
	}

}
