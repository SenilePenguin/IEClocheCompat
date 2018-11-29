package com.nicjames2378.IEClocheCompat.proxy;

import com.nicjames2378.IEClocheCompat.utils.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)

public class ClientProxy extends CommonProxy {
    @Override
    public void PreInit(FMLPreInitializationEvent event) {
        super.PreInit(event);
    }

    @Override
    public void Init(FMLInitializationEvent event) {
        super.Init(event);
    }

    @Override
    public void PostInit(FMLPostInitializationEvent event) {
        super.PostInit(event);
    }
}
