package me.theblockbender.voxelshortcuts;

import me.theblockbender.voxelshortcuts.listeners.ItemDropListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new ItemDropListener(), this);
    }
}
