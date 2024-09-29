package me.xtreme727.melody;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Message {

    public static String prefix = "&9Melody | &7";

    public static String fileSaveFailure = prefix + "Issue saving file &b{0}&7.";
    public static String fileSetupFailure = prefix + "Issue creating file &b{0}&7. [&b{1}&7]";
    public static String fileSetupSuccess = prefix + "Successfully created file &b{0}&7.";

    public static Component format(String input, String... args) {
        for (int i = 0; i < args.length; i++) {
            input = input.replaceAll("\\{" + i + "\\}", args[i]);
        }
        return LegacyComponentSerializer.legacyAmpersand().deserialize(input);
    }

}
