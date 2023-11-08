package com.nukkitx.protocol.bedrock.v630.serializer;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.inventory.InventoryLayout;
import com.nukkitx.protocol.bedrock.data.inventory.InventoryTabLeft;
import com.nukkitx.protocol.bedrock.data.inventory.InventoryTabRight;
import com.nukkitx.protocol.bedrock.packet.SetPlayerInventoryOptionsPacket;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetPlayerInventoryOptionsSerializer_v630 implements BedrockPacketSerializer<SetPlayerInventoryOptionsPacket> {

    public static final SetPlayerInventoryOptionsSerializer_v630 INSTANCE = new SetPlayerInventoryOptionsSerializer_v630();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, SetPlayerInventoryOptionsPacket packet) {
        VarInts.writeInt(buffer, packet.getLeftTab().ordinal());
        VarInts.writeInt(buffer, packet.getRightTab().ordinal());
        buffer.writeBoolean(packet.isFiltering());
        VarInts.writeInt(buffer, packet.getLayout().ordinal());
        VarInts.writeInt(buffer, packet.getCraftingLayout().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, SetPlayerInventoryOptionsPacket packet) {
        packet.setLeftTab(InventoryTabLeft.VALUES[VarInts.readInt(buffer)]);
        packet.setRightTab(InventoryTabRight.VALUES[VarInts.readInt(buffer)]);
        packet.setFiltering(buffer.readBoolean());
        packet.setLayout(InventoryLayout.VALUES[VarInts.readInt(buffer)]);
        packet.setCraftingLayout(InventoryLayout.VALUES[VarInts.readInt(buffer)]);
    }
}