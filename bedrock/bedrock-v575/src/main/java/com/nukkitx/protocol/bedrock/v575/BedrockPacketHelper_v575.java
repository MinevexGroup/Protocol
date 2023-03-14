package com.nukkitx.protocol.bedrock.v575;

import com.nukkitx.protocol.bedrock.data.Ability;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.bedrock.v567.BedrockPacketHelper_v567patch;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BedrockPacketHelper_v575 extends BedrockPacketHelper_v567patch {

    public static final BedrockPacketHelper_v575 INSTANCE = new BedrockPacketHelper_v575();

    @Override
    protected void registerEntityFlags() {
        super.registerEntityFlags();

        this.addEntityFlag(110, EntityFlag.SCENTING);
        this.addEntityFlag(111, EntityFlag.RISING);
        this.addEntityFlag(112, EntityFlag.SEARCHING);
        this.addEntityFlag(113, EntityFlag.FEELING_HAPPY);
    }

    @Override
    protected void registerSoundEvents() {
        super.registerSoundEvents();

        this.addSoundEvent(462, SoundEvent.BRUSH);
        this.addSoundEvent(463, SoundEvent.BRUSH_COMPLETED);
        this.addSoundEvent(464, SoundEvent.SHATTER_DECORATED_POT);
        this.addSoundEvent(465, SoundEvent.BREAK_DECORATED_POT);
        this.addSoundEvent(466, SoundEvent.UNDEFINED);
    }

    @Override
    protected void registerLevelEvents() {
        super.registerLevelEvents();

        this.addLevelEvent(0x4000 + 85, LevelEventType.PARTICLE_BRUSH_DUST);
    }

    @Override
    protected void registerAbilities() {
        super.registerAbilities();

        this.abilities.put(18, Ability.PRIVILEGED_BUILDER);
    }
}
