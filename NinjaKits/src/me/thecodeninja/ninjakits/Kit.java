package me.thecodeninja.ninjakits;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public class Kit {
	private String name;
	private List<ItemStack> kitItems;
	private int countMaster;
	private int countCurrent;
	private int cost;
	private ItemStack crafter;
	private String craftString;
	private boolean isVisible;
	private boolean isDefault;

	public Kit(String name, List<ItemStack> kitItems, ItemStack crafter,
			String craftString, int count, int cost) {
		this.name = name;
		this.kitItems = kitItems;
		this.countMaster = count;
		this.countCurrent = count;
		this.cost = cost;
		this.crafter = crafter;
		this.craftString = craftString;
		this.isVisible = true;
		this.isDefault = false;
	}

	public Kit(String name, List<ItemStack> kitItems, ItemStack crafter,
			String craftString, int count, int cost, boolean isDefault) {
		this.name = name;
		this.kitItems = kitItems;
		this.countMaster = count;
		this.countCurrent = count;
		this.cost = cost;
		this.crafter = crafter;
		this.craftString = craftString;
		this.isDefault = isDefault;
		this.isVisible = true;
	}

	public Kit(String name, List<ItemStack> kitItems, ItemStack crafter,
			String craftString, int count, int cost, boolean isDefault,
			boolean isVisible) {
		this.name = name;
		this.kitItems = kitItems;
		this.countMaster = count;
		this.countCurrent = count;
		this.cost = cost;
		this.crafter = crafter;
		this.craftString = craftString;
		this.isDefault = isDefault;
		this.isVisible = isVisible;
	}

	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}

	public void setDefault(boolean set) {
		this.isDefault = set;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public String toString() {
		return ChatColor.AQUA + "[" + getName() + "]  " + ChatColor.GREEN
				+ printCost() + ChatColor.WHITE + "  Count Remaining: "
				+ getCountString(getCountCurrent());
	}

	public String toStringMaster() {
		return ChatColor.AQUA + "[" + getName() + "]  " + ChatColor.GREEN
				+ printCost() + ChatColor.WHITE + "  Count Per Kit: "
				+ getCountString(getCountMaster());
	}

	public String getCountString(int count) {
		String output = "";
		if (count < 0)
			output += "Infinite";
		else
			output += count;
		return output;
	}

	public String printCost() {
		String output = "";
		if (!getCraftString().equals("") || getCost() >= 0) {
			output = "[";
			if (!getCraftString().equals("")) {
				output += getCraftString() + "/";
			}
			if (getCost() >= 0) {
				output += getCost();
			}
			output += "]";
		}
		return output;

	}

	public String getCraftString() {
		return craftString;
	}

	public String getName() {
		return name;
	}

	public int getCountCurrent() {
		return countCurrent;
	}

	public int getCountMaster() {
		return countMaster;
	}

	public int getCountMaximum() {
		return countMaster * 2;
	}

	public List<ItemStack> getItems() {
		return kitItems;
	}

	public int getCost() {
		return cost;
	}

	public void setCount(int count) {
		countCurrent = count;
	}

	public void decrementCount() {
		countCurrent = countCurrent - 1;
	}

	public ItemStack getCrafter() {
		return crafter;
	}

	public void addCount() {
		countCurrent = countCurrent + countMaster;
	}
}
