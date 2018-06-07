package me.theblockbender.voxelshortcuts.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDropListener implements Listener {
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (event.isCancelled())
            return;

        Material type = event.getItemDrop().getItemStack().getType();
        if (type != Material.SULPHUR && type != Material.ARROW)
            return;
        event.getPlayer().chat("/u");
        event.setCancelled(true);
    }
}
