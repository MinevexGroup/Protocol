package com.nukkitx.protocol.bedrock.v630.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.data.StoreOfferRedirectType;
import com.nukkitx.protocol.bedrock.packet.ShowStoreOfferPacket;
import com.nukkitx.protocol.bedrock.v291.serializer.ShowStoreOfferSerializer_v291;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShowStoreOfferSerializer_v630 extends ShowStoreOfferSerializer_v291 {

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, ShowStoreOfferPacket packet) {
        helper.writeString(buffer, packet.getOfferId());
        buffer.writeByte(packet.getRedirectType().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, ShowStoreOfferPacket packet) {
        packet.setOfferId(packet.getOfferId());
        packet.setRedirectType(StoreOfferRedirectType.values()[buffer.readUnsignedByte()]);
    }
}