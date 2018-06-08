package me.theblockbender.voxelshortcuts;

import me.theblockbender.voxelshortcuts.listeners.ItemDropListener;
import me.theblockbender.voxelshortcuts.listeners.ItemSwitchListener;
import me.theblockbender.voxelshortcuts.listeners.PlayerLeaveListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin {

    public Map<UUID, Integer> doNotBother = new HashMap<>();

    public void onEnable(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ItemDropListener(this), this);
        pm.registerEvents(new ItemSwitchListener(this), this);
        pm.registerEvents(new PlayerLeaveListener(this), this);
    }

    public void onDisable(){
        doNotBother.clear();
    }
}
