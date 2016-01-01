package clinappteam2hs15.emediapp;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;


/*
*Created by corina
* @author: Corina von Kaenel
*
* Pulsmesser starten und auf der Smartwatch, die aktuell gemessene Herzfrequenz anzeigen
*
* Dazu benötigt werden Informationen/Instruktionen in folgenden files:
* wear Manifest:
*   <uses-feature android:name="android.hardware.type.watch" />
    <uses-permission android:name="android.permission.BODY_SENSORS" />
* wear layout:
*   round_activity_emedi_puls.xml (plus values)
*
*
*
*/

public class EMediPulsActivity extends Activity implements SensorEventListener{

    private TextView mTextView;
    private TextView mHeartRateText;
    private SensorManager mSensorManager;
    private Sensor mHeartRateSensor;
    private static final String LOG_TAG = "MyHeart";
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "wear";
    private Sensor mStepCounter;
    private int count = 0;
    private static final String STEP_COUNT = "/stepcount";

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

        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        res = mSensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(LOG_TAG, " sensor registered: " + (res ? "yes" : "no"));


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {

                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .addApi(Wearable.API).build();



    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    private void increaseCounter(final int count) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                PutDataMapRequest putDataMapReq = PutDataMapRequest.create("/count");
                putDataMapReq.getDataMap().putInt("StepCount", count);
                PutDataRequest putDataReq = putDataMapReq.asPutDataRequest();


                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).await();
                byte [] stepBytes = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(count).array();
                for (Node node : nodes.getNodes()) {

                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(mGoogleApiClient, node.getId(), STEP_COUNT, stepBytes).await();
                    if (!result.getStatus().isSuccess()) {
                        Log.e(TAG, "ERROR");
                    } else {
                        Log.i(TAG, "Success sent to: " + node.getDisplayName());
                    }
                }

            }
        });
        t.start();

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            mTextView.setText(Float.toString(event.values[0]));

//        mHeartRateText.setText(Float.toString(event.values[0]));
            Log.d("watchapp", String.format("heart rate %s %f %d", event.sensor.getStringType(), event.values[0], event.timestamp));

            //  increaseCounter((int) event.values[0]);
        }

        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            Log.d("watchapp", String.format("step counter rate %s %f %d", event.sensor.getStringType(), event.values[0], event.timestamp));
            float stepCount = event.values[0];
            increaseCounter((int) stepCount);

        }
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



}