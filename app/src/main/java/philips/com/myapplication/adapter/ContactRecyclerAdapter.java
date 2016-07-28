package philips.com.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.bean.ContactBean;
import philips.com.myapplication.customview.CircleImageView;
import philips.com.myapplication.viewholder.Holder;

/**
 * Created by 310231492 on 2016/7/25.
 */
public class ContactRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ContactBean> contactBeans;
    private ArrayList<ContactBean> recentContactBeans;
    private Context context;
    private int recentCount = 0;

    private final static int TYPE_HEADER = 0;
    private final static int TYPE_TITLE = 1;
    private final static int TYPE_ITEM = 2;
//    private final static int TYPE_FOOTER = 3;

    public ContactRecyclerAdapter(Context context, ArrayList<ContactBean> contactBeans) {
        this.contactBeans = contactBeans;
        this.context = context;
        recentContactBeans = new ArrayList<>();
        for (ContactBean contactBean: contactBeans) {
            if (contactBean.isContactRecently()) {
                recentCount++;
                recentContactBeans.add(contactBean);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.contact_header, parent, false);
            return new ContactHeaderViewHolder(view);
        } else if (viewType == TYPE_TITLE) {
            view = LayoutInflater.from(context).inflate(R.layout.item_graytitle, parent, false);
            return new GrayTitleViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
            return new ContactItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_HEADER) return;
        if (viewType == TYPE_TITLE) {
            GrayTitleViewHolder grayTitleViewHolder = (GrayTitleViewHolder) holder;
            if (position == 1) {
                grayTitleViewHolder.textView.setText("最近联系人");
            } else {
                grayTitleViewHolder.textView.setText("所有联系人");
            }
        } else {
            ContactItemViewHolder contactItemViewHolder = (ContactItemViewHolder) holder;
            if (position < recentCount + 2) {
                contactItemViewHolder.userName.setText(recentContactBeans.get(position - 2).getContactName());
                Picasso.with(context).load(recentContactBeans.get(position - 2).getContactIconUrl()).fit().placeholder(R.mipmap.loading)
                        .config(Bitmap.Config.RGB_565).into(contactItemViewHolder.userIcon);
            } else {
                ContactBean contactBean = contactBeans.get(position - recentCount - 3);
                contactItemViewHolder.userName.setText(contactBean.getContactName());
                Picasso.with(context).load(contactBean.getContactIconUrl()).fit().placeholder(R.mipmap.loading)
                        .config(Bitmap.Config.RGB_565).into(contactItemViewHolder.userIcon);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == 1 || position == recentCount + 2) {
            return TYPE_TITLE;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        //1个header,两个title
        return contactBeans.size() + recentCount + 3;
    }

    class GrayTitleViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public GrayTitleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.title_txt);
        }
    }

    class ContactItemViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        CircleImageView userIcon;
        RelativeLayout relativeLayout;

        public ContactItemViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            userName = (TextView) itemView.findViewById(R.id.contact_name);
            userIcon = (CircleImageView) itemView.findViewById(R.id.contact_icon);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.contact_layout);
        }
    }

    class ContactHeaderViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout1,linearLayout2,linearLayout3;

        public ContactHeaderViewHolder(View itemView) {
            super(itemView);
            linearLayout1 = (LinearLayout) itemView.findViewById(R.id.familiar_people_ll);
            linearLayout2 = (LinearLayout) itemView.findViewById(R.id.star_user_ll);
            linearLayout3 = (LinearLayout) itemView.findViewById(R.id.musician_ll);
            linearLayout1.setClickable(true);
            linearLayout2.setClickable(true);
            linearLayout3.setClickable(true);
        }
    }
}
