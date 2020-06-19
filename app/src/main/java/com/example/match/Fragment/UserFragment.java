package com.example.match.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.match.Activity.CourseTableActivity;
import com.example.match.Activity.LoginActivity;
import com.example.match.Activity.StudyPlanActivity;
import com.example.match.Activity.UserInfoActivity;
import com.example.match.AppDataBase;
import com.example.match.Entity.User;
import com.example.match.R;
import com.example.match.Tool.ImageTool;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URL;
import java.sql.Blob;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    ImageView change_head;
    Button my_info;
    Button my_class;
    Button my_plan;
    Button exit_button;
    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user, container, false);
        change_head= view.findViewById(R.id.change_head);
        exit_button = view.findViewById(R.id.exit_login);
        my_class=view.findViewById(R.id.my_class);
        my_plan=view.findViewById(R.id.my_plan);
        my_info=view.findViewById(R.id.my_info);
        change_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);  //跳转到 ACTION_IMAGE_CAPTURE
                intent.setType("image/*");
                startActivityForResult(intent,2);
            }
        });
        TextView username = view.findViewById(R.id.username);
        ImageView head = view.findViewById(R.id.change_head);
        User user = AppDataBase.instance.userDao().getUserByAccount(getActivity().getSharedPreferences("user",Context.MODE_PRIVATE).getString("username",""));
        username.setText(user.getName());
        head.setImageBitmap(ImageTool.ByteToBimMap(user.getHead()));
         RegisterListener();
        return view;
    }

     public void RegisterListener(){
         exit_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 getActivity().getSharedPreferences("user",Context.MODE_PRIVATE).edit().remove("username").apply();
                 Intent intent = new Intent(getActivity(), LoginActivity.class);
                 startActivity(intent);
             }
         });
         my_class.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), CourseTableActivity.class);
                 startActivity(intent);
             }
         });
         my_plan.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), StudyPlanActivity.class);
                 startActivity(intent);
             }
         });
         my_info.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                 startActivity(intent);
             }
         });
     }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode!=0){
            switch (requestCode){
                case 2://相册
                    Uri uri = data.getData();
                    if(uri==null)break;
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(uri));
                        change_head.setImageBitmap(bitmap);
                        User user = AppDataBase.instance.userDao().getUserByAccount(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("username",""));
                        AppDataBase.instance.userDao().changeHead(user.getUser_id(),ImageTool.ImageToByte(bitmap));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;


            }
        }
    }
}
