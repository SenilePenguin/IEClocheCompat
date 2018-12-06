package com.nicjames2378.IEClocheCompat.config;

import com.nicjames2378.IEClocheCompat.Main;
import com.nicjames2378.IEClocheCompat.utils.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class Configurator {


    public static Configuration config;
    public static Configurator instance;

    // Increment this is config changes make it non-backwards compatible
    public static int version = 1;

    public static boolean integrationMagicalCrops;
    public static boolean integrationMysticalAgraditions;
    public static boolean integrationMysticalAgriculture;

    public static boolean seedMagicalCrops_air;
    public static boolean seedMagicalCrops_coal;
    public static boolean seedMagicalCrops_diamond;
    public static boolean seedMagicalCrops_dye;
    public static boolean seedMagicalCrops_earth;
    public static boolean seedMagicalCrops_emerald;
    public static boolean seedMagicalCrops_experience;
    public static boolean seedMagicalCrops_fire;
    public static boolean seedMagicalCrops_glowstone;
    public static boolean seedMagicalCrops_gold;
    public static boolean seedMagicalCrops_iron;
    public static boolean seedMagicalCrops_lapis;
    public static boolean seedMagicalCrops_minicio;
    public static boolean seedMagicalCrops_nature;
    public static boolean seedMagicalCrops_nether;
    public static boolean seedMagicalCrops_obsidian;
    public static boolean seedMagicalCrops_quartz;
    public static boolean seedMagicalCrops_redstone;
    public static boolean seedMagicalCrops_water;

    public static boolean seedMysticalAgradditionsTier6Inferium;
    public static boolean seedMysticalAgradditionsDragonEgg;
    public static boolean seedMysticalAgradditionsNetherStar;
    public static boolean seedMysticalAgradditionsAwakenedDraconium;

    public static boolean fertMysticalAgricultureMysticalFertilizer;

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.getModID().equals(Reference.MOD_ID)) {
            Configurator.syncConfig();
        }
    }

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        String category;

        category = "__internal";
        config.addCustomCategoryComment(category, "DO NOT CHANGE CONFIGS BELOW!"
                + "\n Changes could result in deleted configs, corruption, or other indesired side-effects!");
        version = config.getInt("version", category, version, 0, 1000, "Internal versioning information. Do not change!");

        category = "global mod integrations";
        config.addCustomCategoryComment(category, "Enable or disable compatibility with other mods."
                + "\nDisabling these will disable all default integrations for a particular mod, regardless of individual seed settings.");
        integrationMagicalCrops = config.getBoolean("magical_crops_integration", category, true, "Magical Crops Integrations enabled?");
        integrationMysticalAgraditions = config.getBoolean("mystical_agradditions_integration", category, true, "Mystical Agradditions Integrations enabled?");
        integrationMysticalAgriculture = config.getBoolean("mystical_agriculture_integration", category, true, "Mystical Agriculture Integrations enabled?");

        category = "magical crops";
        config.addCustomCategoryComment(category, "Enable or disable specific compatibilities with Garden Cloches."
                + "\nThese settings have no effect is the mod's integration is disabled in the global section.");
        seedMagicalCrops_air = config.getBoolean("air_seeds", category, true, "Magical Crops air seeds enabled?");
        seedMagicalCrops_coal = config.getBoolean("coal_seeds", category, true, "Magical Crops coal seeds enabled?");
        seedMagicalCrops_diamond = config.getBoolean("diamond_seeds", category, true, "Magical Crops diamond seeds enabled?");
        seedMagicalCrops_dye = config.getBoolean("dye_seeds", category, true, "Magical Crops dye seeds enabled?");
        seedMagicalCrops_earth = config.getBoolean("earth_seeds", category, true, "Magical Crops earth seeds enabled?");
        seedMagicalCrops_emerald = config.getBoolean("emerald_seeds", category, true, "Magical Crops emerald seeds enabled?");
        seedMagicalCrops_experience = config.getBoolean("experience_seeds", category, true, "Magical Crops experience seeds enabled?");
        seedMagicalCrops_fire = config.getBoolean("fire_seeds", category, true, "Magical Crops fire seeds enabled?");
        seedMagicalCrops_glowstone = config.getBoolean("glowstone_seeds", category, true, "Magical Crops glowstone seeds enabled?");
        seedMagicalCrops_gold = config.getBoolean("gold_seeds", category, true, "Magical Crops gold seeds enabled?");
        seedMagicalCrops_iron = config.getBoolean("iron_seeds", category, true, "Magical Crops iron seeds enabled?");
        seedMagicalCrops_lapis = config.getBoolean("lapis_seeds", category, true, "Magical Crops lapis seeds enabled?");
        seedMagicalCrops_minicio = config.getBoolean("minicio_seeds", category, true, "Magical Crops minicio seeds enabled?");
        seedMagicalCrops_nature = config.getBoolean("nature_seeds", category, true, "Magical Crops nature seeds enabled?");
        seedMagicalCrops_nether = config.getBoolean("nether_seeds", category, true, "Magical Crops nether seeds enabled?");
        seedMagicalCrops_obsidian = config.getBoolean("obsidian_seeds", category, true, "Magical Crops obsidian seeds enabled?");
        seedMagicalCrops_quartz = config.getBoolean("quartz_seeds", category, true, "Magical Crops quartz seeds enabled?");
        seedMagicalCrops_redstone = config.getBoolean("redstone_seeds", category, true, "Magical Crops redstone seeds enabled?");
        seedMagicalCrops_water = config.getBoolean("water_seeds", category, true, "Magical Crops water seeds enabled?");

        category = "mystical agradditions";
        config.addCustomCategoryComment(category, "Enable or disable specific compatibilities with Garden Cloches."
                + "\nThese settings have no effect is the mod's integration is disabled in the global section.");
        seedMysticalAgradditionsTier6Inferium = config.getBoolean("tier6_inferium_seeds", category, true, "Mystical Agradditions tier 6 inferium seeds enabled?");
        seedMysticalAgradditionsDragonEgg = config.getBoolean("dragon_egg_seeds", category, true, "Mystical Agradditions dragon egg seeds enabled?");
        seedMysticalAgradditionsNetherStar = config.getBoolean("nether_star_seeds", category, true, "Mystical Agradditions nether star seeds enabled?");
        seedMysticalAgradditionsAwakenedDraconium = config.getBoolean("awakened_draconium_seeds", category, true, "Mystical Agradditions awakened draconium seeds enabled?");

        category = "mystical agriculture";
        config.addCustomCategoryComment(category, "Enable or disable specific compatibilities with Garden Cloches."
                + "\nThese settings have no effect is the mod's integration is disabled in the global section.");
        fertMysticalAgricultureMysticalFertilizer = config.getBoolean("mystical_fertilizer", category, true, "Mystical Agriculture mystical fertilizer enabled?");


        if (config.hasChanged()) {
            config.save();
        }
    }
}
