package net.dsphat.easyspawnredux;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Location;
import java.util.HashMap;

public class EasySpawnRedux extends JavaPlugin {
    private HashMap<World, Location> spawnLocations = new HashMap<World, Location>();

    @Override
    public void onEnable() {

    }

    public void reloadConfig() {
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
}
