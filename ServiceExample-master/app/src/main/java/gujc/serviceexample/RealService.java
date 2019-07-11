package gujc.serviceexample;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class RealService extends Service {
    private Thread mainThread;
    public static Intent serviceIntent = null;

    public RealService() {
    }
    public static final int ServerPort = 4000;

    public static final String ServerIP = "192.168.219.103";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        serviceIntent = intent;
        showToast(getApplication(), "Start Service");


        mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("aa hh:mm");
                boolean run = true;
                while (run) {
                    try {
                        try {
                        System.out.println("S: Connecting...");
                        ServerSocket serverSocket = new ServerSocket(ServerPort);
                        while (true) {
                            Socket client = serverSocket.accept();
                            System.out.println("S: Receiving...");
                            try {
                                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                                String str = in.readLine();
                                System.out.println("S: Received: '" + str + "'");
                                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
                                out.println("Server Received " + str);
                                if (str == "123"){
                                    String p = "Signal Recive";
                                    showToast(getApplication(),p);
                                }
                            } catch (Exception e) {
                                System.out.println("S: Error");
                                e.printStackTrace();
                            } finally {
                                client.close();
                                System.out.println("S: Done.");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("S: Error");
                        e.printStackTrace();
                    }
                       /* Thread.sleep(1000 * 60 * 1); // 1 minute
                        Date date = new Date();
                        showToast(getApplication(), sdf.format(date));*/
                    } catch (RuntimeException e) {
                        run = false;
                        e.printStackTrace();
                    }
                }
            }
        });
        mainThread.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        serviceIntent = null;
        setAlarmTimer();
        Thread.currentThread().interrupt();

        if (mainThread != null) {
            mainThread.interrupt();
            mainThread = null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void showToast(final Application application, final String msg) {
        Handler h = new Handler(application.getMainLooper());
        h.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(application, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void setAlarmTimer() {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, 1);
        Intent intent = new Intent(this, AlarmRecever.class);
        PendingIntent sender = PendingIntent.getBroadcast(this, 0,intent,0);

        AlarmManager mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
    }

}
