package net.minecraft.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSourceImpl implements IBlockSource {

    private final World world;
    private final BlockPos pos;

    public BlockSourceImpl(World world, BlockPos blockposition) {
        this.world = world;
        this.pos = blockposition;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public double getX() {
        return this.pos.getX() + 0.5D;
    }

    @Override
    public double getY() {
        return this.pos.getY() + 0.5D;
    }

    @Override
    public double getZ() {
        return this.pos.getZ() + 0.5D;
    }

    @Override
    public BlockPos getBlockPos() {
        return this.pos;
    }

    @Override
    public IBlockState getBlockState() {
        return this.world.getBlockState(this.pos);
    }

    @Override
    public <T extends TileEntity> T getBlockTileEntity() {
        return (T) this.world.getTileEntity(this.pos); // Akarin - fix compile error
    }
}