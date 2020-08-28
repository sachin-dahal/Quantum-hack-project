package com.hello.hackers;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hello.hackers.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedInfo extends AppCompatActivity{

    String ID,token; ProgressDialog loading;
    JSONObject info;JSONArray components;
    TextView info_heading,nametv,sizetv,pricetv,manufacturertv;
    String name,price,manufacturer,type,size,constituents;
    String url="https://www.healthos.co//";
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    //TODO: Alternatives and constituents
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Adding back button to actionBar




        //Initialising Views
        setContentView(R.layout.activity_med_info);

        info_heading = (TextView) findViewById(R.id.info_heading);
        nametv = (TextView) findViewById(R.id.name);
        sizetv = (TextView) findViewById(R.id.size);
        manufacturertv = (TextView) findViewById(R.id.manufacturer);
        pricetv = (TextView) findViewById(R.id.price);
        recyclerView=findViewById(R.id.comp_recyclerView);
        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        builder=new AlertDialog.Builder(this,R.style.AlertDialogCustom);
        builder.setView(getLayoutInflater().inflate(R.layout.progrss_dialog,null));
        dialog=builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));







        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        token = intent.getStringExtra("token");
        name=intent.getStringExtra("name");
//        dialog.show();

        Log.d("TAG_info", "onCreate: "+token);


        display();






    }

    private void display() {
        dialog.show();

        Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        JsonApiInterface jsonApiInterface=retrofit.create(JsonApiInterface.class);

        Call<MedClick> medClickCall=jsonApiInterface.getCurrentMedicine(token,ID);

        medClickCall.enqueue(new Callback<MedClick>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<MedClick> call, retrofit2.Response<MedClick> response) {
                if(response.isSuccessful()) {
                    dialog.dismiss();
                    MedClick medClick = response.body();
                    String manufacturer=medClick.getManufacturer();
                    double price=medClick.getPrice()*1.6;
                    String size=medClick.getSize();
                    int no_of_comp=medClick.getComponents().size();
                    List<Component> components=medClick.getComponents();

                    ComponentAdapter adapter=new ComponentAdapter(MedInfo.this,components);

                    nametv.setText(name);
                    pricetv.setText("Rs "+price);
                    manufacturertv.setText(manufacturer);
                    sizetv.setText(size);

                    recyclerView.setAdapter(adapter);




                    Log.d("onCLickItem",manufacturer+price+size+"\n"+medClick.getComponents().get(0).getSide_effects());
//                    for(int i=0;i<componentList.size();i++)
//                    {
//                        String inst=componentList.get(i).getInstructions();
//                        String manual=componentList.get(i).getHow_it_works();
//                        String sideffects=componentList.get(i).getSide_effects();
//                        String used=componentList.get(i).getUsed_for();
//
//                        Log.d("Component", "onResponse: "+sideffects+"\n"+manual);
//
//                    }


                }
                else
                {
                    dialog.dismiss();
                    AlertDialog.Builder dialog1=new AlertDialog.Builder(MedInfo.this);
                    dialog1.setTitle("Error");
                    dialog1.setMessage("Error "+response.code()+"Try again");
                    dialog1.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            display();

                        }
                    });
                    AlertDialog builder1=dialog1.create();

                    builder1.show();
                   builder1.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(R.color.colorPrimary);

                    Log.d("sp_med","Error");
                }
            }

            @Override
            public void onFailure(Call<MedClick> call, Throwable t)
            {
                dialog.dismiss();
                AlertDialog.Builder dialog1=new AlertDialog.Builder(MedInfo.this);
                dialog1.setTitle("Error");
                dialog1.setMessage(t.getMessage()+"\nPlease Try again");
                dialog1.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        display();

                    }
                });

                dialog1.show();
                Log.d("sp_med 1", "onFailure: "+t.getMessage());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

//    private void makeRequest(final MedInfo medInfo)
//    {
//        class GetData extends AsyncTask<Object, Void, Void>
//        {
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(getApplicationContext(), "Loading..","Please Wait...",true,true);
//            }
//
//            @Override
//            protected Void doInBackground(Object... params) {
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//                loading.dismiss();
//            }
//        }
//        final GetData getData = new GetData();
//        getData.execute();
//    }
//    @Override
//    public void onFailure(Request request, IOException e) {
//        e.printStackTrace();
//    }
//
//    @Override
//    public void onResponse(Response response) throws IOException {
//        if (!response.isSuccessful()) {
//            throw new IOException("Unexpected code " + response);
//        } else {
//            try {
//                info = new JSONObject(response.body().string());
//                name = info.getString("name");
//                price = info.getString("price");
//                manufacturer = info.getString("manufacturer");
//                type = info.getString("package_form");
//                size = info.getString("size");
//                components = info.getJSONArray("components");
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //Setting information
//                        nametv.setText(name);
//                        pricetv.setText("Rs. " + price);
//                        manufacturertv.setText(manufacturer);
//                        sizetv.setText(size);
//
//
//
//                        for(int i=0;i<components.length();i++)
//                        {
//                            //Infalting layouts
//                            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                            ViewGroup parent = (ViewGroup)findViewById(R.id.medinfo_ll);
//                            View v = inflater.inflate(R.layout.components_view, parent,false);
//
//                            // Initialising views
//                            TextView name = (TextView) v.findViewById(R.id.cname);
//                            TextView instructions = (TextView) v.findViewById(R.id.instructions);
//                            TextView used_for = (TextView) v.findViewById(R.id.used_for);
//                            TextView side_effects = (TextView) v.findViewById(R.id.side_effects);
//                            TextView how_it_works = (TextView) v.findViewById(R.id.how_it_works);
//
//                            //Setting information
//                            try {
//                                name.setText(components.getJSONObject(i).getString("name")+": "+components.getJSONObject(i).getString("strength"));
//
//                                //As instructions list is HTML , using HTML to show it properly
//                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                                    instructions.setText(Html.fromHtml(components.getJSONObject(i).getString("instructions"),Html.FROM_HTML_MODE_LEGACY));
//                                } else {
//                                    instructions.setText(Html.fromHtml(components.getJSONObject(i).getString("instructions")));
//                                }
//
//                                used_for.setText(components.getJSONObject(i).getString("used_for"));
//                                side_effects.setText(components.getJSONObject(i).getString("side_effects"));
//                                how_it_works.setText(components.getJSONObject(i).getString("how_it_works"));
//                                parent.addView(v);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//
//                    }
//                });
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
}
