package com.nukkitx.protocol.bedrock.v575.serializer;

import com.nukkitx.math.vector.Vector2f;
import com.nukkitx.math.vector.Vector3f;
import com.nukkitx.nbt.NbtMap;
import com.nukkitx.nbt.NbtMapBuilder;
import com.nukkitx.nbt.NbtType;
import com.nukkitx.network.util.Preconditions;
import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.camera.CameraEase;
import com.nukkitx.protocol.bedrock.data.camera.CameraFadeInstruction;
import com.nukkitx.protocol.bedrock.data.camera.CameraSetInstruction;
import com.nukkitx.protocol.bedrock.packet.CameraInstructionPacket;
import com.nukkitx.protocol.util.OptionalBoolean;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

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
        NbtMapBuilder tag = NbtMap.builder();

        if (packet.getSetInstruction() != null) {
            CameraSetInstruction set = packet.getSetInstruction();

            NbtMapBuilder builder = NbtMap.builder()
                    .putInt("preset", set.getPreset());

            if (set.getEase() != null) {
                builder.putCompound("ease", NbtMap.builder()
                        .putString("type", set.getEase().getEaseType().getSerializeName())
                        .putFloat("time", set.getEase().getTime())
                        .build());
            }

            if (set.getPos() != null) {
                builder.putCompound("pos", NbtMap.builder()
                        .putList("pos", NbtType.FLOAT, set.getPos().getX(), set.getPos().getY(), set.getPos().getZ())
                        .build());
            }

            if (set.getRot() != null) {
                builder.putCompound("rot", NbtMap.builder()
                        .putFloat("x", set.getRot().getX()) // pitch
                        .putFloat("y", set.getRot().getY()) // yaw
                        .build());
            }

            if (set.getDefaultPreset().isPresent()) {
                builder.putBoolean("default", set.getDefaultPreset().getAsBoolean());
            }

            tag.put("set", builder.build());
        }

        if (packet.getClear().isPresent()) {
            tag.putBoolean("clear", packet.getClear().getAsBoolean());
        }

        if (packet.getFadeInstruction() != null) {
            CameraFadeInstruction fade = packet.getFadeInstruction();
            NbtMapBuilder builder = NbtMap.builder();

            if (fade.getTimeData() != null) {
                builder.putCompound("time", NbtMap.builder()
                        .putFloat("fadeIn", fade.getTimeData().getFadeInTime())
                        .putFloat("hold", fade.getTimeData().getWaitTime())
                        .putFloat("fadeOut", fade.getTimeData().getFadeOutTime())
                        .build());
            }

            if (fade.getColor() != null) {
                builder.putCompound("color", NbtMap.builder()
                        .putFloat("r", fade.getColor().getRed() / 255F)
                        .putFloat("g", fade.getColor().getBlue() / 255F) // game is sending blue as green and green as blue
                        .putFloat("b", fade.getColor().getGreen() / 255F)
                        .build());
            }

            tag.put("fade", builder.build());
        }
        helper.writeTag(buffer, tag.build());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper,
                            CameraInstructionPacket packet) {
        NbtMap tag = helper.readTag(buffer);

        if (tag.containsKey("set", NbtType.COMPOUND)) {
            CameraSetInstruction set = new CameraSetInstruction();
            NbtMap setTag = tag.getCompound("set");

            int runtimeId = setTag.getInt("preset");
            set.setPreset(runtimeId);

            if (setTag.containsKey("ease", NbtType.COMPOUND)) {
                NbtMap easeTag = setTag.getCompound("ease");
                CameraEase type = CameraEase.fromName(easeTag.getString("type"));
                float time = easeTag.getFloat("time");
                set.setEase(new CameraSetInstruction.EaseData(type, time));
            }

            if (setTag.containsKey("pos", NbtType.COMPOUND)) {
                List<Float> floats = setTag.getCompound("pos").getList("pos", NbtType.FLOAT);

                float x = floats.size() > 0 ? floats.get(0) : 0;
                float y = floats.size() > 1 ? floats.get(1) : 0;
                float z = floats.size() > 2 ? floats.get(2) : 0;
                set.setPos(Vector3f.from(x, y, z));
            }

            if (setTag.containsKey("rot", NbtType.COMPOUND)) {
                NbtMap rot = setTag.getCompound("rot");
                float pitch = rot.containsKey("x", NbtType.FLOAT) ? rot.getFloat("x") : 0;
                float yaw = rot.containsKey("y", NbtType.FLOAT) ? rot.getFloat("y") : 0;
                set.setRot(Vector2f.from(pitch, yaw));
            }

            if (setTag.containsKey("default", NbtType.BYTE)) {
                set.setDefaultPreset(OptionalBoolean.of(setTag.getBoolean("default")));
            }
            packet.setSetInstruction(set);
        }

        if (tag.containsKey("clear", NbtType.BYTE)) {
            packet.setClear(OptionalBoolean.of(tag.getBoolean("clear")));
        }

        if (tag.containsKey("fade", NbtType.COMPOUND)) {
            CameraFadeInstruction fade = new CameraFadeInstruction();
            NbtMap fadeTag = tag.getCompound("fade");

            if (fadeTag.containsKey("time", NbtType.COMPOUND)) {
                NbtMap timeTag = fadeTag.getCompound("time");
                float fadeIn = timeTag.getFloat("fadeIn");
                float wait = timeTag.getFloat("hold");
                float fadeout = timeTag.getFloat("fadeOut");
                fade.setTimeData(new CameraFadeInstruction.TimeData(fadeIn, wait, fadeout));
            }

            if (fadeTag.containsKey("color", NbtType.COMPOUND)) {
                NbtMap colorTag = tag.getCompound("color");

                fade.setColor(new Color(
                        (int) (colorTag.getFloat("r") * 255),
                        (int) (colorTag.getFloat("b") * 255), // game is sending blue as green and green as blue
                        (int) (colorTag.getFloat("g") * 255)
                ));
            }

            packet.setFadeInstruction(fade);
        }
    }
}