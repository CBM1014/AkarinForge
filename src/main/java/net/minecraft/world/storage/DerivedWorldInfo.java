package net.minecraft.world.storage;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldType;

public class DerivedWorldInfo extends WorldInfo {

    private final WorldInfo field_76115_a;

    public DerivedWorldInfo(WorldInfo worlddata) {
        this.field_76115_a = worlddata;
    }

    public NBTTagCompound func_76082_a(@Nullable NBTTagCompound nbttagcompound) {
        return this.field_76115_a.func_76082_a(nbttagcompound);
    }

    public long func_76063_b() {
        return this.field_76115_a.func_76063_b();
    }

    public int func_76079_c() {
        return this.field_76115_a.func_76079_c();
    }

    public int func_76075_d() {
        return this.field_76115_a.func_76075_d();
    }

    public int func_76074_e() {
        return this.field_76115_a.func_76074_e();
    }

    public long func_82573_f() {
        return this.field_76115_a.func_82573_f();
    }

    public long func_76073_f() {
        return this.field_76115_a.func_76073_f();
    }

    public NBTTagCompound func_76072_h() {
        return this.field_76115_a.func_76072_h();
    }

    public String func_76065_j() {
        return this.field_76115_a.func_76065_j();
    }

    public int func_76088_k() {
        return this.field_76115_a.func_76088_k();
    }

    public boolean func_76061_m() {
        return this.field_76115_a.func_76061_m();
    }

    public int func_76071_n() {
        return this.field_76115_a.func_76071_n();
    }

    public boolean func_76059_o() {
        return this.field_76115_a.func_76059_o();
    }

    public int func_76083_p() {
        return this.field_76115_a.func_76083_p();
    }

    public GameType func_76077_q() {
        return this.field_76115_a.func_76077_q();
    }

    public void func_82572_b(long i) {}

    public void func_76068_b(long i) {}

    public void func_176143_a(BlockPos blockposition) {}

    public void func_76062_a(String s) {}

    public void func_76078_e(int i) {}

    public void func_76069_a(boolean flag) {}

    public void func_76090_f(int i) {}

    public void func_76084_b(boolean flag) {}

    public void func_76080_g(int i) {}

    public boolean func_76089_r() {
        return this.field_76115_a.func_76089_r();
    }

    public boolean func_76093_s() {
        return this.field_76115_a.func_76093_s();
    }

    public WorldType func_76067_t() {
        return this.field_76115_a.func_76067_t();
    }

    public void func_76085_a(WorldType worldtype) {}

    public boolean func_76086_u() {
        return this.field_76115_a.func_76086_u();
    }

    public void func_176121_c(boolean flag) {}

    public boolean func_76070_v() {
        return this.field_76115_a.func_76070_v();
    }

    public void func_76091_d(boolean flag) {}

    public GameRules func_82574_x() {
        return this.field_76115_a.func_82574_x();
    }

    public EnumDifficulty func_176130_y() {
        return this.field_76115_a.func_176130_y();
    }

    public void func_176144_a(EnumDifficulty enumdifficulty) {}

    public boolean func_176123_z() {
        return this.field_76115_a.func_176123_z();
    }

    public void func_180783_e(boolean flag) {}

    public void func_186345_a(DimensionType dimensionmanager, NBTTagCompound nbttagcompound) {
        this.field_76115_a.func_186345_a(dimensionmanager, nbttagcompound);
    }

    public NBTTagCompound func_186347_a(DimensionType dimensionmanager) {
        return this.field_76115_a.func_186347_a(dimensionmanager);
    }
}
