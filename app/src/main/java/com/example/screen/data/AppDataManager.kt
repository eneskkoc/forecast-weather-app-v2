package com.example.screen.data

import com.example.screen.data.database.WeatherDatabase
import com.example.screen.data.model.forecast.Forecast
import com.example.screen.data.model.geo.GeoItem
import com.example.screen.data.service.ApiGeo
import com.example.screen.data.service.ForecastApi
import com.example.screen.di.PerApplication
import com.example.screen.util.Constants
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerApplication
class AppDataManager @Inject constructor(
    private val mApi: ApiGeo, private val mDataBase: WeatherDatabase, private val mForecast: ForecastApi
) {
    fun geo(q: String): Observable<ArrayList<GeoItem>> {
        return mApi.getGeo(q, Constants.API_LIMIT, Constants.API_AUTHORIZATION_KEY)
    }

    fun database(): Observable<WeatherDatabase> {
        return Observable.just(mDataBase)
            .subscribeOn(Schedulers.io())
    }

    fun forecast(lat: Double?, lon: Double?): Observable<Forecast> {
        return mForecast.getForecast(
            lat, lon, Constants.API_EXCLUDE,
            Constants.API_UNITS,
            Constants.API_LANG,
            Constants.API_AUTHORIZATION_KEY
        )
    }

}