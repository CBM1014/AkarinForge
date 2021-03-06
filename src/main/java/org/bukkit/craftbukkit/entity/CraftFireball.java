package org.bukkit.craftbukkit.entity;

import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.math.MathHelper;

import org.apache.commons.lang.Validate;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class CraftFireball extends AbstractProjectile implements Fireball {
    public CraftFireball(CraftServer server, EntityFireball entity) {
        super(server, entity);
    }

    public float getYield() {
        return getHandle().bukkitYield;
    }

    public boolean isIncendiary() {
        return getHandle().isIncendiary;
    }

    public void setIsIncendiary(boolean isIncendiary) {
        getHandle().isIncendiary = isIncendiary;
    }

    public void setYield(float yield) {
        getHandle().bukkitYield = yield;
    }

    public ProjectileSource getShooter() {
        return getHandle().projectileSource;
    }

    public void setShooter(ProjectileSource shooter) {
        if (shooter instanceof CraftLivingEntity) {
            getHandle().field_70235_a = ((CraftLivingEntity) shooter).getHandle();
        } else {
            getHandle().field_70235_a = null;
        }
        getHandle().projectileSource = shooter;
    }

    public Vector getDirection() {
        return new Vector(getHandle().field_70232_b, getHandle().field_70233_c, getHandle().field_70230_d);
    }

    public void setDirection(Vector direction) {
        Validate.notNull(direction, "Direction can not be null");
        double x = direction.getX();
        double y = direction.getY();
        double z = direction.getZ();
        double magnitude = (double) MathHelper.func_76133_a(x * x + y * y + z * z);
        getHandle().field_70232_b = x / magnitude;
        getHandle().field_70233_c = y / magnitude;
        getHandle().field_70230_d = z / magnitude;
    }

    @Override
    public EntityFireball getHandle() {
        return (EntityFireball) entity;
    }

    @Override
    public String toString() {
        return "CraftFireball";
    }

    public EntityType getType() {
        return EntityType.UNKNOWN;
    }
}
