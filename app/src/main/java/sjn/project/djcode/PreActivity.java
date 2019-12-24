package sjn.project.djcode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import sjn.project.djcode.value_objects.Branch;
import sjn.project.djcode.value_objects.Theme;

public class PreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference branch = database.getReference().child("branch");
        branch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                LoadedData.Branches.clear();
                LoadedData.WholeThemes.clear();

                for(DataSnapshot item : dataSnapshot.getChildren()) {

                    Branch branch = item.getValue(Branch.class);
                    LoadedData.Branches.add(branch);

                    database.getReference("theme").child(branch.getName()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                                Theme theme = ds.getValue(Theme.class);
                                LoadedData.WholeThemes.add(theme);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_zoom_in_show, R.anim.anim_zoom_in_hide);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                LoadedData.Branches.clear();
            }
        });
    }
}
