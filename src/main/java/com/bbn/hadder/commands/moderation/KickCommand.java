package com.bbn.hadder.commands.moderation;

/*
 * @author Skidder / GregTCLTK
 */

import com.bbn.hadder.commands.Command;
import com.bbn.hadder.commands.CommandEvent;
import com.bbn.hadder.core.Perm;
import com.bbn.hadder.core.Perms;
import com.bbn.hadder.utils.MessageEditor;
import net.dv8tion.jda.api.entities.Member;

@Perms(Perm.KICK_MEMBERS)
public class KickCommand implements Command {

    @Override
    public void executed(String[] args, CommandEvent e) {
        if (e.getMessage().getMentionedMembers().size() == 1) {
            Member victim = e.getMessage().getMentionedMembers().get(0);
            if (!e.getAuthor().getId().equals(victim.getId())) {
                if (!e.getJDA().getSelfUser().getId().equals(victim.getId())) {
                    if (e.getGuild().getSelfMember().canInteract(victim)) {
                        e.getGuild().kick(victim, "Kicked by " + e.getAuthor().getAsTag()).queue();
                        e.getTextChannel().sendMessage(
                                e.getMessageEditor().getMessage(
                                        MessageEditor.MessageType.INFO,
                                        "commands.moderation.kick.success.title",
                                        "✅",
                                        "commands.moderation.kick.success.description",
                                        victim.getUser().getName()).build()).queue();
                    } else {
                        e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.NO_SELF_PERMISSION).build()).queue();
                    }
                } else {
                    e.getTextChannel().sendMessage(
                            e.getMessageEditor().getMessage(
                                    MessageEditor.MessageType.ERROR,
                                    "commands.moderation.kick.error.title",
                                    "commands.moderation.kick.myself.error.description").build()).queue();
                }
            } else {
                e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.ERROR, "commands.moderation.kick.error.title", "commands.moderation.kick.yourself.error.description").build()).queue();
            }
        } else if (e.getMessage().getMentionedMembers().size() == 0) {
            e.getHelpCommand().sendHelp(this, e);
        } else if (e.getMessage().getMentionedMembers().size() > 1) {
            for (int i = 0; i < e.getMessage().getMentionedMembers().size(); i++) {
                Member member = e.getMessage().getMentionedMembers().get(i);
                if (!e.getAuthor().getId().equals(member.getId())) {
                    if (!e.getJDA().getSelfUser().getId().equals(member.getId())) {
                        if (e.getGuild().getSelfMember().canInteract(member)) {
                            e.getGuild().kick(member).reason("Mass Kicked by " + e.getAuthor().getAsTag()).queue();
                        } else {
                            e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.NO_SELF_PERMISSION).build()).queue();
                        }
                    } else {
                        e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.ERROR, "commands.moderation.kick.error.title", "commands.moderation.kick.myself.error.description").build()).queue();
                    }
                } else {
                    e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.ERROR, "commands.moderation.kick.error.title", "commands.moderation.kick.yourself.error.description").build()).queue();
                }
            }
            e.getTextChannel().sendMessage(e.getMessageEditor().getMessage(MessageEditor.MessageType.INFO, "commands.moderation.kick.success.title", "✅", "commands.moderation.kick.masskick.success.description", String.valueOf(e.getMessage().getMentionedMembers().size())).build()).queue();
        }
    }

    @Override
    public String[] labels() {
        return new String[]{"kick"};
    }

    @Override
    public String description() {
        return "commands.moderation.kick.help.description";
    }

    @Override
    public String usage() {
        return "[User(s)]";
    }

    @Override
    public String example() {
        return "@Skidder";
    }
}
