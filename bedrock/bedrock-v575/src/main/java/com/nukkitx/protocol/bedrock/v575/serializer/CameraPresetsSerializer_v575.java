package com.nukkitx.protocol.bedrock.v575.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.CameraPresetsPacket;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraPresetsSerializer_v575 implements BedrockPacketSerializer<CameraPresetsPacket> {

    public static final CameraPresetsSerializer_v575 INSTANCE = new CameraPresetsSerializer_v575();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, CameraPresetsPacket packet) {
        helper.writeTag(buffer, packet.getData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper,
                            CameraPresetsPacket packet) {
        packet.setData(helper.readTag(buffer));
    }
}