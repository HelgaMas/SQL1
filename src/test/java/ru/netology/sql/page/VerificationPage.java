package ru.netology.sql.page;

import com.codeborne.selenide.Condition;

import ru.netology.sql.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    public static VerificationPage validCode(DataHelper.VerificationCode code) {
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible);
        $("[data-test-id=code] input").setValue(String.valueOf(code));
        $("[data-test-id=action-verify]").click();
        $(".heading").shouldBe(Condition.text(" Личный кабинет"));
        return new VerificationPage();
    }

    public static VerificationPage invalidCode(DataHelper.VerificationCode wrongCode) {
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible);
        $("[data-test-id=code] input").setValue(String.valueOf(wrongCode));
        $("[data-test-id=action-verify]").click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Неверно указан код"));
        return new VerificationPage();
    }
}