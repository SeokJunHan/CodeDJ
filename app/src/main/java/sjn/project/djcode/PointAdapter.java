package sjn.project.djcode;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class PointAdapter extends ArrayAdapter<HashMap<Integer, Branch>> {
    private List<Branch> items;
    Context context;

    public PointAdapter(Context context, int resource, List<Branch> objects){
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
        Glide.with(context)
                .load(items.get(position).getImg())
                .into(imageView);
        name.setText(items.get(position).getName());
        address.setText(items.get(position).getAddress());
        DecimalFormat format = new DecimalFormat("#,###");
        price.setText(format.format(items.get(position).getPrice()) + " 원 / 2인");
        return convertView;
    }
}


