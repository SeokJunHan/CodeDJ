package sjn.project.djcode.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import sjn.project.djcode.LoadedData;
import sjn.project.djcode.BranchAdapter;
import sjn.project.djcode.R;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ListView listView = root.findViewById(R.id.branch_list);
        BranchAdapter adapter = new BranchAdapter(root.getContext(), R.id.point_img , LoadedData.Branches);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle args = new Bundle();
                args.putString("branch", LoadedData.Branches.get(position).getName());

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ThemeFragment themeFragment = new ThemeFragment();
                themeFragment.setArguments(args);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.hide(HomeFragment.this);
                fragmentTransaction.add(R.id.nav_host_fragment, themeFragment);
                fragmentTransaction.commit();
            }
        });

//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                listView.setAdapter(adapter);
//            }
//        });
        return root;
    }
}