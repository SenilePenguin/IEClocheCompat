package com.nicjames2378.IEClocheCompat.recipes;

import com.nicjames2378.IEClocheCompat.api.CropFormat;
import com.nicjames2378.IEClocheCompat.api.IEClocheCompat;
import com.nicjames2378.IEClocheCompat.utils.ModChecker;

public class Recipes {

    public static void initialize() {
        if (ModChecker.MYSTICAL_AGGRADITIONS) {
            registerMysticalAgradditionsCompat();
        }

        if (ModChecker.MAGICAL_CROPS) {
            registerMagicalCropsCompat();
        }
    }

    private static void registerMysticalAgradditionsCompat() {
        String p = "mysticalagradditions:";
        CropFormat[] newCrops = new CropFormat[]{
                new CropFormat(p + "nether_star_seeds", p + "nether_star_essence", p + "special=0", p + "nether_star_crop"),
                new CropFormat(p + "dragon_egg_seeds", p + "dragon_egg_essence", p + "special=1", p + "dragon_egg_crop"),
                new CropFormat(p + "awakened_draconium_seeds", p + "awakened_draconium_essence", p + "special=4", p + "awakened_draconium_crop").setCondition(ModChecker.DRACONIC_EVOLUTION),
                new CropFormat(p + "tier6_inferium_seeds", "6*mysticalagriculture:crafting", "mysticalagradditions:storage", p + "tier6_inferium_crop")
        };

        IEClocheCompat.registerAllCrops(newCrops);
    }

    private static void registerMagicalCropsCompat() {
        String p = "magicalcrops:";
        CropFormat[] newCrops = new CropFormat[]{
                new CropFormat(p + "seedair", p + "essenceair", "minecraft:dirt", p + "cropair"),
                new CropFormat(p + "seedcoal", p + "essencecoal", "minecraft:dirt", p + "cropcoal"),
                new CropFormat(p + "seeddiamond", p + "essencediamond", "minecraft:dirt", p + "cropdiamond"),
                new CropFormat(p + "seeddye", p + "essencedye", "minecraft:dirt", p + "cropdye"),
                new CropFormat(p + "seedearth", p + "essenceearth", "minecraft:dirt", p + "cropearth"),
                new CropFormat(p + "seedemerald", p + "essenceemerald", "minecraft:dirt", p + "cropemerald"),
                new CropFormat(p + "seedexperience", p + "essenceexperience", "minecraft:dirt", p + "cropexperience"),
                new CropFormat(p + "seedfire", p + "essencefire", "minecraft:dirt", p + "cropfire"),
                new CropFormat(p + "seedglowstone", p + "essenceglowstone", "minecraft:dirt", p + "cropglowstone"),
                new CropFormat(p + "seedgold", p + "essencegold", "minecraft:dirt", p + "cropgold"),
                new CropFormat(p + "seediron", p + "essenceiron", "minecraft:dirt", p + "cropiron"),
                new CropFormat(p + "seedlapis", p + "essencelapis", "minecraft:dirt", p + "croplapis"),
                new CropFormat(p + "seedminicio", p + "essenceminicio", "minecraft:dirt", p + "cropminicio"),
                new CropFormat(p + "seednature", p + "essencenature", "minecraft:dirt", p + "cropnature"),
                new CropFormat(p + "seednether", p + "essencenether", "minecraft:dirt", p + "cropnether"),
                new CropFormat(p + "seedobsidian", p + "essenceobsidian", "minecraft:dirt", p + "cropobsidian"),
                new CropFormat(p + "seedquartz", p + "essencequartz", "minecraft:dirt", p + "cropquartz"),
                new CropFormat(p + "seedredstone", p + "essenceredstone", "minecraft:dirt", p + "cropredstone"),
                new CropFormat(p + "seedwater", p + "essencewater", "minecraft:dirt", p + "cropwater")
        };

        IEClocheCompat.registerAllCrops(newCrops);
    }
}