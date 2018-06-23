package net.minecraft.tileentity;

import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import co.aikar.timings.MinecraftTimings;
import co.aikar.timings.Timing;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.bukkit.inventory.InventoryHolder;

public abstract class TileEntity implements net.minecraftforge.common.capabilities.ICapabilitySerializable<NBTTagCompound> {

    public Timing tickTimer = MinecraftTimings.getTileEntityTimings(this); // Paper
    public boolean isLoadingStructure = false; // Paper
    private static final Logger field_145852_a = LogManager.getLogger();
    private static final RegistryNamespaced<ResourceLocation, Class<? extends TileEntity>> field_190562_f = new RegistryNamespaced();
    public World field_145850_b;
    public BlockPos field_174879_c;
    protected boolean field_145846_f;
    private int field_145847_g;
    protected Block field_145854_h;

    public TileEntity() {
        this.field_174879_c = BlockPos.field_177992_a;
        this.field_145847_g = -1;
        capabilities = net.minecraftforge.event.ForgeEventFactory.gatherCapabilities(this);
    }

    private static void func_190560_a(String s, Class<? extends TileEntity> oclass) {
        TileEntity.field_190562_f.func_82595_a(new ResourceLocation(s), oclass);
    }

    @Nullable
    public static ResourceLocation func_190559_a(Class<? extends TileEntity> oclass) {
        return TileEntity.field_190562_f.func_177774_c(oclass);
    }

    static boolean IGNORE_TILE_UPDATES = false; // Paper
    public World func_145831_w() {
        return this.field_145850_b;
    }

    public void func_145834_a(World world) {
        this.field_145850_b = world;
    }

    public boolean func_145830_o() {
        return this.field_145850_b != null;
    }

    public void func_145839_a(NBTTagCompound p_145839_1_) {
        this.field_174879_c = new BlockPos(p_145839_1_.func_74762_e("x"), p_145839_1_.func_74762_e("y"), p_145839_1_.func_74762_e("z"));
        if (p_145839_1_.func_74764_b("ForgeData")) this.customTileData = p_145839_1_.func_74775_l("ForgeData");
        if (this.capabilities != null && p_145839_1_.func_74764_b("ForgeCaps")) this.capabilities.deserializeNBT(p_145839_1_.func_74775_l("ForgeCaps"));
    }

    public NBTTagCompound func_189515_b(NBTTagCompound nbttagcompound) {
        return this.func_189516_d(nbttagcompound);
    }

    private NBTTagCompound func_189516_d(NBTTagCompound nbttagcompound) {
        ResourceLocation minecraftkey = TileEntity.field_190562_f.func_177774_c(this.getClass());

        if (minecraftkey == null) {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        } else {
            nbttagcompound.func_74778_a("id", minecraftkey.toString());
            nbttagcompound.func_74768_a("x", this.field_174879_c.func_177958_n());
            nbttagcompound.func_74768_a("y", this.field_174879_c.func_177956_o());
            nbttagcompound.func_74768_a("z", this.field_174879_c.func_177952_p());
            if (this.customTileData != null) nbttagcompound.func_74782_a("ForgeData", this.customTileData);
            if (this.capabilities != null) nbttagcompound.func_74782_a("ForgeCaps", this.capabilities.serializeNBT());
            return nbttagcompound;
        }
    }

    @Nullable
    public static TileEntity func_190200_a(World world, NBTTagCompound nbttagcompound) {
        TileEntity tileentity = null;
        String s = nbttagcompound.func_74779_i("id");
        Class <? extends TileEntity > oclass = null;

        try {
            oclass = TileEntity.field_190562_f.func_82594_a(new ResourceLocation(s));

            if (oclass != null) {
                tileentity = (TileEntity) oclass.newInstance();
            }
        } catch (Throwable throwable) {
            TileEntity.field_145852_a.error("Failed to create block entity {}", s, throwable);
            net.minecraftforge.fml.common.FMLLog.log.error("A TileEntity {}({}) has thrown an exception during loading, its state cannot be restored. Report this to the mod author!",
                    s, oclass == null ? null : oclass.getName(), throwable);
        }

        if (tileentity != null) {
            try {
                tileentity.func_190201_b(world);
                tileentity.func_145839_a(nbttagcompound);
            } catch (Throwable throwable1) {
                TileEntity.field_145852_a.error("Failed to load data for block entity {}", s, throwable1);
                net.minecraftforge.fml.common.FMLLog.log.error("A TileEntity {}({}) has thrown an exception during loading, its state cannot be restored. Report this to the mod author!",
                        s, oclass.getName(), throwable1);
                tileentity = null;
            }
        } else {
            TileEntity.field_145852_a.warn("Skipping BlockEntity with id {}", s);
        }

        return tileentity;
    }
    
    public double func_145835_a(double p_145835_1_, double p_145835_3_, double p_145835_5_)
    {
        double d0 = (double)this.field_174879_c.func_177958_n() + 0.5D - p_145835_1_;
        double d1 = (double)this.field_174879_c.func_177956_o() + 0.5D - p_145835_3_;
        double d2 = (double)this.field_174879_c.func_177952_p() + 0.5D - p_145835_5_;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }
    
 // -- BEGIN FORGE PATCHES --
    /**
     * Called when you receive a TileEntityData packet for the location this
     * TileEntity is currently in. On the client, the NetworkManager will always
     * be the remote server. On the server, it will be whomever is responsible for
     * sending the packet.
     *
     * @param net The NetworkManager the packet originated from
     * @param pkt The data packet
     */
    public void onDataPacket(net.minecraft.network.NetworkManager net, net.minecraft.network.play.server.SPacketUpdateTileEntity pkt)
    {
    }

    /**
     * Called when the chunk's TE update tag, gotten from {@link #getUpdateTag()}, is received on the client.
     * <p>
     * Used to handle this tag in a special way. By default this simply calls {@link #readFromNBT(NBTTagCompound)}.
     *
     * @param tag The {@link NBTTagCompound} sent from {@link #getUpdateTag()}
     */
    public void handleUpdateTag(NBTTagCompound tag)
    {
        this.func_145839_a(tag);
    }

    /**
     * Called when the chunk this TileEntity is on is Unloaded.
     */
    public void onChunkUnload()
    {
    }

    private boolean isVanilla = getClass().getName().startsWith("net.minecraft.");
    /**
     * Called from Chunk.setBlockIDWithMetadata and Chunk.fillChunk, determines if this tile entity should be re-created when the ID, or Metadata changes.
     * Use with caution as this will leave straggler TileEntities, or create conflicts with other TileEntities if not used properly.
     *
     * @param world Current world
     * @param pos Tile's world position
     * @param oldState The old ID of the block
     * @param newState The new ID of the block (May be the same)
     * @return true forcing the invalidation of the existing TE, false not to invalidate the existing TE
     */
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
    {
        return isVanilla ? (oldState.func_177230_c() != newSate.func_177230_c()) : oldState != newSate;
    }

    public boolean shouldRenderInPass(int pass)
    {
        return pass == 0;
    }

    /**
     * Sometimes default render bounding box: infinite in scope. Used to control rendering on {@link TileEntitySpecialRenderer}.
     */
    public static final net.minecraft.util.math.AxisAlignedBB INFINITE_EXTENT_AABB = new net.minecraft.util.math.AxisAlignedBB(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

    /**
     * Checks if this tile entity knows how to render its 'breaking' overlay effect.
     * If this returns true, The TileEntitySpecialRenderer will be called again with break progress set.
     * @return True to re-render tile with breaking effect.
     */
    public boolean canRenderBreaking()
    {
        Block block = this.func_145838_q();
        return (block instanceof net.minecraft.block.BlockChest ||
                block instanceof net.minecraft.block.BlockEnderChest ||
                block instanceof net.minecraft.block.BlockSign ||
                block instanceof net.minecraft.block.BlockSkull);
    }

    private NBTTagCompound customTileData;

    /**
     * Gets a {@link NBTTagCompound} that can be used to store custom data for this tile entity.
     * It will be written, and read from disc, so it persists over world saves.
     *
     * @return A compound tag for custom data
     */
    public NBTTagCompound getTileData()
    {
        if (this.customTileData == null)
        {
            this.customTileData = new NBTTagCompound();
        }
        return this.customTileData;
    }

    /**
     * Determines if the player can overwrite the NBT data of this tile entity while they place it using a ItemStack.
     * Added as a fix for MC-75630 - Exploit with signs and command blocks
     * @return True to prevent NBT copy, false to allow.
     */
    public boolean restrictNBTCopy()
    {
        return this instanceof TileEntityCommandBlock ||
               this instanceof TileEntityMobSpawner ||
               this instanceof TileEntitySign;
    }


    /**
     * Called when this is first added to the world (by {@link World#addTileEntity(TileEntity)}).
     * Override instead of adding {@code if (firstTick)} stuff in update.
     */
    public void onLoad()
    {
        // NOOP
    }

    /**
     * If the TileEntitySpecialRenderer associated with this TileEntity can be batched in with another renderers, and won't access the GL state.
     * If TileEntity returns true, then TESR should have the same functionality as (and probably extend) the FastTESR class.
     */
    public boolean hasFastRenderer()
    {
        return false;
    }

    private net.minecraftforge.common.capabilities.CapabilityDispatcher capabilities;

    @Override
    public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, @Nullable net.minecraft.util.EnumFacing facing)
    {
        return capabilities == null ? false : capabilities.hasCapability(capability, facing);
    }

    @Override
    @Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable net.minecraft.util.EnumFacing facing)
    {
        return capabilities == null ? null : capabilities.getCapability(capability, facing);
    }

    public void deserializeNBT(NBTTagCompound nbt)
    {
        this.func_145839_a(nbt);
    }

    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound ret = new NBTTagCompound();
        this.func_189515_b(ret);
        return ret;
    }

    protected void func_190201_b(World world) {}

    public int func_145832_p() {
        if (this.field_145847_g == -1) {
            IBlockState iblockdata = this.field_145850_b.func_180495_p(this.field_174879_c);

            this.field_145847_g = iblockdata.func_177230_c().func_176201_c(iblockdata);
        }

        return this.field_145847_g;
    }

    public void func_70296_d() {
        if (this.field_145850_b != null) {
            if (IGNORE_TILE_UPDATES) return; // Paper
            IBlockState iblockdata = this.field_145850_b.func_180495_p(this.field_174879_c);

            this.field_145847_g = iblockdata.func_177230_c().func_176201_c(iblockdata);
            this.field_145850_b.func_175646_b(this.field_174879_c, this);
            if (this.func_145838_q() != Blocks.field_150350_a) {
                this.field_145850_b.func_175666_e(this.field_174879_c, this.func_145838_q());
            }
        }

    }

    public BlockPos func_174877_v() {
        return this.field_174879_c;
    }

    public Block func_145838_q() {
        if (this.field_145854_h == null && this.field_145850_b != null) {
            this.field_145854_h = this.field_145850_b.func_180495_p(this.field_174879_c).func_177230_c();
        }

        return this.field_145854_h;
    }

    @Nullable
    public SPacketUpdateTileEntity func_189518_D_() {
        return null;
    }

    public NBTTagCompound func_189517_E_() {
        return this.func_189516_d(new NBTTagCompound());
    }

    public boolean func_145837_r() {
        return this.field_145846_f;
    }

    public void func_145843_s() {
        this.field_145846_f = true;
    }

    public void func_145829_t() {
        this.field_145846_f = false;
    }

    public boolean func_145842_c(int i, int j) {
        return false;
    }

    public void func_145836_u() {
        this.field_145854_h = null;
        this.field_145847_g = -1;
    }

    public void func_145828_a(CrashReportCategory crashreportsystemdetails) {
        crashreportsystemdetails.func_189529_a("Name", new ICrashReportDetail() {
            public String a() throws Exception {
                return TileEntity.field_190562_f.func_177774_c(TileEntity.this.getClass()) + " // " + TileEntity.this.getClass().getCanonicalName();
            }

            @Override
            public Object call() throws Exception {
                return this.a();
            }
        });
        if (this.field_145850_b != null) {
            // Paper start - Prevent TileEntity and Entity crashes
            Block block = this.func_145838_q();
            if (block != null) {
                CrashReportCategory.func_180523_a(crashreportsystemdetails, this.field_174879_c, this.func_145838_q(), this.func_145832_p());
            }
            // Paper end
            crashreportsystemdetails.func_189529_a("Actual block type", new ICrashReportDetail() {
                public String a() throws Exception {
                    int i = Block.func_149682_b(TileEntity.this.field_145850_b.func_180495_p(TileEntity.this.field_174879_c).func_177230_c());

                    try {
                        return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(i), Block.func_149729_e(i).func_149739_a(), Block.func_149729_e(i).getClass().getCanonicalName()});
                    } catch (Throwable throwable) {
                        return "ID #" + i;
                    }
                }

                @Override
                public Object call() throws Exception {
                    return this.a();
                }
            });
            crashreportsystemdetails.func_189529_a("Actual block data value", new ICrashReportDetail() {
                public String a() throws Exception {
                    IBlockState iblockdata = TileEntity.this.field_145850_b.func_180495_p(TileEntity.this.field_174879_c);
                    int i = iblockdata.func_177230_c().func_176201_c(iblockdata);

                    if (i < 0) {
                        return "Unknown? (Got " + i + ")";
                    } else {
                        String s = String.format("%4s", new Object[] { Integer.toBinaryString(i)}).replace(" ", "0");

                        return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[] { Integer.valueOf(i), s});
                    }
                }

                @Override
                public Object call() throws Exception {
                    return this.a();
                }
            });
        }
    }

    public void func_174878_a(BlockPos blockposition) {
        this.field_174879_c = blockposition.func_185334_h();
    }

    public boolean func_183000_F() {
        return false;
    }

    @Nullable
    public ITextComponent func_145748_c_() {
        return null;
    }

    public void func_189667_a(Rotation enumblockrotation) {}

    public void func_189668_a(Mirror enumblockmirror) {}

    static {
        func_190560_a("furnace", TileEntityFurnace.class);
        func_190560_a("chest", TileEntityChest.class);
        func_190560_a("ender_chest", TileEntityEnderChest.class);
        func_190560_a("jukebox", BlockJukebox.TileEntityJukebox.class);
        func_190560_a("dispenser", TileEntityDispenser.class);
        func_190560_a("dropper", TileEntityDropper.class);
        func_190560_a("sign", TileEntitySign.class);
        func_190560_a("mob_spawner", TileEntityMobSpawner.class);
        func_190560_a("noteblock", TileEntityNote.class);
        func_190560_a("piston", TileEntityPiston.class);
        func_190560_a("brewing_stand", TileEntityBrewingStand.class);
        func_190560_a("enchanting_table", TileEntityEnchantmentTable.class);
        func_190560_a("end_portal", TileEntityEndPortal.class);
        func_190560_a("beacon", TileEntityBeacon.class);
        func_190560_a("skull", TileEntitySkull.class);
        func_190560_a("daylight_detector", TileEntityDaylightDetector.class);
        func_190560_a("hopper", TileEntityHopper.class);
        func_190560_a("comparator", TileEntityComparator.class);
        func_190560_a("flower_pot", TileEntityFlowerPot.class);
        func_190560_a("banner", TileEntityBanner.class);
        func_190560_a("structure_block", TileEntityStructure.class);
        func_190560_a("end_gateway", TileEntityEndGateway.class);
        func_190560_a("command_block", TileEntityCommandBlock.class);
        func_190560_a("shulker_box", TileEntityShulkerBox.class);
        func_190560_a("bed", TileEntityBed.class);
    }

    // CraftBukkit start - add method
    // Paper start
    public InventoryHolder getOwner() {
        return getOwner(true);
    }
    public InventoryHolder getOwner(boolean useSnapshot) {
        // Paper end
        if (field_145850_b == null) return null;
        // Spigot start
        org.bukkit.block.Block block = field_145850_b.getWorld().getBlockAt(field_174879_c.func_177958_n(), field_174879_c.func_177956_o(), field_174879_c.func_177952_p());
        if (block == null) {
            org.bukkit.Bukkit.getLogger().log(java.util.logging.Level.WARNING, "No block for owner at %s %d %d %d", new Object[]{field_145850_b.getWorld(), field_174879_c.func_177958_n(), field_174879_c.func_177956_o(), field_174879_c.func_177952_p()});
            return null;
        }
        // Spigot end
        org.bukkit.block.BlockState state = block.getState(useSnapshot); // Paper
        if (state instanceof InventoryHolder) return (InventoryHolder) state;
        return null;
    }
    // CraftBukkit end
}
