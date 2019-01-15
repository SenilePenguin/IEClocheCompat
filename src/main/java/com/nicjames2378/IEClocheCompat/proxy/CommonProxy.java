package com.nicjames2378.IEClocheCompat.proxy;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.nicjames2378.IEClocheCompat.Main;
import com.nicjames2378.IEClocheCompat.config.Configurator;
import com.nicjames2378.IEClocheCompat.recipes.Recipes;
import com.nicjames2378.IEClocheCompat.utils.ModChecker;
import com.nicjames2378.IEClocheCompat.utils.Reference;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CommonProxy {
    public void PreInit(FMLPreInitializationEvent event) {
        Main.PROX = Main.SIDE.SERVER;
        Configurator.init(new File(event.getModConfigurationDirectory() + File.separator + Reference.MOD_ID, Reference.MOD_ID + ".cfg"));

        MinecraftForge.EVENT_BUS.register(new Configurator());
    }

    public void Init(FMLInitializationEvent event) {
        Main.log.info("Mod is running " + (Main.PROX == Main.SIDE.SERVER ? "SERVER" : "CLIENT") + " SIDE!");
        Recipes.initializeAgricraft();
    }

    public void PostInit(FMLPostInitializationEvent event) {
        Recipes.initialize();
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        Recipes.registerHandlers();
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent e) {
        if (ModChecker.AGRICRAFT) {
            String[] warning = new String[]{
                    "IE Cloche Compat has some known bugs!",
                    " If you find that Agricraft Seeds are not working in Garden Cloches, try putting " +
                            "an Industrial Hemp seed (Immersive Engineering) with the any Agricraft stats in first.",
                    " I do not know why, but this seems to make other seeds work again. Sorry for the inconvenience!"
            };
            for (String line : warning) {
                e.player.sendMessage(new TextComponentString(ChatFormatting.DARK_PURPLE + line));
            }
        }
    }
}
