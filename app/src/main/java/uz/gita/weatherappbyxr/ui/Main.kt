package uz.gita.weatherappbyxr.ui

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.weatherappbyxr.R
import uz.gita.weatherappbyxr.presenter.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class Main : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    lateinit var btnMenu: ImageView
    lateinit var city_name: TextView
    lateinit var btnChange_city: ImageView
    lateinit var current_data: TextView
    lateinit var weather_state_image: ImageView
    lateinit var weather_state_text: TextView
    lateinit var temp_text: TextView
    lateinit var temp_day: TextView
    lateinit var temp_night: TextView
    lateinit var barChart: BarChart


    // on below line we are creating
    // a variable for bar data
    lateinit var barData: BarData

    // on below line we are creating a
    // variable for bar data set
    lateinit var barDataSet: BarDataSet

    private var lat = 41.3604195
    private var lon = 69.283817

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lat = requireArguments().getDouble("lat", 41.3604195)
        lon = requireArguments().getDouble("lon", 69.283817)

        viewModel.weatherLive.observe(this){
            city_name.text = it.name
            current_data.text = SimpleDateFormat("yyyy.MM.dd 'at' HH:mm").format(Date())

            val temp = it.main.temp.toInt().toString() + "℃"
            val temp_max = it.main.temp_max.toInt().toString() + "℃"
            val temp_min = it.main.temp_min.toInt().toString() + "℃"
            temp_text.text = temp
            temp_day.text = temp_max
            temp_night.text = temp_min
            weather_state_text.text = it.weather.first().main

            val url = "http://openweathermap.org/img/wn/${it.weather.first().icon}@4x.png"

            Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_baseline_wb_sunny_24)
                .centerCrop()
                .resize(150,150)
                .into(weather_state_image)


        }

//        viewModel.errorMessage.observe(this){
//            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
//        }

        viewModel.gotoLocations.observe(this){
            findNavController().navigate(R.id.action_main_to_locations)
        }

        viewModel.foreCastLive.observe(this){

//            it.list.forEachIndexed { index, weatherData ->
//
//
//            }

            for (index in 1..5){
                val weatherData = it.list[index]

                barEntriesList.add(BarEntry(index.toFloat(), weatherData.main.temp.toFloat()))
            }

            barDataSet = BarDataSet(barEntriesList, "Forecast data in Celsius")

            barData = BarData(barDataSet)

            // on below line we are setting data to our bar chart
            barChart.data = barData

            // on below line we are setting colors for our bar chart text
            barDataSet.valueTextColor = Color.BLACK

            // on below line we are setting color for our bar data set
            barDataSet.color = resources.getColor(R.color.teal_200)

            // on below line we are setting text size
            barDataSet.valueTextSize = 16f

            // on below line we are enabling description as false
            barChart.description.isEnabled = false

            barChart.visibility = BarChart.GONE
            barChart.visibility = BarChart.VISIBLE
        }

    }

    private val barEntriesList = ArrayList<BarEntry>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.apply {
            btnMenu = findViewById(R.id.menu)
            btnChange_city = findViewById(R.id.change_city)
            city_name = findViewById(R.id.city_name)
            current_data = findViewById(R.id.current_data)
            weather_state_text = findViewById(R.id.weather_State_text)
            weather_state_image = findViewById(R.id.weather_state_image)
            temp_text = findViewById(R.id.temp_text)
            temp_day = findViewById(R.id.temp_day_text)
            temp_night = findViewById(R.id.temp_night_text)
            barChart = findViewById(R.id.line_chart)
        }

        viewModel.getWeather(lat,lon)

        btnChange_city.setOnClickListener {
            viewModel.gotoLocations()
        }



        viewModel.getForecast(lat,lon)
    }


}