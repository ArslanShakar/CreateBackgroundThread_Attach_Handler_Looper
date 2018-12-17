package com.practice.coding.createbackgroundthread_attach_handler_looper;

import android.os.Looper;

public class DownloadThread extends Thread{
    private final MainActivity mainActivityRef;
    public DownloadHandler handler;

    public DownloadThread(MainActivity activity) {
        this.mainActivityRef = activity;
    }

    @Override
    public void run() {
        /*
         prepare() is a static method , it create the looper object as well as it create the Message/Work Queue
        and keep in mind we create the looper in Download thread so this looper is of DownloadThread or attached to this Thread
        Under which thread we create it attached with..
        --> The MessageQueue that created belong to this Download thread... only the download thread can send messages to this
        MessageQueue...
         */
        Looper.prepare();

        /*
        You know very well we must attached handler with the specific thread ..Here in the Download thread i instansiate
        the handler so handler is attached with this download thread.
         */
        handler = new DownloadHandler(mainActivityRef);


        Looper.loop();

        /**
         Looper.loop() this method loop over the MessageQueue and send task one by one to the handler for executing..when handler
         executed one task it send the next one.
         */



    }
}
