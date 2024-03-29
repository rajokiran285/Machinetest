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

public class Post extends AppCompatActivity {

    AsyncHttpClient client;
    JSONArray jarray;
    JSONObject jobject;
    RequestParams params;

    String url = "https://jsonplaceholder.typicode.com/posts";

    ArrayList<String> postid, title, body;


    RecyclerView list;
    Adapterview2 adapterview2;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postid = new ArrayList<>();
        title = new ArrayList<>();
        body = new ArrayList<>();

        list = findViewById(R.id.postlist);

        client = new AsyncHttpClient();
        params = new RequestParams();


        intent=getIntent();
        Bundle extras = intent.getExtras();
        final String Userid = extras.getString("userid");

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

                        String User_id=obj.getString("userId");

                        if(Userid.equals(User_id)) {
                            String ID = obj.getString("id");
                            System.out.println(ID);
                            postid.add(ID);

                            String Title = obj.getString("title");
                            System.out.println(Title);
                            title.add(Title);

                            String Body = obj.getString("body");
                            System.out.println(Body);
                            body.add(Body);

                        }
                    }


                } catch (Exception e) {

                }

                int size=postid.size();

                adapterview2=new Adapterview2(postid);
                LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                list.setLayoutManager(verticalLayoutmanager);
                list.setAdapter(adapterview2);
            }
        });

    }


    class Adapterview2 extends RecyclerView.Adapter<Adapterview2.viewholderview> {

        private List<String> verticalList;

        public Adapterview2(List<String> verticalList) {
            this.verticalList = verticalList;
        }

        @NonNull
        @Override
        public Adapterview2.viewholderview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.postdata, viewGroup, false);


            return new Adapterview2.viewholderview(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapterview2.viewholderview viewholderview, int i) {

            viewholderview.t1.setText(postid.get(i));
            viewholderview.t2.setText(title.get(i));
            viewholderview.t3.setText(body.get(i));
//            viewholderview.t4.setText(address.get(i));
//            viewholderview.t5.setText(suite.get(i));
//            viewholderview.t6.setText(city.get(i));
//            viewholderview.t7.setText(zipcode.get(i));
////            viewholderview.t8.setText(lat.get(i));
////            viewholderview.t9.setText(log.get(i));
//            viewholderview.t10.setText(phone.get(i));
//            viewholderview.t11.setText(website.get(i));

            final Bundle bundle=new Bundle();
            bundle.putString("postid",postid.get(i));


            viewholderview.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(),comments.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            });

        }

        @Override
        public int getItemCount() {
            return verticalList.size();
        }


        public class viewholderview extends RecyclerView.ViewHolder {

            TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14;

            public viewholderview(@NonNull View itemView) {
                super(itemView);


                t1=itemView.findViewById(R.id.postid);
                t2=itemView.findViewById(R.id.title);
                t3=itemView.findViewById(R.id.body);
//                t4=itemView.findViewById(R.id.street);
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
