package net.minecraft.util.text;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

public class Style {

    private Style field_150249_a;
    private TextFormatting field_150247_b;
    private Boolean field_150248_c;
    private Boolean field_150245_d;
    private Boolean field_150246_e;
    private Boolean field_150243_f;
    private Boolean field_150244_g;
    private ClickEvent field_150251_h;
    private HoverEvent field_150252_i;
    private String field_179990_j;
    private static final Style field_150250_j = new Style() {
        @Override
        @Nullable
        public TextFormatting func_150215_a() {
            return null;
        }

        @Override
        public boolean func_150223_b() {
            return false;
        }

        @Override
        public boolean func_150242_c() {
            return false;
        }

        @Override
        public boolean func_150236_d() {
            return false;
        }

        @Override
        public boolean func_150234_e() {
            return false;
        }

        @Override
        public boolean func_150233_f() {
            return false;
        }

        @Override
        @Nullable
        public ClickEvent func_150235_h() {
            return null;
        }

        @Override
        @Nullable
        public HoverEvent func_150210_i() {
            return null;
        }

        @Override
        @Nullable
        public String func_179986_j() {
            return null;
        }

        @Override
        public Style func_150238_a(TextFormatting enumchatformat) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Style func_150227_a(Boolean obool) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Style func_150217_b(Boolean obool) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Style func_150225_c(Boolean obool) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Style func_150228_d(Boolean obool) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Style func_150237_e(Boolean obool) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Style func_150241_a(ClickEvent chatclickable) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Style func_150209_a(HoverEvent chathoverable) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Style func_150221_a(Style chatmodifier) {
            throw new UnsupportedOperationException();
        }

        @Override
        public String toString() {
            return "Style.ROOT";
        }

        @Override
        public Style clone() {
            return this;
        }

        @Override
        public Style func_150206_m() {
            return this;
        }
    };

    public Style() {}

    @Nullable
    public TextFormatting func_150215_a() {
        return this.field_150247_b == null ? this.func_150224_n().func_150215_a() : this.field_150247_b;
    }

    public boolean func_150223_b() {
        return this.field_150248_c == null ? this.func_150224_n().func_150223_b() : this.field_150248_c.booleanValue();
    }

    public boolean func_150242_c() {
        return this.field_150245_d == null ? this.func_150224_n().func_150242_c() : this.field_150245_d.booleanValue();
    }

    public boolean func_150236_d() {
        return this.field_150243_f == null ? this.func_150224_n().func_150236_d() : this.field_150243_f.booleanValue();
    }

    public boolean func_150234_e() {
        return this.field_150246_e == null ? this.func_150224_n().func_150234_e() : this.field_150246_e.booleanValue();
    }

    public boolean func_150233_f() {
        return this.field_150244_g == null ? this.func_150224_n().func_150233_f() : this.field_150244_g.booleanValue();
    }

    public boolean func_150229_g() {
        return this.field_150248_c == null && this.field_150245_d == null && this.field_150243_f == null && this.field_150246_e == null && this.field_150244_g == null && this.field_150247_b == null && this.field_150251_h == null && this.field_150252_i == null && this.field_179990_j == null;
    }

    @Nullable
    public ClickEvent func_150235_h() {
        return this.field_150251_h == null ? this.func_150224_n().func_150235_h() : this.field_150251_h;
    }

    @Nullable
    public HoverEvent func_150210_i() {
        return this.field_150252_i == null ? this.func_150224_n().func_150210_i() : this.field_150252_i;
    }

    @Nullable
    public String func_179986_j() {
        return this.field_179990_j == null ? this.func_150224_n().func_179986_j() : this.field_179990_j;
    }

    public Style func_150238_a(TextFormatting enumchatformat) {
        this.field_150247_b = enumchatformat;
        return this;
    }

    public Style func_150227_a(Boolean obool) {
        this.field_150248_c = obool;
        return this;
    }

    public Style func_150217_b(Boolean obool) {
        this.field_150245_d = obool;
        return this;
    }

    public Style func_150225_c(Boolean obool) {
        this.field_150243_f = obool;
        return this;
    }

    public Style func_150228_d(Boolean obool) {
        this.field_150246_e = obool;
        return this;
    }

    public Style func_150237_e(Boolean obool) {
        this.field_150244_g = obool;
        return this;
    }

    public Style func_150241_a(ClickEvent chatclickable) {
        this.field_150251_h = chatclickable;
        return this;
    }

    public Style func_150209_a(HoverEvent chathoverable) {
        this.field_150252_i = chathoverable;
        return this;
    }

    public Style func_179989_a(String s) {
        this.field_179990_j = s;
        return this;
    }

    public Style func_150221_a(Style chatmodifier) {
        this.field_150249_a = chatmodifier;
        return this;
    }

    private Style func_150224_n() {
        return this.field_150249_a == null ? Style.field_150250_j : this.field_150249_a;
    }

    @Override
    public String toString() {
        return "Style{hasParent=" + (this.field_150249_a != null) + ", color=" + this.field_150247_b + ", bold=" + this.field_150248_c + ", italic=" + this.field_150245_d + ", underlined=" + this.field_150246_e + ", obfuscated=" + this.field_150244_g + ", clickEvent=" + this.func_150235_h() + ", hoverEvent=" + this.func_150210_i() + ", insertion=" + this.func_179986_j() + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof Style)) {
            return false;
        } else {
            Style chatmodifier = (Style) object;
            boolean flag;

            if (this.func_150223_b() == chatmodifier.func_150223_b() && this.func_150215_a() == chatmodifier.func_150215_a() && this.func_150242_c() == chatmodifier.func_150242_c() && this.func_150233_f() == chatmodifier.func_150233_f() && this.func_150236_d() == chatmodifier.func_150236_d() && this.func_150234_e() == chatmodifier.func_150234_e()) {
                label65: {
                    if (this.func_150235_h() != null) {
                        if (!this.func_150235_h().equals(chatmodifier.func_150235_h())) {
                            break label65;
                        }
                    } else if (chatmodifier.func_150235_h() != null) {
                        break label65;
                    }

                    if (this.func_150210_i() != null) {
                        if (!this.func_150210_i().equals(chatmodifier.func_150210_i())) {
                            break label65;
                        }
                    } else if (chatmodifier.func_150210_i() != null) {
                        break label65;
                    }

                    if (this.func_179986_j() != null) {
                        if (!this.func_179986_j().equals(chatmodifier.func_179986_j())) {
                            break label65;
                        }
                    } else if (chatmodifier.func_179986_j() != null) {
                        break label65;
                    }

                    flag = true;
                    return flag;
                }
            }

            flag = false;
            return flag;
        }
    }

    @Override
    public int hashCode() {
        // CraftBukkit start - fix npe
        int i = field_150247_b == null ? 0 : this.field_150247_b.hashCode();

        i = 31 * i + (field_150248_c == null ? 0 : this.field_150248_c.hashCode());
        i = 31 * i + (field_150245_d == null ? 0 : this.field_150245_d.hashCode());
        i = 31 * i + (field_150246_e == null ? 0 : this.field_150246_e.hashCode());
        i = 31 * i + (field_150243_f == null ? 0 : this.field_150243_f.hashCode());
        i = 31 * i + (field_150244_g == null ? 0 : this.field_150244_g.hashCode());
        i = 31 * i + (field_150251_h == null ? 0 : this.field_150251_h.hashCode());
        i = 31 * i + (this.field_150252_i == null ? 0 : this.field_150252_i.hashCode());
        i = 31 * i + (field_179990_j == null ? 0 : this.field_179990_j.hashCode());
        // CraftBukkit end
        return i;
    }

    public Style func_150232_l() {
        Style chatmodifier = new Style();

        chatmodifier.field_150248_c = this.field_150248_c;
        chatmodifier.field_150245_d = this.field_150245_d;
        chatmodifier.field_150243_f = this.field_150243_f;
        chatmodifier.field_150246_e = this.field_150246_e;
        chatmodifier.field_150244_g = this.field_150244_g;
        chatmodifier.field_150247_b = this.field_150247_b;
        chatmodifier.field_150251_h = this.field_150251_h;
        chatmodifier.field_150252_i = this.field_150252_i;
        chatmodifier.field_150249_a = this.field_150249_a;
        chatmodifier.field_179990_j = this.field_179990_j;
        return chatmodifier;
    }

    public Style func_150206_m() {
        Style chatmodifier = new Style();

        chatmodifier.func_150227_a(Boolean.valueOf(this.func_150223_b()));
        chatmodifier.func_150217_b(Boolean.valueOf(this.func_150242_c()));
        chatmodifier.func_150225_c(Boolean.valueOf(this.func_150236_d()));
        chatmodifier.func_150228_d(Boolean.valueOf(this.func_150234_e()));
        chatmodifier.func_150237_e(Boolean.valueOf(this.func_150233_f()));
        chatmodifier.func_150238_a(this.func_150215_a());
        chatmodifier.func_150241_a(this.func_150235_h());
        chatmodifier.func_150209_a(this.func_150210_i());
        chatmodifier.func_179989_a(this.func_179986_j());
        return chatmodifier;
    }

    public static class Serializer implements JsonDeserializer<Style>, JsonSerializer<Style> {

        public Serializer() {}

        @Override
        @Nullable
        public Style deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            if (jsonelement.isJsonObject()) {
                Style chatmodifier = new Style();
                JsonObject jsonobject = jsonelement.getAsJsonObject();

                if (jsonobject == null) {
                    return null;
                } else {
                    if (jsonobject.has("bold")) {
                        chatmodifier.field_150248_c = Boolean.valueOf(jsonobject.get("bold").getAsBoolean());
                    }

                    if (jsonobject.has("italic")) {
                        chatmodifier.field_150245_d = Boolean.valueOf(jsonobject.get("italic").getAsBoolean());
                    }

                    if (jsonobject.has("underlined")) {
                        chatmodifier.field_150246_e = Boolean.valueOf(jsonobject.get("underlined").getAsBoolean());
                    }

                    if (jsonobject.has("strikethrough")) {
                        chatmodifier.field_150243_f = Boolean.valueOf(jsonobject.get("strikethrough").getAsBoolean());
                    }

                    if (jsonobject.has("obfuscated")) {
                        chatmodifier.field_150244_g = Boolean.valueOf(jsonobject.get("obfuscated").getAsBoolean());
                    }

                    if (jsonobject.has("color")) {
                        chatmodifier.field_150247_b = (TextFormatting) jsondeserializationcontext.deserialize(jsonobject.get("color"), TextFormatting.class);
                    }

                    if (jsonobject.has("insertion")) {
                        chatmodifier.field_179990_j = jsonobject.get("insertion").getAsString();
                    }

                    JsonObject jsonobject1;
                    JsonPrimitive jsonprimitive;

                    if (jsonobject.has("clickEvent")) {
                        jsonobject1 = jsonobject.getAsJsonObject("clickEvent");
                        if (jsonobject1 != null) {
                            jsonprimitive = jsonobject1.getAsJsonPrimitive("action");
                            ClickEvent.Action chatclickable_enumclickaction = jsonprimitive == null ? null : ClickEvent.Action.func_150672_a(jsonprimitive.getAsString());
                            JsonPrimitive jsonprimitive1 = jsonobject1.getAsJsonPrimitive("value");
                            String s = jsonprimitive1 == null ? null : jsonprimitive1.getAsString();

                            if (chatclickable_enumclickaction != null && s != null && chatclickable_enumclickaction.func_150674_a()) {
                                chatmodifier.field_150251_h = new ClickEvent(chatclickable_enumclickaction, s);
                            }
                        }
                    }

                    if (jsonobject.has("hoverEvent")) {
                        jsonobject1 = jsonobject.getAsJsonObject("hoverEvent");
                        if (jsonobject1 != null) {
                            jsonprimitive = jsonobject1.getAsJsonPrimitive("action");
                            HoverEvent.Action chathoverable_enumhoveraction = jsonprimitive == null ? null : HoverEvent.Action.func_150684_a(jsonprimitive.getAsString());
                            ITextComponent ichatbasecomponent = (ITextComponent) jsondeserializationcontext.deserialize(jsonobject1.get("value"), ITextComponent.class);

                            if (chathoverable_enumhoveraction != null && ichatbasecomponent != null && chathoverable_enumhoveraction.func_150686_a()) {
                                chatmodifier.field_150252_i = new HoverEvent(chathoverable_enumhoveraction, ichatbasecomponent);
                            }
                        }
                    }

                    return chatmodifier;
                }
            } else {
                return null;
            }
        }

        @Override
        @Nullable
        public JsonElement serialize(Style chatmodifier, Type type, JsonSerializationContext jsonserializationcontext) {
            if (chatmodifier.func_150229_g()) {
                return null;
            } else {
                JsonObject jsonobject = new JsonObject();

                if (chatmodifier.field_150248_c != null) {
                    jsonobject.addProperty("bold", chatmodifier.field_150248_c);
                }

                if (chatmodifier.field_150245_d != null) {
                    jsonobject.addProperty("italic", chatmodifier.field_150245_d);
                }

                if (chatmodifier.field_150246_e != null) {
                    jsonobject.addProperty("underlined", chatmodifier.field_150246_e);
                }

                if (chatmodifier.field_150243_f != null) {
                    jsonobject.addProperty("strikethrough", chatmodifier.field_150243_f);
                }

                if (chatmodifier.field_150244_g != null) {
                    jsonobject.addProperty("obfuscated", chatmodifier.field_150244_g);
                }

                if (chatmodifier.field_150247_b != null) {
                    jsonobject.add("color", jsonserializationcontext.serialize(chatmodifier.field_150247_b));
                }

                if (chatmodifier.field_179990_j != null) {
                    jsonobject.add("insertion", jsonserializationcontext.serialize(chatmodifier.field_179990_j));
                }

                JsonObject jsonobject1;

                if (chatmodifier.field_150251_h != null) {
                    jsonobject1 = new JsonObject();
                    jsonobject1.addProperty("action", chatmodifier.field_150251_h.func_150669_a().func_150673_b());
                    jsonobject1.addProperty("value", chatmodifier.field_150251_h.func_150668_b());
                    jsonobject.add("clickEvent", jsonobject1);
                }

                if (chatmodifier.field_150252_i != null) {
                    jsonobject1 = new JsonObject();
                    jsonobject1.addProperty("action", chatmodifier.field_150252_i.func_150701_a().func_150685_b());
                    jsonobject1.add("value", jsonserializationcontext.serialize(chatmodifier.field_150252_i.func_150702_b()));
                    jsonobject.add("hoverEvent", jsonobject1);
                }

                return jsonobject;
            }
        }
    }
    
    public String func_150218_j()
    {
        if (this.func_150229_g())
        {
            return this.field_150249_a != null ? this.field_150249_a.func_150218_j() : "";
        }
        else
        {
            StringBuilder stringbuilder = new StringBuilder();

            if (this.func_150215_a() != null)
            {
                stringbuilder.append((Object)this.func_150215_a());
            }

            if (this.func_150223_b())
            {
                stringbuilder.append((Object)TextFormatting.BOLD);
            }

            if (this.func_150242_c())
            {
                stringbuilder.append((Object)TextFormatting.ITALIC);
            }

            if (this.func_150234_e())
            {
                stringbuilder.append((Object)TextFormatting.UNDERLINE);
            }

            if (this.func_150233_f())
            {
                stringbuilder.append((Object)TextFormatting.OBFUSCATED);
            }

            if (this.func_150236_d())
            {
                stringbuilder.append((Object)TextFormatting.STRIKETHROUGH);
            }

            return stringbuilder.toString();
        }
    }
}
