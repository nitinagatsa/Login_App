package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    public static final int MENU_SORT_NAME = 1;
    public static final int MENU_SORT_AGE = 2;
    public static final int MENU_SORT_CITY = 3;

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private List<ListItem> itemList;
    ArrayList<Model.Datum> arrayList;
    private Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        dbHelper = new DatabaseHelper(this);
//        loadData();


        recyclerView = findViewById(R.id.recycler_view);

        arrayList=new ArrayList<>();
        adapter = new Adapter(this, arrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity2.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        ProgressDialog  mainProgressDialog = new ProgressDialog(MainActivity2.this);
        mainProgressDialog.setMessage("Please wait....");
        mainProgressDialog.setCancelable(false);
        mainProgressDialog.show();



        ApiInterface apiInterface3=Retrofit.getRetrofit().create(ApiInterface.class);
        apiInterface3.imageEcg().enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful()){
                    mainProgressDialog.cancel();
                    arrayList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity2.this,"Success",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(MainActivity2.this,"Fail",Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity2.this,"Fail"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                System.out.println("Error-------------"+t.getLocalizedMessage());

            }
        });









    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort_name:
                Toast.makeText(MainActivity2.this,"Name Sort is Selected",Toast.LENGTH_SHORT).show();
//                loadDataSortedByName();
                return true;

            case R.id.menu_sort_age:
                Toast.makeText(MainActivity2.this,"Age Sort is Selected",Toast.LENGTH_SHORT).show();
//                loadDataSortedByAge();
                return true;
            case R.id.menu_sort_city:
                Toast.makeText(MainActivity2.this,"City Sort is Selected",Toast.LENGTH_SHORT).show();
//                loadDataSortedByCity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



/*    private void loadData() {
        ArrayList<Model.Datum> people = dbHelper.getAllPeople();
        adapter.setData(people);
    }

    private void loadDataSortedByName() {
        ArrayList<Model.Datum> people = dbHelper.getAllPeopleSortedByName();
        adapter.setData(people);
    }

    private void loadDataSortedByAge() {
        ArrayList<Model.Datum> people = dbHelper.getAllPeopleSortedByAge();
        adapter.setData(people);
    }

    private void loadDataSortedByCity() {
        ArrayList<Model.Datum> people = dbHelper.getAllPeopleSortedByCity();
        adapter.setData(people);
    }*/
}