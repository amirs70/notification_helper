package ir.itroid.notificationhelper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationHelper {

    private NotificationCompat.Builder builder;
    private NotificationCompat.BigTextStyle bigText;
    private Context context;
    public NotificationManager notificationManager;
    private int id;

    public NotificationHelper(Context context, int id, String chanel_id) {
        this.id = id;
        this.context = context;
        builder = new NotificationCompat.Builder(this.context, chanel_id);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        builder.setVibrate(new long[]{0, 250, 250, 250});
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            builder.setPriority(Notification.PRIORITY_HIGH);
        bigText = new NotificationCompat.BigTextStyle();
    }

    public NotificationHelper(Context context, int id) {
        this(context, id, "");
    }

    public NotificationHelper(Context context) {
        this(context, 0, "");
    }

    public NotificationHelper setLight(int color) {
        builder.setLights(color, 1000, 5000);
        return this;
    }

    public NotificationHelper setVibrate(long[] times) {
        builder.setVibrate(times);
        return this;
    }

    public NotificationHelper setSound(Uri uri) {
        builder.setSound(uri);
        return this;
    }

    public NotificationHelper setTitle(String title) {
        bigText.setBigContentTitle(title);
        builder.setContentTitle(title);
        return this;
    }

    public NotificationHelper setIntent(Intent intent) {
        int requestID = (int) System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        PendingIntent pIntent = PendingIntent.getActivity(context, requestID, intent, 0);
        builder.setAutoCancel(true);
        builder.setContentIntent(pIntent);
        return this;
    }

    public NotificationHelper addAction(int drawable, String title, Intent intent) {
        int requestID = (int) System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        PendingIntent pIntent = PendingIntent.getActivity(context, requestID, intent, 0);
        builder.setAutoCancel(true);
        builder.addAction(drawable, title, pIntent);
        return this;
    }

    public NotificationHelper addAction(String title, Intent intent) {
        return addAction(0, title, intent);
    }

    public NotificationHelper setAutoCancel(boolean b) {
        builder.setAutoCancel(b);
        return this;
    }

    public NotificationHelper setDefaults(int i) {
        builder.setDefaults(i);
        return this;
    }

    public NotificationHelper setBody(String body) {
        bigText.setSummaryText(body);
        builder.setContentText(body);
        return this;
    }

    public NotificationHelper setBigBody(String body) {
        bigText.bigText(body);
        return this;
    }

    public NotificationHelper setSmallIcon(int drawable) {
        builder.setSmallIcon(drawable);
        return this;
    }

    public NotificationHelper setLargeIcon(int drawable) {
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), drawable));
        return this;
    }

    public NotificationHelper setContentInfo(CharSequence info) {
        builder.setContentInfo(info);
        return this;
    }

    public NotificationHelper setPriority(int priority) {
        builder.setPriority(priority);
        return this;
    }

    public NotificationHelper large(String longText) {
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(longText));
        return this;
    }

    public void fire() {
        builder.setStyle(bigText);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }

}
