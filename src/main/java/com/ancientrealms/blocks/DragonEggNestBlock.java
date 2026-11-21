package com.ancientrealms.blocks;

import com.ancientrealms.entities.DragonTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class DragonEggNestBlock extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final IntegerProperty SPECIES = IntegerProperty.create("species", 0, 2);

    public DragonEggNestBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(AGE, 0).setValue(SPECIES, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, SPECIES);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);
        if (age < 3) {
            level.setBlock(pos, state.setValue(AGE, age + 1), 3);
            return;
        }
        var type = switch (state.getValue(SPECIES)) {
            case 1 -> DragonTypes.FROST_DRAGON;
            case 2 -> DragonTypes.STORM_DRAGON;
            default -> DragonTypes.EMBER_DRAGON;
        };
        var dragon = type.create(level, EntitySpawnReason.TRIGGERED);
        if (dragon != null) {
            dragon.setPos(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            if (level.addFreshEntity(dragon)) {
                level.removeBlock(pos, false);
            }
        }
    }
}
