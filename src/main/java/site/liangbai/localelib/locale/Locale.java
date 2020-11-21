package site.liangbai.localelib.locale;

import org.apache.commons.lang3.Validate;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public final class Locale {
    private final ConfigurationSection locale;

    public Locale(ConfigurationSection locale) {
        this.locale = locale;
    }

    public String safeAsString(String node, Object... args) {


        String str;

        if (locale.isList(node)) {
            List<String> list = locale.getStringList(node);
            StringBuilder sb = new StringBuilder();

            list.forEach(value -> {
                sb.append(value).append("\n");
            });

            str = sb.toString();
        } else {
            str = locale.getString(node);
        }

        for (int i = 0; i < args.length; i++) {
            str = str.replace("{" + i + "}", args[i].toString()).replace('&', ChatColor.COLOR_CHAR);
        }

        return str;
    }

    public String asString(String node, Object... args) {
        Validate.notNull(node, "node can not be bull");

        if (!locale.contains(node)) throw new IllegalStateException("can not found node: " + node);

        if (!locale.isString(node)) throw new IllegalStateException("node: " + node + " must be string");

        return safeAsString(node, args);
    }

    public boolean hasNode(String node) {
        return locale.contains(node);
    }
}
