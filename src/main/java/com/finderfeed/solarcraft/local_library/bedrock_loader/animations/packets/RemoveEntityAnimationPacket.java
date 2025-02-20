package com.finderfeed.solarcraft.local_library.bedrock_loader.animations.packets;

import com.finderfeed.solarcraft.local_library.bedrock_loader.animations.manager.AnimationTicker;
import com.finderfeed.solarcraft.packet_handler.ClientPacketHandles;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RemoveEntityAnimationPacket {

    private int entityId;
    private String tickerName;

    public RemoveEntityAnimationPacket(Entity entity, String tickerName) {
        this.entityId = entity.getId();
        this.tickerName = tickerName;
    }

    public RemoveEntityAnimationPacket(FriendlyByteBuf buf){
        this.entityId = buf.readInt();
        this.tickerName = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(entityId);
        buf.writeUtf(tickerName);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            ClientPacketHandles.handleRemoveEntityAnimationPacket(entityId,tickerName);
        });
        ctx.get().setPacketHandled(true);
    }

}
