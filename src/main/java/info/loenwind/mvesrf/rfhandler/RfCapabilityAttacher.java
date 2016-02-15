package info.loenwind.mvesrf.rfhandler;

import info.loenwind.mves.demo.furnace.FurnaceCapabilityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
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
    TileEntity tileEntity = event.getTileEntity();
    if (tileEntity instanceof IEnergyProvider) {
      event.addCapability(new ResourceLocation("mvesrf:provider"), new FurnaceCapabilityProvider((TileEntityFurnace) tileEntity));
    }
    if (tileEntity instanceof IEnergyReceiver) {
      event.addCapability(new ResourceLocation("mvesrf:receiver"), new FurnaceCapabilityProvider((TileEntityFurnace) tileEntity));
    }
  }

}
