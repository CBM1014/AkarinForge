package net.minecraft.potion;

import com.google.common.collect.ComparisonChain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public class PotionEffect implements Comparable<PotionEffect> {

    private static final Logger field_180155_a = LogManager.getLogger();
    private final Potion field_188420_b;
    private int field_76460_b;
    private int field_76461_c;
    private boolean field_82723_d;
    private boolean field_82724_e;
    private boolean field_188421_h;
    /** List of ItemStack that can cure the potion effect **/
    private java.util.List<net.minecraft.item.ItemStack> curativeItems;

    public PotionEffect(Potion mobeffectlist) {
        this(mobeffectlist, 0, 0);
    }

    public PotionEffect(Potion mobeffectlist, int i) {
        this(mobeffectlist, i, 0);
    }

    public PotionEffect(Potion mobeffectlist, int i, int j) {
        this(mobeffectlist, i, j, false, true);
    }

    public PotionEffect(Potion mobeffectlist, int i, int j, boolean flag, boolean flag1) {
        this.field_188420_b = mobeffectlist;
        this.field_76460_b = i;
        this.field_76461_c = j;
        this.field_82724_e = flag;
        this.field_188421_h = flag1;
        this.curativeItems = mobeffectlist.curativeItems == null ? null : new java.util.ArrayList<net.minecraft.item.ItemStack>(mobeffectlist.curativeItems);
    }

    public PotionEffect(PotionEffect mobeffect) {
        this.field_188420_b = mobeffect.field_188420_b;
        this.field_76460_b = mobeffect.field_76460_b;
        this.field_76461_c = mobeffect.field_76461_c;
        this.field_82724_e = mobeffect.field_82724_e;
        this.field_188421_h = mobeffect.field_188421_h;
    }

    public void func_76452_a(PotionEffect mobeffect) {
        if (this.field_188420_b != mobeffect.field_188420_b) {
            PotionEffect.field_180155_a.warn("This method should only be called for matching effects!");
        }

        if (mobeffect.field_76461_c > this.field_76461_c) {
            this.field_76461_c = mobeffect.field_76461_c;
            this.field_76460_b = mobeffect.field_76460_b;
        } else if (mobeffect.field_76461_c == this.field_76461_c && this.field_76460_b < mobeffect.field_76460_b) {
            this.field_76460_b = mobeffect.field_76460_b;
        } else if (!mobeffect.field_82724_e && this.field_82724_e) {
            this.field_82724_e = mobeffect.field_82724_e;
        }

        this.field_188421_h = mobeffect.field_188421_h;
    }

    public Potion func_188419_a() {
        return this.field_188420_b;
    }

    public int func_76459_b() {
        return this.field_76460_b;
    }

    public int func_76458_c() {
        return this.field_76461_c;
    }

    public boolean func_82720_e() {
        return this.field_82724_e;
    }

    public boolean func_188418_e() {
        return this.field_188421_h;
    }

    public boolean func_76455_a(EntityLivingBase entityliving) {
        if (this.field_76460_b > 0) {
            if (this.field_188420_b.func_76397_a(this.field_76460_b, this.field_76461_c)) {
                this.func_76457_b(entityliving);
            }

            this.func_76454_e();
        }

        return this.field_76460_b > 0;
    }

    private int func_76454_e() {
        return --this.field_76460_b;
    }

    public void func_76457_b(EntityLivingBase entityliving) {
        if (this.field_76460_b > 0) {
            this.field_188420_b.func_76394_a(entityliving, this.field_76461_c);
        }

    }

    public String func_76453_d() {
        return this.field_188420_b.func_76393_a();
    }

    @Override
    public String toString() {
        String s;

        if (this.field_76461_c > 0) {
            s = this.func_76453_d() + " x " + (this.field_76461_c + 1) + ", Duration: " + this.field_76460_b;
        } else {
            s = this.func_76453_d() + ", Duration: " + this.field_76460_b;
        }

        if (this.field_82723_d) {
            s = s + ", Splash: true";
        }

        if (!this.field_188421_h) {
            s = s + ", Particles: false";
        }

        return s;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof PotionEffect)) {
            return false;
        } else {
            PotionEffect mobeffect = (PotionEffect) object;

            return this.field_76460_b == mobeffect.field_76460_b && this.field_76461_c == mobeffect.field_76461_c && this.field_82723_d == mobeffect.field_82723_d && this.field_82724_e == mobeffect.field_82724_e && this.field_188420_b.equals(mobeffect.field_188420_b);
        }
    }

    @Override
    public int hashCode() {
        int i = this.field_188420_b.hashCode();

        i = 31 * i + this.field_76460_b;
        i = 31 * i + this.field_76461_c;
        i = 31 * i + (this.field_82723_d ? 1 : 0);
        i = 31 * i + (this.field_82724_e ? 1 : 0);
        return i;
    }

    public NBTTagCompound func_82719_a(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74774_a("Id", (byte) Potion.func_188409_a(this.func_188419_a()));
        nbttagcompound.func_74774_a("Amplifier", (byte) this.func_76458_c());
        nbttagcompound.func_74768_a("Duration", this.func_76459_b());
        nbttagcompound.func_74757_a("Ambient", this.func_82720_e());
        nbttagcompound.func_74757_a("ShowParticles", this.func_188418_e());
        writeCurativeItems(nbttagcompound);
        return nbttagcompound;
    }

    public static PotionEffect func_82722_b(NBTTagCompound nbttagcompound) {
        int b0 = nbttagcompound.func_74771_c("Id") & 0xFF;
        Potion mobeffectlist = Potion.func_188412_a(b0);

        if (mobeffectlist == null) {
            return null;
        } else {
            byte b1 = nbttagcompound.func_74771_c("Amplifier");
            int i = nbttagcompound.func_74762_e("Duration");
            boolean flag = nbttagcompound.func_74767_n("Ambient");
            boolean flag1 = true;

            if (nbttagcompound.func_150297_b("ShowParticles", 1)) {
                flag1 = nbttagcompound.func_74767_n("ShowParticles");
            }

            return readCurativeItems(new PotionEffect(mobeffectlist, i, b1 < 0 ? 0 : b1, flag, flag1), nbttagcompound);
        }
    }

    @Override
    public int compareTo(PotionEffect p_compareTo_1_) {
        return (this.func_76459_b() <= 32147 || p_compareTo_1_.func_76459_b() <= 32147) && (!this.func_82720_e() || !p_compareTo_1_.func_82720_e()) ? ComparisonChain.start().compare(Boolean.valueOf(this.func_82720_e()), Boolean.valueOf(p_compareTo_1_.func_82720_e())).compare(this.func_76459_b(), p_compareTo_1_.func_76459_b()).compare(this.func_188419_a().getGuiSortColor(this), p_compareTo_1_.func_188419_a().getGuiSortColor(p_compareTo_1_)).result() : ComparisonChain.start().compare(Boolean.valueOf(this.func_82720_e()), Boolean.valueOf(p_compareTo_1_.func_82720_e())).compare(this.func_188419_a().getGuiSortColor(this), p_compareTo_1_.func_188419_a().getGuiSortColor(p_compareTo_1_)).result();
    }
    
    /* ======================================== FORGE START =====================================*/
    /***
     * Returns a list of curative items for the potion effect
     * By default, this list is initialized using {@link Potion#getCurativeItems}
     *
     * @return The list (ItemStack) of curative items for the potion effect
     */
    public java.util.List<net.minecraft.item.ItemStack> getCurativeItems()
    {
        if (this.curativeItems == null) //Lazy load this so that we don't create a circular dep on Items.
        {
            this.curativeItems = func_188419_a().getCurativeItems();
        }
        return this.curativeItems;
    }

    /***
     * Checks the given ItemStack to see if it is in the list of curative items for the potion effect
     * @param stack The ItemStack being checked against the list of curative items for this PotionEffect
     * @return true if the given ItemStack is in the list of curative items for this PotionEffect, false otherwise
     */
    public boolean isCurativeItem(net.minecraft.item.ItemStack stack)
    {
        for (net.minecraft.item.ItemStack curativeItem : this.getCurativeItems())
        {
            if (curativeItem.func_77969_a(stack))
            {
                return true;
            }
        }

        return false;
    }

    /***
     * Sets the list of curative items for this potion effect, overwriting any already present
     * @param curativeItems The list of ItemStacks being set to the potion effect
     */
    public void setCurativeItems(java.util.List<net.minecraft.item.ItemStack> curativeItems)
    {
        this.curativeItems = curativeItems;
    }

    /***
     * Adds the given stack to the list of curative items for this PotionEffect
     * @param stack The ItemStack being added to the curative item list
     */
    public void addCurativeItem(net.minecraft.item.ItemStack stack)
    {
        if (!this.isCurativeItem(stack))
        {
            this.getCurativeItems().add(stack);
        }
    }

    private void writeCurativeItems(NBTTagCompound nbt)
    {
        net.minecraft.nbt.NBTTagList list = new net.minecraft.nbt.NBTTagList();
        for (net.minecraft.item.ItemStack stack : getCurativeItems())
        {
            list.func_74742_a(stack.func_77955_b(new NBTTagCompound()));
        }
        nbt.func_74782_a("CurativeItems", list);
    }

    private static PotionEffect readCurativeItems(PotionEffect effect, NBTTagCompound nbt)
    {
        if (nbt.func_150297_b("CurativeItems", net.minecraftforge.common.util.Constants.NBT.TAG_LIST))
        {
            java.util.List<net.minecraft.item.ItemStack> items = new java.util.ArrayList<net.minecraft.item.ItemStack>();
            net.minecraft.nbt.NBTTagList list = nbt.func_150295_c("CurativeItems", net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND);
            for (int i = 0; i < list.func_74745_c(); i++)
            {
                items.add(new net.minecraft.item.ItemStack(list.func_150305_b(i)));
            }
            effect.setCurativeItems(items);
        }

        return effect;
    }
}
