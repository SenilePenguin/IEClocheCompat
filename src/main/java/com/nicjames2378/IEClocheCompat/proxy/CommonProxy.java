package com.nicjames2378.IEClocheCompat.proxy;

import com.nicjames2378.IEClocheCompat.config.Configurator;
import com.nicjames2378.IEClocheCompat.recipes.Recipes;
import com.nicjames2378.IEClocheCompat.utils.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CommonProxy {
    public void PreInit(FMLPreInitializationEvent event) {
        Configurator.init(new File(event.getModConfigurationDirectory() + "\\" + Reference.MOD_ID, Reference.MOD_ID + ".cfg"));

        MinecraftForge.EVENT_BUS.register(new Configurator());
    }

    public void Init(FMLInitializationEvent event) {
    }

    public void PostInit(FMLPostInitializationEvent event) {
        Recipes.initialize();
    }
}
