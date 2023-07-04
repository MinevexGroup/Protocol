package com.nukkitx.protocol.bedrock.v594;

import com.nukkitx.protocol.bedrock.data.entity.EntityData;
import com.nukkitx.protocol.bedrock.v589.BedrockPacketHelper_v589;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BedrockPacketHelper_v594 extends BedrockPacketHelper_v589 {

    public static final BedrockPacketHelper_v594 INSTANCE = new BedrockPacketHelper_v594();

    @Override
    protected void registerEntityData() {
        super.registerEntityData();

        this.entityData.put(131, EntityData.COLLISION_BOX);
    }
}