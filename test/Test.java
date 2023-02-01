import me.theentropyshard.jdarkroom.Decoder;

import java.util.Objects;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(Objects.requireNonNull(Test.class.getResourceAsStream("/test_data.txt")));
            for(int i = 1; scanner.hasNextLine(); i++) {
                String code = scanner.nextLine();
                if(code.length() != 16) {
                    System.err.println("Found illegal test code in test_data.txt: " + code + ", line=" + i + ", length=" + code.length());
                    continue;
                }
                String decoded = Decoder.decodeInternetCode(code);
                System.out.println("Successfully decoded: " + code + " into " + decoded);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
