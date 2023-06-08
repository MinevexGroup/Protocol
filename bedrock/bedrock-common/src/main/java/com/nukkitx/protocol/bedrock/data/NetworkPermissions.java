package com.nukkitx.protocol.bedrock.data;

import lombok.Value;

/**
 * @author Kaooot
 * @version 1.0
 */
@Value
public class NetworkPermissions {

    public static final NetworkPermissions DEFAULT = new NetworkPermissions(false);

    boolean serverAuthSounds;
}
