package net.nexus.primermod.recipe;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;

//en esta clase se registran las recetas, el serializer y el type que tiene está
public class ModRecipes {
    public static void registerRecipes(){
        //se registra el serializer de la receta con su id y su instancia
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(PrimerMod.MOD_ID, MythrilBlasterRecipe.Serializer.ID),
                MythrilBlasterRecipe.Serializer.INSTANCE);
        //se registra el type de la receta con su id y su instancia
        Registry.register(Registry.RECIPE_TYPE, new Identifier(PrimerMod.MOD_ID, MythrilBlasterRecipe.Type.ID),
                MythrilBlasterRecipe.Type.INSTANCE);
    }
}
