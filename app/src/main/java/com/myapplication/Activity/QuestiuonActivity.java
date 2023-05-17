package com.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.myapplication.Adapter.QuestionAdapter;
import com.myapplication.model.Question;
import com.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class QuestiuonActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private List<Question> questionList;
    private int currentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questiuon);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create list of questions
        questionList = new ArrayList<>();
        questionList.add(new Question("Question 1"));
        questionList.add(new Question("Question 2"));
        questionList.add(new Question("Question 3"));
        questionList.add(new Question("Question 4"));
        questionList.add(new Question("Question 5"));

        // Initialize adapter with first question
        currentQuestionIndex = 0;
        adapter = new QuestionAdapter(questionList.get(currentQuestionIndex),questionList,currentQuestionIndex);
        recyclerView.setAdapter(adapter);
    }

    // Move to next question when Next button is clicked
    public void onNextClicked(View view) {
        if (currentQuestionIndex < questionList.size() - 1) {
            currentQuestionIndex++;
            adapter.setCurrentQuestion(questionList.get(currentQuestionIndex), currentQuestionIndex);
        }
    }
    public void onPreviousClicked(View view) {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            adapter.setCurrentQuestion(questionList.get(currentQuestionIndex), currentQuestionIndex);
        }
    }

}
