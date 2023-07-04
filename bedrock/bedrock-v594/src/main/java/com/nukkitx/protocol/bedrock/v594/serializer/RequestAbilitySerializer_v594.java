package com.nukkitx.protocol.bedrock.v594.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.data.Ability;
import com.nukkitx.protocol.bedrock.data.AbilityType;
import com.nukkitx.protocol.bedrock.packet.RequestAbilityPacket;
import com.nukkitx.protocol.bedrock.v527.serializer.RequestAbilitySerializer_v527;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestAbilitySerializer_v594 extends RequestAbilitySerializer_v527 {

    public static final RequestAbilitySerializer_v594 INSTANCE = new RequestAbilitySerializer_v594();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, RequestAbilityPacket packet) {
        buffer.writeByte(packet.getAbility().ordinal());
        buffer.writeByte(packet.getType().ordinal());
        buffer.writeBoolean(packet.isBoolValue());
        buffer.writeFloatLE(packet.getFloatValue());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, RequestAbilityPacket packet) {
        packet.setAbility(Ability.values()[buffer.readByte()]);
        packet.setType(AbilityType.values()[buffer.readByte()]);
        packet.setBoolValue(buffer.readBoolean());
        packet.setFloatValue(buffer.readFloatLE());
    }
}