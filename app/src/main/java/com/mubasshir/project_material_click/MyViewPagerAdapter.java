package com.mubasshir.project_material_click;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Material_fragment();
            case 1:
                return new PaymentsFragment();
            default:
                return new Material_fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
