package tech.dodd.fruitmvvm;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey
    @NonNull
    private final String item;
    private final Integer amount;
    private final Integer value;

    public Note(String item, Integer amount, Integer value) {
        this.item = item;
        this.amount = amount;
        this.value = value;
    }

    String getItem() {
        return item;
    }

    Integer getAmount() {
        return amount;
    }

    Integer getValue() {
        return value;
    }
}