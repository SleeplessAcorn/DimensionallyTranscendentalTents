package info.sleeplessacorn.nomagi;

import info.sleeplessacorn.nomagi.command.CommandNomagi;
import info.sleeplessacorn.nomagi.core.ModObjects;
import info.sleeplessacorn.nomagi.network.*;
import info.sleeplessacorn.nomagi.proxy.ProxyCommon;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Nomagi.ID, name = Nomagi.NAME, version = Nomagi.VERSION)
public class Nomagi {

    public static final String ID = "nomagi";
    public static final String NAME = "Nomagi";
    public static final String VERSION = "@VERSION@";
    public static final Logger LOGGER = LogManager.getLogger(Nomagi.NAME);
    public static final SimpleNetworkWrapper NETWORK = new SimpleNetworkWrapper(Nomagi.ID);
    public static final CreativeTabs CTAB = new CreativeTabs(Nomagi.ID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModObjects.DOOR);
        }
    };

    @Mod.Instance(Nomagi.ID)
    public static Nomagi INSTANCE;
    @SidedProxy(clientSide = "info.sleeplessacorn.nomagi.proxy.ProxyClient")
    public static ProxyCommon PROXY;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        PROXY.preInit(event);
        NETWORK.registerMessage(MessageCreateRoom.Handler.class, MessageCreateRoom.class, 0, Side.SERVER);
        NETWORK.registerMessage(MessageOpenCreateRoomGui.Handler.class, MessageOpenCreateRoomGui.class, 1, Side.CLIENT);
        NETWORK.registerMessage(MessageOpenPrivacyGui.Handler.class, MessageOpenPrivacyGui.class, 2, Side.CLIENT);
        NETWORK.registerMessage(MessageUpdateUsernames.Handler.class, MessageUpdateUsernames.class, 3, Side.CLIENT);
        NETWORK.registerMessage(MessageOpenBarrelGui.Handler.class, MessageOpenBarrelGui.class, 4, Side.CLIENT);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        PROXY.postInit(event);
    }

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandNomagi());
    }

    public static class ServerProxy extends ProxyCommon {
        // dummy class
    }

}
