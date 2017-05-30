package pregcare.teamiv.lk.pregcare;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class DeleteAppointment extends AppCompatActivity {

    private Button deleteAll,selectToDelete;
    private TextView listOfTitles;
    private EditText num;
    static String cdate, messasge ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_appointment);

        deleteAll =(Button)findViewById(R.id.btndeleteall);
        selectToDelete = (Button)findViewById(R.id.btndeleteselect);
        listOfTitles = (TextView)findViewById(R.id.txtTitleList);
        num = (EditText)findViewById(R.id.txtdeletenum);

        final DataBase db = DataBase.getInstance(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cdate = getIntent().getStringExtra("cdate");
        }

        List<Appo> appos = db.getAllData();
        for (Appo app : appos) {
            if (app.getDay().equals(cdate)) {
                String text = app.getId() + ". " + app.getTime() + " - " + app.getTitle();
                listOfTitles.setText(text);
            }
        }

        deleteAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db.deleteAllAppo(new Appo(cdate));
            }
        });

        selectToDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                List<Appo> appos = db.getAllData();
                for (Appo app : appos) {
                    if (app.getId()==Integer.valueOf(num.getText().toString())) {
                        messasge = "Would you like to delete event: "+app.getTitle()+"?";
                    }else {
                        messasge="No Event number: "+num+"!!!";
                    }
                }

                new AlertDialog.Builder(DeleteAppointment.this)
                        .setTitle("Alert")
                        .setMessage(messasge)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteAnAppo(new Appo(Integer.valueOf(num.getText().toString())));
                                num.setText("");
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
}
