package com.nukkitx.protocol.bedrock.v388.serializer;

import com.nukkitx.protocol.bedrock.BedrockPacketHelper;
import com.nukkitx.protocol.bedrock.data.command.*;
import com.nukkitx.protocol.bedrock.packet.AvailableCommandsPacket;
import com.nukkitx.protocol.bedrock.v340.serializer.AvailableCommandsSerializer_v340;
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

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AvailableCommandsSerializer_v388 extends AvailableCommandsSerializer_v340 {
    public static final AvailableCommandsSerializer_v388 INSTANCE = new AvailableCommandsSerializer_v388();


    @Override
    public void serialize(ByteBuf buffer, BedrockPacketHelper helper, AvailableCommandsPacket packet) {
        Set<String> enumValuesSet = new ObjectOpenHashSet<>();
        Set<String> postfixSet = new ObjectOpenHashSet<>();
        Set<CommandEnumData> enumsSet = new ObjectOpenHashSet<>();
        Set<CommandEnumData> softEnumsSet = new ObjectOpenHashSet<>();

        // Get all enum values
        for (CommandData data : packet.getCommands()) {
            if (data.getAliases() != null) {
                Collections.addAll(enumValuesSet, data.getAliases().getValues());
                enumsSet.add(data.getAliases());
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
        for (CommandEnumData enumData : packet.getConstraints().stream().map(CommandEnumConstraintData::getEnumData).collect(Collectors.toList())) {
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
        helper.writeArray(buffer, postFixes, helper::writeString);

        this.writeEnums(buffer, helper, enumValues, enums);

        helper.writeArray(buffer, packet.getCommands(), (buf, command) -> {
            this.writeCommand(buffer, helper, command, enums, softEnums, postFixes);
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
        List<CommandEnumData> enums = new ObjectArrayList<>();
        List<CommandData.Builder> commands = new ObjectArrayList<>();
        List<CommandEnumData> softEnums = new ObjectArrayList<>();

        helper.readArray(buffer, enumValues, helper::readString);
        helper.readArray(buffer, postFixes, helper::readString);

        this.readEnums(buffer, helper, enumValues, enums);

        helper.readArray(buffer, commands, buf -> this.readCommand(buffer, helper, enums, softEnums, postFixes));

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
                    flagList, command.getPermission(), aliases, Collections.emptyList(), command.getOverloads()));
        }

        // Constraints
        helper.readArray(buffer, packet.getConstraints(), buf -> helper.readCommandEnumConstraints(buf, enums, enumValues));
    }
}