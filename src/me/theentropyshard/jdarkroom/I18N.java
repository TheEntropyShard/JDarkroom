/*      JDarkroom. Reimplementation of Darkroom website.
 *      Copyright (C) 2023 TheEntropyShard
 *
 *      This program is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.theentropyshard.jdarkroom;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum I18N {
    ;

    public static final Locale LOCALE = Locale.getDefault();
    public static final String LANGUAGE = I18N.LOCALE.getLanguage().toLowerCase(Locale.ROOT);

    private static final Map<String, String> TRANSLATION = new HashMap<>();

    static {
        I18N.TRANSLATION.put("en.dropFileHere", "<html>Drop save file here! (slot[0-4].sav)<br>Or click to enter Internet code!</html>");
        I18N.TRANSLATION.put("ru.dropFileHere", "<html>Переместите файл сохранения сюда! (slot[0-4].sav)<br>Или нажмите, чтобы ввести Интернет-код!</html>");

        I18N.TRANSLATION.put("en.enterCodeHere", "Enter Internet code here");
        I18N.TRANSLATION.put("ru.enterCodeHere", "Введите сюда Интернет-код");

        I18N.TRANSLATION.put("en.submit", "Submit");
        I18N.TRANSLATION.put("ru.submit", "Отправить");

        I18N.TRANSLATION.put("en.incorrectCodeLength", "Internet code length must be 16 chars!");
        I18N.TRANSLATION.put("ru.incorrectCodeLength", "Длина Интернет-кода должна быть 16 символов!");

        I18N.TRANSLATION.put("en.badFile", "Bad file given!");
        I18N.TRANSLATION.put("ru.badFile", "Перемещен плохой файл!");

        I18N.TRANSLATION.put("en.resultLabel", "<html>Russian code: %s and English code: %s<br>Total time played: %s</html>");
        I18N.TRANSLATION.put("ru.resultLabel", "<html>Русский код: %s и Английский код: %s<br>Всего времени отыграно: %s</html>");
    }

    public static String getString(String key) {
        return I18N.TRANSLATION.get(I18N.LANGUAGE + "." + key);
    }
}
