package com.laisontech.nettester.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.laisontech.nettester.R;
import com.laisontech.nettester.activity.TcpTestActivity;

import org.w3c.dom.Text;

/**
 * Created by SDP
 * on 2020/3/16
 * Des：
 */
public class ViewUtils {
    public static void setOnEditTextChange(EditText editText,
                                           final TextView tvSendareaClean,
                                           final TextView tvSendareaCopy,
                                           final TextView tvSendlength) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    tvSendareaClean.setEnabled(true);
                    tvSendareaCopy.setEnabled(true);
                    tvSendlength.setText(s.length() + "/10000");
                } else {
                    tvSendareaClean.setEnabled(false);
                    tvSendareaCopy.setEnabled(false);
                    tvSendlength.setText("0/10000");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static CharSequence getClipText(Context context) {
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clip != null) {
            return clip.getText();
        }
        return "";
    }

    public static void clipText(Context context, CharSequence text) {
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clip != null) {
            clip.setText(text);
        }
    }
}
