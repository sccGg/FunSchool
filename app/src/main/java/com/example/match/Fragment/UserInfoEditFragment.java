package com.example.match.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.match.AppDataBase;
import com.example.match.Dao.UserInfoDao;
import com.example.match.Entity.UserInfo;
import com.example.match.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoEditFragment extends Fragment {

    private EditText updateName;
    private EditText updateSex;
    private EditText updateAge;
    private EditText updateBirth;
    private EditText updateAdd;

    private Button button;
    private UserInfoDao userInfoDao;



    public UserInfoEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info_edit, container, false);

        updateName = view.findViewById(R.id.edit1);
        updateSex = view.findViewById(R.id.edit2);
        updateAge = view.findViewById(R.id.edit3);
        updateBirth = view.findViewById(R.id.edit4);
        updateAdd = view.findViewById(R.id.edit5);

        button = view.findViewById(R.id.button);
        userInfoDao = AppDataBase.instance.userInfoDao();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()){
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUserName(updateName.getText().toString());
                    userInfo.setSex(updateSex.getText().toString());
                    userInfo.setAge(updateAge.getText().toString());
                    userInfo.setBirthday(updateAge.getText().toString());
                    userInfo.setAdd(updateAdd.getText().toString());
                    userInfoDao.updateUserInfo(userInfo);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public boolean check(){
        if (CheckChar(updateAdd.getText().toString())){
            Toast.makeText(getActivity().getApplicationContext(),"地址包含非法字符",Toast.LENGTH_LONG).show();
            return false;
        }

        if (checkSex(updateSex.getText().toString())){
            Toast.makeText(getActivity().getApplicationContext(),"性别不合法，请输入男或女",Toast.LENGTH_LONG).show();
            return false;
        }

        if (!checkDate(updateBirth.getText().toString())){
            Toast.makeText(getActivity().getApplicationContext(),"日期不合法",Toast.LENGTH_LONG).show();
            return false;
        }

        if (Integer.parseInt(updateAge.getText().toString())<0||Integer.parseInt(updateAge.getText().toString())>100){
            Toast.makeText(getActivity().getApplicationContext(),"年龄不合法",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }




    public boolean CheckChar(String text){
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        return m.find();
    }

    public boolean checkSex(String sex){
        if ("男".equals(sex)||"女".equals(sex)){
            return false;
        }
        return true;
    }


    public boolean checkDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            Date date = format.parse(strDate);
            String[] sArray = strDate.split("-");
            for (String s : sArray) {
                boolean isNum = s.matches("[0-9]+");
                if (!isNum) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
