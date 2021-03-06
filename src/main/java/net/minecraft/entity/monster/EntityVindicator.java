package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityVindicator extends AbstractIllager {

    private boolean field_190643_b;
    private static final Predicate<Entity> field_190644_c = new Predicate() {
        public boolean a(@Nullable Entity entity) {
            return entity instanceof EntityLivingBase && ((EntityLivingBase) entity).func_190631_cK();
        }

        @Override
        public boolean apply(@Nullable Object object) {
            return this.a((Entity) object);
        }
    };

    public EntityVindicator(World world) {
        super(world);
        this.func_70105_a(0.6F, 1.95F);
    }

    public static void func_190641_b(DataFixer dataconvertermanager) {
        EntityLiving.func_189752_a(dataconvertermanager, EntityVindicator.class);
    }

    @Override
    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.field_70714_bg.func_75776_a(8, new EntityAIWander(this, 0.6D));
        this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.field_70714_bg.func_75776_a(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[] { EntityVindicator.class}));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
        this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
        this.field_70715_bh.func_75776_a(4, new EntityVindicator.a(this));
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3499999940395355D);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(12.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(24.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
    }

    @Override
    protected ResourceLocation func_184647_J() {
        return LootTableList.field_191186_av;
    }

    public void func_190636_a(boolean flag) {
        this.func_193079_a(1, flag);
    }

    @Override
    public void func_70014_b(NBTTagCompound nbttagcompound) {
        super.func_70014_b(nbttagcompound);
        if (this.field_190643_b) {
            nbttagcompound.func_74757_a("Johnny", true);
        }

    }

    @Override
    public void func_70037_a(NBTTagCompound nbttagcompound) {
        super.func_70037_a(nbttagcompound);
        if (nbttagcompound.func_150297_b("Johnny", 99)) {
            this.field_190643_b = nbttagcompound.func_74767_n("Johnny");
        }

    }

    @Override
    @Nullable
    public IEntityLivingData func_180482_a(DifficultyInstance difficultydamagescaler, @Nullable IEntityLivingData groupdataentity) {
        IEntityLivingData groupdataentity1 = super.func_180482_a(difficultydamagescaler, groupdataentity);

        this.func_180481_a(difficultydamagescaler);
        this.func_180483_b(difficultydamagescaler);
        return groupdataentity1;
    }

    @Override
    protected void func_180481_a(DifficultyInstance difficultydamagescaler) {
        this.func_184201_a(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.field_151036_c));
    }

    @Override
    protected void func_70619_bc() {
        super.func_70619_bc();
        this.func_190636_a(this.func_70638_az() != null);
    }

    @Override
    public boolean func_184191_r(Entity entity) {
        return super.func_184191_r(entity) ? true : (entity instanceof EntityLivingBase && ((EntityLivingBase) entity).func_70668_bt() == EnumCreatureAttribute.ILLAGER ? this.func_96124_cp() == null && entity.func_96124_cp() == null : false);
    }

    @Override
    public void func_96094_a(String s) {
        super.func_96094_a(s);
        if (!this.field_190643_b && "Johnny".equals(s)) {
            this.field_190643_b = true;
        }

    }

    @Override
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_191268_hm;
    }

    @Override
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_191269_hn;
    }

    @Override
    protected SoundEvent func_184601_bQ(DamageSource damagesource) {
        return SoundEvents.field_191270_ho;
    }

    static class a extends EntityAINearestAttackableTarget<EntityLivingBase> {

        public a(EntityVindicator entityvindicator) {
            super(entityvindicator, EntityLivingBase.class, 0, true, true, EntityVindicator.field_190644_c);
        }

        @Override
        public boolean func_75250_a() {
            return ((EntityVindicator) this.field_75299_d).field_190643_b && super.func_75250_a();
        }
    }
}
