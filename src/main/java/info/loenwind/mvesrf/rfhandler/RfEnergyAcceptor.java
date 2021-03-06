package info.loenwind.mvesrf.rfhandler;

import info.loenwind.mves.api.IEnergyAcceptor;
import info.loenwind.mves.api.IEnergyOffer;
import info.loenwind.mves.api.IEnergyStack;
import info.loenwind.mvesrf.MvesRfMod;

import java.util.Iterator;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import cofh.api.energy.IEnergyReceiver;

public class RfEnergyAcceptor<TE extends TileEntity & IEnergyReceiver> implements IEnergyAcceptor {

  private final TE te;
  private final EnumFacing facing;

  public RfEnergyAcceptor(TE te, EnumFacing facing) {
    this.te = te;
    this.facing = facing;
  }

  @Override
  public int offerEnergy(IEnergyOffer offer) {
    int used = 0;
    int maxEnergy = offer.getLimit();
    Iterator<IEnergyStack> iterator = offer.getStacks().iterator();
    while (used < maxEnergy && iterator.hasNext()) {
      IEnergyStack next = iterator.next();
      if (next != null && next.getSource() != te && next.getStackSize() >= 0) {
        int max = Math.min(maxEnergy - used, next.getStackSize());
        max = Math.min(max, te.receiveEnergy(facing, max, true));
        if (max > 0) {
          int extractEnergy = next.extractEnergy(max);
          if (extractEnergy > 0) {
            int receiveEnergy = te.receiveEnergy(facing, extractEnergy, false);
            if (receiveEnergy != extractEnergy) {
              MvesRfMod.LOG.warn("Block " + te + " at " + te.getPos() + " said it would accept " + extractEnergy + " RF but only took " + receiveEnergy
                  + ". The remainder was lost.");
            }
            used += Math.max(extractEnergy, receiveEnergy);
          }
        }
      }
    }
    return used;
  }

}
