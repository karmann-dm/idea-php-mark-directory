package ru.softmg.plugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileVisitor;
import org.jetbrains.annotations.NotNull;
import ru.softmg.plugin.models.MarkedFile;
import ru.softmg.plugin.repositories.MarkedFilesRepositoryImpl;

import java.util.Arrays;

public class FileUnmarker extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        final VirtualFile[] virtualFiles = DataKeys.VIRTUAL_FILE_ARRAY.getData(anActionEvent.getDataContext());
        if(virtualFiles != null) {
            MarkedFilesRepositoryImpl markedFilesRepository = ((MarkedFilesRepositoryImpl)MarkedFilesRepositoryImpl.getInstance());
            Arrays.asList(virtualFiles).forEach(virtualFile -> {
                VfsUtil.visitChildrenRecursively(virtualFile, new VirtualFileVisitor<Object>() {
                    @Override
                    public boolean visitFile(@NotNull VirtualFile file) {
                        MarkedFile markedFile = new MarkedFile(file, null);
                        markedFilesRepository.removeNode(markedFile);
                        return true;
                    }
                });
            });
        }
    }
}
