package com.nukkitx.protocol.bedrock.v618.serializer;

import com.nukkitx.math.vector.Vector3f;
import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.camera.CameraAudioListener;
import com.nukkitx.protocol.bedrock.data.camera.CameraPreset;
import com.nukkitx.protocol.bedrock.packet.CameraPresetsPacket;
import com.nukkitx.protocol.util.OptionalBoolean;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraPresetsSerializer_v618 implements BedrockPacketSerializer<CameraPresetsPacket> {

    public static final CameraPresetsSerializer_v618 INSTANCE = new CameraPresetsSerializer_v618();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, CameraPresetsPacket packet) {
        helper.writeArray(buffer, packet.getPresets(), this::writePreset);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, CameraPresetsPacket packet) {
        helper.readArray(buffer, packet.getPresets(), this::readPreset);
    }

    public void writePreset(ByteBuf buffer, BedrockPacketHelper helper, CameraPreset preset) {
        helper.writeString(buffer, preset.getIdentifier());
        helper.writeString(buffer, preset.getParentPreset());
        helper.writeOptionalNull(buffer, preset.getPos(), (buf, pos) -> buf.writeFloatLE(pos.getX()));
        helper.writeOptionalNull(buffer, preset.getPos(), (buf, pos) -> buf.writeFloatLE(pos.getY()));
        helper.writeOptionalNull(buffer, preset.getPos(), (buf, pos) -> buf.writeFloatLE(pos.getZ()));
        helper.writeOptionalNull(buffer, preset.getPitch(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, preset.getYaw(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, preset.getListener(), (buf, listener) -> buf.writeByte(listener.ordinal()));
        helper.writeOptional(buffer, OptionalBoolean::isPresent, preset.getPlayEffect(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
    }

    public CameraPreset readPreset(ByteBuf buffer, BedrockPacketHelper helper) {
        String identifier = helper.readString(buffer);
        String parentPreset = helper.readString(buffer);

        Float x = helper.readOptional(buffer, null, ByteBuf::readFloatLE);
        Float y = helper.readOptional(buffer, null, ByteBuf::readFloatLE);
        Float z = helper.readOptional(buffer, null, ByteBuf::readFloatLE);
        Vector3f pos = x == null || y == null || z == null ? null : Vector3f.from(x, y, z);

        Float pitch = helper.readOptional(buffer, null, ByteBuf::readFloatLE);
        Float yaw = helper.readOptional(buffer, null, ByteBuf::readFloatLE);

        CameraAudioListener listener = helper.readOptional(buffer, null, buf -> CameraAudioListener.values()[buf.readUnsignedByte()]);
        OptionalBoolean effects = helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean()));
        return new CameraPreset(identifier, parentPreset, pos, pitch, yaw, listener, effects);
    }
}