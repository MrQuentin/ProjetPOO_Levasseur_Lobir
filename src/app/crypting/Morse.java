package app.crypting;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static app.crypting.library.utils.*;

/**
 * @author MrQuentin
 */

public class Morse {

    private static String morse[] = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..",
            "--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..",
            "|",".---","..---","...--","....-",".....","-....","--...","---..","----.","-----"};
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890";


    private static Morse instance;

    private Morse(){}

    /**
     * Generate an instance of Morse if not already exist and fill the Code and Decode tables
     * @return Return an instance of Morse
     */
    public static Morse getInstance(){
        if(instance == null){
            //generate the instance of the class
            instance = new Morse();
        }
        return instance;
    }

    /**
     * Encrypt the given string text to a morse code
     * version of it
     * @param text Text to encrypt as a String
     * @return The Encrypted text as a String
     */
    public static String morseCrypString(String text) {
        //the result string
        String res ="";
        //remove accent and put text to upper case
        String capitalizedText = stripAccentsToUpperCase(text);
        for( int i = 0; i < capitalizedText.length(); i++ ){
            if( capitalizedText.charAt(i) == ' ' ){
                res += "| ";
                continue;
            } for( int j = 0; j < alphabet.length(); j++ ){
                if( alphabet.charAt(j) == capitalizedText.charAt(i) ){
                    res += morse[j] + " ";
                    break;
                }
            }
        }
        return res;
    }

    /**
     * Decrypt the given string morse code to text
     * version of it
     * @param text Morse code to decrypt as a String
     * @return The Decrypted text as a String
     */
    public static String morseDecryptString(String text) {
        //result string
        String res = "";
        //temporary string to store the morse letter
        String tmp = "";
        for( int i = 0; i < text.length(); i++ ) {
            if (text.charAt(i) == '|'){
                res += " ";
                tmp = "";
            } else if (text.charAt(i) == ' ') {
                for( int j = 0; j < morse.length ; j++ ){
                    if( morse[j].equals(tmp)){
                        res += alphabet.charAt(j);
                        tmp = "";
                        break;
                    }
                }
            } else {
                tmp += text.charAt(i);
            }
        }
        return res;
    }

    /**
     * Encrypt the file at the given path to morse code
     * It generate a new file at the same location with name XXXXCrypted.XXX for the file XXXX.XXX
     * @param path Path of the file to Encrypt as a String
     */
    public static void morseCryptFile(String path){
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
                String newPath = path.substring(0,path.lastIndexOf('.'))+"MorseCrypted"+path.substring(path.lastIndexOf('.'), path.length());
                PrintWriter writer = new PrintWriter(newPath);
                //we take all the encrypted lines  and write them in the file
                for (int k = 0 ; k < stringList.size() ; k++ ){
                    writer.println(morseCrypString(stringList.get(k)));
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
     * Dencrypt the file at the given path to morse code
     * It generate a new file at the sale location with name XXXXDecrypted.XXX for the file XXXX.XXX
     * @param path Path of the file to Encrypt as a String
     */
    public static void morseDeCryptFile(String path){
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
                String newPath = path.substring(0,path.lastIndexOf('.'))+"MorseDecrypted"+path.substring(path.lastIndexOf('.'), path.length());
                PrintWriter writer = new PrintWriter(newPath);
                for (int i = 0 ; i < stringList.size() ; i++ ){
                    //we take every lines decrypt them and write them in the file
                    writer.println(morseDecryptString(stringList.get(i)));
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

}
