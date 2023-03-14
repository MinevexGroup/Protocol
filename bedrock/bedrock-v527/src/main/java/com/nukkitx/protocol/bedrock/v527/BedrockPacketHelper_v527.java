package com.nukkitx.protocol.bedrock.v527;

import com.nukkitx.protocol.bedrock.data.Ability;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.command.CommandParam;
import com.nukkitx.protocol.bedrock.data.entity.EntityData;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.bedrock.v503.BedrockPacketHelper_v503;

public class BedrockPacketHelper_v527 extends BedrockPacketHelper_v503 {
    public static final BedrockPacketHelper_v527 INSTANCE = new BedrockPacketHelper_v527();

    @Override
    protected void registerCommandParams() {
        this.addCommandParam(1, CommandParam.INT);
        this.addCommandParam(3, CommandParam.FLOAT);
        this.addCommandParam(4, CommandParam.VALUE);
        this.addCommandParam(5, CommandParam.WILDCARD_INT);
        this.addCommandParam(6, CommandParam.OPERATOR);
        this.addCommandParam(7, CommandParam.COMPARE_OPERATOR);
        this.addCommandParam(8, CommandParam.TARGET);
        this.addCommandParam(10, CommandParam.WILDCARD_TARGET);

        this.addCommandParam(17, CommandParam.FILE_PATH);

        this.addCommandParam(39, CommandParam.STRING);
        this.addCommandParam(47, CommandParam.BLOCK_POSITION);
        this.addCommandParam(48, CommandParam.POSITION);
        this.addCommandParam(51, CommandParam.MESSAGE);
        this.addCommandParam(53, CommandParam.TEXT);
        this.addCommandParam(57, CommandParam.JSON);
        this.addCommandParam(67, CommandParam.BLOCK_STATES);
        this.addCommandParam(70, CommandParam.COMMAND);
    }

    @Override
    protected void registerEntityData() {
        super.registerEntityData();

        this.addEntityData(128, EntityData.PLAYER_LAST_DEATH_POS);
        this.addEntityData(129, EntityData.PLAYER_LAST_DEATH_DIMENSION);
        this.addEntityData(130, EntityData.PLAYER_HAS_DIED);
    }

    @Override
    protected void registerEntityFlags() {
        super.registerEntityFlags();

        this.addEntityFlag(106, EntityFlag.SONIC_BOOM);
    }


    @Override
    protected void registerLevelEvents() {
        super.registerLevelEvents();

        this.addLevelEvent(2000 + 39, LevelEventType.SONIC_EXPLOSION);
        this.addLevelEvent(0x4000 + 83, LevelEventType.PARTICLE_SCULK_SOUL);
    }

    @Override
    protected void registerSoundEvents() {
        super.registerSoundEvents();

        this.addSoundEvent(426, SoundEvent.IMITATE_WARDEN);
        this.addSoundEvent(427, SoundEvent.LISTENING_ANGRY);
        this.addSoundEvent(428, SoundEvent.ITEM_GIVEN);
        this.addSoundEvent(429, SoundEvent.ITEM_TAKEN);
        this.addSoundEvent(430, SoundEvent.DISAPPEARED);
        this.addSoundEvent(431, SoundEvent.REAPPEARED);
        this.addSoundEvent(433, SoundEvent.FROGSPAWN_HATCHED);
        this.addSoundEvent(434, SoundEvent.LAY_SPAWN);
        this.addSoundEvent(435, SoundEvent.FROGSPAWN_BREAK);
        this.addSoundEvent(436, SoundEvent.SONIC_BOOM);
        this.addSoundEvent(437, SoundEvent.SONIC_CHARGE);
        this.addSoundEvent(438, SoundEvent.ITEM_THROWN);
        this.addSoundEvent(439, SoundEvent.RECORD_5);
        this.addSoundEvent(440, SoundEvent.CONVERT_TO_FROG);
        this.addSoundEvent(441, SoundEvent.UNDEFINED);
    }

    @Override
    protected void registerAbilities() {
        this.abilities.put(0, Ability.BUILD);
        this.abilities.put(1, Ability.MINE);
        this.abilities.put(2, Ability.DOORS_AND_SWITCHES);
        this.abilities.put(3, Ability.OPEN_CONTAINERS);
        this.abilities.put(4, Ability.ATTACK_PLAYERS);
        this.abilities.put(5, Ability.ATTACK_MOBS);
        this.abilities.put(6, Ability.OPERATOR_COMMANDS);
        this.abilities.put(7, Ability.TELEPORT);
        this.abilities.put(8, Ability.INVULNERABLE);
        this.abilities.put(9, Ability.FLYING);
        this.abilities.put(10, Ability.MAY_FLY);
        this.abilities.put(11, Ability.INSTABUILD);
        this.abilities.put(12, Ability.LIGHTNING);
        this.abilities.put(13, Ability.FLY_SPEED);
        this.abilities.put(14, Ability.WALK_SPEED);
        this.abilities.put(15, Ability.MUTED);
        this.abilities.put(16, Ability.WORLD_BUILDER);
        this.abilities.put(17, Ability.NO_CLIP);
    }
}
