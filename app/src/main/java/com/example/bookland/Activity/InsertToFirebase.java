package com.example.bookland.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookland.R;
import com.example.bookland.Recycler.RecyclerViewAdapter;
import com.example.bookland.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertToFirebase extends AppCompatActivity {

    EditText rname, rsurname,rage;
    Button add, delete;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String name;
    String surname;
    String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_to_firebase);

        rname = findViewById(R.id.name_id);
        rsurname = findViewById(R.id.surname_id);
        rage = findViewById(R.id.age);
        add = findViewById(R.id.add_btn);
        delete = findViewById(R.id.delete_btn);
        rootNode = FirebaseDatabase.getInstance();




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = rootNode.getReference("Book_Saved");
                name = rname.getText().toString();
                surname = rsurname.getText().toString();
                age = rage.getText().toString();
                //User user = new User(name, surname, age);
                //reference.child(name).setValue(user);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reference = rootNode.getReference("Book_Saved");
                //reference.child(rname.getText().toString()).removeValue();

                User user = new User();
                reference = FirebaseDatabase.getInstance().getReference("Book_Saved").child(rname.getText().toString());
                reference.removeValue();
            }
        });
    }

}