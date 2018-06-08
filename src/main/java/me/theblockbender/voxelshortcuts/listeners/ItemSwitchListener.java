package me.theblockbender.voxelshortcuts.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class ItemSwitchListener implements Listener {
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
        if (type != Material.SULPHUR && type != Material.ARROW)
            return;
        TextComponent message = new TextComponent("§8[§b§lWorld§3§lEdit§8]§7 Pressing '§f§lQ§7' will undo your last action.");
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, message);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        ItemStack held = player.getInventory().getItemInMainHand();
        if (held == null || held.getType() == Material.AIR)
            return;
        Material type = held.getType();
        if (type != Material.SULPHUR && type != Material.ARROW)
            return;
        TextComponent message = new TextComponent("§8[§b§lWorld§3§lEdit§8]§7 Pressing '§f§lQ§7' will undo your last action.");
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, message);
    }
}
