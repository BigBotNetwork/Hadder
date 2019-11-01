package com.bbn.hadder.commands.owner;

/*
 * @author Skidder / GregTCLTK
 */

import com.bbn.hadder.Rethink;
import com.bbn.hadder.commands.Command;
import com.bbn.hadder.utils.MessageEditor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Timer;
import java.util.TimerTask;

import static com.bbn.hadder.Hadder.startBot;

public class RebootCommand implements Command {

    @Override
    public void executed(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId().equals("477141528981012511") || event.getAuthor().getId().equals("261083609148948488")) {
            
        } else {
            EmbedBuilder builder = new EmbedBuilder();
            event.getTextChannel().sendMessage(new MessageEditor().setDefaultSettings(MessageEditor.Messagetype.NO_PERMISSION, builder).build()).queue();
        }
    }

    @Override
    public String[] labels() {
        return new String[]{"restart"};
    }

    @Override
    public String description() {
        return "Restart the bot";
    }

    @Override
    public String usage() {
        return labels()[0];
    }
}