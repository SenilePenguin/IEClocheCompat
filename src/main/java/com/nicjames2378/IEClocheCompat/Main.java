package com.nicjames2378.IEClocheCompat;

import com.nicjames2378.IEClocheCompat.proxy.CommonProxy;
import com.nicjames2378.IEClocheCompat.utils.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, updateJSON = Reference.UPDATE_CHECK_URL, guiFactory = Reference.GUI_FACTORY)
public class Main {

    public static final Logger log = LogManager.getLogger(Reference.MOD_ID);

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    public enum SIDE {
        CLIENT,
        SERVER
    }
    public static SIDE PROX = null;

    @EventHandler
    public static void PreInit(FMLPreInitializationEvent event) {
        proxy.PreInit(event);
    }

    @EventHandler
    public static void Init(FMLInitializationEvent event) {
        proxy.Init(event);
    }

    @EventHandler
    public static void PostInit(FMLPostInitializationEvent event) {
        proxy.PostInit(event);
    }
}