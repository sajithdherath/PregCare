package pregcare.teamiv.lk.pregcare;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ViewAndEdit extends AppCompatActivity {

    private TextView list;
    private Button btnview;
    static String cdate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_edit);

        list =(TextView) findViewById(R.id.listappo);
        btnview =(Button)findViewById(R.id.btnview);

        final DataBase databaseHelper = DataBase.getInstance(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cdate = getIntent().getStringExtra("cdate");
        }

        List<Appo> appos = databaseHelper.getAllData();
        for (Appo app : appos) {
            if (app.getDay().equals(cdate)) {
                String text = app.getId() + ". " + app.getTime() + " - " + app.getTitle();
                //Log.d("Name:",log);
                list.setText(text);
            }
        }

        btnview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText input = new EditText(ViewAndEdit.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                new AlertDialog.Builder(ViewAndEdit.this)
                        .setTitle("Prompt")
                        .setMessage("Enter the number to edit the Appointment:-")
                        .setView(input)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(ViewAndEdit.this, Edit.class);
                                intent.putExtra("num",input.getText().toString());
                                startActivity(intent);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });

    }
}
