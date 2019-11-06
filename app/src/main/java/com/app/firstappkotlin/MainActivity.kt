package com.app.firstappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.firstappkotlin.adapter.RecyclerAdapter
import com.app.firstappkotlin.model.ResponseUser
import com.app.firstappkotlin.service.APIServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler)
        recyclerAdapter = RecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        val apiInterface = APIServices.create().getRepos()

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<List<ResponseUser>> {
            override fun onResponse(call: Call<List<ResponseUser>>?, response: Response<List<ResponseUser>>?) {

                if(response?.body() != null)
                    recyclerAdapter.setUserListItems(response.body()!!)
            }

            override fun onFailure(call: Call<List<ResponseUser>>?, t: Throwable?) {

            }
        })
    }
}

