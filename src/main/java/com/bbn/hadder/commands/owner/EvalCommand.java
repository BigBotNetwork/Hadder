package com.bbn.hadder.commands.owner;

/*
 * @author Skidder / GregTCLTK
 */

import com.bbn.hadder.Hadder;
import com.bbn.hadder.audio.AudioManager;
import com.bbn.hadder.commands.Command;
import com.bbn.hadder.commands.CommandEvent;
import com.bbn.hadder.core.Perm;
import com.bbn.hadder.core.Perms;
import com.bbn.hadder.utils.MessageEditor;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Perms(Perm.BOT_OWNER)
public class EvalCommand implements Command {

    @Override
    public void executed(String[] args, CommandEvent event) {
        if (args.length > 0) {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

            try {
                engine.eval("var imports = new JavaImporter(java.io, java.lang, java.util);");
            } catch (ScriptException ex) {
                ex.printStackTrace();
            }

            engine.put("msg", event.getMessage());
            engine.put("shardmanager", Hadder.shardManager);
            engine.put("rethink", event.getRethink());
            engine.put("event", event);
            engine.put("jda", event.getJDA());
            engine.put("message", event.getMessage());
            engine.put("guild", event.getGuild());
            engine.put("channel", event.getChannel());
            engine.put("author", event.getAuthor());
            engine.put("member", event.getMember());
            engine.put("self", event.getGuild().getSelfMember());
            engine.put("audio", new AudioManager());

            ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

            service.schedule(() -> {

                long startExec = System.currentTimeMillis();
                Object out;

                try {
                    String script = "";
                    for (int i = 0; i < args.length; i++) {
                        args[i] = args[i].replace("```java", "").replace("```", "");
                        script += i == args.length - 1 ? args[i] : args[i] + " ";
                    }
                    out = engine.eval(script);

                    event.getTextChannel().sendMessage(event.getMessageEditor()
                            .getMessage(MessageEditor.MessageType.INFO, "commands.owner.eval.success.title", "")
                            .addField(event.getMessageEditor().getTerm("commands.owner.eval.success.input"),
                                    "```java\n\n" + script + "```", false)
                            .addField(event.getMessageEditor().getTerm("commands.owner.eval.success.output"),
                                    "```java\n\n" + out.toString() + "```", false)
                            .addField(event.getMessageEditor().getTerm("commands.owner.eval.success.timing"),
                                    System.currentTimeMillis() - startExec + " milliseconds", false)
                            .build()).queue();
                } catch (Exception ex) {
                    event.getTextChannel().sendMessage(event.getMessageEditor()
                            .getMessage(MessageEditor.MessageType.INFO, "commands.owner.eval.success.title", "")
                            .addField(event.getMessageEditor().getTerm("error"),
                                    "```java\n\n" + ex.getMessage() + "```", false)
                            .addField(event.getMessageEditor().getTerm("commands.owner.eval.success.timing"),
                                    System.currentTimeMillis() - startExec + " milliseconds", false)
                            .build()).queue();

                }

                service.shutdownNow();

            }, 0, TimeUnit.MILLISECONDS);

        } else {
            event.getHelpCommand().sendHelp(this, event);
        }
    }

    @Override
    public String[] labels() {
        return new String[]{"eval"};
    }

    @Override
    public String description() {
        return "commands.owner.eval.help.description";
    }

    @Override
    public String usage() {
        return "commands.owner.eval.help.usage";
    }
}
