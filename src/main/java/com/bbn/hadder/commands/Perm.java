/*
 * @author Hax / Hax6775 / Schlauer_Hax
 */

package com.bbn.hadder.commands;

import net.dv8tion.jda.api.Permission;

public enum Perm {

    BOT_OWNER() {
        @Override
        public boolean check(CommandEvent event) {
            return event.getConfig().getOwners().contains(event.getAuthor().getIdLong());
        }
    },
    MANAGE_MESSAGES {
        @Override
        public boolean check(CommandEvent event) {
            return event.getMember().hasPermission(Permission.MESSAGE_MANAGE);
        }
    },
    EMBED_MESSAGES {
        @Override
        public boolean check(CommandEvent event) {
            return event.getMember().hasPermission(Permission.MESSAGE_EMBED_LINKS);
        }
    },
    BAN_MEMBERS {
        @Override
        public boolean check(CommandEvent event) {
            return event.getMember().hasPermission(Permission.BAN_MEMBERS);
        }
    },
    KICK_MEMBERS {
        @Override
        public boolean check(CommandEvent event) {
            return event.getMember().hasPermission(Permission.KICK_MEMBERS);
        }
    },
    MANAGE_SERVER {
        @Override
        public boolean check(CommandEvent event) {
            return event.getMember().hasPermission(Permission.MANAGE_SERVER);
        }
    },
    MANAGE_ROLES {
        @Override
        public boolean check(CommandEvent event) {
            return event.getMember().hasPermission(Permission.MANAGE_ROLES);
        }
    },
    CHANGE_NICKNAME {
        @Override
        public boolean check(CommandEvent event) {
            return event.getMember().hasPermission(Permission.NICKNAME_CHANGE);
        }
    },
    ADMIN_PERMISSIONS {
        @Override
        public boolean check(CommandEvent event) {
            return event.getMember().hasPermission(Permission.ADMINISTRATOR);
        }
    };

    public abstract boolean check(CommandEvent event);
}
