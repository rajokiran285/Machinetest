package com.example.machinetest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class comments extends AppCompatActivity {

    AsyncHttpClient client;
    JSONArray jarray;
    JSONObject jobject;
    RequestParams params;

    String url = "https://jsonplaceholder.typicode.com/comments";

    ArrayList<String> commentid, name, body,email;


    RecyclerView list;
    Adapterview3 adapterview3;


    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        commentid=new ArrayList<>();
        name=new ArrayList<>();
        body=new ArrayList<>();
        email=new ArrayList<>();

        list = findViewById(R.id.commentlist);

        client = new AsyncHttpClient();
        params = new RequestParams();


        intent=getIntent();
        Bundle extras = intent.getExtras();
        final String Postid = extras.getString("postid");


        client.get(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String content) {
                // TODO Auto-generated method stub
                super.onSuccess(content);
                System.out.println(content);

                try {

//                    jobject = new JSONObject(content);
                    jarray = new JSONArray(content);
                    System.out.println(content);

                    String Idd = content.toString();
                    jarray = new JSONArray(Idd);
//                    System.out.println(jarray.length());
//                    System.out.println(jobject.length());
////                    JSONArray jarray=jobject.getJSONArray();
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject obj = jarray.getJSONObject(i);

                        String Post_id=obj.getString("postId");

                        if(Postid.equals(Post_id)) {
                            String ID = obj.getString("id");
                            System.out.println(ID);
                            commentid.add(ID);

                            String Email = obj.getString("email");
                            System.out.println(Email);
                            email.add(Email);

                            String Name = obj.getString("name");
                            System.out.println(Name);
                            name.add(Name);

                            String Body = obj.getString("body");
                            System.out.println(Body);
                            body.add(Body);

                        }
                    }


                } catch (Exception e) {

                }

                int size=commentid.size();

                adapterview3=new Adapterview3(commentid);
                LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                list.setLayoutManager(verticalLayoutmanager);
                list.setAdapter(adapterview3);
            }
        });



    }

    class Adapterview3 extends RecyclerView.Adapter<Adapterview3.viewholderview> {

        private List<String> verticalList;

        public Adapterview3(List<String> verticalList) {
            this.verticalList = verticalList;
        }

        @NonNull
        @Override
        public Adapterview3.viewholderview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.commentdata, viewGroup, false);


            return new Adapterview3.viewholderview(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapterview3.viewholderview viewholderview, int i) {

            viewholderview.t1.setText(commentid.get(i));
            viewholderview.t2.setText(email.get(i));
            viewholderview.t3.setText(name.get(i));
            viewholderview.t4.setText(body.get(i));
//            viewholderview.t5.setText(suite.get(i));
//            viewholderview.t6.setText(city.get(i));
//            viewholderview.t7.setText(zipcode.get(i));
////            viewholderview.t8.setText(lat.get(i));
////            viewholderview.t9.setText(log.get(i));
//            viewholderview.t10.setText(phone.get(i));
//            viewholderview.t11.setText(website.get(i));


        }

        @Override
        public int getItemCount() {
            return verticalList.size();
        }


        public class viewholderview extends RecyclerView.ViewHolder {

            TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14;

            public viewholderview(@NonNull View itemView) {
                super(itemView);


                t1=itemView.findViewById(R.id.commentid);
                t2=itemView.findViewById(R.id.C_email);
                t3=itemView.findViewById(R.id.C_name);
                t4=itemView.findViewById(R.id.C_body);
//                t5=itemView.findViewById(R.id.suite);
//                t6=itemView.findViewById(R.id.city);
//                t7=itemView.findViewById(R.id.zipcode);
//                t8=itemView.findViewById(R.id.lat);
//                t9=itemView.findViewById(R.id.log);
//                t10=itemView.findViewById(R.id.phone);
//                t11=itemView.findViewById(R.id.website);
//                t12=itemView.findViewById(R.id.companyname);
//                t13=itemView.findViewById(R.id.catchphrase);
//                t14=itemView.findViewById(R.id.bs);
            }
        }
    }

}
