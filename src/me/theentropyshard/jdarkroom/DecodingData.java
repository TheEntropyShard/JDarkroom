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

public enum DecodingData {
    ;

    /**
     * Offset, where Akuda locker code located in save
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
     * Table for decoding Internet Code to bytes, english and russian letters
     * Example code: fCe\w9!iBXJ1ijn&
     */
    public static final Map<Character, Byte> FROM_INTERNET_CODE = Collections.unmodifiableMap(
            new HashMap<Character, Byte>() {{
                // @formatter:off
                this.put('A', (byte) 0); this.put('А', (byte) 0);
                this.put('B', (byte) 1); this.put('Б', (byte) 1);
                this.put('C', (byte) 2); this.put('Ц', (byte) 2);
                this.put('D', (byte) 3); this.put('Д', (byte) 3);
                this.put('E', (byte) 4); this.put('Е', (byte) 4);
                this.put('F', (byte) 5); this.put('Ф', (byte) 5);
                this.put('G', (byte) 6); this.put('Г', (byte) 6);
                this.put('H', (byte) 7); this.put('Ю', (byte) 7);
                this.put('+', (byte) 8);
                this.put('J', (byte) 9); this.put('Й', (byte) 9);
                this.put('K', (byte) 10); this.put('К', (byte) 10);
                this.put('L', (byte) 11); this.put('Л', (byte) 11);
                this.put('M', (byte) 12); this.put('М', (byte) 12);
                this.put('N', (byte) 13); this.put('Н', (byte) 13);
                this.put('\\', (byte) 14);
                this.put('P', (byte) 15); this.put('П', (byte) 15);
                this.put('Q', (byte) 16); this.put('Я', (byte) 16);
                this.put('R', (byte) 17); this.put('Р', (byte) 17);
                this.put('S', (byte) 18); this.put('С', (byte) 18);
                this.put('T', (byte) 19); this.put('Т', (byte) 19);
                this.put('U', (byte) 20); this.put('У', (byte) 20);
                this.put('V', (byte) 21); this.put('В', (byte) 21);
                this.put('W', (byte) 22); this.put('Ш', (byte) 22);
                this.put('X', (byte) 23); this.put('Х', (byte) 23);
                this.put('Y', (byte) 24); this.put('Ч', (byte) 24);
                this.put('Z', (byte) 25); this.put('Ж', (byte) 25);
                this.put('a', (byte) 26); this.put('а', (byte) 26);
                this.put('b', (byte) 27); this.put('б', (byte) 27);
                this.put('c', (byte) 28); this.put('ц', (byte) 28);
                this.put('d', (byte) 29); this.put('д', (byte) 29);
                this.put('e', (byte) 30); this.put('е', (byte) 30);
                this.put('f', (byte) 31); this.put('ф', (byte) 31);
                this.put('g', (byte) 32); this.put('г', (byte) 32);
                this.put('h', (byte) 33); this.put('ю', (byte) 33);
                this.put('i', (byte) 34); this.put('и', (byte) 34);
                this.put('j', (byte) 35); this.put('й', (byte) 35);
                this.put('k', (byte) 36); this.put('к', (byte) 36);
                this.put('&', (byte) 37);
                this.put('m', (byte) 38); this.put('м', (byte) 38);
                this.put('n', (byte) 39); this.put('н', (byte) 39);
                this.put('o', (byte) 40); this.put('о', (byte) 40);
                this.put('p', (byte) 41); this.put('п', (byte) 41);
                this.put('q', (byte) 42); this.put('я', (byte) 42);
                this.put('r', (byte) 43); this.put('р', (byte) 43);
                this.put('s', (byte) 44); this.put('с', (byte) 44);
                this.put('t', (byte) 45); this.put('т', (byte) 45);
                this.put('u', (byte) 46); this.put('у', (byte) 46);
                this.put('v', (byte) 47); this.put('в', (byte) 47);
                this.put('w', (byte) 48); this.put('ш', (byte) 48);
                this.put('x', (byte) 49); this.put('х', (byte) 49);
                this.put('y', (byte) 50); this.put('ч', (byte) 50);
                this.put('z', (byte) 51); this.put('ж', (byte) 51);
                this.put('/', (byte) 52);
                this.put('1', (byte) 53);
                this.put('2', (byte) 54);
                this.put('3', (byte) 55);
                this.put('4', (byte) 56);
                this.put('5', (byte) 57);
                this.put('6', (byte) 58);
                this.put('7', (byte) 59);
                this.put('8', (byte) 60);
                this.put('9', (byte) 61);
                this.put('?', (byte) 62);
                this.put('!', (byte) 63);
                // @formatter:on
            }}
    );

    /**
     * Table for transforming bytes into game code
     * Format of code: letter-digit-letter-digit <br>
     * Example: N5K9
     */
    public static final Map<Byte, String[]> BYTES_TO_CODE = Collections.unmodifiableMap(
            new HashMap<Byte, String[]>() {{
                // @formatter:off
                this.put((byte) 0, new String[]{"A", "А"});
                this.put((byte) 1, new String[]{"B", "Б"});
                this.put((byte) 2, new String[]{"C", "Ц"});
                this.put((byte) 3, new String[]{"D", "Д"});
                this.put((byte) 4, new String[]{"E", "Е"});
                this.put((byte) 5, new String[]{"F", "Ф"});
                this.put((byte) 6, new String[]{"G", "Г"});
                this.put((byte) 7, new String[]{"H", "Ю"});
                this.put((byte) 8, new String[]{"+", "+"});
                this.put((byte) 9, new String[]{"J", "Й"});
                this.put((byte) 10, new String[]{"K", "К"});
                this.put((byte) 11, new String[]{"L", "Л"});
                this.put((byte) 12, new String[]{"M", "М"});
                this.put((byte) 13, new String[]{"N", "Н"});
                this.put((byte) 14, new String[]{"\\", "\\"});
                this.put((byte) 15, new String[]{"P", "П"});
                this.put((byte) 16, new String[]{"Q", "Я"});
                this.put((byte) 17, new String[]{"R", "Р"});
                this.put((byte) 18, new String[]{"S", "С"});
                this.put((byte) 19, new String[]{"T", "Т"});
                this.put((byte) 20, new String[]{"U", "У"});
                this.put((byte) 21, new String[]{"V", "В"});
                this.put((byte) 22, new String[]{"W", "Ш"});
                this.put((byte) 23, new String[]{"X", "Х"});
                this.put((byte) 24, new String[]{"Y", "Ч"});
                this.put((byte) 25, new String[]{"Z", "Ж"});
                this.put((byte) 26, new String[]{"a", "а"});
                this.put((byte) 27, new String[]{"b", "б"});
                this.put((byte) 28, new String[]{"c", "ц"});
                this.put((byte) 29, new String[]{"d", "д"});
                this.put((byte) 30, new String[]{"e", "е"});
                this.put((byte) 31, new String[]{"f", "ф"});
                this.put((byte) 32, new String[]{"g", "г"});
                this.put((byte) 33, new String[]{"h", "ю"});
                this.put((byte) 34, new String[]{"i", "и"});
                this.put((byte) 35, new String[]{"j", "й"});
                this.put((byte) 36, new String[]{"k", "к"});
                this.put((byte) 37, new String[]{"&", "&"});
                this.put((byte) 38, new String[]{"m", "м"});
                this.put((byte) 39, new String[]{"n", "н"});
                this.put((byte) 40, new String[]{"o", "о"});
                this.put((byte) 41, new String[]{"p", "п"});
                this.put((byte) 42, new String[]{"q", "я"});
                this.put((byte) 43, new String[]{"r", "р"});
                this.put((byte) 44, new String[]{"s", "с"});
                this.put((byte) 45, new String[]{"t", "т"});
                this.put((byte) 46, new String[]{"u", "у"});
                this.put((byte) 47, new String[]{"v", "в"});
                this.put((byte) 48, new String[]{"w", "ш"});
                this.put((byte) 49, new String[]{"x", "х"});
                this.put((byte) 50, new String[]{"y", "ч"});
                this.put((byte) 51, new String[]{"z", "ж"});
                this.put((byte) 52, new String[]{"/", "/"});
                this.put((byte) 53, new String[]{"1", "1"});
                this.put((byte) 54, new String[]{"2", "2"});
                this.put((byte) 55, new String[]{"3", "3"});
                this.put((byte) 56, new String[]{"4", "4"});
                this.put((byte) 57, new String[]{"5", "5"});
                this.put((byte) 58, new String[]{"6", "6"});
                this.put((byte) 59, new String[]{"7", "7"});
                this.put((byte) 60, new String[]{"8", "8"});
                this.put((byte) 61, new String[]{"9", "9"});
                this.put((byte) 62, new String[]{"?", "?"});
                this.put((byte) 63, new String[]{"!", "!"});
                // @formatter:on
            }}
    );
}
