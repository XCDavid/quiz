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
import mx.escom.quiz.activities.adapters.adapterVO.LessonVO;

public class LessonsAdapter extends ArrayAdapter<LessonVO> {
    private List<LessonVO> items = new ArrayList<>();
    private Context contexto;

    public LessonsAdapter(Context context, int textViewResourceId, List<LessonVO> data) {
        super(context,textViewResourceId,data);
        this.contexto = context;
        this.items = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemMenuView = convertView;
        if (itemMenuView == null) {
            LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemMenuView = inflater.inflate(R.layout.item_lesson_test, null);
        }
        LessonVO currentItem = items.get(position);
        if (currentItem != null) {
            TextView tvItemEnrolmentDn = (TextView) itemMenuView.findViewById(R.id.tv_lesson_name_test);
            ImageView ivTextureTransparent = (ImageView) itemMenuView.findViewById(R.id.iv_texture_trasnparent);
            if(tvItemEnrolmentDn != null){
                tvItemEnrolmentDn.setText(currentItem.getName());
            }
            if (!currentItem.isActive()){
                itemMenuView.setVisibility(View.GONE);
            }
        }
        return itemMenuView;
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
