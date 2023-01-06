package cipher;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter a random seed: ");
        String key = myScanner.nextLine();

        System.out.println();
        System.out.println("Enter your phrase to encrypt/decrypt");
        String phrase = myScanner.nextLine();

        System.out.println();
        System.out.println("Do you want to encrypt or decrypt? (E/d)");
        boolean doEncrypt = !myScanner.nextLine().toLowerCase().equals("d");

        String result = doEncrypt ? encrypt(phrase, key) : decrypt(phrase, key);

        System.out.println();
        System.out.println("Your result is \"" + result + "\"");
        myScanner.close();
    }

    public static String encrypt(String phrase, String key) {
        int shiftNum = 0;
        int seed = 0;
        String result = "";
        boolean tempB = true;

        /* Turn key into
         * (1) a base shift amount like a regular Caesar Cipher, and
         * (2) an amount for the phrase to shift after each letter
        */
        for(char c : key.toCharArray()) {
            if(tempB)
                seed += c;
            else 
                shiftNum += c;
            tempB = !tempB;
        }
        seed = seed % 26;
        shiftNum = 1 + shiftNum % 25;

        int i = 0;
        for(char c : phrase.toCharArray()) {
            if(!Character.isAlphabetic(c)) {
                result += c;
                continue;
            }

            char base = (Character.isUpperCase(c)) ? 'A' : 'a';

            int temp = c - base;
            temp = Math.floorMod(temp + seed + (shiftNum * i), 26);
            result += Character.toString(base + temp);

            i++;
        }
        return result;
    }

    public static String decrypt(String phrase, String key) {
        int shiftNum = 0;
        int seed = 0;
        String result = "";
        boolean tempB = true;

        /* Turn key into
         * (1) a base shift amount like a regular Caesar Cipher, and
         * (2) an amount for the phrase to shift after each letter
        */
        for(char c : key.toCharArray()) {
            if(tempB)
                seed += c;
            else 
                shiftNum += c;
            tempB = !tempB;
        }
        seed = seed % 26;
        shiftNum = 1 + shiftNum % 25;

        int i = 0;
        for(char c : phrase.toCharArray()) {
            if(!Character.isAlphabetic(c)) {
                result += c;
                continue;
            }

            char base = (Character.isUpperCase(c)) ? 'A' : 'a';

            int temp = c - base;
            temp = Math.floorMod(temp - seed - (shiftNum * i), 26);
            result += Character.toString(base + temp);

            i++;
        }
        return result;
    }
}
