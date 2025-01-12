package com.ukmtechcode.projekmobile

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : AppCompatActivity(), BeritaClickListenerRv {
    private lateinit var rvBerita: RecyclerView
    private lateinit var beritaApi: BeritaApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi RecyclerView
        rvBerita = findViewById(R.id.recycleViewBerita)
        rvBerita.setHasFixedSize(true)
        rvBerita.layoutManager = LinearLayoutManager(this)

        // Inisialisasi API
        beritaApi = BeritaHelper.getInstance().create(BeritaApi::class.java)

        // Muat data berita
        loadBerita()
    }

    private fun loadBerita() {
        CoroutineScope(Dispatchers.IO).launch {
            if (isInternetAvailable(this@MainActivity)) {
                try {
                    val response = beritaApi.getBerita()
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            val beritaList = response.body()
                            if (beritaList != null && beritaList.isNotEmpty()) {
                                setupRecyclerView(beritaList)
                            } else {
                                Toast.makeText(this@MainActivity, "Tidak ada berita tersedia", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(this@MainActivity, "Error ${response.code()}", Toast.LENGTH_LONG).show()
                        }
                    }
                } catch (e: HttpException) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Error: ${e.message()}", Toast.LENGTH_LONG).show()
                    }
                } catch (t: Throwable) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Terjadi kesalahan jaringan", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "No Internet Connection", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupRecyclerView(beritaList: List<Berita>) {
        val listBeritaAdapter = BeritaListAdapter(beritaList)
        rvBerita.adapter = listBeritaAdapter
        listBeritaAdapter.clickListener = this
    }

    override fun onItemClickListenter(view: View, berita: Berita) {
        val intentDetail = Intent(this, DetailBerita::class.java).apply {
            putExtra("testing", berita)
        }
        startActivity(intentDetail)
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR)
    }
}
