package sjn.project.djcode.fragments.review;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
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
import sjn.project.djcode.ThemeAdapter;
import sjn.project.djcode.dialog.ThemeDialog;
import sjn.project.djcode.value_objects.Review;
import sjn.project.djcode.value_objects.Theme;

public class WriteReviewFragment extends Fragment {

    Spinner review_theme;
    EditText review_title, review_content;
    RatingBar review_rate, review_difficulty, review_scary, review_activity;
    Button write_button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_write_review, container, false);

        ArrayList<String> theme_name_list = new ArrayList<>();
        for (Theme theme : LoadedData.WholeThemes)
            theme_name_list.add(theme.getName());

        review_theme = root.findViewById(R.id.write_review_theme);
        review_title = root.findViewById(R.id.write_review_title);
        review_content = root.findViewById(R.id.write_review_content);
        review_rate = root.findViewById(R.id.write_review_rate);
        review_difficulty = root.findViewById(R.id.write_review_difficulty);
        review_scary = root.findViewById(R.id.write_review_scary);
        review_activity = root.findViewById(R.id.write_review_activity);
        write_button = root.findViewById(R.id.write_review_btn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item,
                theme_name_list);

        // fix spinner dropdown height
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            ListPopupWindow window = (ListPopupWindow)popup.get(review_theme);
            window.setHeight(400); //pixel
        } catch (Exception e) {
            e.printStackTrace();
        }

        review_theme.setAdapter(adapter);

        write_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title;
                if (review_title.getText().toString().trim().equals("")) {
                    Toast.makeText(root.getContext(), "글 제목을 적어주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                else title = review_title.getText().toString();

                String content;
                if (review_content.getText().toString().trim().equals("")) {
                    Toast.makeText(root.getContext(), "글 내용을 적어주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                else content = review_content.getText().toString();

                String theme = (String)review_theme.getSelectedItem();
                int rate = (int)review_rate.getRating();
                int difficulty = (int)review_difficulty.getRating();
                int scary = (int)review_scary.getRating();
                int activity = (int)review_activity.getRating();

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(calendar.getTime());

                Review review = new Review(date, theme, title, rate, difficulty, scary, activity, content);
                DatabaseReference reference = database.getReference("review");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference.child(date).setValue(review);
                        Toast.makeText(root.getContext(), "리뷰 작성이 완료되었습니다.", Toast.LENGTH_LONG).show();

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.remove(WriteReviewFragment.this);
                        if(ReviewFragment.reviewFragment != null)
                        fragmentTransaction.show(ReviewFragment.reviewFragment);
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


        return root;
    }
}