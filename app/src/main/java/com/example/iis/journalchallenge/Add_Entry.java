package com.example.iis.journalchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Add_Entry extends AppCompatActivity implements View.OnClickListener{

    EditText entryText, titleText;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_entry);

        entryText = findViewById(R.id.editEntry);
        titleText = findViewById(R.id.editTitle);
        save = findViewById(R.id.btnSave);

        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String date = df.format(Calendar.getInstance().getTime());

                SQLiteClass sqLiteClass = new SQLiteClass(this);
                sqLiteClass.open();
                sqLiteClass.write(date, titleText.getText().toString(), entryText.getText().toString());
                sqLiteClass.close();
                //Toast.makeText(this, "Entry Added", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
