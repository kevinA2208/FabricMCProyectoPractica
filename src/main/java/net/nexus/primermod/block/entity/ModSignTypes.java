package net.nexus.primermod.block.entity;

import net.minecraft.util.SignType;
import net.nexus.primermod.mixin.SignTypeAccessor;

public class ModSignTypes {
    //Aqui se crea el tipo de cartel llamado JACARANDA, usando los metodos del SignTypeAccessor gracias al mixin que se hizo
    //para poder crear estos carteles personalizados
    public static final SignType JACARANDA =
            SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("jacaranda"));
}
