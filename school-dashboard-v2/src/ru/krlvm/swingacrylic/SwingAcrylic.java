package ru.krlvm.swingacrylic;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import java.awt.*;
import javax.swing.*;
import ru.krlvm.swingacrylic.jna.*;

/**
 * SwingAcrylic
 *
 * Native Windows 10 acrylic effect for Java Swing Applications
 *
 * Supported Windows 10 1803 (April 2018 Update) and higher (until it was
 * removed by Microsoft)
 *
 * This is a port of an existing code to Java:
 * https://github.com/riverar/sample-win32-acrylicblur (C#)
 * https://ru.stackoverflow.com/a/858167 (Python)
 *
 * https://github.com/krlvm/SwingAcrylic
 *
 * @author krlvm
 */
public class SwingAcrylic {

    public static final int MIN_BUILD = 17134;
    public static boolean SUPPORTED = Utility.isSupported();

    public static void prepareSwing() {
        if (!SUPPORTED) {
            return;
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    public static void processFrame(JFrame frame) {
        if (!SUPPORTED) {
            return;
        }
        addTransparencyToBackground(frame);
        enableAcrylic(frame, 0, 0x990500);
    }

    /**
     * Automatically make window and its children background transparent and
     * apply native acrylic effect
     *
     * @param frame - frame
     * @param opacity - transparency opacity
     * @param background - blur background (BGR)
     */
    public static void processFrame(JFrame frame, int opacity, int background) {
        addTransparencyToBackground(frame);
        enableAcrylic(frame, opacity, background);
    }

    /**
     * Manually enable native acrylic on window, transparent background is
     * required
     *
     * @param frame - frame
     */
    public static void enableAcrylic(JFrame frame, int opacity, int background) {
        //long hwnd = ((WComponentPeer)frame.getPeer()).getHWnd();
        WinDef.HWND hwnd = new WinDef.HWND(Native.getWindowPointer(frame));

        AccentPolicy policy = new AccentPolicy();
        policy.AccentState = AccentState.ACCENT_ENABLE_BLURBEHIND;
        policy.GradientColor = (opacity << 24) | (background & 0xFFFFFF);
        policy.write();

        WindowCompositionAttributeData data = new WindowCompositionAttributeData();
        data.Attribute = WindowCompositionAttribute.WCA_ACCENT_POLICY;
        data.Data = policy.getPointer();
        data.SizeOfData = policy.size();
        data.write();

        boolean success = SAUser32.INSTANCE.SetWindowCompositionAttribute(hwnd, data.getPointer());
        if (!success) {
            print("Failed to set acrylic: native error %s", Native.getLastError());;
        }
    }

    /**
     * Automatically make component and its children background transparent,
     * then you have to call enableAcrylic method
     *
     * @param component - a Java Swing component
     */
    public static void addTransparencyToBackground(Component component) {
      component.setBackground(new Color(255, 255, 255, 1));
    }

    protected static void print(String s, Object... args) {
        System.out.println("[SwingAcrylic] " + String.format(s, args));
    }

    public static String getVersion() {
        return "1.0";
    }
}
