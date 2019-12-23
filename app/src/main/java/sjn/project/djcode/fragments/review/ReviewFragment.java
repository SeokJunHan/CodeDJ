package sjn.project.djcode.fragments.review;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import sjn.project.djcode.R;
import sjn.project.djcode.ReviewAdapter;
import sjn.project.djcode.fragments.home.HomeFragment;
import sjn.project.djcode.fragments.home.ThemeFragment;
import sjn.project.djcode.value_objects.Review;

public class ReviewFragment extends Fragment {

    private Context mContext;
    private FloatingActionButton fabMain, fabSub1, fabSub2;
    private Animation fabOpen, fabClose;
    private boolean isFabOpen = false;

    FirebaseDatabase database;
    List<Review> reviewList = new ArrayList<>();
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_review, container, false);
        mContext = root.getContext();

        fabOpen = AnimationUtils.loadAnimation(root.getContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(root.getContext(), R.anim.fab_close);

        fabMain = root.findViewById(R.id.fab_main);
        fabSub1 = root.findViewById(R.id.fab_sub1);
        fabSub2 = root.findViewById(R.id.fab_sub2);

        listView = root.findViewById(R.id.review_list);

        database = FirebaseDatabase.getInstance();

        fabMain.setOnClickListener(e -> {
            toggleFab();
        });

        fabSub1.setOnClickListener(e -> {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            WriteReviewFragment writeReviewFragment = new WriteReviewFragment();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.hide(ReviewFragment.this);
            fragmentTransaction.add(R.id.nav_host_fragment, writeReviewFragment);
            fragmentTransaction.commit();
        });

        fabSub2.setOnClickListener(e -> {
            toggleFab();
            // 검색
        });

        LoadReviews();
        return root;
    }

    private void LoadReviews() {
        DatabaseReference review = database.getReference("review");
        reviewList.clear();

        review.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    Review review = item.getValue(Review.class);
                    reviewList.add(review);
                }

                ReviewAdapter reviewAdapter = new ReviewAdapter(mContext, R.layout.review_listview, reviewList);
                listView.setAdapter(reviewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void toggleFab() {
        if (isFabOpen) {
            fabMain.setImageResource(R.drawable.ic_add);
            fabSub1.startAnimation(fabClose);
            fabSub2.startAnimation(fabClose);
            fabSub1.setClickable(false);
            fabSub2.setClickable(false);

            isFabOpen = false;
        } else {
            fabMain.setImageResource(R.drawable.ic_remove);
            fabSub1.startAnimation(fabOpen);
            fabSub2.startAnimation(fabOpen);
            fabSub1.setClickable(true);
            fabSub2.setClickable(true);
            isFabOpen = true;
        }
    }
}