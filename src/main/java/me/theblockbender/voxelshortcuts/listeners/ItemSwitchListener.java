package me.theblockbender.voxelshortcuts.listeners;

import me.theblockbender.voxelshortcuts.Main;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
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
        if (!main.tools.contains(type))
            return;
        trySendNotification(player);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        ItemStack held = player.getInventory().getItemInHand();
        if (held == null || held.getType() == Material.AIR)
            return;
        Material type = held.getType();
        if (!main.tools.contains(type))
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

    /**
     * @param player The player to receive the action bar.
     * @deprecated Sends an action bar message to a player.
     */
    private void send(Player player) {
        String msg = "§8[§e§lBee§6§lEdit§8]§7 Pressing '§f§lQ§7' will undo your last action.";
        IChatBaseComponent message = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(message, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
