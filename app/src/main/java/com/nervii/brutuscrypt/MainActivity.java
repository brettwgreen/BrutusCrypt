package com.nervii.brutuscrypt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private void decryptMessage() {
        TextView message = (TextView)findViewById(R.id.message);
        String msg = message.getText().toString();
        TextView code = (TextView)findViewById(R.id.code);
        TextView decryptedMessage = (TextView)findViewById(R.id.decryptedMessage);
        CrackedPackage result = Decipher.CrackShiftCipher(msg);
        code.setText(result.Cipher);
        decryptedMessage.setText(result.DecryptedMessage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button decryptButton = (Button)findViewById(R.id.decryptBtn);
        decryptButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                decryptMessage();
             }
         }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
