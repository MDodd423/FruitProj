package tech.dodd.fruitmvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button fruitButton, bagButton;
    TextView fruitTextView;
    NoteViewModel noteViewModel;
    Integer randomfruit, updateamount, value;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fruitTextView = findViewById(R.id.fruitTextView);
        fruitButton = findViewById(R.id.fruitButton);
        bagButton = findViewById(R.id.bagButton);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        noteViewModel.getCount().observe(this, integer ->
                bagButton.setText(getResources().getString(R.string.string_BagTotal, integer))
        );
    }

    public void doButtonClick(View v) {
        int id = v.getId();
        if (id == R.id.fruitButton) {
            doGiveFruit();
        } else if (id == R.id.bagButton) {
            startActivity(new Intent(this, BagActivity.class));
        }
    }

    public interface NoteAmountListener {
        void getAmount(Integer amount);
    }

    public interface NoteAmountandValueListener {
        void getAmountandValue(Integer amount, Integer value);
    }

    public void doGiveFruit() {
        randomfruit = new Random().nextInt(3) + 1; // [0, 2] + 1 => [1, 3]
        switch (randomfruit){
            case 1:
                item = "Apple";
                value = 2;
                break;
            case 2:
                item = "Banana";
                value = 4;
                break;
            case 3:
                item = "Orange";
                value = 3;
                break;
        }

        fruitTextView.setText(getResources().getString(R.string.string_FruitPicked, item));

        noteViewModel.getNoteAmountwithItem(item, queryAmount -> {
            if (!(queryAmount == null)) {
                updateamount = (queryAmount + 1);
            } else {
                updateamount = 1;
            }
            Note note = new Note(item, updateamount, value);
            noteViewModel.insert(note);
        });

        noteViewModel.getCount().observe(this, integer -> {
            bagButton.setText(getResources().getString(R.string.string_BagTotal, integer));
        });
    }
}