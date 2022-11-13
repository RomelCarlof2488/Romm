package com.example.romm;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPeli#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPeli extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddPeli() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPeli.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPeli newInstance(String param1, String param2) {
        AddPeli fragment = new AddPeli();
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
    private Button add;
    private EditText title,time,description;
    private FirebaseFirestore mfirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        View root= inflater.inflate(R.layout.fragment_add_peli, container, false);
        mfirestore =FirebaseFirestore.getInstance();

        add = root.findViewById(R.id.add);
        title = root.findViewById(R.id.title);
        time = root.findViewById(R.id.time);
        description = root.findViewById(R.id.description);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String titles = title.getText().toString().trim();
            String times = time.getText().toString().trim();
            String descriptions = description.getText().toString().trim();

            if (titles.isEmpty() && times.isEmpty() && descriptions.isEmpty() ){
                Toast.makeText(getActivity(),"Ingresar los dates", LENGTH_SHORT).show();
            }else{
                postPelicula(titles,times,descriptions);
            }

            }

            private void postPelicula(String titles, String times, String descriptions) {
                Map<String, Object> map = new HashMap<>();
                map.put("title",titles);
                map.put("time",times);
                map.put("description",descriptions);


                mfirestore.collection("pelicula").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(),"Datos Guardados", LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"Ocurrió un error", LENGTH_SHORT).show();
                    }
                });
            }
        });





        return root;
    }


}