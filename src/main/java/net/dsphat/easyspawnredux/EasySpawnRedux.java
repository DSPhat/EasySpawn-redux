package net.dsphat.easyspawnredux;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Location;
import java.util.HashMap;
import java.util.Map;


/**
 * Copyright &copy; 2017 James Golding
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file or http://www.wtfpl.net/
 * for more details.
 */
public class EasySpawnRedux extends JavaPlugin {
    private HashMap<World, Location> spawnLocations = new HashMap<World, Location>();

    @Override
    public void onEnable() {
        loadConfig();
    }

    public void loadConfig() {
        for (World world : getServer().getWorlds()) {
            String name = world.getName();
            String format = "spawn." + name;
            double x = getConfig().getDouble(format + ".x");
            double y = getConfig().getDouble(format + ".y");
            double z = getConfig().getDouble(format + ".z");
            float yaw = (float) getConfig().getDouble(format + ".yaw");
            float pitch = (float) getConfig().getDouble(format + ".pitch");
            Location loc = new Location(world, x, y, z, yaw, pitch);
            spawnLocations.put(world, loc);
        }
    }

    public void saveConfig() {
        for (Map.Entry<World, Location> entry : spawnLocations.entrySet()) {
            String worldName = entry.getKey().getName();
            double x = entry.getValue().getX();
            double y = entry.getValue().getY();
            double z = entry.getValue().getZ();
            double yaw = entry.getValue().getYaw();
            double pitch = entry.getValue().getPitch();
            String format = "spawn." + worldName;

            getConfig().set(format + ".x", x);
            getConfig().set(format + ".y", y);
            getConfig().set(format + ".z", z);
            getConfig().set(format + ".yaw", yaw);
            getConfig().set(format + ".pitch", pitch);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (!(sender.hasPermission(cmd.getPermission()))) return false;
        }
        return true;
    }
}
