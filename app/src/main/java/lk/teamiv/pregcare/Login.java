package lk.teamiv.pregcare;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private TextView signup;
    private EditText editEmail;
    private EditText editPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        signup = (TextView)findViewById(R.id.Signup);
        editEmail = (EditText) findViewById(R.id.Email);
        editPassword = (EditText) findViewById(R.id.Password);

        buttonLogin.setOnClickListener(this);
        signup.setOnClickListener(this);

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileSplash.class));
        }
    }

    private void LoginUser() {
        String newEmail = editEmail.getText().toString().trim();
        String newPassword = editPassword.getText().toString().trim();

        if (TextUtils.isEmpty(newEmail)) {
            // name is empty
            Toast.makeText(this, "Please fill name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            // password is empty
            Toast.makeText(this, "Please fill password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Loading.....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(newEmail,newPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileSplash.class));
                        } else {
                            Toast.makeText(Login.this, "Could not login successfully, please try again", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == buttonLogin) {
            LoginUser();
        }
        if(v == signup){
            startActivity(new Intent(Login.this,CreateAccount.class));
        }
    }
}