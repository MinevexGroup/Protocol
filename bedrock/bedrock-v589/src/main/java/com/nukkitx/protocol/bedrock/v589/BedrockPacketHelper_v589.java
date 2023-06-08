package com.nukkitx.protocol.bedrock.v589;

import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.v582.BedrockPacketHelper_v582;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BedrockPacketHelper_v589 extends BedrockPacketHelper_v582 {

    public static final BedrockPacketHelper_v589 INSTANCE = new BedrockPacketHelper_v589();

    @Override
    protected void registerSoundEvents() {
        super.registerSoundEvents();
        this.addSoundEvent(466, SoundEvent.SNIFFER_EGG_CRACK);
        this.addSoundEvent(467, SoundEvent.SNIFFER_EGG_HATCHED);
        this.addSoundEvent(468, SoundEvent.WAXED_SIGN_INTERACT_FAIL);
        this.addSoundEvent(469, SoundEvent.RECORD_RELIC);
    }
}