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

    public boolean isConditionValid() {
        return condition;
    }

    public CropFormat setCondition(boolean condition) {
        this.condition = condition;
        return this;
    }

    private ItemStack seed;
    private ItemStack[] output;
    private ItemStack soil;
    private Block crop;
    private boolean condition = true;

    public CropFormat(String seedItemStack, String[] outputItemStack, String soilItemStack, String cropBlock) {
        this.seed = ConvertUtils.getItemStackFromStringClean(seedItemStack);
        this.output = ConvertUtils.StringArrayToItemStackArray(outputItemStack);
        this.soil = ConvertUtils.getItemStackFromString(soilItemStack, false);
        this.crop = Block.getBlockFromName(cropBlock);
    }

    public CropFormat(String seedItemStack, String outputItemStack, String soilItemStack, String cropBlock) {
        this.seed = ConvertUtils.getItemStackFromStringClean(seedItemStack);
        this.output = new ItemStack[]{ConvertUtils.getItemStackFromString(outputItemStack, false)};
        this.soil = ConvertUtils.getItemStackFromString(soilItemStack, false);
        this.crop = Block.getBlockFromName(cropBlock);
    }

    public static class ConvertUtils extends com.nicjames2378.IEClocheCompat.utils.ConversionUtils {
    } // Makes ConversionUtils available to public through the API in case anyone wants to use it.
}
