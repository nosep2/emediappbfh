package clinappteam2hs15.emediapp;
/*Manuel Pfister*/
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MedReminderActivity extends AppCompatActivity {


    ImageButton imageButtonEingenommen;
    ImageButton imageButtonNichtEingenommen;
    Button spaeterEingenommenbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
        imageButtonEingenommen = (ImageButton) findViewById(R.id.imageButton_Eingenommen);
        imageButtonEingenommen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hier sollte man vermerken, dass das Medidikament eingenommen wurde
                finish();
            }
        });
        imageButtonNichtEingenommen = (ImageButton) findViewById(R.id.imageButton_NichtEingenommen);
        imageButtonNichtEingenommen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hier sollte man vermerken, dass das Medidikament nicht eingenommen wurde
                finish();
            }
        });

        spaeterEingenommenbutton = (Button) findViewById(R.id.spaeter_Eingenommenbutton);
        spaeterEingenommenbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //hier wird ein Timer gestartet mit 60min, welcher die App erneut startet mit dieser
                // View wenn er auf 0 abgelaufen ist oder erneut eine Notifikation senden nach 60min
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onBarcodeClick(View view) {
        QrCode qr = new QrCode();
        Bitmap bmp = qr.generateQrcode();
        ImageView imgView = new ImageView(this);

        imgView.setImageBitmap(bmp);



    }
}
