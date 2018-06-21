package net.minecraft.stats;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class StatList {

    protected static final Map<String, StatBase> field_188093_a = Maps.newHashMap();
    public static final List<StatBase> field_75940_b = Lists.newArrayList();
    public static final List<StatBase> field_188094_c = Lists.newArrayList();
    public static final List<StatCrafting> field_188095_d = Lists.newArrayList();
    public static final List<StatCrafting> field_188096_e = Lists.newArrayList();
    public static final StatBase field_75947_j = (new StatBasic("stat.leaveGame", new TextComponentTranslation("stat.leaveGame", new Object[0]))).func_75966_h().func_75971_g();
    public static final StatBase field_188097_g = (new StatBasic("stat.playOneMinute", new TextComponentTranslation("stat.playOneMinute", new Object[0]), StatBase.field_75981_i)).func_75966_h().func_75971_g();
    public static final StatBase field_188098_h = (new StatBasic("stat.timeSinceDeath", new TextComponentTranslation("stat.timeSinceDeath", new Object[0]), StatBase.field_75981_i)).func_75966_h().func_75971_g();
    public static final StatBase field_188099_i = (new StatBasic("stat.sneakTime", new TextComponentTranslation("stat.sneakTime", new Object[0]), StatBase.field_75981_i)).func_75966_h().func_75971_g();
    public static final StatBase field_188100_j = (new StatBasic("stat.walkOneCm", new TextComponentTranslation("stat.walkOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_188101_k = (new StatBasic("stat.crouchOneCm", new TextComponentTranslation("stat.crouchOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_188102_l = (new StatBasic("stat.sprintOneCm", new TextComponentTranslation("stat.sprintOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_75946_m = (new StatBasic("stat.swimOneCm", new TextComponentTranslation("stat.swimOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_75943_n = (new StatBasic("stat.fallOneCm", new TextComponentTranslation("stat.fallOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_188103_o = (new StatBasic("stat.climbOneCm", new TextComponentTranslation("stat.climbOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_188104_p = (new StatBasic("stat.flyOneCm", new TextComponentTranslation("stat.flyOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_188105_q = (new StatBasic("stat.diveOneCm", new TextComponentTranslation("stat.diveOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_188106_r = (new StatBasic("stat.minecartOneCm", new TextComponentTranslation("stat.minecartOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_188107_s = (new StatBasic("stat.boatOneCm", new TextComponentTranslation("stat.boatOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_188108_t = (new StatBasic("stat.pigOneCm", new TextComponentTranslation("stat.pigOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_188109_u = (new StatBasic("stat.horseOneCm", new TextComponentTranslation("stat.horseOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_188110_v = (new StatBasic("stat.aviateOneCm", new TextComponentTranslation("stat.aviateOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    public static final StatBase field_75953_u = (new StatBasic("stat.jump", new TextComponentTranslation("stat.jump", new Object[0]))).func_75966_h().func_75971_g();
    public static final StatBase field_75952_v = (new StatBasic("stat.drop", new TextComponentTranslation("stat.drop", new Object[0]))).func_75966_h().func_75971_g();
    public static final StatBase field_188111_y = (new StatBasic("stat.damageDealt", new TextComponentTranslation("stat.damageDealt", new Object[0]), StatBase.field_111202_k)).func_75971_g();
    public static final StatBase field_188112_z = (new StatBasic("stat.damageTaken", new TextComponentTranslation("stat.damageTaken", new Object[0]), StatBase.field_111202_k)).func_75971_g();
    public static final StatBase field_188069_A = (new StatBasic("stat.deaths", new TextComponentTranslation("stat.deaths", new Object[0]))).func_75971_g();
    public static final StatBase field_188070_B = (new StatBasic("stat.mobKills", new TextComponentTranslation("stat.mobKills", new Object[0]))).func_75971_g();
    public static final StatBase field_151186_x = (new StatBasic("stat.animalsBred", new TextComponentTranslation("stat.animalsBred", new Object[0]))).func_75971_g();
    public static final StatBase field_75932_A = (new StatBasic("stat.playerKills", new TextComponentTranslation("stat.playerKills", new Object[0]))).func_75971_g();
    public static final StatBase field_188071_E = (new StatBasic("stat.fishCaught", new TextComponentTranslation("stat.fishCaught", new Object[0]))).func_75971_g();
    public static final StatBase field_188074_H = (new StatBasic("stat.talkedToVillager", new TextComponentTranslation("stat.talkedToVillager", new Object[0]))).func_75971_g();
    public static final StatBase field_188075_I = (new StatBasic("stat.tradedWithVillager", new TextComponentTranslation("stat.tradedWithVillager", new Object[0]))).func_75971_g();
    public static final StatBase field_188076_J = (new StatBasic("stat.cakeSlicesEaten", new TextComponentTranslation("stat.cakeSlicesEaten", new Object[0]))).func_75971_g();
    public static final StatBase field_188077_K = (new StatBasic("stat.cauldronFilled", new TextComponentTranslation("stat.cauldronFilled", new Object[0]))).func_75971_g();
    public static final StatBase field_188078_L = (new StatBasic("stat.cauldronUsed", new TextComponentTranslation("stat.cauldronUsed", new Object[0]))).func_75971_g();
    public static final StatBase field_188079_M = (new StatBasic("stat.armorCleaned", new TextComponentTranslation("stat.armorCleaned", new Object[0]))).func_75971_g();
    public static final StatBase field_188080_N = (new StatBasic("stat.bannerCleaned", new TextComponentTranslation("stat.bannerCleaned", new Object[0]))).func_75971_g();
    public static final StatBase field_188081_O = (new StatBasic("stat.brewingstandInteraction", new TextComponentTranslation("stat.brewingstandInteraction", new Object[0]))).func_75971_g();
    public static final StatBase field_188082_P = (new StatBasic("stat.beaconInteraction", new TextComponentTranslation("stat.beaconInteraction", new Object[0]))).func_75971_g();
    public static final StatBase field_188083_Q = (new StatBasic("stat.dropperInspected", new TextComponentTranslation("stat.dropperInspected", new Object[0]))).func_75971_g();
    public static final StatBase field_188084_R = (new StatBasic("stat.hopperInspected", new TextComponentTranslation("stat.hopperInspected", new Object[0]))).func_75971_g();
    public static final StatBase field_188085_S = (new StatBasic("stat.dispenserInspected", new TextComponentTranslation("stat.dispenserInspected", new Object[0]))).func_75971_g();
    public static final StatBase field_188086_T = (new StatBasic("stat.noteblockPlayed", new TextComponentTranslation("stat.noteblockPlayed", new Object[0]))).func_75971_g();
    public static final StatBase field_188087_U = (new StatBasic("stat.noteblockTuned", new TextComponentTranslation("stat.noteblockTuned", new Object[0]))).func_75971_g();
    public static final StatBase field_188088_V = (new StatBasic("stat.flowerPotted", new TextComponentTranslation("stat.flowerPotted", new Object[0]))).func_75971_g();
    public static final StatBase field_188089_W = (new StatBasic("stat.trappedChestTriggered", new TextComponentTranslation("stat.trappedChestTriggered", new Object[0]))).func_75971_g();
    public static final StatBase field_188090_X = (new StatBasic("stat.enderchestOpened", new TextComponentTranslation("stat.enderchestOpened", new Object[0]))).func_75971_g();
    public static final StatBase field_188091_Y = (new StatBasic("stat.itemEnchanted", new TextComponentTranslation("stat.itemEnchanted", new Object[0]))).func_75971_g();
    public static final StatBase field_188092_Z = (new StatBasic("stat.recordPlayed", new TextComponentTranslation("stat.recordPlayed", new Object[0]))).func_75971_g();
    public static final StatBase field_188061_aa = (new StatBasic("stat.furnaceInteraction", new TextComponentTranslation("stat.furnaceInteraction", new Object[0]))).func_75971_g();
    public static final StatBase field_188062_ab = (new StatBasic("stat.craftingTableInteraction", new TextComponentTranslation("stat.workbenchInteraction", new Object[0]))).func_75971_g();
    public static final StatBase field_188063_ac = (new StatBasic("stat.chestOpened", new TextComponentTranslation("stat.chestOpened", new Object[0]))).func_75971_g();
    public static final StatBase field_188064_ad = (new StatBasic("stat.sleepInBed", new TextComponentTranslation("stat.sleepInBed", new Object[0]))).func_75971_g();
    public static final StatBase field_191272_ae = (new StatBasic("stat.shulkerBoxOpened", new TextComponentTranslation("stat.shulkerBoxOpened", new Object[0]))).func_75971_g();
    private static final StatBase[] field_188065_ae = new StatBase[4096];
    private static final StatBase[] field_188066_af = new StatBase[32000];
    private static final StatBase[] field_75929_E = new StatBase[32000];
    private static final StatBase[] field_75930_F = new StatBase[32000];
    private static final StatBase[] field_188067_ai = new StatBase[32000];
    private static final StatBase[] field_188068_aj = new StatBase[32000];

    @Nullable
    public static StatBase func_188055_a(Block block) {
        return StatList.field_188065_ae[Block.func_149682_b(block)];
    }

    @Nullable
    public static StatBase func_188060_a(Item item) {
        return StatList.field_188066_af[Item.func_150891_b(item)];
    }

    @Nullable
    public static StatBase func_188057_b(Item item) {
        return StatList.field_75929_E[Item.func_150891_b(item)];
    }

    @Nullable
    public static StatBase func_188059_c(Item item) {
        return StatList.field_75930_F[Item.func_150891_b(item)];
    }

    @Nullable
    public static StatBase func_188056_d(Item item) {
        return StatList.field_188067_ai[Item.func_150891_b(item)];
    }

    @Nullable
    public static StatBase func_188058_e(Item item) {
        return StatList.field_188068_aj[Item.func_150891_b(item)];
    }

    public static void func_151178_a() {
        func_151181_c();
        func_75925_c();
        func_151179_e();
        func_75918_d();
        func_188054_f();
    }

    private static void func_75918_d() {
        HashSet hashset = Sets.newHashSet();
        Iterator iterator = CraftingManager.field_193380_a.iterator();

        while (iterator.hasNext()) {
            IRecipe irecipe = (IRecipe) iterator.next();
            ItemStack itemstack = irecipe.func_77571_b();

            if (!itemstack.func_190926_b()) {
                hashset.add(irecipe.func_77571_b().func_77973_b());
            }
        }

        iterator = FurnaceRecipes.func_77602_a().func_77599_b().values().iterator();

        while (iterator.hasNext()) {
            ItemStack itemstack1 = (ItemStack) iterator.next();

            hashset.add(itemstack1.func_77973_b());
        }

        iterator = hashset.iterator();

        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();

            if (item != null) {
                int i = Item.func_150891_b(item);
                String s = func_180204_a(item);

                if (s != null) {
                    StatList.field_188066_af[i] = (new StatCrafting("stat.craftItem.", s, new TextComponentTranslation("stat.craftItem", new Object[] { (new ItemStack(item)).func_151000_E()}), item)).func_75971_g();
                }
            }
        }

        replaceAllSimilarBlocks(field_188066_af, true);
    }

    private static void func_151181_c() {
        Iterator iterator = Block.field_149771_c.iterator();

        while (iterator.hasNext()) {
            Block block = (Block) iterator.next();
            Item item = Item.func_150898_a(block);

            if (item != Items.field_190931_a) {
                int i = Block.func_149682_b(block);
                String s = func_180204_a(item);

                if (s != null && block.func_149652_G()) {
                    StatList.field_188065_ae[i] = (new StatCrafting("stat.mineBlock.", s, new TextComponentTranslation("stat.mineBlock", new Object[] { (new ItemStack(block)).func_151000_E()}), item)).func_75971_g();
                    StatList.field_188096_e.add((StatCrafting) StatList.field_188065_ae[i]);
                }
            }
        }

        replaceAllSimilarBlocks(field_188065_ae, false);
    }

    private static void func_75925_c() {
        Iterator iterator = Item.field_150901_e.iterator();

        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();

            if (item != null) {
                int i = Item.func_150891_b(item);
                String s = func_180204_a(item);

                if (s != null) {
                    StatList.field_75929_E[i] = (new StatCrafting("stat.useItem.", s, new TextComponentTranslation("stat.useItem", new Object[] { (new ItemStack(item)).func_151000_E()}), item)).func_75971_g();
                    if (!(item instanceof ItemBlock)) {
                        StatList.field_188095_d.add((StatCrafting) StatList.field_75929_E[i]);
                    }
                }
            }
        }

        replaceAllSimilarBlocks(field_75929_E, true);
    }

    private static void func_151179_e() {
        Iterator iterator = Item.field_150901_e.iterator();

        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();

            if (item != null) {
                int i = Item.func_150891_b(item);
                String s = func_180204_a(item);

                if (s != null && item.func_77645_m()) {
                    StatList.field_75930_F[i] = (new StatCrafting("stat.breakItem.", s, new TextComponentTranslation("stat.breakItem", new Object[] { (new ItemStack(item)).func_151000_E()}), item)).func_75971_g();
                }
            }
        }

        replaceAllSimilarBlocks(field_75930_F, true);
    }

    private static void func_188054_f() {
        Iterator iterator = Item.field_150901_e.iterator();

        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();

            if (item != null) {
                int i = Item.func_150891_b(item);
                String s = func_180204_a(item);

                if (s != null) {
                    StatList.field_188067_ai[i] = (new StatCrafting("stat.pickup.", s, new TextComponentTranslation("stat.pickup", new Object[] { (new ItemStack(item)).func_151000_E()}), item)).func_75971_g();
                    StatList.field_188068_aj[i] = (new StatCrafting("stat.drop.", s, new TextComponentTranslation("stat.drop", new Object[] { (new ItemStack(item)).func_151000_E()}), item)).func_75971_g();
                }
            }
        }

        replaceAllSimilarBlocks(field_75930_F, true);
    }

    private static String func_180204_a(Item item) {
        ResourceLocation minecraftkey = (ResourceLocation) Item.field_150901_e.func_177774_c(item);

        return minecraftkey != null ? minecraftkey.toString().replace(':', '.') : null;
    }

    private static void replaceAllSimilarBlocks(StatBase[] p_75924_0_, boolean useItemIds) {
        mergeStatBases(p_75924_0_, Blocks.field_150355_j, Blocks.field_150358_i, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_150353_l, Blocks.field_150356_k, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_150428_aP, Blocks.field_150423_aK, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_150470_am, Blocks.field_150460_al, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_150439_ay, Blocks.field_150450_ax, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_150416_aS, Blocks.field_150413_aR, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_150455_bV, Blocks.field_150441_bU, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_150429_aA, Blocks.field_150437_az, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_150374_bv, Blocks.field_150379_bu, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_150334_T, Blocks.field_150333_U, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_150373_bw, Blocks.field_150376_bx, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_180388_cO, Blocks.field_180389_cP, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_150349_c, Blocks.field_150346_d, useItemIds);
        mergeStatBases(p_75924_0_, Blocks.field_150458_ak, Blocks.field_150346_d, useItemIds);
    }

    private static void mergeStatBases(StatBase[] astatistic, Block block, Block block1, boolean useItemIds) {
        int i;
        int j;
        if (useItemIds) {
            i = Item.func_150891_b(Item.func_150898_a(block));
            j = Item.func_150891_b(Item.func_150898_a(block1));
        } else {
            i = Block.func_149682_b(block);
            j = Block.func_149682_b(block1);
        }

        if (astatistic[i] != null && astatistic[j] == null) {
            astatistic[j] = astatistic[i];
        } else {
            StatList.field_75940_b.remove(astatistic[i]);
            StatList.field_188096_e.remove(astatistic[i]);
            StatList.field_188094_c.remove(astatistic[i]);
            astatistic[i] = astatistic[j];
        }
    }

    public static StatBase func_151182_a(EntityList.EntityEggInfo entitytypes_monsteregginfo) {
        String s = EntityList.func_191302_a(entitytypes_monsteregginfo.field_75613_a);

        return s == null ? null : (new StatBase("stat.killEntity." + s, new TextComponentTranslation("stat.entityKill", new Object[] { new TextComponentTranslation("entity." + s + ".name", new Object[0])}))).func_75971_g();
    }

    public static StatBase func_151176_b(EntityList.EntityEggInfo entitytypes_monsteregginfo) {
        String s = EntityList.func_191302_a(entitytypes_monsteregginfo.field_75613_a);

        return s == null ? null : (new StatBase("stat.entityKilledBy." + s, new TextComponentTranslation("stat.entityKilledBy", new Object[] { new TextComponentTranslation("entity." + s + ".name", new Object[0])}))).func_75971_g();
    }

    @Nullable
    public static StatBase func_151177_a(String s) {
        return (StatBase) StatList.field_188093_a.get(s);
    }
    
    @Deprecated //MODDER DO NOT CALL THIS ITS JUST A EVENT CALLBACK FOR FORGE
    public static void reinit() {
        field_188093_a.clear();
        field_188094_c.clear();
        field_188095_d.clear();
        field_188096_e.clear();

        for (StatBase[] sb : new StatBase[][]{field_188065_ae,  field_188066_af, field_75929_E, field_75930_F, field_188067_ai, field_188068_aj})
        {
            for (int x = 0; x < sb.length; x++)
            {
                if (sb[x] != null)
                {
                    field_75940_b.remove(sb[x]);
                    sb[x] = null;
                }
            }
        }
        List<StatBase> unknown = Lists.newArrayList(field_75940_b);
        field_75940_b.clear();

        for (StatBase b : unknown)
            b.func_75971_g();

        func_151181_c();
        func_75925_c();
        func_151179_e();
        func_75918_d();
        func_188054_f();
    }
}
