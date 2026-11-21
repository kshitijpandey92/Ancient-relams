package com.ancientrealms.items;

import com.ancientrealms.blocks.ModBlocks;
import com.ancientrealms.blocks.DragonEggNestBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public final class DragonEggItem extends Item {
    public DragonEggItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos target = context.getClickedPos();
        if (!level.getBlockState(target).canBeReplaced()) {
            target = target.relative(context.getClickedFace());
        }
        if (!level.getBlockState(target).canBeReplaced()) {
            return InteractionResult.FAIL;
        }
        if (!level.isClientSide()) {
            BlockState nest = ModBlocks.DRAGON_EGG_NEST.defaultBlockState()
                .setValue(DragonEggNestBlock.AGE, 0);
            if (!level.setBlock(target, nest, 3)) {
                return InteractionResult.FAIL;
            }
            if (context.getPlayer() == null || !context.getPlayer().getAbilities().instabuild) {
                context.getItemInHand().shrink(1);
            }
            level.gameEvent(net.minecraft.world.level.gameevent.GameEvent.BLOCK_PLACE, target,
                net.minecraft.world.level.gameevent.GameEvent.Context.of(context.getPlayer(), nest));
        }
        return InteractionResult.SUCCESS;
    }
}
