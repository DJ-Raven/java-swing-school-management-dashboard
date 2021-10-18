package ru.krlvm.swingacrylic.jna;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class WindowCompositionAttributeData extends Structure implements Structure.ByReference {

    public int Attribute;
    public Pointer Data;
    public int SizeOfData;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(
                "Attribute",
                "Data",
                "SizeOfData");
    }
}