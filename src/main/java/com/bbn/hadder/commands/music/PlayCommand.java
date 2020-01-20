package com.bbn.hadder.commands.music;

import com.bbn.hadder.commands.Command;
import com.bbn.hadder.commands.CommandEvent;
import com.bbn.hadder.utils.MessageEditor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;

import java.net.URL;

/**
 * @author Skidder / GregTCLTK
 */

public class PlayCommand implements Command {

    @Override
    public void executed(String[] args, CommandEvent e) {
        if (args.length > 0) {
            if (e.getMember().getVoiceState().inVoiceChannel()) {
                String input = e.getMessage().getContentRaw().replaceFirst(e.getRethink().getGuildPrefix(e.getGuild().getId()) + "play ", "").replaceFirst(e.getRethink().getUserPrefix(e.getAuthor().getId()) + "play ", "");
                try {
                    new URL(input).toURI();
                    Message msg = e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.INFO,
                            "commands.music.play.load.title", "⭕",
                            "commands.music.play.load.description", "").build()).complete();
                    e.getAudioManager().loadTrack(input, e, msg);
                } catch (InsufficientPermissionException ex) {
                    e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.ERROR,
                            "commands.music.join.error.permission.title",
                            "commands.music.join.error.permission.description")
                            .build()).queue();
                } catch (Exception ignore) {
                    Message msg = e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.INFO,
                            "commands.music.play.load.title", "⭕",
                            "commands.music.play.load.description", "").build()).complete();
                    e.getAudioManager().loadTrack("ytsearch: " + input, e, msg);
                }
            } else {
                e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(
                        MessageEditor.MessageType.ERROR,
                        "commands.music.join.error.channel.title",
                        "commands.music.join.error.channel.description")
                        .build()).queue();
            }
        } else if (e.getAudioManager().getPlayer(e.getGuild()).isPaused()) {
            if (e.getMember().getVoiceState().inVoiceChannel() && e.getGuild().getSelfMember().getVoiceState().inVoiceChannel() && e.getGuild().getSelfMember().getVoiceState().getChannel().equals(e.getMember().getVoiceState().getChannel())) {
                e.getAudioManager().getPlayer(e.getGuild()).setPaused(false);
                e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.INFO,
                        "commands.music.play.success.unpause.title",
                        "commands.music.play.success.unpause.description")
                        .build()).queue();
            } else {
                e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.ERROR,
                        "commands.music.play.error.connected.title",
                        "commands.music.play.error.connected.description")
                        .build()).queue();
            }
        } else e.getHelpCommand().sendHelp(this, e);
    }

    @Override
    public String[] labels() {
        return new String[]{"play"};
    }

    @Override
    public String description() {
        return "commands.music.play.help.description";
    }

    @Override
    public String usage() {
        return "[Song URL/Name]";
    }

    @Override
    public String example() {
        return "Last Christmas";
    }
}
