package com.nukkitx.protocol.bedrock.v594.serializer;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.data.command.*;
import com.nukkitx.protocol.bedrock.packet.AvailableCommandsPacket;
import com.nukkitx.protocol.bedrock.v448.serializer.AvailableCommandsSerializer_v448;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nukkitx.network.util.Preconditions.checkArgument;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AvailableCommandsSerializer_v594 extends AvailableCommandsSerializer_v448 {

    public static final AvailableCommandsSerializer_v594 INSTANCE = new AvailableCommandsSerializer_v594();

    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, AvailableCommandsPacket packet) {
        Set<String> enumValuesSet = new ObjectOpenHashSet<>();
        List<String> subCommandValues = new ObjectArrayList<>();
        Set<String> postfixSet = new ObjectOpenHashSet<>();
        Set<CommandEnumData> enumsSet = new ObjectOpenHashSet<>();
        List<ChainedSubCommandData> subCommandData = new ObjectArrayList<>();
        Set<CommandEnumData> softEnumsSet = new ObjectOpenHashSet<>();

        // Get all enum values
        for (CommandData data : packet.getCommands()) {
            if (data.getAliases() != null) {
                Collections.addAll(enumValuesSet, data.getAliases().getValues());
                enumsSet.add(data.getAliases());
            }

            for (ChainedSubCommandData subcommand : data.getSubcommands()) {
                if (subCommandData.contains(subcommand)) {
                    continue;
                }

                subCommandData.add(subcommand);
                for (ChainedSubCommandData.Value value : subcommand.getValues()) {
                    if (subCommandValues.contains(value.getFirst())) {
                        subCommandValues.add(value.getFirst());
                    }

                    if (subCommandValues.contains(value.getSecond())) {
                        subCommandValues.add(value.getSecond());
                    }
                }
            }

            for (CommandOverloadData overload : data.getOverloads()) {
                for (CommandParamData parameter : overload.getOverloads()) {
                    CommandEnumData commandEnumData = parameter.getEnumData();
                    if (commandEnumData != null) {
                        if (commandEnumData.isSoft()) {
                            softEnumsSet.add(commandEnumData);
                        } else {
                            Collections.addAll(enumValuesSet, commandEnumData.getValues());
                            enumsSet.add(commandEnumData);
                        }
                    }

                    String postfix = parameter.getPostfix();
                    if (postfix != null) {
                        postfixSet.add(postfix);
                    }
                }
            }
        }

        // Add Constraint Enums
        for (CommandEnumData enumData : packet.getConstraints().stream().map(
                CommandEnumConstraintData::getEnumData).collect(Collectors.toList())) {
            if (enumData.isSoft()) {
                softEnumsSet.add(enumData);
            } else {
                enumsSet.add(enumData);
            }
            enumValuesSet.addAll(Arrays.asList(enumData.getValues()));
        }

        List<String> enumValues = new ObjectArrayList<>(enumValuesSet);
        List<String> postFixes = new ObjectArrayList<>(postfixSet);
        List<CommandEnumData> enums = new ObjectArrayList<>(enumsSet);
        List<CommandEnumData> softEnums = new ObjectArrayList<>(softEnumsSet);

        helper.writeArray(buffer, enumValues, helper::writeString);
        helper.writeArray(buffer, subCommandValues, helper::writeString);
        helper.writeArray(buffer, postFixes, helper::writeString);

        this.writeEnums(buffer, helper, enumValues, enums);

        helper.writeArray(buffer, subCommandData, (buf, value) -> this.writeSubCommand(buf, helper, subCommandValues, value));

        helper.writeArray(buffer, packet.getCommands(), (buf, command) -> {
            this.writeCommand(buffer, helper, command, enums, softEnums, postFixes, subCommandData);
        });

        helper.writeArray(buffer, softEnums, helper::writeCommandEnum);

        // Constraints
        helper.writeArray(buffer, packet.getConstraints(), (buf, constraint) -> {
            helper.writeCommandEnumConstraints(buf, constraint, enums, enumValues);
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockPacketHelper helper, AvailableCommandsPacket packet) {
        List<String> enumValues = new ObjectArrayList<>();
        List<String> postFixes = new ObjectArrayList<>();
        List<String> subCommandValues = new ObjectArrayList<>();
        List<CommandEnumData> enums = new ObjectArrayList<>();
        List<ChainedSubCommandData> subCommands = new ObjectArrayList<>();
        List<CommandData.Builder> commands = new ObjectArrayList<>();
        List<CommandEnumData> softEnums = new ObjectArrayList<>();

        helper.readArray(buffer, enumValues, helper::readString);
        helper.readArray(buffer, subCommandValues, helper::readString);
        helper.readArray(buffer, postFixes, helper::readString);

        this.readEnums(buffer, helper, enumValues, enums);

        helper.readArray(buffer, subCommands, buf -> this.readSubCommand(buffer, helper, subCommandValues));

        helper.readArray(buffer, commands, buf -> this.readCommand(buffer, helper, enums, softEnums, postFixes, subCommands));

        helper.readArray(buffer, softEnums, buf -> helper.readCommandEnum(buffer, true));

        // Generate command data
        for (CommandData.Builder command : commands) {
            int flags = command.getFlags();
            List<CommandData.Flag> flagList = new ObjectArrayList<>();
            for (int i = 0; i < 6; i++) {
                if ((flags & (1 << i)) != 0) {
                    flagList.add(FLAGS[i]);
                }
            }
            int aliasesIndex = command.getAliases();
            CommandEnumData aliases = aliasesIndex == -1 ? null : enums.get(aliasesIndex);

            packet.getCommands().add(new CommandData(command.getName(), command.getDescription(),
                    flagList, command.getPermission(), aliases, subCommands, command.getOverloads()));
        }

        // Constraints
        helper.readArray(buffer, packet.getConstraints(), buf -> helper.readCommandEnumConstraints(buf, enums, enumValues));
    }

    protected void writeCommand(ByteBuf buffer, BedrockPacketHelper helper, CommandData commandData, List<CommandEnumData> enums, List<CommandEnumData> softEnums, List<String> postFixes, List<ChainedSubCommandData> subCommands) {
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

        helper.writeArray(buffer, commandData.getSubcommands(), (buf, subcommand) -> {
            int index = subCommands.indexOf(subcommand);
            checkArgument(index > -1, "Invalid subcommand index: " + subcommand);
            buf.writeShortLE(index);
        });

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

    protected CommandData.Builder readCommand(ByteBuf buffer, BedrockPacketHelper helper, List<CommandEnumData> enums, List<CommandEnumData> softEnums, List<String> postFixes, List<ChainedSubCommandData> subCommandsList) {
        String name = helper.readString(buffer);
        String description = helper.readString(buffer);
        int flags = buffer.readUnsignedShortLE();
        byte permissions = buffer.readByte();
        int aliasesIndex = buffer.readIntLE();

        List<ChainedSubCommandData> subcommands = new ObjectArrayList<>();
        helper.readArray(buffer, subcommands, (buf, help) -> {
            int index = buf.readUnsignedShortLE();
            return subCommandsList.get(index);
        });

        CommandOverloadData[] overloads = new CommandOverloadData[VarInts.readUnsignedInt(buffer)];
        for (int i = 0; i < overloads.length; i++) {
            boolean chaining = buffer.readBoolean();
            CommandParamData[] params = new CommandParamData[VarInts.readUnsignedInt(buffer)];
            overloads[i] = new CommandOverloadData(chaining, params);
            for (int i2 = 0; i2 < params.length; i2++) {
                params[i2] = readParameter(buffer, helper, enums, softEnums, postFixes);
            }
        }
        return new CommandData.Builder(name, description, flags, permissions, aliasesIndex, subCommandsList, overloads);
    }

    protected void writeSubCommand(ByteBuf buffer, BedrockPacketHelper helper, List<String> values, ChainedSubCommandData data) {
        helper.writeString(buffer, data.getName());
        helper.writeArray(buffer, data.getValues(), (buf, val) -> {
            int first = values.indexOf(val.getFirst());
            checkArgument(first > -1, "Invalid enum value detected: " + val.getFirst());

            int second = values.indexOf(val.getSecond());
            checkArgument(second > -1, "Invalid enum value detected: " + val.getSecond());

            buf.writeShortLE(first);
            buf.writeShortLE(second);
        });
    }

    protected ChainedSubCommandData readSubCommand(ByteBuf buffer, BedrockPacketHelper helper, List<String> values) {
        String name = helper.readString(buffer);
        ChainedSubCommandData data = new ChainedSubCommandData(name);

        helper.readArray(buffer, data.getValues(), buf -> {
            int first = buf.readUnsignedShortLE();
            int second = buf.readUnsignedShortLE();
            return new ChainedSubCommandData.Value(values.get(first), values.get(second));
        });
        return data;
    }
}
