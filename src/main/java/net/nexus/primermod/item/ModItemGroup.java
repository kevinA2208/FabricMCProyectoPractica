package net.nexus.primermod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.nexus.primermod.PrimerMod;

public class ModItemGroup {
    public static final ItemGroup MYTHRIL = FabricItemGroupBuilder.build(new Identifier(PrimerMod.MOD_ID, "mythril"),
            () -> new ItemStack(ModItems.MYTHRIL_INGOT));

    public static final ItemGroup BLOOD = FabricItemGroupBuilder.build(new Identifier(PrimerMod.MOD_ID, "blood"),
            () -> new ItemStack(ModItems.BLOOD_INGOT));
}
