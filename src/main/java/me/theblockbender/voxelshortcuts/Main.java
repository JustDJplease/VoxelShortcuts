package me.theblockbender.voxelshortcuts;

import me.theblockbender.voxelshortcuts.listeners.ItemDropListener;
import me.theblockbender.voxelshortcuts.listeners.ItemSwitchListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void onEnable(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ItemDropListener(), this);
        pm.registerEvents(new ItemSwitchListener(), this);
    }
}
