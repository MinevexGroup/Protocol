package com.nukkitx.protocol.bedrock.data.camera;

import com.nukkitx.math.vector.Vector2f;
import com.nukkitx.math.vector.Vector3f;
import com.nukkitx.protocol.util.OptionalBoolean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CameraSetInstruction {

    private int preset;
    private EaseData ease;
    private Vector3f pos;
    private Vector2f rot;
    private Vector3f facing;
    private OptionalBoolean defaultPreset = OptionalBoolean.empty();

    @Data
    public static class EaseData {
        private final CameraEase easeType;
        private final float time;
    }
}