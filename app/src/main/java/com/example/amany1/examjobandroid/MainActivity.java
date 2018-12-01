package com.example.amany1.examjobandroid;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TextView tvJSONPost,tvJSONContent;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvJSONPost = findViewById(R.id.tvJSON1);
        tvJSONContent = findViewById(R.id.tvJSON2);

        img = findViewById(R.id.img);


        getPosts();
        getContacts();
    }



    public void getContacts(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, AppConstants.API_URL_Content, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ContactResponse contactResponse = gson.fromJson(response.toString(),ContactResponse.class);
                tvJSONContent.setText(((ContactResponse)contactResponse).getContacts().get(0).getEmail());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MyApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }



    public void getPosts(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(AppConstants.API_URL_Posts, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Type type = new TypeToken<List<Post>>(){}.getType();
                List<Post> posts = gson.fromJson(response.toString(),type);

                tvJSONPost.setText(((List<Post>)posts).get(0).getBody());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MyApplication.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    public void getTogobiImage(){
        ImageRequest imageRequest = new ImageRequest(AppConstants.API_URL_Image, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                //img.setImageBitmap(response);
                img.setImageBitmap((Bitmap)response);


            }
        }, 500, 500, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApplication.getInstance().addToRequestQueue(imageRequest);


    }



    /*public void sendPosts(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST,AppConstants.API_URL_Posts, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap map=new HashMap();
                Post post=new Post("s","",1,1);
                map.put("body","sdsffd");
                map.put("title","sdfd");
                map.put(1,1);
                map.put(1,1);


                return map;
            }
        };

        MyApplication.getInstance().addToRequestQueue(jsonArrayRequest);
    }*/

}