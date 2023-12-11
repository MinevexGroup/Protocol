package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.BedrockPacketType;
import com.nukkitx.protocol.bedrock.data.inventory.InventoryLayout;
import com.nukkitx.protocol.bedrock.data.inventory.InventoryTabLeft;
import com.nukkitx.protocol.bedrock.data.inventory.InventoryTabRight;
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Kaooot
 * @version 1.0
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
@ToString(doNotUseGetters = true)
public class SetPlayerInventoryOptionsPacket extends BedrockPacket {

    private InventoryTabLeft leftTab;
    private InventoryTabRight rightTab;
    private boolean filtering;
    private InventoryLayout layout;
    private InventoryLayout craftingLayout;

    @Override
    public boolean handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_PLAYER_INVENTORY_OPTIONS;
    }
}