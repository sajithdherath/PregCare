package pregcare.teamiv.lk.pregcare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class SearchAppointment extends AppCompatActivity {
    private EditText search;
    private TextView searchAppo, SearchDetails;
    private Button btnsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_appointment);

        search =(EditText) findViewById(R.id.search);
        searchAppo= (TextView) findViewById(R.id.listappoSearch);
        SearchDetails = (TextView) findViewById(R.id.SearchAppo);
        btnsearch = (Button) findViewById(R.id.btnSearchAppo);

        final DataBase databaseHelper = DataBase.getInstance(this);

        btnsearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Log.d("Reading:","Reading all Appointments...");
                List<Appo> appos = databaseHelper.getAllData();
                for (Appo app : appos) {
                    String title = app.getTitle()+" "+app.getDetails();
                    boolean containsT = title.matches(".*\\b"+search.getText().toString()+"\\b.*");
                    if (containsT) {
                        searchAppo.setText(app.getTitle());
                   }else {
                       searchAppo.setText("No match found !!!");
                    }
                }
            }
        });

        searchAppo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });


    }
}
