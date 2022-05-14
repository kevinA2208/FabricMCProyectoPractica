package net.nexus.primermod.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.nexus.primermod.screen.slot.ModFuelSlot;
import net.nexus.primermod.screen.slot.ModResultSlot;

public class MythrilBlasterScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public MythrilBlasterScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(4));
    }

    public MythrilBlasterScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.MYTHRIL_BLASTER_SCREEN_HANDLER ,syncId);
        checkSize(inventory, 4);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);


        //Se añaden los slots al gui del inventario del bloque, tambien se añade el index y las coordenadas donde estará
        //el slot en el gui

        //primer slot, el fuel slot donde va el combustible para que funcione el bloque
        this.addSlot(new ModFuelSlot(inventory, 0, 18, 50));

        //Estos dos slots son slots normales donde van items normales
        this.addSlot(new Slot(inventory, 1, 66, 16));
        this.addSlot(new Slot(inventory, 2, 66, 50));
        //el ultimo slot es el resultSlot donde va el resultado de la recipe de los otros slots, no se pueden poner items
        this.addSlot(new ModResultSlot(inventory, 3, 114, 33));

        //Se muestra el inventario y el hotbar del jugador en el inventory screen del bloque
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }


    //El metodo transferSlot simplemente se asegura de que cuando se presione shift + click en el item de tu inventario
    //se transfiera al inventario del bloque blaster correctamente, y viceversa
    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }


    //Este metodo añade el inventario del jugador para que se muestre en la pantalla al abrir
    //el inventario del bloque blaster
    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    //Este metodo añade la barra de herramientas principales del jugador en la pantalla al abrir el inventario del bloque blaster
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}
