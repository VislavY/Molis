package ru.vyapps.molis.screens.tasklist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import ru.vyapps.molis.R;
import ru.vyapps.molis.screens.tasklist.TaskListContract;

public class TaskCreationSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private TextInputLayout textInputLayoutTaskName;
    private TextInputEditText textInputEditTextTaskName;
    private Button buttonCreateTask;

    private TaskListContract.Presenter presenter;

    public TaskCreationSheetFragment(TaskListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return inflater.inflate(R.layout.sheet_task_creation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textInputLayoutTaskName = view.findViewById(R.id.textInputLayoutTaskName);
        textInputEditTextTaskName = view.findViewById(R.id.textInputEditTextTaskName);
        buttonCreateTask = view.findViewById(R.id.buttonCreateTask);

        buttonCreateTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String taskName = textInputEditTextTaskName.getText().toString().trim();
        presenter.createTask(taskName);
        dismiss();
    }
}
