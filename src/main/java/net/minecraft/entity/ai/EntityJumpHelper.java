package net.minecraft.entity.ai;
import net.minecraft.entity.EntityLiving;


public class EntityJumpHelper {

    private final EntityLiving field_75663_a;
    protected boolean field_75662_b;

    public EntityJumpHelper(EntityLiving entityinsentient) {
        this.field_75663_a = entityinsentient;
    }

    public void func_75660_a() {
        this.field_75662_b = true;
    }

    public void jumpIfSet() { this.func_75661_b(); } // Paper - OBFHELPER
    public void func_75661_b() {
        this.field_75663_a.func_70637_d(this.field_75662_b);
        this.field_75662_b = false;
    }
}
