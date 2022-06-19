package ru.netology.sql.test;

import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.page.LoginPage;
import ru.netology.sql.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class SqlTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    public static void delete() {
        DataHelper.clearTables();
    }

    @Test
    public void shouldCheckValidCode() {
        var user = DataHelper.getUser();
        var verificationPage = LoginPage.validLogin(user);
        var verificationCode = DataHelper.getVerificationCode();
        verificationPage = verificationPage.validCode(verificationCode);
    }

    @Test
    public void shouldCheckInvalidCode() {
        var user = DataHelper.getUser();
        var verificationPage = LoginPage.validLogin(user);
        var verificationCode = DataHelper.wrongCode();
        verificationPage = verificationPage.invalidCode(verificationCode);
    }
}