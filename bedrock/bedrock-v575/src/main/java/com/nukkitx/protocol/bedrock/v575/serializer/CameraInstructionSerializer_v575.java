package com.nukkitx.protocol.bedrock.v575.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.CameraInstructionPacket;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraInstructionSerializer_v575
    implements BedrockPacketSerializer<CameraInstructionPacket> {

    public static final CameraInstructionSerializer_v575 INSTANCE =
        new CameraInstructionSerializer_v575();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper,
                          CameraInstructionPacket packet) {
        BedrockPacketSerializer.super.serialize(buffer, helper, packet);
        helper.writeTag(buffer, packet.getData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper,
                            CameraInstructionPacket packet) {
        BedrockPacketSerializer.super.deserialize(buffer, helper, packet);
        packet.setData(helper.readTag(buffer));
    }
}