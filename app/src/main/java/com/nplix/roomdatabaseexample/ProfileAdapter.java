package com.nplix.roomdatabaseexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Pawan on 1/18/2018.
 */
public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private String TAG="ProfileAdapter";

    Context context;
    private List<ProfileEntity> profileEntities;

    private boolean mWithHeader;
    private boolean mWithFooter;
    private View.OnClickListener mOnClickListener;

    public ProfileAdapter(List<ProfileEntity> profileEntities, Context context, boolean withHeader, boolean withFooter) {
        this.profileEntities = profileEntities;
        this.context=context;
        this.mWithHeader=withHeader;
        this.mWithFooter=withFooter;

    }
    public ProfileAdapter(Context context, boolean withHeader, boolean withFooter){
        this.context=context;
        this.mWithHeader=withHeader;
        this.mWithFooter=withFooter;
    }
    @Override
    public int getItemViewType(int position) {

        if (mWithHeader && isPositionHeader(position))
            return TYPE_HEADER;
        if (mWithFooter && isPositionFooter(position))
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if(viewType==TYPE_HEADER) {

            return new header(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header, viewGroup, false));
        }
        else if(viewType==TYPE_FOOTER){
            return new footer(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footer, viewGroup, false));
        }
        else {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.profile_item, viewGroup, false);
            VideoViewHolder holder = new VideoViewHolder(itemView);
            itemView.setTag(holder);
            itemView.setOnClickListener(mOnClickListener);

            return holder;
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof  header){

        }
        else if(holder instanceof  footer){
            ((footer) holder).context = context;
        }
        else {
            ProfileEntity profileEntity=getItem(position);

            ((VideoViewHolder)holder).vName.setText(profileEntity.getName());
            ((VideoViewHolder)holder).vEmail.setText(profileEntity.getEmail());
            ((VideoViewHolder)holder).vAbout.setText(profileEntity.getAbout());
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        int itemCount=0;
       if(profileEntities!=null) {


    itemCount = profileEntities.size();
    if (mWithHeader)
        itemCount = itemCount + 1;
    if (mWithFooter)
        itemCount = itemCount + 1;
   // return itemCount;
   }
return itemCount;
    }


    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }
    public void setOnClickListener(View.OnClickListener lis) {
        mOnClickListener = lis;
    }

    protected ProfileEntity getItem(int position) {
        return mWithHeader ? profileEntities.get(position - 1) : profileEntities.get(position);
    }

    private int getItemPosition(int position){
        return mWithHeader ? position - 1 : position;
    }

    public void setData(List<ProfileEntity> profileEntities) {
        this.profileEntities=profileEntities;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName,vEmail,vAbout;


        protected  Context context;

        public VideoViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.name);
            vEmail=(TextView) v.findViewById(R.id.email);
            vAbout=(TextView) v.findViewById(R.id.about);
        }

        public void clearAnimation() {
            this.clearAnimation();
        }


    }

    public class header extends RecyclerView.ViewHolder {


        protected  Context context;
        protected int position;

        public header(View v) {
            super(v);


        }


    }


    public class footer extends RecyclerView.ViewHolder {


        protected  Context context;
        protected int position;

        public footer(View v) {
            super(v);


        }


    }

}
