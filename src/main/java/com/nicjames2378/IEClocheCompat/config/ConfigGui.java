package com.nicjames2378.IEClocheCompat.config;

import com.nicjames2378.IEClocheCompat.utils.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parent) {
        super(parent, getConfigElements(), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(Configurator.config.toString()));
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        for (String category : Configurator.config.getCategoryNames()) {
            list.add(new ConfigElement(Configurator.config.getCategory(category)));
        }

        return list;
    }
}