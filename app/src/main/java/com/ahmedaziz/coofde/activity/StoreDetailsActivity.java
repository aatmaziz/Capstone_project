package com.ahmedaziz.coofde.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ahmedaziz.coofde.Core;
import com.ahmedaziz.coofde.adapter.OfferListAdapter;
import com.ahmedaziz.coofde.adapter.OffersAdapter;
import com.ahmedaziz.coofde.beans.OfferBean;
import com.ahmedaziz.coofde.config.constants;
import com.akhooo.coofde.R;
import com.firebase.client.Firebase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Aziz on 9/22/2017.
 */

public class StoreDetailsActivity extends AppCompatActivity {
    private static final String LOG_TAG = StoreDetailsActivity.class.getSimpleName();
    private Firebase mRef;
    private String mStore;
    OfferListAdapter mAdapter;
    OffersAdapter offersAdapter;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.adView)
    AdView mAdView;
    @Bind(R.id.header_image_view)
    ImageView imageView;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
        ButterKnife.bind(this);
        mStore = this.getIntent().getStringExtra(constants.KEY_STORE);
        String backdrop = this.getIntent().getStringExtra(constants.BACKDROP);
        Picasso.with(this).load(backdrop).placeholder(R.color.colorPrimaryDark).into(imageView);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String transitionNameLogo = this.getIntent().getStringExtra(constants.TRANSITION_NAME_LOGO);
            imageView.setTransitionName(transitionNameLogo);
        }
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(mStore);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRef = new Firebase(constants.FIREBASE_URL).child(constants.STORES).child(mStore);
        Log.e(LOG_TAG, "RefPATH:" + mRef.toString());
        offersAdapter = new OffersAdapter(this, OfferBean.class, R.layout.item_store_details,
                OffersAdapter.OffersViewHolder.class, mRef);


        if (findViewById(R.id.store_offers_list_tablet) == null) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.store_offers_list_tablet);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(offersAdapter);
            offersAdapter.setOnItemClickListener(new OffersAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(View itemView, int position) {
                    setItemClick(position);
                }
            });


        } else {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.store_offers_list_tablet);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setAdapter(offersAdapter);
            offersAdapter.setOnItemClickListener(new OffersAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
                    setItemClick(position);
                }
            });

        }

        ((Core) getApplication()).startTracking();
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);

    }
    private void setupWindowAnimations() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.setDuration(1000);
            getWindow().setEnterTransition(fade);

            Fade fade1 = new Fade();
            fade1.setDuration(1000);
            getWindow().setReturnTransition(fade1);
        }
    }

    private void setItemClick(int position) {
        OfferBean bean = offersAdapter.getItem(position);
        if (bean != null) {
            String listId = offersAdapter.getRef(position).getKey();
            Intent intent = new Intent(StoreDetailsActivity.this, OfferDetailsActivity.class);
            String refPath = mRef.child(listId).toString();
            intent.putExtra(constants.REF_PATH, refPath);
            intent.putExtra(constants.KEY_STORE, mStore);
            intent.putExtra(constants.KEY_CATEGORY, bean.getType());
            intent.putExtra(constants.KEY_LIST_ID, listId);
            Log.e(LOG_TAG, "RefPATH:" + refPath);
            startActivity(intent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        offersAdapter.cleanup();
    }
}
