package by.it_academy.hw_008_asynktaskloader_zviagov;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<StudentItem>> {

    private MyRecyclerAdapter myRecyclerAdapter;
    private int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        myRecyclerAdapter = new MyRecyclerAdapter();
        recyclerView.setAdapter(myRecyclerAdapter);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }


    @Override
    public android.support.v4.content.Loader<ArrayList<StudentItem>> onCreateLoader(int id, Bundle args) {
        Loader<ArrayList<StudentItem>> loader = null;
        if (id == LOADER_ID) {
            loader = new MyAsyncTaskLoader(this);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<ArrayList<StudentItem>> loader, ArrayList<StudentItem> data) {
        myRecyclerAdapter.setData(data);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<ArrayList<StudentItem>> loader) {
        myRecyclerAdapter.setData(null);
    }

}
