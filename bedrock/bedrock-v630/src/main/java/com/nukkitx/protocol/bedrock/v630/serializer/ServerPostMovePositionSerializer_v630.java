package com.nukkitx.protocol.bedrock.v630.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ServerPostMovePositionPacket;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerPostMovePositionSerializer_v630 implements BedrockPacketSerializer<ServerPostMovePositionPacket> {

    public static final ServerPostMovePositionSerializer_v630 INSTANCE = new ServerPostMovePositionSerializer_v630();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, ServerPostMovePositionPacket packet) {
        helper.writeVector3f(buffer, packet.getPosition());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, ServerPostMovePositionPacket packet) {
        packet.setPosition(helper.readVector3f(buffer));
    }
}