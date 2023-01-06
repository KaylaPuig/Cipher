package cipher;

public class Cipher {
    public static final String characters =
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`-=[]\\;',./~!@#$%^&*()_+{}|:\"<>?";

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
        seed = seed % characters.length();
        shiftNum = 1 + shiftNum % (characters.length() - 1);

        int i = 0;
        for(char c : phrase.toCharArray()) {
            if(!characters.contains(Character.toString(c))) {
                result += c;
                continue;
            }

            int index = characters.indexOf(Character.toString(c));
            result += Character.toString(characters.charAt(Math.floorMod(index + seed + (shiftNum * i), characters.length())));
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
        seed = seed % characters.length();
        shiftNum = 1 + shiftNum % (characters.length() - 1);

        int i = 0;
        for(char c : phrase.toCharArray()) {
            if(!characters.contains(Character.toString(c))) {
                result += c;
                continue;
            }

            int index = characters.indexOf(Character.toString(c));
            result += Character.toString(characters.charAt(Math.floorMod(index - seed - (shiftNum * i), characters.length())));
            i++;
        }
        return result;
    }
}
