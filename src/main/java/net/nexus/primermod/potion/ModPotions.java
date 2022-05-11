package net.nexus.primermod.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;
import net.nexus.primermod.effect.ModEffect;
import net.nexus.primermod.item.ModItems;
import net.nexus.primermod.mixin.BrewingRecipeRegistryMixin;

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

        //se registran las recetas de pociones
        registerPotionRecipes();
    }

    //Con esta clase se crea la receta de la poción gracias al mixin que hicimos con el interface BrewingRecipeRegistryMixin
    //Se le agrega en el primer parametro la awkward potion o la botella con agua que no da ningun efecto, para crear la pocion
    //el segundo parametro es el que le va a dar el efecto, que en este caso es el mythril ingot
    //y el tercer parametro es la potion con el efecto resultante de esta receta
    private static void registerPotionRecipes(){
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, ModItems.MYTHRIL_INGOT, ModPotions.FREEZE_POTION);
    }
}
