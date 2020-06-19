package com.example.match.Fragment;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.match.AppDataBase;
import com.example.match.Dao.UserDao;
import com.example.match.Entity.User;
import com.example.match.Entity.UserInfo;
import com.example.match.R;
import com.example.match.Tool.ImageTool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * 注册界面
 */
public class RegisterFragment extends Fragment {
    private EditText account;
    private EditText password;
    private EditText name;
    private EditText email;
    private Button button;
    private UserDao userDao;
    public RegisterFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_register, container, false);
        userDao= AppDataBase.instance.userDao();
        account = view.findViewById(R.id.item_username);
        password = view.findViewById(R.id.password);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        button = view.findViewById(R.id.registerbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()){
                    User user = new User();
                    user.setAccount(account.getText().toString());
                    user.setName(name.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());
                    Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.header);
                    user.setHead(ImageTool.ImageToByte(bitmap));
                    userDao.addUser(user);
                    user=AppDataBase.instance.userDao().getUserByAccount(account.getText().toString());
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUser_id(user.getUser_id());
                    userInfo.setUserName(user.getName());
                    userInfo.setAdd("未设置");
                    userInfo.setAge("未设置");
                    userInfo.setSex("未设置");
                    userInfo.setBirthday("未设置");
                    AppDataBase.instance.userInfoDao().addUserInfo(userInfo);
                    Toast.makeText(getActivity().getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
    //检测输入是否合法
    public boolean check(){
        if(CheckChar(account.getText().toString())){
            Toast.makeText(getActivity().getApplicationContext(),"账户名包含非法字符",Toast.LENGTH_LONG).show();
            return false;
        }
        if(account.getText().length()<6||account.getText().length()>16){
            Toast.makeText(getActivity().getApplicationContext(),"账户名长度必须为6-16",Toast.LENGTH_LONG).show();
            return false;
        }
        if(userDao.getUserByAccount(account.getText().toString())!=null){
            Toast.makeText(getActivity().getApplicationContext(),"该账户已经存在",Toast.LENGTH_LONG).show();
            return false;
        }
        if(password.getText().length()<6||password.getText().length()>16){
            Toast.makeText(getActivity().getApplicationContext(),"密码长度必须为6-16",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!checkEmail(email.getText().toString())){
            Toast.makeText(getActivity().getApplicationContext(),"请输入正确的邮箱格式",Toast.LENGTH_LONG).show();
            return false;
        }
            return true;
    }
    //检测非法字符
    public boolean CheckChar(String text){
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        return m.find();
    }
    public static boolean checkEmail(String email) {
        String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
        return email.matches(regex);
    }
}
