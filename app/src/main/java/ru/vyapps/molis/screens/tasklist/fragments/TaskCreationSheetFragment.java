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
import androidx.lifecycle.Observer;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ru.vyapps.molis.R;

public class TaskCreationSheetFragment extends BottomSheetDialogFragment {

    private EditText editTextTaskName;
    private Button buttonCreateTask;

    private TaskCreationViewModel viewModel;

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

        viewModel = new TaskCreationViewModel(getActivity().getApplication());
        viewModel.getCreateTaskButtonEnabled().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                buttonCreateTask.setEnabled(aBoolean);
            }
        });

        editTextTaskName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.taskNameChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = editTextTaskName.getText().toString().trim();
                viewModel.createTask(taskName);
                dismiss();
            }
        });
    }
}
