package com.nukkitx.protocol.bedrock.data.command;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ChainedSubCommandData {
    private final String name;
    private final List<Value> values = new ObjectArrayList<>();

    @Data
    public static class Value {
        private final String first;
        private final String second;
    }
}