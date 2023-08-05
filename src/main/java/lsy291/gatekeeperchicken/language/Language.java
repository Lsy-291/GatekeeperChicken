package lsy291.gatekeeperchicken.language;

import lsy291.gatekeeperchicken.config.ConfigManager;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Language extends ConfigManager {

    private String langName;
    private static final List<Language> languages = new ArrayList<>();

    public Language(Plugin plugin, String lang) {
        super(plugin, lang, plugin.getDataFolder() + "/languages");
        this.langName = lang;
        languages.add(this);
    }

    /**
     * Get language with given info.
     *
     * @return null if could not find.
     */
    public static Language getLang(String langName) {
        for (Language language : languages) {
            if (language.langName.equalsIgnoreCase(langName)) {
                return language;
            }
        }
        return new SimplifiedChinese();
    }

}
