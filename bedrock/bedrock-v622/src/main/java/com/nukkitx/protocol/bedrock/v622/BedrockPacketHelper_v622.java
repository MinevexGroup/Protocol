package com.nukkitx.protocol.bedrock.v622;

import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.bedrock.v618.BedrockPacketHelper_v618;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BedrockPacketHelper_v622 extends BedrockPacketHelper_v618 {

    public static final BedrockPacketHelper_v622 INSTANCE = new BedrockPacketHelper_v622();

    @Override
    protected void registerEntityFlags() {
        super.registerEntityFlags();
        this.addEntityFlag(115, EntityFlag.TIMER_FLAG_1);
        this.addEntityFlag(116, EntityFlag.TIMER_FLAG_2);
        this.addEntityFlag(117, EntityFlag.TIMER_FLAG_3);
    }

    @Override
    protected void registerSoundEvents() {
        super.registerSoundEvents();
        this.addSoundEvent(477, SoundEvent.BOTTLE_FILL);
        this.addSoundEvent(478, SoundEvent.BOTTLE_EMPTY);
        this.addSoundEvent(479, SoundEvent.UNDEFINED);
    }
}