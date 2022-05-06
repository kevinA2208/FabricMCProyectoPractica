package net.nexus.primermod.util;

import net.minecraft.nbt.NbtCompound;

//Se crea una interfaz para tener la persistentData del mixin que inyectamos en la clase entity
public interface IEntityDataSaver {
    NbtCompound getPersistentData();
}
