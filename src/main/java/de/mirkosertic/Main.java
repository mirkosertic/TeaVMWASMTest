package de.mirkosertic;

import org.teavm.interop.Import;

public class Main {

    @Import(module = "log", name = "log_string")
    public static native void log(String aValue);

    public static abstract class PositionAnchor {

        public static final PositionAnchor SCENE = new PositionAnchor("SCENE") {
        };

        public static final PositionAnchor CENTER = new PositionAnchor("CENTER") {
        };

        public static final PositionAnchor TOP_RIGHT = new PositionAnchor("TOP_RIGHT") {
        };

        private final String name;

        private PositionAnchor(String aName) {
            name = aName;
        }

        public String name() {
            return name;
        }

        public static PositionAnchor valueOf(String aName) {
            switch (aName) {
            case "TOP_RIGHT":
                return TOP_RIGHT;
            case "SCENE":
                return SCENE;
            case "CENTER":
                return CENTER;
            }
            throw new IllegalArgumentException(aName);
        }
    }

    public static void main(String[] args) {
        log("Start");
        PositionAnchor a1 = PositionAnchor.valueOf("CENTER");
        log("Finished " + a1.name());
        PositionAnchor a2 = PositionAnchor.valueOf("SCENE");
        log("Finished " + a2.name());
    }
}
