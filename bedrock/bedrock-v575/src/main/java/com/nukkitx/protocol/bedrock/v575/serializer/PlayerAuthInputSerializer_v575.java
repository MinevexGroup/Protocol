package com.nukkitx.protocol.bedrock.v575.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.packet.PlayerAuthInputPacket;
import com.nukkitx.protocol.bedrock.v527.serializer.PlayerAuthInputSerializer_v527;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAuthInputSerializer_v575 extends PlayerAuthInputSerializer_v527 {

    public static final PlayerAuthInputSerializer_v575 INSTANCE =
        new PlayerAuthInputSerializer_v575();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper,
                          PlayerAuthInputPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeVector2f(buffer, packet.getAnalogMoveVector());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper,
                            PlayerAuthInputPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setAnalogMoveVector(helper.readVector2f(buffer));
    }
}