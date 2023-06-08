package com.nukkitx.protocol.bedrock.v388.serializer;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.EmoteFlag;
import com.nukkitx.protocol.bedrock.packet.EmotePacket;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class EmoteSerializer_v388 implements BedrockPacketSerializer<EmotePacket> {

    public static final EmoteSerializer_v388 INSTANCE = new EmoteSerializer_v388();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, EmotePacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
        helper.writeString(buffer, packet.getEmoteId());
        this.writeFlags(buffer, packet);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, EmotePacket packet) {
        packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
        packet.setEmoteId(helper.readString(buffer));
        this.readFlags(buffer, packet);
    }

    public void writeFlags(ByteBuf buffer, EmotePacket packet) {
        int flags = 0;
        for (EmoteFlag flag : packet.getFlags()) {
            flags |= 1 << flag.ordinal();
        }
        buffer.writeByte(flags);
    }

    public void readFlags(ByteBuf buffer, EmotePacket packet) {
        int flags = buffer.readUnsignedByte();
        for (EmoteFlag flag : EmoteFlag.values()) {
            if ((flags & (1L << flag.ordinal())) != 0) {
                packet.getFlags().add(flag);
            }
        }
    }
}
