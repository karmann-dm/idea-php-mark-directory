package ru.softmg.plugin.models;

import com.intellij.openapi.vfs.VirtualFile;

import java.awt.*;

public class MarkedFile {
    private VirtualFile virtualFile;
    private Color color;

    public MarkedFile(VirtualFile virtualFile, Color color) {
        this.virtualFile = virtualFile;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public VirtualFile getVirtualFile() {
        return virtualFile;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof VirtualFile) {
            return obj.equals(virtualFile);
        }
        if(obj instanceof MarkedFile) {
            return ((MarkedFile)obj).getVirtualFile().equals(virtualFile);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return virtualFile.hashCode();
    }
}
