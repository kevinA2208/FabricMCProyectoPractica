package net.nexus.primermod.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class ModResultSlot extends Slot {
    public ModResultSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    //El metodo basicamente dice que no se puede insertar ningun item en el slot de result
    //que en este caso es el 4 slot o 3 en index, sin importar el tipo de item
    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }
}
