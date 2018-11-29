package com.nicjames2378.IEClocheCompat.api;

import com.nicjames2378.IEClocheCompat.utils.ConversionUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class CropFormat {

    public ItemStack getSeed() {
        return seed;
    }

    public ItemStack[] getOutputs() {
        return output;
    }

    public ItemStack getSoil() {
        return soil;
    }

    public Block getCrop() {
        return crop;
    }

    public String getSeedAsString() {
        return seed.getItem().getRegistryName().toString();
    }

    public String getOutputsAsString() {
        return ConversionUtils.ItemStackArrayToString(output);
    }

    public String getSoilAsString() {
        return soil.getItem().getRegistryName().toString();
    }

    public String getCropAsString() {
        return crop.getRegistryName().toString();
    }

    public void setSeed(String seed) {
        this.seed = ConversionUtils.getItemStackFromStringClean(seed);
    }

    public void setOutput(String[] output) {
        this.output = ConversionUtils.StringArrayToItemStackArray(output);
    }

    public void setSoil(String soil) {
        this.soil = ConversionUtils.getItemStackFromStringClean(soil);
    }

    public void setCrop(String crop) {
        this.crop = Block.getBlockFromName(crop);
    }

    private static ItemStack seed;
    private static ItemStack[] output;
    private static ItemStack soil;
    private static Block crop;

    public CropFormat(String seedItemStack, String[] outputItemStack, String soilItemStack, String cropBlock) {
        this.seed = ConvertUtils.getItemStackFromStringClean(seedItemStack);
        this.output = ConvertUtils.StringArrayToItemStackArray(outputItemStack);
        this.soil = ConvertUtils.getItemStackFromString(soilItemStack);
        this.crop = Block.getBlockFromName(cropBlock);
    }

    public static class ConvertUtils extends com.nicjames2378.IEClocheCompat.utils.ConversionUtils {} //Makes calling the utils easier from the users of the API
}
