package com.ddkirill.ratesbot.telegrambot.keyboard;

import com.ddkirill.ratesbot.enums.HoursEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class HoursKeyboard {

    public ReplyKeyboardMarkup getKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();
        KeyboardRow row5 = new KeyboardRow();
        List<KeyboardRow> keyboard = new ArrayList<>();
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        row1.add(new KeyboardButton(HoursEnum.H00.getHours()));
        row1.add(new KeyboardButton(HoursEnum.H01.getHours()));
        row1.add(new KeyboardButton(HoursEnum.H02.getHours()));
        row1.add(new KeyboardButton(HoursEnum.H03.getHours()));
        row1.add(new KeyboardButton(HoursEnum.H04.getHours()));
        row2.add(new KeyboardButton(HoursEnum.H05.getHours()));
        row2.add(new KeyboardButton(HoursEnum.H06.getHours()));
        row2.add(new KeyboardButton(HoursEnum.H07.getHours()));
        row2.add(new KeyboardButton(HoursEnum.H08.getHours()));
        row2.add(new KeyboardButton(HoursEnum.H09.getHours()));
        row3.add(new KeyboardButton(HoursEnum.H10.getHours()));
        row3.add(new KeyboardButton(HoursEnum.H11.getHours()));
        row3.add(new KeyboardButton(HoursEnum.H12.getHours()));
        row3.add(new KeyboardButton(HoursEnum.H13.getHours()));
        row3.add(new KeyboardButton(HoursEnum.H14.getHours()));
        row4.add(new KeyboardButton(HoursEnum.H15.getHours()));
        row4.add(new KeyboardButton(HoursEnum.H16.getHours()));
        row4.add(new KeyboardButton(HoursEnum.H17.getHours()));
        row4.add(new KeyboardButton(HoursEnum.H18.getHours()));
        row4.add(new KeyboardButton(HoursEnum.H19.getHours()));
        row5.add(new KeyboardButton(HoursEnum.H20.getHours()));
        row5.add(new KeyboardButton(HoursEnum.H21.getHours()));
        row5.add(new KeyboardButton(HoursEnum.H22.getHours()));
        row5.add(new KeyboardButton(HoursEnum.H23.getHours()));
        row5.add(new KeyboardButton(HoursEnum.H24.getHours()));


        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);

        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }
}
