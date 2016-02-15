package info.loenwind.mvesrf.rfhandler;

import info.loenwind.mves.api.EnergyRole;
import info.loenwind.mves.api.IEnergyHandler;

import java.util.EnumSet;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;

public class RfEnergyHandler<TE extends TileEntity & cofh.api.energy.IEnergyHandler> implements IEnergyHandler {

  private static final EnumSet<EnergyRole> roleSupplier = EnergyRole.build(EnergyRole.GENERATOR);
  private static final EnumSet<EnergyRole> roleAcceptor = EnergyRole.build(EnergyRole.MACHINE);
  private static final EnumSet<EnergyRole> roleBoth = EnergyRole.build(EnergyRole.GENERATOR, EnergyRole.MACHINE);

  private final TE te;
  private final EnumFacing facing;

  public RfEnergyHandler(TE te, EnumFacing facing) {
    this.te = te;
    this.facing = facing;
  }

  @Override
  public EnumSet<EnergyRole> getRoles() {
    boolean sup = te instanceof IEnergyProvider;
    boolean acc = te instanceof IEnergyReceiver;
    return sup && acc ? roleBoth : sup ? roleSupplier : roleAcceptor;
  }

  @Override
  public long getBufferSize() {
    return te.getMaxEnergyStored(facing);
  }

  @Override
  public long getBufferContent() {
    return te.getEnergyStored(facing);
  }

  @Override
  public long getBufferFree() {
    return getBufferSize() - getBufferContent();
  }

  @Override
  public Object getNetwork() {
    return te;
  }

}
