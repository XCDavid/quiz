package mx.escom.quiz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.escom.quiz.R;
import mx.escom.quiz.activities.adapters.HistoryTestAdapter;
import mx.escom.quiz.activities.adapters.adapterVO.HistoryTestVO;
import mx.escom.quiz.activities.adapters.adapterVO.LessonVO;
import mx.escom.quiz.activities.fragments.QuestionsFragment;
import mx.escom.quiz.utils.SharedPreferencesUtils;

public class HistoryTest extends AppCompatActivity {

    ListView lvHistoryTest;
    HistoryTestAdapter historyTestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_test);
        lvHistoryTest = (ListView) findViewById(R.id.lv_history_test);

        historyTestAdapter = new HistoryTestAdapter(HistoryTest.this, R.layout.item_history_test, getData(HistoryTest.this));
        lvHistoryTest.setAdapter(historyTestAdapter);
        lvHistoryTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HistoryTestVO historyTestVO = (HistoryTestVO) lvHistoryTest.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putInt("idHistoryTest", historyTestVO.getIdTest());
                bundle.putBoolean("historyFlag", true);
                Intent intent = new Intent(HistoryTest.this, TestActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private List<HistoryTestVO> getData(HistoryTest historyTest) {
        List<HistoryTestVO> resultList = new ArrayList<>();

        String testCount = SharedPreferencesUtils.readFromPreferencesString(historyTest, SharedPreferencesUtils.TEST_COUNT, "0");
        int actualCount = Integer.valueOf(testCount);
        for (int i = 1; i <= actualCount; i++) {
            HistoryTestVO historyTestVO = new HistoryTestVO();
            String testJsonString = SharedPreferencesUtils.readFromPreferencesString(historyTest, SharedPreferencesUtils.TEST_AUX_NAME + i, "{}");
            try {
                JSONObject testJSON = new JSONObject(testJsonString);
                JSONArray lessonJSONArray = testJSON.optJSONArray("secciones");
//                    for (int i = 0; i < lessonJSONArray.length(); i++) {
//                        JSONObject lessonObj = lessonJSONArray.getJSONObject(i);
//                        int idLesson = lessonObj.optInt("id");
//                        String name = lessonObj.optString("nombre");
//                        boolean active = lessonObj.optBoolean("active");
//                        JSONArray questions = lessonObj.optJSONArray("preguntas");
//
//                        LessonVO lessonVO = new LessonVO();
//                        lessonVO.setIdLesson(idLesson);
//                        lessonVO.setName(name);
//                        lessonVO.setActive(active);
//                        lessonVO.setQuestions(questions);
//
//                        lessonVOLst.add(lessonVO);
//                    }
                int idTest = testJSON.optInt("idTest");
                String dateTest = testJSON.optString("fechaTest");
                int corrects = testJSON.optInt("correctas");
                int incorrects = testJSON.optInt("incorrectas");
                double res = testJSON.optDouble("calificacion");

                historyTestVO.setIdTest(idTest);
                historyTestVO.setDate(dateTest);
                historyTestVO.setCorrect(corrects);
                historyTestVO.setIncorrect(incorrects);
                historyTestVO.setResult(res);
                resultList.add(historyTestVO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }
}
