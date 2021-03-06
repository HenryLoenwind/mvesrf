package info.loenwind.mvesrf.config.gui;

import info.loenwind.mvesrf.MvesRfMod;
import info.loenwind.mvesrf.config.ConfigHandler;
import info.loenwind.mvesrf.config.Section;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiConfigFactory extends GuiConfig {

  public static ConfigHandler CONFIGHANDLER;

  public GuiConfigFactory(GuiScreen parentScreen) {
    super(parentScreen, getConfigElements(parentScreen), MvesRfMod.MODID, false, false, StatCollector.translateToLocal("mves.config.title"));
  }

  private static List<IConfigElement> getConfigElements(GuiScreen parent) {
    List<IConfigElement> list = new ArrayList<IConfigElement>();
    String prefix = MvesRfMod.MODID + ".config.";

    for (Section section : Section.values()) {
      if (!section.sync || !CONFIGHANDLER.configLockedByServer) {
        list.add(new ConfigElement(CONFIGHANDLER.configuration.getCategory(section.name).setLanguageKey(prefix + section.name)));
      }
    }

    return list;
  }
}
