package net.nexus.primermod.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;

public class ModTags {
    public static class Blocks{

        //Se agrega un tag a Dowsing_rod, que sirve para agregar bloques valiosos a este tag, para asi despues llamarse
        //en la funcion isValuableBlock de la clase DowsingRodItem, para poder agregar bloques valiosos mas facilmente
        //estos bloques se pueden agregar desde el archivo json dowsing_rod_detectable_blocks en resources/data/primermod/tags/blocks
        public static final TagKey<Block> DOWSING_ROD_DETECTABLE_BLOCKS =
                createTag("dowsing_rod_detectable_blocks");


        //Se crean los constructores de tags

        private static TagKey<Block> createTag(String name){
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(PrimerMod.MOD_ID, name));

        }
        //Los commonTags tendrán el nombre c y serán commonTags que se pueden compartir entre diferentes mods
        private static TagKey<Block> createCommonTag(String name) {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier("c", name));
        }

    }

    public static class Items{

        //Se agrega un  common tag a mythril ingots y nuggets, que sirven para llamar estos tags en las recetas por ejemplo
        //al crear la receta de bloques mythril, se puede llamar este tag de mythril ingots, asi si existe otro objeto similar de otro mod
        //no va a interferir
        public static final TagKey<Item>MYTHRIL_INGOTS = createCommonTag("mythril_ingots");
        public static final TagKey<Item>MYTHRIL_NUGGETS = createCommonTag("mythril_nuggets");

        //Se crean los constructores de tags


        private static TagKey<Item> createTag(String name){
            return TagKey.of(Registry.ITEM_KEY, new Identifier(PrimerMod.MOD_ID, name));
        }
        //Los commonTags tendrán el nombre c y serán commonTags que se pueden compartir entre diferentes mods
        private static TagKey<Item> createCommonTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
        }
    }
}
