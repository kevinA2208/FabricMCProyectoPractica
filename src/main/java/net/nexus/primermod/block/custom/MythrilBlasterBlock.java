package net.nexus.primermod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;


//Esta clase custom del mythril blaster o fundidor mythril es simplemente para que el bloque
//pueda rotar segun como lo pongamos
public class MythrilBlasterBlock extends Block {
    //Esta función le da al bloque la propiedad de rotar horizontalmente, ya que queremos que rote, gracias al blockstate
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public MythrilBlasterBlock(Settings settings) {
        super(settings);
    }

    //El voxel shape es el corte que se le da al bloque especial, para que no tenga la forma de un bloque normal y cuando nos paremos
    //sobre el estemos en el aire, sino que los limites del bloque van a ser iguales a la textura, no a un bloque, ajustandose a la textura del bloque mismo
    //estos voxelShape se sacan del blockBench, con el plugin ModUtils

    //Voxel shape del bloque en dirección norte
    private static final VoxelShape SHAPE_N = Stream.of(
                    Block.createCuboidShape(13, 0, 0, 16, 3, 3),
                    Block.createCuboidShape(13, 0, 13, 16, 3, 16),
                    Block.createCuboidShape(0, 0, 13, 3, 3, 16),
                    Block.createCuboidShape(0, 0, 0, 3, 3, 3),
                    Block.createCuboidShape(3, 2, 4, 13, 6, 5),
                    Block.createCuboidShape(2, 0, 1, 14, 2, 14),
                    Block.createCuboidShape(3, 0, 15, 13, 2, 16),
                    Block.createCuboidShape(3, 2, 5, 13, 14, 14),
                    Block.createCuboidShape(3, 0, 14, 13, 7, 15),
                    Block.createCuboidShape(4, 13, 7, 12, 15, 13)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(0, 0, 13, 3, 3, 16),
            Block.createCuboidShape(0, 0, 0, 3, 3, 3),
            Block.createCuboidShape(13, 0, 0, 16, 3, 3),
            Block.createCuboidShape(13, 0, 13, 16, 3, 16),
            Block.createCuboidShape(3, 2, 11, 13, 6, 12),
            Block.createCuboidShape(2, 0, 2, 14, 2, 15),
            Block.createCuboidShape(3, 0, 0, 13, 2, 1),
            Block.createCuboidShape(3, 2, 2, 13, 14, 11),
            Block.createCuboidShape(3, 0, 1, 13, 7, 2),
            Block.createCuboidShape(4, 13, 3, 12, 15, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(0, 0, 0, 3, 3, 3),
            Block.createCuboidShape(13, 0, 0, 16, 3, 3),
            Block.createCuboidShape(13, 0, 13, 16, 3, 16),
            Block.createCuboidShape(0, 0, 13, 3, 3, 16),
            Block.createCuboidShape(4, 2, 3, 5, 6, 13),
            Block.createCuboidShape(1, 0, 2, 14, 2, 14),
            Block.createCuboidShape(15, 0, 3, 16, 2, 13),
            Block.createCuboidShape(5, 2, 3, 14, 14, 13),
            Block.createCuboidShape(14, 0, 3, 15, 7, 13),
            Block.createCuboidShape(7, 13, 4, 13, 15, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(13, 0, 13, 16, 3, 16),
            Block.createCuboidShape(0, 0, 13, 3, 3, 16),
            Block.createCuboidShape(0, 0, 0, 3, 3, 3),
            Block.createCuboidShape(13, 0, 0, 16, 3, 3),
            Block.createCuboidShape(11, 2, 3, 12, 6, 13),
            Block.createCuboidShape(2, 0, 2, 15, 2, 14),
            Block.createCuboidShape(0, 0, 3, 1, 2, 13),
            Block.createCuboidShape(2, 2, 3, 11, 14, 13),
            Block.createCuboidShape(1, 0, 3, 2, 7, 13),
            Block.createCuboidShape(3, 13, 4, 9, 15, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    //Con esta funcion se relaciona el Shape del bloque con la dirección en la que apunta
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)){
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    //Esta propiedad de BlockState, basicamente hace que el bloque que colocamos este mirando al jugador, obteniendo el context.getPlayerFacing
    //que obtiene la dirección hacia donde esta mirando el jugador y obtiene el opposite que es el contrario
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    //Se añade la propiedad FACING
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
