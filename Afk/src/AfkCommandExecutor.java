package com.gmail.thecarninja6.afk;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AfkCommandExecutor implements CommandExecutor {
	AfkSetters setter;
	
	public AfkCommandExecutor(Afk plugin) {
		setter = new AfkSetters();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		// TODO Auto-generated method stub
		if (cmd.getName().equalsIgnoreCase("afk")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				setter.ToggleAfk(player, args);
			}
			return true;
		}
		return false;
	}

	
}
