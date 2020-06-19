package com.example.match.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.match.Adapter.CommentListAdapter;
import com.example.match.AppDataBase;
import com.example.match.Entity.Comment;
import com.example.match.R;
import com.google.android.material.math.MathUtils;

import java.util.ArrayList;
import java.util.Iterator;

public class ReplayListDialog extends DialogFragment {
    private int article_id;
    private int up_id;
    private ListView listView;
    private View view;
    private int comment_id;
    public ReplayListDialog(int article_id, int user_id,int comment_id) {
        this.article_id = article_id;
        this.up_id = user_id;
        this.comment_id=comment_id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        view = View.inflate(getContext(),R.layout.replay_list,null);
        dialog.setContentView(view);
        listView = view.findViewById(R.id.replay_list);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.dimAmount = 0.5f;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height= 2300;
        window.getDecorView().setPadding(0,0,0,0);
        window.getDecorView().setBackgroundColor(Color.WHITE);
        window.setAttributes(lp);
        RegisterListener();
        loadReply();
        return dialog;
    }
    public void RegisterListener(){
        ImageButton back = view.findViewById(R.id.reply_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getDialog().cancel();
            }
        });
    }
    public void loadReply(){
        ArrayList<Comment> comments = (ArrayList<Comment>) AppDataBase.instance.commentDao().getAllReply(article_id,up_id,false,comment_id);
        listView.setAdapter(new CommentListAdapter(comments,getActivity(),false));
    }
}
