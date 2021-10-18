package ru.krlvm.swingacrylic;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT;

public class Utility {

    /**
     * Windows 10 April 2018 Update (1803, build 17134) required to run
     */
    protected static boolean isSupported() {
        if(!System.getProperty("os.name").equals("Windows 10")) {
            return false;
        }
        Kernel32 kernel = Kernel32.INSTANCE;
        WinNT.OSVERSIONINFOEX vex = new WinNT.OSVERSIONINFOEX();
        if (kernel.GetVersionEx(vex)) {
            try {
                int build = Integer.parseInt(vex.dwBuildNumber.toString());
                return build >= SwingAcrylic.MIN_BUILD;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return false;
    }
}