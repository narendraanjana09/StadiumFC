package com.nsa.stadiumfc.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.nsa.stadiumfc.R;
import com.nsa.stadiumfc.databinding.FragmentMainBinding;
import com.nsa.stadiumfc.databinding.FragmentTakeAttendanceBinding;
import com.nsa.stadiumfc.models.AttendanceModel;
import com.nsa.stadiumfc.models.UserModel;
import com.nsa.stadiumfc.viewmodels.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TakeAttendanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TakeAttendanceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TakeAttendanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TakeAttendanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TakeAttendanceFragment newInstance(String param1, String param2) {
        TakeAttendanceFragment fragment = new TakeAttendanceFragment();
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

    private FragmentTakeAttendanceBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentTakeAttendanceBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        binding.cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(userModel==null){
                  Toast.makeText(getContext(), "Please Create Profile First!", Toast.LENGTH_SHORT).show();
                  navController.navigate(R.id.profileFragment);
              }else{
                  ImagePicker.with(getActivity())
                          .cropSquare()
                          .cameraOnly()//Crop image(Optional), Check Customization for more option
                          .compress(1024)            //Final image size will be less than 1 MB(Optional)
                          .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                          .createIntent(new Function1<Intent, Unit>() {
                              @Override
                              public Unit invoke(Intent intent) {
                                  takePicture.launch(intent);
                                  return null;
                              }
                          });
              }
            }
        });
        binding.rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.imageView.setImageDrawable(getResources().getDrawable(R.drawable.user));
            }
        });
        viewModel.getUser().observe(getViewLifecycleOwner(),model->{
           this.userModel=model;
        });
        binding.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri==null){
                    Toast.makeText(getContext(), "Please Take a photo first!", Toast.LENGTH_SHORT).show();
                }else{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
                    Date date = new Date();

                    AttendanceModel attendanceModel=new AttendanceModel(0,userModel.getName(),
                        dateFormat.format(date),timeFormat.format(date),imageUri.toString());
                    imageUri=null;
                    viewModel.addAttendance(attendanceModel);
                    Toast.makeText(getContext(), "Attendance Taken Successfully", Toast.LENGTH_SHORT).show();
                    binding.imageView.setImageDrawable(getResources().getDrawable(R.drawable.user));

                }
            }
        });
    }
    private UserModel userModel;
    private Uri imageUri=null;
    ActivityResultLauncher<Intent> takePicture = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                         imageUri = result.getData().getData();
                        binding.imageView.setImageURI(imageUri);


                        // Handle the Intent
                    }
                }
            });
}