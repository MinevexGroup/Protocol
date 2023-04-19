package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.BedrockPacketType;
import com.nukkitx.protocol.bedrock.data.TrimMaterial;
import com.nukkitx.protocol.bedrock.data.TrimPattern;
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Kaooot
 * @version 1.0
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class TrimDataPacket extends BedrockPacket {

    private final List<TrimPattern> patterns = new ObjectArrayList<>();
    private final List<TrimMaterial> materials = new ObjectArrayList<>();

    @Override
    public boolean handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.TRIM_DATA;
    }
}
