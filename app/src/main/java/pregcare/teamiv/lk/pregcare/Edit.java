package pregcare.teamiv.lk.pregcare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit extends AppCompatActivity {

    private Button save;
    private EditText txtTitle,txtTime,txtDetails;
    public static String  num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final DataBase databaseHelper = DataBase.getInstance(this);

        save = (Button) findViewById(R.id.btnsave);
        txtTitle = (EditText) findViewById(R.id.txttitle);
        txtTime = (EditText) findViewById(R.id.txttime);
        txtDetails = (EditText) findViewById(R.id.txtdetails);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            num = getIntent().getStringExtra("num");
        }

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                databaseHelper.update(new Appo(Integer.valueOf(num),txtTitle.getText().toString(),txtTime.getText().toString(),txtDetails.getText().toString()));
                txtTitle.setText("");
                txtTime.setText("");
                txtDetails.setText("");

            }
        });
    }
}
