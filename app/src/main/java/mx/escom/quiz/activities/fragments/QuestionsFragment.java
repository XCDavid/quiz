package mx.escom.quiz.activities.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.escom.quiz.R;
import mx.escom.quiz.activities.TestActivity;
import mx.escom.quiz.activities.adapters.LessonsAdapter;
import mx.escom.quiz.activities.adapters.adapterVO.LessonVO;
import mx.escom.quiz.utils.SharedPreferencesUtils;

public class QuestionsFragment extends Fragment {
    LinearLayout linearLayout;
    private boolean isHistory = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.questions_fragment, container, false);
        linearLayout = (LinearLayout) layout.findViewById(R.id.ll_content_questions);

        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        linearLayout.removeAllViews();
        Bundle args = getArguments();
        if (args != null) {
            int idLesson = args.getInt("idLesson");
            isHistory = args.getBoolean("isHistory");

            getData(getActivity(),idLesson);
        }
    }

    private List<LessonVO> getData(final FragmentActivity activity, final int idLesson) {
        List<LessonVO> lessonVOLst = new ArrayList<>();
        String countLesson = SharedPreferencesUtils.readFromPreferencesString(activity,SharedPreferencesUtils.TEST_COUNT,"0");
        final int count = Integer.valueOf(countLesson);
        if(count != 0){
            final String testJsonString = SharedPreferencesUtils.readFromPreferencesString(activity,SharedPreferencesUtils.TEST_AUX_NAME+count,"{}");
            try {
                final JSONObject testJSON = new JSONObject(testJsonString);
                final JSONArray lessonJSONArray = testJSON.optJSONArray("secciones");

                if (idLesson!=0) {

                    for (int i = 0; i < lessonJSONArray.length(); i++) {
                        final JSONObject lessonObj = lessonJSONArray.getJSONObject(i);
                        int idLessonTest = lessonObj.optInt("id");
                        //Materia correcta - obetener las preguntas e imprimirlas
                        if(idLessonTest == idLesson) {
                            String name = lessonObj.optString("nombre");
                            boolean active = lessonObj.optBoolean("active");
                            JSONArray questions = lessonObj.optJSONArray("preguntas");
                            if(questions != null){
                                for (int c =0; c<questions.length(); c++){
                                    JSONObject questionJSON = questions.optJSONObject(c);
                                    int numQuestion = questionJSON.optInt("pregunta");
                                    String descQuestion = questionJSON.optString("descripcion");
                                    String op1 = questionJSON.optString("opcion1");
                                    String op2 = questionJSON.optString("opcion2");
                                    String op3 = questionJSON.optString("opcion3");
                                    String op4 = questionJSON.optString("opcion4");
                                    String resp = questionJSON.optString("respuesta");
                                    String userResp = questionJSON.optString("usuario");

//                                    LayoutInflater inflater = LayoutInflater.from(getActivity());
//                                    LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.item_question, null, false);

                                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View layout =  inflater.inflate(R.layout.item_question, null);
                                    TextView tvNumQ = (TextView) layout.findViewById(R.id.count_question_test);
                                    TextView tvDescQ = (TextView) layout.findViewById(R.id.tv_description_question_test);

                                    tvNumQ.setText(numQuestion +"");
                                    tvDescQ.setText(descQuestion);

                                    RadioGroup rdQuestions = (RadioGroup) layout.findViewById(R.id.rg_question);
                                    rdQuestions.setTag(numQuestion);
                                    RadioButton rbQ1 = (RadioButton)rdQuestions.findViewById(R.id.rb_question_a);
                                    rbQ1.setTag("opcion1");
                                    rbQ1.setText(op1);
                                    RadioButton rbQ2 = (RadioButton)rdQuestions.findViewById(R.id.rb_question_b);
                                    rbQ2.setTag("opcion2");
                                    rbQ2.setText(op2);
                                    RadioButton rbQ3 = (RadioButton)rdQuestions.findViewById(R.id.rb_question_c);
                                    rbQ3.setTag("opcion3");
                                    rbQ3.setText(op3);
                                    RadioButton rbQ4 = (RadioButton)rdQuestions.findViewById(R.id.rb_question_d);
                                    rbQ4.setTag("opcion4");
                                    rbQ4.setText(op4);
                                    rdQuestions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                                    {
                                        public void onCheckedChanged(RadioGroup group, int checkedId)
                                        {
                                            // This will get the radiobutton that has changed in its check state
                                            RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                                            // This puts the value (true/false) into the variable
                                            boolean isChecked = checkedRadioButton.isChecked();
                                            // If the radiobutton that has changed in check state is now checked...
                                            if (isChecked)
                                            {
                                                // Changes the textview's text to "Checked: example radiobutton text"
//                                                tv.setText("Checked:" + checkedRadioButton.getText());
                                                //Num Quesiton to response
                                                int idQRg = (int) group.getTag();
                                                //user response
                                                String respUser = (String) checkedRadioButton.getTag();
                                                String testJsonStringInner = SharedPreferencesUtils.readFromPreferencesString(getActivity(),SharedPreferencesUtils.TEST_AUX_NAME+count,"{}");
                                                try {
                                                    JSONObject testJSONInner = new JSONObject(testJsonStringInner);
                                                    JSONArray lessonJSONArrayInner = testJSONInner.optJSONArray("secciones");
                                                    for (int ii = 0; ii < lessonJSONArrayInner.length(); ii++) {
                                                        JSONObject lessonObjInner = lessonJSONArrayInner.getJSONObject(ii);
                                                        int idLessonTestInner = lessonObjInner.optInt("id");
                                                        if(idLessonTestInner == idLesson) {
                                                            JSONArray questionsInner = lessonObjInner.optJSONArray("preguntas");
                                                            if (questionsInner != null) {
                                                                for (int cc = 0; cc < questionsInner.length(); cc++) {
                                                                    JSONObject questionJSONInner = questionsInner.optJSONObject(cc);
                                                                    int numQuestionInner = questionJSONInner.optInt("pregunta");
                                                                    if(numQuestionInner == idQRg){
                                                                        switch (checkedId){
                                                                            case R.id.rb_question_a:

                                                                                break;
                                                                            case R.id.rb_question_b:

                                                                                break;
                                                                            case R.id.rb_question_c:

                                                                                break;
                                                                            case R.id.rb_question_d:

                                                                                break;
                                                                        }


                                                                        questionJSONInner.put("usuario",respUser);

                                                                        SharedPreferencesUtils.saveToPreferencesString(getActivity(), SharedPreferencesUtils.TEST_AUX_NAME + count, testJSONInner.toString());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }catch (Exception ee){
                                                    ee.printStackTrace();
                                                }
                                            }
                                        }
                                    });

                                    switch (userResp){
                                        case "opcion1":
                                            rbQ1.setChecked(true);
                                            break;
                                        case "opcion2":
                                            rbQ2.setChecked(true);
                                            break;
                                        case "opcion3":
                                            rbQ3.setChecked(true);
                                            break;
                                        case "opcion4":
                                            rbQ4.setChecked(true);
                                            break;
                                    }

                                    if(isHistory){
                                        layout.setEnabled(false);
                                    }

                                    linearLayout.addView(layout);
                                }
                            }
                        }

                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  lessonVOLst;
    }

}
