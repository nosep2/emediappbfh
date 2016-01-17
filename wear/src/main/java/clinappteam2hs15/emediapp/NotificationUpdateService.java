package clinappteam2hs15.emediapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by corina
 * Dieser NotificationUpdateService hört (listens) auf den Aufruf vom mobilen device her und erstellt die Notifikation
 * um die Pulsmessung zu starten
 * Der Service ist im Manifest der Wear registriert
 */
public class NotificationUpdateService extends WearableListenerService {

    private static final int NOTIFICATION_REQUEST_CODE = 1;
    private static final int NOTIFICATION_ID = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("wear", "Starting...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);
        Log.i("wear", "received a message" + messageEvent.getPath());
        /*Intent i = new Intent(this, EMediPulsActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);*/

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
    }
}
