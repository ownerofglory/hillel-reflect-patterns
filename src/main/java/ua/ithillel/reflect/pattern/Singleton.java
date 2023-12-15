package ua.ithillel.reflect.pattern;

public enum Singleton {
    INSTANCE("val");

    private String val;

    Singleton(String val) {
        this.val = val;
    }
}
