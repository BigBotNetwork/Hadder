package com.bbn.hadder.commands.music;

import com.bbn.hadder.commands.Command;
import com.bbn.hadder.commands.CommandEvent;
import com.bbn.hadder.utils.MessageEditor;

/**
 * @author Skidder / GregTCLTK
 */

public class VolumeCommand implements Command {

    @Override
    public void executed(String[] args, CommandEvent event) {
        if (args.length > 0) {
            if (event.getAudioManager().hasPlayer(event.getGuild()) && event.getAudioManager().getPlayer(event.getGuild()).getPlayingTrack() != null) {
                try {
                    int volume = Integer.parseInt(args[0]);
                    if (volume < 200 && volume > 0 || event.getConfig().getOwners().contains(event.getAuthor().getId())) {
                        event.getAudioManager().getPlayer(event.getGuild()).setVolume(volume);
                        event.getTextChannel().sendMessage(event.getMessageEditor().getMessage(MessageEditor.MessageType.INFO,
                                "commands.music.volume.success.title", "",
                                "commands.music.volume.success.description", String.valueOf(volume)).build()).queue();
                    } else {
                        event.getTextChannel().sendMessage(event.getMessageEditor().getMessage(MessageEditor.MessageType.ERROR,
                                "commands.music.volume.error.int.title",
                                "commands.music.volume.error.int.description").build()).queue();
                    }
                } catch (NumberFormatException e) {
                    event.getHelpCommand().sendHelp(this, event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                event.getTextChannel().sendMessage(event.getMessageEditor().getMessage(MessageEditor.MessageType.ERROR,
                        "commands.music.info.error.title",
                        "commands.music.info.error.description").build()).queue();
            }
        } else event.getHelpCommand().sendHelp(this, event);
    }

    @Override
    public String[] labels() {
        return new String[]{"volume"};
    }

    @Override
    public String description() {
        return "Change the volume of the music.";
    }

    @Override
    public String usage() {
        return "[New volume]";
    }

    @Override
    public String example() {
        return "100";
    }
}