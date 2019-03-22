package ru.softmg.plugin.providers;

import com.intellij.ide.projectView.TreeStructureProvider;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.NotNull;
import ru.softmg.plugin.models.MarkedFile;
import ru.softmg.plugin.repositories.MarkedFilesRepository;
import ru.softmg.plugin.repositories.MarkedFilesRepositoryImpl;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MarkedFileTreeStructureProvider implements TreeStructureProvider {
    @NotNull
    @Override
    public Collection<AbstractTreeNode> modify(@NotNull AbstractTreeNode parent, @NotNull Collection<AbstractTreeNode> child, ViewSettings viewSettings) {
//        List<AbstractTreeNode> newNodes = new ArrayList<>();
//        child.forEach(abstractTreeNode -> {
//            if(abstractTreeNode instanceof PsiFileNode) {
//                MarkedFile markedFile = new MarkedFile(ProjectManager.getInstance().getOpenProjects()[0],
//                        ((PsiFileNode)abstractTreeNode).getValue(),
//                        ((PsiFileNode)abstractTreeNode).getSettings(),
//                        Color.BLUE
//                );
//                newNodes.add(markedFile);
//            } else
//                newNodes.add(abstractTreeNode);
//        });
//        return newNodes;
        List<MarkedFile> markedFiles = ((MarkedFilesRepositoryImpl)MarkedFilesRepositoryImpl.getInstance()).getNodes();
        return child;
    }
}
