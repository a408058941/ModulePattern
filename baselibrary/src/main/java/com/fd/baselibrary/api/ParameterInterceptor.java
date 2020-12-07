package com.fd.baselibrary.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fd.baselibrary.BuildConfig;
import com.fd.baselibrary.utils.EmptyUtil;
import com.fd.baselibrary.utils.L;
import com.fd.baselibrary.utils.SPManager;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * 参数格式为Json格式的拦截器
 */
public class ParameterInterceptor implements Interceptor {
    private static final String TAG = ParameterInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        RequestBody requestBody = makeRequestBody(oldRequest);
        HttpUrl httpUrl = oldRequest.url().newBuilder().build();
        Request newRequest = null;
        if (oldRequest.method().equals("POST")) {
            newRequest = oldRequest.newBuilder()
                    .addHeader("Cookie", SPManager.getLogin_cookie())//添加header
                    .post(requestBody)
                    .build();
        } else {
            newRequest = oldRequest.newBuilder()
                    .url(httpUrl)
                    .addHeader("Cookie", SPManager.getLogin_cookie())//添加header
                    .build();
        }
        Response response = chain.proceed(newRequest);
        Log.e("DEBUG",BuildConfig.DEBUG+"");
        //打印返回数据
//        if (BuildConfig.DEBUG) {
        ResponseBody resultBody = response.body();
        String result = resultBody.string();
//            Log.e("result",result);
        try {
            L.json(result);
        } catch (Exception e) {
            L.e(result);
        }

        /*** 因为调用ResponseBody.string()后即关闭，后续无法获取内容 */
        response = response.newBuilder()
                .body(ResponseBody.create(resultBody.contentType(), result))
                .build();
//            Log.e("响应头", response.headers().toString());
//            if (response.header("Set-Cookie") != null) {
//                SPManager.setLogin_cookie(response.header("Set-Cookie"));
//                if (!EmptyUtil.isNotEmpty(SPManager.getLogin_cookie()))
//                    SPManager.setLogin_cookie(response.header("set-cookie"));
//            } else
        if (response.header("set-cookie") != null) {
            if (EmptyUtil.isNotEmpty(response.header("set-cookie"))){
                SPManager.setLogin_cookie(response.header("set-cookie"));
            }

        }
        if (!EmptyUtil.isNotEmpty(SPManager.getLogin_cookie())){
            if (response.header("Set-Cookie") != null) {
                if (EmptyUtil.isNotEmpty(response.header("Set-Cookie"))){
                    SPManager.setLogin_cookie(response.header("Set-Cookie"));
                }
            }
        }

//        }
        return response;
    }

    @NonNull
    private RequestBody makeRequestBody(Request oldRequest) {

        String sing = "";
        HashMap<String, String> map = new HashMap<>();
        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
        if (oldRequest.method().equals("POST")) {
            if (oldRequest.body() instanceof FormBody) {
                FormBody body = (FormBody) oldRequest.body();
                if (body != null) {
                    for (int i = body.size() - 1; i >= 0; i--) {
                        String name = body.name(i);
                        String value = body.value(i);
                        map.put(name, value);
                    }
                    String sign = getSign(map);
                    map.put("sign", sign);
                }
            } else if (oldRequest.body() instanceof MultipartBody) {
                /*** 当参数以 @MultipartBody 提交时 */
                L.d(TAG, "instanceof MultipartBody");
//
                multipartBodyBuilder.setType(MultipartBody.FORM);

                for (MultipartBody.Part part : ((MultipartBody) oldRequest.body()).parts()) {
                    multipartBodyBuilder.addPart(part);
                }

                return multipartBodyBuilder.build();
            }
        } else {
            Set<String> set = oldRequest.url().queryParameterNames();
            for (String str : set) {
                List<String> list = oldRequest.url().queryParameterValues(str);
                if (list != null && list.size() > 0)
                    map.put(str, list.get(0));
            }
            String sign = getSign(map);
            map.put("sign", sign);
        }
        /** * 添加Sign参数 */
        FormBody.Builder newBodyBuilder = new FormBody.Builder();
        for (String str : map.keySet()) {
            newBodyBuilder.add(str, map.get(str));
        }


        return newBodyBuilder.build();
    }

    public String getSign(HashMap<String, String> map) {
        map.put("request_time", (System.currentTimeMillis() / 1000) + "");
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (String key : map.keySet()) {
            basestring.append(key).append("=").append(map.get(key));
        }
        basestring.append("AIfenDanKey201907");
        MessageDigest md5Temp = null;
        byte[] bytes = null;
        String str = "";
        try {
            md5Temp = MessageDigest.getInstance("MD5");
            bytes = md5Temp.digest(basestring.toString().getBytes("UTF-8"));
            str = new String(bytes, "UTF-8");
            StringBuffer sb = new StringBuffer(bytes.length);
            String sTmp;
            for (int i = 0; i < bytes.length; i++) {
                sTmp = Integer.toHexString(0xFF & bytes[i]);
                if (sTmp.length() < 2)
                    sb.append(0);
//                sb.append(sTmp.toUpperCase());
                sb.append(sTmp);
            }
            str = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;

    }

    /**
     * body 中的内容
     *
     * @param request
     * @return
     */
    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
