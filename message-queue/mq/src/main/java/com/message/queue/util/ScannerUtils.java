package com.message.queue.util;

import java.util.Scanner;
import java.util.function.Consumer;

/**
 * @author xuweizhi
 * @since 2020/12/07 16:28
 */
public class ScannerUtils {

    public static void input(Consumer<String> consumer) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            consumer.accept(input);
        }

    }
}
