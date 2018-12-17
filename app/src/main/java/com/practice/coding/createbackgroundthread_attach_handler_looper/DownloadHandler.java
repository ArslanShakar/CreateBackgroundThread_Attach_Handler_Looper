package com.practice.coding.createbackgroundthread_attach_handler_looper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class DownloadHandler extends Handler {

    private static final String TAG = "myTag";
    private final MainActivity mainActivityRef;

    public DownloadHandler(MainActivity activity) {
        this.mainActivityRef = activity;
    }

    @Override
    public void handleMessage(Message msg) {
        String songName = msg.obj.toString();
        downloadSong(songName);

    }

    private void downloadSong(final String songName) {
        mainActivityRef.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivityRef.setData("Start Downloading. . .");
                mainActivityRef.startProgressBar();
            }
        });
        Log.i(TAG, "Start Downloading. . .");
        try { Thread.sleep(4000); } catch (InterruptedException e) { }
        Log.i(TAG, "Song Downloaded : "+songName);

        /*
        here with the mainActivity reference i am update the views but getting the reference of activity and updating the views
        this approuch is not reccomended. for best programming practice use interface for communication, or Shared ViewModel
         */

        mainActivityRef.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivityRef.setData("Song Downloaded : "+songName);
            }
        });

        /*
        This code also do the 100% same work of runOnUiThread().. actually runOnUiThread using this method we dont need to
        get the handler reference..it automatically handler reference by using the main activity reference...
        here i get manually handler and looper reference... in the above runOnUIThread... that the the handler reference by using
        the activity reference..here i dont need reference of activity for handler and looper ..becz i update my textview
        using public method setData so here i need the activity referece...thats why using the above technique..if use the interfaces
        for updating the views then this technique is the good programming practice
        let see code
         */

        /*
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                mainActivityRef.setData("Song Downloaded : "+songName);
            }
        });
        */
    }

}
