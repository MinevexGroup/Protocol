package com.nukkitx.protocol.bedrock.v582.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.packet.RequestChunkRadiusPacket;
import com.nukkitx.protocol.bedrock.v291.serializer.RequestChunkRadiusSerializer_v291;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestChunkRadiusSerializer_v582 extends RequestChunkRadiusSerializer_v291 {

    public static final RequestChunkRadiusSerializer_v582 INSTANCE = new RequestChunkRadiusSerializer_v582();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, RequestChunkRadiusPacket packet) {
        super.serialize(buffer, helper, packet);

        buffer.writeByte(packet.getMaxRadius());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, RequestChunkRadiusPacket packet) {
        super.deserialize(buffer, helper, packet);

        packet.setMaxRadius(buffer.readUnsignedByte());
    }
}