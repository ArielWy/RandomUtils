package me.olios.plugins.randomutils.listeners

import me.olios.plugins.randomutils.EntityDrop
import me.olios.plugins.randomutils.RandomUtils
import org.bukkit.entity.Warden
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

class EntityDeathListener(private val plugin: RandomUtils): Listener {

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val warden = event.entity
        if (warden !is Warden) return

        EntityDrop(plugin).wardenDrop(event)  // call the fun
    }
}