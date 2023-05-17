package com.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.Activity.QuestiuonActivity;
import com.myapplication.R;
import com.myapplication.model.Question;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyHolder> {

    private Question currentQuestion;

    List<Question> questionList;
    int currentQuestionIndex;

    public QuestionAdapter(Question currentQuestion, List<Question> questionList, int currentQuestionIndex) {
        this.currentQuestion = currentQuestion;
        this.questionList = questionList;
        this.currentQuestionIndex = currentQuestionIndex;
    }

    @NonNull
    @Override
    public QuestionAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_question, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.MyHolder holder, int position) {
        holder.questionTextView.setText(currentQuestion.getText());


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void setCurrentQuestion(Question question, int currentQuestionIndex) {
        this.currentQuestion = question;
        this.currentQuestionIndex = currentQuestionIndex;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView questionTextView;
        public Button nextButton, previousButton;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            nextButton = itemView.findViewById(R.id.nextButton);
            previousButton = itemView.findViewById(R.id.previousButton);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    QuestiuonActivity activity = (QuestiuonActivity) itemView.getContext();
                    activity.onNextClicked(view);

                }
            });

            previousButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    QuestiuonActivity activity = (QuestiuonActivity) itemView.getContext();
                    activity.onPreviousClicked(view);

                }
            });
        }
    }
}
