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

package one.bbn.hadder.commands.general;

import one.bbn.hadder.commands.Command;
import one.bbn.hadder.commands.CommandEvent;
import one.bbn.hadder.utils.MessageEditor;

public class AboutCommand implements Command {

    @Override
    public void executed(String[] args, CommandEvent e) {
        e.getTextChannel().sendMessage(
                e.getMessageEditor().getMessage(
                        MessageEditor.MessageType.INFO,
                        "commands.general.about.success.title",
                        "commands.general.about.success.description")
                        .addField(e.getMessageEditor().getTerm("commands.general.about.success.field.one.title"), e.getMessageEditor().getTerm("commands.general.about.success.field.one.description", "(https://donatebot.io/checkout/448554629282922527?buyer=" + e.getAuthor().getId() + "). :smiley:", ""), true)
                        .setThumbnail("https://bbn.one/images/Hadder.png")
                        .build()).queue();
    }

    @Override
    public String[] labels() {
        return new String[]{"about"};
    }

    @Override
    public String description() {
        return "commands.general.about.help.description";
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
