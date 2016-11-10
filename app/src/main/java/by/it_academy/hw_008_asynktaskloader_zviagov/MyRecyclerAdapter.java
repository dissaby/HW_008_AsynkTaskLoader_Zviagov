package by.it_academy.hw_008_asynktaskloader_zviagov;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;


import java.util.ArrayList;

/**
 * Created by dissa on 14.09.2016.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.RecyclerViewHolder> {
    private ArrayList<StudentItem> studentsList = new ArrayList<>();


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ColorGenerator generator = ColorGenerator.MATERIAL;
        String letter = String.valueOf(studentsList.get(position).getFirstName().charAt(0));
        TextDrawable drawable = TextDrawable.builder().buildRect(letter, generator.getRandomColor());
        holder.imageView.setImageDrawable(drawable);
        holder.firstNameView.setText(studentsList.get(position).getFirstName());
        holder.lastNameView.setText(studentsList.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public void setData(ArrayList<StudentItem> data) {
        studentsList.clear();
        studentsList.addAll(data);
        notifyDataSetChanged();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView firstNameView;
        final TextView lastNameView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            firstNameView = (TextView) itemView.findViewById(R.id.first_name);
            lastNameView = (TextView) itemView.findViewById(R.id.last_name);
        }
    }
}
