package pregcare.teamiv.lk.pregcare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MoveAppoCalendar extends AppCompatActivity {

    private Button btnmove;
    private CalendarView calendarView;
    public static String curDate, num, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_appo_calendar);

        btnmove =(Button)findViewById(R.id.btnmove);
        calendarView =(CalendarView)findViewById(R.id.Calendar);
        final DataBase databaseHelper = DataBase.getInstance(this);

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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            num = getIntent().getStringExtra("num");
        }

        btnmove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                databaseHelper.updateDate(new Appo(Integer.valueOf(num),curDate));
            }
        });
    }

}
