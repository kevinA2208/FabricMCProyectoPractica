package net.nexus.primermod.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import net.nexus.primermod.item.ModFoodComponents;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SodaDrinkItem extends Item {

    public SodaDrinkItem(Settings settings) {super(settings);}


    //Se crean los tips del item especial
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()){
            tooltip.add(new TranslatableText("item.primermod.soda_drink.tooltip.shift"));
        }else{
            tooltip.add(new TranslatableText("item.primermod.soda_drink.tooltip"));
        }
    }

    //Esta funcion se llama al terminar de usar un item, en este caso la soda
    //al terminar la soda, le da al jugador regeneracion por 5 segundos
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user){
        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, (100)));
        }
        return super.finishUsing(stack, world, user);
    }


    //Estas funcines traen los sonidos de uso y bebida, en este caso trae los de botellas de miel
    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

}
