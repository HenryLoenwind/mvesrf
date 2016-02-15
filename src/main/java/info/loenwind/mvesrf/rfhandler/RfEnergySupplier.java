package info.loenwind.mvesrf.rfhandler;

import info.loenwind.mves.api.IEnergyStack;
import info.loenwind.mves.api.IEnergySupplier;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import cofh.api.energy.IEnergyProvider;

public class RfEnergySupplier<TE extends TileEntity & IEnergyProvider> implements IEnergySupplier {

  private final TE te;
  private final EnumFacing facing;

  public RfEnergySupplier(TE te, EnumFacing facing) {
    this.te = te;
    this.facing = facing;
  }

  @Override
  public IEnergyStack get() {
    if (te.getEnergyStored(facing) > 0) {
      return new RfEnergyStack<TE>(te, facing);
    } else {
      return null;
    }
  }

}
