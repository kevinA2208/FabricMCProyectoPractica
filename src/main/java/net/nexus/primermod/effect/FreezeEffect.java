package net.nexus.primermod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FreezeEffect extends StatusEffect {
    //Constructor del freezeEffect
    protected FreezeEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    //Esta función agrega el efecto de freeze, o practicamente teletransporta a la entidad a la misma
    //posición en la que estaba cuando se le da el efecto y la velocidad se le pone en 0
    //esta funcion se aplica cada tick por lo tanto no se podrá mover hasta despues de cierto tiempo
    @Override
    public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.world.isClient()) {
            double x = pLivingEntity.getX();
            double y = pLivingEntity.getY();
            double z = pLivingEntity.getZ();

            pLivingEntity.teleport(x, y, z);
            pLivingEntity.setVelocity(0, 0, 0);
        }

        super.applyUpdateEffect(pLivingEntity, pAmplifier);
    }

    //Esta función retorna True si la entidad se le puede aplicar el efecto
    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}
