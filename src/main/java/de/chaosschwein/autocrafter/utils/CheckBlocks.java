package de.chaosschwein.autocrafter.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.block.data.Directional;

public class CheckBlocks {

    private final Block block;

    public CheckBlocks(Block block) {
        this.block = block;
    }

    public boolean isCrafter() {
        Block b = this.block;
        boolean isCrafter = false;
        Location loc = b.getLocation();
        if (b.getType() != Material.DISPENSER) {
            return false;
        }
        if (b.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) b.getBlockData()).getFacing();
            switch (face) {
                case DOWN:
                    isCrafter = (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.CRAFTING_TABLE &&
                            b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 2, loc.getBlockZ()).getType() == Material.HOPPER);
                    break;
                case NORTH:
                    isCrafter = (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 1).getType() == Material.CRAFTING_TABLE &&
                            b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() - 1).getType() == Material.HOPPER);
                    break;
                case SOUTH:
                    isCrafter = (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 1).getType() == Material.CRAFTING_TABLE &&
                            b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() + 1).getType() == Material.HOPPER);
                    break;
                case WEST:
                    isCrafter = (b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ()).getType() == Material.CRAFTING_TABLE &&
                            b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.HOPPER);
                    break;
                case EAST:
                    isCrafter = (b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ()).getType() == Material.CRAFTING_TABLE &&
                            b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.HOPPER);
                    break;
                default:
                    break;
            }
        }
        return isCrafter;
    }

    public boolean isPlacer() {
        Block b = this.block;
        Location loc = b.getLocation();
        if (b.getType() != Material.DISPENSER) {
            return false;
        }
        if (b.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) b.getBlockData()).getFacing();
            switch (face) {
                case NORTH:
                    return (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() - 1).getType() == Material.STICKY_PISTON ||
                            b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() - 1).getType() == Material.STICKY_PISTON);
                case SOUTH:
                    return (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() + 1).getType() == Material.STICKY_PISTON ||
                            b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() + 1).getType() == Material.STICKY_PISTON);
                case WEST:
                    return (b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.STICKY_PISTON ||
                            b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.STICKY_PISTON);
                case EAST:
                    return  (b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.STICKY_PISTON ||
                            b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.STICKY_PISTON);
                case UP:
                    return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ()).getType() == Material.STICKY_PISTON;
                default:
                    break;
            }
        }
        return false;
    }

    public boolean isBreaker() {
        Block b = this.block;
        Location loc = b.getLocation();
        if (b.getType() != Material.STICKY_PISTON) {
            return false;
        }
        if (b.getBlockData() instanceof Directional) {
            BlockFace face = ((Directional) b.getBlockData()).getFacing();
            switch (face) {
                case DOWN:
                    return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.END_ROD;
                case NORTH:
                    return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 1).getType() == Material.END_ROD;
                case SOUTH:
                    return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 1).getType() == Material.END_ROD;
                case WEST:
                    return b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ()).getType() == Material.END_ROD;
                case EAST:
                    return b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ()).getType() == Material.END_ROD;
                case UP:
                    return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.END_ROD;
                default:
                    break;
            }
        }
        return false;
    }

    public boolean isSender() {
        Block b = this.block;
        Location loc = b.getLocation();
        if (b.getType() != Material.TRAPPED_CHEST) {
            return false;
        }
        if (b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() != Material.END_ROD) {
            return false;
        }
        return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 1).getType() == Material.HOPPER ||
                b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 1).getType() == Material.HOPPER ||
                b.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ()).getType() == Material.HOPPER ||
                b.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ()).getType() == Material.HOPPER;
    }

    public boolean isReceiver() {
        Block b = this.block;
        Location loc = b.getLocation();
        if (b.getType() != Material.TRAPPED_CHEST) {
            return false;
        }
        return b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType() == Material.END_ROD &&
                b.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.HOPPER;
    }
}
