package com.mrym.newsbulletion.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.feedback.FeedBackPresenter;
import com.mrym.newsbulletion.mvp.activity.feedback.FeedBackView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：SigmaClassBoard
 * 类描述：
 * 创建人：jianz
 * 创建时间：2017/1/23 21:16
 * 修改备注：
 */
public class FeedBackActivity extends MvpActivity<FeedBackPresenter> implements FeedBackView {
    @Bind(R.id.left_back_title)
    TextView leftBackTitle;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.leftback_toobar_l1)
    RelativeLayout leftbackToobarL1;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.header)
    LinearLayout header;
    @Bind(R.id.et_feedback_mag)
    EditText etFeedbackMag;
    @Bind(R.id.img_grid)
    GridView imgGrid;
    @Bind(R.id.img_horizscroll)
    HorizontalScrollView imgHorizscroll;
    @Bind(R.id.release_num_tx)
    TextView releaseNumTx;
    @Bind(R.id.bt_feedback)
    Button btFeedback;
    private CharSequence temp;
    private int selectionStart;
    private int selectionEnd;
    private int num = 500;
    @Override
    protected FeedBackPresenter createPresenter() {
        return new FeedBackPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        initView();
    }


    public void initView(){
        dynamicAddView(header, "background", R.color.primary_dark);
        leftBackTitle.setText("反馈");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etFeedbackMag.addTextChangedListener(textWatcher.get());
    }
    private final ThreadLocal<TextWatcher> textWatcher = new ThreadLocal<TextWatcher>() {
        @Override
        protected TextWatcher initialValue() {
            return new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    temp = s;
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    releaseNumTx.setText(temp.length() + "/500");
                    selectionStart = etFeedbackMag.getSelectionStart();
                    selectionEnd = etFeedbackMag.getSelectionEnd();
                    if (temp.length() > num) {
                        editable.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionEnd;
                        etFeedbackMag.setText(editable);
                        etFeedbackMag.setSelection(tempSelection);// 设置光标在最后
                    }
                }
            };
        }
    };
    @Override
    protected String getTag() {
        return null;
    }
}
