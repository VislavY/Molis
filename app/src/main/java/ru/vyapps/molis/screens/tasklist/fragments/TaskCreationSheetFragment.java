package ru.vyapps.molis.screens.tasklist.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import ru.vyapps.molis.R;
import ru.vyapps.molis.screens.tasklist.TaskListActivity;
import ru.vyapps.molis.screens.tasklist.TaskListContract;

public class TaskCreationSheetFragment extends BottomSheetDialogFragment {

    private EditText editTextTaskName;
    private Button buttonCreateTask;

    private TaskListContract.View mainView;

    public TaskCreationSheetFragment(TaskListContract.View mainView) {
        this.mainView = mainView;
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

        editTextTaskName = view.findViewById(R.id.editTextTaskName);
        buttonCreateTask = view.findViewById(R.id.buttonCreateTask);

        editTextTaskName.addTextChangedListener((TextWatcher) mainView);
    }

    public EditText getEditTextTaskName() {
        return editTextTaskName;
    }

    public Button getButtonCreateTask() {
        return buttonCreateTask;
    }
}
