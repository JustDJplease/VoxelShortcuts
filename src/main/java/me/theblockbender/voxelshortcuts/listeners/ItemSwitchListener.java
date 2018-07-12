package me.theblockbender.voxelshortcuts.listeners;

import me.theblockbender.voxelshortcuts.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ItemSwitchListener implements Listener {

    private Main main;

    public ItemSwitchListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onInventorySelect(PlayerItemHeldEvent event) {
        if (event.isCancelled())
            return;

        int slot = event.getNewSlot();
        Player player = event.getPlayer();
        ItemStack held = player.getInventory().getItem(slot);
        if (held == null || held.getType() == Material.AIR)
            return;
        Material type = held.getType();
        if (type != Material.SULPHUR && type != Material.ARROW && type != Material.FLINT)
            return;
        trySendNotification(player);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        ItemStack held = player.getInventory().getItemInMainHand();
        if (held == null || held.getType() == Material.AIR)
            return;
        Material type = held.getType();
        if (type != Material.SULPHUR && type != Material.ARROW && type != Material.FLINT)
            return;
        trySendNotification(player);
    }

    private void trySendNotification(Player player) {
        UUID uuid = player.getUniqueId();
        if (main.doNotBother.containsKey(uuid)) {
            int times = main.doNotBother.get(uuid);
            if (times <= 4) {
                times = times + 1;
                main.doNotBother.put(uuid, times);
                send(player);
            }
        } else {
            main.doNotBother.put(uuid, 1);
            send(player);
        }
    }

    private void send(Player player) {
        TextComponent message = new TextComponent("§8[§e§lBee§6§lEdit§8]§7 Pressing '§f§lQ§7' will undo your last action.");
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, message);
    }
}
