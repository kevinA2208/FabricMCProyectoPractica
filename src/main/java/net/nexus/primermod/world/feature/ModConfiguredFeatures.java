package net.nexus.primermod.world.feature;

import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.nexus.primermod.PrimerMod;
import net.nexus.primermod.block.ModBlocks;
import java.util.List;


public class ModConfiguredFeatures {

    //El flujo de los features es que tenemos que crear un ConfiguredFeature del arbol de como se genera para despues hacerlo un PlacedFeature
    //para despues hacer un ConfiguredFeature de la vegetación de este arbol y donde debería spawnear

    //Con esta función se detalla el como se van a generar los arboles del sapling jacaranda
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JACARANDA_TREE =
            //Primero se registra el ConfiguredFeatured que en este caso será el jacaranda_tree y va a ser un feature TREE, y se pone el constructor
            //de arboles con el TreeFeatureConfig.Builder
            ConfiguredFeatures.register("jacaranda_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    //Aqui se le da el blockState que en este caso es el jacaranda_log
                    //este será el tipo de bloque que estarán hechos los troncos del arbol que creamos
                    BlockStateProvider.of(ModBlocks.JACARANDA_LOG),
                    //Con el straightTrunkPlacer se le da el tipo de orden que van a estar colocados estos troncos (hay distintos tipos como
                    //los arboles grandes, arboles gigantes oscuros, etc)
                    //el primer parametro es la altura base, el segundo parametro es la altura random que puede llegar a tener si no es la base
                    //y el tercer parametro es la otra altura random que puede llegar a tener si no es ninguna de las otras dos
                    new StraightTrunkPlacer(5, 6, 3),
                    //Despues el segundo blockState que se le da es el bloque jacaranda_leaves
                    //que son las hojas del arbol jacaranda que va a tener
                    BlockStateProvider.of(ModBlocks.JACARANDA_LEAVES),
                    //El BlobFoliagePlacer es el como se ponen y ordenan las hojas en el arbol en este caso Blob
                    //hay otros tipos, como el de los pinos (pineFollagePlacer) o uno que organice las hojas random (RandomSpreadFollagePlacer)
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                    //el twolayersfeaturesize determina cuantos features de estos pueden spawnear algo asi
                    new TwoLayersFeatureSize(1, 0, 2)).build());


    //Con está función se coloca el featured configured que ya hicimos anteriormente
    public static final RegistryEntry<PlacedFeature> JACARANDA_CHECKED =
            PlacedFeatures.register("jacaranda_checked", JACARANDA_TREE,
                    //El wouldSurvive es para que los arboles solo puedan spawnear en el mundo en bloques donde puedan sobrevivir
                    //si no estuviera los arboles podrian spawnear en el aire o en otros lugares donde no deberian
                    PlacedFeatures.wouldSurvive(ModBlocks.JACARANDA_SAPLING));


    //Con está función registramos un nuevo ConfiguredFeature para el tipo de spawn que va a tener nuestro PlacedFeature Jacaranda_checked
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> JACARANDA_SPAWN =
            ConfiguredFeatures.register("jacaranda_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(JACARANDA_CHECKED, 0.5f)),
                            JACARANDA_CHECKED));

    //Se registra el mod de ConfiguredFeatures
    //Este tiene que estar de primero en el archivo PrimerMod OnInitialize()
    public static void registerConfiguredFeatures() {
        System.out.println("Se registran los ModConfiguredFeatures para " + PrimerMod.MOD_ID);
    }
}
