package tech.dodd.fruitmvvm;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

class NoteRepository {
    private final NoteDao noteDao;
    private final LiveData<List<Note>> allNotesItemSortedASC, allNotesAmountSortedDESC, allNotesAmountSortedASC;
    private final LiveData<Integer> allNotesCount, NoteAmount;

    NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotesItemSortedASC = noteDao.getAllNotesItemSortedASC();
        allNotesAmountSortedDESC = noteDao.getAllNotesAmountSortedDESC();
        allNotesAmountSortedASC = noteDao.getAllNotesAmountSortedASC();
        allNotesCount = noteDao.getCount();
        NoteAmount = noteDao.getAmount();
    }

    void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
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

    LiveData<Integer> getAmount() {
        return NoteAmount;
    }

    LiveData<Integer> getNoteAmountwithItem(String item) {
        return noteDao.getAmountwithItem(item);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private final NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private final NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private final NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private final NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}