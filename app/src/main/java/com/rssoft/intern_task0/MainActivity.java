package com.rssoft.intern_task0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText regd = findViewById(R.id.reg_input);
        Button btn = findViewById(R.id.btn);
        final TextView name = findViewById(R.id.name_view);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (regd.getText().toString().isEmpty()) {
                    name.setText("");
                    return;
                }
                if (regd.getText().toString().equals("1801106398")) name.setText("Rohnak Agarwal");
                else name.setText("Not a Valid Registration Number!");
            }
        });

    }
}
