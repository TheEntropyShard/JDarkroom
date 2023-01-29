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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Utils {
    ;

    private static final Map<String, BufferedImage> IMAGE_CACHE = new HashMap<>();

    public static BufferedImage loadImage(String path) {
        if(Utils.IMAGE_CACHE.containsKey(path)) {
            return Utils.IMAGE_CACHE.get(path);
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(Utils.class.getResourceAsStream(path)));
            Utils.IMAGE_CACHE.put(path, bufferedImage);
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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
}
