package net.nexus.primermod.item.custom;

import net.minecraft.block.PressurePlateBlock;

//Con las funciones ModPressurePlateBlock, extienden de el bloque pressure plate original de minecraft
//por lo tanto nos da el esquema del objeto para poder basarnos en el con otros nuevos objetos
public class ModPressurePlateBlock extends PressurePlateBlock {
    public ModPressurePlateBlock(ActivationRule type, Settings settings) {
        super(type, settings);
    }
}
