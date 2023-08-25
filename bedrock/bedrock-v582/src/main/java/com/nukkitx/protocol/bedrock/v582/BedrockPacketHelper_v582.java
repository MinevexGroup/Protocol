package com.nukkitx.protocol.bedrock.v582;

import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.command.CommandParam;
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

        this.addCommandParam(44, CommandParam.STRING);
        this.addCommandParam(52, CommandParam.BLOCK_POSITION);
        this.addCommandParam(53, CommandParam.POSITION);
        this.addCommandParam(55, CommandParam.MESSAGE);
        this.addCommandParam(58, CommandParam.TEXT);
        this.addCommandParam(62, CommandParam.JSON);
        this.addCommandParam(71, CommandParam.BLOCK_STATES);
        this.addCommandParam(74, CommandParam.COMMAND);
    }

    @Override
    protected void registerContainerSlotTypes() {
        super.registerContainerSlotTypes();

        this.containerSlotTypes.put(61, ContainerSlotType.SMITING_TABLE_TEMPLATE);
    }

    @Override
    protected void registerCommandParams() {
        addCommandParam(1, CommandParam.INT);
        addCommandParam(3, CommandParam.FLOAT);
        addCommandParam(4, CommandParam.VALUE);
        addCommandParam(5, CommandParam.WILDCARD_INT);
        addCommandParam(6, CommandParam.OPERATOR);
        addCommandParam(7, CommandParam.COMPARE_OPERATOR);
        addCommandParam(8, CommandParam.TARGET);
        addCommandParam(10, CommandParam.WILDCARD_TARGET);
        addCommandParam(17, CommandParam.FILE_PATH);
        addCommandParam(23, CommandParam.INT_RANGE);
        addCommandParam(44, CommandParam.STRING);
        addCommandParam(52, CommandParam.BLOCK_POSITION);
        addCommandParam(53, CommandParam.POSITION);
        addCommandParam(55, CommandParam.MESSAGE);
        addCommandParam(58, CommandParam.TEXT);
        addCommandParam(62, CommandParam.JSON);
        addCommandParam(71, CommandParam.BLOCK_STATES);
        addCommandParam(74, CommandParam.COMMAND);
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