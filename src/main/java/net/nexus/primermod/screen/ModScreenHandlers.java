package net.nexus.primermod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.nexus.primermod.PrimerMod;

public class ModScreenHandlers {
    //Se registran los screen handlers de los bloques
    public static ScreenHandlerType<MythrilBlasterScreenHandler> MYTHRIL_BLASTER_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(
                    new Identifier(PrimerMod.MOD_ID, "mythril_blaster"),
                    MythrilBlasterScreenHandler::new);
}
