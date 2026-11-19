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
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    public static long readLong(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }
}
