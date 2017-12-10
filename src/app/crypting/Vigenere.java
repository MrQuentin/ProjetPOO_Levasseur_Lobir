package app.crypting;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MrQuentinet
 */
public class Vigenere {

	private static String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private static Map<String, Map<String,String>> cryptingtable;
	private static Map<String, Map<String,String>> decryptingtable;

	private static Vigenere instance;
	
	private Vigenere(){}

	/**
	 * Generate an instance of app.crypting.Vigenere if not already exist and fill the Code and Decode tables
	 * @return Return an instance of app.crypting.Vigenere
	 */
	public static Vigenere getInstance(){
		if(instance == null){
			//generate the instance of the class
			instance = new Vigenere();
			//fill the tables
			generateTables();
		}
		return instance;
	}

	/**
	 * Generate the content of Code and Decode tables
	 */
	private static void generateTables(){
		//create the maps
		cryptingtable = new HashMap<String, Map<String,String>>();
		decryptingtable = new HashMap<String, Map<String,String>>();
		//creates the content of encryption and decryption tables
		for (int i = 0 ; i < alphabet.length ; i++) {
			cryptingtable.put(alphabet[i],alphabetDecale(i));
			decryptingtable.put(alphabet[i],reversealphabetDecale(i));
		}
	}

	/**
	 * Generate a Map witch associate a letter to the
     * corresponding one depending on the row of the table
	 * (A <-- B )
	 * @param index An integer value witch correspond to the shift beetween letters
	 * @return A Map associating letters with other letters according to the shift index
	 */
	private static Map<String, String> alphabetDecale(Integer index) {
	    //create a map for the result
		Map<String, String> res = new HashMap<String, String>();
		//create pairs with a left shift of index value
		for (int j = 0 ; j < alphabet.length ; j++) {
			res.put(alphabet[j], alphabet[(j+index)%26]);
		}
		return res;
	}

    /**
     * Generate a Map witch associate a letter to the
     * corresponding one depending on the row of the table
     * (A <-- Z )
     * @param index An integer value witch correspond to the shift beetween letters
     * @return A Map associating letters with other letters according to the shift index
     */
	private static Map<String, String> reversealphabetDecale(Integer index) {
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
	 * Encrypt the given string text according to the app.crypting.Vigenere algorythm with the key key
	 * @param text Test to encrypt as a String
	 * @param key Encryption key as a String
	 * @return The Encrypted text as a String
	 */
	public static String vigenereCryptString(String text, String key){
	    //create the result String
		String res = "";
		//store the key without accents and to upper case
		String cryptkey = (stripAccentsToUpperCase(key));
        //store the text to encrypt without accents and to upper case
		String capitalizedText = (stripAccentsToUpperCase(text));
		//value of the index in the key
		Integer keyindex = 0;
		//for each character of the text we test
		for (int i = 0 ; i < capitalizedText.length() ; i++){
		    //we test if the character is a letter if not we add it to the result
			if (!Character.isLetter(capitalizedText.charAt(i))) {
				res += capitalizedText.charAt(i);
			//else we change it according to the algorythme of vigenere
			} else {
			    //we test if the character of the key is a letter else, we go to the next character
				while(!Character.isLetter(cryptkey.charAt(keyindex%cryptkey.length()))){keyindex++;}
				//we change the letter with the app.crypting table according to the letter of the text and the letter of the key
				res += cryptingtable.get(Character.toString(capitalizedText.charAt(i))).get(Character.toString(cryptkey.charAt((keyindex)%cryptkey.length())));
				//we go to the next charactere
				keyindex++;
			}
		}
		//we return the result of the encryption
		return res;
	}

	/**
	 * Decrypt the given text text according to the app.crypting.Vigenere algorythm with the encryption key key
	 * @param text Text to Decrypt as a String
	 * @param key Encryption key as a String
	 * @return The Decrypted text as a String
	 */
	public static String vigenereDecryptString(String text, String key){
	    //create the result String
		String res = "";
		//store the key without accents and in upper cases
		String cryptkey = (stripAccentsToUpperCase(key));
		//store the crypted text witout accents and in upper cases
		String capitalizedCryptedText = (stripAccentsToUpperCase(text));
		//value of the index in the key
		Integer keyindex = 0;
        //for each character of the text we test
		for (int i = 0 ; i < capitalizedCryptedText.length() ; i++) {
			//if the charactere of the text is a letter if not it adds it to the result else it crypt it
			if (!Character.isLetter(capitalizedCryptedText.charAt(i))) {
				res += capitalizedCryptedText.charAt(i);
			} else {
				//test if the character of the key is a letter if it increment the index of the key by one
				while (!Character.isLetter(cryptkey.charAt(keyindex%cryptkey.length()))){keyindex++;}
                //we change the letter with the decrypting table according to the letter of the text and the letter of the key
				res += decryptingtable.get(Character.toString(cryptkey.charAt((keyindex)%cryptkey.length()))).get(Character.toString(capitalizedCryptedText.charAt(i)));
				//we go to the next charactere in the key
				keyindex++;
			}
		}
		return res;
	}

    /**
     * Encrypt the file at the given path according to the app.crypting.Vigenere algorythm with the key key
     * It generate a new file at the sale location with name XXXXCrypted.XXX for the file XXXX.XXX
     * @param path Path of the file to Encrypt as a String
     * @param key Encryption key as a String
     */
	public static void vigenereCryptFile(String path, String key){
	    //create a list of string that will contain every lines of the text
		List<String> stringList = new ArrayList<>();
		//try to create the new file
		try
		{
			File f = new File (path);
            //we use StandardCharsets.ISO_8859_1 to get character with accent without errors
            BufferedReader br = new BufferedReader(new InputStreamReader( new FileInputStream(f), StandardCharsets.ISO_8859_1));
            //try to read the file
            try{
                //get the line from the file
				String line = br.readLine();
				//while we have a line
				while (line != null){
				    //we add the line to the list
					stringList.add(stripAccentsToUpperCase(line));
					//we get the next line
					line = br.readLine();
				}
				br.close();
				//create a new file at the same place to store the encrypted data
				String newPath = path.substring(0,path.lastIndexOf('.'))+"Crypted"+path.substring(path.lastIndexOf('.'), path.length());
				PrintWriter writer = new PrintWriter(newPath);
				//we take avery lines encrypt them and write them in the file
				for (int k = 0 ; k < stringList.size() ; k++ ){
					writer.println(vigenereCryptString(stringList.get(k), key));
				}
				writer.close();
			}catch (IOException exception){
				System.out.println ("Error occured during the reading :" + exception.getMessage());
			}
		}catch (FileNotFoundException exception){
			System.out.println ("File not found");
		}
        System.out.println ("File Encrypted !");
	}

    /**
     * Dencrypt the file at the given path according to the app.crypting.Vigenere algorythm with the key key
     * It generate a new file at the sale location with name XXXXDecrypted.XXX for the file XXXX.XXX
     * @param path Path of the file to Encrypt as a String
     * @param key Encryption key as a String
     */
	public static void vigenereDeCryptFile(String path, String key){
        //create a list of string that will contain every lines of the text
		List<String> stringList = new ArrayList<>();
        //try to create the new file
		try
		{
			File f = new File (path);
            //we use StandardCharsets.ISO_8859_1 to get character with accent without errors
            BufferedReader br = new BufferedReader(new InputStreamReader( new FileInputStream(f), StandardCharsets.ISO_8859_1));
            //try to read the file
			try{
                //get the line from the file
				String line = br.readLine();
                //while we have a line
				while (line != null){
                    //we add the line to the list
					stringList.add(stripAccentsToUpperCase(line));
                    //we get the next line
					line = br.readLine();
				}
				br.close();
                //create a new file at the same place to store the decrypted data
				String newPath = path.substring(0,path.lastIndexOf('.'))+"Decrypted"+path.substring(path.lastIndexOf('.'), path.length());
				PrintWriter writer = new PrintWriter(newPath);
				for (int i = 0 ; i < stringList.size() ; i++ ){
                    //we take avery lines decrypt them and write them in the file
					writer.println(vigenereDecryptString(stringList.get(i), key));
				}
				writer.close();
			}catch (IOException exception){
				System.out.println ("Error occured during the reading :" + exception.getMessage());
			}
		}catch (FileNotFoundException exception){
			System.out.println ("file not found");
		}
        System.out.println ("File Decrypted !");
    }

	/**
	 * Remove every accent from chracteres of a string and put it too upper case
	 * @param s the string in witch we want to remove accents
	 * @return the string without accents
	 */
	private static String stripAccentsToUpperCase(String s)	{
		s = Normalizer.normalize(s, Normalizer.Form.NFD);
		s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return s.toUpperCase();
	}
}