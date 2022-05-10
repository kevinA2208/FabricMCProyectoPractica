package net.nexus.primermod.world.gen;

public class ModWorldGen {
    //Aqui se añaden los metodos de las clases que hemos añadido para generarse en el mundo
    //Los metodos deben estar en orden de esta forma
    /*RAW_GENERATION,
    LAKES,
    LOCAL_MODIFICATIONS,
    UNDERGROUND_STRUCTURES,
    SURFACE_STRUCTURES,
    STRONGHOLDS,
    UNDERGROUND_ORES,
    UNDERGROUND_DECORATION,
    FLUID_SPRINGS,
    VEGETAL_DECORATION,
    TOP_LAYER_MODIFICATION; ya que deben generarse en ese orden para que funcionen dependiendo del
    generation step feature que se agregue al metodo generation*/
    public static void  generateModWorldGen(){
        ModOreGeneration.generateOres();

        ModTreeGeneration.generateTrees();
        ModFlowerGeneration.generateFlower();
    }
}
