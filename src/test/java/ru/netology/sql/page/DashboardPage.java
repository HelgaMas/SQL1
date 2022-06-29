package ru.netology.sql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private static SelenideElement nextPage = $("[data-test-id=dashboard]");

    public static DashboardPage personalAccount() {
        nextPage.shouldBe(Condition.text(" Личный кабинет"));
        nextPage.shouldBe(visible);
        return null;
    }
}