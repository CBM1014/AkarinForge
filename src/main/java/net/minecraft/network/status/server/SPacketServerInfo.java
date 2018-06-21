package net.minecraft.network.status.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;

public class SPacketServerInfo implements Packet<INetHandlerStatusClient> {

    public static final Gson field_149297_a = (new GsonBuilder()).registerTypeAdapter(ServerStatusResponse.Version.class, new ServerStatusResponse.Version.Serializer()).registerTypeAdapter(ServerStatusResponse.Players.class, new ServerStatusResponse.Players.Serializer()).registerTypeAdapter(ServerStatusResponse.class, new ServerStatusResponse.Serializer()).registerTypeHierarchyAdapter(ITextComponent.class, new ITextComponent.Serializer()).registerTypeHierarchyAdapter(Style.class, new Style.Serializer()).registerTypeAdapterFactory(new EnumTypeAdapterFactory()).create();
    private ServerStatusResponse field_149296_b;

    public SPacketServerInfo() {}

    public SPacketServerInfo(ServerStatusResponse serverping) {
        this.field_149296_b = serverping;
    }

    public void func_148837_a(PacketBuffer packetdataserializer) throws IOException {
        this.field_149296_b = (ServerStatusResponse) JsonUtils.func_188178_a(SPacketServerInfo.field_149297_a, packetdataserializer.func_150789_c(32767), ServerStatusResponse.class);
    }

    public void func_148840_b(PacketBuffer packetdataserializer) throws IOException {
        packetdataserializer.func_180714_a(SPacketServerInfo.field_149297_a.toJson(this.field_149296_b));
    }

    public void func_148833_a(INetHandlerStatusClient packetstatusoutlistener) {
        packetstatusoutlistener.func_147397_a(this);
    }
}
