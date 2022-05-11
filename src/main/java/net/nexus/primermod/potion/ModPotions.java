package net.nexus.primermod.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;
import net.nexus.primermod.effect.ModEffect;

public class ModPotions {
    //se crea una poción llamada FREEZE POTION
    public static Potion FREEZE_POTION;

    //Se registra la poción con el registry, se llama su status effect y se le agrega la duración de 200 ticks
    public static Potion RegisterPotion(String name){
        return Registry.register(Registry.POTION, new Identifier(PrimerMod.MOD_ID, name),
                new Potion(new StatusEffectInstance(ModEffect.FREEZE, 200, 0)));
    }

    //Se agrega el registro de la poción a la poción creada al principio
    public static void RegisterPotions(){
        FREEZE_POTION = RegisterPotion("freeze_potion");
    }
}
