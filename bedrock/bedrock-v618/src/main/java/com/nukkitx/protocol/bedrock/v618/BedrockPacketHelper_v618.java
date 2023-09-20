package com.nukkitx.protocol.bedrock.v618;

import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.v594.BedrockPacketHelper_v594;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BedrockPacketHelper_v618 extends BedrockPacketHelper_v594 {

    public static final BedrockPacketHelper_v618 INSTANCE = new BedrockPacketHelper_v618();

    @Override
    protected void registerSoundEvents() {
        super.registerSoundEvents();
        this.addSoundEvent(470, SoundEvent.BUMP);
        this.addSoundEvent(471, SoundEvent.PUMPKIN_CARVE);
        this.addSoundEvent(472, SoundEvent.CONVERT_HUSK_TO_ZOMBIE);
        this.addSoundEvent(473, SoundEvent.PIG_DEATH);
        this.addSoundEvent(474, SoundEvent.HOGLIN_CONVERT_TO_ZOMBIE);
        this.addSoundEvent(475, SoundEvent.AMBIENT_UNDERWATER_ENTER);
        this.addSoundEvent(476, SoundEvent.AMBIENT_UNDERWATER_EXIT);
        this.addSoundEvent(477, SoundEvent.UNDEFINED);
    }

    @Override
    protected void registerLevelEvents() {
        super.registerLevelEvents();
        this.addLevelEvent(0x4000 + 86, LevelEventType.PARTICLE_CHERRY_LEAVES);
    }
}