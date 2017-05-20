package ua.com.izotov.izotovprojectsoftgroup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivitySecond extends AppCompatActivity implements View.OnClickListener {

    private EditText etSecondMail;
    private EditText etSecondPassword;
    private TextView etSecondPasswordConfirm;
    private Button regBtn;
    private SharedPreferences sprefSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_second);

        etSecondMail = (EditText) findViewById(R.id.editTextSecondMail);
        etSecondPassword = (EditText) findViewById(R.id.editTextSecondPassword);
        etSecondPasswordConfirm = (EditText) findViewById(R.id.editTextSecondPasswordConfirm);
        regBtn = (Button) findViewById(R.id.buttonSecondReg);
        regBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (checkMailPref(etSecondMail.getText().toString()))
            etSecondMail.setError("Пользователь с таким именем уже зарегистрирован");

        if (!isPasswordValid(etSecondPassword.getText().toString()))
            etSecondPassword.setError("Пароль должен быть не менее 4 символов");

        if (!equalsPassword(etSecondPassword.getText().toString(), etSecondPasswordConfirm.getText().toString())) {
            Toast toastFirst = Toast.makeText(MainActivitySecond.this, "Пароли не совпадают ", Toast.LENGTH_SHORT);
            toastFirst.show();

        } else if (isEmailValid(etSecondMail.getText().toString())
                && isPasswordValid(etSecondPassword.getText().toString())
                && !checkMailPref(etSecondMail.getText().toString())) {
            saveData(etSecondMail.getText().toString(), etSecondPassword.getText().toString());
            Intent intentOne = new Intent(this, MainActivityFirst.class);
            startActivity(intentOne);

        } else {
            Toast toastSecond = Toast.makeText(MainActivitySecond.this, "Неверный логин и/или пароль ", Toast.LENGTH_SHORT);
            toastSecond.show();
        }
    }

    public Boolean checkMailPref(String email) {
        sprefSecond = getSharedPreferences("myEmailPassword", MODE_PRIVATE);
        Map<String, ?> allPrefer = sprefSecond.getAll();
        for (Map.Entry entry : allPrefer.entrySet()) {
            if (entry.getKey().equals(email))
                return true;
        }
        return false;
    }

    private void saveData(String email, String password) {
        sprefSecond = getSharedPreferences("myEmailPassword", MODE_PRIVATE);
        SharedPreferences.Editor ed = sprefSecond.edit();
        ed.putString(email, String.valueOf(password.hashCode()));
        ed.apply();
    }

    private boolean isEmailValid(String email) {
        String patternEmail = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        Pattern ptrn = Pattern.compile(patternEmail);
        Matcher matcher = ptrn.matcher(email);

        if (matcher.matches() && (email.length() > 5))
            return true;
        else {
            etSecondMail.setError("Проверьте правильность написания E-mail");
            return false;
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 3;
    }

    private boolean equalsPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }


}




