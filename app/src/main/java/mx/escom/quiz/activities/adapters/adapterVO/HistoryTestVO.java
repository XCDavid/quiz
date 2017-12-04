package mx.escom.quiz.activities.adapters.adapterVO;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Desarrollo on 31/10/2017.
 */

public class HistoryTestVO {
    int idTest;
    String date;
    int correct;
    int incorrect;
    double result;
    List<LessonVO> lessonVOList;

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public List<LessonVO> getLessonVOList() {
        return lessonVOList;
    }

    public void setLessonVOList(List<LessonVO> lessonVOList) {
        this.lessonVOList = lessonVOList;
    }

}
