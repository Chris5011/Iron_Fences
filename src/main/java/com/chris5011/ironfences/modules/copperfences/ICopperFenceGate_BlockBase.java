package com.chris5011.ironfences.modules.copperfences;

import com.chris5011.ironfences.setup.Registry;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;
import java.util.function.Supplier;

public interface ICopperFenceGate_BlockBase extends ChangeOverTimeBlock<ICopperFenceGate_BlockBase.CopperFenceGateWeatherState> {

    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(Registry.COPPER_FENCE_GATE_BLOCK.get(), Registry.EXPOSED_COPPER_FENCE_GATE_BLOCK.get())
            .put(Registry.EXPOSED_COPPER_FENCE_GATE_BLOCK.get(), Registry.WEATHERED_COPPER_FENCE_GATE_BLOCK.get())
            .put(Registry.WEATHERED_COPPER_FENCE_GATE_BLOCK.get(), Registry.OXIDIZED_COPPER_FENCE_GATE_BLOCK.get())
            .put(Registry.COPPER_FENCE_BLOCK.get(), Registry.EXPOSED_COPPER_FENCE_BLOCK.get())
            .put(Registry.EXPOSED_COPPER_FENCE_BLOCK.get(), Registry.WEATHERED_COPPER_FENCE_BLOCK.get())
            .put(Registry.WEATHERED_COPPER_FENCE_BLOCK.get(), Registry.OXIDIZED_COPPER_FENCE_BLOCK.get())
            .put(Registry.COPPER_WALL_BLOCK.get(), Registry.EXPOSED_COPPER_WALL_BLOCK.get())
            .put(Registry.EXPOSED_COPPER_WALL_BLOCK.get(), Registry.WEATHERED_COPPER_WALL_BLOCK.get())
            .put(Registry.WEATHERED_COPPER_WALL_BLOCK.get(), Registry.OXIDIZED_COPPER_WALL_BLOCK.get())
            .build());
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

    static Optional<Block> getPrevious(Block block) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(block));
    }

    static Block getFirst(Block initialBlock) {
        Block block = initialBlock;

        for (Block block1 = PREVIOUS_BY_BLOCK.get().get(initialBlock); block1 != null; block1 = PREVIOUS_BY_BLOCK.get().get(block1)) {
            block = block1;
        }

        return block;
    }

    static Optional<BlockState> getPrevious(BlockState blockState) {
        return getPrevious(blockState.getBlock()).map((block) -> block.withPropertiesOf(blockState));
    }

    static Optional<Block> getNext(Block block) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
    }

    static BlockState getFirst(BlockState blockState) {
        return getFirst(blockState.getBlock()).withPropertiesOf(blockState);
    }

    default Optional<BlockState> getNext(BlockState blockState) {
        return getNext(blockState.getBlock()).map((p_154896_) -> p_154896_.withPropertiesOf(blockState));
    }

    default float getChanceModifier() {
        return this.getAge() == ICopperFenceGate_BlockBase.CopperFenceGateWeatherState.UNAFFECTED ? 0.75F : 1.0F;
    }


    public static enum CopperFenceGateWeatherState {
        UNAFFECTED,
        EXPOSED,
        WEATHERED,
        OXIDIZED;
    }
}
