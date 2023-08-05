package lsy291.gatekeeperchicken.database;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.UUID;

import static lsy291.gatekeeperchicken.GatekeeperChicken.plugin;

public class sqlite {
    private String url;

    private Connection connection;

    public sqlite() {
        if (!plugin.getDataFolder().exists()) {
            if (!plugin.getDataFolder().mkdir()) {
                plugin.getLogger().severe("Could not create data folder.");
            }
        }

        File dataFile = new File(plugin.getDataFolder().getPath(), "data.db");
        if (!dataFile.exists()) {
            try {
                if (!dataFile.createNewFile()) {
                    plugin.getLogger().severe("Could not create data.db file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        this.url = "jdbc:sqlite:" + dataFile;
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            if (e instanceof ClassNotFoundException) {
                plugin.getLogger().severe("Could Not Found SQLite Driver.");
            }
            e.printStackTrace();
        }
    }

    public void init() {
        String sql;

        try {
            checkConnection();

            sql = "CREATE TABLE IF NOT EXISTS player_data (uuid VARCHAR PRIMARY KEY NOT NULL, pwd VARCHAR NOT NULL);";

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) this.connection = DriverManager.getConnection(url);
    }

    public boolean hasData(UUID uuid) {
        String sql = "SELECT uuid FROM player_data WHERE uuid = ?;";
        try {
            checkConnection();

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, uuid.toString());
                try (ResultSet result = statement.executeQuery()) {
                    return result.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void setPlayerData(UUID player, String pwd) {
        try {
            checkConnection();

            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT pwd FROM player_data WHERE uuid = '" + player.toString() + "';")) {
                    if (rs.next()) {
                        try (PreparedStatement st = connection.prepareStatement("UPDATE player_data SET pwd=? WHERE uuid=?")) {
                            st.setString(1, pwd);
                            st.setString(2, player.toString());
                            st.executeUpdate();
                        }
                    } else {
                        try (PreparedStatement st = connection.prepareStatement("INSERT INTO player_data (uuid, pwd) VALUES (?, ?);")) {
                            st.setString(1, player.toString());
                            st.setString(2, pwd);
                            st.execute();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getPlayerData(UUID player) {
        HashMap<String, String> data = new HashMap<>();

        try {
            checkConnection();

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM player_data WHERE uuid = ?;")) {
                ps.setString(1, player.toString());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        data.put("uuid", rs.getString("uuid"));
                        data.put("pwd", rs.getString("pwd"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

}
