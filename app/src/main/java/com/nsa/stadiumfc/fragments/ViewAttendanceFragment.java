package com.nsa.stadiumfc.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nsa.stadiumfc.R;
import com.nsa.stadiumfc.adapters.AttendanceAdapters;
import com.nsa.stadiumfc.databinding.FragmentTakeAttendanceBinding;
import com.nsa.stadiumfc.databinding.FragmentViewAttendanceBinding;
import com.nsa.stadiumfc.viewmodels.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewAttendanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewAttendanceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewAttendanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewAttendanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewAttendanceFragment newInstance(String param1, String param2) {
        ViewAttendanceFragment fragment = new ViewAttendanceFragment();
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

    private FragmentViewAttendanceBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentViewAttendanceBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getAttendance().observe(getViewLifecycleOwner(),list->{
            if(list==null){
                Toast.makeText(getContext(), "No Previous Attendance", Toast.LENGTH_SHORT).show();
            }else{
                if(list.size()==0){
                    Toast.makeText(getContext(), "No Previous Attendance", Toast.LENGTH_SHORT).show();
                }else{
                    binding.recyclerview.setAdapter(new AttendanceAdapters(list));
                }
            }
        });
    }
}