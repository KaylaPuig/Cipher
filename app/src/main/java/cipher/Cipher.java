package cipher;

public class Cipher {
    public static final String characters =
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`-=[]\\;',./~!@#$%^&*()_+{}|:\"<>?";

    public static int generateSeed(String key) {
        int seed = 0;
        
        for(int i = 0; i < key.length(); i++) {
            int character = (int) key.charAt(i);
            seed += character << ((8 * i) % 32);
        }

        return seed;
    }

    public static String encrypt(String phrase, String key) {
        int seed = generateSeed(key);
        String result = "";

        int i = 0;
        for(char c : phrase.toCharArray()) {
            if(!characters.contains(Character.toString(c))) {
                result += c;
                continue;
            }

            int index = characters.indexOf(Character.toString(c));
            result += Character.toString(characters.charAt(Math.floorMod(index + seed + (seed * i), characters.length())));
            i++;
        }
        return result;
    }

    public static String decrypt(String phrase, String key) {
        int seed = generateSeed(key);
        String result = "";

        int i = 0;
        for(char c : phrase.toCharArray()) {
            if(!characters.contains(Character.toString(c))) {
                result += c;
                continue;
            }

            int index = characters.indexOf(Character.toString(c));
            result += Character.toString(characters.charAt(Math.floorMod(index - seed - (seed * i), characters.length())));
            i++;
        }
        return result;
    }
}
