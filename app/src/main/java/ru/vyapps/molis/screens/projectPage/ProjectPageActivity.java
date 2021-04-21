package ru.vyapps.molis.screens.projectPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import ru.vyapps.molis.R;
import ru.vyapps.molis.models.pojo.Task;
import ru.vyapps.molis.screens.projectPage.adapters.task.TaskTouchHelperCallback;
import ru.vyapps.molis.screens.projectPage.adapters.task.TaskAdapter;
import ru.vyapps.molis.screens.projectPage.fragments.TaskCreationSheetFragment;

public class ProjectPageActivity extends AppCompatActivity  {

    private String projectName;

    private ProjectPageViewModel viewModel;

    private RecyclerView recyclerViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_page);

        projectName = getIntent().getStringExtra("projectName");
        TextView textViewTitle = findViewById(R.id.projectPage_title);
        textViewTitle.setText(projectName);

        Toolbar toolbar = findViewById(R.id.projectPage_topAppBar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        recyclerViewTasks = findViewById(R.id.projectPage_recyclerViewTasks);

        viewModel = new ProjectPageViewModel(getApplication());
        viewModel.getTasks(projectName).observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                TaskAdapter taskAdapter = new TaskAdapter(getApplicationContext(), tasks);
                recyclerViewTasks.setAdapter(taskAdapter);
                recyclerViewTasks.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

                TaskTouchHelperCallback callback = new TaskTouchHelperCallback(taskAdapter);
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
                itemTouchHelper.attachToRecyclerView(recyclerViewTasks);

                taskAdapter.setOnTaskClickListener(new TaskAdapter.onTaskClickListener() {
                    @Override
                    public void onClick(CheckedTextView checkedTextView) {
                        checkedTextView.setChecked(!checkedTextView.isChecked());

                        if (checkedTextView.isChecked()) {
                            checkedTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                            checkedTextView.setTextColor(getResources().getColor(R.color.reply_blue_800));
                        } else {
                            checkedTextView.setPaintFlags(0);
                            checkedTextView.setTextColor(getResources().getColor(R.color.black));
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_project_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
            return true;
        } else if (itemId == R.id.action_settings) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void onFABClick(View view) {
        TaskCreationSheetFragment taskCreationSheetFragment = new TaskCreationSheetFragment(projectName);
        taskCreationSheetFragment.show(getSupportFragmentManager(), "TaskCreationSheet");
    }
}