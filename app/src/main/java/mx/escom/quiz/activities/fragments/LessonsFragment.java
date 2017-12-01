package mx.escom.quiz.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.escom.quiz.R;
import mx.escom.quiz.activities.TestActivity;
import mx.escom.quiz.activities.adapters.LessonsAdapter;
import mx.escom.quiz.activities.adapters.adapterVO.LessonVO;
import mx.escom.quiz.utils.SharedPreferencesUtils;

public class LessonsFragment extends Fragment {
    ListView lvLessons;
    LessonsAdapter lessonsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lessons, container, false);
        lvLessons = (ListView) layout.findViewById(R.id.lv_lessons);

        lessonsAdapter = new LessonsAdapter(getActivity(), R.layout.item_lesson_test, getData(getActivity()));
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
        return layout;
    }

    private List<LessonVO> getData(FragmentActivity activity) {
        List<LessonVO> lessonVOLst = new ArrayList<>();
        String countLesson = SharedPreferencesUtils.readFromPreferencesString(activity,SharedPreferencesUtils.TEST_COUNT,"0");
        int count = Integer.valueOf(countLesson);
        if(count != 0){
            String testJsonString = SharedPreferencesUtils.readFromPreferencesString(activity,SharedPreferencesUtils.TEST_AUX_NAME+count,"{}");
            try {
                JSONObject testJSON = new JSONObject(testJsonString);
                JSONArray lessonJSONArray = testJSON.optJSONArray("secciones");
                for (int i = 0; i < lessonJSONArray.length(); i++) {
                        JSONObject lessonObj = lessonJSONArray.getJSONObject(i);
                        int idLesson = lessonObj.optInt("id");
                        String name = lessonObj.optString("nombre");
                        boolean active = lessonObj.optBoolean("active");
                        JSONArray questions = lessonObj.optJSONArray("preguntas");

                        LessonVO lessonVO = new LessonVO();
                        lessonVO.setIdLesson(idLesson);
                        lessonVO.setName(name);
                        lessonVO.setActive(active);
                        lessonVO.setQuestions(questions);

                    lessonVOLst.add(lessonVO);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  lessonVOLst;
    }

}
