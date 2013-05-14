package net.physalis.shibainu.config;

public final class Env {

    private Env() {
    }

    public static String name() {
        String s = System.getProperty("app.env");
        if (s == null) throw new IllegalStateException("System property app.env not found.");
        else return s;
    }
}
