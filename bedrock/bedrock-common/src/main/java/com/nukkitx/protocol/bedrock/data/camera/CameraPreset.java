package com.nukkitx.protocol.bedrock.data.camera;

import com.nukkitx.math.vector.Vector3f;
import com.nukkitx.protocol.util.OptionalBoolean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CameraPreset {

    private String identifier;
    private String parentPreset = "";
    // All the values below are optional, and will not be encoded if null is used
    private Vector3f pos;
    private Float yaw;
    private Float pitch;
    private CameraAudioListener listener;
    private OptionalBoolean playEffect;
}