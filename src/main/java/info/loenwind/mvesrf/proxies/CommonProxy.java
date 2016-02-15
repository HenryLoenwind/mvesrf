package info.loenwind.mvesrf.proxies;

import info.loenwind.mvesrf.config.Config;
import info.loenwind.mvesrf.rfhandler.RfCapabilityAttacher;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

  public void init(FMLPreInitializationEvent e) {
    if (Config.enableRfHooking.getBoolean()) {
      MinecraftForge.EVENT_BUS.register(new RfCapabilityAttacher());
    }
//    if (Config.enableRainbowWire.getBoolean()) {
//      BlockMvesWire.create();
//    }
  }

  public void init(FMLInitializationEvent e) {
//    if (Config.enableRainbowWire.getBoolean() && Config.enableRainbowWireRecipe.getBoolean()) {
//      GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(BlockMvesWire.block)), "345", "2x6", "1x7", 'x', Items.redstone, '1', new ItemStack(Items.dye,
//          1, EnumDyeColor.RED.getDyeDamage()), '2', new ItemStack(Items.dye, 1, EnumDyeColor.ORANGE.getDyeDamage()), '3', new ItemStack(Items.dye, 1,
//          EnumDyeColor.YELLOW.getDyeDamage()), '4', new ItemStack(Items.dye, 1, EnumDyeColor.GREEN.getDyeDamage()), '5', new ItemStack(Items.dye, 1,
//          EnumDyeColor.CYAN.getDyeDamage()), '6', new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeDamage()), '7', new ItemStack(Items.dye, 1,
//          EnumDyeColor.PURPLE.getDyeDamage()));
//    }
  }

  public void init(FMLPostInitializationEvent e) {

  }
}
