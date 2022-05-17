package net.nexus.primermod.block.entity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nexus.primermod.item.inventory.ImplementedInventory;
import net.nexus.primermod.recipe.MythrilBlasterRecipe;
import net.nexus.primermod.screen.MythrilBlasterScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MythrilBlasterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    //Con este metodo,se le añade una lista de inventario dentro del gui del bloque, en este caso es de 4 espacios
    //y empezará vacia
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(4, ItemStack.EMPTY);

    //Estas variables regulan las barras de progreso y el combustible en la interfaz del inventario del bloque
    //es necesario el propertyDelegate para sincronizar estos progresos entre el servidor y el cliente
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;

    //Aumentando el maxProgress va a tardar mas en craftear el item que se quiere en el output
    private int maxProgress = 150;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public MythrilBlasterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MYTHRIL_BLASTER, pos, state);

        //El propertyDelegate sincroniza las variables que tenemos
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return MythrilBlasterBlockEntity.this.progress;
                    case 1: return MythrilBlasterBlockEntity.this.maxProgress;
                    case 2: return MythrilBlasterBlockEntity.this.fuelTime;
                    case 3: return MythrilBlasterBlockEntity.this.maxFuelTime;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: MythrilBlasterBlockEntity.this.progress = value; break;
                    case 1: MythrilBlasterBlockEntity.this.maxProgress = value; break;
                    case 2: MythrilBlasterBlockEntity.this.fuelTime = value; break;
                    case 3: MythrilBlasterBlockEntity.this.maxFuelTime = value; break;
                }
            }

            public int size() {
                return 4;
            }
        };
    }

    //Obtiene el nombre del bloque
    @Override
    public Text getDisplayName() {
        return new LiteralText("Mythril Blaster");
    }



    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new MythrilBlasterScreenHandler(syncId, inv, this, this.propertyDelegate);
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
        //Se pone en el nbt el int con la llave de cada variable, y se le pone los datos de cada variable
        //cuando se guarde una partida se va a guardar tambien el progreso de las recetas en el bloque
        nbt.putInt("blaster.progress", progress);
        nbt.putInt("blaster.fuelTime", fuelTime);
        nbt.putInt("blaster.maxFuelTime", maxFuelTime);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        //Se lee el nbt y se obtiene la información de cada variable de progreso con su llave
        //y se guarda en las variables
        //cuando se cargue la partida se va a cargar tambien el progreso de las recetas en el bloque
        progress = nbt.getInt("blaster.progress");
        fuelTime = nbt.getInt("blaster.fuelTime");
        maxFuelTime = nbt.getInt("blaster.maxFuelTime");
    }

    //Este metodo se llama cuando haya un item de combustible en el primer slot del bloque
    private void consumeFuel() {
        if(!getStack(0).isEmpty()) {
            //se le agrega a la variable FuelTime, el tiempo de fuel que tiene el item dentro del slot
            this.fuelTime = FuelRegistry.INSTANCE.get(this.removeStack(0, 1).getItem());
            //al max fuel time se le añade el mismo fuel time
            this.maxFuelTime = this.fuelTime;
        }
    }
    //la función tick es llamada cada tick que pasa, dentro tiene varias condiciones para las barras de progreso y consumo
    public static void tick(World world, BlockPos pos, BlockState state, MythrilBlasterBlockEntity entity) {
        //si el bloque esta consumiendo un combustible,
        //que el fuelTime o la barra del tiempo del combustible baje
        if(isConsumingFuel(entity)) {
            //Basicamente el fuelTime es el tiempo de combustible que tiene el item puesto en el fuel slot del bloque
            //y el max fuel time es el mismo, pero cuando se esté consumiendo el combustible, el fuel time normal
            //va a ir bajando por cada tick, hasta que llegue a 0, cuando llegue a 0 ya va a desaparecer y se va a dejar
            //de consumir
            entity.fuelTime--;
        }

        //Si hay una receta en el bloque
        if(hasRecipe(entity)) {
            //si hay un item combustible en el slot de fuel en el bloque, y no hay otro combustible consumiendose
            if(hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {
                //que llame la función consumeFuel
                entity.consumeFuel();
            }
            //Si se está consumiendo un combustible
            if(isConsumingFuel(entity)) {
                //se aumenta la variable progress que es la barra de progreso para craftear el item
                entity.progress++;
                //sí el progress normal es mayor al maxProgress, el maxProgress es el progreso maximo que necesita
                // llegar el progress para craftear el item
                if(entity.progress > entity.maxProgress) {
                    //que se llame el metodo craftItem en el bloque
                    craftItem(entity);
                }
            }
            //al finalizar, si no hay recipe o receta en el inventario del bloque, que se resetee la barra de progreso con resetProgress
        } else {
            entity.resetProgress();
        }
    }

    private static boolean hasFuelInFuelSlot(MythrilBlasterBlockEntity entity) {
        return !entity.getStack(0).isEmpty();
    }

    private static boolean isConsumingFuel(MythrilBlasterBlockEntity entity) {
        return entity.fuelTime > 0;
    }

    //Esta función devuelve un true o false, dependiendo de las condiciones
    private static boolean hasRecipe(MythrilBlasterBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<MythrilBlasterRecipe> match = world.getRecipeManager()
                .getFirstMatch(MythrilBlasterRecipe.Type.INSTANCE, inventory, world);

        //si en el match esta presente una recipe de tipo MythrilBlasterRecipe, esto retorna verdadero o true
        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    //este metodo se llama cuando el progress esté lleno en el inventario del bloque
    private static void craftItem(MythrilBlasterBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<MythrilBlasterRecipe> match = world.getRecipeManager()
                .getFirstMatch(MythrilBlasterRecipe.Type.INSTANCE, inventory, world);

        //si está la receta de mythrilBlasterRecipe presente en el match
        if(match.isPresent()) {
            //se remueven los items que hay en el slot 2 y 3, que son los slots inputs, donde se ponen los items
            entity.removeStack(1,1);
            entity.removeStack(2,1);
            //y en el output slot en el slot 4, que obtenga el item que se encuentra en el output de la receta que esté en el match
            //y lo añada ahi
            entity.setStack(3, new ItemStack(match.get().getOutput().getItem(),
                    entity.getStack(3).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    //Este metodo retorna true o false, dependiendo si el output slot está vacio, o si ya está el mismo item que va a tener de resultado dentro del slot
    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(3).getItem() == output.getItem() || inventory.getStack(3).isEmpty();
    }

    //Este metodo retorna verdadero o falso, dependiendo si se puede añadir un item mas al slot output del inventario del bloque
    //si se puede retorna true sino retorna false
    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }
}
