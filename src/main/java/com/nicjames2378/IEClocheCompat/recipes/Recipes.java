package com.nicjames2378.IEClocheCompat.recipes;

import com.nicjames2378.IEClocheCompat.CRUD.IEClocheCompat;
import com.nicjames2378.IEClocheCompat.CRUD.compats.AgriCraft.AgriClocheCompat;
import com.nicjames2378.IEClocheCompat.CRUD.formats.CropFormat;
import com.nicjames2378.IEClocheCompat.CRUD.formats.FertilizerFormat;
import com.nicjames2378.IEClocheCompat.Main;
import com.nicjames2378.IEClocheCompat.config.Configurator;
import com.nicjames2378.IEClocheCompat.utils.ModChecker;

public class Recipes {

    public static void registerHandlers() {
        if (ModChecker.AGRICRAFT && Configurator.integrationAgricraft) {
            Main.log.info("Beginning HANDLERS registry");
            AgriClocheCompat.registerHandler();
        }
    }

    public static void initializeAgricraft() {
        //Have to do Agricraft compat first to prevent issues with mystical agradditions?
        if (ModChecker.AGRICRAFT && Configurator.integrationAgricraft) {
            Main.log.info("Beginning AGRICRAFT registry");
            AgriClocheCompat.initialize();
        }
    }
    public static void initialize() {
        if (!ModChecker.AGRICRAFT && ModChecker.MYSTICAL_AGGRADITIONS && Configurator.integrationMysticalAgraditions) {
            //Disabling this while Agricraft is installed mostly solves the issue where NBTs aren't used.
            //Doesn't fix Vanilla or IE seeds though. Pretty sure that needs fixed on Blu's side... somewhere
            Main.log.info("Beginning MYSTICAL_AGGRADITIONS registry");
            registerMysticalAgradditionsCompat();
        }

        if (ModChecker.MAGICAL_CROPS && Configurator.integrationMagicalCrops) {
            Main.log.info("Beginning MAGICAL_CROPS registry");
            registerMagicalCropsCompat();
        }
    }

    private static void registerMysticalAgradditionsCompat() {
        String p = "mysticalagradditions:";

        CropFormat[] newCrops = new CropFormat[]{
                new CropFormat(p + "nether_star_seeds", p + "nether_star_essence", p + "special=0", p + "nether_star_crop").setCondition(Configurator.seedMysticalAgradditionsNetherStar),
                new CropFormat(p + "dragon_egg_seeds", p + "dragon_egg_essence", p + "special=1", p + "dragon_egg_crop").setCondition(Configurator.seedMysticalAgradditionsDragonEgg),
                new CropFormat(p + "awakened_draconium_seeds", p + "awakened_draconium_essence", p + "special=4", p + "awakened_draconium_crop").setCondition(ModChecker.DRACONIC_EVOLUTION && Configurator.seedMysticalAgradditionsAwakenedDraconium),
                new CropFormat(p + "tier6_inferium_seeds", "6*mysticalagriculture:crafting", "mysticalagradditions:storage", p + "tier6_inferium_crop").setCondition(Configurator.seedMysticalAgradditionsTier6Inferium)
        };

        IEClocheCompat.registerAllCrops(newCrops);

        if (Configurator.integrationMysticalAgriculture) {
            registerMysticalAgricultureCompat();
        }
    }

    private static void registerMysticalAgricultureCompat() {
        IEClocheCompat.registerFertilizer(new FertilizerFormat("mysticalagriculture:mystical_fertilizer", Configurator.statMysticalAgricultureMysticalFertilizerStrength).setCondition(Configurator.fertMysticalAgricultureMysticalFertilizer));
    }

    private static void registerMagicalCropsCompat() {
        String p = "magicalcrops:";
        CropFormat[] newCrops = new CropFormat[]{
                new CropFormat(p + "seedair", p + "essenceair", "minecraft:dirt", p + "cropair").setCondition(Configurator.seedMagicalCrops_air),
                new CropFormat(p + "seedcoal", p + "essencecoal", "minecraft:dirt", p + "cropcoal").setCondition(Configurator.seedMagicalCrops_coal),
                new CropFormat(p + "seeddiamond", p + "essencediamond", "minecraft:dirt", p + "cropdiamond").setCondition(Configurator.seedMagicalCrops_diamond),
                new CropFormat(p + "seeddye", p + "essencedye", "minecraft:dirt", p + "cropdye").setCondition(Configurator.seedMagicalCrops_dye),
                new CropFormat(p + "seedearth", p + "essenceearth", "minecraft:dirt", p + "cropearth").setCondition(Configurator.seedMagicalCrops_earth),
                new CropFormat(p + "seedemerald", p + "essenceemerald", "minecraft:dirt", p + "cropemerald").setCondition(Configurator.seedMagicalCrops_emerald),
                new CropFormat(p + "seedexperience", p + "essenceexperience", "minecraft:dirt", p + "cropexperience").setCondition(Configurator.seedMagicalCrops_experience),
                new CropFormat(p + "seedfire", p + "essencefire", "minecraft:dirt", p + "cropfire").setCondition(Configurator.seedMagicalCrops_fire),
                new CropFormat(p + "seedglowstone", p + "essenceglowstone", "minecraft:dirt", p + "cropglowstone").setCondition(Configurator.seedMagicalCrops_glowstone),
                new CropFormat(p + "seedgold", p + "essencegold", "minecraft:dirt", p + "cropgold").setCondition(Configurator.seedMagicalCrops_gold),
                new CropFormat(p + "seediron", p + "essenceiron", "minecraft:dirt", p + "cropiron").setCondition(Configurator.seedMagicalCrops_iron),
                new CropFormat(p + "seedlapis", p + "essencelapis", "minecraft:dirt", p + "croplapis").setCondition(Configurator.seedMagicalCrops_lapis),
                new CropFormat(p + "seedminicio", p + "essenceminicio", "minecraft:dirt", p + "cropminicio").setCondition(Configurator.seedMagicalCrops_minicio),
                new CropFormat(p + "seednature", p + "essencenature", "minecraft:dirt", p + "cropnature").setCondition(Configurator.seedMagicalCrops_nature),
                new CropFormat(p + "seednether", p + "essencenether", "minecraft:dirt", p + "cropnether").setCondition(Configurator.seedMagicalCrops_nether),
                new CropFormat(p + "seedobsidian", p + "essenceobsidian", "minecraft:dirt", p + "cropobsidian").setCondition(Configurator.seedMagicalCrops_obsidian),
                new CropFormat(p + "seedquartz", p + "essencequartz", "minecraft:dirt", p + "cropquartz").setCondition(Configurator.seedMagicalCrops_quartz),
                new CropFormat(p + "seedredstone", p + "essenceredstone", "minecraft:dirt", p + "cropredstone").setCondition(Configurator.seedMagicalCrops_redstone),
                new CropFormat(p + "seedwater", p + "essencewater", "minecraft:dirt", p + "cropwater").setCondition(Configurator.seedMagicalCrops_water)
        };

        IEClocheCompat.registerAllCrops(newCrops);
    }
}