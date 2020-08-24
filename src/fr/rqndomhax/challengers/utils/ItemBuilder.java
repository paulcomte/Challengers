/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.utils;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.UUID;

public class ItemBuilder {
    private final ItemStack is;

    public ItemBuilder(Material m) {
        this(m, 1);
    }

    public ItemBuilder(ItemStack is) {
        this.is = is;
    }

    public ItemBuilder(Material m, int amount) {
        this.is = new ItemStack(m, amount);
    }

    public ItemBuilder(Material m, int amount, short meta) {
        this.is = new ItemStack(m, amount, meta);
    }

    public ItemBuilder clone() {
        return new ItemBuilder(this.is);
    }

    public ItemBuilder setDurability(short dur) {
        this.is.setDurability(dur);
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta im = this.is.getItemMeta();
        im.setDisplayName(name);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        this.is.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench) {
        this.is.removeEnchantment(ench);
        return this;
    }

    public ItemBuilder setSkullOwner(UUID owner) {
        try {
            SkullMeta im = (SkullMeta)this.is.getItemMeta();
            im.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
            this.is.setItemMeta(im);
        } catch (ClassCastException var3) {
        }

        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench, int level) {
        ItemMeta im = this.is.getItemMeta();
        im.addEnchant(ench, level, true);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setInfinityDurability() {
        this.is.setDurability((short)32767);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta im = this.is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setWoolColor(DyeColor color) {
        if (this.is.getType().equals(Material.WOOL)) {
            this.is.setDurability(color.getDyeData());
        }
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta)this.is.getItemMeta();
            im.setColor(color);
            this.is.setItemMeta(im);
        } catch (ClassCastException var3) {
        }

        return this;
    }

    public ItemBuilder setBannerColor(DyeColor color) {
        if(this.is.getType().equals(Material.BANNER)) {
            BannerMeta m = (BannerMeta)this.is.getItemMeta();
            m.setBaseColor(color);
            this.is.setItemMeta(m);
        }

        return this;
    }

    public ItemBuilder hideEnchants() {
        ItemMeta im = this.is.getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemStack toItemStack() {
        return this.is;
    }
}
