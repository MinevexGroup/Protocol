package com.nukkitx.protocol.bedrock.v594.serializer;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.AgentAnimationPacket;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgentAnimationSerializer_v594 implements BedrockPacketSerializer<AgentAnimationPacket> {

    public static final AgentAnimationSerializer_v594 INSTANCE = new AgentAnimationSerializer_v594();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, AgentAnimationPacket packet) {
        buffer.writeByte(packet.getAnimation());
        VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, AgentAnimationPacket packet) {
        packet.setAnimation(buffer.readByte());
        packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
    }
}