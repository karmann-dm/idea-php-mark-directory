package ru.softmg.plugin.repositories;

import ru.softmg.plugin.models.MarkedFile;

import java.util.ArrayList;
import java.util.List;

public class MarkedFilesRepositoryImpl implements MarkedFilesRepository {
    private List<MarkedFile> markedFiles = new ArrayList<>();
    private static MarkedFilesRepository repository = null;

    @Override
    public List<MarkedFile> getNodes() {
        return markedFiles;
    }

    @Override
    public void addNode(MarkedFile node) {
        if(!containsNode(node))
            markedFiles.add(node);
        else {
            int index = markedFiles.indexOf(node);
            MarkedFile existing = markedFiles.get(index);
            existing.setColor(node.getColor());
        }
    }

    @Override
    public boolean containsNode(MarkedFile node) {
        return markedFiles.contains(node);
    }

    @Override
    public void removeNode(MarkedFile node) {
        markedFiles.remove(node);
    }

    public static MarkedFilesRepository getInstance() {
        if(repository != null)
            return repository;
        else {
            repository = new MarkedFilesRepositoryImpl();
            return repository;
        }
    }
}
