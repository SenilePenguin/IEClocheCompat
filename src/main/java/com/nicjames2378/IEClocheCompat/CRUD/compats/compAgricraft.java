package com.nicjames2378.IEClocheCompat.CRUD.compats;

import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import com.infinityraider.agricraft.api.v1.AgriApi;
import com.infinityraider.agricraft.api.v1.plant.IAgriPlant;
import com.infinityraider.agricraft.api.v1.soil.IAgriSoil;
import com.nicjames2378.IEClocheCompat.Main;
import com.nicjames2378.IEClocheCompat.config.Configurator;
import com.nicjames2378.IEClocheCompat.utils.ConversionUtils;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

@SuppressWarnings("unchecked")
public class compAgricraft {
    static class SeedAgriItem {
        private Item itemSeed;
        private String agriSeed = null;

        private SeedAgriItem(ItemStack seed) {
            if (seed.hasTagCompound()) {
                NBTTagCompound nbt = seed.getTagCompound();
                if (nbt.hasKey("agri_seed")) {
                    agriSeed = nbt.getString("agri_seed");
                }
            }
            itemSeed = seed.getItem();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            SeedAgriItem that = (SeedAgriItem) other;
            return Objects.equals(itemSeed, that.itemSeed) &&
                    Objects.equals(agriSeed, that.agriSeed);
        }

        @Override
        public int hashCode() {
            return Objects.hash(itemSeed, agriSeed);
        }
    }

    private static ArrayList<String> filterList;

    //Map seed ItemStacks to their corresponding IAgriPlants to make things easier
    private static HashMap<SeedAgriItem, IAgriPlant> plantMap = new HashMap();
    private static HashMap<IAgriPlant, ItemStack[]> soilMap = new HashMap();
    private static HashMap<IAgriPlant, ItemStack[]> outputMap = new HashMap<>();
    private static HashMap<IAgriPlant, IBlockState[]> renderMap = new HashMap<>();

    static class AgricraftCropHandler implements BelljarHandler.IPlantHandler {
        private HashSet<IAgriPlant> validSeeds = new HashSet<>();

        protected HashSet<IAgriPlant> getSeedSet() {
            return validSeeds;
        }

        @Override
        public boolean isValid(ItemStack seed) {
            IAgriPlant plant = null;

            if (plantMap.containsKey(new SeedAgriItem(seed))) {
                plant = plantMap.get(new SeedAgriItem(seed));
            }

            return getSeedSet().contains(plant);
        }

        @Override
        public boolean isCorrectSoil(ItemStack seed, ItemStack soil) {
            for (ItemStack reqSoil : soilMap.get(plantMap.get(new SeedAgriItem(seed)))) {
                if (ItemStack.areItemsEqual(reqSoil, soil)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public float getGrowthStep(ItemStack seed, ItemStack soil, float growth, TileEntity tile, float fertilizer, boolean render) {
            //TODO: Implement growth modifier based on NBT stats
            byte modifier = 0;
            if (seed.hasTagCompound()) {
                if (seed.getTagCompound().hasKey("agri_growth")) {
                    modifier = seed.getTagCompound().getByte("agri_growth");
                }
            }

            if (modifier == 0) { // If a seed somehow has a modifier below the lowest, make sure it doesn't negatively impact growth
                return (.003125f * (fertilizer + Configurator.seedAgricraftStrengthModifier));
            }
            return (.003125f * (fertilizer + ((Configurator.seedAgricraftStrengthModifier * modifier) - Configurator.seedAgricraftStrengthModifier)));
        }

        @Override
        public ItemStack[] getOutput(ItemStack seed, ItemStack soil, TileEntity tile) {
            return outputMap.get(plantMap.get(new SeedAgriItem(seed)));
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IBlockState[] getRenderedPlant(ItemStack seed, ItemStack soil, float growth, TileEntity tile) {
            //TODO: Generate Render method that gets info from the plant itself.
            //      Consider caching the renders?
            //      https://github.com/AgriCraft/AgriCraft/blob/master/src/main/java/com/infinityraider/agricraft/renderers/PlantRenderer.java#L28
            //
            //      Currently, WHEAT is used as the stand-in renderer in the register method. That needs changed after fixing this
            //
            IBlockState[] states = renderMap.get(plantMap.get(new SeedAgriItem(seed)));
            if (states != null) {
                IBlockState[] ret = new IBlockState[states.length];
                for (int i = 0; i < states.length; i++)
                    if (states[i] != null)
                        if (states[i].getBlock() instanceof BlockCrops) {
                            int max = ((BlockCrops) states[i].getBlock()).getMaxAge();
                            ret[i] = ((BlockCrops) states[i].getBlock()).withAge(Math.min(max, Math.round(max * growth)));
                        } else {
                            for (IProperty prop : states[i].getPropertyKeys())
                                if ("age".equals(prop.getName()) && prop instanceof PropertyInteger) {
                                    int max = 0;
                                    for (Integer allowed : ((PropertyInteger) prop).getAllowedValues())
                                        if (allowed != null && allowed > max)
                                            max = allowed;
                                    ret[i] = states[i].withProperty(prop, Math.min(max, Math.round(max * growth)));
                                }
                            if (ret[i] == null)
                                ret[i] = states[i];
                        }
                return ret;
            }
            return null;
        }

        public void register(IAgriPlant agriPlant) {
            ItemStack seed = agriPlant.getSeed();

            Main.log.info(String.format("Agricraft Compat - Registering IAgriPlant '%1$s', with output '%2$s', with soil '%3$s'",
                    agriPlant.getPlantName(),
                    ConversionUtils.ItemStackArrayToString(getPlantOutputs(agriPlant)),
                    ConversionUtils.ItemStackArrayToString(getPlantSoils(agriPlant))));
            Main.log.info("-------------------------------");

            plantMap.put(new SeedAgriItem(seed), agriPlant);

            getSeedSet().add(agriPlant);
            soilMap.put(agriPlant, getPlantSoils(agriPlant));
            outputMap.put(agriPlant, getPlantOutputs(agriPlant));

            //TODO: FIX RENDERING
            renderMap.put(agriPlant, new IBlockState[]{Blocks.WHEAT.getDefaultState()});
        }
    }

    public static AgricraftCropHandler agricraftHandler = new AgricraftCropHandler() {
    };

    public static void initialize() {
        filterList = new ArrayList<>(Arrays.asList(Configurator.seedAgricraftList));
        BelljarHandler.registerHandler(agricraftHandler);
        for (IAgriPlant plant : AgriApi.getPlantRegistry().all()) {
            if (canRegister(plant)) {
                agricraftHandler.register(plant);
            }
        }
    }

//    private static ItemStack[] getPlantSeeds(IAgriPlant agriPlant) {
//        //HAS NBT HERE
//        Main.log.info("TT - getPlantSeeds: " + agriPlant.getSeed().getTagCompound());
//        ArrayList<ItemStack> seeds = new ArrayList<>();
//        for (FuzzyStack stack : agriPlant.getSeedItems()) {
//            seeds.add(new ItemStack(stack.getItem()));
//            Main.log.info("TT - fuzzyLoop: " + stack.toString());
//        }
//        return seeds.toArray(new ItemStack[0]);
//    }

    private static ItemStack[] getPlantOutputs(IAgriPlant agriPlant) {
        ArrayList<ItemStack> outputs = new ArrayList<>();
        agriPlant.getPossibleProducts(outputs::add);

        return outputs.toArray(new ItemStack[0]);
    }

    private static ItemStack[] getPlantSoils(IAgriPlant agriPlant) {
        ArrayList<ItemStack> soils = new ArrayList<>();


        for (IAgriSoil soil : agriPlant.getGrowthRequirement().getSoils()) {
            try {
                ItemStack soilStack = new ItemStack(Item.getByNameOrId(soil.getName()));
                ItemStack addMe = soilStack;

                // If the soil is farmland, tell it to use dirt instead
                if (ItemStack.areItemsEqual(soilStack, new ItemStack(Blocks.FARMLAND))) {

                    // Unless it's from MysticalAgradditions, in which case we use cruxes
                    NBTTagCompound tag = (agriPlant.getSeed().hasTagCompound() ? agriPlant.getSeed().getTagCompound() : new NBTTagCompound());
                    if (tag.hasKey("agri_seed") && tag.getString("agri_seed").contains("mysticalagradditions")) {
                        switch (tag.getString("agri_seed").split(":")[1]) {
                            case "tier_six_inferium_plant":
                                addMe = ConversionUtils.getItemStackFromStringClean("mysticalagradditions:storage");
                                break;
                            case "awakened_draconium_plant":
                                addMe = ConversionUtils.getItemStackFromStringClean("mysticalagradditions:special=4");
                                break;
                            case "nether_star_plant":
                                addMe = ConversionUtils.getItemStackFromStringClean("mysticalagradditions:special=0");
                                break;
                            case "dragon_egg_plant":
                                addMe = ConversionUtils.getItemStackFromStringClean("mysticalagradditions:special=1");
                                break;
                        }
                    } else {
                        addMe = new ItemStack(Blocks.DIRT);
                    }
                }
                soils.add(addMe);
            } catch (Exception e) {
                Main.log.error("compAgricraft: getSoils (" + agriPlant.getSeedName() + ") - Error Getting Soil '" + soil.toString() + "' ");
            }
        }
        return soils.toArray(new ItemStack[0]);
    }

    private static boolean canRegister(IAgriPlant agriPlant) {
        String agriValue = null;
        try {
            agriValue = agriPlant.getSeed().getTagCompound().getString("agri_seed");
        } catch (Exception e) {
            Main.log.error("compAgricraft#canRegister: Seed does not have proper compound! Either a mod is incorrectly " +
                    "registering or Agricraft has changed! {" + agriPlant.getSeedName() + "}");
        }

        // If it's not in the proper format, reject it
        if (agriValue == null || agriValue.isEmpty() || agriValue == "") {
            return false;
        }

        // If it's blacklisted, reject it
        if (Configurator.seedAgricraftListType.equals("BLACK") && (filterList.contains(agriValue) || filterList.contains(agriValue.split(":")[0] + ":*"))) {
            Main.log.info("Agricraft Compat - Skipping blacklisted seed: " + agriPlant.getSeedName());
            Main.log.info("-------------------------------");
            return false;
        }

        // If it's NOT whitelisted, reject it
        if (Configurator.seedAgricraftListType.equals("WHITE") && (!filterList.contains(agriValue) || !filterList.contains(agriValue.split(":")[0] + ":*"))) {
            Main.log.info("Agricraft Compat - Skipping non-whitelisted seed: " + agriPlant.getSeedName());
            Main.log.info("-------------------------------");
            return false;
        }

        // If it's somehow survived, keep it. It can be our pet.
        return true;
    }
}