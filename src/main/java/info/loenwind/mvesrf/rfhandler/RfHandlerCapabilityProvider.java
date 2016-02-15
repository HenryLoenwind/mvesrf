package info.loenwind.mvesrf.rfhandler;

import info.loenwind.mvesrf.MvesRfMod;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import cofh.api.energy.IEnergyHandler;

public class RfHandlerCapabilityProvider<TE extends TileEntity & IEnergyHandler> implements ICapabilityProvider {

  private final TE te;
  private RfEnergyHandler[] handlers = null;

  public RfHandlerCapabilityProvider(TE te) {
    this.te = te;
  }

  @Override
  public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
    return te.hasWorldObj() && !te.getWorld().isRemote && capability == MvesRfMod.CAP_EnergyHandler && te.canConnectEnergy(facing);
  }

  @Override
  public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
    if (hasCapability(capability, facing)) {
      if (handlers == null) {
        handlers = new RfEnergyHandler[EnumFacing.values().length];
      }
      if (handlers[facing.ordinal()] == null) {
        handlers[facing.ordinal()] = new RfEnergyHandler(te, facing);
      }
      return (T) handlers[facing.ordinal()];
    }
    return null;
  }

}
