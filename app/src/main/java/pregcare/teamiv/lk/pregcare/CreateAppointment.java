package pregcare.teamiv.lk.pregcare;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class CreateAppointment extends AppCompatActivity {


    private EditText txtTitle, txtTime, txtDetails;
    static String cdate;
    public boolean check = true;
    public Button save;
    private String lang = "en_US";
    public static String word, text;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment2);


        save = (Button) findViewById(R.id.btnsave);
        txtTitle = (EditText) findViewById(R.id.txttitle);
        txtTime = (EditText) findViewById(R.id.txttime);
        txtDetails = (EditText) findViewById(R.id.txtdetails);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            cdate = getIntent().getStringExtra("cdate");
        }
        final DataBase databaseHelper = DataBase.getInstance(this);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                List<Appo> appos = databaseHelper.titles();
                for (Appo app : appos) {
                    if (app.getDay().equals(cdate) && app.getTitle().equals(txtTitle.getText().toString())) {
                        check = false;
                    } else {
                        check = true;
                    }
                }
                if (check) {
                    Appo.title = txtTitle.getText().toString();
                    Appo.time = txtTime.getText().toString();
                    Appo.details = txtDetails.getText().toString();
                    Appo.day = cdate;

                    databaseHelper.addAppo();
                    txtTitle.setText("");
                    txtTime.setText("");
                    txtDetails.setText("");
                } else {
                    new AlertDialog.Builder(CreateAppointment.this)
                            .setTitle("ERROR")
                            .setMessage("Appointment " + txtTitle.getText().toString() + " already exists, please choose a diﬀerent event title ")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }
        });


    }

}