/*
 * Copyright 2019-2021 GregTCLTK and Schlauer-Hax
 *
 * Licensed under the GNU Affero General Public License, Version 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.gnu.org/licenses/agpl-3.0.en.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package one.bbn.hadder.commands;

import one.bbn.hadder.audio.AudioManager;
import one.bbn.hadder.commands.general.HelpCommand;
import one.bbn.hadder.core.CommandHandler;
import one.bbn.hadder.core.Config;
import one.bbn.hadder.db.Mongo;
import one.bbn.hadder.db.MongoServer;
import one.bbn.hadder.db.MongoUser;
import one.bbn.hadder.utils.EventWaiter;
import one.bbn.hadder.utils.MessageEditor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nonnull;

public class CommandEvent extends MessageReceivedEvent {

    private Mongo mongo;
    private Config config;
    private CommandHandler commandHandler;
    private HelpCommand helpCommand;
    private MessageEditor messageEditor;
    private EventWaiter eventWaiter;
    private AudioManager audioManager;
    private MongoUser mongoUser;
    private MongoServer mongoServer;

    public CommandEvent(@Nonnull JDA api, long responseNumber, @Nonnull Message message, Mongo mongo, Config config,
                        CommandHandler commandHandler, HelpCommand helpCommand, MessageEditor messageEditor,
                        EventWaiter eventWaiter, AudioManager audioManager, MongoUser mongoUser,
                        MongoServer mongoServer) {
        super(api, responseNumber, message);
        this.mongo = mongo;
        this.config = config;
        this.commandHandler = commandHandler;
        this.helpCommand = helpCommand;
        this.messageEditor = messageEditor;
        this.eventWaiter = eventWaiter;
        this.audioManager = audioManager;
        this.mongoUser = mongoUser;
        this.mongoServer = mongoServer;
    }

    public Mongo getMongo() {
        return mongo;
    }

    public Config getConfig() {
        return config;
    }

    public HelpCommand getHelpCommand() {
        return helpCommand;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public MessageEditor getMessageEditor() {
        return messageEditor;
    }

    public EventWaiter getEventWaiter() {
        return eventWaiter;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public MongoServer getMongoServer() {
        return mongoServer;
    }

    public MongoUser getMongoUser() {
        return mongoUser;
    }
}
