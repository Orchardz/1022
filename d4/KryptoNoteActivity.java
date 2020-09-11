//NOAH CHIANG
//student id : 216612285
//https://www.youtube.com/watch?v=6rVLxI-zDPI&feature=youtu.be

package com.example.kryptonote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class KryptoNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonot_layout);
    }
public void onEncrypt(View V) {
    String key = ((EditText) findViewById(R.id.key)).getText().toString();
    String  name= ((EditText) findViewById(R.id.data)).getText().toString();
    try {
        cipher myModel = new cipher(key);
        String result = myModel.Encrypt(name);
        ((TextView) findViewById(R.id.data)).setText(result);
    } catch (Exception e){}
}
    public void onDecrypt(View V){  String key = ((EditText) findViewById(R.id.key)).getText().toString();
        String  name= ((EditText) findViewById(R.id.data)).getText().toString();
        try {
            cipher myModel = new cipher(key);
            String result = myModel.Decrypt(name);
            ((TextView) findViewById(R.id.data)).setText(result);
        } catch (Exception e){}
    }

    public void onSave(View v)
    {
        try

        {
            //onSave
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText)findViewById(R.id.data)).getText().toString());

            fw.close();
            Toast.makeText(this,"Note Saved.",Toast.LENGTH_LONG).show();

        }
        catch (Exception e)

        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void onLoad(View v) {


        //onLoad
        try {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();

            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileReader fr = new FileReader(file);
            String show = "";
            for (int c = fr.read(); c != -1; c = fr.read()) {
                show += (char) c;
            }
                fr.close();
            ((EditText)findViewById(R.id.data)).setText(show);


        } catch (Exception e) { Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}


