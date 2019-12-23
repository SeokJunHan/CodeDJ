package sjn.project.djcode.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import sjn.project.djcode.LoadedData;
import sjn.project.djcode.R;
import sjn.project.djcode.dialog.ThemeDialog;
import sjn.project.djcode.value_objects.Theme;
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
                    LoadedData.Themes.add(theme);
                }

                //TDOO 로드 완료까지 대기.
                final ListView listView = root.findViewById(R.id.theme_list);
                ThemeAdapter adapter = new ThemeAdapter(root.getContext(), R.id.point_img , LoadedData.Themes);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(root.getContext(), ThemeDialog.class);
                        Theme item = LoadedData.Themes.get(position);
                        intent.putExtra("theme", item);
                        startActivity(intent);
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