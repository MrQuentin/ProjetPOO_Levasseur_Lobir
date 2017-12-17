package app.crypting;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

import static app.crypting.library.utils.*;

public class Caesar {

    private static  Map<String,String> cryptingtable;
    private static  Map<String,String> decryptingtable;

    private static Caesar instance;

    public Caesar() {
    }
    /**
     * Generate an instance of Caesar if not already exist and fill the Code and Decode tables
     * @return Return an instance of Caesar
     */
    public static Caesar getInstance(){
        if (instance == null){
            //generate the instance of the class
            instance = new Caesar();
        }
        return instance;
    }
    /**
     * Encrypt the given string text according to the Caesar algorythm with the key key
     * @param text Text to encrypt as a String
     * @param key Encryption key as an Integer
     * @return The Encrypted text as a String
     */
    public static String caesarCryptString(String text,Integer key){
        //shifting the alphabet with the key
        cryptingtable = alphabetDecale(key);
        //create the result string
        String res = "";
        //store the text to encrypt without accents and to upper case
        String capitalizedText = (stripAccentsToUpperCase(text));
        //for each character of the text we test
        for (int i = 0;i<capitalizedText.length();i++){
            if (Character.isLetter(capitalizedText.charAt(i))) {
                res += cryptingtable.get(Character.toString((capitalizedText.charAt(i))));
            }else{
                res += capitalizedText.charAt(i);
            }
        }
        //we return the result of the encryption

        return res;
    }
    /**
     * Decrypt the given string text according to the Caesar algorythm with the key key
     * @param text Text to encrypt as a String
     * @param key Encryption key as an Integer
     * @return The Decrypted text as a String
     */
    public static  String caesarDecryptString(String text,Integer key){
        //reverse the shifting of the alphabet with the key
        decryptingtable = reversealphabetDecale(key);
        //create the result string
        String res = "";
        //store the text to encrypt without accents and to upper case
        String capitalizedText = (stripAccentsToUpperCase(text));
        //for each character of the text we test
        for (int i=0;i<capitalizedText.length();i++){
            if (Character.isLetter(capitalizedText.charAt(i))){
                res += decryptingtable.get(Character.toString(capitalizedText.charAt(i)));
            } else {
                res += capitalizedText.charAt(i);
            }
        }
        //we return the result of the decryption
        return res;

    }
    /**
     * Encrypt the file at the given path according to the Caesar algorythm with the key key
     * It generate a new file at the same location with name XXXXCaesarCrypted.XXX for the file XXXX.XXX
     * @param path Path of the file to Encrypt as a String
     * @param key Encryption key as an Integer
     */
    public static void caesarCryptFile(String path,Integer key){
        //create a list of string that will contain every lines of the text
        List<String> stringList = new ArrayList<>();
        //try to create the new file
        try{File f = new File(path);
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
                String newPath = path.substring(0,path.lastIndexOf('.'))+"CaesarCrypted"+path.substring(path.lastIndexOf('.'), path.length());
                PrintWriter writer = new PrintWriter(newPath);
                //we take avery lines encrypt them and write them in the file
                for (int k = 0 ; k < stringList.size() ; k++ ){
                    writer.println(caesarCryptString(stringList.get(k), key));
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
     * Decrypt the file at the given path according to the Caesar algorythm with the key key
     * It generate a new file at the sale location with name XXXXCaesarDecrypted.XXX for the file XXXX.XXX
     * @param path Path of the file to Decrypt as a String
     * @param key Encryption key as a String
     */
    public static void caesarDecryptFile(String path,Integer key){
        //create a list of string that will contain every lines of the text
        List<String> stringList = new ArrayList<>();
        //try to create the new file
        try {
            File f = new File(path);
            //we use StandardCharsets.ISO_8859_1 to get character with accent without errors
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.ISO_8859_1));
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
                String newPath = path.substring(0,path.lastIndexOf('.'))+"CaesarDecrypted"+path.substring(path.lastIndexOf('.'), path.length());
                PrintWriter writer = new PrintWriter(newPath);
                //we take avery lines encrypt them and write them in the file
                for (int k = 0 ; k < stringList.size() ; k++ ){
                    writer.println(caesarDecryptString(stringList.get(k), key));
                }
                writer.close();
            }catch (IOException exception){
                System.out.println ("Error occured during the reading :" + exception.getMessage());
            }
        }catch (FileNotFoundException exception){
            System.out.println ("File not found");
        }
        System.out.println ("File Decrypted !");


    }

}
