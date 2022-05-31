package ru.minersdream.stalker.events;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;
import ru.minersdream.stalker.item.StalkerFood;
import ru.minersdream.stalker.main.STALKERMain;
import ru.minersdream.stalker.other.OtherMethods;

public class CommandReloadConfig extends CommandBase {
    private String usage = "stalker_reloadconfig";

    @Override
    public String getName() {
        return "stalker_reloadconfig";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return usage;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        
        STALKERMain.instance.loadConfig();

        sender.sendMessage(new TextComponentTranslation("command.reloadconfig"));
    }
}
