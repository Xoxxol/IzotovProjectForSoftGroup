package ua.com.izotov.izotovprojectsoftgroup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityThird extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewTherdEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_third);

        Button btnExit = (Button) findViewById(R.id.buttonThirdBack);
        btnExit.setOnClickListener(this);
        textViewTherdEmail = (TextView) findViewById(R.id.textViewThirdEmail);
        textViewTherdEmail.setText(MainActivityFirst.getEnterEmail());

    }

    @Override
    public void onClick(View v) {
        Intent intentFour = new Intent(this, MainActivityFirst.class);
        startActivity(intentFour);

    }
}
