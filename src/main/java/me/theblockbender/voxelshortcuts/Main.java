package me.theblockbender.voxelshortcuts;

import me.theblockbender.voxelshortcuts.listeners.ItemDropListener;
import me.theblockbender.voxelshortcuts.listeners.ItemSwitchListener;
import me.theblockbender.voxelshortcuts.listeners.PlayerLeaveListener;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Main extends JavaPlugin {

    public Map<UUID, Integer> doNotBother = new HashMap<>();
    public List<Material> tools = new ArrayList<>();

    public void onEnable() {
        populateTools();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ItemDropListener(this), this);
        pm.registerEvents(new ItemSwitchListener(this), this);
        pm.registerEvents(new PlayerLeaveListener(this), this);
    }

    private void populateTools() {
        tools.add(Material.FLINT);
        tools.add(Material.FEATHER);
        tools.add(Material.ARROW);
        tools.add(Material.SULPHUR);
    }

    public void onDisable() {
        doNotBother.clear();
    }
}
