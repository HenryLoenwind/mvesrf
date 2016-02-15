package info.loenwind.mvesrf.config;

import info.loenwind.mvesrf.config.ConfigHandler;
import info.loenwind.mvesrf.config.PacketConfigSync;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class HandleConfigSync implements IMessageHandler<PacketConfigSync, IMessage> {

  private final ConfigHandler confighandler;

  public HandleConfigSync(ConfigHandler confighandler) {
    super();
    this.confighandler = confighandler;
  }

  @Override
  public IMessage onMessage(PacketConfigSync message, MessageContext ctx) {
    confighandler.fromBytes(message.bufferCopy);
    return null;
  }


}
