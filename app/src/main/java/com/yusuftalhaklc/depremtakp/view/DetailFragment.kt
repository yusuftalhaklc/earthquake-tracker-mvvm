package com.yusuftalhaklc.depremtakp.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.yusuftalhaklc.depremtakp.R
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private var lat : Double = 0.0
    private var lng : Double = 0.0
    private var locN:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val supportMapFragment: SupportMapFragment? =
            childFragmentManager.findFragmentByTag("mapFragment") as SupportMapFragment?
        supportMapFragment?.getMapAsync(this)

        arguments?.let {
            val dt = DetailFragmentArgs.fromBundle(it)
            val quakeVal = dt.siddet
            locN = dt.lokasyon
            DetailquakeLocation.text = dt.lokasyon
            DetailquakeLength.text = dt.derinlik.toString() +" KM Derinlikte"
            DetailquakeTime.text = dt.saat
            DetailquakeValue.text = quakeVal.toString()

            if (quakeVal>=0 && quakeVal<2) DetailquakeValue.setTextColor(Color.parseColor("#BFBDCB"))
            else if (quakeVal>=2 && quakeVal<4) DetailquakeValue.setTextColor(Color.parseColor("#8D8AFF"))
            else if (quakeVal>=4 && quakeVal<5) DetailquakeValue.setTextColor(Color.parseColor("#58D096"))
            else if (quakeVal>=5 && quakeVal<6) DetailquakeValue.setTextColor(Color.parseColor("#FECC8C"))
            else if (quakeVal>=6) DetailquakeValue.setTextColor(Color.parseColor("#F97689"))

            lat = dt.lat.toDouble()
            lng = dt.lng.toDouble()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity?.window?.statusBarColor = requireActivity().getColor(getColor(quakeVal))
            }
        }

    }
    fun getColor(quakeVal: Float) : Int{
        var colorHex = R.color.gray

        if (quakeVal>=0 && quakeVal<2) colorHex = R.color.gray
        else if (quakeVal>=2 && quakeVal<4) colorHex = R.color.purple
        else if (quakeVal>=4 && quakeVal<5) colorHex = R.color.green
        else if (quakeVal>=5 && quakeVal<6) colorHex = R.color.yellow
        else if (quakeVal>=6)colorHex = R.color.red
        return colorHex
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        val myLatLng = LatLng(lat, lng)

        mMap.addMarker(MarkerOptions().position(myLatLng).title(locN))

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 7F))
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

}