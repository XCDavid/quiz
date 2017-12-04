package mx.escom.quiz.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        toolbar = (Toolbar) findViewById(R.id.test_app_bar);
        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle(getResources().getString(R.string.enrolamiento_activity_title));
//            invalidateOptionsMenu();
//        }
        LessonsFragment lessonsFragment = new LessonsFragment();
        String tagFragmentLessons = "lessons";

        Bundle bundle = getIntent().getExtras();
        historyFlag = bundle.getBoolean("historyFlag");
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
}
