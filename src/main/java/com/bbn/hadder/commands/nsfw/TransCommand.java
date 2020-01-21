package com.bbn.hadder.commands.nsfw;

/*
 * @author Skidder / GregTCLTK
 */

import com.bbn.hadder.commands.Command;
import com.bbn.hadder.commands.CommandEvent;
import com.bbn.hadder.utils.MessageEditor;
import com.bbn.hadder.utils.Request;

public class TransCommand implements Command {

    @Override
    public void executed(String[] args, CommandEvent e) {
        if (e.getTextChannel().isNSFW()) {

            String url = Request.get("https://nekos.life/api/v2/img/trap/");

            e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.INFO)
                    .setAuthor(e.getMessageEditor().getTerm("commands.nsfw.gif.error.title"), url)
                    .setImage(url)
                    .setFooter("Trans")
                    .build()).queue();

        } else {
            e.getTextChannel()
                    .sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.NO_NSFW).build())
                    .queue();
        }
    }

    @Override
    public String[] labels() {
        return new String[] { "trans" };
    }

    @Override
    public String description() {
        return "commands.nsfw.trans.help.description";
    }

    @Override
    public String usage() {
        return null;
    }

    @Override
    public String example() {
        return null;
    }
}
