package sjn.project.djcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class ReviewAdapter extends ArrayAdapter<HashMap<Integer, Reviews>> {
    private HashMap<Integer, Reviews> items;
    Context context;

    public ReviewAdapter(Context context, int resource, HashMap<Integer, Reviews> objects){
        super(context, resource);
        items = objects;
        this.context = context;
    }

    public int getCount() { return items.size(); }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.review_listview, null, true);

        TextView title = convertView.findViewById(R.id.titles);
        TextView content = convertView.findViewById(R.id.content);


        title.setText(items.get(position).getContent());
        content.setText(items.get(position).getTitle());

        return convertView;
    }
}
