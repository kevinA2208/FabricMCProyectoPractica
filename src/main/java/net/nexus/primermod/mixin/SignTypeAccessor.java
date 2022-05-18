package net.nexus.primermod.mixin;

import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

//Con el signTypeAccessor se puede acceder a los metodos de minecraft para crear una señal o sign o cartel
@Mixin(SignType.class)
public interface SignTypeAccessor {
    //Con el new Sign Type se le da el nombre al nuevo tipo de cartel
    @Invoker("<init>")
    static SignType newSignType(String name) {
        throw new AssertionError();
    }

    //Con registerNew se crea el cartel nuevo
    @Invoker("register")
    static SignType registerNew(SignType type) {
        throw new AssertionError();
    }
}