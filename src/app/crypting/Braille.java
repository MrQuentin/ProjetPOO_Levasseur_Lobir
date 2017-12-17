package app.crypting;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static app.crypting.library.utils.stripAccentsToUpperCase;

/**
 * @author L'empapaouteur
 */

public class Braille {
    private static String braille[] = {"⠨⠁","⠨⠃","⠨⠉","⠨⠙","⠨⠑","⠨⠋","⠨⠛","⠨⠓","⠨⠊","⠨⠚","⠨⠅","⠨⠇","⠨⠍","⠨⠝","⠨⠕","⠨⠏","⠨⠟","⠨⠗","⠨⠎","⠨⠞","⠨⠥","⠨⠧","⠨⠺","⠨⠭","⠨⠽","⠨⠵"," ","⠠⠡","⠠⠣","⠠⠩","⠠⠹","⠠⠱","⠠⠫","⠠⠻","⠠⠳","⠠⠪","⠠⠼"};
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890";
    private static Braille instance;

    public Braille() {
    }
    /**
     * Generate an instance of Braille if not already exist and fill the Code and Decode tables
     * @return Return an instance of Braille
     */
    public static Braille getInstance(){
        if (instance == null){
            //generate instance of the class
            instance = new Braille();
        }
        return instance;
    }
    /**
     * Encrypt the given string text to a braille code
     * version of it
     * @param text Text to encrypt as a String
     * @return The Encrypted text as a String
     */
    public static String brailleCryptingString(String text){
        //the result String
        String res="";
        //remove accent from the text and put it to upper case
        String capitalizedText = stripAccentsToUpperCase(text);
        for (int i=0;i<capitalizedText.length();i++){
            if (capitalizedText.charAt(i)== ' '){
                res+=" ";
                continue;
            }
            for (int j =0;j<alphabet.length();j++){
               if (alphabet.charAt(j) == capitalizedText.charAt(i)){
                   res += braille[j] + " ";
                   break;
               }
            }

        }
        return res;
    }
    /**
     * Decrypt the given string braille code to text
     * version of it
     * @param text Braill code to decrypt as a String
     * @return The Decrypted text as a String
     */
    public static  String brailleDecryptString(String text){
        //the result String
        String res = "";
        //temporary String to store the braille code
        String tmp ="";
        for(int i=0;i<text.length();i++){
            if (text.charAt(i) == ' '){
                int index = 0 ;
                boolean isBraille = false;
                for (int l = 0 ; l < braille.length ; l++){
                    if (braille[l].equals(tmp)){
                        isBraille = ! isBraille;
                        index = l;
                        break;
                    }
                }
                if (isBraille){
                    res += alphabet.charAt(index);
                    tmp = "";
                }else {
                    res += text.charAt(i);
                    tmp = "";
                }
            } else {
                tmp += text.charAt(i);
            }
        }
        return res;
    }
    /**
     * Encrypt the file at the given path to braille code
     * It generate a new file at the same location with name XXXXBrailleCrypted.XXX for the file XXXX.XXX
     * @param path Path of the file to Encrypt as a String
     */
    public static void brailleCryptFile(String path){
        //create a list of string that will contain every lines of the text
        List<String> stringList = new ArrayList<>();
        //try to create the new file
        try{
            File f = new File(path);
            //we use StandardCharsets.ISO_8859_1 to get character with accent without errors
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.ISO_8859_1));
            //try to read the file
            try{
                //get the line from the file
                String line = br.readLine();
                //while we have a line
                while(line != null){
                    //we add the line to the list
                    stringList.add(stripAccentsToUpperCase(line));
                    //we get the next line
                    line = br.readLine();
                }
                br.close();
                //create a new file at the same place to store the encrypted data
                String newPath = path.substring(0,path.lastIndexOf('.'))+"BrailleCrypted"+path.substring(path.lastIndexOf('.'),path.length());
                PrintWriter writer = new PrintWriter(newPath);
                //we take all the encrypted lines  and write them in the file
                for(int k=0;k<stringList.size();k++){
                    writer.println(brailleCryptingString(stringList.get(k)));
                }
                writer.close();


            } catch (IOException e) {
                System.out.println ("Error occured during the reading :" + e.getMessage());            }
        } catch (FileNotFoundException e) {
            System.out.println ("File not found");
        }
        System.out.println ("File Encrypted !");
    }
    /**
     * Dencrypt the file at the given path to braille code
     * It generate a new file at the sale location with name XXXXBrailleDecrypted.XXX for the file XXXX.XXX
     * @param path Path of the file to Encrypt as a String
     */
    public static  void brailleDecryptFile(String path){
        //create a list of string that will contain every lines of the text
        List<String> stringList = new ArrayList<>();
        //try to create the new file
        try{
            File f = new File(path);
            //we use StandardCharsets.ISO_8859_1 to get character with accent without errors
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.ISO_8859_1));
            //try to read the file
            try{
                //get the line from the file
                String line = br.readLine();
                //while we have a line
                while(line != null){
                    //we add the line to the list
                    stringList.add(stripAccentsToUpperCase(line));
                    //we get the next line
                    line = br.readLine();
                }
                br.close();
                //create a new file at the same place to store the decrypted data
                String newPath = path.substring(0,path.lastIndexOf('.'))+"BrailleDecrypted"+path.substring(path.lastIndexOf('.'),path.length());
                PrintWriter writer = new PrintWriter(newPath);

                for(int k=0;k<stringList.size();k++){
                    //we take all the decrypted lines  and write them in the file
                    writer.println(brailleDecryptString(stringList.get(k)));
                }
                writer.close();


            } catch (IOException e) {
                System.out.println ("Error occured during the reading :" + e.getMessage());            }
        } catch (FileNotFoundException e) {
            System.out.println ("File not found");
        }
        System.out.println ("File Encrypted !");
    }

}
