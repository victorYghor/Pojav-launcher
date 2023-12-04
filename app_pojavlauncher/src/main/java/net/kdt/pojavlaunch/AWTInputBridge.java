package net.kdt.pojavlaunch;

/**
 * the AWT(Abstract Window toolkit) is a way to integrate, native GUI with java. For learn more about,
 * read: <a href="https://en.wikipedia.org/wiki/Abstract_Window_Toolkit">AWT</a> download .jar in the
 * device.
 */
public class AWTInputBridge {
    /**
     * @see <a href="https://docs.oracle.com/en/java/">Java Dcoumentation</a>
     */
    public static final int EVENT_TYPE_CHAR = 1000;
    public static final int EVENT_TYPE_CURSOR_POS = 1003;
    public static final int EVENT_TYPE_KEY = 1005;
    public static final int EVENT_TYPE_MOUSE_BUTTON = 1006;
    
    public static void sendKey(char keychar, int keycode) {
        // TODO: Android -> AWT keycode mapping
        nativeSendData(EVENT_TYPE_KEY, (int) keychar, keycode, 1, 0);
        nativeSendData(EVENT_TYPE_KEY, (int) keychar, keycode, 0, 0);
    }

    public static void sendKey(char keychar, int keycode, int state) {
        // TODO: Android -> AWT keycode mapping
        nativeSendData(EVENT_TYPE_KEY, (int) keychar, keycode, state, 0);
    }

    public static void sendChar(char keychar){
        nativeSendData(EVENT_TYPE_CHAR, (int) keychar, 0, 0, 0);
    }
    
    public static void sendMousePress(int awtButtons, boolean isDown) {
        nativeSendData(EVENT_TYPE_MOUSE_BUTTON, awtButtons, isDown ? 1 : 0, 0, 0);
    }
    
    public static void sendMousePress(int awtButtons) {
        sendMousePress(awtButtons, true);
        sendMousePress(awtButtons, false);
    }
    
    public static void sendMousePos(int x, int y) {
        nativeSendData(EVENT_TYPE_CURSOR_POS, x, y, 0, 0);
    }
    
    static {
        System.loadLibrary("pojavexec_awt");
    }
    
    public static native void nativeSendData(int type, int i1, int i2, int i3, int i4);
    public static native void nativeClipboardReceived(String data, String mimeTypeSub);
    public static native void nativeMoveWindow(int xoff, int yoff);
}
