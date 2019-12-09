package sjn.project.djcode;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class PointAdapter extends ArrayAdapter<HashMap<Integer, Points>> {
    private HashMap<Integer, Points> items;
    Context context;

    public PointAdapter(Context context, int resource, HashMap<Integer, Points> objects){
        super(context, resource);
        items = objects;
        this.context = context;
    }

    public int getCount() { return items.size(); }

    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.point_listview, null, true);
        ImageView imageView = convertView.findViewById(R.id.point_img);
        TextView name = convertView.findViewById(R.id.point_name);
        TextView address = convertView.findViewById(R.id.address);
        TextView price = convertView.findViewById(R.id.price);
        imageView.setImageResource(items.get(position).getPointImg());
        name.setText(items.get(position).getPointName());
        address.setText(items.get(position).getAddress());
        price.setText(items.get(position).getPointPrice());
        return convertView;
    }
}


