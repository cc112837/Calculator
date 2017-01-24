package com.cc.calculator.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cc.calculator.R;
import com.cc.calculator.adapter.DataAdapter;
import com.cc.calculator.model.DataHand;
import com.cc.calculator.utils.MyUtils;
import com.cc.calculator.view.DividerItemDecoration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment {
    private RecyclerView lv_data;
    private DataAdapter dataAdapter;
    private List<DataHand.OneHand> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_data, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        lv_data = (RecyclerView) v.findViewById(R.id.lv_data);
        dataAdapter = new DataAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        lv_data.setLayoutManager(manager);
        lv_data.setAdapter(dataAdapter);
        lv_data.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        String s = MyUtils.readFromRaw(getActivity(), R.raw.itemdata);


        int[] image = new int[]{R.mipmap.first,
                R.mipmap.two, R.mipmap.three, R.mipmap.four,R.mipmap.five,R.mipmap.six,R.mipmap.seven,R.mipmap.eight};
        try {
            JSONArray jsonObject = new JSONArray(s);
            for (int i=0;i<jsonObject.length();i++){
                DataHand.OneHand oneHand=new DataHand.OneHand();
                oneHand.setImage(image[i]);
                JSONObject twoHand = jsonObject.getJSONObject(i);
                oneHand.setTitle(twoHand.getString("title"));
                JSONArray data = twoHand.getJSONArray("data");
                List< DataHand.OneHand.TwoHand> listhand=new ArrayList<>();
                for(int j=0;j<data.length();j++){
                    DataHand.OneHand.TwoHand twoHand1 = new DataHand.OneHand.TwoHand();
                    JSONObject jsonObject1 = data.getJSONObject(j);
                    twoHand1.setTwotitle(jsonObject1.getString("twotitle"));
                    listhand.add(twoHand1);
                }
                oneHand.setData(listhand);
                list.add(oneHand);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dataAdapter.setData(list);
    }

}
