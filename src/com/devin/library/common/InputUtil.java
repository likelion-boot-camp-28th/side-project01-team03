package com.devin.library.common;

import java.util.Scanner;

/**
 * 콘솔 입력 공용 유틸.
 * System.in Scanner는 프로그램 전체에서 하나만 써야 하므로 모든 도메인이 이 클래스를 사용한다.
 */
public class InputUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUtil() {
    }

    public static String readNonBlank(String prompt) {
        while (true) {
            String input = readLine(prompt);
            if (input.isEmpty()) {
                System.out.println("\u001B[31m값을 입력해주세요.\u001B[0m");
            }else {
                return input;
            }
        }
    }

    public static boolean readYesNo(String prompt) {
        while (true) {
            String input = readLine(prompt);
            if (input.equalsIgnoreCase("y")) {
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("\u001B[31my 또는 n을 입력해주세요.\u001B[0m");
            }
        }
    }

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31m숫자를 입력해주세요.\u001B[0m");
            }
        }
    }

    public static long readLong(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31m숫자를 입력해주세요.\u001B[0m");
            }
        }
    }
}
