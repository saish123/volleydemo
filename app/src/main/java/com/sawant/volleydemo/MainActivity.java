package com.sawant.volleydemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // @formatter:off

    private Button jsonRequestButton;
    private Button jsonArrayRequestButton;
    private Button stringRequestButton;
    private Button postParamRequestButton;
    private Button headerRequestButton;
    private Button imageRequestButton;
    private Button loadImageButton;
    private String TAG                                 = "MainActivity";
    private NetworkImageView imageNetworkView;
    private ImageView imageView;
    // @formatter:on

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilizeResources();
    }

    private void initilizeResources() {

        jsonRequestButton = (Button) findViewById(R.id.jsonRequestButton);
        jsonArrayRequestButton = (Button) findViewById(R.id.jsonArrayRequestButton);
        stringRequestButton = (Button) findViewById(R.id.stringRequestButton);
        postParamRequestButton = (Button) findViewById(R.id.postParamRequestButton);
        headerRequestButton = (Button) findViewById(R.id.headerRequestButton);
        imageRequestButton = (Button) findViewById(R.id.imageRequestButton);
        loadImageButton = (Button) findViewById(R.id.loadImageButton);
        imageNetworkView = (NetworkImageView) findViewById(R.id.imageNetworkView);
        imageView = (ImageView) findViewById(R.id.imageView);

        jsonRequestButton.setOnClickListener(this);
        jsonArrayRequestButton.setOnClickListener(this);
        stringRequestButton.setOnClickListener(this);
        postParamRequestButton.setOnClickListener(this);
        headerRequestButton.setOnClickListener(this);
        imageRequestButton.setOnClickListener(this);
        loadImageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jsonRequestButton:
                callJsonAPIRequest();
                break;

            case R.id.jsonArrayRequestButton:
                callJsonArrayAPIRequest();
                break;


            case R.id.stringRequestButton:
                callStringAPIRequest();
                break;

            case R.id.postParamRequestButton:
                callAPIWithPostParamRequest();
                break;

            case R.id.headerRequestButton:
                callAPIWithHeaderRequest();
                break;

            case R.id.imageRequestButton:
                callImageAPIRequest();
                break;

            case R.id.loadImageButton:
                loadImageOnButton();
                break;

        }
    }


    private void callJsonAPIRequest() {
        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        String url = "https://api.androidhive.info/volley/person_object.json";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });


// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);

    }


    private void callJsonArrayAPIRequest() {
// Tag used to cancel the request
        String tag_json_arry = "json_array_req";

        String url = "https://api.androidhive.info/volley/person_array.json";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest, tag_json_arry);
    }


    private void callStringAPIRequest() {
// Tag used to cancel the request
        String tag_string_req = "string_req";

        String url = "https://api.androidhive.info/volley/string_response.html";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });


        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);
    }


    private void callAPIWithPostParamRequest() {

        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        String url = "https://api.androidhive.info/volley/person_object.json";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", "Androidhive");
                params.put("email", "abc@androidhive.info");
                params.put("password", "password123");
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);
    }


    private void callAPIWithHeaderRequest() {

        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        String url = "https://api.androidhive.info/volley/person_object.json";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("apiKey", "xxxxxxxxxxxxxxx");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);
    }

    private void callImageAPIRequest() {
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
// If you are using NetworkImageView
        String url = "http://download.seaicons.com/icons/custom-icon-design/mono-general-2/512/help-icon.png";
        imageNetworkView.setImageUrl(url, imageLoader);
    }

    private void loadImageOnButton() {

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        String url = "http://download.seaicons.com/icons/custom-icon-design/mono-general-2/512/help-icon.png";
        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    imageView.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Load Error: " + error.getMessage());

            }
        });
    }

}
