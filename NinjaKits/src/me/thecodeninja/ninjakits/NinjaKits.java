package me.thecodeninja.ninjakits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NinjaKits extends JavaPlugin {
	public final KitListener listener = new KitListener(this);
	FileConfiguration config;
	public KitController kitControl;

	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		getLogger().info("Crafting turned on.");
		pm.registerEvents(this.listener, this);
		getCommand("kit").setExecutor(new KitCommandExecutor(this));
		if (this.getDataFolder().mkdir()) {
			Bukkit.getServer().broadcastMessage("Data folder created");
		}
		kitControl = new KitController(this);
		LoadKits();
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			listener.PlayerLoad(p);
		}
	}
	
	@Override
	public void onDisable() {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			listener.PlayerSave(p);
		}
	}
	
	private void LoadKits() {
		// Kit (Name,  Items List,  Crafting Recipe,  Crafting Recipe String,  Count per Kit,  Cost in Levels)	
		List<ItemStack> pickList = new ArrayList<ItemStack>();
		pickList.add(new ItemStack(Material.DIAMOND_PICKAXE));
		Kit dPick = new Kit("dPick", pickList, new ItemStack(Material.DIAMOND_PICKAXE), "Diamond Pickaxe", 5, 15);
		kitControl.AddToMain(dPick);
		
		List<ItemStack> shovelList = new ArrayList<ItemStack>();
		shovelList.add(new ItemStack(Material.DIAMOND_SPADE));
		Kit dShovel = new Kit("dShovel", shovelList, new ItemStack(Material.DIAMOND_SPADE), "Diamond Shovel", 5, 15);
		kitControl.AddToMain(dShovel);
		
		List<ItemStack> axeList = new ArrayList<ItemStack>();
		axeList.add(new ItemStack(Material.DIAMOND_AXE));
		Kit dAxe = new Kit("dAxe", axeList, new ItemStack(Material.DIAMOND_AXE), "Diamond Axe", 5, 12);
		kitControl.AddToMain(dAxe);
		
		List<ItemStack> swordList = new ArrayList<ItemStack>();
		swordList.add(new ItemStack(Material.DIAMOND_SWORD));
		Kit dSword = new Kit("dSword", swordList, new ItemStack(Material.DIAMOND_SWORD), "Diamond Sword", 5, 20);
		kitControl.AddToMain(dSword);
		
		List<ItemStack> eChestList = new ArrayList<ItemStack>();
		eChestList.add(new ItemStack(Material.ENDER_CHEST));
		Kit eChest = new Kit("eChest", eChestList, new ItemStack(Material.ENDER_CHEST), "Ender Chest", 3, 25);
		kitControl.AddToMain(eChest);
		
		List<ItemStack> stBookList = new ArrayList<ItemStack>();
		ItemStack stBookItem = new ItemStack(Material.ENCHANTED_BOOK);
		stBookItem.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
		stBookList.add(stBookItem);
		Kit stBook = new Kit("stBook", stBookList, null, "", 1, 15);
		kitControl.AddToMain(stBook);
		
		List<ItemStack> starterList = new ArrayList<ItemStack>();
		starterList.add(new ItemStack(Material.STONE_PICKAXE));
		starterList.add(new ItemStack(Material.STONE_SPADE));
		starterList.add(new ItemStack(Material.LEATHER_CHESTPLATE));
		starterList.add(new ItemStack(Material.WORKBENCH));
		Kit starter = new Kit("starter", starterList, null, "", 3, 5, true);
		kitControl.AddToMain(starter);
		
		List<ItemStack> stoneList = new ArrayList<ItemStack>();
		stoneList.add(new ItemStack(Material.STONE, 4));
		Kit stone = new Kit("stone", stoneList, null, "", -1, 0, true, false);
		kitControl.AddToMain(stone);
	}
}
