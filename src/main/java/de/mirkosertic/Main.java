package de.mirkosertic;

import org.teavm.interop.Import;
import org.teavm.jso.JSBody;

public class Main {

    @JSBody(params = "aValue", script = "console.print(aValue);")
    @Import(module = "log", name = "log_int")
    public static native void log(int aValue);

    @JSBody(params = "aValue", script = "console.print(aValue);")
    @Import(module = "log", name = "log_float")
    public static native void log(float aValue);

    @JSBody(params = "aValue", script = "console.print(aValue);")
    @Import(module = "log", name = "log_double")
    public static native void log(double aValue);

    @JSBody(params = "aValue", script = "console.print(aValue);")
    @Import(module = "log", name = "log_string")
    public static native void log(String aValue);

    static class Test {
        private final int value;

        public Test(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int i = 12;
        int j = 13;
        int k = i + j;
        Test test = new Test(11);
        log(k);
        log(10.344f);
        log(10.344d);
        log("Hello");
        log("Sir");
        log("DuD");
        log(test.value);
    }
}
