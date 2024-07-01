package me.olios.plugins.randomutils.listeners

import me.olios.plugins.randomutils.Items
import me.olios.plugins.randomutils.RandomUtils
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class PlayerInteractListener(private val plugin: RandomUtils): Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val item = event.item ?: return

        if (event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK)
            flyingFeather(item)

    }

    private fun flyingFeather(item: ItemStack) {
        if (!item.hasItemMeta()) return

        val key = NamespacedKey(plugin, "Uses")  // get the namespace
        val itemMeta = item.itemMeta

        if (!itemMeta.persistentDataContainer.has(key)) return

        Items(plugin).updateFlyingFeather(item)
    }
}