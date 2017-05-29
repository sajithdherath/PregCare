package pregcare.teamiv.lk.pregcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Userdetails extends AppCompatActivity implements View.OnClickListener{


    private Button savebutton;
    private EditText editfname;
    private EditText editlname;
    private EditText editYourNumber;
    private EditText editage;
    private EditText editblood;
    private EditText editAllergies;
    private EditText editMobileNumber1;
    private EditText editMobileNumber2;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        savebutton = (Button) findViewById(R.id.SaveInformation);
        editfname = (EditText) findViewById(R.id.fname);
        editlname = (EditText) findViewById(R.id.lname);
        editage = (EditText)findViewById(R.id.age);
        editYourNumber =(EditText) findViewById(R.id.editMobile);
        editblood = (EditText) findViewById(R.id.blood);
        savebutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == savebutton){
            Savedetails();
            startActivity(new Intent(Userdetails.this,Profile.class));
        }

    }

    private void Savedetails(){
        String fname = editfname.getText().toString().trim();
        String lname = editlname.getText().toString().trim();
        String age = editage.getText().toString().trim();
        String yourmobile = editYourNumber.getText().toString().trim();
        String blood = editblood.getText().toString().trim();
        String allergies = editAllergies.getText().toString().trim();
        String mobile1 = editMobileNumber1.getText().toString().trim();
        String mobile2 = editMobileNumber2.getText().toString().trim();

        Save save = new Save(fname,lname,age,yourmobile,blood,allergies,mobile1,mobile2);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(save);

        Toast.makeText(this,"save information",Toast.LENGTH_SHORT).show();
    }

}