package ru.netology.sql.page;

import ru.netology.sql.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public static VerificationPage validLogin(DataHelper.User info) {
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }
}