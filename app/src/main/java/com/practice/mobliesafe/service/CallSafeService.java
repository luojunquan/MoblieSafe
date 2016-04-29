package com.practice.mobliesafe.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.SmsMessage;

import com.practice.mobliesafe.db.dao.BlackNumberDao;

public class CallSafeService extends Service {

    private BlackNumberDao dao;

    public CallSafeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        dao = new BlackNumberDao(this);
        //��ʼ�����ŵĹ㲥
        InnerReceiver innerReceiver = new InnerReceiver();
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        intentFilter.setPriority(Integer.MAX_VALUE);
        registerReceiver(innerReceiver, intentFilter);
    }

    private class InnerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("��������");

            Object[] objects = (Object[]) intent.getExtras().get("pdus");

            for (Object object : objects) {// �������140�ֽ�,
                // �����Ļ�,���Ϊ�������ŷ���,������һ������,��Ϊ���ǵĶ���ָ��ܶ�,����forѭ��ִֻ��һ��
                SmsMessage message = SmsMessage.createFromPdu((byte[]) object);
                String originatingAddress = message.getOriginatingAddress();// ������Դ����
                String messageBody = message.getMessageBody();// ��������
                //ͨ�����ŵĵ绰�����ѯ���ص�ģʽ
                String mode = dao.findNumber(originatingAddress);
                /**
                 * ����������ģʽ
                 * 1 ȫ������ �绰���� + ��������
                 * 2 �绰����
                 * 3 ��������
                 */
                if(mode.equals("1")){
                  abortBroadcast();
                }else if(mode.equals("3")){
                    abortBroadcast();
                }
                //��������ģʽ ��Ʊ  ���ͷ��Ư�� �ִ�
                if(messageBody.contains("fapiao")){
                    abortBroadcast();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
