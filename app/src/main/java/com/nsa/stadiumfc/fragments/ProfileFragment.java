package com.nsa.stadiumfc.fragments;

import static com.nsa.stadiumfc.extra.Constants.EDIT;
import static com.nsa.stadiumfc.extra.Constants.SAVE;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.nsa.stadiumfc.R;
import com.nsa.stadiumfc.databinding.FragmentProfileBinding;
import com.nsa.stadiumfc.extra.Keyboard;
import com.nsa.stadiumfc.models.UserModel;
import com.nsa.stadiumfc.viewmodels.MainViewModel;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private MainViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        viewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())
                .create(MainViewModel.class);
    }

    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.editSaveBtn.setText(EDIT);
        binding.txt1.setText(Html.fromHtml("<u>Profile</u>"));
        binding.editSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.editSaveBtn.getText().toString().equals(EDIT)){
                    binding.editSaveBtn.setText(SAVE);
                binding.nameEd.setEnabled(true);
                binding.dobEd.setEnabled(true);
                binding.emailEd.setEnabled(true);
                }else{
                    UserModel userModel=new UserModel(0,binding.nameEd.getText().toString().trim()
                    ,binding.dobEd.getText().toString().trim(),binding.emailEd.getText().toString().trim());
                   if(checkData(userModel)){
                       if(isNew){
                           viewModel.saveUser(userModel);
                           Toast.makeText(getContext(), "Profile Saved", Toast.LENGTH_SHORT).show();
                       }else{
                           Toast.makeText(getContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                           viewModel.update(userModel);
                       }
                       binding.nameEd.setEnabled(false);
                       binding.dobEd.setEnabled(false);
                       binding.emailEd.setEnabled(false);
                       binding.editSaveBtn.setText(EDIT);
                   }else{
                       Toast.makeText(getContext(), "please enter correct info!", Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
        binding.dobEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Keyboard.hide(view);
                getDOB();
            }
        });
        viewModel.getUser().observe(getViewLifecycleOwner(),model->{
            if(model!=null){
                isNew=false;
                binding.nameEd.setText(model.getName());
                binding.dobEd.setText(model.getDob());
                binding.emailEd.setText(model.getEmail());
            }else{
                isNew=true;
            }
        });
    }
    boolean isNew=true;

    private boolean checkData(UserModel userModel) {
        if(userModel.getName().isEmpty() || userModel.getDob().isEmpty() || userModel.getEmail().isEmpty()){
            return false;
        }
        return true;
    }

    private void getDOB() {
        final Calendar c = Calendar.getInstance();
       int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        binding.dobEd.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        datePickerDialog.show();
    }
}