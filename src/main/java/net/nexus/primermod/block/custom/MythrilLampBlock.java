package net.nexus.primermod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//Un bloque es el simple bloque, sin embargo un blockstate son las instancias
//o diferentes variantes que tiene un bloque, este blockstate tiene propiedades
//estas propiedades podemos definirlas nosotros mismos y añadirla con appendProperties
//Un bloque puede tener multiples y diferentes propiedades
//las propiedades de blockstate son usadas para referir a diferentes modelos dentro de
//los archivos json blockstates para el bloque
//para cambiar un blockstate se usa world.setBlockState y este recibe dos parametros
//el primer parametro es la posicion del bloque en su estado inicial
//el segundo parametro es el nuevo estado de esa posicion del bloque
//el tercer parametro no es tan importante, solo poner Block.NOTIFI_ALL

public class MythrilLampBlock extends Block {

    //se crea la propiedad de clicked, que puede ser true o false, dependiendo si se le dio
    //click o no al bloque
    public static final BooleanProperty CLICKED = BooleanProperty.of("clicked");

    public MythrilLampBlock(Settings settings) {
        super(settings);
    }

    //La función onUse se llama cuando se da click derecho con la mano principal
    //al llamarla guarda el estado del bloque que se usó en currentState, y
    //se cambia el estado del bloque con setBlockState, dandole la posicion donde se dió click
    //al bloque, cambiando a el estado contrario del currentState, que si esta en false estaría en true
    //y viceversa, al hacer esto cada vez que de click derecho al bloque mythril lamp se encenderá y apagará
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient() && hand == Hand.MAIN_HAND){
            boolean currentState = state.get(CLICKED);
            world.setBlockState(pos, state.with(CLICKED, !currentState), Block.NOTIFY_ALL);
        }


        return ActionResult.SUCCESS;
    }

    //se añade la propiedad clicked
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CLICKED);
    }
}
