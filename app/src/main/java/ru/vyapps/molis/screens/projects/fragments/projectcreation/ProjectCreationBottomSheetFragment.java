package ru.vyapps.molis.screens.projects.fragments.projectcreation;

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

public class ProjectCreationBottomSheetFragment extends BottomSheetDialogFragment {

    public static final String TAG = "ProjectCreationBottomSheet";

    private ProjectCreationViewModel viewModel;

    private EditText editTextProjectName;
    private Button buttonCreateProject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return inflater.inflate(R.layout.bottom_sheet_project_creation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ProjectCreationViewModel(getActivity().getApplication());

        editTextProjectName = view.findViewById(R.id.editTextProjectName);
        buttonCreateProject = view.findViewById(R.id.buttonCreateProject);

        editTextProjectName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.projectNameChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        viewModel.isProjectCreationButtonEnable().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                buttonCreateProject.setEnabled(aBoolean);
            }
        });
    }
}
