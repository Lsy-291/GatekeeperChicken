package lsy291.gatekeeperchicken.config;

import static lsy291.gatekeeperchicken.GatekeeperChicken.mainConfig;

public class ConfigItems {
    public static String languageName = mainConfig.getString(MainConfigPath.LANGUAGE);
    public static boolean enablePluginPrefix = mainConfig.getBoolean(MainConfigPath.PLUGIN_PREFIX_ENABLE);
    public static String loginMethod = mainConfig.getString(MainConfigPath.LOGIN_METHOD);
    public static boolean enablePassworldEncryption = mainConfig.getBoolean(MainConfigPath.PASSWORD_ENCRYPTION);
    public static String loginSucessSound = mainConfig.getString(MainConfigPath.LOGIN_SUCESS_SOUND);
    public static String loginFailedSound = mainConfig.getString(MainConfigPath.LOGIN_FAILED_SOUND);
    public static float loginSoundVolume = (float) mainConfig.getYml().getDouble(MainConfigPath.LOGIN_SOUND_VOLUME);
    public static int loginTimeout = mainConfig.getInt(MainConfigPath.LOGIN_TIMEOUT);
    public static int autoLoginTime = mainConfig.getInt(MainConfigPath.AUTO_LOGIN_TIME);
    public static int passwordMinLength = mainConfig.getInt(MainConfigPath.PASSWORD_MIN_LENGTH);
    public static int passwordMaxLength = mainConfig.getInt(MainConfigPath.PASSWORD_MAX_LENGTH);
    public static boolean displayLoginTimeoutTimer = mainConfig.getBoolean(MainConfigPath.DISPLAY_LOGIN_TIMEOUT_TIMER);
}
