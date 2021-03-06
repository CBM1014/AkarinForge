package net.minecraft.entity.passive;

import java.util.UUID;
import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityHorse extends AbstractHorse {

    private static final UUID field_184786_bD = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
    private static final DataParameter<Integer> field_184789_bG = EntityDataManager.func_187226_a(EntityHorse.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> field_184791_bI = EntityDataManager.func_187226_a(EntityHorse.class, DataSerializers.field_187192_b);
    private static final String[] field_110268_bz = new String[] { "textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png"};
    private static final String[] field_110269_bA = new String[] { "hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
    private static final String[] field_110291_bB = new String[] { null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png"};
    private static final String[] field_110292_bC = new String[] { "", "wo_", "wmo", "wdo", "bdo"};
    private String field_110286_bQ;
    private final String[] field_110280_bR = new String[3];

    public EntityHorse(World world) {
        super(world);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(EntityHorse.field_184789_bG, Integer.valueOf(0));
        this.field_70180_af.func_187214_a(EntityHorse.field_184791_bI, Integer.valueOf(HorseArmorType.NONE.func_188579_a()));
    }

    public static void func_189803_b(DataFixer dataconvertermanager) {
        AbstractHorse.func_190683_c(dataconvertermanager, EntityHorse.class);
        dataconvertermanager.func_188258_a(FixTypes.ENTITY, (new ItemStackData(EntityHorse.class, new String[] { "ArmorItem"})));
    }

    @Override
    public void func_70014_b(NBTTagCompound nbttagcompound) {
        super.func_70014_b(nbttagcompound);
        nbttagcompound.func_74768_a("Variant", this.func_110202_bQ());
        if (!this.field_110296_bG.func_70301_a(1).func_190926_b()) {
            nbttagcompound.func_74782_a("ArmorItem", this.field_110296_bG.func_70301_a(1).func_77955_b(new NBTTagCompound()));
        }

    }

    @Override
    public void func_70037_a(NBTTagCompound nbttagcompound) {
        super.func_70037_a(nbttagcompound);
        this.func_110235_q(nbttagcompound.func_74762_e("Variant"));
        if (nbttagcompound.func_150297_b("ArmorItem", 10)) {
            ItemStack itemstack = new ItemStack(nbttagcompound.func_74775_l("ArmorItem"));

            if (!itemstack.func_190926_b() && HorseArmorType.func_188577_b(itemstack.func_77973_b())) {
                this.field_110296_bG.func_70299_a(1, itemstack);
            }
        }

        this.func_110232_cE();
    }

    public void func_110235_q(int i) {
        this.field_70180_af.func_187227_b(EntityHorse.field_184789_bG, Integer.valueOf(i));
        this.func_110230_cF();
    }

    public int func_110202_bQ() {
        return this.field_70180_af.func_187225_a(EntityHorse.field_184789_bG).intValue();
    }

    private void func_110230_cF() {
        this.field_110286_bQ = null;
    }

    @Override
    protected void func_110232_cE() {
        super.func_110232_cE();
        this.func_146086_d(this.field_110296_bG.func_70301_a(1));
    }

    public void func_146086_d(ItemStack itemstack) {
        HorseArmorType enumhorsearmor = HorseArmorType.func_188580_a(itemstack);

        this.field_70180_af.func_187227_b(EntityHorse.field_184791_bI, Integer.valueOf(enumhorsearmor.func_188579_a()));
        this.func_110230_cF();
        if (!this.field_70170_p.field_72995_K) {
            this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_188479_b(EntityHorse.field_184786_bD);
            int i = enumhorsearmor.func_188578_c();

            if (i != 0) {
                this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111121_a((new AttributeModifier(EntityHorse.field_184786_bD, "Horse armor bonus", i, 0)).func_111168_a(false));
            }
        }

    }

    public HorseArmorType func_184783_dl() {
        return HorseArmorType.func_188575_a(this.field_70180_af.func_187225_a(EntityHorse.field_184791_bI).intValue());
    }

    @Override
    public void func_76316_a(IInventory iinventory) {
        HorseArmorType enumhorsearmor = this.func_184783_dl();

        super.func_76316_a(iinventory);
        HorseArmorType enumhorsearmor1 = this.func_184783_dl();

        if (this.field_70173_aa > 20 && enumhorsearmor != enumhorsearmor1 && enumhorsearmor1 != HorseArmorType.NONE) {
            this.func_184185_a(SoundEvents.field_187702_cm, 0.5F, 1.0F);
        }

    }

    @Override
    protected void func_190680_a(SoundType soundeffecttype) {
        super.func_190680_a(soundeffecttype);
        if (this.field_70146_Z.nextInt(10) == 0) {
            this.func_184185_a(SoundEvents.field_187705_cn, soundeffecttype.func_185843_a() * 0.6F, soundeffecttype.func_185847_b());
        }

    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.func_110267_cL());
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.func_110203_cN());
        this.func_110148_a(EntityHorse.field_110271_bv).func_111128_a(this.func_110245_cM());
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K && this.field_70180_af.func_187223_a()) {
            this.field_70180_af.func_187230_e();
            this.func_110230_cF();
        }

    }

    @Override
    protected SoundEvent func_184639_G() {
        super.func_184639_G();
        return SoundEvents.field_187696_ck;
    }

    @Override
    protected SoundEvent func_184615_bR() {
        super.func_184615_bR();
        return SoundEvents.field_187708_co;
    }

    @Override
    protected SoundEvent func_184601_bQ(DamageSource damagesource) {
        super.func_184601_bQ(damagesource);
        return SoundEvents.field_187717_cr;
    }

    @Override
    protected SoundEvent func_184785_dv() {
        super.func_184785_dv();
        return SoundEvents.field_187699_cl;
    }

    @Override
    protected ResourceLocation func_184647_J() {
        return LootTableList.field_186396_D;
    }

    @Override
    public boolean func_184645_a(EntityPlayer entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.func_184586_b(enumhand);
        boolean flag = !itemstack.func_190926_b();

        if (flag && itemstack.func_77973_b() == Items.field_151063_bx) {
            return super.func_184645_a(entityhuman, enumhand);
        } else {
            if (!this.func_70631_g_()) {
                if (this.func_110248_bS() && entityhuman.func_70093_af()) {
                    this.func_110199_f(entityhuman);
                    return true;
                }

                if (this.func_184207_aI()) {
                    return super.func_184645_a(entityhuman, enumhand);
                }
            }

            if (flag) {
                if (this.func_190678_b(entityhuman, itemstack)) {
                    if (!entityhuman.field_71075_bZ.field_75098_d) {
                        itemstack.func_190918_g(1);
                    }

                    return true;
                }

                if (itemstack.func_111282_a(entityhuman, this, enumhand)) {
                    return true;
                }

                if (!this.func_110248_bS()) {
                    this.func_190687_dF();
                    return true;
                }

                boolean flag1 = HorseArmorType.func_188580_a(itemstack) != HorseArmorType.NONE;
                boolean flag2 = !this.func_70631_g_() && !this.func_110257_ck() && itemstack.func_77973_b() == Items.field_151141_av;

                if (flag1 || flag2) {
                    this.func_110199_f(entityhuman);
                    return true;
                }
            }

            if (this.func_70631_g_()) {
                return super.func_184645_a(entityhuman, enumhand);
            } else {
                this.func_110237_h(entityhuman);
                return true;
            }
        }
    }

    @Override
    public boolean func_70878_b(EntityAnimal entityanimal) {
        return entityanimal == this ? false : (!(entityanimal instanceof EntityDonkey) && !(entityanimal instanceof EntityHorse) ? false : this.func_110200_cJ() && ((AbstractHorse) entityanimal).func_110200_cJ());
    }

    @Override
    public EntityAgeable func_90011_a(EntityAgeable entityageable) {
        Object object;

        if (entityageable instanceof EntityDonkey) {
            object = new EntityMule(this.field_70170_p);
        } else {
            EntityHorse entityhorse = (EntityHorse) entityageable;

            object = new EntityHorse(this.field_70170_p);
            int i = this.field_70146_Z.nextInt(9);
            int j;

            if (i < 4) {
                j = this.func_110202_bQ() & 255;
            } else if (i < 8) {
                j = entityhorse.func_110202_bQ() & 255;
            } else {
                j = this.field_70146_Z.nextInt(7);
            }

            int k = this.field_70146_Z.nextInt(5);

            if (k < 2) {
                j |= this.func_110202_bQ() & '\uff00';
            } else if (k < 4) {
                j |= entityhorse.func_110202_bQ() & '\uff00';
            } else {
                j |= this.field_70146_Z.nextInt(5) << 8 & '\uff00';
            }

            ((EntityHorse) object).func_110235_q(j);
        }

        this.func_190681_a(entityageable, (AbstractHorse) object);
        return (EntityAgeable) object;
    }

    @Override
    public boolean func_190677_dK() {
        return true;
    }

    @Override
    public boolean func_190682_f(ItemStack itemstack) {
        return HorseArmorType.func_188577_b(itemstack.func_77973_b());
    }

    @Override
    @Nullable
    public IEntityLivingData func_180482_a(DifficultyInstance difficultydamagescaler, @Nullable IEntityLivingData groupdataentity) {
        Object object = super.func_180482_a(difficultydamagescaler, groupdataentity);
        int i;

        if (object instanceof EntityHorse.a) {
            i = ((EntityHorse.a) object).a;
        } else {
            i = this.field_70146_Z.nextInt(7);
            object = new EntityHorse.a(i);
        }

        this.func_110235_q(i | this.field_70146_Z.nextInt(5) << 8);
        return (IEntityLivingData) object;
    }

    public static class a implements IEntityLivingData {

        public int a;

        public a(int i) {
            this.a = i;
        }
    }
}
