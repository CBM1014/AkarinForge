package net.minecraft.entity.passive;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


public abstract class EntityAmbientCreature extends EntityLiving implements IAnimals {

    public EntityAmbientCreature(World world) {
        super(world);
    }

    public boolean func_184652_a(EntityPlayer entityhuman) {
        return false;
    }
}
