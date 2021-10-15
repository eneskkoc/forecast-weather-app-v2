package com.example.screen.ui.main

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.screen.R
import com.example.screen.data.database.entity.AdapterModel
import com.example.screen.data.database.entity.DailyEntity
import com.example.screen.data.database.entity.TempEntity
import com.example.screen.databinding.ActivityMainBinding
import com.example.screen.databinding.ForecastLayoutBinding
import com.example.screen.util.BaseAdapter
import com.example.screen.util.ConnectivityInterceptor
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    lateinit var viewmodel: MainViewModel
    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var connectivityInterceptor: ConnectivityInterceptor
    lateinit var adapter: BaseAdapter<ForecastLayoutBinding, AdapterModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        viewmodel = ViewModelProvider(this, viewModelProvider).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = object : BaseAdapter<ForecastLayoutBinding, AdapterModel>(R.layout.forecast_layout) {
            override fun bindView(binding: ForecastLayoutBinding, item: AdapterModel?, adapterPosition: Int) {
                binding.item = item
            }

            override fun clickListener(item: AdapterModel?, position: Int, binding: ForecastLayoutBinding) {
                //recycler itema click olduğunda tetiklenir
            }
        }
        binding?.geo = viewmodel
        binding?.recyclerForecast?.adapter = adapter
        viewmodel.getCurrent()
        viewmodel.data.observe(this, { state ->
            when (state) {
                is MainViewModel.State.OnCompleted -> onCompleted()
                is MainViewModel.State.OnError -> onError(state.error)
                is MainViewModel.State.OnMessage -> onMessage()
                is MainViewModel.State.OnForecast -> onDailyForecast()


            }
        })

    }

    override fun onBackPressed() {
        return
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchView = menu.findItem(R.id.appSearchBar)?.actionView as SearchView
        searchView.queryHint = "Şehir İsmini Giriniz"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (connectivityInterceptor.isConnectionOn()) {
                    viewmodel.deleteDatabase()
                    if (query.toString().length > 3) {
                        viewmodel.getName(query.toString().toLowerCase((Locale("tr", "TR"))))
                    }
                } else {
                    Toast.makeText(applicationContext, "İnternet Bağlantınızı Kontrol Ediniz", Toast.LENGTH_SHORT).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun onMessage() {
        Toast.makeText(applicationContext, viewmodel.mError, Toast.LENGTH_LONG).show()
    }

    private fun onError(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
    }

    private fun onCompleted() {
        binding?.tvCity?.text = viewmodel.mCoord?.name
        binding?.tvDay?.text = viewmodel.mCurrent?.date()
        binding?.tvTemp?.text = viewmodel.mCurrent?.tempRound().toString() + "°"
        binding?.tvDescription?.text = viewmodel.mWeatherr?.description
        onForecast()

    }

    private fun onDailyForecast() {
        binding?.tvCity?.text = viewmodel.mCoord?.name
        binding?.tvDay?.text = viewmodel.mCurrent?.date()
        binding?.tvTemp?.text = viewmodel.mCurrent?.tempRound().toString() + "°"
        binding?.tvDescription?.text = viewmodel.mWeatherr?.description
        onDaily()
    }

    private fun onDaily() {

        val daily = viewmodel.mDaily?.sortedBy { it.dt }?.map {
            AdapterModel(it, it.temp)
        }?.toCollection(ArrayList())
        adapter.items = daily


    }

    private fun onForecast() {

        val dailylist = viewmodel.mDailylist?.map {
            AdapterModel(DailyEntity(it?.dt, TempEntity(it?.temp?.max), it?.humidity), TempEntity(it?.temp?.max))
        }?.sortedBy { it.dailyEntity?.dt }?.toCollection(ArrayList())
        adapter.items = dailylist


    }


    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatchingAndroidInjector
    }
}