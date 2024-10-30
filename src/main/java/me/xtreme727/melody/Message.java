package me.xtreme727.melody;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class Message {

    public static String prefix = "&9Melody | &7";

    public static String commandCreate = prefix + "Created song &b{0} &7- [{1} BPM]";
    public static String commandCreateBlocktype = prefix + "You must be looking at your starting block, a redstone lamp.";
    public static String commandEditor = prefix + "Toggling editor mode.";
    public static String commandEditorAddOn = " Run &b/melody editor &7again to disable editor mode.";
    public static String commandHelpHeader = prefix + "v{0} Commands:";
    public static String commandHelpList = "  &7> /melody &b{0}";
    public static String commandInvalidFormat = prefix + "Invalid command format! &b/melody {0}";
    public static String commandPlay = prefix + "Now playing &b{0} &7- [{1} BPM]";
    public static String commandPlayNull = prefix + "Can't find a song named &b{0}&7!";

    public static String consoleCommandSender = prefix + "Must be a player to run commands!";

    public static String fileSaveFailure = prefix + "Issue saving file &b{0}&7. [&b{1}&7]";
    public static String fileSetupFailure = prefix + "Issue creating file &b{0}&7. [&b{1}&7]";
    public static String fileSetupSuccess = prefix + "Successfully created file &b{0}&7.";

    public static Component format(String input, String... args) {
        for (int i = 0; i < args.length; i++) {
            input = input.replaceAll("\\{" + i + "\\}", args[i]);
        }
        return LegacyComponentSerializer.legacyAmpersand().deserialize(input);
    }

    public static String stripColor(Component c) {
        return PlainTextComponentSerializer.plainText().serialize(c);
    }

}
