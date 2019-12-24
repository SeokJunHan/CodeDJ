package sjn.project.djcode.fragments.review;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import sjn.project.djcode.LoadedData;
import sjn.project.djcode.R;
import sjn.project.djcode.value_objects.Review;
import sjn.project.djcode.value_objects.Theme;

public class ReadReviewFragment extends Fragment {

    TextView review_theme, review_title, review_content;
    RatingBar review_rate, review_difficulty, review_scary, review_activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_read_review, container, false);

        Bundle args = getArguments();
        Review review = (Review)args.getSerializable("review");

        review_theme = root.findViewById(R.id.read_review_theme);
        review_title = root.findViewById(R.id.read_review_title);
        review_content = root.findViewById(R.id.read_review_content);
        review_rate = root.findViewById(R.id.read_review_rate);
        review_difficulty = root.findViewById(R.id.read_review_difficulty);
        review_scary = root.findViewById(R.id.read_review_scary);
        review_activity = root.findViewById(R.id.read_review_activity);

        review_theme.setText("[ " + review.getTheme() + " ]");
        review_title.setText(review.getTitle());
        review_content.setText(review.getContent());
        review_difficulty.setRating(review.getDifficulty());
        review_scary.setRating(review.getScary());
        review_activity.setRating(review.getActivity());
        review_rate.setRating(review.getRate());

        return root;
    }
}