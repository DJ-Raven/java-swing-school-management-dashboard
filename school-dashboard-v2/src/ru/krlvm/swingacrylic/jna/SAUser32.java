package ru.krlvm.swingacrylic.jna;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public interface SAUser32 extends StdCallLibrary {

    SAUser32 INSTANCE = Native.load("user32", SAUser32.class, W32APIOptions.DEFAULT_OPTIONS);

    boolean SetWindowCompositionAttribute(int hwnd, Pointer data);
    boolean SetWindowCompositionAttribute(WinDef.HWND hwnd, Pointer data);
}