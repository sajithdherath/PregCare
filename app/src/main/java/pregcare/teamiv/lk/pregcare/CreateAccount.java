package pregcare.teamiv.lk.pregcare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCreate;
    public EditText editUsername;
    private EditText editEmail;
    private EditText editPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonCreate = (Button) findViewById(R.id.buttonCreate);
        editUsername = (EditText) findViewById(R.id.Username);
        editEmail = (EditText) findViewById(R.id.Email);
        editPassword = (EditText) findViewById(R.id.Password);

        buttonCreate.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == buttonCreate) {
            creatAccount();
        }
    }

    public void creatAccount() {

        String username  = editUsername.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String passsword = editPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            // email is empty
            Toast.makeText(this,"Please fill your email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(username)){
            // username is empty
            Toast.makeText(this,"Please fill your username",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(passsword)){
            // password is empty
            Toast.makeText(this,"Please fill your Password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Creating account..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,passsword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult>task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileSplash.class));
                        }
                    }
                });

    }

}


