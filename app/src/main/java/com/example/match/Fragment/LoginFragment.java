package com.example.match.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.match.Activity.LoginActivity;
import com.example.match.AppDataBase;
import com.example.match.Dao.UserDao;
import com.example.match.Entity.User;
import com.example.match.MainActivity;
import com.example.match.R;

/**
 * A simple {@link Fragment} subclass.
 * 登陆界面
 */
public class LoginFragment extends Fragment {
    private EditText username;
    private EditText password;
    private UserDao userDao;
    private Button login_button;
    private Button register_button;
    private View view;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_login, container, false);
        userDao=AppDataBase.instance.userDao();
        register_button=view.findViewById(R.id.register_button);
        login_button=view.findViewById(R.id.loginbutton);
        username=view.findViewById(R.id.item_username);
        password=view.findViewById(R.id.password);
        CheckAutoLogin();
        RegisterButtonListen();
        return view;
    }
    //注册按钮监听
    public void RegisterButtonListen(){
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller= Navigation.findNavController(v);
                controller.navigate(R.id.action_loginFragment_to_registerFragment);

            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(username.getText().toString(),password.getText().toString());
            }
        });
    }
    //判断是否已经登陆
    public void CheckAutoLogin(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("username")&&sharedPreferences.getBoolean("auto",false)){

            check(sharedPreferences.getString("username",""),sharedPreferences.getString("password",""));
        }

    }
    //检查账户密码
    public boolean check(String username,String password){
        if(username.isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "请输入账户", Toast.LENGTH_LONG).show();
            return false;
        }
        if(password.isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "请输入密码", Toast.LENGTH_LONG).show();
            return false;
        }
        User user= userDao.getUserByAccount(username);
        if(user==null) {
            Toast.makeText(getActivity().getApplicationContext(), "用户不存在", Toast.LENGTH_LONG).show();
            return false;
        }
        if(!user.getPassword().equals(password)){
            Toast.makeText(getActivity().getApplicationContext(), "密码错误", Toast.LENGTH_LONG).show();
            return false;
        }
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.putBoolean("auto",true);
        editor.commit();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        return true;
    }
}
