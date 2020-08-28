package com.hello.hackers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.hello.hackers.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    GridView grid;
    String[] text = { "Med Reminder","Health Tips","Know Your Med"};
    int[] imageId = { R.drawable.grid2,R.drawable.health_tips,R.drawable.grid3};
    int[] imagesliderId = {R.drawable.a2,R.drawable.a1,R.drawable.a3,R.drawable.a4,R.drawable.a5};


    ViewPager viewPager;
    ImageSlider slider;
    private OnFragmentInteractionListener mListener;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity activity=(MainActivity) getActivity();
        //needs to change!!
        activity.getSupportActionBar().setTitle("Hello Hackers!!");

        View v=inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
//        viewPager = (ViewPager)v.findViewById(R.id.viewPager);
//        final PagerAdapter adapter = new CustomAdapter(getContext());

        viewPager=v.findViewById(R.id.viewPager);


       slider=new ImageSlider(getActivity().getApplicationContext(),imagesliderId);

       viewPager.setAdapter(slider);

//
//       viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//           @Override
//           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//           }
//
//           @Override
//           public void onPageSelected(int position) {
//
//           }
//
//           @Override
//           public void onPageScrollStateChanged(int state) {
//
//           }
//       });
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == imagesliderId.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);




//
//        final Handler mHandler = new Handler();
//
//        // Create runnable for posting
//        final Runnable mUpdateResults = new Runnable() {
//            public void run() {
//
//
//
//
//            }
//        };
//
//        int delay = 2000; // delay for 1 sec.
//        int period = 3000; // repeat every 5 sec.
//        Timer timer = new Timer();
//
//        timer.scheduleAtFixedRate(new TimerTask() {
//
//            public void run() {
//
//                mHandler.post(mUpdateResults);
//
//            }
//
//        }, delay, period);

        //Setting grid view
        CustomGrid gridAdapter = new CustomGrid(getContext(), text, imageId);
        grid=(GridView) v.findViewById(R.id.grid_home);
        grid.setAdapter(gridAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch(position) {
                    case 0: {
                        FragmentReminder rm = new FragmentReminder();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.container, rm).commit();

                        break;
                    }
                    case 1: {
                        HealthTips rm = new HealthTips();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.container, rm).commit();
                        break;
                    }
                    case 2: {
                        Knowmed rm = new Knowmed();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.container, rm).commit();
                        break;
                    }
                }

            }
        });

        return v;
    }

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
