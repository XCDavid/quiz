package mx.escom.quiz.activities;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import mx.escom.quiz.R;
import mx.escom.quiz.activities.fragments.LessonsFragment;
import mx.escom.quiz.utils.SharedPreferencesUtils;

public class TestActivity extends AppCompatActivity {

    Toolbar toolbar;
    String testJSONString = "";
    JSONObject testJSON;

    boolean historyFlag = false;
    private TextView timerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        toolbar = (Toolbar) findViewById(R.id.test_app_bar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        historyFlag = bundle.getBoolean("historyFlag");
        if (getSupportActionBar() != null) {
            if (!historyFlag) {
                getSupportActionBar().setTitle(getResources().getString(R.string.timer_left));
                invalidateOptionsMenu();
            }
        }
        LessonsFragment lessonsFragment = new LessonsFragment();
        String tagFragmentLessons = "lessons";


        if (historyFlag) {
            int idHistoryTest = bundle.getInt("idHistoryTest");

            Bundle args = new Bundle();
            args.putInt("idHistoryTest", idHistoryTest);
            args.putBoolean("isHistory", true);
            lessonsFragment.setArguments(args);
        } else {
            int testCount = bundle.getInt("next_test_count");
            createNewTest(testCount + 1);

            Bundle args = new Bundle();
            args.putBoolean("isHistory", false);
            lessonsFragment.setArguments(args);
        }

        replaceFragmentHomeContent(lessonsFragment, tagFragmentLessons);
    }

    private void createNewTest(int nextCount) {
        testJSONString = getString(R.string.test_json);
        try {
            testJSON = new JSONObject(testJSONString);
            JSONArray arrayMaterias = testJSON.optJSONArray("secciones");
            int totalQ = 0;
            if (arrayMaterias != null) {

                for (int i = 0; i < arrayMaterias.length(); i++) {
                    JSONObject materiaJSON = arrayMaterias.getJSONObject(i);
                    JSONArray questions = materiaJSON.optJSONArray("preguntas");
                    if (questions != null)
                        totalQ += questions.length();
                }
            }

            testJSON.put("fechaTest", new Date().toString());
            testJSON.put("correctas", 0);
            testJSON.put("incorrectas", totalQ);
            testJSON.put("calificacion", 0);
            testJSON.put("idTest", nextCount);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferencesUtils.saveToPreferencesString(TestActivity.this, SharedPreferencesUtils.TEST_AUX_NAME + nextCount, testJSON.toString());
        //update test Count
        SharedPreferencesUtils.saveToPreferencesString(TestActivity.this, SharedPreferencesUtils.TEST_COUNT, nextCount + "");
    }

    public void replaceFragmentHomeContent(Fragment fragment, String fragmentTag) {
//        changeTitleActionBar(fragmentTag);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();
//        fragmentTrans.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_right_out);
        fragmentTrans.replace(R.id.fragment_container, fragment, fragmentTag);
        fragmentTrans.addToBackStack("add" + fragmentTag);
        fragmentTrans.commit();
//        fragmentTrans.commit();
//        fragmentManager.executePendingTransactions();
    }

    @Override
    public void onBackPressed() {
        if (isPrincipalFragmentVisible()) {
            finish();
        } else {
            super.onBackPressed();
//            navigationDrawerFragment.goToPrincipalFragment();
        }
    }

    public boolean isPrincipalFragmentVisible() {
//        String fragmentTagAux = getString(R.string.menu_home_drawer1);
        String fragmentTagAux = "lessons";
        LessonsFragment lessonsFragment = (LessonsFragment) getSupportFragmentManager().findFragmentByTag(fragmentTagAux);
        if (lessonsFragment != null && lessonsFragment.isVisible()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!historyFlag) {

            getMenuInflater().inflate(R.menu.timer_menu, menu);

            MenuItem timerItem = menu.findItem(R.id.break_timer);
            timerText = (TextView) MenuItemCompat.getActionView(timerItem);

            timerText.setPadding(10, 0, 10, 0); //Or something like that...

            startTimer(30000, 1000); //One tick every second for 30 seconds
        }

        return true;
    }

    private void startTimer(long duration, long interval) {

        CountDownTimer timer = new CountDownTimer(duration, interval) {

            @Override
            public void onFinish() {
                //TODO Whatever's meant to happen when it finishes
                Toast.makeText(TestActivity.this, "Tiempo agotado consulta tus resultados en la seccion \"Consultar resultados\"", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onTick(long millisecondsLeft) {
                int secondsLeft = (int) Math.round((millisecondsLeft / (double) 1000));
                timerText.setText(secondsToString(secondsLeft));
            }
        };

        timer.start();
    }

    private String secondsToString(int improperSeconds) {

        //Seconds must be fewer than are in a day

        Time secConverter = new Time();

        secConverter.hour = 0;
        secConverter.minute = 0;
        secConverter.second = 0;

        secConverter.second = improperSeconds;
        secConverter.normalize(true);

        String hours = String.valueOf(secConverter.hour);
        String minutes = String.valueOf(secConverter.minute);
        String seconds = String.valueOf(secConverter.second);

        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        if (hours.length() < 2) {
            hours = "0" + hours;
        }

        String timeString = hours + ":" + minutes + ":" + seconds;
        return timeString;
    }
}
