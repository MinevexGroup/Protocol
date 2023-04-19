package com.nukkitx.protocol.bedrock.v582;

import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.v575.BedrockPacketHelper_v575;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BedrockPacketHelper_v582 extends BedrockPacketHelper_v575 {

    public static final BedrockPacketHelper_v582 INSTANCE = new BedrockPacketHelper_v582();

    @Override
    protected void registerContainerSlotTypes() {
        super.registerContainerSlotTypes();

        this.containerSlotTypes.put(61, ContainerSlotType.SMITING_TABLE_TEMPLATE);
    }

    @Override
    protected void registerLevelEvents() {
        super.registerLevelEvents();

        this.addLevelEvent(1000 + 67, LevelEventType.SOUND_AMETHYST_RESONATE);
        this.addLevelEvent(0x4000 + 103, LevelEventType.PARTICLE_BREAK_BLOCK_DOWN);
        this.addLevelEvent(0x4000 + 104, LevelEventType.PARTICLE_BREAK_BLOCK_UP);
        this.addLevelEvent(0x4000 + 105, LevelEventType.PARTICLE_BREAK_BLOCK_NORTH);
        this.addLevelEvent(0x4000 + 106, LevelEventType.PARTICLE_BREAK_BLOCK_SOUTH);
        this.addLevelEvent(0x4000 + 107, LevelEventType.PARTICLE_BREAK_BLOCK_WEST);
        this.addLevelEvent(0x4000 + 108, LevelEventType.PARTICLE_BREAK_BLOCK_EAST);
        this.addLevelEvent(0x4000 + 109, LevelEventType.ALL_PLAYERS_SLEEPING);
        this.addLevelEvent(9810, LevelEventType.JUMP_PREVENTED);
    }
}