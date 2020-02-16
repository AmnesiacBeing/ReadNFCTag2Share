package edu.cuc.ccc.helpers;

import android.content.Context;

import edu.cuc.ccc.backends.BackendService;

public class BackendServiceHelper {
    private static final String TAG = BackendServiceHelper.class.getSimpleName();

    private final Context mContext;
    public BackendService mService;
    private boolean mBound = false;

    public BackendServiceHelper(Context context) {
        mContext = context;
    }

    public void onStart() {
//        BackendService.bindBackendService(mContext, connection);
    }

//    private ServiceConnection connection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName className, IBinder service) {
//            BackendService.BackendServiceBinder binder = (BackendService.BackendServiceBinder) service;
//            mService = binder.getService();
//            mService.setBackendServiceCallback((BackendService.BackendServiceCallbackInterface) mContext);
//            mBound = true;
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName arg0) {
//            mBound = false;
//        }
//    };

    public void onStop() {
//        mContext.unbindService(connection);
        mBound = false;
    }

    public void onDestroy() {
//        mContext.stopService(new Intent(mContext, BackendService.class));
//        mContext.unbindService(connection);
//        mBound = false;
//        Intent intent = new Intent(mContext, BackendService.class);
//        intent.putExtra("command", BackendService.Command.CMD_DESTROY);
//        mContext.startService(intent);
    }

}