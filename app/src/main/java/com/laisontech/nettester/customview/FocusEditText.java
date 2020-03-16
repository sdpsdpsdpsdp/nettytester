package com.laisontech.nettester.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by SDP
 * on 2020/3/16
 * Des：
 */
public class FocusEditText extends android.support.v7.widget.AppCompatEditText{
    public FocusEditText(Context context) {
        super(context);
    }

    public FocusEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
