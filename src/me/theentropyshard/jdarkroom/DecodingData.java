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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class DecodingData {
    /**
     * Offset, where Akuda locker code located
     */
    public static final long CODE_OFFSET = 0x00002D58L;

    /**
     * Key for decoding Internet Code
     */
    public static final byte[] KEY = {
            0x25, 0x1F, 0x1D, 0x17, 0x13, 0x11, 0x0B, 0x07
    };

    /**
     * Table for decoding bytes from save file, english and russian letters
     * Huge thing...
     */
    public static final Map<Integer, String> SAVE_INDEX_TABLE = Collections.unmodifiableMap(
            new HashMap<Integer, String>() {{
                this.put(1, "A А");
                this.put(2, "B Б");
                this.put(3, "C Ц");
                this.put(4, "D Д");
                this.put(5, "E Е");
                this.put(6, "F Ф");
                this.put(7, "G Г");
                this.put(8, "H Ю");
                this.put(9, "I И");
                this.put(10, "J Й");
                this.put(11, "K К");
                this.put(12, "L Л");
                this.put(13, "M М");
                this.put(14, "N Н");
                this.put(15, "O О");
                this.put(16, "P П");
                this.put(17, "Q Я");
                this.put(18, "R Р");
                this.put(19, "S С");
                this.put(20, "T Т");
                this.put(21, "U У");
                this.put(22, "V В");
                this.put(23, "W Ш");
                this.put(24, "X Х");
                this.put(25, "Y Ч");
                this.put(26, "Z Ж");
                this.put(27, "0 0");
                this.put(28, "1 1");
                this.put(29, "2 2");
                this.put(30, "3 3");
                this.put(31, "4 4");
                this.put(32, "5 5");
                this.put(33, "6 6");
                this.put(34, "7 7");
                this.put(35, "8 8");
                this.put(36, "9 9");
            }}
    );

    /**
     * Table for decoding bytes from decoded Internet Code, english and russian letters
     * Ok, this is bigger
     */
    public static final Map<String, Byte> CODE_INDEX_TABLE = Collections.unmodifiableMap(
            new HashMap<String, Byte>() {{
                // @formatter:off
                this.put("A", (byte) 0); this.put("А", (byte) 0);
                this.put("B", (byte) 1); this.put("Б", (byte) 1);
                this.put("C", (byte) 2); this.put("Ц", (byte) 2);
                this.put("D", (byte) 3); this.put("Д", (byte) 3);
                this.put("E", (byte) 4); this.put("Е", (byte) 4);
                this.put("F", (byte) 5); this.put("Ф", (byte) 5);
                this.put("G", (byte) 6); this.put("Г", (byte) 6);
                this.put("H", (byte) 7); this.put("Ю", (byte) 7);
                this.put("+", (byte) 8); // this.put("+", (byte) 8);
                this.put("J", (byte) 9); this.put("Й", (byte) 9);
                this.put("K", (byte) 10); this.put("К", (byte) 10);
                this.put("L", (byte) 11); this.put("Л", (byte) 10);
                this.put("M", (byte) 12); this.put("М", (byte) 10);
                this.put("N", (byte) 13); this.put("Н", (byte) 10);
                this.put("\\", (byte) 14); // this.put("\\", (byte) 10);
                this.put("P", (byte) 15); this.put("П", (byte) 10);
                this.put("Q", (byte) 16); this.put("Я", (byte) 10);
                this.put("R", (byte) 17); this.put("Р", (byte) 10);
                this.put("S", (byte) 18); this.put("С", (byte) 10);
                this.put("T", (byte) 19); this.put("Т", (byte) 10);
                this.put("U", (byte) 20); this.put("У", (byte) 10);
                this.put("V", (byte) 21); this.put("В", (byte) 10);
                this.put("W", (byte) 22); this.put("Ш", (byte) 10);
                this.put("X", (byte) 23); this.put("Х", (byte) 10);
                this.put("Y", (byte) 24); this.put("Ч", (byte) 10);
                this.put("Z", (byte) 25); this.put("Ж", (byte) 10);
                this.put("a", (byte) 26); this.put("а", (byte) 10);
                this.put("b", (byte) 27); this.put("б", (byte) 10);
                this.put("c", (byte) 28); this.put("ц", (byte) 10);
                this.put("d", (byte) 29); this.put("д", (byte) 10);
                this.put("e", (byte) 30); this.put("е", (byte) 10);
                this.put("f", (byte) 31); this.put("ф", (byte) 10);
                this.put("g", (byte) 32); this.put("г", (byte) 10);
                this.put("h", (byte) 33); this.put("ю", (byte) 10);
                this.put("i", (byte) 34); this.put("и", (byte) 10);
                this.put("j", (byte) 35); this.put("й", (byte) 10);
                this.put("k", (byte) 36); this.put("к", (byte) 10);
                this.put("&", (byte) 37); // this.put("&", (byte) 10);
                this.put("m", (byte) 38); this.put("м", (byte) 10);
                this.put("n", (byte) 39); this.put("н", (byte) 10);
                this.put("o", (byte) 40); this.put("о", (byte) 10);
                this.put("p", (byte) 41); this.put("п", (byte) 10);
                this.put("q", (byte) 42); this.put("я", (byte) 10);
                this.put("r", (byte) 43); this.put("р", (byte) 10);
                this.put("s", (byte) 44); this.put("с", (byte) 10);
                this.put("t", (byte) 45); this.put("т", (byte) 10);
                this.put("u", (byte) 46); this.put("у", (byte) 10);
                this.put("v", (byte) 47); this.put("в", (byte) 10);
                this.put("w", (byte) 48); this.put("ш", (byte) 10);
                this.put("x", (byte) 49); this.put("х", (byte) 10);
                this.put("y", (byte) 50); this.put("ч", (byte) 10);
                this.put("z", (byte) 51); this.put("ж", (byte) 10);
                this.put("/", (byte) 52); // this.put("/", (byte) 10);
                this.put("1", (byte) 53); // this.put("K", (byte) 10);
                this.put("2", (byte) 54); // this.put("K", (byte) 10);
                this.put("3", (byte) 55); // this.put("K", (byte) 10);
                this.put("4", (byte) 56); // this.put("K", (byte) 10);
                this.put("5", (byte) 57); // this.put("K", (byte) 10);
                this.put("6", (byte) 58); // this.put("K", (byte) 10);
                this.put("7", (byte) 59); // this.put("K", (byte) 10);
                this.put("8", (byte) 60); // this.put("K", (byte) 10);
                this.put("9", (byte) 61); // this.put("K", (byte) 10);
                this.put("?", (byte) 62); // this.put("K", (byte) 10);
                this.put("!", (byte) 63); // this.put("K", (byte) 10);
                // @formatter:on
            }}
    );
}
