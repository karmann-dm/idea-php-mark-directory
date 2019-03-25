package ru.softmg.plugin.providers;

import com.intellij.openapi.fileEditor.impl.EditorTabColorProvider;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.softmg.plugin.models.MarkedFile;
import ru.softmg.plugin.repositories.MarkedFilesRepositoryImpl;

import java.awt.*;
import java.util.List;

public class MarkedFileEditorTabColorProvider implements EditorTabColorProvider {
    private Color getColorFromRepository(VirtualFile file) {
        List<MarkedFile> markedFiles = MarkedFilesRepositoryImpl.getInstance().getNodes();
        for(MarkedFile markedFile : markedFiles) {
            if(markedFile.equals(file)) {
                return markedFile.getColor();
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Color getEditorTabColor(@NotNull Project project, @NotNull VirtualFile file) {
        return getColorFromRepository(file);
    }

    @Nullable
    @Override
    public Color getEditorTabColor(@NotNull Project project, @NotNull VirtualFile file, @Nullable EditorWindow editorWindow) {
        return getColorFromRepository(file);
    }

    @Nullable
    @Override
    public Color getProjectViewColor(@NotNull Project project, @NotNull VirtualFile file) {
        return getColorFromRepository(file);
    }

}
