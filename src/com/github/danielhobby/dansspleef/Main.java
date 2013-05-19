package com.github.danielhobby.dansspleef;

import java.util.HashSet;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public Location pos1 = null;
	public Location pos2 = null;

	public void onEnable() {
	}

	public void onDisable() {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;

		if (cmd.getLabel().equalsIgnoreCase("spleef") && args[0].equalsIgnoreCase("reset")) {
			
			if ((this.pos1 == null) || (this.pos2 == null)) {
				player.sendMessage("Please supply pos 1 and pos 2.");
				return true;
			}
			
			World world = Bukkit.getWorld("world");
			HashSet<Location> locations = new HashSet<Location>();

			Location loc1 = this.pos1;
			Location loc2 = this.pos2;

			Boolean xpositive = true;
			Boolean zpositive = true;
			Boolean ypositive = true;

			if (loc1.getX() > loc2.getX())
				xpositive = false;
			else {
				xpositive = true;
			}

			if (loc1.getZ() > loc2.getZ())
				zpositive = false;
			else {
				zpositive = true;
			}

			if (loc1.getY() > loc2.getY()) {
				ypositive = false;
			} else {
				ypositive = true;
			}
			
			if ((xpositive.booleanValue()) && (zpositive.booleanValue())
					&& (ypositive.booleanValue())) {
				for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
					for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {
						for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++)
							locations.add(new Location(world, x, y, z));
					}
				}
			} else if ((xpositive.booleanValue()) && (zpositive.booleanValue())
					&& (!ypositive.booleanValue())) {
				for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
					for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {
						for (int y = loc1.getBlockY(); y >= loc2.getBlockY(); y--)
							locations.add(new Location(world, x, y, z));
					}
				}
			} else if ((xpositive.booleanValue())
					&& (!zpositive.booleanValue())
					&& (ypositive.booleanValue())) {
				for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
					for (int z = loc1.getBlockZ(); z >= loc2.getBlockZ(); z--) {
						for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++)
							locations.add(new Location(world, x, y, z));
					}
				}
			} else if ((xpositive.booleanValue())
					&& (!zpositive.booleanValue())
					&& (!ypositive.booleanValue())) {
				for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
					for (int z = loc1.getBlockZ(); z >= loc2.getBlockZ(); z--) {
						for (int y = loc1.getBlockY(); y >= loc2.getBlockY(); y--)
							locations.add(new Location(world, x, y, z));
					}
				}
			} else if ((!xpositive.booleanValue())
					&& (zpositive.booleanValue()) && (ypositive.booleanValue())) {
				for (int x = loc1.getBlockX(); x >= loc2.getBlockX(); x--) {
					for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {
						for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++)
							locations.add(new Location(world, x, y, z));
					}
				}
			} else if ((!xpositive.booleanValue())
					&& (zpositive.booleanValue())
					&& (!ypositive.booleanValue())) {
				for (int x = loc1.getBlockX(); x >= loc2.getBlockX(); x--) {
					for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {
						for (int y = loc1.getBlockY(); y >= loc2.getBlockY(); y--)
							locations.add(new Location(world, x, y, z));
					}
				}
			} else if ((!xpositive.booleanValue())
					&& (!zpositive.booleanValue())
					&& (ypositive.booleanValue())) {
				for (int x = loc1.getBlockX(); x >= loc2.getBlockX(); x--) {
					for (int z = loc1.getBlockZ(); z >= loc2.getBlockZ(); z--) {
						for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++)
							locations.add(new Location(world, x, y, z));
					}
				}
			} else if ((!xpositive.booleanValue())
					&& (!zpositive.booleanValue())
					&& (!ypositive.booleanValue())) {
				for (int x = loc1.getBlockX(); x >= loc2.getBlockX(); x--) {
					for (int z = loc1.getBlockZ(); z >= loc2.getBlockZ(); z--) {
						for (int y = loc1.getBlockY(); y >= loc2.getBlockY(); y--) {
							locations.add(new Location(world, x, y, z));
						}
					}
				}

			}

			int counter = 0;

			for (Location loc : locations) {
				Block floor = world.getBlockAt(loc);
				if (floor.getType().equals(Material.AIR)) {
					floor.setType(Material.SNOW_BLOCK);
					counter++;
				}
			}

			sender.sendMessage("Done, changed " + counter + " blocks.");

			return true;
		}
		if ((cmd.getLabel().equalsIgnoreCase("spleef")) && (args[0].equalsIgnoreCase("pos1"))) {
			pos1 = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
			player.sendMessage("Added p1.");
		} else if ((cmd.getLabel().equalsIgnoreCase("spleef")) && (args[0].equalsIgnoreCase("pos2"))) {
			pos2 = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
			player.sendMessage("Added p2.");
		}

		return false;
	}
}