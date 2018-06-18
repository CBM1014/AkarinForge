package net.minecraft.inventory;

import java.util.List;
import java.util.Random;

import java.util.Map;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.inventory.CraftInventoryEnchanting;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

// CraftBukkit start
import java.util.Collections;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.craftbukkit.inventory.CraftInventoryEnchanting;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.entity.Player;
// CraftBukkit end

public class ContainerEnchantment extends Container {

    public IInventory tableInventory = new InventoryBasic("Enchant", true, 2) {
        public int getInventoryStackLimit() {
            return 64;
        }

        public void markDirty() {
            super.markDirty();
            ContainerEnchantment.this.onCraftMatrixChanged((IInventory) this);
        }

        // CraftBukkit start
        @Override
        public Location getLocation() {
            return new org.bukkit.Location(worldPointer.getWorld(), position.getX(), position.getY(), position.getZ());
        }
        // CraftBukkit end
    };
    public World worldPointer;
    private final BlockPos position;
    private final Random rand = new Random();
    public int xpSeed;
    public int[] enchantLevels = new int[3];
    public int[] enchantClue = new int[] { -1, -1, -1};
    public int[] worldClue = new int[] { -1, -1, -1};
    // CraftBukkit start
    private CraftInventoryView bukkitEntity = null;
    private Player player;
    // CraftBukkit end

    public ContainerEnchantment(InventoryPlayer playerinventory, World world, BlockPos blockposition) {
        this.worldPointer = world;
        this.position = blockposition;
        this.xpSeed = playerinventory.player.getXPSeed();
        this.addSlotToContainer(new Slot(this.tableInventory, 0, 15, 47) {
            public boolean isItemValid(ItemStack itemstack) {
                return true;
            }

            public int getSlotStackLimit() {
                return 1;
            }
        });
        this.addSlotToContainer(new Slot(this.tableInventory, 1, 35, 47) {
            public boolean isItemValid(ItemStack itemstack) {
                return itemstack.getItem() == Items.DYE && EnumDyeColor.byDyeDamage(itemstack.getMetadata()) == EnumDyeColor.BLUE;
            }
        });

        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(playerinventory, i, 8 + i * 18, 142));
        }

        // CraftBukkit start
        player = (Player) playerinventory.player.getBukkitEntity();
        // CraftBukkit end
    }

    protected void broadcastData(IContainerListener icrafting) {
        icrafting.sendWindowProperty(this, 0, this.enchantLevels[0]);
        icrafting.sendWindowProperty(this, 1, this.enchantLevels[1]);
        icrafting.sendWindowProperty(this, 2, this.enchantLevels[2]);
        icrafting.sendWindowProperty(this, 3, this.xpSeed & -16);
        icrafting.sendWindowProperty(this, 4, this.enchantClue[0]);
        icrafting.sendWindowProperty(this, 5, this.enchantClue[1]);
        icrafting.sendWindowProperty(this, 6, this.enchantClue[2]);
        icrafting.sendWindowProperty(this, 7, this.worldClue[0]);
        icrafting.sendWindowProperty(this, 8, this.worldClue[1]);
        icrafting.sendWindowProperty(this, 9, this.worldClue[2]);
    }

    public void addListener(IContainerListener icrafting) {
        super.addListener(icrafting);
        this.broadcastData(icrafting);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i) {
            IContainerListener icrafting = (IContainerListener) this.listeners.get(i);

            this.broadcastData(icrafting);
        }

    }

    public void onCraftMatrixChanged(IInventory iinventory) {
        if (iinventory == this.tableInventory) {
            ItemStack itemstack = iinventory.getStackInSlot(0);
            int i;

            if (!itemstack.isEmpty()) { // CraftBukkit - relax condition
                if (!this.worldPointer.isRemote) {
                    i = 0;

                    int j;

                    for (j = -1; j <= 1; ++j) {
                        for (int k = -1; k <= 1; ++k) {
                            if ((j != 0 || k != 0) && this.worldPointer.isAirBlock(this.position.add(k, 0, j)) && this.worldPointer.isAirBlock(this.position.add(k, 1, j))) {
                                if (this.worldPointer.getBlockState(this.position.add(k * 2, 0, j * 2)).getBlock() == Blocks.BOOKSHELF) {
                                    ++i;
                                }

                                if (this.worldPointer.getBlockState(this.position.add(k * 2, 1, j * 2)).getBlock() == Blocks.BOOKSHELF) {
                                    ++i;
                                }

                                if (k != 0 && j != 0) {
                                    if (this.worldPointer.getBlockState(this.position.add(k * 2, 0, j)).getBlock() == Blocks.BOOKSHELF) {
                                        ++i;
                                    }

                                    if (this.worldPointer.getBlockState(this.position.add(k * 2, 1, j)).getBlock() == Blocks.BOOKSHELF) {
                                        ++i;
                                    }

                                    if (this.worldPointer.getBlockState(this.position.add(k, 0, j * 2)).getBlock() == Blocks.BOOKSHELF) {
                                        ++i;
                                    }

                                    if (this.worldPointer.getBlockState(this.position.add(k, 1, j * 2)).getBlock() == Blocks.BOOKSHELF) {
                                        ++i;
                                    }
                                }
                            }
                        }
                    }

                    this.rand.setSeed((long) this.xpSeed);

                    for (j = 0; j < 3; ++j) {
                        this.enchantLevels[j] = EnchantmentHelper.calcItemStackEnchantability(this.rand, j, i, itemstack);
                        this.enchantClue[j] = -1;
                        this.worldClue[j] = -1;
                        if (this.enchantLevels[j] < j + 1) {
                            this.enchantLevels[j] = 0;
                        }
                    }

                    for (j = 0; j < 3; ++j) {
                        if (this.enchantLevels[j] > 0) {
                            List list = this.getEnchantmentList(itemstack, j, this.enchantLevels[j]);

                            if (list != null && !list.isEmpty()) {
                                EnchantmentData weightedrandomenchant = (EnchantmentData) list.get(this.rand.nextInt(list.size()));

                                this.enchantClue[j] = Enchantment.getEnchantmentID(weightedrandomenchant.enchantment);
                                this.worldClue[j] = weightedrandomenchant.enchantmentLevel;
                            }
                        }
                    }

                    // CraftBukkit start
                    CraftItemStack item = CraftItemStack.asCraftMirror(itemstack);
                    org.bukkit.enchantments.EnchantmentOffer[] offers = new EnchantmentOffer[3];
                    for (j = 0; j < 3; ++j) {
                        org.bukkit.enchantments.Enchantment enchantment = (this.enchantClue[j] >= 0) ? org.bukkit.enchantments.Enchantment.getById(this.enchantClue[j]) : null;
                        offers[j] = (enchantment != null) ? new EnchantmentOffer(enchantment, this.worldClue[j], this.enchantLevels[j]) : null;
                    }

                    PrepareItemEnchantEvent event = new PrepareItemEnchantEvent(player, this.getBukkitView(), this.worldPointer.getWorld().getBlockAt(position.getX(), position.getY(), position.getZ()), item, offers, i);
                    event.setCancelled(!itemstack.isItemEnchantable());
                    this.worldPointer.getServer().getPluginManager().callEvent(event);

                    if (event.isCancelled()) {
                        for (j = 0; j < 3; ++j) {
                            this.enchantLevels[j] = 0;
                            this.enchantClue[j] = -1;
                            this.worldClue[j] = -1;
                        }
                        return;
                    }

                    for (j = 0; j < 3; j++) {
                        EnchantmentOffer offer = event.getOffers()[j];
                        if (offer != null) {
                            this.enchantLevels[j] = offer.getCost();
                            this.enchantClue[j] = offer.getEnchantment().getId();
                            this.worldClue[j] = offer.getEnchantmentLevel();
                        } else {
                            this.enchantLevels[j] = 0;
                            this.enchantClue[j] = -1;
                            this.worldClue[j] = -1;
                        }
                    }
                    // CraftBukkit end

                    this.detectAndSendChanges();
                }
            } else {
                for (i = 0; i < 3; ++i) {
                    this.enchantLevels[i] = 0;
                    this.enchantClue[i] = -1;
                    this.worldClue[i] = -1;
                }
            }
        }

    }

    public boolean enchantItem(EntityPlayer entityhuman, int i) {
        ItemStack itemstack = this.tableInventory.getStackInSlot(0);
        ItemStack itemstack1 = this.tableInventory.getStackInSlot(1);
        int j = i + 1;

        if ((itemstack1.isEmpty() || itemstack1.getCount() < j) && !entityhuman.capabilities.isCreativeMode) {
            return false;
        } else if (this.enchantLevels[i] > 0 && !itemstack.isEmpty() && (entityhuman.experienceLevel >= j && entityhuman.experienceLevel >= this.enchantLevels[i] || entityhuman.capabilities.isCreativeMode)) {
            if (!this.worldPointer.isRemote) {
                List list = this.getEnchantmentList(itemstack, i, this.enchantLevels[i]);

                // CraftBukkit start
                if (true || !list.isEmpty()) {
                    // entityhuman.enchantDone(itemstack, j); // Moved down
                    boolean flag = itemstack.getItem() == Items.BOOK;
                    Map<org.bukkit.enchantments.Enchantment, Integer> enchants = new java.util.HashMap<org.bukkit.enchantments.Enchantment, Integer>();
                    for (Object obj : list) {
                        EnchantmentData instance = (EnchantmentData) obj;
                        enchants.put(org.bukkit.enchantments.Enchantment.getById(Enchantment.getEnchantmentID(instance.enchantment)), instance.enchantmentLevel);
                    }
                    CraftItemStack item = CraftItemStack.asCraftMirror(itemstack);

                    EnchantItemEvent event = new EnchantItemEvent((Player) entityhuman.getBukkitEntity(), this.getBukkitView(), this.worldPointer.getWorld().getBlockAt(position.getX(), position.getY(), position.getZ()), item, this.enchantLevels[i], enchants, i);
                    this.worldPointer.getServer().getPluginManager().callEvent(event);

                    int level = event.getExpLevelCost();
                    if (event.isCancelled() || (level > entityhuman.experienceLevel && !entityhuman.capabilities.isCreativeMode) || event.getEnchantsToAdd().isEmpty()) {
                        return false;
                    }

                    if (flag) {
                        itemstack = new ItemStack(Items.ENCHANTED_BOOK);
                        this.tableInventory.setInventorySlotContents(0, itemstack);
                    }

                    for (Map.Entry<org.bukkit.enchantments.Enchantment, Integer> entry : event.getEnchantsToAdd().entrySet()) {
                        try {
                            if (flag) {
                                int enchantId = entry.getKey().getId();
                                if (Enchantment.getEnchantmentByID(enchantId) == null) {
                                    continue;
                                }

                                EnchantmentData weightedrandomenchant = new EnchantmentData(Enchantment.getEnchantmentByID(enchantId), entry.getValue());
                                ItemEnchantedBook.addEnchantment(itemstack, weightedrandomenchant);
                            } else {
                                item.addUnsafeEnchantment(entry.getKey(), entry.getValue());
                            }
                        } catch (IllegalArgumentException e) {
                            /* Just swallow invalid enchantments */
                        }
                    }

                    entityhuman.onEnchant(itemstack, j);
                    // CraftBukkit end

                    // CraftBukkit - TODO: let plugins change this
                    if (!entityhuman.capabilities.isCreativeMode) {
                        itemstack1.shrink(j);
                        if (itemstack1.isEmpty()) {
                            this.tableInventory.setInventorySlotContents(1, ItemStack.EMPTY);
                        }
                    }

                    entityhuman.addStat(StatList.ITEM_ENCHANTED);
                    if (entityhuman instanceof EntityPlayerMP) {
                        CriteriaTriggers.ENCHANTED_ITEM.trigger((EntityPlayerMP) entityhuman, itemstack, j);
                    }

                    this.tableInventory.markDirty();
                    this.xpSeed = entityhuman.getXPSeed();
                    this.onCraftMatrixChanged(this.tableInventory);
                    this.worldPointer.playSound((EntityPlayer) null, this.position, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0F, this.worldPointer.rand.nextFloat() * 0.1F + 0.9F);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private List<EnchantmentData> getEnchantmentList(ItemStack itemstack, int i, int j) {
        this.rand.setSeed((long) (this.xpSeed + i));
        List list = EnchantmentHelper.buildEnchantmentList(this.rand, itemstack, j, false);

        if (itemstack.getItem() == Items.BOOK && list.size() > 1) {
            list.remove(this.rand.nextInt(list.size()));
        }

        return list;
    }

    public void onContainerClosed(EntityPlayer entityhuman) {
        super.onContainerClosed(entityhuman);
        // CraftBukkit Start - If an enchantable was opened from a null location, set the world to the player's world, preventing a crash
        if (this.worldPointer == null) {
            this.worldPointer = entityhuman.getEntityWorld();
        }
        // CraftBukkit end
        if (!this.worldPointer.isRemote) {
            this.clearContainer(entityhuman, entityhuman.world, this.tableInventory);
        }
    }

    public boolean canInteractWith(EntityPlayer entityhuman) {
        if (!this.checkReachable) return true; // CraftBukkit
        return this.worldPointer.getBlockState(this.position).getBlock() != Blocks.ENCHANTING_TABLE ? false : entityhuman.getDistanceSq((double) this.position.getX() + 0.5D, (double) this.position.getY() + 0.5D, (double) this.position.getZ() + 0.5D) <= 64.0D;
    }

    public ItemStack transferStackInSlot(EntityPlayer entityhuman, int i) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(i);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();

            itemstack = itemstack1.copy();
            if (i == 0) {
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (i == 1) {
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (itemstack1.getItem() == Items.DYE && EnumDyeColor.byDyeDamage(itemstack1.getMetadata()) == EnumDyeColor.BLUE) {
                if (!this.mergeItemStack(itemstack1, 1, 2, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (((Slot) this.inventorySlots.get(0)).getHasStack() || !((Slot) this.inventorySlots.get(0)).isItemValid(itemstack1)) {
                    return ItemStack.EMPTY;
                }

                if (itemstack1.hasTagCompound() && itemstack1.getCount() == 1) {
                    ((Slot) this.inventorySlots.get(0)).putStack(itemstack1.copy());
                    itemstack1.setCount(0);
                } else if (!itemstack1.isEmpty()) {
                    // Spigot start
                    ItemStack clone = itemstack1.copy();
                    clone.setCount(1);
                    ((Slot) this.inventorySlots.get(0)).putStack(clone);
                    // Spigot end
                    itemstack1.shrink(1);
                }
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(entityhuman, itemstack1);
        }

        return itemstack;
    }

    // CraftBukkit start
    @Override
    public CraftInventoryView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventoryEnchanting inventory = new CraftInventoryEnchanting(this.tableInventory);
        bukkitEntity = new CraftInventoryView(this.player, inventory, this);
        return bukkitEntity;
    }
    // CraftBukkit end
}