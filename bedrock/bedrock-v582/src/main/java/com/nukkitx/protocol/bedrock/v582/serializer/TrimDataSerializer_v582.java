package com.nukkitx.protocol.bedrock.v582.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.TrimMaterial;
import com.nukkitx.protocol.bedrock.data.TrimPattern;
import com.nukkitx.protocol.bedrock.packet.TrimDataPacket;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrimDataSerializer_v582 implements BedrockPacketSerializer<TrimDataPacket> {

    public static final TrimDataSerializer_v582 INSTANCE = new TrimDataSerializer_v582();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, TrimDataPacket packet) {
        helper.writeArray(buffer, packet.getPatterns(), (buf, bedrockPacketHelper, trimPattern) -> {
            bedrockPacketHelper.writeString(buf, trimPattern.getItemName());
            bedrockPacketHelper.writeString(buf, trimPattern.getPatternId());
        });

        helper.writeArray(buffer, packet.getMaterials(), (buf, bedrockPacketHelper, trimMaterial) -> {
            bedrockPacketHelper.writeString(buf, trimMaterial.getMaterialId());
            bedrockPacketHelper.writeString(buf, trimMaterial.getColor());
            bedrockPacketHelper.writeString(buf, trimMaterial.getItemName());
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, TrimDataPacket packet) {
        helper.readArray(buffer, packet.getPatterns(), (buf, bedrockPacketHelper) -> {
            final String itemName = bedrockPacketHelper.readString(buf);
            final String patternId = bedrockPacketHelper.readString(buf);

            return new TrimPattern(itemName, patternId);
        });

        helper.readArray(buffer, packet.getMaterials(), (buf, bedrockPacketHelper) -> {
            final String materialId = bedrockPacketHelper.readString(buf);
            final String color = bedrockPacketHelper.readString(buf);
            final String itemName = bedrockPacketHelper.readString(buf);

            return new TrimMaterial(materialId, color, itemName);
        });
    }
}