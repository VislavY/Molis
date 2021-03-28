package ru.vyapps.molis.screens.createtaskscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ru.vyapps.molis.R;
import ru.vyapps.molis.screens.tasklistscreen.TaskListPresenter;

public class TaskCreationSheet extends BottomSheetDialogFragment {

    private EditText editTextTaskName;
    private Button buttonCreateTask;

    private TaskListPresenter presenter;

    public TaskCreationSheet(TaskListPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        View view = inflater.inflate(R.layout.sheet_task_creation, container, false);
        editTextTaskName = view.findViewById(R.id.editTextTaskName);
        buttonCreateTask = view.findViewById(R.id.buttonCreateTask);

        buttonCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskName = editTextTaskName.getText().toString().trim();
                presenter.createTask(taskName);

                dismiss();
            }
        });

        return view;
    }
}
