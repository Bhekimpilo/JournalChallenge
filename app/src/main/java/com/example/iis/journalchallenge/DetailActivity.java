package com.example.iis.journalchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView createdAt, entry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        createdAt = findViewById(R.id.creation);
        entry = findViewById(R.id.showContent);

        createdAt.setText("Created at: " + getIntent().getStringExtra("date") + "\n" + getIntent().getStringExtra("title"));
        entry.setText(getIntent().getStringExtra("entry"));

    }


}
