package me.olios.plugins.randomutils

import org.bukkit.event.entity.EntityDeathEvent

class EntityDrop(private val plugin: RandomUtils) {

    fun wardenDrop(event: EntityDeathEvent) {
        event.drops.add(Items(plugin).flyingFeather()) // add the drop
    }
}