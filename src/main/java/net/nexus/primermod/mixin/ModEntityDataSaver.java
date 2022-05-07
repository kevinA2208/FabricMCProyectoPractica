package net.nexus.primermod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.nexus.primermod.util.IEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;




//Con el mixin, vamos a inyectarle o agregarle cosas nuevas que nosotros queramos
//a la clase de minecraft en este caso Entity
//las clases mixin deberian ser clases abstractas y solo se llegaría a ella por
// las interfaces que se crean
@Mixin(Entity.class)
public abstract class ModEntityDataSaver implements IEntityDataSaver {
    private NbtCompound persistentData;

    //con esta función se trae la informacion persistente, si no hay
    //o está nulo esta persistentData se crea un nuevo Nbtcompound haciendo que siga el flujo
    //desde cero
    @Override
    public NbtCompound getPersistentData() {
        if(this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    //Con el @inject se le inyecta nuevas lineas a la funcion o metodo writeNbt de la clase entity
    //@At("HEAD) significa que se lo inyecta en la parte de arriba del metodo
    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info) {
        //Aqui se le manda que si la data persistente es no nula, osea que sí hay,
        //que esta se guarde en el compound o compuesto nbt con la key, como un map
        if(persistentData != null) {
            nbt.put("primermod.kaupen_data", persistentData);
        }
    }

    //aqui se inyecta el metodo readNbt tambien en la parte de arriba
    //una condicion que si el nbt contiene la key que le pusimos antes,
    //que este compound se guardé en persistentData
    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("primermod.kaupen_data", 10)) {
            persistentData = nbt.getCompound("primermod.kaupen_data");
        }
    }


}
