package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardDeliveryTest {

    public String dateGenerator(int days, String pattern){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldSendFormWithValidData(){

        String date = dateGenerator(3, "dd.MM.yyyy");

        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Махачкала");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(date);
        //$("[data-test-id='date'] input").setValue(dateGenerator(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Махачкалалов Махачкал");
        $("[data-test-id='phone'] input").setValue("+78854261573");
        $("[data-test-id='agreement']").click();
        $$(".button").findBy(Condition.exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification']").shouldHave(Condition.text(date));
    }





}
