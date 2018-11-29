package com.nicjames2378.IEClocheCompat.proxy;

import com.nicjames2378.IEClocheCompat.recipes.Recipes;
import com.nicjames2378.IEClocheCompat.utils.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CommonProxy {
    public void PreInit(FMLPreInitializationEvent event) {
    }

    public void Init(FMLInitializationEvent event) {
    }

    public void PostInit(FMLPostInitializationEvent event) {
        Recipes.initialize();
    }
}
