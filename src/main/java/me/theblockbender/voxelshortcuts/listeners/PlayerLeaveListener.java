package me.theblockbender.voxelshortcuts.listeners;

import me.theblockbender.voxelshortcuts.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    private Main main;

    public PlayerLeaveListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        main.doNotBother.remove(event.getPlayer().getUniqueId());
    }
}
