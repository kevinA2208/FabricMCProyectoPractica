package net.nexus.primermod.painting;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;


public class ModPaintings {


    //Se crean los cuadros con sus nombres y sus resoluciones
    public static final PaintingMotive MARATHON = registerPainting("marathon",
            new PaintingMotive(16, 16));

    public static final PaintingMotive FAMILY = registerPainting("family",
            new PaintingMotive(16, 32));

    //CONSTRUCTOR

    public static PaintingMotive registerPainting(String name, PaintingMotive paintingMotive){
        return Registry.register(Registry.PAINTING_MOTIVE, new Identifier(PrimerMod.MOD_ID, name),
                paintingMotive);
    }

    public static void registerPaintings(){
        PrimerMod.LOGGER.info("Se registran los cuadros para "+PrimerMod.MOD_ID);
    }
}
