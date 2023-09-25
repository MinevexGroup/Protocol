package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.BedrockPacketType;
import com.nukkitx.protocol.bedrock.data.camera.CameraFadeInstruction;
import com.nukkitx.protocol.bedrock.data.camera.CameraSetInstruction;
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import com.nukkitx.protocol.util.OptionalBoolean;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Kaooot
 * @version 1.0
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class CameraInstructionPacket extends BedrockPacket {

    private CameraSetInstruction setInstruction;
    private CameraFadeInstruction fadeInstruction;
    private OptionalBoolean clear = OptionalBoolean.empty();

    @Override
    public boolean handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CAMERA_INSTRUCTION;
    }

    public void setClear(boolean clear) {
        this.clear = OptionalBoolean.of(clear);
    }

    public void setClear(OptionalBoolean clear) {
        this.clear = clear;
    }
}