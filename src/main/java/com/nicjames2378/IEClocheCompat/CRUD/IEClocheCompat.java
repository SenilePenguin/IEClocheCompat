// Completely Reliable User Development API
package com.nicjames2378.IEClocheCompat.CRUD;

import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import com.nicjames2378.IEClocheCompat.CRUD.formats.CropFormat;
import com.nicjames2378.IEClocheCompat.CRUD.formats.FertilizerFormat;
import com.nicjames2378.IEClocheCompat.Main;
import com.nicjames2378.IEClocheCompat.utils.ConversionUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class IEClocheCompat {

    public static boolean registerCrop(ItemStack seed, ItemStack[] output, ItemStack soil, Block crop, boolean condition) {
        if (condition) {
            try {
                BelljarHandler.cropHandler.register(seed, output, soil, crop.getDefaultState());

                Main.log.info(String.format("Registering seed '%1$s', with output '%2$s', with soil '%3$s', with crop '%4$s'",
                        seed.getItem().getRegistryName(), ConversionUtils.ItemStackArrayToString(output), soil.getItem().getRegistryName(), crop.getRegistryName()));
                Main.log.info("-------------------------------");
                return true;
            } catch (Exception e) {
                Main.log.error(String.format("Error registering crop for seed '%1$s'. Skipping!", seed.toString()));
                Main.log.error(e);
                return false;
            }
        }
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

    public static boolean registerFertilizer(ItemStack fertilizer, float growthMultiplier, boolean condition) {
        if (condition) {
            try {
                BelljarHandler.registerBasicItemFertilizer(fertilizer, growthMultiplier);

                Main.log.info(String.format("Registering fertilizer '%1$s', with growth multiplier of '%2$s'",
                        fertilizer.getItem().getRegistryName(), growthMultiplier));
                Main.log.info("-------------------------------");
                return true;
            } catch (Exception e) {
                Main.log.error(String.format("Error registering fertilizer '%1$s'. Skipping!", fertilizer.getItem().getRegistryName()));
                Main.log.error(e);
                return false;
            }
        }
        return false;
    }

    public static boolean registerFertilizer(FertilizerFormat newFertilizer) {
        ItemStack fertilizer = newFertilizer.getFertilizer();
        float growthMultiplier = newFertilizer.getGrowthMultiplier();
        boolean condition = newFertilizer.isConditionValid();

        return IEClocheCompat.registerFertilizer(fertilizer, growthMultiplier, condition);
    }

    public static boolean[] registerAllFertilizers(FertilizerFormat[] newFertilizers) {
        boolean[] results = new boolean[newFertilizers.length];
        int current = 0;

        for (FertilizerFormat newFertilizer : newFertilizers) {
            results[current] = registerFertilizer(newFertilizer);
        }
        return results;
    }
}
