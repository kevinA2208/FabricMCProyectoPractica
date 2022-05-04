package net.nexus.primermod.item;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {

    //Con la clase ModToolMaterials se crean materiales nuevos para herramientas, y se les puede agregar parametros
    //y stats como la durabilidad, la velocidad de minar, el daño de ataque, entre otras cosas, el mining levels es el nivel que puede picar la herramienta
    //por ejemplo, el material mythril tiene nivel de minar de hierro por lo tanto puede picar materiales que se puedan picar con pico de hierro en adelante
    MYTHRIL(MiningLevels.IRON, 1200, 10.0f, 2.5f, 24, () -> Ingredient.ofItems(ModItems.MYTHRIL_INGOT)),
    BLOOD(MiningLevels.DIAMOND, 1600, 13.0f, 5.0f, 30, () -> Ingredient.ofItems(ModItems.BLOOD_INGOT));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    private ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy<Ingredient>(repairIngredient);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

}
