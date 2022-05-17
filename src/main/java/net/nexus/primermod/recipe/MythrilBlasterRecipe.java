package net.nexus.primermod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class MythrilBlasterRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public MythrilBlasterRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }


    //este metodo revisa o confirma que los items que esten en la receta, sean los mismos que estan en el inventario del item
    //mythril blaster, si esto es correcto se retorna true
    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        //primero se revisa si el item de la receta en el index 0 que sería el primer item
        //es el mismo que el item en el inventario del bloque en el index 1, que sería el primer slot input
        //si es correcto se hace lo mismo pero con el segundo item de la receta en el index 1
        //en el segundo slot input en el index 2
        if(world.isClient()){
            return false;
        }
        if(recipeItems.get(0).test(inventory.getStack(1))){
            return recipeItems.get(1).test(inventory.getStack(2));
        }

        return false;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    //Este metodo simplemente crea la instancia del tipo de recipe
    public static class Type implements RecipeType<MythrilBlasterRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "mythril_blaster";
    }

    //Este metodo serializa la información de json, para poder crear las recetas
    //el id que se pone en este metodo sirve para crear las recetas en los archivos json
    public static class Serializer implements RecipeSerializer<MythrilBlasterRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "mythril_blaster"; // este es el nombre que se le da en el archivo json


        //Este otro metodo read es el que lee la información json
        @Override
        public MythrilBlasterRecipe read(Identifier id, JsonObject json) {
            //primero se guarda el output, que viene de la shapedRecipe, de un archivo json, que tenga el id "output"
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            //despues guardamos los ingredientes en un jsonArray, y se buscan los ingredientes con el id "ingredients"
            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            //se crea una lista de ingredientes vacia, junto con el tamaño de los slots inputs que tenemos,
            // en nuestro caso son solo dos, ya que uno es del combustible y el otro del resultado o el output
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(2, Ingredient.EMPTY);

            //despues a la lista inputs que esta vacia, se le settea con los ingredientes que estan en el array
            //que vienen del archivo json con el id "ingredients" y se retorna esta información
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new MythrilBlasterRecipe(id, output, inputs);
        }

        //el BUF es como un transportador donde se guardan unos datos, para despues traerse desde otro lado, funciona con write y read

        //este metodo trae el buf que escribimos en write
        @Override
        public MythrilBlasterRecipe read(Identifier id, PacketByteBuf buf) {
            //primero en una lista llamada
            //inputs guarda la cantidad de ingredientes que escribimos en el buf
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
            //despues en un for de la cantidad de items que hay en la lista inputs, guarda cada item de la receta
            //que escribimos en el buf
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }
            //por ultimo guarda el itemStack que es donde se guardó el output y se guarda en una variable output
            //y se retorna toda esta información
            ItemStack output = buf.readItemStack();
            return new MythrilBlasterRecipe(id, output, inputs);
        }


        //Este metodo escribe la información de la receta en un buf
        @Override
        public void write(PacketByteBuf buf, MythrilBlasterRecipe recipe) {
            //primero guarda el tamaño de cuantos ingredientes tenemos
            buf.writeInt(recipe.getIngredients().size());
            //despues loopea todos los ingredientes que hay en un for para escribir cada ingrediente en el buf
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            //despues escribe en el buf el output de la recipe, que es resultado que da la receta y se guarda en un ItemStack
            buf.writeItemStack(recipe.getOutput());
        }
    }}
