package me.xtreme727.melody;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class Message {

    public static String prefix = "&9Melody | &7";

    public static String commandHelpHeader = prefix + "v{0} Commands:";
    public static String commandHelpList = "  &7> /melody &b{0}";
    public static String commandInvalidFormat = prefix + "Invalid command format! &b/melody {0}";
    public static String commandTools = prefix + "You've been given the music writing tools.";
    public static String commandToolsWarn = prefix + "&c&lWARNING!&7 This command will clear and replace your inventory with music writing tools. Send &b/melody tools&7 again to confirm.";

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
