package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.BedrockPacketType;
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Kaooot
 * @version 1.0
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class AgentAnimationPacket extends BedrockPacket {

    private byte animation;
    private long runtimeEntityId;

    @Override
    public boolean handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.AGENT_ANIMATION;
    }
}
