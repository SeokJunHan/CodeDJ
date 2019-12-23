package sjn.project.djcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import sjn.project.djcode.value_objects.Review;

public class ReviewAdapter extends ArrayAdapter<HashMap<Integer, Review>> {
    private List<Review> items;
    Context context;

    public ReviewAdapter(Context context, int resource, List<Review> objects){
        super(context, resource);
        items = objects;
        this.context = context;
    }

    public int getCount() { return items.size(); }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.review_listview, null, true);

        TextView title = convertView.findViewById(R.id.review_list_title);
        TextView content = convertView.findViewById(R.id.review_list_content);

        title.setText(" [ " + items.get(position).getTheme() + " ] " + items.get(position).getContent());
        content.setText(items.get(position).getTitle());

        return convertView;
    }
}
