package org.bukkit.craftbukkit.inventory;

import net.minecraft.inventory.IInventory;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemStack;

public class CraftInventoryHorse extends CraftSaddledInventory implements HorseInventory {

    public CraftInventoryHorse(IInventory inventory) {
        super(inventory);
    }

    public ItemStack getArmor() {
       return getItem(1);
    }

    public void setArmor(ItemStack stack) {
        setItem(1, stack);
    }
}