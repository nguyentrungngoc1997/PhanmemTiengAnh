package com.trungngoc.myapplication;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private EditText text; // khoi tao Edittext
    private Button b1;// khoi tao  Button in ra chu
    private Button b2;// khoi tao  Button phan Listenning
    private TextToSpeech tts; // khoi tao TextToSpeech
    private TextView textView; // khoi tao textView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        textView = (TextView)findViewById(R.id.textView) ;// tra ve 1 View dung id tryen vao
        text = (EditText)findViewById( R.id.editText1);
          b1 = (Button)findViewById( R.id.button1 );
          b2 = (Button)findViewById( R.id.button2 );

        tts = new TextToSpeech( this,this );

        /* Xu ly su kien trong Edittext bang setOnClickListener goi ham getData()*/
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getData();
            }

        });

        /* Xu ly su kien trong Edittext bang setOnClickListener bang cach goi textToSpeak*/
        b2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak( text.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }

        } );


    }


    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        getMenuInflater().inflate( R.menu.main, menu );
//        return true;
//    }

    protected void onDestroy() //ket thuc vong doi activity
    {
        if(tts!=null)
        {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    @Override
    public void onInit(int status) {  //được gọi để báo hiệu việc hoàn thành khởi tạo công cụ TextToSpeech.
        if(status!=TextToSpeech.ERROR)
        {
            tts.setLanguage( Locale.US);//US Su dung ngon ngu
        }
    }

    /*Ham su ly su kien  trong Edittext*/
    public void getData()
    {
        String data = text.getText().toString(); // lay du lieu trong Edittext
        textView.setText(data);// thuyet lap gia tri cho Edittext
    }

}
