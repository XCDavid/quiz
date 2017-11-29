package mx.escom.quiz.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import mx.escom.quiz.R;
import mx.escom.quiz.activities.fragments.LessonsFragment;
import mx.escom.quiz.utils.SharedPreferencesUtils;

public class TestActivity extends AppCompatActivity {

    Toolbar toolbar;
    String testJSONString = "";

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
        Bundle bundle = getIntent().getExtras();
        int nextTestCount = bundle.getInt("next_test_count");
        createNewTest(nextTestCount+1);

        LessonsFragment lessonsFragment = new LessonsFragment();
        String tagFragmentLessons = "lessons";
        replaceFragmentHomeContent(lessonsFragment,tagFragmentLessons);
    }

    private void createNewTest(int i) {
        testJSONString = getString(R.string.test_json);
        SharedPreferencesUtils.saveToPreferencesString(TestActivity.this,SharedPreferencesUtils.TEST_AUX_NAME+i,testJSONString);
        //update test Count
        SharedPreferencesUtils.saveToPreferencesString(TestActivity.this,SharedPreferencesUtils.TEST_COUNT,i+"");
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

//    public void

    public String getActualTest(){
        return testJSONString;
    }
}
