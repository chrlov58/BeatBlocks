package me.xtreme727.melody.commands;

import me.xtreme727.melody.Message;
import me.xtreme727.melody.users.User;

public class SCEditor implements MSubCommand {

    public boolean onCommand(User u, String[] args) {
        u.toggleEditor();
        u.sendMessage(Message.format(Message.commandEditor + (u.editor ? Message.commandEditorAddOn : "")));
        return true;
    }

    public String getCommandFormat() {
        return "editor";
    }
}
