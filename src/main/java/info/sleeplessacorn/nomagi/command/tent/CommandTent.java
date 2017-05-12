package info.sleeplessacorn.nomagi.command.tent;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import info.sleeplessacorn.nomagi.command.tent.privacy.CommandPrivacy;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

import java.util.List;

public class CommandTent extends CommandTreeBase {

    public CommandTent() {
        addSubcommand(new CommandReset());
        addSubcommand(new CommandRemove());
        addSubcommand(new CommandPrivacy());
    }

    @Override
    public String getName() {
        return "tent";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        List<String> subCommands = Lists.newArrayList();
        for (ICommand subCommand : getSubCommands())
            subCommands.add(subCommand.getName());
        return "tent [" + Joiner.on("|").join(subCommands) + "]";
    }
}