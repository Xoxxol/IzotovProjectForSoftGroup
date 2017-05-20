package ua.com.izotov.izotovprojectsoftgroup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Map;

public class MainActivityFirst extends AppCompatActivity implements View.OnClickListener {

    private Button enterBtn;
    private Button regBtn;
    private EditText emailEditText;
    private EditText passwordEditText;
    private static String enterEmail;
    private Toast toastFirst;
    private SharedPreferences sprefFirst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_first);

        enterBtn = (Button) findViewById(R.id.enterButton);
        regBtn = (Button) findViewById(R.id.regButton);
        emailEditText = (EditText) findViewById(R.id.editText_enter);
        passwordEditText = (EditText) findViewById(R.id.editText_reg);

        enterBtn.setOnClickListener(this);
        regBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.enterButton:

                if (containsMailPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())) {
                    Intent intentThrid = new Intent(this, MainActivityThird.class);
                    setEnterEmail(emailEditText.getText().toString());
                    startActivity(intentThrid);
                } else {
                    toastFirst = Toast.makeText(MainActivityFirst.this, "Неверный логин и/или пароль ", Toast.LENGTH_SHORT);
                    toastFirst.show();
                }
                break;

            case R.id.regButton:
                Intent intentTwo = new Intent(this, MainActivitySecond.class);
                startActivity(intentTwo);
                break;
        }
    }

    public boolean containsMailPassword(String email, String password){
        sprefFirst = getSharedPreferences("myEmailPassword", MODE_PRIVATE);
        Map<String, ?> allPrefer = sprefFirst.getAll();
        for (Map.Entry entry : allPrefer.entrySet()){
            if (entry.getKey().equals(email)&& entry.getValue().equals(String.valueOf(password.hashCode())))
                return true;
           }
           return false;

    }

    public static void setEnterEmail(String email) {
        enterEmail = email;
    }

    public static String getEnterEmail() {
        return enterEmail;
    }
}