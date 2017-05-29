package pregcare.teamiv.lk.pregcare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class ProfileSplash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_splash);

        Handler myHandler = new Handler();
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ProfileSplash.this, Profile.class));
                finish();
            }
        }, 3800);
    }

    }
