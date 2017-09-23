package com.ahmedaziz.coofde.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ahmedaziz.coofde.adapter.OfferListAdapter;
import com.ahmedaziz.coofde.beans.OfferBean;
import com.ahmedaziz.coofde.config.constants;
import com.akhooo.coofde.R;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Aziz on 9/22/2017.
 */

public class StoreOffersFragment extends Fragment {
    private static final String LOG_TAG = StoreOffersFragment.class.getSimpleName();
    private static final String STORE_KEY = "store_key";
    private static final String CATEGORY_KEY = "category_key";

    @Bind(R.id.progress)
    ProgressBar progressBar;
    @Bind(R.id.coofde_status)
    TextView coofdeStatus;

    private Firebase ref;
    private OfferListAdapter adapter;


    private String mStoreKey;
    private String mCategoryKey;





    public StoreOffersFragment() {

    }

    public static StoreOffersFragment newInstance(String paramStoreKey, String paramCategoryKey) {
        StoreOffersFragment fragment = new StoreOffersFragment();
        Bundle args = new Bundle();
        args.putString(STORE_KEY, paramStoreKey);
        args.putString(CATEGORY_KEY, paramCategoryKey);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mStoreKey = getArguments().getString(STORE_KEY);
            mCategoryKey = getArguments().getString(CATEGORY_KEY);

            ref = new Firebase(constants.FIREBASE_URL).child(constants.STORES).child(mStoreKey);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_store_offers, container, false);
        ButterKnife.bind(this, rootView);

        Query queryRef = ref.orderByChild("type").equalTo(mCategoryKey);
        adapter = new OfferListAdapter(getActivity(), OfferBean.class, R.layout.item_store_details, queryRef);

        progressBar.setVisibility(ProgressBar.GONE);
        if (rootView.findViewById(R.id.offers_list_tablet) == null) {
            ListView listView = (ListView) rootView.findViewById(R.id.offers_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    setItemClick(position);
                }
            });

        } else {
            GridView gridView = (GridView) rootView.findViewById(R.id.offers_list_tablet);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    setItemClick(position);
                }
            });

        }
        return rootView;
    }

    private void setItemClick(int position) {
        OfferBean bean = adapter.getItem(position);
        if (bean != null) {
            String listId = adapter.getRef(position).getKey();
            Intent intent = new Intent(getActivity(), OfferDetailsActivity.class);
            String refPath = ref.child(listId).toString();
            intent.putExtra(constants.REF_PATH, refPath);
            intent.putExtra(constants.KEY_STORE, mStoreKey);
            intent.putExtra(constants.KEY_CATEGORY, mCategoryKey);
            intent.putExtra(constants.KEY_LIST_ID, listId);
            Log.e(LOG_TAG, "RefPATH:" + refPath);
            startActivity(intent);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.cleanup();
    }
}
