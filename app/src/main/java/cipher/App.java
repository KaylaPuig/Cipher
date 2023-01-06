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

        String result = doEncrypt ? Cipher.encrypt(phrase, key) : Cipher.decrypt(phrase, key);

        System.out.println();
        System.out.println("Your result is \"" + result + "\"");
        myScanner.close();
    }
}
