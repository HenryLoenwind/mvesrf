package info.loenwind.mvesrf.rfhandler;

import info.loenwind.mvesrf.MvesRfMod;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import cofh.api.energy.IEnergyReceiver;

public class RfReceiverCapabilityProvider<TE extends TileEntity & IEnergyReceiver> implements ICapabilityProvider {

  private final TE te;
  private RfEnergyAcceptor[] acceptors = null;

  public RfReceiverCapabilityProvider(TE te) {
    this.te = te;
  }

  @Override
  public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
    return te.hasWorldObj() && !te.getWorld().isRemote && capability == MvesRfMod.CAP_EnergyAcceptor && te.canConnectEnergy(facing);
  }

  @Override
  public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
    if (hasCapability(capability, facing)) {
      if (acceptors == null) {
        acceptors = new RfEnergyAcceptor[EnumFacing.values().length];
      }
      if (acceptors[facing.ordinal()] == null) {
        acceptors[facing.ordinal()] = new RfEnergyAcceptor(te, facing);
      }
      return (T) acceptors[facing.ordinal()];
    }
    return null;
  }

}
