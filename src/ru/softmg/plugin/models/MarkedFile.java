package ru.softmg.plugin.models;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class MarkedFile extends PsiFileNode {
    private Color backgroundColor;

    public MarkedFile(Project project, @NotNull PsiFile value, ViewSettings viewSettings, Color backgroundColor) {
        super(project, value, viewSettings);
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @NotNull
    @Override
    protected PresentationData createPresentation() {
        PresentationData presentationData = super.createPresentation();
        presentationData.setForcedTextForeground(backgroundColor);
        return presentationData;
    }
}
