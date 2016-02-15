package info.loenwind.mvesrf.rfhandler;

import info.loenwind.mves.api.IEnergyStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import cofh.api.energy.IEnergyProvider;

public class RfEnergyStack<TE extends TileEntity & IEnergyProvider> implements IEnergyStack {

  private final TE te;
  private final EnumFacing facing;

  public RfEnergyStack(TE te, EnumFacing facing) {
    this.te = te;
    this.facing = facing;
  }

  @Override
  public int getStackSize() {
    return te.getEnergyStored(facing);
  }

  @Override
  public int extractEnergy(int amount) {
    return te.extractEnergy(facing, amount, false);
  }

  @Override
  public Object getSource() {
    return te;
  }

  @Override
  public boolean isStoredEnergy() {
    return false;
  }

}
