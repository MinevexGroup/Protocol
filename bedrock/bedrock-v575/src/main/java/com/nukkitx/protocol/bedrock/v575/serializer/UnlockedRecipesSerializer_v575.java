package com.nukkitx.protocol.bedrock.v575.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.UnlockedRecipesPacket;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnlockedRecipesSerializer_v575
    implements BedrockPacketSerializer<UnlockedRecipesPacket> {

    public static final UnlockedRecipesSerializer_v575 INSTANCE =
        new UnlockedRecipesSerializer_v575();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper,
                          UnlockedRecipesPacket packet) {
        BedrockPacketSerializer.super.serialize(buffer, helper, packet);
        buffer.writeBoolean(packet.isUnlockedNotification());
        helper.writeArray(buffer, packet.getUnlockedRecipes(), helper::writeString);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper,
                            UnlockedRecipesPacket packet) {
        BedrockPacketSerializer.super.deserialize(buffer, helper, packet);
        packet.setUnlockedNotification(buffer.readBoolean());
        helper.readArray(buffer, packet.getUnlockedRecipes(), helper::readString);
    }
}