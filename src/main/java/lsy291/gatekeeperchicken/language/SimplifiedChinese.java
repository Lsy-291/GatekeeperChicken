package lsy291.gatekeeperchicken.language;

import org.bukkit.configuration.file.YamlConfiguration;

import static lsy291.gatekeeperchicken.GatekeeperChicken.plugin;

public class SimplifiedChinese extends Language {
    public SimplifiedChinese() {
        super(plugin, "zh_cn");

        YamlConfiguration yml = getYml();
        yml.addDefault(Message.REGISTER_REQUIRED, "§c请注册: register <密码> <确认密码> (.或/均可)");
        yml.addDefault(Message.LOGIN_REQUIRED, "§a请登录: login <密码> (.或/均可)");
        yml.addDefault(Message.LOGIN_SUCESS, "§f玩家§a§l%player%§f登录啦 欢迎~");
        yml.addDefault(Message.LOGIN_SUCESS_AUTO_LOGIN, "§a已为你自动登录");
        yml.addDefault(Message.LOGIN_FAILED, "§c密码错误,请重新输入");
        yml.addDefault(Message.LOGIN_FAILED_USAGE_ERROR, "§c用法有误,请使用 login <密码> (.或/均可)");
        yml.addDefault(Message.LOGIN_FAILED_UNREGISTERED, "§c无法登录,你尚未进行注册");
        yml.addDefault(Message.LOGIN_FAILED_TIME_OUT, "§c登录超时");
        yml.addDefault(Message.REGISTER_SUCESS, "§a注册成功,欢迎游玩本服务器");
        yml.addDefault(Message.REGISTER_FAILED_ALREADY_REGISTERED, "§c该账号已注册,请使用login指令登录");
        yml.addDefault(Message.REGISTER_FAILED_USAGE_ERROR, "§c注册失败,格式错误,请使用 reister <密码> <确认密码> (.或/均可)");
        yml.addDefault(Message.REGISTER_FAILED_PASSWORD_NOT_EQUAL, "§c注册失败,两次密码不相等");
        yml.addDefault(Message.REGISTER_FAILED_LENGTH_INVAILD, "§c注册失败,密码长度不合法,有效值为%passwordMinLength% - %passwordMaxLength%");
        yml.addDefault(Message.LOGIN_TIMEOUT_TIMER, "§f登录超时: §c%timeout%§f秒");

        yml.options().copyDefaults(true);
        save();
    }
}
