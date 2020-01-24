package com.example.namit.adddata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextPetName;
    private Spinner breedNames;
    private Button AddPet;
    DatabaseReference databasePets;
    private ListView listView;

    List<Pets> petsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasePets= FirebaseDatabase.getInstance().getReference("pets");

        editTextPetName= (EditText) findViewById(R.id.editTextPetName);
        breedNames= (Spinner) findViewById(R.id.breedsName);
        AddPet= (Button) findViewById(R.id.addData);
        listView= (ListView) findViewById(R.id.listView);

        petsList=new ArrayList<>();

        AddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPet();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databasePets.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)         //This method will be called everytime we perform any change in the database
            {
                petsList.clear();
                for (DataSnapshot petsSnapShot : dataSnapshot.getChildren())
                {
                    Pets pets=petsSnapShot.getValue(Pets.class);
                    petsList.add(pets);
                }

                PetsList adapter=new PetsList(MainActivity.this,petsList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    private void addPet()
    {
        String name=editTextPetName.getText().toString().trim();
        String type=breedNames.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name))
        {
            String id=databasePets.push().getKey();           // push() will create a unique string inside "pets" & getKey() would get that unique string
            Pets pets=new Pets(id,name,type);
            databasePets.child(id).setValue(pets);          //sets data within uniquely generated id,which is child od "pets"

            Toast.makeText(this,"Pet Added!!!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Name can not be empty",Toast.LENGTH_SHORT).show();
        }
    }
}
