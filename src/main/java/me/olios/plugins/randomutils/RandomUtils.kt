package me.olios.plugins.randomutils

import me.olios.plugins.randomutils.listeners.EntityDeathListener
import me.olios.plugins.randomutils.listeners.PlayerInteractListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class RandomUtils : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic

        registerListeners()
    }

    private fun registerListeners() {
        Bukkit.getServer().pluginManager.registerEvents(EntityDeathListener(this), this)
        Bukkit.getServer().pluginManager.registerEvents(PlayerInteractListener(this), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}