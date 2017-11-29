package mx.escom.quiz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import mx.escom.quiz.R;
import mx.escom.quiz.utils.SharedPreferencesUtils;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button bNewTest;
    Button bTestHistory;

    int testNext = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bNewTest = (Button) findViewById(R.id.b_new_test_home);
        bTestHistory = (Button) findViewById(R.id.b_test_history_home);

        getOrCreateTests();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b_new_test_home:
                Bundle bundle = new Bundle();
                bundle.putInt("next_test_count", testNext);
                Intent intentTest = new Intent(HomeActivity.this, TestActivity.class);
                intentTest.putExtras(bundle);
                startActivity(intentTest);
                break;
            case R.id.b_test_history_home:

                break;
        }
    }

    private void getOrCreateTests() {
        String testJSONString = getString(R.string.test_json);
        try {
            JSONObject testJson = new JSONObject(testJSONString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String testCount = SharedPreferencesUtils.readFromPreferencesString(HomeActivity.this, SharedPreferencesUtils.TEST_COUNT, "0");
        testNext = Integer.valueOf(testCount);

    }
}
