package net.nexus.primermod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class ModBloodSwordItem extends SwordItem {
    public ModBloodSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }


    //Esta función se llama despues de golpear a alguna entidad con el item, se le da un efecto de debilidad a la entidad
    //golpeada, durante 60 ticks o 3 segundos, y se agrega el attacker al final, para que en el inventario de la entidad, salga el atacante que le puso el efecto
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,60), attacker);
        return super.postHit(stack, target, attacker);
    }
}
