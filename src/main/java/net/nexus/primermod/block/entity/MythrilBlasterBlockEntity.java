package net.nexus.primermod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nexus.primermod.item.ModItems;
import net.nexus.primermod.item.inventory.ImplementedInventory;
import net.nexus.primermod.screen.MythrilBlasterScreenHandler;
import org.jetbrains.annotations.Nullable;

public class MythrilBlasterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    //Con este metodo,se le añade una lista de inventario dentro del gui del bloque, en este caso es de 4 espacios
    //y empezará vacia
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(4, ItemStack.EMPTY);

    public MythrilBlasterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MYTHRIL_BLASTER, pos, state);
    }

    //Obtiene el nombre del bloque
    @Override
    public Text getDisplayName() {
        return new LiteralText("Mythril Blaster");
    }



    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new MythrilBlasterScreenHandler(syncId, inv, this);
    }

    //Retorna la lista inventario de items del bloque
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    //Estos metodos es para que se salven completamente los items que esten en el inventario del bloque
    //como cuando guardemos una partida y nos salgamos, se guarden y aparezcan correctamente
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
    }

    //la función tick es llamada cada tick que pasa, y dentro tiene una condicion de si hay una receta en el bloque, y si tiene todos
    // los slots de inventario llenos que llame a la funcion craftItem
    public static void tick(World world, BlockPos pos, BlockState state, MythrilBlasterBlockEntity entity) {
        if(hasRecipe(entity) && hasNotReachedStackLimit(entity)) {
            craftItem(entity);
        }
    }

    //este metodo se llama cuando se cumplen las condiciones hasRecipe y stackLimit en el tick del inventario del bloque
    //basicamente si cumple eso, elimina los items
    //que hay ne los slots del inventario del bloque, y agrega uno nuevo, que es el mythril pickaxe en el 4 slot del inventario
    //del bloque mythril blaster, siempre va a ser un mythril pickaxe ya que este item no se stackea

    private static void craftItem(MythrilBlasterBlockEntity entity) {
        entity.removeStack(0, 1);
        entity.removeStack(1, 1);
        entity.removeStack(2, 1);

        entity.setStack(3, new ItemStack(ModItems.MYTHRIL_PICKAXE,
                entity.getStack(3).getCount() + 1));
    }

    //Esta función devuelve un true o false, dependiendo si cumple con las condiciones de:
    //si el item del primer slot del inventario del bloque es igual a un lilac flower bulb
    //y si el item del segundo slot es igual a un golden pickaxe
    //y si el item del tercer slot es igual a un mythril ingot
    //que devuelva true, y que si tiene una receta o hasRecipe
    private static boolean hasRecipe(MythrilBlasterBlockEntity entity) {
        boolean hasItemInFirstSlot = entity.getStack(0).getItem() == ModItems.LILAC_FLOWER_BULB;
        boolean hasItemInSecondSlot = entity.getStack(1).getItem() == Items.GOLDEN_PICKAXE;
        boolean hasItemInThirdSlot = entity.getStack(2).getItem() == ModItems.MYTHRIL_INGOT;

        return hasItemInFirstSlot && hasItemInSecondSlot && hasItemInThirdSlot;
    }

    //La función hasNotReachedStackLimit, devuelve un true o false, para determinar si
    //los 3 slots del inventario del bloque estan llenos, o tienen objetos, si es asi
    //devuelve un true
    private static boolean hasNotReachedStackLimit(MythrilBlasterBlockEntity entity) {
        return entity.getStack(3).getCount() < entity.getStack(3).getMaxCount();
    }
}
