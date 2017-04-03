package de.mirkosertic;

import org.teavm.interop.Import;
import org.teavm.jso.JSBody;

public class Main {

    @JSBody(params = "aValue", script = "console.print(aValue);")
    @Import(module = "log", name = "log_int")
    public static native void log(int aValue);

    @JSBody(params = "aValue", script = "console.print(aValue);")
    @Import(module = "log", name = "log_string")
    public static native void log(String aValue);

    public static void main(String[] args) {
        int i = 12;
        int j = 13;
        int k = i + j;
        log(k);
        log("Hello");
    }
}
