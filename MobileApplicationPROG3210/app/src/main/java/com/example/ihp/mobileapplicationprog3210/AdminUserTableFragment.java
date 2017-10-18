package com.example.ihp.mobileapplicationprog3210;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by iHP on 10/17/2017.
 */

public class AdminUserTableFragment extends Fragment {
    private static final String TAG = "AdminUserTableFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_user_table, container, false);

        return view;
    }
}
