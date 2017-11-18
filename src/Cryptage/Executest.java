package Cryptage;

import java.text.Normalizer;
import java.util.Map;

public class Executest {

	public static void main(String[] args) {

		String text = "Bonjour mon nom est Quentin  !!!";
		String key = "You shall not look";
        //Encrypt a string
		String encrypted = Vigenere.getInstance().vigenereCryptString(text, key);
		//Decrypt an encrypted string
		String decrypted = Vigenere.getInstance().vigenereDecryptString(encrypted, key);

		System.out.println(text);
		System.out.println(encrypted);
		System.out.println(decrypted);

		//Encrypt a file
		Vigenere.getInstance().vigenereCryptFile("C:\\Users\\quent\\Desktop\\test.txt", "Bonjour");
		//Decrypt a file
		Vigenere.getInstance().vigenereDeCryptFile("C:\\Users\\quent\\Desktop\\testCrypted.txt", "Bonjour");

	}

}
