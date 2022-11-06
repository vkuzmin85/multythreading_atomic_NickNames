package org.example;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[Words.NICK_NAMES_COUNT];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = Words.generateText("abc", 3 + random.nextInt(3));
        }

        Thread thread1 = new Thread(null, () -> Words.palindromeWords(texts), "Client1");
        Thread thread2 = new Thread(null, () -> Words.equalCharsWords(texts), "Client2");
        Thread thread3 = new Thread(null, () -> Words.orderCharsWords(texts), "Client3");
        thread1.start();
        thread2.start();
        thread3.start();

        thread3.join();
        thread2.join();
        thread1.join();

        Words.printWordsCount();

    }
}