package com.nicjames2378.IEClocheCompat.api;

import net.minecraft.item.ItemStack;

public class FertilizerFormat {

    public ItemStack getFertilizer() {
        return fertilizer;
    }

    public String getFertilizerAsString() {
        return fertilizer.getItem().getRegistryName().toString();
    }

    public void setFertilizer(String seed) {
        this.fertilizer = ConvertUtils.getItemStackFromStringClean(seed);
    }

    public float getGrowthMultiplier() {
        return growthMultiplier;
    }

    public void setGrowthMultiplier(float growthMultiplier) {
        this.growthMultiplier = growthMultiplier;
    }

    public boolean isConditionValid() {
        return condition;
    }

    public FertilizerFormat setCondition(boolean condition) {
        this.condition = condition;
        return this;
    }

    private ItemStack fertilizer;
    private float growthMultiplier = 0.25f;
    private boolean condition = true;

    public FertilizerFormat(ItemStack fertilizer, float growthMultiplier) {
        this.fertilizer = fertilizer;
        this.growthMultiplier = growthMultiplier;
    }

    public FertilizerFormat(String fertilizerItemStack, float growthMultiplier) {
        this.fertilizer = ConvertUtils.getItemStackFromStringClean(fertilizerItemStack);
        this.growthMultiplier = growthMultiplier;
    }

    public static class ConvertUtils extends com.nicjames2378.IEClocheCompat.utils.ConversionUtils {
    } // Makes ConversionUtils easier to access from the API (removes the need for an extra import)
}
