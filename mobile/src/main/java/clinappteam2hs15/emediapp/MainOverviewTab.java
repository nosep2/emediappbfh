package clinappteam2hs15.emediapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by corina on 08.12.15.
 *
 * Dies ist der Inhalt des Übersichts Tabs in der Startactivität (MainPageActivity.java)
 * dazugehöriges Layout: overview_main_page.xml
 * (extends Fragment, da die Tabview mit "Fragmenten" arbeitet
 *
 * In der Ansicht befindet sich eine analoge Uhr, das Datum und Informationen zu den Medikationseinnahme am aktuellen Tag
 */
public class MainOverviewTab extends Fragment
        implements DataApi.DataListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, MessageApi.MessageListener
{

    private GoogleApiClient mGoogleApiClient;
    public static String TAG = "mobile";
    private static final String STEP_COUNT = "/stepcount";
    private TextView mStepCount;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout of this view
        View view = inflater.inflate(R.layout.content_overview_main_page, container, false);

        // TextView für die Datumsanzeige erstellen und Datum für die Anzeige holen
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd.MM.yyyy");
        TextView dateTextView = (TextView) view.findViewById(R.id.content_overview_date);
        dateTextView.setText(dateFormat.format(Calendar.getInstance().getTime()));

        // Textview für die Anzeige, der auf der Wear gemessenen Schritte
        mStepCount = (TextView) view.findViewById(R.id.step_count);
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


        updateCount(100);


        return view;

    }


    // es folgen die Methoden für das Auslesen der Schrittdaten der Wear

    public void onStart() {
        super.onStart();
        Log.d(TAG, "starting");
        mGoogleApiClient.connect();
    }


    public void onStop() {
        super.onStop();

        if (null != mGoogleApiClient && mGoogleApiClient.isConnected()) {
            Wearable.DataApi.removeListener(mGoogleApiClient, this);
            Wearable.MessageApi.removeListener(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
        Log.d(TAG, "stopping");

    }

    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        /*
        final List<DataEvent> events = FreezableUtils
                .freezeIterable(dataEventBuffer);
        dataEventBuffer.close();
        Log.d(TAG, "DataItem changed");
        for (DataEvent event : events) {
            if (event.getType() == DataEvent.TYPE_DELETED) {
                Log.d(TAG, "DataItem deleted: " + event.getDataItem().getUri());
            } else if (event.getType() == DataEvent.TYPE_CHANGED) {
                Log.d(TAG, "DataItem changed: " + event.getDataItem().getUri());
                DataItem item = event.getDataItem();
                if (item.getUri().getPath().compareTo("/count") == 0) {
                    DataMap dataMap = DataMapItem.fromDataItem(item).getDataMap();
                    updateCount(dataMap.getInt("StepCount"));
                }
            }
        }*/
    }

    private void updateCount(int stepCount) {
        mStepCount.setText(String.format(getString(R.string.step_count), stepCount));
    }


    public void onConnected(Bundle bundle) {
        Log.d(TAG, "Connected to Google Api Service");
        Wearable.DataApi.addListener(mGoogleApiClient, this);
        Wearable.MessageApi.addListener(mGoogleApiClient, this);
    }

    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Connection suspended");
    }


    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(TAG, "Conneciton failed");
    }

    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d(TAG, "received a message " + messageEvent.getPath());
        if (messageEvent.getPath().contains(STEP_COUNT)) {
            int count = ByteBuffer.wrap(messageEvent.getData()).order(ByteOrder.LITTLE_ENDIAN).getInt();
            updateCount(count);
        }

    }


}