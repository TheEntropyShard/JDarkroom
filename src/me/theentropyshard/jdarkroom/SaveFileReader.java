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

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public enum SaveFileReader {
    ;

    public static String findInSave(File file) {
        if(file == null || file.isDirectory()) {
            return null;
        }

        StringBuilder russianCode = new StringBuilder();
        StringBuilder englishCode = new StringBuilder();
        String totalPlaytime = "00:00:00";
        try(RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            if(raf.length() <= 0) {
                return null;
            }

            raf.seek(DecodingData.CODE_OFFSET);

            for(int i = 0; i < 16; i++) {
                byte b = raf.readByte();
                if(i % 4 == 0) {
                    String[] letters = DecodingData.SAVE_INDEX_TABLE.get((int) b).split("\\s");
                    russianCode.append(letters[1]);
                    englishCode.append(letters[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String resultLabel = I18N.getString("resultLabel");
        return String.format(resultLabel, russianCode, englishCode, totalPlaytime);
    }
}
