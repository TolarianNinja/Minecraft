package me.thecodeninja.extendedrecipes;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ExtendedRecipes extends JavaPlugin {
	@Override
    public void onEnable(){
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		PluginManager pm = getServer().getPluginManager();
		
		// Crafting fire with blaze powder surrounded by redstone
		ShapedRecipe fire = new ShapedRecipe(new ItemStack(Material.FIRE));
		fire.shape("RRR", "RBR", "RRR");
		fire.setIngredient('R', Material.REDSTONE);
		fire.setIngredient('B', Material.BLAZE_POWDER);
		
		// Crafting mycelium with slimeballs surrounding dirt
		ShapedRecipe mycelium = new ShapedRecipe(new ItemStack(Material.MYCEL));
		mycelium.shape("SSS", "SDS", "SSS");
		mycelium.setIngredient('S', Material.SLIME_BALL);
		mycelium.setIngredient('D', Material.DIRT);
		
		// Crafting glowstone dust
		ShapedRecipe glowstoneDust = new ShapedRecipe(new ItemStack(Material.GLOWSTONE_DUST, 4));
		glowstoneDust.shape("   ", " R ", "SGS");
		glowstoneDust.setIngredient('S', Material.SUGAR);
		glowstoneDust.setIngredient('R', Material.REDSTONE);
		glowstoneDust.setIngredient('G', Material.GOLD_NUGGET);
		
		// Crafting name tags
		ShapedRecipe nameTag = new ShapedRecipe(new ItemStack(Material.NAME_TAG));
		nameTag.shape("IS ", "PPS", "IS ");
		nameTag.setIngredient('I', Material.INK_SACK);
		nameTag.setIngredient('P', Material.PAPER);
		nameTag.setIngredient('S', Material.STRING);
		
		// Crafting diamonds with coal blocks (81 coal = 1 diamond)
		ShapedRecipe coal2diamond = new ShapedRecipe(new ItemStack(Material.DIAMOND));
		coal2diamond.shape("BBB", "BBB", "BBB");
		coal2diamond.setIngredient('B', Material.COAL_BLOCK);
		
		// Doomblade
//		ItemStack doomBlade = new ItemStack(Material.DIAMOND_SWORD);
//		doomBlade.addEnchantment(Enchantment.DAMAGE_ALL, 5);
//		doomBlade.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
//		doomBlade.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
//		doomBlade.addEnchantment(Enchantment.FIRE_ASPECT, 1);
		
//		ShapedRecipe doomBladeRecipe = new ShapedRecipe(doomBlade);
//		doomBladeRecipe.shape("BDB", "BSB", "BBB");
//		doomBladeRecipe.setIngredient('B', Material.BLAZE_POWDER);
//		doomBladeRecipe.setIngredient('D', Material.DIAMOND);
//		doomBladeRecipe.setIngredient('S', Material.DIAMOND_SWORD);
		
		// Furnace recipes
		FurnaceRecipe gravel2clay = new FurnaceRecipe(new ItemStack(Material.CLAY), Material.GRAVEL);
		FurnaceRecipe zombieFlesh2Leather = new FurnaceRecipe(new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH);
		
		
		getServer().addRecipe(fire);
		getServer().addRecipe(mycelium);
		getServer().addRecipe(gravel2clay);
		getServer().addRecipe(glowstoneDust);
		//getServer().addRecipe(doomBladeRecipe);
		getServer().addRecipe(nameTag);
		getServer().addRecipe(coal2diamond);
		getServer().addRecipe(zombieFlesh2Leather);
		
		pm.enablePlugin(this);
    }
 
    @Override
    public void onDisable() {
    	getLogger().info("Extended Recipes disabled.");
    }

}
