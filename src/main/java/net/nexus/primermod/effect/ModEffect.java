package net.nexus.primermod.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;

public class ModEffect {
    //Se crea el status effect Freeze
    public static StatusEffect FREEZE;

    //Aqui se registra el efecto FreezeEffect con la clase que creamos, y se le agrega la categoria HARMFUL y se le da un color a las particulas

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(PrimerMod.MOD_ID, name),
                new FreezeEffect(StatusEffectCategory.HARMFUL, 3124687));
    }

    //se registra el nombre del efecto FREEZE
    public static void registerEffects() {
        FREEZE = registerStatusEffect("freeze");
    }
}
