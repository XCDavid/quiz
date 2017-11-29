package mx.escom.quiz.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mx.escom.quiz.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    TextInputEditText etUser;
    TextInputEditText etPass;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = (TextInputEditText) findViewById(R.id.ti_et_user_login);
        etPass = (TextInputEditText) findViewById(R.id.ti_et_password_login);
        bLogin = (Button) findViewById(R.id.b_login);
        bLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.b_login:
                String userText = etUser.getText().toString();
                String passText = etPass.getText().toString();

                if(userText.equals("123")  && passText.equals("123")){
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    etUser.setError("Usuario o contraseña incorrecta.");
                }
                break;
        }
    }
}
