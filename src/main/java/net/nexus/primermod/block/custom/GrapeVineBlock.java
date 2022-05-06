package net.nexus.primermod.block.custom;

import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.nexus.primermod.item.ModItems;

public class GrapeVineBlock extends CropBlock {
    //esta clase es el bloque de cultivo de uvas o grapes
    public GrapeVineBlock(Settings settings) {
        super(settings);
    }

    //se sobreescribe la funcion de obtener semillas para cambiarlas por semillas de grape que son las que creamos
    @Override
    protected ItemConvertible getSeedsItem() {

        return ModItems.GRAPE_SEEDS;
    }
}
