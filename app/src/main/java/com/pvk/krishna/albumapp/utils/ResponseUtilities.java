package com.pvk.krishna.albumapp.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pvk.krishna.albumapp.volley.RequestManager;

import java.util.Map;

/**
 * {@link ResponseUtilities} is an utility class for sending the requests for the server and invokes the classes after receiving
 * the responses from the server.
 *
 * @author Kartheek, Krishna
 */
public class ResponseUtilities {

    private static final String TAG = ResponseUtilities.class.getSimpleName();
    private static ResponseUtilities responseUtilities;

    /**
     * This method is used to getting the instance of the {@link ResponseUtilities}.
     *
     * @return which returns the static instance of the {@link ResponseUtilities}.
     */
    public static ResponseUtilities getInstance() {
        if (responseUtilities == null)
            responseUtilities = new ResponseUtilities();
        return responseUtilities;
    }

    /**
     * This method is used to getting the response from the url.
     *
     * @param listener listerner of the URLResponse
     * @param context  context of the application.
     * @param url      url for getting response.
     * @param tag      Tag for the current request.
     * @param id       Id for the current request.
     */
    public void getStringResponseFromUrl(final OnStringResponseListener listener, final int id, final Context context, final String url,
                                         final String tag) {

        Log.d(TAG, "TAG:" + tag + " URL: " + url);

        // Creating the StringRequest for requesting the data from the server.
        StringRequest listRequest = new StringRequest(Method.GET, url, new Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response : " + response);
                // setting response to the listener.
                if (listener != null) {
                    listener.onResponse(response, id);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                // Toast.makeText(context, context.getResources().getString(R.string.connection_error),
                // Toast.LENGTH_SHORT).show();
                arg0.printStackTrace();
                Log.e(TAG, "Request with url " + url + "\t TAG  " + tag + "  on Error response");
                // setting error response to the listener.
                if (listener != null) {
                    listener.onErrorResponse(arg0, id);
                }
            }
        }) {

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                // setting network response to the listener.
                if (listener != null)
                    listener.parseNetworkResponse(response, id);
                return super.parseNetworkResponse(response);
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.i("Params", "Param:---> get params");
                if (listener != null) {
                    Log.i("Params", "Param:---> "+listener.getParams(id));
                    return listener.getParams(id);
                }
                Log.i("Params", "Param:---> invalid");
                return super.getParams();
            }
        };
        // Adding the request to the RequestQueue
        listRequest.setTag(tag);
        listRequest.setRetryPolicy(new DefaultRetryPolicy(5000,2,2));
        Log.i("listRequest", "ABCD ");
        Log.i("listRequest", "urlREQ " + listRequest.toString());
        RequestManager.getRequestQueue().add(listRequest);
    }
}
