package me.olios.plugins.randomutils

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class Items(private val plugin: RandomUtils) {

    fun flyingFeather() : ItemStack {
        val flyingFeatherLore = listOf(
            "§7Hold §eRIGHT-CLICK §7to fly.",
            "",
            "§byou have §a3 §buses left.",
        )

        val itemStack = createItemStack(Material.FEATHER, "§eWarden’s Feather", flyingFeatherLore)  // get the ItemStack
        val itemMeta = itemStack.itemMeta  // get the itemMeta

        itemMeta.addEnchant(Enchantment.OXYGEN, 0, false)   // add the glowing effect
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)  // hide the enchantment

        val key = NamespacedKey(plugin, "Uses")
        itemMeta.persistentDataContainer.set(key, PersistentDataType.INTEGER, 3)  // set the default usages to 3
        itemStack.itemMeta = itemMeta

        return itemStack
    }

    fun updateFlyingFeather(itemStack: ItemStack) {
        val itemMeta = itemStack.itemMeta
        val key = NamespacedKey(plugin, "Uses")
        val uses = itemMeta.persistentDataContainer.get(key, PersistentDataType.INTEGER)  // get the uses amount

        if (uses != null && uses > 0) {
            itemMeta.persistentDataContainer.set(key, PersistentDataType.INTEGER, uses-1)

            val lore = listOf(  // update the lore
                Component.text("§7Hold §eRIGHT-CLICK §7to fly."),
                Component.empty(),
                Component.text("§bYou have §a$uses §buses left.")
            )
            itemMeta.lore(lore)  // set the lore
        }
        else itemStack.amount = 0  // remove the item

        itemStack.itemMeta = itemMeta // implement the changes
    }



    private fun createItemStack(material: Material, displayName: String, loreLines: List<String>, customModelData: Int? = null): ItemStack {
        val item = ItemStack(material) // crate the ItemStack
        val itemMeta = item.itemMeta

        if(customModelData != null) itemMeta.setCustomModelData(customModelData)  // set the custom model data if exist
        itemMeta.displayName(Component.text(displayName))  // set the display name

        val loreComponents = loreLines.map { Component.text(it) }  // adjust the lore

        itemMeta.lore(loreComponents)  // set the lore

        item.itemMeta = itemMeta
        return item
    }
}