package sjn.project.djcode.ui.chatting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import sjn.project.djcode.R;

public class ChattingFragment extends Fragment {

    private ChattingViewModel chattingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chattingViewModel =
                ViewModelProviders.of(this).get(ChattingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chatting, container, false);
        final TextView textView = root.findViewById(R.id.text_chatting);
        chattingViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });
        return root;
    }
}