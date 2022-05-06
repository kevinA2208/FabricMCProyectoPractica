package net.nexus.primermod.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryUtil {

    //Está funcion pregunta si el jugador tiene una misma tablet stack, osea tiene dos tablets en el inventario que devuelva verdadero
    public static boolean hasPlayerStackInInventory(PlayerEntity player, Item item) {
        for(int i = 0; i < player.getInventory().size(); i++) {
            ItemStack currentStack = player.getInventory().getStack(i);
            if (!currentStack.isEmpty() && currentStack.isItemEqual(new ItemStack(item))) {
                return true;
            }
        }

        return false;
    }

    //Si el jugador tiene la tablet stack, que devuelva la primera tablet que aparezca en el inventario
    public static int getFirstInventoryIndex(PlayerEntity player, Item item) {
        for(int i = 0; i < player.getInventory().size(); i++) {
            ItemStack currentStack = player.getInventory().getStack(i);
            if (!currentStack.isEmpty() && currentStack.isItemEqual(new ItemStack(item))) {
                return i;
            }
        }

        return -1;
    }
}
