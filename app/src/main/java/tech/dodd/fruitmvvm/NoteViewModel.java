package tech.dodd.fruitmvvm;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {
    private final NoteRepository repository;
    private final LiveData<List<Note>> allNotesItemSortedASC, allNotesAmountSortedDESC, allNotesAmountSortedASC;
    private final LiveData<Integer> allNotesCount;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotesItemSortedASC = repository.getAllNotesItemSortedASC();
        allNotesAmountSortedDESC = repository.getAllNotesAmountSortedDESC();
        allNotesAmountSortedASC = repository.getAllNotesAmountSortedASC();
        allNotesCount = repository.getCount();
    }

    void insert(Note note) {
        repository.insert(note);
    }

    void update(Note note) {
        repository.update(note);
    }

    void delete(Note note) {
        repository.delete(note);
    }

    void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    LiveData<List<Note>> getAllNotesItemSortedASC() {
        return allNotesItemSortedASC;
    }

    LiveData<List<Note>> getAllNotesAmountSortedDESC() {
        return allNotesAmountSortedDESC;
    }

    LiveData<List<Note>> getAllNotesAmountSortedASC() {
        return allNotesAmountSortedASC;
    }

    LiveData<Integer> getCount() {
        return allNotesCount;
    }

    void getNoteAmountwithItem(String item, MainActivity.NoteAmountListener listener) {
        repository.getNoteAmountwithItem(item, listener);
    }
}