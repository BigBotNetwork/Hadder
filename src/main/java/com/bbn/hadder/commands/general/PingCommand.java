package com.bbn.hadder.commands.general;

/*
 * @author Skidder / GregTCLTK
 */

import com.bbn.hadder.commands.Command;
import com.bbn.hadder.commands.CommandEvent;
import com.bbn.hadder.utils.MessageEditor;
import net.dv8tion.jda.api.EmbedBuilder;

public class PingCommand implements Command {

    @Override
    public void executed(String[] args, CommandEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        event.getJDA().getRestPing().queue(ping -> event.getTextChannel().sendMessage(new MessageEditor().setDefaultSettings(MessageEditor.MessageType.INFO, builder).setTitle("Ping").setDescription(String.valueOf(ping)).build()).queue());
    }

    @Override
    public String[] labels() {
        return new String[]{"ping"};
    }

    @Override
    public String description() {
        return "Shows the ping to the Discord API";
    }

    @Override
    public String usage() {
        return "";
    }
}
