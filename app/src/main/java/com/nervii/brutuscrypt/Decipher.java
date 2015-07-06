package com.nervii.brutuscrypt;

public class Decipher {
    private static String decodeMessage(String encryptedMessage, String cipher) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        cipher = cipher.toUpperCase();
        if (alpha.length() != cipher.length()) {
            throw new IllegalArgumentException("alpha and cipher string must have same length");
        }
        String decryptedMsg = "";
        for (int i=0; i < encryptedMessage.length(); i++) {
            String character = Character.toString(encryptedMessage.charAt(i));
            String charUpper = character.toUpperCase();
            boolean lowerCase = false;
            if (!charUpper.equals(character)) {
                lowerCase = true;
            }
            int index = cipher.indexOf(charUpper);
            if (index == -1) {
                decryptedMsg += character;
            }
            else {
                String eChar = Character.toString(alpha.charAt(index));
                decryptedMsg += lowerCase ? eChar.toLowerCase() : eChar;
            }
        }
        return decryptedMsg;
    }

    public static CrackedPackage CrackShiftCipher(String encryptedMessage) {
        CrackedPackage result = new CrackedPackage();
        WordList wordList = WordList.getInstance();

        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // Get a caesar cipher starting from A
        String cipher = Cipher.CaesarShift(alpha, 'A');

        // Use that cipher and decrypt the message
        String decryptedMessage = decodeMessage(encryptedMessage, cipher);

        // Get all the words in the message
        String[] words = decryptedMessage.split(" ");
        boolean cracked = true;

        // See if all the words are in our english word list
        for (int i=0; i<words.length; i++) {
            if (!wordList.IsWordInList(words[i])) {
                // if even one word is not found,
                // the cipher is wrong
                // This could be refined... maybe to set a threshold on 70% or words or something
                cracked = false;
            }
        }
        // if we cracked it, fill in the accepted cipher and decrypted message
        if (cracked) {
            result.Cipher = cipher;
            result.DecryptedMessage = decryptedMessage;
        }
        // Otherwise just set the cipher to 'Unable to Crack'
        else {
            result.Cipher = "--UNABLE TO CRACK--";
            result.DecryptedMessage = "";
        }
        return result;
    }
}
