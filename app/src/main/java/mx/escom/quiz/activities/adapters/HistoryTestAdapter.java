package mx.escom.quiz.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.escom.quiz.R;
import mx.escom.quiz.activities.adapters.adapterVO.HistoryTestVO;

public class HistoryTestAdapter extends ArrayAdapter<HistoryTestVO> {
    private List<HistoryTestVO> items = new ArrayList<>();
    private Context contexto;

    public HistoryTestAdapter(Context context, int textViewResourceId, List<HistoryTestVO> data) {
        super(context,textViewResourceId,data);
        this.contexto = context;
        this.items = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemHistoryTestView = convertView;
        if (itemHistoryTestView == null) {
            LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemHistoryTestView = inflater.inflate(R.layout.item_history_test, null);
        }
        HistoryTestVO currentItem = items.get(position);
        if (currentItem != null) {
            TextView tvDateTest = (TextView) itemHistoryTestView.findViewById(R.id.tv_date_history_test);
            TextView tvResult = (TextView) itemHistoryTestView.findViewById(R.id.tv_result_history_test);
            TextView tvCorrects = (TextView) itemHistoryTestView.findViewById(R.id.tv_correct_history_test);
            TextView tvIncorrects = (TextView) itemHistoryTestView.findViewById(R.id.tv_incorrect_history_test);
            if(tvDateTest != null){
                tvDateTest.setText(currentItem.getDate());
            }
            if(tvResult != null){
                tvResult.setText(currentItem.getResult()+"");
            }
            if(tvCorrects != null){
                tvCorrects.setText(currentItem.getCorrect()+"");
            }
            if(tvIncorrects != null){
                tvIncorrects.setText(currentItem.getIncorrect()+"");
            }
        }
        return itemHistoryTestView;
    }

//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemDrawerView = inflater.inflate(R.layout.item_menu_drawer, parent,false);
//        MyViewHolder holder = new MyViewHolder(itemDrawerView);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        DrawerMenuItem currentObject = items.get(position);
//        holder.descTextView.setText(currentObject.getTitle());
//        holder.imageView.setImageResource(currentObject.getIconId());
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView descTextView;
//        ImageView imageView;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            descTextView = (TextView) itemView.findViewById(R.id.d_item_text);
//            imageView = (ImageView) itemView.findViewById(R.id.d_item_image);
//        }
//    }
}
