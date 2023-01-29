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

import javax.swing.*;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Pattern localePattern = Pattern.compile("([a-z]{2})-([A-Z]{2})$");
        if(args.length > 0) {
            Matcher matcher = localePattern.matcher(args[0]);
            if(matcher.matches()) {
                try {
                    String[] langAndCountry = args[0].split("-");
                    Locale.setDefault(new Locale(langAndCountry[0], langAndCountry[1]));
                } catch (Exception se) {
                    se.printStackTrace();
                }
            }
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {

        }

        new JDarkroom();
    }
}
