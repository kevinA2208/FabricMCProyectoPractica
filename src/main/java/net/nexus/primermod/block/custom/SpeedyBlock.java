package net.nexus.primermod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nexus.primermod.PrimerMod;

public class SpeedyBlock extends Block {

    public SpeedyBlock(Settings settings) {

        super(settings);
    }

    //funcion onUse se llama cuando se right clickea un bloque
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        //se revisa si el mundo es cliente
        if(world.isClient()){
            //si la mano del jugador es la mano principal mande un mensaje
            if(hand == hand.MAIN_HAND){
                player.sendMessage(new LiteralText("Client: this is the client, main hand"), false);
            }else{
                player.sendMessage(new LiteralText("Client: this is the client, Off hand"), false);
            }
        }

        return ActionResult.SUCCESS;
    }

    //funcion onSteppedOn se llama cuando se pisa un bloque
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(!world.isClient()){
            //si hay una entidad viva
            if(entity instanceof LivingEntity){
                //En living entity se guarda el jugador o entidad viva, que este encima del bloque
                LivingEntity livingEntity = ((LivingEntity) entity);
                //Aqui se le da un efecto a la entidad que este encima del bloque, el efecto de velocidad con duración de 300 ticks, 100 ticks equivale a 5 segundos
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300));
            }
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}
