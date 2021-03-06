package net.minecraft.entity.passive;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class EntityMooshroom extends EntityCow {

    public EntityMooshroom(World world) {
        super(world);
        this.func_70105_a(0.9F, 1.4F);
        this.field_175506_bl = Blocks.field_150391_bh;
    }

    public static void func_189791_c(DataFixer dataconvertermanager) {
        EntityLiving.func_189752_a(dataconvertermanager, EntityMooshroom.class);
    }

    @Override
    public boolean func_184645_a(EntityPlayer entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.func_184586_b(enumhand);

        if (itemstack.func_77973_b() == Items.field_151054_z && this.func_70874_b() >= 0 && !entityhuman.field_71075_bZ.field_75098_d) {
            itemstack.func_190918_g(1);
            if (itemstack.func_190926_b()) {
                entityhuman.func_184611_a(enumhand, new ItemStack(Items.field_151009_A));
            } else if (!entityhuman.field_71071_by.func_70441_a(new ItemStack(Items.field_151009_A))) {
                entityhuman.func_71019_a(new ItemStack(Items.field_151009_A), false);
            }

            return true;
        } else if (itemstack.func_77973_b() == Items.field_151097_aZ && this.func_70874_b() >= 0) {
            // CraftBukkit start
            PlayerShearEntityEvent event = new PlayerShearEntityEvent((org.bukkit.entity.Player) entityhuman.getBukkitEntity(), this.getBukkitEntity());
            this.field_70170_p.getServer().getPluginManager().callEvent(event);

            if (event.isCancelled()) {
                return false;
            }
            // CraftBukkit end
            this.func_70106_y();
            this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_LARGE, this.field_70165_t, this.field_70163_u + this.field_70131_O / 2.0F, this.field_70161_v, 0.0D, 0.0D, 0.0D, new int[0]);
            if (!this.field_70170_p.field_72995_K) {
                EntityCow entitycow = new EntityCow(this.field_70170_p);

                entitycow.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
                entitycow.func_70606_j(this.func_110143_aJ());
                entitycow.field_70761_aq = this.field_70761_aq;
                if (this.func_145818_k_()) {
                    entitycow.func_96094_a(this.func_95999_t());
                }

                this.field_70170_p.func_72838_d(entitycow);

                for (int i = 0; i < 5; ++i) {
                    this.field_70170_p.func_72838_d(new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u + this.field_70131_O, this.field_70161_v, new ItemStack(Blocks.field_150337_Q)));
                }

                itemstack.func_77972_a(1, entityhuman);
                this.func_184185_a(SoundEvents.field_187784_dt, 1.0F, 1.0F);
            }

            return true;
        } else {
            return super.func_184645_a(entityhuman, enumhand);
        }
    }

    @Override
    public EntityMooshroom func_90011_a(EntityAgeable entityageable) {
        return new EntityMooshroom(this.field_70170_p);
    }

    @Override
    @Nullable
    protected ResourceLocation func_184647_J() {
        return LootTableList.field_186400_H;
    }
}
