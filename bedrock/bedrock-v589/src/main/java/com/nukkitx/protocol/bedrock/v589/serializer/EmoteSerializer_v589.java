package com.nukkitx.protocol.bedrock.v589.serializer;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.packet.EmotePacket;
import com.nukkitx.protocol.bedrock.v388.serializer.EmoteSerializer_v388;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmoteSerializer_v589 extends EmoteSerializer_v388 {

    public static final EmoteSerializer_v589 INSTANCE = new EmoteSerializer_v589();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, EmotePacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
        helper.writeString(buffer, packet.getEmoteId());
        helper.writeString(buffer, packet.getXuid());
        helper.writeString(buffer, packet.getPlatformId());
        this.writeFlags(buffer, packet);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, EmotePacket packet) {
        packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
        packet.setEmoteId(helper.readString(buffer));
        packet.setXuid(helper.readString(buffer));
        packet.setPlatformId(helper.readString(buffer));
        this.readFlags(buffer, packet);
    }
}