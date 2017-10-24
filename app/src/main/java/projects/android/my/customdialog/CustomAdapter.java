package projects.android.my.customdialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by User on 24-10-2017.
 */

public class CustomAdapter extends BaseAdapter
{
    Context context;
    String[] name,phone,dob;

    public  CustomAdapter(Context context,String[] name,String[] phone,String[] dob)
    {
        this.context=context;
        this.name=name;
        this.phone=phone;
        this.dob=dob;
    }
    @Override
    public int getCount() {
        return name.length;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.listlayout,null);

        TextView contactName = (TextView) view.findViewById(R.id.name);
        TextView contactNumber = (TextView) view.findViewById(R.id.phone);
        TextView contactDob = (TextView) view.findViewById(R.id.dob);

        contactName.setText(name[position]);
        contactNumber.setText(phone[position]);
        contactDob.setText(dob[position]);

        return  view;
    }
}
