package mx.escom.quiz.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import mx.escom.quiz.R;

public class LessonsFragment extends Fragment {
    ListView lvLessons;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lessons, container, false);
        lvLessons = (ListView) layout.findViewById(R.id.lv_lessons);


        return layout;
    }

}
