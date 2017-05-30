package pregcare.teamiv.lk.pregcare;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Information extends AppCompatActivity {

    public final String TAG = "Main";
    private Bluetooth bt;
    String message;
    TextView temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        temp= (TextView) findViewById(R.id.temp);
    }

    public void genVal(){
        TextView air = (TextView) findViewById(R.id.air);
        TextView ecg = (TextView) findViewById(R.id.ecg);
        //ecg.setText("Normal");
        TextView pulse = (TextView) findViewById(R.id.pulse);
        //pulse.setText("81");
        //temp = (TextView) findViewById(R.id.temp);
        //temp.setText(bt.getMsgTemp());
        bt = new Bluetooth(this,mHandler);
        connectService();

    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Bluetooth.MESSAGE_STATE_CHANGE:
                    Log.d(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    break;
                case Bluetooth.MESSAGE_WRITE:
                    Log.d(TAG, "MESSAGE_WRITE ");
                    break;
                case Bluetooth.MESSAGE_READ:
                    Log.d(TAG, "MESSAGE_READ ");
                    //Extract the string from the Message
                    message = (String) msg.obj;
                    temp.setText(message+"\u00b0"+"C");
                    break;
                case Bluetooth.MESSAGE_DEVICE_NAME:
                    Log.d(TAG, "MESSAGE_DEVICE_NAME "+msg);
                    break;
                case Bluetooth.MESSAGE_TOAST:
                    Log.d(TAG, "MESSAGE_TOAST "+msg);
                    break;
            }
        }
    };
    public void connectService(){
        try {
            //status.setText("Connecting...");
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter.isEnabled()) {
                bt.start();
                bt.connectDevice("HC-05");
                Log.d(TAG, "Btservice started - listening");
                //status.setText("Connected");

            } else {
                Log.w(TAG, "Btservice started - bluetooth is not enabled");
                //status.setText("Bluetooth Not enabled");
            }
        } catch(Exception e){
            Log.e(TAG, "Unable to start bt ",e);
            //status.setText("Unable to connect " +e);
        }
    }
}
