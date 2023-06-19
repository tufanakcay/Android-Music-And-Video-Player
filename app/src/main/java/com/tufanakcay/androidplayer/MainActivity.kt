package com.tufanakcay.androidplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.tufanakcay.androidplayer.configs.ApiClient
import com.tufanakcay.androidplayer.models.UserData
import com.tufanakcay.androidplayer.services.RandomUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {
    lateinit var userListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userListView = findViewById(R.id.userListView)
        val arr = mutableListOf<String>()

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr )
        userListView.adapter = adapter

        val randomUser = ApiClient.getClient().create(RandomUser::class.java)
        randomUser.allUser(10).enqueue(object : Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                val res = response.body()
                if ( res != null ) {
                    for ( item in res.results ) {
                        arr.add( item.name.first + " " + item.name.last )
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<UserData>, t: Throwable) {
                Log.e("allUser Error", t.toString())
            }
        })

        userListView.setOnItemClickListener { adapterView, view, i, l ->
            if ( i == 0 ) {
                val intent = Intent(this, MusicPlayer::class.java)
                startActivity(intent)
            }
            if ( i == 1 ) {
                val intent = Intent(this, VideoPlayer::class.java)
                startActivity(intent)
            }
        }

    }
}