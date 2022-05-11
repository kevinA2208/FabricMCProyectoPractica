package net.nexus.primermod.mixin;


import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

//Aqui se crea el nuevo mixin interface que sirve para registrar recetas de pociones
@Mixin(BrewingRecipeRegistry.class)
public interface BrewingRecipeRegistryMixin {

    @Invoker("registerPotionRecipe")
    static void invokeRegisterPotionRecipe(Potion input, Item item, Potion output){
        throw new AssertionError();
    }
}
