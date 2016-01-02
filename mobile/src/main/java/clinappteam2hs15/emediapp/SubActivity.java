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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

/*
editor corina:
- Buttons für die Praesentation eingefügt:

1. Notifikation für die Erinnerung der Medikamenteneinnahme.
Nur eine "statische" Notifikation umgesetzt, welche zu einer Einnahmebestaetigungsseite auf dem mobilen Device navigiert

2.Toastdarselltung beim Button"AddMedication"

1. Nofification für den Start der Pulsmessung
Da die Pulsmessung unzuverlässig ist, wird sie nicht automatisch gestartet, sondern es wird dem Benutzer eine Notifikation angezeigt,
dass dieser die Pulsmessung durchführen kann. Über die angezeigte Notifikation kann die Pulsmessung gestartet werden.

 */

public class SubActivity extends AppCompatActivity {

    private Button mAddMediIntakeButton;
    private Button mMediRemNotificationButton;
    private Button mPulsNotificationButton;
    public static final int NOTIFICATION_ID1 = 1;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "PhoneActivity";


    //onCreate ist die Methode, in welcher für die Activity definiert wird, was beim Erstellen beinhaltet sein soll
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Zeigt die Toastfunktion - der Benutzer soll eine Information erhalten, wenn die App eine Handlung ausgeführt hat
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
                        onMedReminderButtonClick(v);
                    }
                });

        /* Temporaer soll dieser Button die Notifikation für den Start der Pulsmessung auslösen
         */
        mPulsNotificationButton = (Button) findViewById(R.id.sendNotificationToWearable);
        mPulsNotificationButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendNotification();
                    }
                });

        //TODO: IMPLEMENT Floating Button Action
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    // zu Pulsmessungsnotifikation: Verbindung für den Aufruf der Notifikation auf der Wear
    mGoogleApiClient = new GoogleApiClient.Builder(this)
            .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(Bundle connectionHint) {
            Log.d(TAG, "onConnected: " + connectionHint);
        }
        @Override
        public void onConnectionSuspended(int cause) {
            Log.d(TAG, "onConnectionSuspended: " + cause);
        }
    })
            .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(ConnectionResult result) {
            Log.d(TAG, "onConnectionFailed: " + result);
        }
    })
            .addApi(Wearable.API)
    .build();
}
    // zu Pulsmessungsnotifikation
    private void sendNotification() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).await();
                for (Node node : nodes.getNodes()) {
                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(mGoogleApiClient, node.getId(), "/start/MainActivity", "Hello".getBytes()).await();
                    if (!result.getStatus().isSuccess()) {
                        Log.e(TAG, "ERROR");
                    } else {
                        Log.i(TAG, "Success sent to: " + node.getDisplayName());
                    }
                }
            }
        });
        thread.start();

    }

    // zu Pulsmessungsnotifikation
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();

    }

    // zu Pulsmessungsnotifikation
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();

    }


    // zu Medikationseinnahmeerinnerung
    // (author cvk) fuer Notifikationserzeugung auch bei geschlossener App //(author Mp) geändert auf eigene Klasse(SubActivity.java)
    private PendingIntent getActivityPendingIntent() {
        Intent activityIntent = new Intent(this, MedReminderActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this, 0, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    // (author cvk)zu Medikationseinnahmeerinnerung: Notifikationserstellung auf Knopfdruck fuer die Praesentation

    public void onMedReminderButtonClick(View view) {

        PendingIntent medReminderPendingIntent = getActivityPendingIntent();

        Notification medicationNotification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_2pill)
                .setContentTitle("6 Uhr Einnahme- Erinnerung")
                .setContentText("mit dem Essen -" + "\n" + "1 Tablette Dafalgan 500mg")
                .setContentIntent(medReminderPendingIntent)
                .setPriority(Notification.PRIORITY_HIGH)
                /* Visibility Private reduziert bei gelocktem Screen den Informationsgehalt der Notification
                .setVisibility(Notification.VISIBILITY_PRIVATE) */
                .setDefaults(Notification.DEFAULT_ALL)
                .setCategory(Notification.CATEGORY_STATUS)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID1, medicationNotification);

    }



}
