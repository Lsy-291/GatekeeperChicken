package lsy291.gatekeeperchicken.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class MainConfig extends ConfigManager {
    public MainConfig(Plugin plugin, String name) {
        super(plugin, name, plugin.getDataFolder().getPath());

        YamlConfiguration yml = getYml();
        yml.addDefault(MainConfigPath.LANGUAGE, "zh_cn");
        yml.addDefault(MainConfigPath.LOGIN_METHOD, "both");
        yml.addDefault(MainConfigPath.PASSWORD_ENCRYPTION, true);
        yml.addDefault(MainConfigPath.LOGIN_SUCESS_SOUND, "ENTITY_EXPERIENCE_ORB_PICKUP");
        yml.addDefault(MainConfigPath.LOGIN_FAILED_SOUND, "ENTITY_VILLAGER_NO");
        yml.addDefault(MainConfigPath.LOGIN_SOUND_VOLUME, 0.4);
        yml.addDefault(MainConfigPath.LOGIN_TIMEOUT, 60);
        yml.addDefault(MainConfigPath.AUTO_LOGIN_TIME, 6000);
        yml.addDefault(MainConfigPath.PASSWORD_MIN_LENGTH, 5);
        yml.addDefault(MainConfigPath.PASSWORD_MAX_LENGTH, 16);
        yml.addDefault(MainConfigPath.DISPLAY_LOGIN_TIMEOUT_TIMER, true);

        yml.options().copyDefaults(true);
        save();
    }
}
