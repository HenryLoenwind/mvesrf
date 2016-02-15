package info.loenwind.mvesrf;

import info.loenwind.mves.api.IEnergyAcceptor;
import info.loenwind.mves.api.IEnergyHandler;
import info.loenwind.mves.api.IEnergySupplier;
import info.loenwind.mves.api.IEnergyTransporter;
import info.loenwind.mvesrf.config.ConfigHandler;
import info.loenwind.mvesrf.config.gui.GuiConfigFactory;
import info.loenwind.mvesrf.proxies.CommonProxy;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

import org.apache.logging.log4j.Logger;

@Mod(modid = MvesRfMod.MODID, version = MvesRfMod.VERSION, guiFactory = "info.loenwind.mvesrf.config.gui.ConfigFactory", dependencies = "required-after:mves")
public class MvesRfMod {
  public static final String MODID = "mvesrf";
  public static final String VERSION = "1.0.0";

  @SidedProxy(clientSide = "info.loenwind.mvesrf.proxies.ClientProxy", serverSide = "info.loenwind.mvesrf.proxies.CommonProxy")
  public static CommonProxy PROXY;

  public static Logger LOG;

  public static SimpleNetworkWrapper NETWORK;

  public static ConfigHandler CONFIGHANDLER;

  @CapabilityInject(IEnergyHandler.class)
  public static final Capability<IEnergyHandler> CAP_EnergyHandler = null;
  @CapabilityInject(IEnergySupplier.class)
  public static final Capability<IEnergySupplier> CAP_EnergySupplier = null;
  @CapabilityInject(IEnergyTransporter.class)
  public static final Capability<IEnergyTransporter> CAP_EnergyTransporter = null;
  @CapabilityInject(IEnergyAcceptor.class)
  public static final Capability<IEnergyAcceptor> CAP_EnergyAcceptor = null;

  @EventHandler
  public void preinit(FMLPreInitializationEvent event) {
    LOG = event.getModLog();
    NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
    GuiConfigFactory.CONFIGHANDLER = CONFIGHANDLER = new ConfigHandler(MODID, LOG, NETWORK);
    CONFIGHANDLER.init(event);
    PROXY.init(event);
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    PROXY.init(event);
  }

  @EventHandler
  public void init(FMLPostInitializationEvent event) {
    PROXY.init(event);
  }
}
