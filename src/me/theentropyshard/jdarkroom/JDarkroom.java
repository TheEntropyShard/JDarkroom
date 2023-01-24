package me.theentropyshard.jdarkroom;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class JDarkroom {
    public JDarkroom() {
        if(instance != null) {
            throw new IllegalStateException("JDarkroom already running!");
        }
        instance = this;

        new View();
    }

    public String findInSave(File file) {
        if(file == null || file.isDirectory()) {
            return null;
        }

        StringBuilder russianCode = new StringBuilder();
        StringBuilder englishCode = new StringBuilder();
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

        return russianCode + " or " + englishCode;
    }

    public String decodeInternetCode(String code) {
        if(code == null || code.length() != 16) {
            return null;
        }

        byte[] bytes = new byte[16];
        for(int i = 0; i < 16; i++) {
            bytes[i] = DecodingData.CODE_INDEX_TABLE.get(Character.toString(code.charAt(i)));
        }

        for(int i = 6; i > 0; i--) {
            int byte1 = 2 * i + i / 6;
            int bi1 = i % 6;
            int byte2 = 15;
            int bi2 = i - 1;

            JDarkroom.swapBits(byte1, byte2, bi1, bi2, bytes);
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

            JDarkroom.swapBits(byte1, byte2, bi1, bi2, bytes);
        }

        byte thirdRoundKey = DecodingData.KEY[bytes[bytes.length - 1] & 0b111];

        for(int i = 40; i > 0; i--) {
            int m = i * thirdRoundKey % 90;
            int n = m % 6;
            int byteInd = (m - n) / 6;
            int bitInd = n;

            bytes[byteInd] ^= (1 << bitInd);
        }

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

        return russianCode + " or " + englishCode;
    }

    /**
     * Swaps two different bits in two numbers
     *
     * @param byteInd1 Index of first byte
     * @param byteInd2 Index of second byte
     * @param bi1      0-based bit from first number, indexed from right
     * @param bi2      0-based bit from second number, indexed from right
     * @param bytes    Byte array
     */
    public static void swapBits(int byteInd1, int byteInd2, int bi1, int bi2, byte... bytes) {
        int bit1 = (bytes[byteInd1] >> bi1) & 1;
        int bit2 = (bytes[byteInd2] >> bi2) & 1;

        if(bit1 == 0) {
            bytes[byteInd2] &= ~(1 << bi2);
        } else {
            bytes[byteInd2] |= 1 << bi2;
        }

        if(bit2 == 0) {
            bytes[byteInd1] &= ~(1 << bi1);
        } else {
            bytes[byteInd1] |= 1 << bi1;
        }
    }

    private static JDarkroom instance;

    public static JDarkroom getInstance() {
        return instance;
    }
}
