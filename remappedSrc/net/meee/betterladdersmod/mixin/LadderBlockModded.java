package net.meee.betterladdersmod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LadderBlock.class)
public class LadderBlockModded {
    @Inject(method = "canPlaceAt", at = @At("HEAD"),cancellable = true)
    public void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
        callback.setReturnValue(true);
        callback.cancel();
    }

    @Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"),cancellable = true)
    public void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> info) {
        if (!state.canPlaceAt(world, pos)){
            info.setReturnValue(Blocks.AIR.getDefaultState());
            info.cancel();
        }
    }
}
