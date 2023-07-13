package com.nukkitx.protocol.bedrock.v448.serializer;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.data.command.CommandData;
import com.nukkitx.protocol.bedrock.data.command.CommandEnumData;
import com.nukkitx.protocol.bedrock.data.command.CommandOverloadData;
import com.nukkitx.protocol.bedrock.data.command.CommandParamData;
import com.nukkitx.protocol.bedrock.v388.serializer.AvailableCommandsSerializer_v388;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AvailableCommandsSerializer_v448 extends AvailableCommandsSerializer_v388 {

    public static final AvailableCommandsSerializer_v448 INSTANCE = new AvailableCommandsSerializer_v448();

    @Override
    protected void writeCommand(ByteBuf buffer, BedrockPacketHelper helper, CommandData commandData,
                                List<CommandEnumData> enums, List<CommandEnumData> softEnums, List<String> postFixes) {
        helper.writeString(buffer, commandData.getName());
        helper.writeString(buffer, commandData.getDescription());
        int flags = 0;
        for (CommandData.Flag flag : commandData.getFlags()) {
            flags |= 1 << flag.ordinal();
        }
        buffer.writeShortLE(flags);
        buffer.writeByte(commandData.getPermission());

        CommandEnumData aliases = commandData.getAliases();
        buffer.writeIntLE(enums.indexOf(aliases));

        CommandOverloadData[] overloads = commandData.getOverloads();
        VarInts.writeUnsignedInt(buffer, overloads.length);
        for (CommandOverloadData overload : overloads) {
            buffer.writeBoolean(overload.isChaining());
            VarInts.writeUnsignedInt(buffer, overload.getOverloads().length);
            for (CommandParamData param : overload.getOverloads()) {
                this.writeParameter(buffer, helper, param, enums, softEnums, postFixes);
            }
        }
    }

    @Override
    protected CommandData.Builder readCommand(ByteBuf buffer, BedrockPacketHelper helper, List<CommandEnumData> enums, List<CommandEnumData> softEnums, List<String> postFixes) {
        String name = helper.readString(buffer);
        String description = helper.readString(buffer);
        int flags = buffer.readUnsignedShortLE();
        byte permissions = buffer.readByte();
        int aliasesIndex = buffer.readIntLE();

        CommandOverloadData[] overloads = new CommandOverloadData[VarInts.readUnsignedInt(buffer)];
        for (int i = 0; i < overloads.length; i++) {
            boolean chaining = buffer.readBoolean();
            CommandParamData[] params = new CommandParamData[VarInts.readUnsignedInt(buffer)];
            overloads[i] = new CommandOverloadData(chaining, params);
            for (int i2 = 0; i2 < params.length; i2++) {
                params[i2] = readParameter(buffer, helper, enums, softEnums, postFixes);
            }
        }
        return new CommandData.Builder(name, description, flags, permissions, aliasesIndex, Collections.emptyList(), overloads);
    }
}
