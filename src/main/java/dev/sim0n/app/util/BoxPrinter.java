package dev.sim0n.app.util;

import java.util.List;

public class BoxPrinter {

    private static final String TL = "╔";
    private static final String TR = "╗";
    private static final String BL = "╚";
    private static final String BR = "╝";
    private static final String H  = "═";
    private static final String V  = "║";
    private static final String ML = "╠";
    private static final String MR = "╣";

    public static void printBox(String title, List<String> lines) {
        int width = title.length();
        for (String line : lines) width = Math.max(width, stripAnsi(line).length());
        width += 4;

        String top    = TL + H.repeat(width) + TR;
        String bottom = BL + H.repeat(width) + BR;
        String divider = ML + H.repeat(width) + MR;
        String titleLine = V + " " + pad(title, width - 2) + " " + V;

        System.out.println(top);
        System.out.println(titleLine);
        System.out.println(divider);
        for (String line : lines) {
            int padLen = width - 2 - stripAnsi(line).length();
            System.out.println(V + " " + line + " ".repeat(Math.max(0, padLen)) + " " + V);
        }
        System.out.println(bottom);
    }

    public static void printSummaryBox(String title, int passed, int failed, long ms) {
        int total = passed + failed;
        String passLine  = "  Passed : " + passed + " / " + total;
        String failLine  = "  Failed : " + failed;
        String timeLine  = "  Time   : " + ms + "ms";
        String status    = failed == 0 ? "  Result : ALL TESTS PASSED" : "  Result : " + failed + " TEST(S) FAILED";

        int width = Math.max(title.length(),
                    Math.max(stripAnsi(status).length(),
                    Math.max(passLine.length(), timeLine.length()))) + 4;

        String top     = TL + H.repeat(width) + TR;
        String bottom  = BL + H.repeat(width) + BR;
        String divider = ML + H.repeat(width) + MR;

        System.out.println(top);
        System.out.println(V + " " + pad(title, width - 2) + " " + V);
        System.out.println(divider);
        printRow(passLine,  width);
        printRow(failLine,  width);
        printRow(timeLine,  width);
        System.out.println(divider);
        printRow(status, width);
        System.out.println(bottom);
    }

    private static void printRow(String text, int width) {
        int padLen = width - 2 - stripAnsi(text).length();
        System.out.println(V + text + " ".repeat(Math.max(0, padLen)) + " " + V);
    }

    private static String pad(String s, int len) {
        int padLen = len - stripAnsi(s).length();
        return s + " ".repeat(Math.max(0, padLen));
    }

    private static String stripAnsi(String s) {
        return s.replaceAll("\u001B\\[[;\\d]*m", "");
    }
}
