package net.physalis.shibainu.presentation.requestid;

public final class Radix32 {

    /** exclude D, I, O, Q to avoid mistaking (Q's pronounciation is same as 9 in Japanese), so 32 */
    private static final String CHARS = "0123456789ABCEFGHJKLMNPRSTUVWXYZ";

    public static int radix = CHARS.length();

    public static String encode(int x) {
        if (x == 0) {
            return String.valueOf(CHARS.charAt(0));
        }

        StringBuilder sb = new StringBuilder();

        int value = x;
        while (value > 0) {
            int mod = value % radix;
            sb.append(CHARS.charAt(mod));
            value = value / radix;
        }

        return sb.reverse().toString();
    }

    public static String encode(int x, int size) {
        return padLeft(encode(x), size);
    }

    private static String padLeft(String s, int size) {
        int pad = size - s.length();

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < pad; i++) {
            sb.append(CHARS.charAt(0));
        }
        sb.append(s);
        return sb.toString();
    }

}
