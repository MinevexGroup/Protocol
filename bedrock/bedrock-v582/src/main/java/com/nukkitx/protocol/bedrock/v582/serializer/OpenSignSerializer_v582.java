package com.nukkitx.protocol.bedrock.v582.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.OpenSignPacket;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenSignSerializer_v582 implements BedrockPacketSerializer<OpenSignPacket> {

    public static final OpenSignSerializer_v582 INSTANCE = new OpenSignSerializer_v582();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, OpenSignPacket packet) {
        helper.writeSignedBlockPosition(buffer, packet.getPosition());
        buffer.writeBoolean(packet.isFrontSide());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, OpenSignPacket packet) {
        packet.setPosition(helper.readSignedBlockPosition(buffer));
        packet.setFrontSide(buffer.readBoolean());
    }
}