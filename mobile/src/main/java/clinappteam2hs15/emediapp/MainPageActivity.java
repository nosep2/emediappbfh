package clinappteam2hs15.emediapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import simulationData.Medicationplan;

import static clinappteam2hs15.emediapp.R.drawable.mr_ic_play_light;

/*
* Activity der Startseite
*
* Dazu benötigt werden Informationen/Instruktionen in folgenden files:
* mobile Manifest:
*   aktuell keine Aenderungen zum Default
* Startseiten layout:
*   activity_main_page.xml (Grundsaetzlicher Aufbau: Collapsing Toolbar, Floating Actionbutton) und
*   content_main_page.xml (Scrollbarer Inhalt)
*   (plus values)
*
* @author: Corina von Kaenel
*
*/


public class MainPageActivity extends AppCompatActivity {
    public static final int NOTIFICATION_ID = 1;

    private Button mAddMediIntakeButton;
    private Medicationplan mMedicationplan;
    private TextView mMedicationView;
    private Button mMediRemNotificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Ueber diesen Button soll eine Medikamenteneinnahme protokolliert werden können
        mAddMediIntakeButton = (Button) findViewById(R.id.addMedIntake_button);
        mAddMediIntakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPageActivity.this, R.string.medieinnahmeErfasst_toast, Toast.LENGTH_SHORT).show();
            }
        });

        // Temporaer soll dieser Button eine mediNotifikation auslösen
        mMediRemNotificationButton = (Button) findViewById(R.id.mediIntakeReminder_button);
        mMediRemNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            onMedReminderButtonClick(v);
            }
        });

/*
ToDo: auf 2. Menuinhalt verschieben.
Ziel: Der Medikationsplan soll ausgelesen und dargestellt werden.
*/
        mMedicationplan = new Medicationplan();
        mMedicationView = (TextView) findViewById(R.id.Medication_textView);
        mMedicationView.setText((CharSequence) mMedicationplan.getMedication());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // (author cvk) fuer Notifikationserzeugung auch bei geschlossener App
    private PendingIntent getActivityPendingIntent() {
        Intent activityIntent = new Intent(this, MainPageActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this, 0, activityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
    // (author cvk) 1. Schritt der Notifikationserstellung auf Knopfdruck und mit Fixtext
    // ToDo: Notifikation aufgrund geplanter Uhrzeit starten und Daten für die Erinnerung auslesen
    public void onMedReminderButtonClick(View view) {

        PendingIntent medReminderPendingIntent = getActivityPendingIntent();

        Notification medicationNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Dafalgan 500mg")
                .setContentText("Einnahme für 6 Uhr geplant")
                .setSmallIcon(mr_ic_play_light)
                .setContentIntent(medReminderPendingIntent)
                .setPriority(Notification.PRIORITY_HIGH)
                // Visibility Private reduziert bei gelocktem Screen den Informationsgehalt der Notification
                .setVisibility(Notification.VISIBILITY_PRIVATE)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, medicationNotification);

    }
    public void onUpdateStandardNotificationButtonClick(View view) {

    }



}
