package info.loenwind.mvesrf.rfhandler;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;

public class RfCapabilityAttacher {

  public RfCapabilityAttacher() {
  }

  @SubscribeEvent
  public void onTELoad(AttachCapabilitiesEvent.TileEntity event) {
    boolean flag = false;
    TileEntity tileEntity = event.getTileEntity();
    if (tileEntity instanceof IEnergyProvider) {
      event.addCapability(new ResourceLocation("mvesrf:provider"), new RfProviderCapabilityProvider(tileEntity));
      flag = true;
    }
    if (tileEntity instanceof IEnergyReceiver) {
      event.addCapability(new ResourceLocation("mvesrf:receiver"), new RfReceiverCapabilityProvider(tileEntity));
      flag = true;
    }
    if (flag) {
      event.addCapability(new ResourceLocation("mvesrf:handler"), new RfHandlerCapabilityProvider(tileEntity));
    }
  }

}
