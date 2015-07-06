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
        String cipher = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String decryptedMessage = decodeMessage(encryptedMessage, cipher);
        String[] words = decryptedMessage.split(" ");
        WordList wordList = WordList.getInstance();
        boolean cracked = true;
        for (int i=0; i<words.length; i++) {
            if (!wordList.IsWordInList(words[i])) {
                cracked = false;
            }
        }
        if (cracked) {
            result.Cipher = cipher;
            result.DecryptedMessage = decryptedMessage;
        }
        else {
            result.Cipher = "UNABLE TO CRACK";
            result.DecryptedMessage = "";
        }
        return result;
    }
}
