package site.liangbai.localelib;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import site.liangbai.localelib.locale.Locale;
import site.liangbai.localelib.reflect.ReflectSecurityManager;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * You can use {@link LocaleLib#loadLocale(Plugin, String)} to load your plugin locale.
 * How to use it? {@link Locale#asString(String, Object...)}
 * @author Liangbai
 */
public final class LocaleLib {
    private static final ReflectSecurityManager reflectSecurityManager = new ReflectSecurityManager();

    public static Locale loadLocale(String path) {
       File file = new File(path);

       if (file.exists()) return new Locale(YamlConfiguration.loadConfiguration(file));

       Class<?> cls = reflectSecurityManager.getClassContext()[1];

       return loadJarLocale(cls.getClassLoader(), path);
    }

    public static boolean hasLocale(Plugin plugin, String path) {
        File file = new File(plugin.getDataFolder(), path);

        if (!file.exists()) return hasJarLocale(plugin, path);

        return true;
    }

    public static boolean hasJarLocale(Plugin plugin, String path) {
        InputStream is = plugin.getClass().getClassLoader().getResourceAsStream(path);

        if (is == null) return false;

        return true;
    }

    public static Locale loadLocale(Plugin plugin, String path) {
        File file = new File(plugin.getDataFolder(), path);

        if (!file.exists()) return loadJarLocale(plugin, path);

        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        return new Locale(yaml);
    }

    public static Locale loadJarLocale(Plugin plugin, String path) {
        InputStream is = plugin.getClass().getClassLoader().getResourceAsStream(path);

        if (is == null) throw new IllegalStateException("can not pass locale: " + path + " by: " + plugin.getName());

        FileConfiguration yaml = YamlConfiguration.loadConfiguration(new InputStreamReader(is));

        return new Locale(yaml);
    }

    private static Locale loadJarLocale(ClassLoader classLoader, String path) {
        InputStream is = classLoader.getResourceAsStream(path);

        if (is == null) throw new IllegalStateException("can not pass locale: " + path + " by: " + classLoader.toString());

        FileConfiguration yaml = YamlConfiguration.loadConfiguration(new InputStreamReader(is));

        return new Locale(yaml);
    }
}
