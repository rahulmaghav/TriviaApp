package com.example.triviaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triviaapp.R;
import com.example.triviaapp.model.UserDataModel;

import java.util.ArrayList;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<UserDataModel> mUserDataModelArrayList;

    public HistoryRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        mUserDataModelArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.item_history,parent,false);

        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        HistoryViewHolder historyViewHolder = (HistoryViewHolder) holder;
        if(mUserDataModelArrayList != null)
        {
            UserDataModel userDataModel = mUserDataModelArrayList.get(position);
            if(userDataModel != null)
            {
                historyViewHolder.tvGameNumber.setText(mContext.getString(R.string.game) + " " + userDataModel.getUser_id() + ":");
                historyViewHolder.tvSubmittedOn.setText(userDataModel.getSubmitted_on());

                historyViewHolder.tvName.setText(userDataModel.getAnswerModelList().get(0).getAnswer());
                historyViewHolder.tvQues1.setText(userDataModel.getAnswerModelList().get(1).getQuestion_name());
                historyViewHolder.tvAnswer1.setText(userDataModel.getAnswerModelList().get(1).getAnswer());
                historyViewHolder.tvQues2.setText(userDataModel.getAnswerModelList().get(2).getQuestion_name());
                historyViewHolder.tvAnswer2.setText(userDataModel.getAnswerModelList().get(2).getAnswer());
            }
        }

    }

    @Override
    public int getItemCount() {
        return mUserDataModelArrayList.size();
    }

    public void onDataChanged(ArrayList<UserDataModel> updatedList)
    {
        if(updatedList != null)
        {
            mUserDataModelArrayList.clear();
            mUserDataModelArrayList.addAll(updatedList);
            notifyDataSetChanged();
        }
    }



    public class HistoryViewHolder extends RecyclerView.ViewHolder{

        private TextView tvGameNumber,tvSubmittedOn,tvName,tvQues1,tvAnswer1,tvQues2,tvAnswer2;


        public HistoryViewHolder(View itemView) {
            super(itemView);

            tvGameNumber=itemView.findViewById(R.id.tv_game_number);
            tvSubmittedOn=itemView.findViewById(R.id.tv_submitted_on);
            tvName=itemView.findViewById(R.id.tv_name);
            tvQues1=itemView.findViewById(R.id.tv_question1);
            tvAnswer1 =itemView.findViewById(R.id.tv_answer1);
            tvQues2 =itemView.findViewById(R.id.tv_question2);
            tvAnswer2 =itemView.findViewById(R.id.tv_answer2);

        }

    }
}
