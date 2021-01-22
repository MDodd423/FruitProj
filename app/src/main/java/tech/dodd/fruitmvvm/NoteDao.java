package tech.dodd.fruitmvvm;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT item, value, amount FROM note_table ORDER BY item ASC")
    LiveData<List<Note>> getAllNotesItemSortedASC();

    @Query("SELECT item, value, amount FROM note_table ORDER BY amount DESC")
    LiveData<List<Note>> getAllNotesAmountSortedDESC();

    @Query("SELECT item, value, amount FROM note_table ORDER BY cast((amount) as int) ASC")
    LiveData<List<Note>> getAllNotesAmountSortedASC();

    @Query("SELECT COUNT(*) FROM note_table")
    LiveData<Integer> getCount();

    @Query("SELECT amount FROM note_table WHERE item LIKE :items")
    Integer getAmountwithItemWithListener(String items);
}