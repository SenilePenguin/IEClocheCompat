package com.nicjames2378.IEClocheCompat.api;

import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import com.nicjames2378.IEClocheCompat.Main;
import com.nicjames2378.IEClocheCompat.utils.ConversionUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class IEClocheCompat {

    private static boolean spawnCheckPasses(ItemStack seed, ItemStack[] output, ItemStack soil, Block crop, boolean condition) {
        return true;
    }

    public static boolean registerCrop(ItemStack seed, ItemStack[] output, ItemStack soil, Block crop, boolean condition) {
        if (condition) {
            try {
                BelljarHandler.cropHandler.register(seed, output, soil, crop.getDefaultState());

                Main.log.info(String.format("Registering seed '%1$s', with output '%2$s', with soil '%3$s', with crop '%4$s'",
                        seed.getItem().getRegistryName(), ConversionUtils.ItemStackArrayToString(output), soil.getItem().getRegistryName(), crop.getRegistryName()));

                //Main.log.info("seed.getItem().getRegistryName() " + seed.getItem().getRegistryName());
                //Main.log.info("ConversionUtils.ItemStackArrayToString(output) " + ConversionUtils.ItemStackArrayToString(output));
                //Main.log.info("soil.getItem().getRegistryName() " + soil.getItem().getRegistryName());
                //Main.log.info("crop.getRegistryName() " + crop.getRegistryName());
                Main.log.info("-------------------------------");


                return true;
            } catch (Exception e) {
                //Main.log.info(String.format("!!!Error occured adding item '%1$s', with output '%2$s', with soil '%3$s', with crop '%4$s'!!!",
                //        seed.getItem().getRegistryName(), ConversionUtils.ItemStackArrayToString(output), soil.getItem().getRegistryName(), ""/*crop.getRegistryName()*/));
                Main.log.error(e);
                //Main.log.info("Attempting to continue!");
                return false;
            }
        }
        //Main.log.info("How it be?");
        //Main.log.info(String.format("Condition for item '%1$s', with output '%2$s', with soil '%3$s', with crop '%4$s', not met. Skipping.",
        //        seed.getItem().getRegistryName(), ConversionUtils.ItemStackArrayToString(output), soil.getItem().getRegistryName(), crop.getRegistryName()));
        return false;
    }

    public static boolean registerCrop(CropFormat newCrop) {
        ItemStack seed = newCrop.getSeed();
        ItemStack[] output = newCrop.getOutputs();
        ItemStack soil = newCrop.getSoil();
        Block crop = newCrop.getCrop();
        boolean condition = newCrop.isConditionValid();

        return IEClocheCompat.registerCrop(seed, output, soil, crop, condition);
    }

    public static boolean[] registerAllCrops(CropFormat[] newCrops) {
        boolean[] results = new boolean[newCrops.length];
        int current = 0;

        for (CropFormat newCrop : newCrops) {
            results[current] = registerCrop(newCrop);
        }
        return results;
    }
}
