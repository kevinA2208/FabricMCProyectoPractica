package net.nexus.primermod.item;

import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    //Para crear comida se pueden sacar desde los archivos de minecraft
    //FoodComponents.java, puedo usar los que estan ahi y personalizarlos a mi gusto
    //en hunger se pone la cantidad de barras de comida que llena, cada cantidad se reparte en una mitad
    //si en hunger se pone 1, significa que repone la mitad de una barrita de hambre del jugador, si pone 2 repone una barrita completa, y asi
    //en este caso està en 6, por lo tanto deberia llenar 3 barritas de hambre
    public static final FoodComponent FRIES_POTATO = new FoodComponent.Builder().hunger(6).saturationModifier(0.5f).build();
    public static final FoodComponent SODA_DRINK = new FoodComponent.Builder().hunger(4).saturationModifier(0.5f).build();
    public static final FoodComponent GRAPE = new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build();
}
