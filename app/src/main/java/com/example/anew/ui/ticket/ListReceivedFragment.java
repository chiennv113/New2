package com.example.anew.ui.ticket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Adapter.AdapterListTicketAccepted;
import com.example.anew.Model.ModelListTicket.ModelListTickKet;
import com.example.anew.Model.ModelListTicketAccepted.Datum;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListReceivedFragment extends Fragment {
    private ImageView mFilterTicketAccept;
    private TextView mDateStart,mDateEnd;
    private RecyclerView mRVTicketAccepted;
    private List<Datum> data = new ArrayList<>();
    private AdapterListTicketAccepted ticketAccepted;
    private LinearLayoutManager layoutManager;


    private int from = 0;
    private int take = 5;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_received_ticket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initView(view);
        super.onViewCreated(view, savedInstanceState);
    }
    private void initView(View view){
        mRVTicketAccepted=view.findViewById(R.id.rvlistTicketAccepted);
    }
    public  void getListTicketAccepted(long dateStart, long dateEnd,int from, int take, String cookie){

    }
}
