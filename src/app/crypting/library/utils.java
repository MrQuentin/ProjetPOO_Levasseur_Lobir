package app.crypting.library;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MrQuentin
 */

public class utils {

    private static String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    /**
     * Generate a Map which associate a letter to the
     * corresponding one depending on the row of the table
     * (A <-- B )
     * @param index An integer value which correspond to the shift beetween letters
     * @return A Map associating letters with other letters according to the shift index
     */
    public static Map<String, String> alphabetDecale(Integer index) {
        //create a map for the result
        Map<String, String> res = new HashMap<String, String>();
        //create pairs with a left shift of index value
        for (int j = 0 ; j < alphabet.length ; j++) {
            res.put(alphabet[j], alphabet[(j+index)%26]);
        }
        return res;
    }

    /**
     * Generate a Map which associate a letter to the
     * corresponding one depending on the row of the table
     * (A <-- Z )
     * @param index An integer value which correspond to the shift beetween letters
     * @return A Map associating letters with other letters according to the shift index
     */
    public static Map<String, String> reversealphabetDecale(Integer index) {
        //crete the result map
        Map<String, String> res = new HashMap<String, String>();
        //create pairs with a right shift of index value
        for (int j = 0 ; j < alphabet.length ; j++) {
            if((j-index)<0){
                res.put(alphabet[j], alphabet[(j - index + 26)]);
            } else {
                res.put(alphabet[j], alphabet[(j - index)]);
            }
        }
        return res;
    }

    /**
     * This methods simply return a string table whith
     * every letters of the alphabet
     * @return a String table of the alphabet
     */
    public static String[] getAlphabet() {
        return alphabet;
    }

    /**
     * Remove every accent from characters of a string and put it too upper case
     * @param s the string in which we want to remove accents
     * @return the string without accents
     */
    public static String stripAccentsToUpperCase(String s)	{
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s.toUpperCase();
    }

}
