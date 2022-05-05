package net.nexus.primermod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nexus.primermod.util.ModTags;
import org.jetbrains.annotations.Nullable;


import net.minecraft.util.registry.Registry;
import java.util.List;

public class DowsingRodItem extends Item {
    public DowsingRodItem(Settings settings){
        super(settings);
    }


    //idea de mini mod, hacer un item parecido al stand harvest de jojos, usando el sonido
    //cuando encuentre un bloque y la imagen de el como item
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        //se mira si el mundo en el que esta el jugador es cliente
        if(context.getWorld().isClient()){
            //pos es position
            //se trae la posicion del bloque que hizo click derecho
            BlockPos positionClicked = context.getBlockPos();
            //se trae el jugador y se guarda
            PlayerEntity player = context.getPlayer();
            //variable si se encontró el bloque
            boolean foundBlock = false;


            //se hace un for con la posicion Y para determinar a cuantos bloques
            //hacia abajo esta el bloque valioso
            for (int i = 0; i <= positionClicked.getY() + 64 ; i++) {
                //se guarda cada bloque que hay debajo del bloque que right clickeo el jugador
                Block blockBelow = context.getWorld().getBlockState(positionClicked.down(i)).getBlock();

                //si el bloque que esta abajo es valioso, se traen las coordenadas del bloque
                //la variable foundBlock se pone en true
                if(isValuableBlock(blockBelow)){
                    outputValuableCoordinates(positionClicked.down(i), player, blockBelow);
                    foundBlock = true;
                    break;
                }
            }

            //si no se encuentra un bloque valioso debajo del jugador, se manda un mensaje diciendo que no se ha encontrado
            if(!foundBlock){
                player.sendMessage(new TranslatableText("item.primermod.dowsing_rod.no_valuables"), false);
            }
        }

        //creo que en esta parte es para que el item no se pueda stackear o acumular
        context.getStack().damage(1, context.getPlayer(),
                (player) -> player.sendToolBreakStatus(player.getActiveHand()));

        return super.useOnBlock(context);
    }


    //FUNCIONES

    //con la funcion appendToolTip se le añade un tip al item, que se puede ver desde el inventario
    //se le agrega una llave y esta llave se llama desde el archivo en_us.json de lang, para añadirle el texto o mensaje
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()){
            tooltip.add(new TranslatableText("item.primermod.dowsing_rod.tooltip.shift"));
        }else{
            tooltip.add(new TranslatableText("item.primermod.dowsing_rod.tooltip"));
        }
    }

    //en esta función se traen las coordenadas del bloque valioso
    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block blockBelow){
        player.sendMessage(new LiteralText("Encontrado" + blockBelow.asItem().getName().getString() + "en " +
                "("+ blockPos.getX() + ", "+ blockPos.getY() + ", " + blockPos.getZ() + ")"), false);
    }

    //en esta función se chequea si el bloque es igual a un bloque de
    //carbón, cobre, diamante o hierro, si es asi, es un bloque valioso
    private boolean isValuableBlock(Block block) {
        return Registry.BLOCK.getOrCreateEntry(Registry.BLOCK.getKey(block).get()).isIn(ModTags.Blocks.DOWSING_ROD_DETECTABLE_BLOCKS);
    }
}
