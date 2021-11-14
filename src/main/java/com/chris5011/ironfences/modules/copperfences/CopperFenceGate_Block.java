package com.chris5011.ironfences.modules.copperfences;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class CopperFenceGate_Block  extends FenceGateBlock {
    public CopperFenceGate_Block(BlockBehaviour.Properties properties) {
        super(properties);
    }

    //Suppress redstone activation
    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {

    }
}
