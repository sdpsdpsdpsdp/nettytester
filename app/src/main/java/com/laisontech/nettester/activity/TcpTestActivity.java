package com.laisontech.nettester.activity;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laisontech.nettester.R;
import com.laisontech.nettester.base.BaseActivity;
import com.laisontech.nettester.netty.client.NettyClient;
import com.laisontech.nettester.netty.coder.RpcRequest;
import com.laisontech.nettester.utils.ViewUtils;

import java.io.IOException;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by SDP
 * on 2020/3/16
 * Des：
 */
public class TcpTestActivity extends BaseActivity {
    @BindView(R.id.view_parentcenter)
    View viewParentcenter;
    @BindView(R.id.tv_sendarea_encode)
    TextView tvSendareaEncode;
    @BindView(R.id.tv_sendarea_clean)
    TextView tvSendareaClean;
    @BindView(R.id.tv_sendarea_paste)
    TextView tvSendareaPaste;
    @BindView(R.id.tv_sendarea_copy)
    TextView tvSendareaCopy;
    @BindView(R.id.tv_sendarea_send)
    TextView tvSendareaSend;
    @BindView(R.id.ll_send_bottommenu)
    LinearLayout tvSendBottommenu;
    @BindView(R.id.et_sendarea_send)
    EditText etSendareaSend;
    @BindView(R.id.tv_sendlength)
    TextView tvSendlength;
    @BindView(R.id.tv_receive_encode)
    TextView tvReceiveEncode;
    @BindView(R.id.tv_receive_clean)
    TextView tvReceiveClean;
    @BindView(R.id.tv_receive_switch)
    TextView tvReceiveSwitch;
    @BindView(R.id.tv_receive_copy)
    TextView tvReceiveCopy;
    @BindView(R.id.tv_receive_save)
    TextView tvReceiveSave;
    @BindView(R.id.ll_receive_bottommenu)
    LinearLayout llReceiveBottommenu;
    @BindView(R.id.tv_receivearea_receive)
    TextView tvReceiveareaReceive;
    @BindView(R.id.tv_receivearea_receivelength)
    TextView tvReceiveareaReceivelength;
    @BindView(R.id.et_host)
    EditText etHost;
    @BindView(R.id.et_port)
    EditText etPort;
    //netty客户端
    private NettyClient nettyClient;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_tcp;
    }

    @Override
    protected void initEvent() {
        ViewUtils.setOnEditTextChange(etSendareaSend, tvSendareaClean, tvSendareaCopy, tvSendlength);
        //改变剪贴板中Content
        tvSendareaPaste.setEnabled(!TextUtils.isEmpty(getClipText()));
        tvSendareaSend.setEnabled(true);
    }

    @SuppressLint("SetTextI18n")
    @OnClick({R.id.btn_connect, R.id.tv_sendarea_encode, R.id.tv_sendarea_clean, R.id.tv_sendarea_paste, R.id.tv_sendarea_copy, R.id.tv_sendarea_send, R.id.tv_receive_encode, R.id.tv_receive_clean, R.id.tv_receive_switch, R.id.tv_receive_copy, R.id.tv_receive_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_connect:
                connectNetty();
                break;
            case R.id.tv_sendarea_encode:
                break;
            case R.id.tv_sendarea_clean:
                etSendareaSend.setText("");
                break;
            case R.id.tv_sendarea_paste:
                String oldChart = etSendareaSend.getText().toString();
                etSendareaSend.setText(oldChart + getClipText().toString());
                etSendareaSend.setSelection(etSendareaSend.getText().length());
                break;
            case R.id.tv_sendarea_copy:
                ViewUtils.clipText(this, etSendareaSend.getText());
                break;
            case R.id.tv_sendarea_send:
                //发送数据
                sendArea();
                break;
            case R.id.tv_receive_encode:
                break;
            case R.id.tv_receive_clean:
                break;
            case R.id.tv_receive_switch:
                break;
            case R.id.tv_receive_copy:
                break;
            case R.id.tv_receive_save:
                break;
        }
    }

    private void connectNetty() {
        String host = etHost.getText().toString().trim();
        String port = etPort.getText().toString().trim();
        if (TextUtils.isEmpty(host) || TextUtils.isEmpty(port)) {
            return;
        }
        if (nettyClient == null) {
            nettyClient = new NettyClient(host, Integer.parseInt(port));
        }
        try {
            nettyClient.connectServer();
        } catch (IOException e) {
            e.printStackTrace();
            showToast(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendArea() {
        //第二步判断是否输入了内容
        if (TextUtils.isEmpty(etSendareaSend.getText())) {
            showToast("请输入发送的数据");
            return;
        }
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setId(UUID.randomUUID().toString());
        rpcRequest.setData(etSendareaSend.getText().toString());
        nettyClient.sendMessage(rpcRequest);

    }

    private CharSequence getClipText() {
        return ViewUtils.getClipText(this);
    }
}
