package com.practice.coding.createbackgroundthread_attach_handler_looper;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DownloadThread downloadThread;

    private TextView textView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBarHorizontal);

        downloadThread = new DownloadThread(MainActivity.this);
        downloadThread.start();
        textView.setText("Code Executing by "+downloadThread.getName());

    }

    public void setData(String data)
    {
        textView.append("\n"+data);
        progressBar.setVisibility(View.GONE);
    }

    public void startProgressBar()
    {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void runCode(View view) {
        //when downloading start the progress bar visible

        /*
        here thread is start in the onCreate Method..Two reasons
        --> first only one threat create at background when we again
        and again click on the Run Code button then the message pass to the Message queue for performing some task
        like here download song list but , number of times we download one list , for this only One Thread do this work
        No more threads created again and again like in basics example 1 when we click on run code button new thread created
        every time that download the song list...
        --> Secondly the most technical and important reason is if we start the thread in on Click Button then here the thread
        starting and executing the next code then handler in Download thread may be it is not initialized fully or not the Download
        Thread run method code is executed fully and here we send message to the MessageQueue with the handler reference
        May be handler is not instantiated , it contain null reference then exception araise and app crashes.

        Thats why we start our Download Thread in onCreate Method...
         */

        for(String songName:SongsPlaylist.songs)
        {
            /*
            Message.obtain(); it create a ThreadPool for message and if the message id already available in the pool it send
            its  benefit is again and agian Message object is not created and destroyed ...means its increase the performance
             */
            Message message = Message.obtain();
            /*
            we can send data thu sendData method pass bundle but we need one string so we send the obj variable that Message class
            builtin
             */
            message.obj = songName;

            /*here we can access the handler object that is created in the DownloadThread or you can say the handler of the
            DownloadThread  we using that handler i send the message in the MessageQueue of DownloadThread for executing*/
            downloadThread.handler.sendMessage(message);
        }
    }
}
