package com.ancientrealms.blocks;

import com.ancientrealms.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * Server-authoritative progression workstation. Right-click with a listed
 * catalyst to consume it and receive the station's crafted result.
 */
public final class StationBlock extends Block {
    public enum Kind {
        DRAGON_FORGE, RUNE_ANVIL, SOUL_ALTAR, ELEMENTAL_CRUCIBLE,
        CELESTIAL_LOOM, BLOOD_INFUSER, ETERNAL_FOUNDRY, WORLD_CORE_MATRIX
    }

    private record Recipe(Item input, int inputCount, Item output, int outputCount, String name) {}

    private final Kind kind;

    public StationBlock(Properties properties, Kind kind) {
        super(properties);
        this.kind = kind;
    }

    private Recipe recipe() {
        return switch (kind) {
            case DRAGON_FORGE -> new Recipe(ModItems.DRAGON_HEART, 1, ModItems.DRAGONSTEEL_INGOT, 1, "Dragonsteel Forging");
            case RUNE_ANVIL -> new Recipe(ModItems.MYTHRIL_INGOT, 2, ModItems.MYTHRIL_SWORD, 1, "Runic Weapon Forging");
            case SOUL_ALTAR -> new Recipe(ModItems.SOULSTONE, 2, ModItems.ANCIENT_ESSENCE, 1, "Soul Transmutation");
            case ELEMENTAL_CRUCIBLE -> new Recipe(ModItems.FROST_CORE, 1, ModItems.THUNDER_CORE, 1, "Elemental Core Reforging");
            case CELESTIAL_LOOM -> new Recipe(ModItems.ANCIENT_DRAGON_SCALE, 2, ModItems.DRAGON_ARMOR, 1, "Dragon Armor Weaving");
            case BLOOD_INFUSER -> new Recipe(ModItems.BLOOD_CRYSTAL, 2, ModItems.DRAGON_HEART, 1, "Blood Crystallization");
            case ETERNAL_FOUNDRY -> new Recipe(ModItems.DIVINE_ESSENCE, 2, ModItems.ETERNAL_FRAGMENT, 4, "Eternal Fragment Casting");
            case WORLD_CORE_MATRIX -> new Recipe(ModItems.ETERNAL_FRAGMENT, 4, ModItems.WORLD_CORE, 1, "World Core Synthesis");
        };
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level,
            BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        Recipe recipe = recipe();
        if (!stack.is(recipe.input())) return InteractionResult.PASS;
        if (stack.getCount() < recipe.inputCount()) {
            if (level.isClientSide()) return InteractionResult.SUCCESS;
            player.sendSystemMessage(Component.literal(kindName() + " requires "
                    + recipe.inputCount() + " " + recipe.input().getName().getString() + "."));
            return InteractionResult.SUCCESS;
        }
        if (!level.isClientSide()) {
            if (!player.getAbilities().instabuild) stack.shrink(recipe.inputCount());
            ItemStack result = new ItemStack(recipe.output(), recipe.outputCount());
            player.addItem(result);
            level.levelEvent(1042, pos, 0);
            player.sendSystemMessage(Component.literal(recipe.name() + " complete."));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level,
            BlockPos pos, Player player, BlockHitResult hit) {
        Recipe recipe = recipe();
        player.sendSystemMessage(Component.literal(kindName() + ": place "
                + recipe.inputCount() + " " + recipe.input().getName().getString()
                + " in your hand and use it on this station to craft "
                + recipe.output().getName().getString() + "."));
        return InteractionResult.SUCCESS;
    }

    private String kindName() {
        return switch (kind) {
            case DRAGON_FORGE -> "Dragon Forge";
            case RUNE_ANVIL -> "Rune Anvil";
            case SOUL_ALTAR -> "Soul Altar";
            case ELEMENTAL_CRUCIBLE -> "Elemental Crucible";
            case CELESTIAL_LOOM -> "Celestial Loom";
            case BLOOD_INFUSER -> "Blood Infuser";
            case ETERNAL_FOUNDRY -> "Eternal Foundry";
            case WORLD_CORE_MATRIX -> "World Core Matrix";
        };
    }
}
