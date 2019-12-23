package sjn.project.djcode.fragments.review;

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
import sjn.project.djcode.ThemeAdapter;
import sjn.project.djcode.dialog.ThemeDialog;
import sjn.project.djcode.value_objects.Theme;

public class WriteReviewFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_write_review, container, false);

        return root;
    }
}