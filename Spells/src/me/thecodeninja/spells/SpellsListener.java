package me.thecodeninja.spells;

import java.util.Stack;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpellsListener implements Listener {

	@EventHandler
	public void castSpell(PlayerInteractEvent event) {

		class KingSlime implements Listener {
			@EventHandler
			public void spawnKingSlime(CreatureSpawnEvent event) {
				Bukkit.broadcastMessage("Can you hear me now?");
				if (event.getEntityType() == EntityType.SLIME) {
					Bukkit.broadcastMessage("I am getting to this point.");
					Slime kingSlime = (Slime) event.getEntity();
					kingSlime.setSize(32);
				}
			}
		}

		Player p = event.getPlayer();
		KingSlime kingSlimeListener = new KingSlime();
		Stack<Block> s = new Stack<Block>();
		BlockFace[] faces = { BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH,
				BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
		BlockFace[] facesAndCorners = { BlockFace.UP, BlockFace.DOWN,
				BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST,
				BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH_WEST };
		int counter = 0;
		if (p.getItemInHand().getType() == Material.STICK) {
			counter = 0;
			Block lavaTemp;
			for (BlockFace b : faces) {
				lavaTemp = event.getClickedBlock();
				if (lavaTemp.getRelative(b).getType() == Material.LAVA
						|| lavaTemp.getRelative(b).getType() == Material.STATIONARY_LAVA) {
					s.push(lavaTemp.getRelative(b));
					counter++;
				}
			}
			if (s.isEmpty()) {
				p.sendMessage("There is no lava alongside any surface of that block.");
			} else {
				while (!s.isEmpty()) {
					lavaTemp = s.pop();
					if (lavaTemp.getType() == Material.LAVA
							|| lavaTemp.getType() == Material.STATIONARY_LAVA) {
						for (BlockFace b : faces) {
							if (counter < 45
									&& (lavaTemp.getRelative(b).getType() == Material.LAVA || lavaTemp
											.getRelative(b).getType() == Material.STATIONARY_LAVA)) {
								s.push(lavaTemp.getRelative(b));
								counter++;
							}
						}
						lavaTemp.setType(Material.OBSIDIAN);
					}
				}
				if (counter < 45) {
					p.sendMessage("All of the lava has crystallized into obsidian.");
				} else
					p.sendMessage("A patch of the lava crystallizes into obsidian.");
			}
		} else if (p.getItemInHand().getType() == Material.PUMPKIN_SEEDS) {
			Block obsidianTemp = event.getClickedBlock();
			counter = 0;
			if (obsidianTemp.getType() == Material.OBSIDIAN) {
				s.push(obsidianTemp);
				counter++;
			}
			while (!s.isEmpty()) {
				obsidianTemp = s.pop();
				for (BlockFace b : faces) {
					if (counter < 45 && (obsidianTemp.getRelative(b).getType() == Material.OBSIDIAN)) {
						s.push(obsidianTemp.getRelative(b));
						counter++;
					}
				}
				if (obsidianTemp.getType() == Material.OBSIDIAN)
					obsidianTemp.breakNaturally();
			}
		} else if (p.getItemInHand().getType() == Material.SLIME_BALL) {
			p.getWorld().spawnEntity(p.getLocation(), EntityType.SLIME);
		} else if (p.getItemInHand().getType() == Material.NETHER_STAR) {
			p.getWorld().spawnEntity(p.getLocation(), EntityType.GIANT);
		} else if (p.getItemInHand().getType() == Material.WOOD_AXE) {
			Block treeTemp = event.getClickedBlock();
			if (treeTemp.getType() == Material.LOG || treeTemp.getType() == Material.LEAVES) {
				s.push(treeTemp);
			}
			while (!s.isEmpty()) {
				treeTemp = s.pop();
				for (BlockFace b : facesAndCorners) {
					if (treeTemp.getType() == Material.LOG || treeTemp.getType() == Material.LEAVES) {
						s.push(treeTemp.getRelative(b));
					}
				}
				if (treeTemp.getType() == Material.LOG || treeTemp.getType() == Material.LEAVES)
					treeTemp.breakNaturally();
			}
			p.sendMessage("Your chainsaw destroys the wood of the tree.");
		}
	}

}
