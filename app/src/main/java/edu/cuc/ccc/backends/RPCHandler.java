package edu.cuc.ccc.backends;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

import edu.cuc.ccc.Device;
import edu.cuc.ccc.plugins.PluginBase;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

import static edu.cuc.ccc.backends.RPCUtil.getOkHttpClient;
import static edu.cuc.ccc.backends.RPCUtil.getRequest;

// 从Android 9开始，应用内不允许使用无SSL连接，无奈
public class RPCHandler {

    private static final String TAG = RPCHandler.class.getSimpleName();

    // 配对设备地址已知，自身信息已知，插件名称需提供，内容需要提供
    public void sendRPCRequest(Device targetDevice, String rpc, Callback callback) {
        OkHttpClient okHttpClient = getOkHttpClient(targetDevice);
        HttpUrl url = RPCUtil.getHttpUrl(targetDevice);
        Request request = getRequest(url);
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public void sendRPCRequest(Device targetDevice, PluginBase plugin, Callback callback) {
        sendRPCRequest(targetDevice, plugin.getPluginName(), callback);
    }

    public static abstract class PairRequestCallback implements Callback {
        public abstract void onPairRequestError();

        public abstract void onPairRequestComplete();

        @Override
        public final void onFailure(@NotNull Call call, @NotNull IOException e) {
            e.printStackTrace();
            onPairRequestError();
        }

        @Override
        public final void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            onPairRequestComplete();
        }
    }

}
