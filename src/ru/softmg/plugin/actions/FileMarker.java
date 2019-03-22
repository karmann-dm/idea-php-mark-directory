package ru.softmg.plugin.actions;

import com.intellij.ide.favoritesTreeView.FavoritesViewSettings;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.AbstractProjectViewPane;
import com.intellij.ide.projectView.impl.ProjectViewPane;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileSystemItem;
import org.jetbrains.annotations.NotNull;
import ru.softmg.plugin.models.MarkedFile;
import ru.softmg.plugin.repositories.MarkedFilesRepositoryImpl;

import java.awt.*;
import java.util.Arrays;

public class FileMarker extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        final Project currentProject = DataKeys.PROJECT.getData(anActionEvent.getDataContext());
        AbstractProjectViewPane currentProjectViewPane = ProjectView.getInstance(currentProject).getCurrentProjectViewPane();
        final PsiElement[] psiElements =  DataKeys.PSI_ELEMENT_ARRAY.getData(anActionEvent.getDataContext());
        if(psiElements != null) {
            Arrays.asList(psiElements).forEach(psiElement -> {
                if(psiElement instanceof PsiFile) {
                    ViewSettings viewSettings = new FavoritesViewSettings();
                    MarkedFile markedFile = new MarkedFile(currentProject, (PsiFile)psiElement, viewSettings, Color.BLUE);
                    MarkedFilesRepositoryImpl markedFilesRepository = ((MarkedFilesRepositoryImpl)MarkedFilesRepositoryImpl.getInstance());
                    markedFilesRepository.addNode(markedFile);

                    PsiFileSystemItem psiFileSystemItem = (PsiFileSystemItem)psiElement;
                    currentProjectViewPane.updateFrom(psiFileSystemItem, false, true);
                }
            });
        }
    }
}
