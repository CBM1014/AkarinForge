package net.minecraft.world.storage.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;

import net.minecraft.server.LootItemConditionKilledByPlayer.a;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;

public class KilledByPlayer implements LootCondition {

    private final boolean inverse;

    public KilledByPlayer(boolean flag) {
        this.inverse = flag;
    }

    public boolean testCondition(Random random, LootContext loottableinfo) {
        boolean flag = loottableinfo.getKillerPlayer() != null;

        return flag == !this.inverse;
    }

    public static class a extends LootItemCondition.a<KilledByPlayer> {

        protected a() {
            super(new ResourceLocation("killed_by_player"), KilledByPlayer.class);
        }

        public void a(JsonObject jsonobject, KilledByPlayer lootitemconditionkilledbyplayer, JsonSerializationContext jsonserializationcontext) {
            jsonobject.addProperty("inverse", Boolean.valueOf(lootitemconditionkilledbyplayer.inverse));
        }

        public KilledByPlayer a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            return new KilledByPlayer(JsonUtils.getBoolean(jsonobject, "inverse", false));
        }

        public LootCondition b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            return this.a(jsonobject, jsondeserializationcontext);
        }
    }
}