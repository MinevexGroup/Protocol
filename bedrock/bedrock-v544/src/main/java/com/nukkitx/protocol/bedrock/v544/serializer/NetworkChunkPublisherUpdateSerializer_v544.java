package com.nukkitx.protocol.bedrock.v544.serializer;

import com.nukkitx.math.vector.Vector2i;
import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.packet.NetworkChunkPublisherUpdatePacket;
import com.nukkitx.protocol.bedrock.v313.serializer.NetworkChunkPublisherUpdateSerializer_v313;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NetworkChunkPublisherUpdateSerializer_v544 extends NetworkChunkPublisherUpdateSerializer_v313 {

    public static final NetworkChunkPublisherUpdateSerializer_v544 INSTANCE = new NetworkChunkPublisherUpdateSerializer_v544();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, NetworkChunkPublisherUpdatePacket packet) {
        super.serialize(buffer, helper, packet);

        helper.writeArray(buffer, packet.getSavedChunks(), ByteBuf::writeIntLE, this::writeSavedChunk);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, NetworkChunkPublisherUpdatePacket packet) {
        super.deserialize(buffer, helper, packet);

        helper.readArray(buffer, packet.getSavedChunks(), ByteBuf::readIntLE, this::readSavedChunk);
    }

    protected void writeSavedChunk(ByteBuf buffer, BedrockPacketHelper helper, Vector2i savedChunk) {
        VarInts.writeInt(buffer, savedChunk.getX());
        VarInts.writeInt(buffer, savedChunk.getY());
    }

    protected Vector2i readSavedChunk(ByteBuf buffer, BedrockPacketHelper helper) {
        return Vector2i.from(VarInts.readInt(buffer), VarInts.readInt(buffer));
    }
}
