package ne.com.hypergaragesale;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;


public class Recycler_View_Adapter extends RecyclerView.Adapter<Recycler_View_Adapter.ViewHolder> {

    Context context;
    private LayoutInflater inflater;
    private List<Data > data =Collections.emptyList();
    private Data currentData;


    public Recycler_View_Adapter(Context context,List<Data> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;

    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        public TextView mTitle;
        public TextView  mDisc;
        public TextView mprice;
       public ImageView mimageView;
        public  TextView mAddress;
        public int itemid= 0;

        public ImageView imgView;


        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);

          cv = (CardView)v.findViewById(R.id.cardView);
            mTitle = (TextView)v.findViewById(R.id.title);
            mDisc = (TextView)v.findViewById(R.id.description);
            mprice = (TextView)v.findViewById(R.id.price);
           mimageView = (ImageView)v.findViewById(R.id.imageView);
            imgView = (ImageView)v.findViewById(R.id.sampleImg);
            mAddress = (TextView)v.findViewById(R.id.address);


        }
        public void setItemId(int itemId){
            itemid =itemId;

        }
        @Override
        public void onClick(View view) {
            //Log.d(TAG, "onClick " + getPosition() + " " + mItem);
            //Intent intent = new Intent();
            Log.i("Ranjan" + view.getId(), " ");
            //int id = Integer.parseInt(view.getTag(R.string.id).toString());
            //Log.i("Alok " + id , " XX");
            view.setId(itemid);
            Intent i = new Intent(context,review_post.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("itemId",itemid);
            //i.putExtra
            context.startActivity(i);
        }
    }


    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
       // v.setOnClickListener(mOnClickListener);
        ViewHolder holder = new ViewHolder(v);
       /* v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                startActivity(intent));
            }
        });*/


        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       newPost npost = new newPost();
        Data current = data.get(position);
        this.currentData = current;
       // npost.decodeSampledBitmapFromResource(current.getImgUri(),R.id.sampleImg,50,50);
       // BitmapWorkerTask bitmapWorkerTask=new BitmapWorkerTask(holder.imgView);
        holder.mTitle.setText(current.title);
        holder.mDisc.setText(current.description);
        holder.mprice.setText(current.price);
        holder.mAddress.setText(current.getAddress());
       holder.imgView.setImageBitmap(npost.decodeSampledBitmapFromResource(current.getImgUri(),R.id.sampleImg,50,50));
       // holder.imgView.setImageBitmap(bitmapWorkerTask.doInBackground(R.id.sampleImg,50,50));
        holder.mimageView.setTag(R.string.id, current.id);
        holder.mimageView.setTag(R.string.pos, position);
        holder.itemView.setTag(R.string.id, current.id);

        Log.i("XX1 ", "YY");
        Log.i("AAA1 " +current.id, " ZZ");
        holder.setItemId(current.id);
        //int j = 1/0;
        //holder.setTag(R.string.pos, position);




    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void insert(int position, Data data1) {
        data.add(position, data1);
        notifyItemInserted(position);
    }

    public void removeDataPosition(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

}
