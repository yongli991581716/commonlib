
package com.common.lib.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.common.lib.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.HashMap;

/**
 * 异常错误解析提示信息类
 * 
 * @author liyong
 */
public class ErrorHelper {

    public static String getMessage(Object error, Context context) {

        if (error != null && context != null) {
            if (error instanceof TimeoutError) {

                return context.getResources().getString(R.string.generic_server_down);

            } else if (isServerProblem(error)) {

                return handleServerError(error, context);

            } else if (isNetworkProblem(error)) {

                return context.getResources().getString(R.string.no_internet);

            } else if (error instanceof JSONException) {

                return context.getResources().getString(R.string.json_error);

            }
        }

        if (context != null) {
            return context.getResources().getString(R.string.generic_error);
        }

        return "";

    }

    private static boolean isNetworkProblem(Object error) {

        return (error instanceof NetworkError) || (error instanceof NoConnectionError);

    }

    private static boolean isServerProblem(Object error) {

        return (error instanceof ServerError) || (error instanceof AuthFailureError);

    }

    private static String handleServerError(Object err, Context context) {

        VolleyError error = (VolleyError) err;

        NetworkResponse response = error.networkResponse;

        if (response != null) {
            switch (response.statusCode) {

                case 404:

                case 422:

                case 401:

                    try {

                        // server might return error like this { "error": "Some error occured" }

                        // Use "Gson" to parse the result

                        @SuppressWarnings("rawtypes")
                        HashMap result = new Gson().fromJson(new String(response.data),

                                new TypeToken() {

                                }.getType());

                        if (result != null && result.containsKey("error")) {

                            return (String) result.get("error");

                        }

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                    // invalid request

                    return error.getMessage();

                default:

                    return context.getResources().getString(R.string.generic_server_error);

            }

        }

        return context.getResources().getString(R.string.generic_error);

    }
}
