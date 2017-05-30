package pregcare.teamiv.lk.pregcare;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class CreateAppointment extends AppCompatActivity {

    private Button save,btnThesaurus,btnHighlighted;
    private EditText txtTitle,txtTime,txtDetails,thesaurus;
    static String cdate;
    public boolean check=true;
    public static final String THESAURUS_KEY = "8CK1lIeHHcbHMO234Xcm ";
    private String lang = "en_US";
    public static String  word, text;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment2);


        save = (Button) findViewById(R.id.btnsave);
        btnThesaurus = (Button) findViewById(R.id.btnThe);
        btnHighlighted = (Button) findViewById(R.id.btnTheDetails);
        txtTitle = (EditText) findViewById(R.id.txttitle);
        txtTime = (EditText) findViewById(R.id.txttime);
        txtDetails = (EditText) findViewById(R.id.txtdetails);
        thesaurus = (EditText) findViewById(R.id.txtThe);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            cdate = getIntent().getStringExtra("cdate");
        }
        final DataBase databaseHelper = DataBase.getInstance(this);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    List<Appo> appos = databaseHelper.titles();
                    for (Appo app : appos) {
                        if (app.getDay().equals(cdate)&& app.getTitle().equals(txtTitle.getText().toString())) {
                            check=false;
                        }else {
                           check=true;
                        }
                    }
                if (check){
                    Appo.title = txtTitle.getText().toString();
                    Appo.time = txtTime.getText().toString();
                    Appo.details = txtDetails.getText().toString();
                    Appo.day = cdate;

                    databaseHelper.addAppo();
                    txtTitle.setText("");
                    txtTime.setText("");
                    txtDetails.setText("");
                }else {
                    new AlertDialog.Builder(CreateAppointment.this)
                            .setTitle("ERROR")
                            .setMessage("Appointment " +txtTitle.getText().toString()+" already exists, please choose a diﬀerent event title ")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }
        });

        btnThesaurus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 word= thesaurus.getText().toString();
                Download download = new Download();
                download.execute();
            }
        });

        }

    private class Download extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            //download the file
            try {
                DownloadFromUrl("http://thesaurus.altervista.org/thesaurus/v1?word=" + word +
                                "&language=" + lang + "&%20key=" + THESAURUS_KEY + "&output=xml",
                        openFileOutput("synonyms.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            ThesaurusXML TX = new ThesaurusXML();

            List<Synonym> syno = TX.getSynonymsFromFile(CreateAppointment.this);
            for (Synonym word : syno) {
                     text = word.getCategory() + ". " + word.getSynonyms();


            }

            new AlertDialog.Builder(CreateAppointment.this)
                    .setTitle("ERROR")
                    .setMessage(text)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }

        public static void DownloadFromUrl(String URL, FileOutputStream fos) {
            try {

                java.net.URL url = new URL(URL); //URL of the file

			/* Open a connection to that URL. */
                URLConnection connection = url.openConnection();


                //input stream that'll read from the connection
                InputStream is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                //buffer output stream that'll write to the xml file
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                //write to the file while reading
                byte data[] = new byte[1024];
                int count;
                //loop and read the current chunk
                while ((count = bis.read(data)) != -1) {
                    //write this chunk
                    bos.write(data, 0, count);
                }

                bos.flush();
                bos.close();

            } catch (IOException e) {
            }
        }


}


