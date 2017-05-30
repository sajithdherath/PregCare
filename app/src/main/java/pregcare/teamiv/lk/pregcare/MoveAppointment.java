package pregcare.teamiv.lk.pregcare;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MoveAppointment extends AppCompatActivity {
    private TextView listMove;
    static String cdate;
    static String text;
    public static boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_appointment);

        listMove = (TextView)findViewById(R.id.listmove);
        final DataBase databaseHelper = DataBase.getInstance(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cdate = getIntent().getStringExtra("cdate");
        }

        List<Appo> appos = databaseHelper.getAllData();
        for (Appo app : appos) {
            if (app.getDay().equals(cdate)) {
                 text = app.getId() + ". " + app.getTime() + " - " + app.getTitle();
                //Log.d("Name:",log);
                listMove.setText(text);
                check=true;
            } else {
                check=false;
                listMove.setText("No Appointments today !!!");
            }
        }

        listMove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (check) {
                    final EditText input = new EditText(MoveAppointment.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    input.setLayoutParams(lp);
                    new AlertDialog.Builder(MoveAppointment.this)
                            .setTitle("Prompt")
                            .setMessage("Enter the number to move the Appointment:-")
                            .setView(input)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(MoveAppointment.this, MoveAppoCalendar.class);
                                    intent.putExtra("num",input.getText().toString());
                                    startActivity(intent);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });
    }
}
