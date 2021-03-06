package net.minecraft.server;

import com.destroystokyo.paper.profile.CraftPlayerProfile;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mojang.authlib.GameProfile;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.util.Waitable;
import org.spigotmc.AsyncCatcher;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderServer;

public final class MCUtil {
    private static final Executor asyncExecutor = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("Paper Async Task Handler Thread - %1$d").build());

    private MCUtil() {}

    /**
     * Quickly generate a stack trace for current location
     *
     * @return Stacktrace
     */
    public static String stack() {
        return ExceptionUtils.getFullStackTrace(new Throwable());
    }

    /**
     * Quickly generate a stack trace for current location with message
     *
     * @param str
     * @return Stacktrace
     */
    public static String stack(String str) {
        return ExceptionUtils.getFullStackTrace(new Throwable(str));
    }

    /**
     * Ensures the target code is running on the main thread
     * @param reason
     * @param run
     * @param <T>
     * @return
     */
    public static <T> T ensureMain(String reason, Supplier<T> run) {
        if (AsyncCatcher.enabled && Thread.currentThread() != MinecraftServer.getServer().primaryThread) {
            new IllegalStateException( "Asynchronous " + reason + "! Blocking thread until it returns ").printStackTrace();
            Waitable<T> wait = new Waitable<T>() {
                @Override
                protected T evaluate() {
                    return run.get();
                }
            };
            MinecraftServer.getServer().processQueue.add(wait);
            try {
                return wait.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }
        return run.get();
    }

    public static PlayerProfile toBukkit(GameProfile profile) {
        return CraftPlayerProfile.asBukkitMirror(profile);
    }

    /**
     * Calculates distance between 2 entities
     * @param e1
     * @param e2
     * @return
     */
    public static double distance(Entity e1, Entity e2) {
        return Math.sqrt(distanceSq(e1, e2));
    }


    /**
     * Calculates distance between 2 block positions
     * @param e1
     * @param e2
     * @return
     */
    public static double distance(BlockPos e1, BlockPos e2) {
        return Math.sqrt(distanceSq(e1, e2));
    }

    /**
     * Gets the distance between 2 positions
     * @param x1
     * @param y1
     * @param z1
     * @param x2
     * @param y2
     * @param z2
     * @return
     */
    public static double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(distanceSq(x1, y1, z1, x2, y2, z2));
    }

    /**
     * Get's the distance squared between 2 entities
     * @param e1
     * @param e2
     * @return
     */
    public static double distanceSq(Entity e1, Entity e2) {
        return distanceSq(e1.field_70165_t,e1.field_70163_u,e1.field_70161_v, e2.field_70165_t,e2.field_70163_u,e2.field_70161_v);
    }

    /**
     * Gets the distance sqaured between 2 block positions
     * @param pos1
     * @param pos2
     * @return
     */
    public static double distanceSq(BlockPos pos1, BlockPos pos2) {
        return distanceSq(pos1.func_177958_n(), pos1.func_177956_o(), pos1.func_177952_p(), pos2.func_177958_n(), pos2.func_177956_o(), pos2.func_177952_p());
    }

    /**
     * Gets the distance squared between 2 positions
     * @param x1
     * @param y1
     * @param z1
     * @param x2
     * @param y2
     * @param z2
     * @return
     */
    public static double distanceSq(double x1, double y1, double z1, double x2, double y2, double z2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2);
    }

    /**
     * Converts a NMS World/BlockPosition to Bukkit Location
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static Location toLocation(World world, double x, double y, double z) {
        return new Location(world.getWorld(), x, y, z);
    }

    /**
     * Converts a NMS World/BlockPosition to Bukkit Location
     * @param world
     * @param pos
     * @return
     */
    public static Location toLocation(World world, BlockPos pos) {
        return new Location(world.getWorld(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
    }

    /**
     * Converts an NMS entity's current location to a Bukkit Location
     * @param entity
     * @return
     */
    public static Location toLocation(Entity entity) {
        return new Location(entity.func_130014_f_().getWorld(), entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
    }

    public static BlockPos toBlockPosition(Location loc) {
        return new BlockPos(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    public static boolean isEdgeOfChunk(BlockPos pos) {
        final int modX = pos.func_177958_n() & 15;
        final int modZ = pos.func_177952_p() & 15;
        return (modX == 0 || modX == 15 || modZ == 0 || modZ == 15);
    }

    /**
     * Gets a chunk without changing its boolean for should unload
     * @param world
     * @param x
     * @param z
     * @return
     */
    @Nullable
    public static Chunk getLoadedChunkWithoutMarkingActive(World world, int x, int z) {
        return ((ChunkProviderServer) world.field_73020_y).field_73244_f.get(ChunkPos.func_77272_a(x, z));
    }

    /**
     * Gets a chunk without changing its boolean for should unload
     * @param provider
     * @param x
     * @param z
     * @return
     */
    @Nullable
    public static Chunk getLoadedChunkWithoutMarkingActive(IChunkProvider provider, int x, int z) {
        return ((ChunkProviderServer)provider).field_73244_f.get(ChunkPos.func_77272_a(x, z));
    }

    /**
     * Posts a task to be executed asynchronously
     * @param run
     */
    public static void scheduleAsyncTask(Runnable run) {
        asyncExecutor.execute(run);
    }

    @Nullable
    public static TileEntityHopper getHopper(World world, BlockPos pos) {
        Chunk chunk = world.getChunkIfLoaded(pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
        if (chunk != null && chunk.func_177435_g(pos).func_177230_c() == Blocks.field_150438_bZ) {
            TileEntity tileEntity = chunk.getTileEntityImmediately(pos);
            if (tileEntity instanceof TileEntityHopper) {
                return (TileEntityHopper) tileEntity;
            }
        }
        return null;
    }

    @Nonnull
    public static World getNMSWorld(@Nonnull org.bukkit.World world) {
        return ((CraftWorld) world).getHandle();
    }

    public static World getNMSWorld(@Nonnull org.bukkit.entity.Entity entity) {
        return getNMSWorld(entity.getWorld());
    }
}
