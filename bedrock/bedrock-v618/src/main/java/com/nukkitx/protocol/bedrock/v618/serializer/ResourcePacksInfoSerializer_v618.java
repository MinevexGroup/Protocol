package com.nukkitx.protocol.bedrock.v618.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.packet.ResourcePacksInfoPacket;
import com.nukkitx.protocol.bedrock.v448.serializer.ResourcePacksInfoSerializer_v448;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePacksInfoSerializer_v618 extends ResourcePacksInfoSerializer_v448 {

    public static final ResourcePacksInfoSerializer_v618 INSTANCE = new ResourcePacksInfoSerializer_v618();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, ResourcePacksInfoPacket packet) {
        super.serialize(buffer, helper, packet);

        helper.writeArray(buffer, packet.getCdnEntries(), (buf, cdnEntry) -> {
            helper.writeString(buffer, cdnEntry.getPackId());
            helper.writeString(buffer, cdnEntry.getRemoteUrl());
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, ResourcePacksInfoPacket packet) {
        super.deserialize(buffer, helper, packet);

        helper.readArray(buffer, packet.getCdnEntries(), buf ->
                new ResourcePacksInfoPacket.CDNEntry(helper.readString(buf), helper.readString(buf)));
    }
}