package ru.softmg.plugin.repositories;

import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import ru.softmg.plugin.models.MarkedFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MarkedFilesRepositoryImpl implements MarkedFilesRepository {
    private List<MarkedFile> markedFiles = new ArrayList<>();
    private static MarkedFilesRepository repository;

    @Override
    public List<MarkedFile> getNodes() {
        return markedFiles;
    }

    @Override
    public Stream<? extends PsiFileNode> getNodesStream() {
        return markedFiles.stream();
    }

    public void addNode(MarkedFile node) {
        markedFiles.add(node);
    }

    public static MarkedFilesRepository getInstance() {
        if(repository != null)
            return repository;
        else
            return new MarkedFilesRepositoryImpl();
    }
}
