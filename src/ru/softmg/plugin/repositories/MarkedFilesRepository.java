package ru.softmg.plugin.repositories;

import com.intellij.ide.projectView.impl.nodes.PsiFileNode;

import java.util.List;
import java.util.stream.Stream;

public interface MarkedFilesRepository {
    List<? extends PsiFileNode> getNodes();
    Stream<? extends PsiFileNode> getNodesStream();
}
