package net.minecraft.world.biome;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.List;

import net.minecraft.server.BiomeCache.a;
import net.minecraft.server.MinecraftServer;

public class BiomeCache {

    private final BiomeProvider provider;
    private long lastCleanupTime;
    private final Long2ObjectMap<BiomeCache.a> cacheMap = new Long2ObjectOpenHashMap(4096);
    private final List<BiomeCache.a> cache = Lists.newArrayList();

    public BiomeCache(BiomeProvider worldchunkmanager) {
        this.provider = worldchunkmanager;
    }

    public BiomeCache.a a(int i, int j) {
        i >>= 4;
        j >>= 4;
        long k = (long) i & 4294967295L | ((long) j & 4294967295L) << 32;
        BiomeCache.a biomecache_a = (BiomeCache.a) this.cacheMap.get(k);

        if (biomecache_a == null) {
            biomecache_a = new BiomeCache.a(i, j);
            this.cacheMap.put(k, biomecache_a);
            this.cache.add(biomecache_a);
        }

        biomecache_a.d = MinecraftServer.getCurrentTimeMillis();
        return biomecache_a;
    }

    public Biome getBiome(int i, int j, Biome biomebase) {
        Biome biomebase1 = this.a(i, j).a(i, j);

        return biomebase1 == null ? biomebase : biomebase1;
    }

    public void cleanupCache() {
        long i = MinecraftServer.getCurrentTimeMillis();
        long j = i - this.lastCleanupTime;

        if (j > 7500L || j < 0L) {
            this.lastCleanupTime = i;

            for (int k = 0; k < this.cache.size(); ++k) {
                BiomeCache.a biomecache_a = (BiomeCache.a) this.cache.get(k);
                long l = i - biomecache_a.d;

                if (l > 30000L || l < 0L) {
                    this.cache.remove(k--);
                    long i1 = (long) biomecache_a.b & 4294967295L | ((long) biomecache_a.c & 4294967295L) << 32;

                    this.cacheMap.remove(i1);
                }
            }
        }

    }

    public Biome[] getCachedBiomes(int i, int j) {
        return this.a(i, j).a;
    }

    public class a {

        public Biome[] a = new Biome[256];
        public int b;
        public int c;
        public long d;

        public a(int i, int j) {
            this.b = i;
            this.c = j;
            BiomeCache.this.provider.getBiomes(this.a, i << 4, j << 4, 16, 16, false);
        }

        public Biome a(int i, int j) {
            return this.a[i & 15 | (j & 15) << 4];
        }
    }
}