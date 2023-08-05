package lsy291.gatekeeperchicken;

import lsy291.gatekeeperchicken.commands.ChangePasswordCommand;
import lsy291.gatekeeperchicken.commands.LoginCommand;
import lsy291.gatekeeperchicken.commands.RegisterCommand;
import lsy291.gatekeeperchicken.config.MainConfig;
import lsy291.gatekeeperchicken.database.sqlite;
import lsy291.gatekeeperchicken.task.AutoLoginTask;
import lsy291.gatekeeperchicken.task.LoginTimeoutKickTask;
import lsy291.gatekeeperchicken.language.Language;
import lsy291.gatekeeperchicken.language.SimplifiedChinese;
import lsy291.gatekeeperchicken.listeners.*;
import lsy291.gatekeeperchicken.task.LoginTimeoutTimerTask;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;

import static lsy291.gatekeeperchicken.config.ConfigItems.*;

public final class GatekeeperChicken extends JavaPlugin {

    public static GatekeeperChicken plugin;
    public static String pluginPrefix = ChatColor.AQUA + "[GatekeeperChicken] ";
    public static MainConfig mainConfig;
    private static sqlite database;
    public static Language language;
    private BukkitTask autoLoginTask;
    private BukkitTask loginTimeoutKickTask;
    private BukkitTask loginTimeoutTimerTask;

    @Override
    public void onEnable() {
        plugin = this;

        mainConfig = new MainConfig(this, "config");

        database = new sqlite();
        database.init();

        registerEvents(new PlayerJoinListener(), new PlayerQuitListener(), new PlayerLoggedListener(), new PlayerRegisteredListener(),
                new PlayerLoginListener(), new PlayerRegisterListener(), new PlayerActionsListener());

        if (loginMethod.equals("both") || loginMethod.equals("cmd"))
        {
            getCommand("reg").setExecutor(new RegisterCommand());
            getCommand("register").setExecutor(new RegisterCommand());
            getCommand("l").setExecutor(new LoginCommand());
            getCommand("login").setExecutor(new LoginCommand());
            getCommand("changepwd").setExecutor(new ChangePasswordCommand());
        }

        if (autoLoginTime > 0) {
            autoLoginTask = getServer().getScheduler().runTaskTimer(this, new AutoLoginTask(), 0L, 20L);
        }

        if (loginTimeout > 0) {
            loginTimeoutKickTask = getServer().getScheduler().runTaskTimer(this, new LoginTimeoutKickTask(), 0L, 20L);
        }

        if (displayLoginTimeoutTimer) {
            loginTimeoutTimerTask = getServer().getScheduler().runTaskTimer(this, new LoginTimeoutTimerTask(), 0L, 20L);
        }

        new SimplifiedChinese();

        language = Language.getLang(languageName);

        getLogger().info(pluginPrefix + "Loaded.");
    }

    @Override
    public void onDisable() {
        if (autoLoginTask != null) autoLoginTask.cancel();
        if (loginTimeoutKickTask != null) loginTimeoutKickTask.cancel();
        if (loginTimeoutTimerTask != null) loginTimeoutTimerTask.cancel();
        getLogger().info(pluginPrefix + "Successfully closed.");
    }

    public static void registerEvents(Listener... listeners) {
        Arrays.stream(listeners).forEach(l -> plugin.getServer().getPluginManager().registerEvents(l, plugin));
    }

    public static sqlite getDatabase() { return database; }
}
