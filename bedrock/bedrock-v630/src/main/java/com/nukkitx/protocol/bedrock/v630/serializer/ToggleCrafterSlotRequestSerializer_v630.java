package com.nukkitx.protocol.bedrock.v630.serializer;

import com.nukkitx.math.vector.Vector3i;
import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ToggleCrafterSlotRequestPacket;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToggleCrafterSlotRequestSerializer_v630 implements BedrockPacketSerializer<ToggleCrafterSlotRequestPacket> {

    public static final ToggleCrafterSlotRequestSerializer_v630 INSTANCE = new ToggleCrafterSlotRequestSerializer_v630();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, ToggleCrafterSlotRequestPacket packet) {
        buffer.writeIntLE(packet.getBlockPosition().getX());
        buffer.writeIntLE(packet.getBlockPosition().getY());
        buffer.writeIntLE(packet.getBlockPosition().getZ());
        buffer.writeByte(packet.getSlot());
        buffer.writeBoolean(packet.isDisabled());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, ToggleCrafterSlotRequestPacket packet) {
        packet.setBlockPosition(Vector3i.from(buffer.readIntLE(), buffer.readIntLE(), buffer.readIntLE()));
        packet.setSlot(buffer.readByte());
        packet.setDisabled(buffer.readBoolean());
    }
}