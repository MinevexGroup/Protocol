package com.nukkitx.protocol.bedrock.v589.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockSession;
import com.nukkitx.protocol.bedrock.data.NetworkPermissions;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.bedrock.v582.serializer.StartGameSerializer_v582;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v589 extends StartGameSerializer_v582 {

    public static final StartGameSerializer_v589 INSTANCE = new StartGameSerializer_v589();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, StartGamePacket packet, BedrockSession session) {
        super.serialize(buffer, helper, packet, session);

        buffer.writeBoolean(packet.getNetworkPermissions().isServerAuthSounds());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, StartGamePacket packet, BedrockSession session) {
        super.deserialize(buffer, helper, packet, session);

        packet.setNetworkPermissions(new NetworkPermissions(buffer.readBoolean()));
    }
}