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

public enum Decoder {
    ;

    public static String decodeInternetCode(String code) {
        byte[] bytes = new byte[16];
        for(int i = 0; i < 16; i++) {
            bytes[i] = DecodingData.CODE_INDEX_TABLE.get(Character.toString(code.charAt(i)));
        }

        for(int i = 6; i > 0; i--) {
            int byte1 = 2 * i + i / 6;
            int bi1 = i % 6;
            int byte2 = 15;
            int bi2 = i - 1;

            Utils.swapBits(byte1, byte2, bi1, bi2, bytes);
        }

        String bin = Integer.toBinaryString(bytes[bytes.length - 1]);
        int index = Integer.parseInt(bin.substring(0, 3), 2);

        byte firstRoundKey = DecodingData.KEY[index];
        byte secondRoundKey = DecodingData.KEY[DecodingData.KEY.length - 1 - index];

        for(int i = 30; i > 0; i--) {
            int m = (i * firstRoundKey + 45) % 90;
            int n = (i * secondRoundKey + 45) % 90;
            int p = m % 6;
            int q = n % 6;

            int byte1 = (m - p) / 6;
            int bi1 = p;
            int byte2 = (n - q) / 6;
            int bi2 = q;

            Utils.swapBits(byte1, byte2, bi1, bi2, bytes);
        }

        byte thirdRoundKey = DecodingData.KEY[bytes[bytes.length - 1] & 0b111];

        for(int i = 40; i > 0; i--) {
            int m = i * thirdRoundKey % 90;
            int n = m % 6;
            int byteInd = (m - n) / 6;
            int bitInd = n;

            bytes[byteInd] ^= (1 << bitInd);
        }

        return Decoder.readInternetCode(bytes);
    }

    private static String readInternetCode(byte[] bytes) {
        int intCode = 0;
        intCode |= (bytes[8] & (0x1E000000 >> 25)) << 25;
        intCode |= (bytes[9] & (0x01000000 >> 19)) << 19;
        intCode |= (bytes[9] & (0x00001F00 >> 8)) << 8;
        intCode |= (bytes[10] & (0x001F0000 >> 14)) << 14;
        intCode |= (bytes[10] & (0x0000000C >> 2)) << 2;
        intCode |= (bytes[11] & (0x00000003 << 4)) >> 4;

        int l1 = Integer.parseInt(Integer.toHexString((intCode >> 24) & 0xFF), 10);
        int d1 = Integer.parseInt(Integer.toHexString((intCode >> 16) & 0xFF), 16);
        int l2 = Integer.parseInt(Integer.toHexString((intCode >> 8) & 0xFF), 16);
        int d2 = Integer.parseInt(Integer.toHexString((intCode >> 0) & 0xFF), 16);

        String[] letters1 = DecodingData.SAVE_INDEX_TABLE.get(l1 + 1).split("\\s");
        String[] letters2 = DecodingData.SAVE_INDEX_TABLE.get(l2 + 1).split("\\s");

        String russianCode = letters1[1] + d1 + letters2[1] + d2;
        String englishCode = letters1[0] + d1 + letters2[0] + d2;

        String russianLabel = I18N.getString("codeLabelRu");
        String englishLabel = I18N.getString("codeLabelEn");
        String andText = I18N.getString("andText");
        return String.format("%s: %s %s %s: %s", russianLabel, russianCode, andText, englishLabel, englishCode);
    }
}
