package com.bbn.hadder.commands.misc;

/*
 * @author Skidder / GregTCLTK
 */

import com.bbn.hadder.commands.Command;
import com.bbn.hadder.utils.MessageEditor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GitHubCommand implements Command {
    @Override
    public void executed(String[] args, MessageReceivedEvent event) {
        if (args.length > 0) {
            Request request = new Request.Builder().url("https://api.github.com/users/" + args[0]).build();
            try {

                Response response = new OkHttpClient().newCall(request).execute();
                JSONObject json = new JSONObject(response.body().string());

                String nickname = json.getString("name");
                String bio = "None";
                String location = "Unknown";
                String website = "None";
                try {
                    bio = json.getString("bio");
                } catch (JSONException e) {

                }
                try {
                    location = json.getString("location");
                } catch (JSONException e) {

                }

                if(!json.getString("blog").equals("")) website = json.getString("blog");

                EmbedBuilder builder = new EmbedBuilder();
                event.getTextChannel().sendMessage(new MessageEditor().setDefaultSettings(MessageEditor.Messagetype.INFO, builder)
                .setAuthor("Information about " + nickname + " (" + args[0] + ")", "https://github.com/" + args[0] + "", "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png")
                .setThumbnail(json.getString("avatar_url"))
                .addField("User bio", bio, false)
                .addField("Location", location, true)
                .addField("Website", website, true)
                .addField("Public repositories", String.valueOf(json.getInt("public_repos")), true)
                .addField("Public gists", String.valueOf(json.getInt("public_gists")), true)
                .addField("Followers", String.valueOf(json.getInt("followers")), true)
                .addField("Following", String.valueOf(json.getInt("following")), true)
                        .build()).queue();

            } catch (IOException | NullPointerException e) {
                EmbedBuilder builder = new EmbedBuilder();
                event.getTextChannel().sendMessage(new MessageEditor().setDefaultSettings(MessageEditor.Messagetype.ERROR, builder).setDescription("The GitHub API might be down at the moment!").build()).queue();
            } catch (JSONException e) {
                EmbedBuilder builder = new EmbedBuilder();
                event.getTextChannel().sendMessage(new MessageEditor().setDefaultSettings(MessageEditor.Messagetype.WARNING, builder).setDescription("This user does not exist!").build()).queue();
            }
        } else {
            EmbedBuilder builder = new EmbedBuilder();
            event.getTextChannel().sendMessage(new MessageEditor().setDefaultSettings(MessageEditor.Messagetype.WARNING, builder).setDescription("You have to specify a user!").build()).queue();
        }
    }

    @Override
    public String[] labels() {
        return new String[]{"GitHub"};
    }

    @Override
    public String description() {
        return "Shows info of an user";
    }

    @Override
    public String usage() {
        return labels()[0]+" <Username>";
    }
}