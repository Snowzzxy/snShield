package me.snow.shield.api;

import me.snow.shield.services.SQLite;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShieldAPI extends SQLite {

    public static void setupFactions() {

        try {
            PreparedStatement stm = SQLite.getConnection().prepareStatement("SELECT * FROM shield");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String tag = rs.getString("tag");
                ShieldAPI.setEscudo(tag, 0);
            }

            Bukkit.getConsoleSender().sendMessage("§b[snShield] Todas as facções foram carregadas.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§b[snShield] §cErro ao carregar as facções.");
        }
    }

    public static void deleteFac(String tag) {

        PreparedStatement stm = null;

        try {
            stm = getConnection().prepareStatement("DELETE FROM `shield` WHERE `tag` = ?");
            stm.setString(1, tag);
            stm.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public static Double getPoints(String tag) {

        PreparedStatement stm = null;

        try {
            stm = getConnection().prepareStatement("SELECT * FROM `shield` WHERE `tag` = ?");
            stm.setString(1, tag);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                return rs.getDouble("pontos");
            }
            return 0.0;
        } catch (SQLException e) {
            return 0.0;
        }
    }

    public static Integer getNivel(String tag) {

        PreparedStatement stm = null;

        try {
            stm = getConnection().prepareStatement("SELECT * FROM `shield` WHERE `tag` = ?");
            stm.setString(1, tag);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                return rs.getInt("nivel");
            }
            return 0;
        } catch (SQLException e) {
            return 0;
        }
    }

    public static Boolean getEscudo(String tag) {

        PreparedStatement stm = null;

        try {
            stm = getConnection().prepareStatement("SELECT * FROM `shield` WHERE `tag` = ?");
            stm.setString(1, tag);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                return rs.getBoolean("escudoAtivado");
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void setNivel(String tag, int quantia) {


        PreparedStatement stm = null;

        try {
            stm = getConnection().prepareStatement("UPDATE `shield` SET `nivel` = ? WHERE `tag` = ?");
            stm.setInt(1, quantia);
            stm.setString(2,tag);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void setEscudo(String tag, int quantia) {


        PreparedStatement stm = null;

        try {
            stm = getConnection().prepareStatement("UPDATE `shield` SET `escudoAtivado` = ? WHERE `tag` = ?");
            stm.setInt(1, quantia);
            stm.setString(2,tag);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addPontos(String tag, double amount) {


        PreparedStatement stm = null;

        double pontosTotal = getPoints(tag) + amount;

        try {
            stm = getConnection().prepareStatement("UPDATE `shield` SET pontos= ? WHERE `tag` = ?");
            stm.setDouble(1, pontosTotal);
            stm.setString(2,tag);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void removePontos(String tag, double amount) {


        PreparedStatement stm = null;

        double pontosTotal = getPoints(tag) - amount;

        try {
            stm = getConnection().prepareStatement("UPDATE `shield` SET pontos= ? WHERE `tag` = ?");
            stm.setDouble(1, pontosTotal);
            stm.setString(2,tag);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
