package net.minecraft.item;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.SoundEvents;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;


public class ItemEgg extends Item {

    public ItemEgg() {
        this.field_77777_bU = 16;
        this.func_77637_a(CreativeTabs.field_78035_l);
    }

    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.func_184586_b(enumhand);

        if (!entityhuman.field_71075_bZ.field_75098_d) {
            itemstack.func_190918_g(1);
        }

        world.func_184148_a((EntityPlayer) null, entityhuman.field_70165_t, entityhuman.field_70163_u, entityhuman.field_70161_v, SoundEvents.field_187511_aA, SoundCategory.PLAYERS, 0.5F, 0.4F / (ItemEgg.field_77697_d.nextFloat() * 0.4F + 0.8F));
        if (!world.field_72995_K) {
            EntityEgg entityegg = new EntityEgg(world, entityhuman);

            entityegg.func_184538_a(entityhuman, entityhuman.field_70125_A, entityhuman.field_70177_z, 0.0F, 1.5F, 1.0F);
            world.func_72838_d(entityegg);
        }

        entityhuman.func_71029_a(StatList.func_188057_b((Item) this));
        return new ActionResult(EnumActionResult.SUCCESS, itemstack);
    }
}
