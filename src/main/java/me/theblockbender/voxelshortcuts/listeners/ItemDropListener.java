package me.theblockbender.voxelshortcuts.listeners;

import me.theblockbender.voxelshortcuts.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDropListener implements Listener {

    private Main main;

    public ItemDropListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (event.isCancelled())
            return;

        Material type = event.getItemDrop().getItemStack().getType();
        if (!main.tools.contains(type))
            return;
        event.getPlayer().chat("/u");
        event.setCancelled(true);
    }
}
