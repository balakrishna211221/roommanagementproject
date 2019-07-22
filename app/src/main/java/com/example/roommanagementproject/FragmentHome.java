package com.example.roommanagementproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;


import java.net.URISyntaxException;

import static android.content.Context.MODE_PRIVATE;

class FragmentHome  extends Fragment {
    TextView textView,pttotal;
    PTAdp ptAdp;

    Context context;
    ImageButton maket,creatGroup,makemande;
    Button button;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("Profile");

        View view = inflater.inflate(R.layout.home, container, false);



        final SharedPreferences preferences = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String num = preferences.getString("mobile", null);
        String pass = preferences.getString("pass", null);
        SharedPreferences totalSharedPreferences=this.getActivity().getSharedPreferences("total",Context.MODE_PRIVATE);
       String s= totalSharedPreferences.getString("pt",null);

        textView = view.findViewById(R.id.profileusername);
        pttotal=view.findViewById(R.id.pttotalshow);
        makemande=view.findViewById(R.id.makeMandtory);
        creatGroup=view.findViewById(R.id.creatGroup);
        maket = view.findViewById(R.id.maketranp);
        textView.setText(num);
        if (num != null && pass != null) {

            button = view.findViewById(R.id.logout);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.commit();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);

                }
            });
        } else {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }

        maket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MakeTransaction.class);
                startActivity(intent);
            }
        });

        creatGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),CreatGroup.class);
                startActivity(intent);
            }
        });

        makemande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MakeMadetory.class);
                startActivity(intent);
            }
        });




        pttotal.setText("Personal Transactions total:"+s);


      //  Toast.makeText(context, ""+pttotals, Toast.LENGTH_SHORT).show();


        return view;




    }

}
