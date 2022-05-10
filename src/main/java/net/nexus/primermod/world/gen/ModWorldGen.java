package net.nexus.primermod.world.gen;

public class ModWorldGen {
    //Aqui se añaden los metodos de las clases que hemos añadido para generarse en el mundo
    public static void  generateModWorldGen(){
        ModTreeGeneration.generateTrees();
        ModFlowerGeneration.generateFlower();
    }
}
