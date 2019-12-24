package com.bbn.hadder.commands.music;

import com.bbn.hadder.audio.AudioInfo;
import com.bbn.hadder.audio.AudioManager;
import com.bbn.hadder.commands.Command;
import com.bbn.hadder.commands.CommandEvent;

/**
 * @author Skidder / GregTCLTK
 */

public class SkipCommand implements Command {

    @Override
    public void executed(String[] args, CommandEvent event) {

    }

    @Override
    public String[] labels() {
        return new String[]{"skip"};
    }

    @Override
    public String description() {
        return "Skips the song";
    }

    @Override
    public String usage() {
        return "";
    }
}