package com.example.ailatrieuphu.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> mListUser;
    private IClickItemUser iClickItemUser;

    public interface IClickItemUser{
        void updateUser(User user);
    }

    public void setData(List<User> list) {
        this.mListUser = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent,false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        User user = mListUser.get(position);
        if(user == null){
            return;
        }

        holder.tvUserName.setText(user.getUsername());
        holder.tvScore.setText(user.getScore());

    }

    @Override
    public int getItemCount() {
        if (mListUser != null){
            return mListUser.size();
        }
            return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUserName;
        private TextView tvScore;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tv_user_name);
            tvScore = itemView.findViewById(R.id.tv_score);

        }
    }
}
