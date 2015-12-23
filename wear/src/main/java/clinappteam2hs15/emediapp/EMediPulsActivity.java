package clinappteam2hs15.emediapp;


import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;

import java.util.List;


/*
* Pulsmesser starten und auf der Smartwatch, die aktuell gemessene Herzfrequenz anzeigen
*
* Dazu benötigt werden Informationen/Instruktionen in folgenden files:
* wear Manifest:
*   <uses-feature android:name="android.hardware.type.watch" />
    <uses-permission android:name="android.permission.BODY_SENSORS" />
* wear layout:
*   round_activity_emedi_puls.xml (plus values)
*
* @author: Corina von Kaenel
*
*/

public class EMediPulsActivity extends Activity implements SensorEventListener{

    private TextView mTextView;
    private TextView mHeartRateText;
    private SensorManager mSensorManager;
    private Sensor mHeartRateSensor;
    private static final String LOG_TAG = "MyHeart";
    private GoogleApiClient mGoogleApiClient;

    private static final int NOTIFICATION_REQUEST_CODE = 1;
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emedi_puls);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.heartrate_text);
                mHeartRateText = (TextView) findViewById(R.id.heartrate_text);
            }
        });

        mSensorManager = ((SensorManager)getSystemService(SENSOR_SERVICE));
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor s : sensorList) {
            Log.d(LOG_TAG, s.getStringType() + " " + s.getType());

        }
        mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        boolean res = mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(LOG_TAG, " sensor registered: " + (res ? "yes" : "no"));

        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(Wearable.API).build();
        mGoogleApiClient.connect();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        mTextView.setText(Float.toString(event.values[0]));

//        mHeartRateText.setText(Float.toString(event.values[0]));
        Log.d("watchapp", String.format("%s %f %d", event.sensor.getStringType(), event.values[0], event.timestamp));
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    /**
     * Handles the button press to finish this activity and take the user back to the Home.
     */
    public void onFinishActivity(View view) {
        setResult(RESULT_OK);
        mSensorManager.unregisterListener(this);
        finish();
    }
    /**
     * cvk: Notifikation um die Pulsmessung zu starten
     * ToDo: Timer starten oder auf Phone anzeigen, wenn Battery Charging
     */
    public void showNotification(View view) {
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                .setSmallIcon(R.drawable.ic_heart)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .addAction(R.drawable.ic_heart,
                        getText(R.string.action_launch_activity),
                        PendingIntent.getActivity(this, NOTIFICATION_REQUEST_CODE,
                                new Intent(this, EMediPulsActivity.class),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .build();
        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification);
        finish();
    }


}