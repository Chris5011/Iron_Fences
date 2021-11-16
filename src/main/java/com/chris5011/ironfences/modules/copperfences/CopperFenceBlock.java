package com.chris5011.ironfences.modules.copperfences;

import com.chris5011.ironfences.setup.Registry;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class CopperFenceBlock extends FenceBlock implements ICopperFenceGate_BlockBase{
    private final ICopperFenceGate_BlockBase.CopperFenceGateWeatherState weatherState;

    public static final Supplier<BiMap<Block, Block>> WAXABLES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(Registry.COPPER_FENCE_BLOCK.get(), Registry.WAXED_COPPER_FENCE_BLOCK.get())
            .put(Registry.EXPOSED_COPPER_FENCE_BLOCK.get(), Registry.WAXED_EXPOSED_COPPER_FENCE_BLOCK.get())
            .put(Registry.WEATHERED_COPPER_FENCE_BLOCK.get(), Registry.WAXED_WEATHERED_COPPER_FENCE_BLOCK.get())
            .put(Registry.OXIDIZED_COPPER_FENCE_BLOCK.get(), Registry.WAXED_OXIDIZED_COPPER_FENCE_BLOCK.get())
            .build());

    public static final Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK = Suppliers.memoize(() -> WAXABLES.get().inverse());



    public CopperFenceBlock(ICopperFenceGate_BlockBase.CopperFenceGateWeatherState weatherState, BlockBehaviour.Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }


    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        if (!world.isClientSide() && player instanceof ServerPlayer) {
            Item handheldItem = player.getItemInHand(hand).getItem();
            if (Items.HONEYCOMB.equals(handheldItem)) {
                if (!isWaxed(blockState)) {

                    return wax(blockState).map((newBlockState) -> {
                        ItemStack itemstack = player.getItemInHand(hand);
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockPos, itemstack);

                        if (!player.isCreative()) {
                            player.getItemInHand(hand).shrink(1);
                        }

                        world.setBlock(blockPos, newBlockState, 11);
                        world.playSound(null, blockPos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
                        world.levelEvent(player, 3003, blockPos, 0);
                        return InteractionResult.SUCCESS;
                    }).orElse(InteractionResult.CONSUME);
                } else {
                    return InteractionResult.PASS;
                }
            } else if (handheldItem instanceof AxeItem) {
                //Block is waxed --> Remove wax
                if (isWaxed(blockState)) {
                    Optional<Block> unwaxedBlock = getUnwaxedBlock(blockState);
                    if (unwaxedBlock.isPresent()) {
                        world.setBlockAndUpdate(blockPos, unwaxedBlock.get().withPropertiesOf(blockState));
                        world.playSound(null, blockPos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                        world.levelEvent(player, 3005, blockPos, 0);
                        if (!player.isCreative()) {
                            player.getItemInHand(hand).hurtAndBreak(1, player, (event) -> {
                                event.broadcastBreakEvent(hand);
                            });
                        }
                    }
                    return InteractionResult.SUCCESS;
                    //Block is not waxed --> Clean it
                } else {
                    ICopperFenceGate_BlockBase.getPrevious(blockState).ifPresent((optBlockState) -> {
                        world.playSound(null, blockPos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                        world.setBlockAndUpdate(blockPos, optBlockState);
                    });
                    world.levelEvent(player, 3005, blockPos, 0);
                    return InteractionResult.SUCCESS;
                }
            } else {
                return super.use(blockState, world, blockPos, player, hand, blockHitResult);
            }
        }
        return InteractionResult.PASS;
    }

    public static Optional<BlockState> wax(BlockState blockState) {
        return Optional.ofNullable(WAXABLES.get().get(blockState.getBlock())).map((newBlockState) -> newBlockState.withPropertiesOf(blockState));
    }

    public static boolean isWaxed(BlockState blockState) {
        return WAXABLES.get().containsValue(blockState.getBlock());
    }

    public static Optional<Block> getUnwaxedBlock(BlockState blockState) {
        for (Map.Entry<Block, Block> mapEntry : WAXABLES.get().entrySet()) {
            if (mapEntry.getValue().equals(blockState.getBlock())) {
                return Optional.of(mapEntry.getKey());
            }
        }
        return Optional.empty();
    }

    public void randomTick(BlockState state, ServerLevel serverWorld, BlockPos pos, Random rd) {
        if (!isWaxed(state)) {
            this.onRandomTick(state, serverWorld, pos, rd);
        }
    }

    public boolean isRandomlyTicking(BlockState blockState) {
        return ICopperFenceGate_BlockBase.getNext(blockState.getBlock()).isPresent();
    }

    @Override
    public CopperFenceGateWeatherState getAge() {
        return this.weatherState;
    }
}
