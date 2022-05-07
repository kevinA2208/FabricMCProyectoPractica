package net.nexus.primermod.sound;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;


public class ModSounds {

    //Se registra sonido cuando se encuentra un ore con dowsing rod
    public static SoundEvent DOWSING_ROD_FOUND_ORE = registerSoundEvent("dowsing_rod_found_ore");

    //set de sonidos de la mythril lamp
    public static SoundEvent MYTHRIL_LAMP_BREAK = registerSoundEvent("mythril_lamp_break");
    public static SoundEvent MYTHRIL_LAMP_STEP = registerSoundEvent("mythril_lamp_step");
    public static SoundEvent MYTHRIL_LAMP_PLACE = registerSoundEvent("mythril_lamp_place");
    public static SoundEvent MYTHRIL_LAMP_HIT = registerSoundEvent("mythril_lamp_hit");
    public static SoundEvent MYTHRIL_LAMP_FALL = registerSoundEvent("mythril_lamp_fall");

    //En el blocksoundgroup se definen todos los sonidos de los bloques mythril
    public static final BlockSoundGroup MYTHRIL_SOUNDS = new BlockSoundGroup(1f, 1f,
            ModSounds.MYTHRIL_LAMP_BREAK, ModSounds.MYTHRIL_LAMP_STEP, ModSounds.MYTHRIL_LAMP_PLACE,
            ModSounds.MYTHRIL_LAMP_HIT, ModSounds.MYTHRIL_LAMP_FALL);

    //constructor de sonidos
    private static SoundEvent registerSoundEvent(String name){
        Identifier id = new Identifier(PrimerMod.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
