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
            bytes[i] = DecodingData.FROM_INTERNET_CODE.get(code.charAt(i));
        }

        for(int i = 6; i > 0; i--) {
            int byte1 = 2 * i + i / 6;
            int bi1 = i % 6;
            int byte2 = 15;
            int bi2 = i - 1;

            Utils.swapBits(byte1, byte2, bi1, bi2, bytes);
        }

        int index = (bytes[15] >> 3) & 0b111;

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

        byte thirdRoundKey = DecodingData.KEY[bytes[15] & 0b111];
        for(int i = 40; i > 0; i--) {
            int m = i * thirdRoundKey % 90;
            int n = m % 6;
            int byteInd = (m - n) / 6;
            int bitInd = n;

            bytes[byteInd] ^= (1 << bitInd);
        }

        return Decoder.getGameInfo(bytes);
    }

    public static String getGameInfo(byte[] bytes) {
        String resultLabel = I18N.getString("resultLabel");
        String[] internetCodes = Decoder.readCode(bytes).split("_");
        String totalPlaytime = Decoder.readTotalPlaytime(bytes);
        return String.format(resultLabel, internetCodes[0], internetCodes[1], totalPlaytime);
    }

    public static String readCode(byte[] bytes) {
        int intCode = 0;
        intCode |= (bytes[8]  & (0x1E000000 >> 25)) << 25;
        intCode |= (bytes[9]  & (0x01000000 >> 19)) << 19;
        intCode |= (bytes[9]  & (0x00001F00 >>  8)) <<  8;
        intCode |= (bytes[10] & (0x001F0000 >> 14)) << 14;
        intCode |= (bytes[10] & (0x0000000C >>  2)) <<  2;
        intCode |= (bytes[11] & (0x00000003 <<  4)) >>  4;

        int l1 = (intCode >> 24) & 0xFF;
        int d1 = (intCode >> 16) & 0xFF;
        int l2 = (intCode >>  8) & 0xFF;
        int d2 = (intCode >>  0) & 0xFF;

        String[] letters1 = DecodingData.BYTES_TO_CODE.get(Integer.valueOf(l1).byteValue());
        String[] letters2 = DecodingData.BYTES_TO_CODE.get(Integer.valueOf(l2).byteValue());

        // From https://9214.github.io/13
        // "Recall that locker code is stored in letter-digit-letter-digit format – letter
        // is a letter index from 0-based A-Z alphabet, and digit is actual digit."
        String russianCode = letters1[1] + d1 + letters2[1] + d2;
        String englishCode = letters1[0] + d1 + letters2[0] + d2;

        return russianCode + "_" + englishCode;
    }

    public static String readTotalPlaytime(byte[] bytes) {
        return (bytes[14] < 10 ? "0" + bytes[14] : "" + bytes[14]) + ":" +
               (bytes[13] < 10 ? "0" + bytes[13] : "" + bytes[13]) + ":" +
               (bytes[15] < 10 ? "0" + bytes[15] : "" + bytes[15]);
    }
}
