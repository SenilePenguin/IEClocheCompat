package com.nicjames2378.IEClocheCompat.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class ConversionUtils {

    /// Custom "toString" method for getting the list of an ItemStack
    public static ItemStack[] StringArrayToItemStackArray(String[] input) {
        int iMax = input.length - 1;
        if (input == null || iMax == -1)
            return new ItemStack[]{};

        ArrayList<ItemStack> lst = new ArrayList<>();
        for (String str : input) {
            lst.add(getItemStackFromString(str, false));
        }
        return lst.toArray(new ItemStack[0]);
    }

    public static ItemStack getItemStackFromString(String input, boolean makeClean) {
        // Clean version returns a generic stack without amount or metadata.
        // Format is ##*modid:itemid@meta#
        // Example: A stack of
        int amount = 1;
        String item;
        int meta = 0;

        if (input.contains("*")) {
            String[] ss = input.split("\\*");
            amount = Integer.parseInt(ss[0]);
            input = ss[1];
        }

        if (input.contains("=")) {
            String[] ss = input.split("=");
            meta = Integer.parseInt(ss[1]);
            input = ss[0];
        }
        item = input;


        return new ItemStack(Item.getByNameOrId(item), amount, meta);
    }

    public static ItemStack getItemStackFromStringClean(String input) {
        String item;

        if (input.contains("*")) {
            String[] ss = input.split("\\*");
            input = ss[1];
        }

        if (input.contains("=")) {
            String[] ss = input.split("=");
            input = ss[0];
        }
        item = input;

        return new ItemStack(Item.getByNameOrId(item));
    }

    public static String ItemStackArrayToString(ItemStack[] input) {
        if (input == null)
            return "null";
        int iMax = input.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(input[i].getCount());
            b.append(("*"));
            b.append(input[i].getItem().getRegistryName());
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}
