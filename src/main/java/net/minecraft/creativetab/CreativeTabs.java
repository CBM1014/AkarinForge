package net.minecraft.creativetab;

import javax.annotation.Nullable;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public abstract class CreativeTabs {

    public static CreativeTabs[] field_78032_a = new CreativeTabs[12];
    public static final CreativeTabs field_78030_b = new CreativeTabs(0, "buildingBlocks") {
    };
    public static final CreativeTabs field_78031_c = new CreativeTabs(1, "decorations") {
    };
    public static final CreativeTabs field_78028_d = new CreativeTabs(2, "redstone") {
    };
    public static final CreativeTabs field_78029_e = new CreativeTabs(3, "transportation") {
    };
    public static final CreativeTabs field_78026_f = new CreativeTabs(6, "misc") {
    };
    public static final CreativeTabs field_78027_g = (new CreativeTabs(5, "search") {
    }).func_78025_a("item_search.png");
    public static final CreativeTabs field_78039_h = new CreativeTabs(7, "food") {
    };
    public static final CreativeTabs field_78040_i = (new CreativeTabs(8, "tools") {
    }).func_111229_a(new EnumEnchantmentType[] { EnumEnchantmentType.ALL, EnumEnchantmentType.DIGGER, EnumEnchantmentType.FISHING_ROD, EnumEnchantmentType.BREAKABLE});
    public static final CreativeTabs field_78037_j = (new CreativeTabs(9, "combat") {
    }).func_111229_a(new EnumEnchantmentType[] { EnumEnchantmentType.ALL, EnumEnchantmentType.ARMOR, EnumEnchantmentType.ARMOR_FEET, EnumEnchantmentType.ARMOR_HEAD, EnumEnchantmentType.ARMOR_LEGS, EnumEnchantmentType.ARMOR_CHEST, EnumEnchantmentType.BOW, EnumEnchantmentType.WEAPON, EnumEnchantmentType.WEARABLE, EnumEnchantmentType.BREAKABLE});
    public static final CreativeTabs field_78038_k = new CreativeTabs(10, "brewing") {
    };
    public static final CreativeTabs field_78035_l = CreativeTabs.field_78026_f;
    public static final CreativeTabs field_192395_m = new CreativeTabs(4, "hotbar") {
    };
    public static final CreativeTabs field_78036_m = (new CreativeTabs(11, "inventory") {
    }).func_78025_a("inventory.png").func_78022_j().func_78014_h();
    private final int field_78033_n;
    private final String field_78034_o;
    private String field_78043_p = "items.png";
    private boolean field_78042_q = true;
    private boolean field_78041_r = true;
    private EnumEnchantmentType[] field_111230_s = new EnumEnchantmentType[0];
    private ItemStack field_151245_t;
    
    public CreativeTabs(String label) {
        this(getNextID(), label);
    }

    public CreativeTabs(int i, String s) {
        if (i >= field_78032_a.length) {
            CreativeTabs[] tmp = new CreativeTabs[i + 1];
            for (int x = 0; x < field_78032_a.length; x++)
            {
                tmp[x] = field_78032_a[x];
            }
            field_78032_a = tmp;
        }
        this.field_78033_n = i;
        this.field_78034_o = s;
        this.field_151245_t = ItemStack.field_190927_a;
        CreativeTabs.field_78032_a[i] = this;
    }

    public CreativeTabs func_78025_a(String s) {
        this.field_78043_p = s;
        return this;
    }

    public CreativeTabs func_78014_h() {
        this.field_78041_r = false;
        return this;
    }

    public CreativeTabs func_78022_j() {
        this.field_78042_q = false;
        return this;
    }

    public EnumEnchantmentType[] func_111225_m() {
        return this.field_111230_s;
    }

    public CreativeTabs func_111229_a(EnumEnchantmentType... aenchantmentslottype) {
        this.field_111230_s = aenchantmentslottype;
        return this;
    }

    public boolean func_111226_a(@Nullable EnumEnchantmentType enchantmentslottype) {
        if (enchantmentslottype != null) {
            EnumEnchantmentType[] aenchantmentslottype = this.field_111230_s;
            int i = aenchantmentslottype.length;

            for (int j = 0; j < i; ++j) {
                EnumEnchantmentType enchantmentslottype1 = aenchantmentslottype[j];

                if (enchantmentslottype1 == enchantmentslottype) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public int getTabPage()
    {
        if (field_78033_n > 11)
        {
            return ((field_78033_n - 12) / 10) + 1;
        }
        return 0;
    }

    public static int getNextID()
    {
        return field_78032_a.length;
    }

    /**
     * Determines if the search bar should be shown for this tab.
     *
     * @return True to show the bar
     */
    public boolean hasSearchBar()
    {
        return field_78033_n == CreativeTabs.field_78027_g.field_78033_n;
    }

    /**
     * Gets the width of the search bar of the creative tab, use this if your
     * creative tab name overflows together with a custom texture.
     *
     * @return The width of the search bar, 89 by default
     */
    public int getSearchbarWidth()
    {
        return 89;
    }
}
