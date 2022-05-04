package net.nexus.primermod.block.custom;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

public class ModAxeItem extends AxeItem {
    //Con esta clase se extienden de las clases de herramientas originales de minecraft, para poder acceder
    //a sus funciones y atributos de forma publica, ya que minecraft los ha protegido
    public ModAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
