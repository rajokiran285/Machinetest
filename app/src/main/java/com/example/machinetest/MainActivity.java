package com.example.machinetest;

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

public class MainActivity extends AppCompatActivity {

    AsyncHttpClient client;
    JSONArray jarray;
    JSONObject jobject;
    RequestParams params;

    String url = "https://jsonplaceholder.typicode.com/users";


    ArrayList<String> id, name, username, email;
    ArrayList<String> street, suite, city, zipcode,address;
    ArrayList<String> lat, log;
    ArrayList<String> phone, website;
    ArrayList<String> companyname, catchphrase, bs;

    RecyclerView list;
    Adapterview adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        id = new ArrayList<String>();
        name = new ArrayList<>();
        username = new ArrayList<>();
        email = new ArrayList<>();
        street = new ArrayList<>();
        suite = new ArrayList<>();
        city = new ArrayList<>();
        zipcode = new ArrayList<>();
        lat = new ArrayList<>();
        log = new ArrayList<>();
        phone = new ArrayList<>();
        website = new ArrayList<>();
        companyname = new ArrayList<>();
        catchphrase = new ArrayList<>();
        bs = new ArrayList<>();
        address=new ArrayList<>();

        list = findViewById(R.id.userlist);

        client = new AsyncHttpClient();
        params = new RequestParams();

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

                    String Idd=content.toString();
                    jarray = new JSONArray(Idd);
//                    System.out.println(jarray.length());
//                    System.out.println(jobject.length());
////                    JSONArray jarray=jobject.getJSONArray();
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject obj = jarray.getJSONObject(i);

                        String ID = obj.getString("id");
                        System.out.println(ID);
                        id.add(ID);

                        String Name = obj.getString("name");
                        System.out.println(Name);
                        name.add(Name);

                        String UserName = obj.getString("username");
                        System.out.println(UserName);
                        username.add(UserName);
                        String Email = obj.getString("email");
                        System.out.println(UserName);
                        email.add(Email);


//                        String Adrss = obj.getString("address");
////                        street.add(Adrss);
//                        JSONArray jarray2=new JSONArray(Adrss);
//                        address.add(Adrss);
//                        for (int j=0;j<jarray2.length();j++) {
//                            JSONObject obj2 = jarray2.getJSONObject(j);
//
//                            String Street = obj2.getString("street");
//                            System.out.println(Street);
//                            street.add(Street);
//
//                            String Suite = obj2.getString("suite");
//                            System.out.println(Suite);
//                            suite.add(Suite);
//
//                            String City = obj2.getString("city");
//                            System.out.println(City);
//                            city.add(City);
//
//                            String Zipcode = obj2.getString("zipcode");
//                            System.out.println(Zipcode);
//                            zipcode.add(Zipcode);
//
////                            String Lat = obj.getString("lat");
////                            System.out.println(Lat);
////                            lat.add(Lat);
////
////                            String Log = obj.getString("log");
////                            System.out.println(Log);
////                            log.add(Log);
//                        }
                        String Phone = obj.getString("phone");
                        System.out.println(Phone);
                        phone.add(Phone);

                        String Website = obj.getString("website");
                        System.out.println(Website);
                        website.add(Website);
                    }


                } catch (Exception e) {

                }

                int size=website.size();
                adapter =new Adapterview(id);
                LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                list.setLayoutManager(verticalLayoutmanager);
                list.setAdapter(adapter);


            }
        });
    }

    class Adapterview extends RecyclerView.Adapter<Adapterview.viewholderview> {

        private List<String> verticalList;

        public Adapterview(List<String> verticalList) {
            this.verticalList = verticalList;
        }

        @NonNull
        @Override
        public viewholderview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.userdata, viewGroup, false);


            return new viewholderview(view);
        }

        @Override
        public void onBindViewHolder(@NonNull viewholderview viewholderview, int i) {

            viewholderview.t1.setText(name.get(i));
            viewholderview.t2.setText(username.get(i));
            viewholderview.t3.setText(email.get(i));
//            viewholderview.t4.setText(address.get(i));
//            viewholderview.t5.setText(suite.get(i));
//            viewholderview.t6.setText(city.get(i));
//            viewholderview.t7.setText(zipcode.get(i));
////            viewholderview.t8.setText(lat.get(i));
////            viewholderview.t9.setText(log.get(i));
            viewholderview.t10.setText(phone.get(i));
            viewholderview.t11.setText(website.get(i));

        }

        @Override
        public int getItemCount() {
            return verticalList.size();
        }


        public class viewholderview extends RecyclerView.ViewHolder {

            TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14;

            public viewholderview(@NonNull View itemView) {
                super(itemView);


                t1=itemView.findViewById(R.id.name);
                t2=itemView.findViewById(R.id.username);
                t3=itemView.findViewById(R.id.email);
                t4=itemView.findViewById(R.id.street);
                t5=itemView.findViewById(R.id.suite);
                t6=itemView.findViewById(R.id.city);
                t7=itemView.findViewById(R.id.zipcode);
                t8=itemView.findViewById(R.id.lat);
                t9=itemView.findViewById(R.id.log);
                t10=itemView.findViewById(R.id.phone);
                t11=itemView.findViewById(R.id.website);
                t12=itemView.findViewById(R.id.companyname);
                t13=itemView.findViewById(R.id.catchphrase);
                t14=itemView.findViewById(R.id.bs);
            }
        }
    }

}
