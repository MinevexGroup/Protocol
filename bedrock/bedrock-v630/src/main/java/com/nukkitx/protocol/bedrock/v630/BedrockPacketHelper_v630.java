package com.nukkitx.protocol.bedrock.v630;

import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.v622.BedrockPacketHelper_v622;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BedrockPacketHelper_v630 extends BedrockPacketHelper_v622 {

    public static BedrockPacketHelper_v630 INSTANCE = new BedrockPacketHelper_v630();

    @Override
    protected void registerSoundEvents() {
        super.registerSoundEvents();
        this.addSoundEvent(479, SoundEvent.CRAFTER_CRAFT);
        this.addSoundEvent(480, SoundEvent.CRAFTER_FAILED);
        this.addSoundEvent(481, SoundEvent.DECORATED_POT_INSERT);
        this.addSoundEvent(482, SoundEvent.DECORATED_POT_INSERT_FAILED);
        this.addSoundEvent(483, SoundEvent.CRAFTER_DISABLE_SLOT);
        this.addSoundEvent(490, SoundEvent.COPPER_BULB_ON);
        this.addSoundEvent(491, SoundEvent.COPPER_BULB_OFF);
        this.addSoundEvent(492, SoundEvent.UNDEFINED);
    }

    @Override
    protected void registerLevelEvents() {
        super.registerLevelEvents();
        this.addLevelEvent(0x4000 + 87, LevelEventType.PARTICLE_DUST_PLUME);
        this.addLevelEvent(0x4000 + 88, LevelEventType.PARTICLE_WHITE_SMOKE);
    }
}