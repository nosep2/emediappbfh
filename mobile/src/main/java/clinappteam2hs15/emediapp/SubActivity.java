package clinappteam2hs15.emediapp;
/*Manuel Pfister*/
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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
editor corina:
- Notifikationen für die Praesentation eingefügt:
1. Nofification für den Start der Pulsmessung
Da die Pulsmessung unzuverlässig ist, wird sie nicht automatisch gestartet, sondern es wird dem Benutzer eine Notifikation angezeigt,
dass dieser die Pulsmessung durchführen kann. Über die angezeigte Notifikation kann die Pulsmessung gestartet werden.

2. Notifikation für die Erinnerung der Medikamenteneinnahme.
Nur eine "statische" Notifikation umgesetzt, welche zu einer Einnahmebestaetigungsseite auf dem mobilen Device navigiert

- "AddMedication" eingefügt, da nicht vollständig umgesetzt.
 */

public class SubActivity extends AppCompatActivity {

    private Button mAddMediIntakeButton;
    private Button mMediRemNotificationButton;
    public static final int NOTIFICATION_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Ueber diesen Button soll eine Medikamenteneinnahme protokolliert werden können
        mAddMediIntakeButton = (Button) findViewById(R.id.addMedIntake_button);
        mAddMediIntakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SubActivity.this, R.string.medieinnahmeErfasst_toast, Toast.LENGTH_SHORT).show();
            }
        });

        /* Temporaer soll dieser Button eine mediNotifikation auslösen*/
        mMediRemNotificationButton = (Button) findViewById(R.id.mediIntakeReminder_button);
        mMediRemNotificationButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        //PendingIntent pIntent =PendingIntent.getActivity(MainOverviewTab.this,0,intent,0);*/
                        //)
                        onMedReminderButtonClick(v);
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
                .setSmallIcon(R.drawable.ic_2pill)
                .setContentTitle("Medikamteneinnahme 6 Uhr")
                .setContentText("mit dem Essen - 1 Tablette Dafalgan 500mg")
                .setContentIntent(medReminderPendingIntent)
                .setPriority(Notification.PRIORITY_HIGH)
                /* Visibility Private reduziert bei gelocktem Screen den Informationsgehalt der Notification
                .setVisibility(Notification.VISIBILITY_PRIVATE) */
                .setDefaults(Notification.DEFAULT_ALL)
                .setCategory(Notification.CATEGORY_STATUS)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, medicationNotification);

    }


    public void onUpdateStandardNotificationButtonClick(View view) {

    }

}
