package converter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    final static private Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        // input
        boolean error = false;
        int outRadix = 0;
        String input = "";
        int inRadix = 0;

        if (SCANNER.hasNextInt()) {
            inRadix = SCANNER.nextInt();
        }

        if (SCANNER.hasNext()) {
            input = SCANNER.next();
        }

        if (SCANNER.hasNextInt()) {
            outRadix = SCANNER.nextInt();
        }

        if (!(inRadix > 0 && inRadix <= 36 )) { error = true; }

        if (!(outRadix > 0 && outRadix <= 36)) { error = true; }

        if (!error) {
            // first stage
            String[] number = input.split("\\.");
            int integer = inRadix <= 1 ? number[0].length() : Integer.parseInt(number[0], inRadix);
            double fractional = 0.0;
            if (number.length == 2) {
                for (int i = 0; i < number[1].length(); i++) {
                    fractional += Character.getNumericValue(number[1].charAt(i)) / Math.pow(inRadix, i + 1);
                }
            }

            // second stage
            StringBuilder builder = new StringBuilder(Integer.toString(integer, outRadix) + ".");
            for (int i = 0; i < 5; i++) {
                fractional *= outRadix;
                int temp = (int) fractional;
                if (temp >= 10) {
                    builder.append((char) (temp + 87));
                } else {
                    builder.append(temp);
                }
                fractional -= temp;
            }
            System.out.println(outRadix <= 1 ? "1".repeat(integer) : builder.toString());
        } else {
            System.out.println("error");
        }

    }

}
