package com.nicjames2378.IEClocheCompat.CRUD.formats;

import com.nicjames2378.IEClocheCompat.utils.ConversionUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class CropFormat {
    public ItemStack getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = ConversionUtils.getItemStackFromStringClean(seed);
    }

    public ItemStack[] getOutputs() {
        return output;
    }

    public void setOutputs(String[] output) {
        this.output = ConversionUtils.StringArrayToItemStackArray(output);
    }

    public ItemStack getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = ConversionUtils.getItemStackFromStringClean(soil);
    }

    public Block getCrop() {
        return crop;
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

    public CropFormat(ItemStack seed, ItemStack[] output, ItemStack soil, Block crop, boolean condition) {
        this.seed = seed;
        this.output = output;
        this.soil = soil;
        this.crop = crop;
        this.condition = condition;
    }

    public CropFormat(ItemStack seed, ItemStack output, ItemStack soil, Block crop, boolean condition) {
        this.seed = seed;
        this.output = new ItemStack[]{output};
        this.soil = soil;
        this.crop = crop;
        this.condition = condition;
    }

    public CropFormat(ItemStack seed, String outputItemStack, String soilItemStack, String cropBlock) {
        this.seed = seed;
        this.output = new ItemStack[]{ConvertUtils.getItemStackFromString(outputItemStack, false)};
        this.soil = ConvertUtils.getItemStackFromString(soilItemStack, false);
        this.crop = Block.getBlockFromName(cropBlock);
    }

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
    } // Makes ConversionUtils easier to access from the API (removes the need for an extra import)
}
