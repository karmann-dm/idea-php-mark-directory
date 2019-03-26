package ru.softmg.plugin.repositories;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.softmg.plugin.models.MarkedFile;

import java.util.ArrayList;
import java.util.List;

@State(name = "markedFiles.xml")
public class MarkedFilesRepositoryImpl implements MarkedFilesRepository, PersistentStateComponent<MarkedFilesRepositoryImpl.State> {
    private static MarkedFilesRepository repository = null;
    private State state = new State();

    static class State {
        List<MarkedFile> markedFiles = new ArrayList<>();
    }

    @Nullable
    @Override
    public State getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull State state) {
        this.state = state;
    }

    @Override
    public List<MarkedFile> getNodes() {
        return state.markedFiles;
    }

    @Override
    public void addNode(MarkedFile node) {
        if(!containsNode(node))
            state.markedFiles.add(node);
        else {
            int index = state.markedFiles.indexOf(node);
            MarkedFile existing = state.markedFiles.get(index);
            existing.setColor(node.getColor());
        }
    }

    @Override
    public boolean containsNode(MarkedFile node) {
        return state.markedFiles.contains(node);
    }

    @Override
    public void removeNode(MarkedFile node) {
        state.markedFiles.remove(node);
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
