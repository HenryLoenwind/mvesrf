package info.loenwind.mvesrf.rfhandler;

import info.loenwind.mvesrf.MvesRfMod;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import cofh.api.energy.IEnergyProvider;

public class RfProviderCapabilityProvider<TE extends TileEntity & IEnergyProvider> implements ICapabilityProvider {

  private final TE te;
  private RfEnergySupplier[] suppliers = null;

  public RfProviderCapabilityProvider(TE te) {
    this.te = te;
  }

  @Override
  public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
    return te.hasWorldObj() && !te.getWorld().isRemote && capability == MvesRfMod.CAP_EnergySupplier && te.canConnectEnergy(facing);
  }

  @Override
  public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
    if (hasCapability(capability, facing)) {
      if (suppliers == null) {
        suppliers = new RfEnergySupplier[EnumFacing.values().length];
      }
      if (suppliers[facing.ordinal()] == null) {
        suppliers[facing.ordinal()] = new RfEnergySupplier(te, facing);
      }
      return (T) suppliers[facing.ordinal()];
    }
    return null;
  }

}
