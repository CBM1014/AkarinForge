package net.minecraft.enchantment;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class EnchantmentThorns extends Enchantment {

    public EnchantmentThorns(Enchantment.Rarity enchantment_rarity, EntityEquipmentSlot... aenumitemslot) {
        super(enchantment_rarity, EnumEnchantmentType.ARMOR_CHEST, aenumitemslot);
        this.func_77322_b("thorns");
    }

    public int func_77321_a(int i) {
        return 10 + 20 * (i - 1);
    }

    public int func_77317_b(int i) {
        return super.func_77321_a(i) + 50;
    }

    public int func_77325_b() {
        return 3;
    }

    public boolean func_92089_a(ItemStack itemstack) {
        return itemstack.func_77973_b() instanceof ItemArmor ? true : super.func_92089_a(itemstack);
    }

    public void func_151367_b(EntityLivingBase entityliving, Entity entity, int i) {
        Random random = entityliving.func_70681_au();
        ItemStack itemstack = EnchantmentHelper.func_92099_a(Enchantments.field_92091_k, entityliving);

        if (entity != null && func_92094_a(i, random)) { // CraftBukkit
            if (entity != null) {
                entity.func_70097_a(DamageSource.func_92087_a(entityliving), (float) func_92095_b(i, random));
            }

            if (!itemstack.func_190926_b()) {
                damageArmor(itemstack, 3, entityliving);
            }
        } else if (!itemstack.func_190926_b()) {
            damageArmor(itemstack, 1, entityliving);
        }

    }
    
    private void damageArmor(ItemStack stack, int amount, EntityLivingBase entity)
    {
        int slot = -1;
        int x = 0;
        for (ItemStack i : entity.func_184193_aE())
        {
            if (i == stack){
                slot = x;
                break;
            }
            x++;
        }
        if (slot == -1 || !(stack.func_77973_b() instanceof net.minecraftforge.common.ISpecialArmor))
        {
            stack.func_77972_a(1, entity);
            return;
        }
        net.minecraftforge.common.ISpecialArmor armor = (net.minecraftforge.common.ISpecialArmor)stack.func_77973_b();
        armor.damageArmor(entity, stack, DamageSource.func_92087_a(entity), amount, slot);
    }

    public static boolean func_92094_a(int i, Random random) {
        return i <= 0 ? false : random.nextFloat() < 0.15F * (float) i;
    }

    public static int func_92095_b(int i, Random random) {
        return i > 10 ? i - 10 : 1 + random.nextInt(4);
    }
}
