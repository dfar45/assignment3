package assignment3;

import java.util.ArrayList;
import java.util.Scanner;

public class CryptoManager {

	private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;
    // Use 64-character matrix (8X8) for Playfair cipher  
    private static final String ALPHABET64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&'()*+,-./:;<=>?@[\\]^_";

    public static boolean isStringInBounds(String plainText) {
        for (int i = 0; i < plainText.length(); i++) {
            if (!(plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE)) {
                return false;
            }
        }
        return true;
    }

	

    public static String vigenereEncryption(String plainText, String key) {
    	String encrypt = "";
    	for (int i = 0; i < plainText.length(); i++) {
    		char c = plainText.charAt(i);
    		char s = key.charAt(i % key.length());
    		char w = ' ';
    		int plainIndex = 0, keyIndex = 0;
	    	for (int j = 0; j < ALPHABET64.length(); j++) {
	    		if (ALPHABET64.charAt(j) == c) {
	    			plainIndex = j;
	    		}
	    		if (ALPHABET64.charAt(j) == s) {
	    			keyIndex = j;
	    		}
	    	}
    		w = ALPHABET64.charAt(plainIndex + keyIndex);
	    	encrypt += w;
    	}
        return encrypt;
    }

    // Vigenere Decryption
    public static String vigenereDecryption(String encryptedText, String key) {
    	String decrypt = "";
    	for (int i = 0; i < encryptedText.length(); i++) {
    		char c = encryptedText.charAt(i);
    		char s = key.charAt(i % key.length());
    		char w = ' ';
    		int encryptIndex = 0, keyIndex = 0;
	    	for (int j = 0; j < ALPHABET64.length(); j++) {
	    		if (ALPHABET64.charAt(j) == c) {
	    			encryptIndex = j;
	    		}
	    		if (ALPHABET64.charAt(j) == s) {
	    			keyIndex = j;
	    		}
	    	}
    		w = ALPHABET64.charAt(encryptIndex - keyIndex);
	    	decrypt += w;
    	}
         return decrypt;
    }


	

    public static String playfairEncryption(String plainText, String key) {
        String encrypt = "";
        var message = new ArrayList<Character>(64);
 		for (int j = 0; j < ALPHABET64.length(); j++) {
 			message.add(ALPHABET64.charAt(j));
 		}
 		for (int i = 0; i < key.length(); i++) {
 	    	for (int c = 0; c < message.size(); c++) {
 	    		if (key.charAt(i) == message.get(c)) {
 	    			message.remove(c);
 	    		}
 	    	}
 			message.add(i, key.charAt(i));
 	   	}
 		char[][] matrix = new char[8][8];
 		int a = 0;
 		for (int i = 0; i < 8; i++) {
 			for (int j = 0; j < 8; j++) {
 				matrix[i][j] = message.get(a);
 				a++;
 			}
 		}
 		String[] pairs = new String[plainText.length() / 2];
 		for (int i = 0, j = 0; i < plainText.length(); j++) {
 			pairs[j] = plainText.charAt(i) +""+ plainText.charAt(i + 1);
 			String en = "";
 			int first = 0, second = 0, third = 0, fourth = 0;
 			for (int row = 0; row < 8; row++) {
 				for (int col = 0; col < 8; col++) {
 					if (pairs[j].charAt(0) == matrix[row][col]) {
 						first = row;
 						second = col;
 					}
 					if (pairs[j].charAt(1) == matrix[row][col]) {
 						third = row;
 						fourth = col;
 					}
 				}
 			}
 			if (first == third) {
 				en = matrix[first][(second + 1) % 8] +""+ matrix[third][(fourth + 1) % 8];
 			} else if (second == fourth) {
 				en = matrix[(first + 1) % 8][second] +""+ matrix[(third + 1) % 8][fourth];
 			} else {
 				en = matrix[first][fourth] +""+ matrix[third][second];
 			}
 			i += 2;
 			encrypt += en;
 		}
        return encrypt;
    }

    // Vigenere Decryption
    public static String playfairDecryption(String encryptedText, String key) {
        String decrypt = "";
        var message = new ArrayList<Character>(64);
  		for (int j = 0; j < ALPHABET64.length(); j++) {
  			message.add(ALPHABET64.charAt(j));
  		}
  		for (int i = 0; i < key.length(); i++) {
  	    	for (int c = 0; c < message.size(); c++) {
  	    		if (key.charAt(i) == message.get(c)) {
  	    			message.remove(c);
  	    		}
  	    	}
  			message.add(i, key.charAt(i));
  	   	}
  		char[][] matrix = new char[8][8];
  		int a = 0;
  		for (int i = 0; i < 8; i++) {
  			for (int j = 0; j < 8; j++) {
  				matrix[i][j] = message.get(a);
  				a++;
  			}
  		}
  		String[] pairs = new String[encryptedText.length() / 2];
         for (int i = 0, j = 0; i < encryptedText.length(); j++) {
 			pairs[j] = encryptedText.charAt(i) +""+ encryptedText.charAt(i + 1);
 			String de = "";
 			int first = 0, second = 0, third = 0, fourth = 0;
 			for (int row = 0; row < 8; row++) {
 				for (int col = 0; col < 8; col++) {
 					if (pairs[j].charAt(0) == matrix[row][col]) {
 						first = row;
 						second = col;
 					}
 					if (pairs[j].charAt(1) == matrix[row][col]) {
 						third = row;
 						fourth = col;
 					}
 				}
 			}
 			if (first == third) {
 				if (second == 0) {
 					de = matrix[first][7] +""+ matrix[third][fourth - 1];
 				} else if (fourth == 0) {
 					de = matrix[first][second - 1] +""+ matrix[third][7];
 				} else {
 					de = matrix[first][second - 1] +""+ matrix[third][fourth - 1];
 				}
 			} else if (second == fourth) {
 				de = matrix[first - 1][second] +""+ matrix[third - 1][fourth];
 			} else {
 				de = matrix[first][fourth] +""+ matrix[third][second];
 			}
 			i += 2;
 			decrypt += de;
 		}
         return decrypt;
    }

       
 
    public static String caesarEncryption(String plainText, int key) {
    	String encrypt = "";
    	for (int i = 0; i < plainText.length(); i++) {
	    	char c = plainText.charAt(i);
	    	char w = (char) (c + key);
	    	encrypt += w;
	   	}
		return encrypt;
    }

    // Caesar Decryption
    public static String caesarDecryption(String encryptedText, int key) {
    	String decrypt = "";
    	for (int i = 0; i < encryptedText.length(); i++) {
    		char c = encryptedText.charAt(i);
	    	char w = (char) (c - key);
	    	decrypt += w;
		}
		return decrypt;
    }
    public static void main (String[] args) {
    	Scanner input = new Scanner(System.in);
    	String vigenereText = input.next();
    	String keyword = input.next();
    	System.out.println(vigenereEncryption(vigenereText, keyword));
    	System.out.println(vigenereDecryption(vigenereEncryption(vigenereText, keyword), keyword));
    	System.out.println();
    	String playfairText = input.next();
    	String keywordTwo = input.next();
    	System.out.println(playfairEncryption(playfairText, keywordTwo));
    	System.out.println(playfairDecryption(playfairEncryption(playfairText, keywordTwo), keywordTwo));
    	System.out.println();
    	String caesarText = input.next();
    	int key = input.nextInt();
    	System.out.println(caesarEncryption(caesarText, key));
    	System.out.println(caesarDecryption(caesarEncryption(caesarText, key), key));
    	input.close();
    }
}
