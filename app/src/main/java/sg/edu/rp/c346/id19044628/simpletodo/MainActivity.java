package sg.edu.rp.c346.id19044628.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edTask;
    Button btnAdd, btnDel, btnClear;
    ListView lvtask;
    Spinner spnTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDel=findViewById(R.id.btnDel);
        btnClear = findViewById(R.id.buttonClear);
        lvtask = findViewById(R.id.lvTask);
        spnTask = findViewById(R.id.spnTask);

        ArrayList<String> alTask;
        alTask = new ArrayList<String>();

        ArrayAdapter aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);
        lvtask.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = edTask.getText().toString();
                if (!task.isEmpty()) {
                    alTask.add(task);
                    edTask.setText("");
                    aaTask.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a task!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=Integer.parseInt(edTask.getText().toString());

                if (pos > alTask.size() || pos<alTask.size())
                {
                    Toast.makeText(getBaseContext(),"Wrong index number",Toast.LENGTH_SHORT).show();
                    edTask.setText("");
                }else {
                    alTask.remove(pos);
                    edTask.setText("");
                    aaTask.notifyDataSetChanged();
                }


            }

        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (alTask.size() > 0) {
                    alTask.clear();
                    aaTask.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "There is no task to clear!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spnTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        edTask.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnDel.setEnabled(true);
                        btnAdd.setEnabled(false);
                        if (alTask.size()==0)
                        {
                            Toast.makeText(MainActivity.this, "You don't have any task to remove!", Toast.LENGTH_SHORT).show();
                        }
                        edTask.setHint("Type in the index of the task to be removed");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}