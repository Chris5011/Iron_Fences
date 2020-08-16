package dev.chris5011.ironfences.modules.ironfencegate;

import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class IronFenceGate_Block extends FenceGateBlock {
    public IronFenceGate_Block(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return ActionResultType.CONSUME;
    }
}
