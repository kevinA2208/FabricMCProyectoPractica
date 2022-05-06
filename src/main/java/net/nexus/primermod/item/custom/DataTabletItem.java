package net.nexus.primermod.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DataTabletItem extends Item {

    //El data tablet es una tablet o celular donde se guardará la información del ultimo bloque valioso encontrado por el item DowsingRod
    public DataTabletItem(Settings settings) {
        super(settings);
    }

    /*NBT:
    NBT es un componente por asi decirlo, donde se guarda información y gracias a este
    se puede mover o transportar información de un item a otro, como por ejemplo, enviar la información de
    el item dowsingRod a la DataTablet, para mostrarla ahi despues*/
    //cuando se llama esta funcion, revisa si el item tiene información asociada, si es asi, que reemplaze la información por un
    //nuevo componente nbt vacio, que despues se añadirá
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user.getStackInHand(hand).hasNbt()){
            user.getStackInHand(hand).setNbt(new NbtCompound());
        }

        return super.use(world, user, hand);

    }

    //Esta funcion le da un brillo a la tablet si tiene información de ores guardadas
    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasNbt();
    }

    //con esta función imprime la información del DowsingRod usado sobre la posicion del ultimo ore encontrado
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(stack.hasNbt()){
            String currentOre = stack.getNbt().getString("primermod.last_ore");
            tooltip.add(new LiteralText(currentOre));
        }
    }
}
