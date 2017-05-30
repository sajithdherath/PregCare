package pregcare.teamiv.lk.pregcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Appointment extends AppCompatActivity {
    private Button btncreate,btnviewedit,btndelete,btnsearch,btnmove;
    private CalendarView calendarView;
    static String curDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        btncreate = (Button) findViewById(R.id.btncreate);
        btnviewedit =(Button)findViewById(R.id.btnviewedit);
        btndelete = (Button) findViewById(R.id.btndelete);
        calendarView = (CalendarView) findViewById(R.id.CalendarView);
        btnsearch = (Button)findViewById(R.id.btnsearch);
        btnmove = (Button)findViewById(R.id.btnmove);

        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");

        curDate = sdf.format(date.getTime());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,int dayOfMonth) {
                int d = dayOfMonth;
                int m = month+1;
                int y = year;
                curDate =String.valueOf(d)+"-"+String.valueOf(m)+"-"+String.valueOf(y);
            }
        });

        btncreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(Appointment.this, CreateAppointment.class);
                intent.putExtra("cdate",curDate);
                startActivity(intent);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(Appointment.this, DeleteAppointment.class);
                intent.putExtra("cdate",curDate);
                startActivity(intent);
            }
        });

        btnviewedit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(Appointment.this, ViewAndEdit.class);
                intent.putExtra("cdate",curDate);
                startActivity(intent);
            }
        });
        btnsearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(Appointment.this, SearchAppointment.class);
                startActivity(intent);
            }
        });
        btnmove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(Appointment.this, MoveAppointment.class);
                intent.putExtra("cdate",curDate);
                startActivity(intent);
            }
        });
    }

}
