package de.mirkosertic;

public abstract class Main {

    public static final Main ONE = new Main() {};
    public static final Main TWO = new Main() {};
    public static final Main THREE = new Main() {};

    public static Main valueOf(String aValue) {
        switch (aValue) {
            case "THREE":
                return THREE;
            case "ONE":
                return ONE;
            case "TWO":
                return TWO;
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        Main theInstance = valueOf("");
    }
}