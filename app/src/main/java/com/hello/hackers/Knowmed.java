package com.hello.hackers;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.hello.hackers.R;

import java.util.List;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Knowmed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Knowmed extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    Button search; EditText search_box; ListView listView;
    String token; ArrayAdapter<String> adapter;
    String[] medicineId;
    String[] medicineName;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    ImageView imageView;


    public Knowmed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Knowmed.
     */
    // TODO: Rename and change types and number of parameters
    public static Knowmed newInstance(String param1, String param2) {
        Knowmed fragment = new Knowmed();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity activity=(MainActivity) getActivity();
        activity.getSupportActionBar().setTitle("Know your meds");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_knowmed, container, false);

      builder=new AlertDialog.Builder(getContext());
      View view=getLayoutInflater().inflate(R.layout.progrss_dialog,null);
      builder.setView(view);
      dialog=builder.create();
      dialog.setCanceledOnTouchOutside(true);
      dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      imageView=v.findViewById(R.id.know_ur_med);




//        OkHttpClient client = new OkHttpClient();
//        type = 0;
//        client.setConnectTimeout(30, TimeUnit.SECONDS);
//        client.setReadTimeout(30, TimeUnit.SECONDS);
//        client.setWriteTimeout(30, TimeUnit.SECONDS);
//        MediaType JSON = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(JSON, "{\n  \"grant_type\": \"client_credentials\",\n  \"client_id\": \"1b72cf4474d2c203ed16cf0ee86665b0792c97f0830fda3267d86d74a4f21905\",\n  \"client_secret\": \"f07e56e0e5ad7274d41ac985a8d034ae609accde515aa3bb8a9af1a731f60e64\",\n  \"scope\": \"public read write\"\n}");
//        final Request request = new Request.Builder()
//                .url("http://www.healthos.co//api/v1/oauth/token.json")
//                .method("POST",body)
//                .addHeader("Content-Type", "application/json")
//                .build();
//
//
//
//
//        client.newCall(request).enqueue(this);



        search = (Button) v.findViewById(R.id.search_button);
        search_box = (EditText) v.findViewById(R.id.search_text);
        listView= (ListView) v.findViewById(R.id.med_list);





listView.setOnItemClickListener(this);
        search_box.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView tv, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String find = search_box.getEditableText().toString();

                    search(find);


                }
                return handled;
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void search(final String find)
    {

         final String url="https://www.healthos.co//";
//      /  progressBar.setVisibility(View.VISIBLE);
        dialog.show();




            ClienCredentials clienCredentials=new ClienCredentials("client_credentials",
                    "0fb57bc15ec046716ba0952bd179b4b11a80a7ebbdfe9864193a9839f918ce46",
                    "d4ac4c6c4ba01e03261635b656a6d923d8810a03f5176332ab3c990cb335c72d",
                    "public read write");
            Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
            JsonApiInterface jsonApiInterface=retrofit.create(JsonApiInterface.class);
            Call<AccessToken> call=jsonApiInterface.getToken(clienCredentials);

            call.enqueue(new retrofit2.Callback<AccessToken>() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    if(!response.isSuccessful())
                    {
                        imageView.setVisibility(View.GONE);
                        dialog.dismiss();
//                       progressBar.setVisibility(View.GONE);
                        AlertDialog.Builder dialog1=new AlertDialog.Builder(getContext(),R.style.AlertDialogCustom);
                        dialog1.setTitle("Error");
                        dialog1.setMessage(response.code()+"Please Try again");
                        dialog1.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                search(search_box.getText().toString());

                            }
                        });
                        AlertDialog d=dialog1.create();

                        d.show();
                        d.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(R.color.colorPrimary);
//                        Log.d("TAG 5", "onResponse: "+response.message());
                    }
                    else
                    {
                        imageView.setVisibility(View.GONE);


                        token="Bearer "+response.body().getAccess_token();
                        Log.d("TAG 1", "onResponse: "+token);
                        Retrofit retrofit1=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
                        JsonApiInterface jsonApiInterface1=retrofit1.create(JsonApiInterface.class);
                        Call<List<Medicine>> med=jsonApiInterface1.getAllMedicines(token,find);
                        med.enqueue(new retrofit2.Callback<List<Medicine>>() {
                            @Override
                            public void onResponse(Call<List<Medicine>> call, Response<List<Medicine>> medresponse) {
                                if(!medresponse.isSuccessful())
                                {
//                                    progressBar.setVisibility(View.GONE);
                                    dialog.dismiss();
                                    AlertDialog.Builder dialog1=new AlertDialog.Builder(getContext(),R.style.AlertDialogCustom);
                                    dialog1.setTitle("Error");
                                    dialog1.setMessage("Error "+medresponse.code()+"\nPlease Try again");
                                    dialog1.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            search(search_box.getText().toString());

                                        }
                                    });

                                    dialog1.show();

//                                   Toast.makeText(getActivity().getApplicationContext(),"Try Again",Toast.LENGTH_SHORT).show();
                                }
                                else {
//                                    progressBar.setVisibility(View.GONE);
                                      dialog.dismiss();
                                    Log.d("TAG 3", "onResponse: "+"Success");
                                    List<Medicine> medicines = medresponse.body();
                                    if(medicines.size()==0)
                                    {
                                        AlertDialog.Builder dialog1=new AlertDialog.Builder(getContext(),R.style.AlertDialogCustom);
                                        dialog1.setTitle("Error");
                                        dialog1.setMessage("Please Try again. No match found");
                                        dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                getFragmentManager().beginTransaction().replace(R.id.container,new Knowmed()).commit();

                                            }
                                        });

                                        dialog1.show();

                                    }
                                    else {
                                        medicineId = new String[medicines.size()];
                                        medicineName = new String[medicines.size()];
                                        for (int i = 0; i < medicines.size(); i++) {

                                            medicineId[i] = medicines.get(i).getMedicine_id();
                                            medicineName[i] = medicines.get(i).getName();


                                        }

                                        adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, medicineName);
                                        listView.setAdapter(adapter);
                                    }
                                    }


                            }

                            @Override
                            public void onFailure(Call<List<Medicine>> call, Throwable t) {
                               dialog.dismiss();
//                                progressBar.setVisibility(View.GONE);
                                Log.d("TAG 4", "onFailure: "+t.getMessage());
                                AlertDialog.Builder dialog1=new AlertDialog.Builder(getContext());
                                dialog1.setTitle("Error");
                                dialog1.setMessage(t.getMessage()+"\n Please Try again");
                                dialog1.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        search(search_box.getText().toString());



                                    }
                                });

                                dialog1.show();

                                Toast.makeText(getActivity().getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
//                    progressBar.setVisibility(View.GONE);

                    AlertDialog.Builder dialog1=new AlertDialog.Builder(getContext());
                    dialog1.setTitle("Error");
                    dialog1.setMessage(t.getMessage()+"\n Please Try again");
                    dialog1.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            search(search_box.getText().toString());

                        }
                    });

                    dialog1.show();


                    Log.d("TAG 6","Failed "+t.getMessage());

                }
            });




    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        try {
//            JSONObject med = med_array.getJSONObject(position);
//           // Toast.makeText(getContext(), med.getString("medicine_id"), Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getContext(),MedInfo.class);
//            intent.putExtra("ID",med.getString("medicine_id"));
//            intent.putExtra("token",token);
//            intent.putExtra("name",med.getString("name"));
//            startActivity(intent);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        Intent intent=new Intent(getActivity().getApplicationContext(),MedInfo.class);
        intent.putExtra("ID",medicineId[position]);
        intent.putExtra("token",token);
        intent.putExtra("name",medicineName[position]);
        startActivity(intent);
        //Toast.makeText(getActivity().getApplicationContext(),medicineName[position]+"\n"+token+"",Toast.LENGTH_SHORT).show();


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
