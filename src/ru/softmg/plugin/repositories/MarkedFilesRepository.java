package ru.softmg.plugin.repositories;

import ru.softmg.plugin.models.MarkedFile;

import java.util.List;

public interface MarkedFilesRepository {
    List<MarkedFile> getNodes();
    void addNode(MarkedFile node);
    boolean containsNode(MarkedFile node);
    void removeNode(MarkedFile node);
}
