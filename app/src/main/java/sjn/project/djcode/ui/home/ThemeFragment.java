package sjn.project.djcode.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import sjn.project.djcode.BranchAdapter;
import sjn.project.djcode.LoadedData;
import sjn.project.djcode.R;
import sjn.project.djcode.Theme;
import sjn.project.djcode.ThemeAdapter;

public class ThemeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_theme, container, false);

        Bundle args = getArguments();
        String branch = args.getString("branch");

        // 테마 관련 정보 불러오기
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference theme = database.getReference().child("theme").child(branch);
        theme.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                LoadedData.Themes.clear();
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    Theme theme = item.getValue(Theme.class);
                    System.out.println(theme.getName());
                    LoadedData.Themes.add(theme);
                }

                final ListView listView = root.findViewById(R.id.theme_list);
                ThemeAdapter adapter = new ThemeAdapter(root.getContext(), R.id.point_img , LoadedData.Themes);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(root.getContext(), position+"", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                LoadedData.Themes.clear();
            }
        });

        return root;
    }
}