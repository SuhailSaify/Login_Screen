package com.example.suhail.loginattempt1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suhail.loginattempt1.Models.notice_info;
import com.example.suhail.loginattempt1.R;

import com.example.suhail.loginattempt1.Utils.DownloadDemo;
import com.example.suhail.loginattempt1.Utils.DownloadManagerClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suhail on 10/20/2017.
 */

public class NoticeRecyclerViewAdapter extends RecyclerView.Adapter<NoticeRecyclerViewAdapter.MyViewHolder> implements Filterable{


    public List<notice_info> notice;
    public List<notice_info>  mFilteredList;
    private Context context;
    int layout;


    @Override
    public NoticeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        return new MyViewHolder(view);
    }

    public NoticeRecyclerViewAdapter(List<notice_info> notice, int layout, Context context) {
        mFilteredList=notice;
        this.notice = notice;
        this.layout = layout;
        this.context = context;

    }

    @Override
    public void onBindViewHolder(NoticeRecyclerViewAdapter.MyViewHolder holder, final int position) {

        holder.noticetext.setText(mFilteredList.get(position).getNotice());
        holder.notice_date.setText("Dated : " + mFilteredList.get(position).getDate());


        holder.image_pdf.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String url = mFilteredList.get(position).getPdf_url();

                        if (url != null) {

                           // DownloadDemo downloadDemo=new DownloadDemo(context);
                         //   downloadDemo.startDownload("https://static.vecteezy.com/system/resources/previews/000/100/433/non_2x/free-dummy-car-vector.png");



                            DownloadManagerClass downloadManagerClass = new DownloadManagerClass(context);
                            // downloadManagerClass.checkWriteExternalStoragePermission();

                            String filename= downloadManagerClass.Download_file(

                            //"https://static.vecteezy.com/system/resources/previews/000/100/433/non_2x/free-dummy-car-vector.png");
                                  mFilteredList.get(position).getPdf_url());

                            //downloadManagerClass.broadcast();
                        }


                    }
                }
        );

        if (mFilteredList.get(position).getImageurl() == null) {
            holder.image_pdf.setVisibility(View.GONE);
        } else

        {
            holder.image_pdf.setVisibility(View.VISIBLE);
            String imgUrl = mFilteredList.get(position).getImageurl();
            //Picasso
            Picasso.with(context).setLoggingEnabled(true);

            Picasso.with(context).load(imgUrl).into(holder.image_pdf);
            //Glide
            // Glide.with(mContext).load(imgUrl).into(holder.mImageView);

        }


    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView noticetext;
        TextView notice_date;
        ImageView image_pdf;

        public MyViewHolder(View view) {

            super(view);

//<!--------------------------------
            //binding all view
            image_pdf = (ImageView) view.findViewById(R.id.pdf_image);
            noticetext = (TextView) view.findViewById(R.id.notice);
            notice_date = (TextView) view.findViewById(R.id.notice_date);

//---------------------------------------------------!>


        }
    }



    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString==null) {

                    mFilteredList = notice;
                } else {

                    ArrayList<notice_info> filteredList = new ArrayList<>();

                    for (notice_info androidVersion : notice) {

                        if (androidVersion.getNotice().toLowerCase().contains(charString) || androidVersion.getDate().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<notice_info>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


}
