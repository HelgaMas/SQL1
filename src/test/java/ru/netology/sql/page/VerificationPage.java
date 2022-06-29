package ru.netology.sql.page;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;
import ru.netology.sql.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement pageOfCode = $(withText("Необходимо подтверждение"));
    private SelenideElement codeInput = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement notification = $("[data-test-id=error-notification]");


    public DashboardPage getAnyCode(DataHelper.VerificationCode code) {
        pageOfCode.shouldBe(Condition.visible);
        codeInput.setValue(String.valueOf(code));
        verifyButton.click();
        return new DashboardPage();
    }

    public void invalidCode(DataHelper.VerificationCode wrongCode) {
        getAnyCode(wrongCode);
        notification.shouldHave(Condition.text("Неверно указан код"));
        notification.shouldBe(Condition.visible);
    }
}