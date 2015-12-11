package clinappteam2hs15.emediapp;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import simulationData.Medicationplan;

/**
 * Created by corina on 08.12.15.
 */
public class MainOverviewTab extends Fragment {

    private Button mAddMediIntakeButton;
    private Medicationplan mMedicationplan;
    private TextView mMedicationView;
    private Button mMediRemNotificationButton;
    public static final int NOTIFICATION_ID = 1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout of this view
        View view = inflater.inflate(R.layout.content_main_page, container, false);

        //Ueber diesen Button soll eine Medikamenteneinnahme protokolliert werden können
        mAddMediIntakeButton = (Button) view.findViewById(R.id.addMedIntake_button);
        mAddMediIntakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.medieinnahmeErfasst_toast, Toast.LENGTH_SHORT).show();
            }
        });

        /* Temporaer soll dieser Button eine mediNotifikation auslösen*/
        mMediRemNotificationButton = (Button) view.findViewById(R.id.mediIntakeReminder_button);
        mMediRemNotificationButton.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent =new Intent();
               //PendingIntent pIntent =PendingIntent.getActivity(MainOverviewTab.this,0,intent,0);*/
                //)
            onMedReminderButtonClick(v);
            }
        });

        /*
        ToDo: auf 2. Menuinhalt verschieben.
        Ziel: Der Medikationsplan soll ausgelesen und dargestellt werden.

        mMedicationplan = new Medicationplan();
        mMedicationView = (TextView) findViewById(R.id.Medication_textView);
        mMedicationView.setText((CharSequence) mMedicationplan.getMedication());
*/

        return view;

    }

    /*// (author cvk) fuer Notifikationserzeugung auch bei geschlossener App // Vermerk Mp ->alte Variante um App von neuem auf default Startseite zu starten
    private PendingIntent getActivityPendingIntent() {
        Intent activityIntent = new Intent(getActivity(), MainPageActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(getActivity(), 0, activityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }*/
    // (author cvk) fuer Notifikationserzeugung auch bei geschlossener App //(author Mp) geändert auf eigene Klasse(SubActivity.java)
    private PendingIntent getActivityPendingIntent() {
        Intent activityIntent = new Intent(getActivity(), MedReminderActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(getActivity(), 0, activityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    // (author cvk) 1. Schritt der Notifikationserstellung auf Knopfdruck und mit Fixtext
    // ToDo: Notifikation aufgrund geplanter Uhrzeit starten und Daten für die Erinnerung auslesen
    public void onMedReminderButtonClick(View view) {

        PendingIntent medReminderPendingIntent = getActivityPendingIntent();

        Notification medicationNotification = new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.drawable.ic_2pill)
                .setContentTitle("Dafalgan 500mg")
                .setContentText("Einnahme für 6 Uhr geplant")
                .setContentIntent(medReminderPendingIntent)
                .setPriority(Notification.PRIORITY_HIGH)
                /* Visibility Private reduziert bei gelocktem Screen den Informationsgehalt der Notification
                .setVisibility(Notification.VISIBILITY_PRIVATE) */
                .setDefaults(Notification.DEFAULT_ALL)
                .setCategory(Notification.CATEGORY_STATUS)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());
        notificationManager.notify(NOTIFICATION_ID, medicationNotification);

    }
    public void onUpdateStandardNotificationButtonClick(View view) {

    }



}