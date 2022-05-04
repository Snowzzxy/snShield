package me.snow.shield.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ItemBuilder {

    private ItemStack item;

    public ItemBuilder(Material m) {
        this(m, 1);
    }

    public ItemBuilder(ItemStack is) {
        item = is;
    }

    public ItemBuilder(Material m, int quantia) {
        item = new ItemStack(m, quantia);

    }

    public ItemBuilder(Material m, int quantia, byte durabilidade) {
        item = new ItemStack(m, quantia, durabilidade);
    }

    public ItemBuilder(Material m, int quantia, int durabilidade) {
        item = new ItemStack(m, quantia, (short) durabilidade);
    }

    public ItemBuilder clone() {
        return new ItemBuilder(item);
    }

    public ItemBuilder setDurability(short durabilidade) {
        item.setDurability(durabilidade);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);

        ItemMeta im = item.getItemMeta();
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });

        item.setItemMeta(im);
        return this;
    }

    public ItemBuilder setDurability(String durabilidade) {
        item.setDurability(Short.valueOf(durabilidade).shortValue());
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);

        item.setItemMeta(im);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        item.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench) {
        item.removeEnchantment(ench);
        return this;
    }

    public ItemBuilder setSkullOwner(String dono) {
        try {
            SkullMeta im = (SkullMeta) item.getItemMeta();
            im.setOwner(dono);
            item.setItemMeta((ItemMeta) im);

        } catch (ClassCastException classCastException) {

        }
        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench, int level) {
        ItemMeta im = item.getItemMeta();
        im.addEnchant(ench, level, true);

        item.setItemMeta(im);
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        item.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilder setInfinityDurability() {
        item.setDurability((short) 63);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag flag) {
        ItemMeta im = item.getItemMeta();
        im.addItemFlags(new ItemFlag[] { flag });

        item.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta im = item.getItemMeta();
        im.setLore(Arrays.asList(lore));

        item.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta im = item.getItemMeta();
        im.setLore(lore);

        item.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeLoreLine(String linha) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());

        if (!lore.contains(linha))
            return this;

        lore.remove(linha);
        im.setLore(lore);

        item.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeLoreLine(int index) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());

        if (index < 0 || index > lore.size())
            return this;

        lore.remove(index);
        im.setLore(lore);

        item.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLoreLine(String linha) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        if (im.hasLore())
            lore = new ArrayList<>(im.getLore());

        lore.add(linha);
        im.setLore(lore);

        item.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLores(List<String> linha) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        if (im.hasLore())
            lore = new ArrayList<>(im.getLore());

        for (String s : linha)
            lore.add(s);

        im.setLore(lore);
        item.setItemMeta(im);

        return this;
    }

    public ItemBuilder addLoreLine(String linha, int pos) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());

        lore.set(pos, linha);
        im.setLore(lore);

        item.setItemMeta(im);

        return this;
    }

    public ItemBuilder owner(String owner) {
        try {
            SkullMeta im = (SkullMeta) item.getItemMeta();
            im.setOwner(owner);
            item.setItemMeta((ItemMeta) im);

        } catch (ClassCastException classCastException) {

        }
        return this;
    }

    public ItemBuilder lore(String... lore) {
        ItemMeta im = item.getItemMeta();
        List<String> out = (im.getLore() == null) ? new ArrayList<>() : im.getLore();
        byte b;
        int i;
        String[] arrayOfString;

        for (i = (arrayOfString = lore).length, b = 0; b < i;) {
            String string = arrayOfString[b];
            out.add(ChatColor.translateAlternateColorCodes('&', string));
            b = (byte) (b + 1);

        }
        im.setLore(out);
        item.setItemMeta(im);

        return this;
    }

    public ItemBuilder setLore2(String... lore) {
        ItemMeta im = item.getItemMeta();
        List<String> out = (im.getLore() == null) ? new ArrayList<>() : im.getLore();
        byte b;
        int i;
        String[] arrayOfString;

        for (i = (arrayOfString = lore).length, b = 0; b < i;) {
            String string = arrayOfString[b];
            out.add(ChatColor.translateAlternateColorCodes('&', string));
            b = (byte) (b + 1);

        }
        im.setLore(out);
        item.setItemMeta(im);

        return this;
    }

    public ItemBuilder removeAttributes() {
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);

        return this;
    }

    public ItemBuilder color(Color color) {
        if (!item.getType().name().contains("LEATHER_"))
            return this;

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(color);
        item.setItemMeta((ItemMeta) meta);

        return this;
    }

    public ItemBuilder setDyeColor(DyeColor cor) {
        item.setDurability(cor.getData());
        return this;
    }

    @Deprecated
    public ItemBuilder setWoolColor(DyeColor cor) {
        if (!item.getType().equals(Material.WOOL))
            return this;

        item.setDurability(cor.getData());
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color cor) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) item.getItemMeta();
            im.setColor(cor);
            item.setItemMeta((ItemMeta) im);

        } catch (ClassCastException classCastException) {

        }
        return this;
    }

    public ItemBuilder builder(Consumer<ItemStack> consumer) {
        consumer.accept(item);
        return this;
    }

    public ItemBuilder builderMeta(Consumer<ItemMeta> consumer) {
        ItemMeta meta = item.getItemMeta();
        consumer.accept(meta);
        item.setItemMeta(meta);

        return this;
    }

    public ItemBuilder addGlow(boolean glow) {
        if (!glow)
            return this;

        builder(it -> it.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1));
        builderMeta(meta -> meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS }));
        return this;
    }

    public ItemStack toItemStack() {
        return item;
    }

    public ItemStack build() {
        return item;

    }
}
