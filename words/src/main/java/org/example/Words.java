package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Words {
    public static int NICK_NAMES_COUNT = 100_000;
    public static AtomicInteger threeLetterWord = new AtomicInteger(0);
    private static AtomicInteger fourLetterWord = new AtomicInteger(0);
    private static AtomicInteger fiveLetterWord = new AtomicInteger(0);

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    private static boolean hasEqualChars(String string) {
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) != string.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean inOrder(String string) {
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) < string.charAt(i - 1)) {
                return false;
            }
        }
        return !hasEqualChars(string);
    }

    private static boolean isPalindrome(String string) {
        int i = 0;
        int j = string.length() - 1;
        while (i <= j) {
            if (string.charAt(i) != string.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return !hasEqualChars(string);
    }

    public static void printWordsCount() {
        System.out.println("Красивых слов с длиной 3: " + threeLetterWord.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + fourLetterWord.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + fiveLetterWord.get() + " шт");
    }

    public static void incrementCounter(String text) {
        switch (text.length()) {
            case 3:
                threeLetterWord.addAndGet(1);
                break;
            case 4:
                fourLetterWord.addAndGet(1);
                break;
            case 5:
                fiveLetterWord.addAndGet(1);
                break;
        }
    }

    public static void palindromeWords(String[] texts) {
        for (int i = 0; i < texts.length; i++) {
            if (isPalindrome(texts[i])) {
                incrementCounter(texts[i]);
            }
        }
    }

    public static void orderCharsWords(String[] texts) {
        for (int i = 0; i < texts.length; i++) {
            if (inOrder(texts[i])) {
                incrementCounter(texts[i]);
            }
        }
    }

    public static void equalCharsWords(String[] texts) {
        for (int i = 0; i < texts.length; i++) {
            if (hasEqualChars(texts[i])) {
                incrementCounter(texts[i]);
            }
        }
    }

    public static void countWords(String[] texts, int length) {
        for (int i = 0; i < texts.length; i++) {
            if (texts[i].length() != length) {
                continue;
            }
            if (inOrder(texts[i]) || isPalindrome(texts[i])) {
                incrementCounter(texts[i]);
            }
        }
    }

}
