package com.nukkitx.protocol.bedrock.v582.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.CompressedBiomeDefinitionListPacket;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompressedBiomeDefinitionListSerializer_v582 implements BedrockPacketSerializer<CompressedBiomeDefinitionListPacket> {

    public static final CompressedBiomeDefinitionListSerializer_v582 INSTANCE = new CompressedBiomeDefinitionListSerializer_v582();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, CompressedBiomeDefinitionListPacket packet) {
        helper.writeTag(buffer, packet.getDefinitions());
        helper.writeString(buffer, packet.getDictionaryLookup());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, CompressedBiomeDefinitionListPacket packet) {
        packet.setDefinitions(helper.readTag(buffer));
        packet.setDictionaryLookup(helper.readString(buffer));
    }
}