package mx.escom.quiz.activities.adapters.adapterVO;

import org.json.JSONArray;

/**
 * Created by Desarrollo on 31/10/2017.
 */

public class HistoryTestVO {
    int idLesson;
    String name;
    boolean active;
    JSONArray questions;
    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public JSONArray getQuestions() {
        return questions;
    }

    public void setQuestions(JSONArray questions) {
        this.questions = questions;
    }

}
