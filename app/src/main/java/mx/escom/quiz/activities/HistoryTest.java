package mx.escom.quiz.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import mx.escom.quiz.R;
import mx.escom.quiz.activities.adapters.LessonsAdapter;
import mx.escom.quiz.activities.adapters.adapterVO.LessonVO;
import mx.escom.quiz.activities.fragments.QuestionsFragment;

public class HistoryTest extends AppCompatActivity {

    ListView lvLessons;
    LessonsAdapter lessonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_test);
        lvLessons = (ListView) findViewById(R.id.lv_history_test);

        lessonsAdapter = new LessonsAdapter(HistoryTest.this, R.layout.item_lesson_test, getData(HistoryTest.this));
        lvLessons.setAdapter(lessonsAdapter);
        lvLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LessonVO lessonVO = (LessonVO) lvLessons.getItemAtPosition(position);
                if(lessonVO.isActive()){
                    QuestionsFragment questionsFragment = new QuestionsFragment();
                    Bundle args = new Bundle();
                    args.putInt("idLesson", lessonVO.getIdLesson());
                    args.putBoolean("isHistory", false);
                    questionsFragment .setArguments(args);
                    ((TestActivity)getActivity()).replaceFragmentHomeContent(questionsFragment, "questions");
                }
            }
        });

    }
}
