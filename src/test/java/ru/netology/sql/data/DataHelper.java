package ru.netology.sql.data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class User {
        private String login;
        private String password;
    }

    public static User getUser() {
        return new User("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @SneakyThrows
    public static VerificationCode getVerificationCode() {
        var codeSQL = "SELECT code FROM users join auth_codes " +
                "on users.id = auth_codes.user_id " +
                "where login = 'vasya' " +
                "ORDER BY created DESC LIMIT 1;";

        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "olga", "warm");
        ) {
            var code = runner.query(conn, codeSQL, new ScalarHandler<String>());

            return new VerificationCode(code);
        }
    }

    public static VerificationCode wrongCode() {
        return new VerificationCode("22");
    }

    @SneakyThrows
    public static void clearTables() {
        var cardTr = "DELETE FROM card_transactions;";
        var authCodes = "DELETE FROM auth_codes;";
        var cards = "DELETE FROM cards;";
        var users = "DELETE FROM users;";
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "olga", "warm");
        ) {
            runner.update(conn, cardTr);
            runner.update(conn, authCodes);
            runner.update(conn, cards);
            runner.update(conn, users);
        }
    }
}