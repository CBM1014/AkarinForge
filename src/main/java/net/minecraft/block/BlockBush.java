package net.minecraft.block;

import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBush extends Block implements net.minecraftforge.common.IPlantable { // Akarin Forge

    protected static final AxisAlignedBB field_185515_b = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);

    protected BlockBush() {
        this(Material.field_151585_k);
    }

    protected BlockBush(Material material) {
        this(material, material.func_151565_r());
    }

    protected BlockBush(Material material, MapColor materialmapcolor) {
        super(material, materialmapcolor);
        this.func_149675_a(true);
        this.func_149647_a(CreativeTabs.field_78031_c);
    }

    // Akarin Forge - start
    public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
        // return super.func_176196_c(world, blockposition) && this.func_185514_i(world.func_180495_p(blockposition.func_177977_b()));
        IBlockState soil = p_176196_1_.func_180495_p(p_176196_2_.func_177977_b());
        return super.func_176196_c(p_176196_1_, p_176196_2_) && soil.func_177230_c().canSustainPlant(soil, p_176196_1_, p_176196_2_.func_177977_b(), EnumFacing.UP, this);
        // Akarin Forge - end
    }

    protected boolean func_185514_i(IBlockState iblockdata) {
        return iblockdata.func_177230_c() == Blocks.field_150349_c || iblockdata.func_177230_c() == Blocks.field_150346_d || iblockdata.func_177230_c() == Blocks.field_150458_ak;
    }

    public void func_189540_a(IBlockState iblockdata, World world, BlockPos blockposition, Block block, BlockPos blockposition1) {
        super.func_189540_a(iblockdata, world, blockposition, block, blockposition1);
        this.func_176475_e(world, blockposition, iblockdata);
    }

    public void func_180650_b(World world, BlockPos blockposition, IBlockState iblockdata, Random random) {
        this.func_176475_e(world, blockposition, iblockdata);
    }

    protected void func_176475_e(World world, BlockPos blockposition, IBlockState iblockdata) {
        if (!this.func_180671_f(world, blockposition, iblockdata)) {
            // CraftBukkit start
            if (org.bukkit.craftbukkit.event.CraftEventFactory.callBlockPhysicsEvent(world, blockposition).isCancelled()) {
                return;
            }
            // CraftBukkit end
            this.func_176226_b(world, blockposition, iblockdata, 0);
            world.func_180501_a(blockposition, Blocks.field_150350_a.func_176223_P(), 3);
        }

    }

    public boolean func_180671_f(World world, BlockPos blockposition, IBlockState iblockdata) {
        // Akarin Forge - start
        if (iblockdata.func_177230_c() == this) { // Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            IBlockState soil = world.func_180495_p(blockposition.func_177977_b());
            return soil.func_177230_c().canSustainPlant(soil, world, blockposition.func_177977_b(), net.minecraft.util.EnumFacing.UP, this);
        }
        // Akarin Forge - end
        return this.func_185514_i(world.func_180495_p(blockposition.func_177977_b()));
    }

    public AxisAlignedBB func_185496_a(IBlockState iblockdata, IBlockAccess iblockaccess, BlockPos blockposition) {
        return BlockBush.field_185515_b;
    }

    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState iblockdata, IBlockAccess iblockaccess, BlockPos blockposition) {
        return BlockBush.field_185506_k;
    }

    public boolean func_149662_c(IBlockState iblockdata) {
        return false;
    }

    public boolean func_149686_d(IBlockState iblockdata) {
        return false;
    }

    public BlockFaceShape func_193383_a(IBlockAccess iblockaccess, IBlockState iblockdata, BlockPos blockposition, EnumFacing enumdirection) {
        return BlockFaceShape.UNDEFINED;
    }
    
    // Akarin Forge - start
    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        if (this == Blocks.field_150464_aj)          return net.minecraftforge.common.EnumPlantType.Crop;
        if (this == Blocks.field_150459_bM)        return net.minecraftforge.common.EnumPlantType.Crop;
        if (this == Blocks.field_150469_bN)       return net.minecraftforge.common.EnumPlantType.Crop;
        if (this == Blocks.field_185773_cZ)      return net.minecraftforge.common.EnumPlantType.Crop;
        if (this == Blocks.field_150394_bc)     return net.minecraftforge.common.EnumPlantType.Crop;
        if (this == Blocks.field_150393_bb)   return net.minecraftforge.common.EnumPlantType.Crop;
        if (this == Blocks.field_150330_I)       return net.minecraftforge.common.EnumPlantType.Desert;
        if (this == Blocks.field_150392_bi)      return net.minecraftforge.common.EnumPlantType.Water;
        if (this == Blocks.field_150337_Q)   return net.minecraftforge.common.EnumPlantType.Cave;
        if (this == Blocks.field_150338_P) return net.minecraftforge.common.EnumPlantType.Cave;
        if (this == Blocks.field_150388_bm)    return net.minecraftforge.common.EnumPlantType.Nether;
        if (this == Blocks.field_150345_g)        return net.minecraftforge.common.EnumPlantType.Plains;
        if (this == Blocks.field_150329_H)      return net.minecraftforge.common.EnumPlantType.Plains;
        if (this == Blocks.field_150398_cm)   return net.minecraftforge.common.EnumPlantType.Plains;
        if (this == Blocks.field_150328_O)     return net.minecraftforge.common.EnumPlantType.Plains;
        if (this == Blocks.field_150327_N)  return net.minecraftforge.common.EnumPlantType.Plains;
        return net.minecraftforge.common.EnumPlantType.Plains;
    }

    @Override
    public IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.func_180495_p(pos);
        if (state.func_177230_c() != this) return func_176223_P();
        return state;
    }
    // Akarin Forge - end
}
