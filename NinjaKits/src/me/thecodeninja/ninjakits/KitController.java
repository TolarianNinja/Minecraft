package me.thecodeninja.ninjakits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitController {
	static HashMap<Player, List<Kit>> kitMap;
	static List<Kit> mainList;
	NinjaKits plugin;

	public KitController(NinjaKits plugin) {
		kitMap = new HashMap<Player, List<Kit>>();
		mainList = new ArrayList<Kit>();
		this.plugin = plugin;
	}

	public List<Kit> getPlayerList(Player player) {
		return kitMap.get(player);
	}

	public List<Kit> getMainList() {
		return mainList;
	}

	public void AddToMain(Kit kit) {
		mainList.add(kit);
	}

	public HashMap<Player, List<Kit>> getKitMap() {
		return kitMap;
	}

	public Kit getPlayerKit(Player player, String name) {
		for (Kit k : getPlayerList(player)) {
			if (k.getName().equalsIgnoreCase(name))
				return k;
		}
		return null;
	}

	public Kit getMasterKit(String name) {
		for (Kit k : getMainList()) {
			if (k.getName().equalsIgnoreCase(name))
				return k;
		}
		return null;
	}

	public void addKit(Player player, Kit kit) {
		Kit playerKit = getPlayerKit(player, kit.getName());
		List<Kit> playerList = getKitMap().get(player);
		int countCurrent = kit.getCountCurrent();
		int countMaster = kit.getCountMaster();
		if (playerKit != null) {
			if (countCurrent + countMaster >= kit.getCountMaximum())
				playerKit.setCount(kit.getCountMaximum());
			else
				playerKit.addCount();
		} else {
			Kit masterKit = getMasterKit(kit.getName());
			playerKit = new Kit(masterKit.getName(), masterKit.getItems(),
					masterKit.getCrafter(), masterKit.getCraftString(),
					masterKit.getCountMaster(), masterKit.getCountMaster());
			playerKit.setDefault(masterKit.isDefault());
			playerKit.setVisible(masterKit.isVisible());
			if (playerList == null)
				playerList = new ArrayList<Kit>();
			playerList.add(playerKit);
			getKitMap().put(player, playerList);
		}
	}

	public boolean removeKit(Player target, String name) {
		int index = 0;
		boolean inList = false;
		List<Kit> playerList = getPlayerList(target);
		for (Kit k : playerList) {
			if (k.getName().equalsIgnoreCase(name) && !inList) {
				inList = true;
				break;
			} else {
				index++;
			}
		}
		if (!inList) {
			return false;
		} else {
			playerList.remove(index);
			getKitMap().put(target, playerList);
			return true;
		}

	}

	public void giveKit(Player target, Kit kit) {
		for (ItemStack i : kit.getItems()) {
			target.getInventory().addItem(i);
		}
	}

	public void activateKit(Player player, String name) {
		Kit usedKit = getPlayerKit(player, name);
		if (usedKit == null) {
			player.sendMessage("You have not crafted or purchased that kit.");
		} else if (usedKit.getCountCurrent() == 0) {
			player.sendMessage("You are out of that type of kit.");
		} else {
			if (usedKit.getCountMaster() != -1)
				usedKit.decrementCount();
			giveKit(player, usedKit);
			player.sendMessage("You activate the " + usedKit.getName()
					+ " kit.");
			getKitMap().put(player, getPlayerList(player));
		}
	}

	public void buyKit(Player player, String name) {
		Kit boughtKit = getMasterKit(name);
		if (boughtKit == null
				|| (!(boughtKit.isVisible() && getPlayerKit(player,
						boughtKit.getName()) != null) && !player.isOp())) {
			player.sendMessage("That is not a valid kit. /kit list all for a list of valid kits.");
		}
		if (boughtKit.getCost() <= 0) {
			player.sendMessage("You can not buy that kit.");
		} else if (player.getLevel() < boughtKit.getCost()) {
			player.sendMessage("That kit costs " + boughtKit.getCost()
					+ " levels, but you don't have enough.");
		} else if (getPlayerKit(player, boughtKit.getName()) != null
				&& getPlayerKit(player, boughtKit.getName()).getCountCurrent() >= getPlayerKit(
						player, boughtKit.getName()).getCountMaximum()) {
			player.sendMessage("You already have the maximum count of that kit.");

		} else {
			player.sendMessage("You buy the " + boughtKit.getName()
					+ " kit for " + boughtKit.getCost() + " levels.");
			player.setLevel(player.getLevel() - boughtKit.getCost());
			addKit(player, boughtKit);
		}
	}

	public void kitInfo(Player player, String name) {
		boolean playerKit = false;
		Kit infoKit = getPlayerKit(player, name);
		if (infoKit != null) {
			playerKit = true;
		} else {
			infoKit = getMasterKit(name);
		}
		if (infoKit == null
				|| (!(infoKit.isVisible() && playerKit) && !player.isOp())) {
			player.sendMessage("That is not a valid kit. /kit list all for a list of valid kits.");
		} else {
			player.sendMessage(ChatColor.GREEN + "Activation Name: "
					+ ChatColor.WHITE + infoKit.getName());
			if (infoKit.isDefault()) {
				player.sendMessage("This is a default kit given to new players.");
			}
			if (infoKit.getCraftString().equals("") && infoKit.getCost() <= 0) {
				player.sendMessage("There is no way to create this kit.");
			} else if (infoKit.getCost() < 0) {
				player.sendMessage(ChatColor.GREEN + "Crafting Recipe: "
						+ ChatColor.WHITE + infoKit.getCraftString());
			} else if (infoKit.getCraftString().equals("")) {
				player.sendMessage(ChatColor.GREEN + "Cost in Levels: "
						+ ChatColor.WHITE + infoKit.getCost());
			} else {
				player.sendMessage(ChatColor.GREEN + "Crafting Recipe: "
						+ ChatColor.WHITE + infoKit.getCraftString()
						+ ChatColor.GREEN + "  Cost in Levels: "
						+ ChatColor.WHITE + infoKit.getCost());
			}
			if (playerKit) {
				player.sendMessage(ChatColor.GREEN + "Count Purchased: "
						+ ChatColor.WHITE
						+ infoKit.getCountString(infoKit.getCountMaster())
						+ ChatColor.GREEN + "  Count Left: " + ChatColor.WHITE
						+ infoKit.getCountString(infoKit.getCountCurrent()));
			} else {
				player.sendMessage(ChatColor.GREEN + "Kits per Craft: "
						+ ChatColor.WHITE + infoKit.getCountMaster());
			}
			player.sendMessage(ChatColor.LIGHT_PURPLE + "Kit Contents:");
			for (ItemStack i : infoKit.getItems()) {
				player.sendMessage(ChatColor.AQUA + "- " + i.getType() + "  x"
						+ i.getAmount());
				for (Enchantment e : i.getEnchantments().keySet()) {
					player.sendMessage(ChatColor.AQUA + "   - " + e.getName());
				}
			}

		}
	}

	public void printMaster(Player player) {
		player.sendMessage("Master list of all kits:");
		player.sendMessage(ChatColor.AQUA + "KitName " + ChatColor.GREEN
				+ "  CraftRecipe/LevelCost " + ChatColor.WHITE
				+ "  Uses Per Kit");
		for (Kit k : getMainList()) {
			if (k.isVisible())
				player.sendMessage(k.toStringMaster());
		}
	}

	public void playerInfo(Player player, Player target) {
		player.sendMessage("All kits of " + target.getName() + ":");
		player.sendMessage(ChatColor.AQUA + "KitName   " + ChatColor.GREEN
				+ "CraftRecipe/LevelCost   " + ChatColor.WHITE
				+ "Remaining Uses");
		if (getPlayerList(target) != null) {
			for (Kit k : getPlayerList(target))
				if (k.getCountCurrent() != 0)
					player.sendMessage(k.toString());
		}
	}

	public void addDefaults(Player player) {
		for (Kit k : getMainList()) {
			if (k.isDefault() || k.getCost() == 0) {
				addKit(player, k);
			}
		}
		plugin.getLogger().info(
				"Default kits added to " + player.getName() + ".");
	}
}
