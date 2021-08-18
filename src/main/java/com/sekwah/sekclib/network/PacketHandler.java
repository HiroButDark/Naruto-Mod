package com.sekwah.sekclib.network;
import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.network.s2c.ServerUpdateCustomData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

public class PacketHandler {

    public static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel SYNC_CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(SekCLib.MOD_ID, "sync_channel"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .simpleChannel();

    public static void init() {
        SYNC_CHANNEL.registerMessage(0, ServerUpdateCustomData.class, ServerUpdateCustomData::encode, ServerUpdateCustomData::decode, ServerUpdateCustomData.Handler::handle);
    }

    public static void sendToPlayer(Object obj, ServerPlayer player) {
        SYNC_CHANNEL.sendTo(obj, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

}
