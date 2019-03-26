package ru.softmg.plugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileVisitor;
import org.jetbrains.annotations.NotNull;
import ru.softmg.plugin.models.MarkedFile;
import ru.softmg.plugin.repositories.MarkedFilesRepositoryImpl;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileMarker extends AnAction {
    private static Map<String, Color> idToColorMap = new HashMap<>();

    static {
        idToColorMap.put("n1Marker", Color.decode("#f0d392"));
        idToColorMap.put("n2Marker", Color.decode("#c7f092"));
        idToColorMap.put("n3Marker", Color.decode("#92c9f0"));
        idToColorMap.put("n4Marker", Color.decode("#bb92f0"));
        idToColorMap.put("n5Marker", Color.decode("#f092a0"));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        String actionId = anActionEvent.getActionManager().getId(this);
        Color targetColor = idToColorMap.get(actionId);
        Color transparent = new Color(targetColor.getRed(), targetColor.getGreen(), targetColor.getBlue(), 50);

        final VirtualFile[] virtualFiles = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(anActionEvent.getDataContext());
        if(virtualFiles != null) {
            MarkedFilesRepositoryImpl markedFilesRepository = ((MarkedFilesRepositoryImpl)MarkedFilesRepositoryImpl.getInstance());
            Arrays.asList(virtualFiles).forEach(virtualFile -> {
                VfsUtil.visitChildrenRecursively(virtualFile, new VirtualFileVisitor<Object>() {
                    @Override
                    public boolean visitFile(@NotNull VirtualFile file) {
                        markedFilesRepository.addNode(new MarkedFile(file, transparent));
                        return true;
                    }
                });
            });
        }
    }
}
