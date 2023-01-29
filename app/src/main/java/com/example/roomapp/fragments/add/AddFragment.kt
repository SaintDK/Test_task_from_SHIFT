package com.example.roomapp.fragments.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.roomapp.R
import com.example.roomapp.database.model.Entity
import com.example.roomapp.database.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import org.json.JSONObject

class AddFragment : Fragment() {


    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.GetBinB.setOnClickListener {
            getResponsAndInsertDataToDatabase()
        }

        view.ArchiveB.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_addFragment_to_listFragment)
        }

        return view
    }

    //in the beginning, I planned to use retrofit, but decided to try something new, Valley became it.
    //didn't expect so many try catch

    var answer = ""

    private fun getResponsAndInsertDataToDatabase() {

        var main_textView = view?.findViewById(R.id.main_textView) as TextView

        val url = "https://lookup.binlist.net/${Bin_editText.text}"
        val queue = Volley.newRequestQueue(getActivity())
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                Log.d("MyLog", "Response: $response") //all what we get

                try {

                    val obj = JSONObject(response)

                    val number = obj.getJSONObject("number")
                    answer = "Number"
                    try {answer += "\nlength:   " + number.getString("length")} catch (_: Exception){}
                    try {answer += "\nluhn:   " + number.getString( "luhn")} catch (_: Exception){}


                    val scheme = obj.getString("scheme")
                    try {answer += "\n\nscheme:   " + scheme} catch (_: Exception){}

                    val type = obj.getString("type")
                    try {answer += "\ntype:   " + type} catch (_: Exception){}

                    val brand = obj.getString("brand")
                    try {answer += "\nbrand:   " + brand} catch (_: Exception){}

                    val prepaid = obj.getString("prepaid")
                    try {answer += "\nprepaid:   " + prepaid} catch (_: Exception){}


                    val country = obj.getJSONObject("country")
                    answer += "\n\nCountry"
                    try {answer += "\nnumeric:   " + country.getString("numeric")} catch (_: Exception){}
                    try {answer += "\nalpha2:   " + country.getString( "alpha2")} catch (_: Exception){}
                    try {answer += "\nname:   " + country.getString( "name")} catch (_: Exception){}
                    try {answer += "\nemoji:   " + country.getString( "emoji")} catch (_: Exception){}
                    try {answer += "\ncurrency:   " + country.getString( "currency")} catch (_: Exception){}
                    try {answer += "\nlatitude:   " + country.getString( "latitude")} catch (_: Exception){}
                    try {answer += "\nlongitude:   " + country.getString( "longitude")} catch (_: Exception){}

                    val bank = obj.getJSONObject("bank")
                    answer += "\n\nBank"
                    try {answer += "\nname:   " + bank.getString("name")} catch (_: Exception){}
                    try {answer += "\nurl:   " + bank.getString( "url")} catch (_: Exception){}
                    try {answer += "\nphone:   " + bank.getString( "phone")} catch (_: Exception){}
                    try {answer += "\ncity:   " + bank.getString( "city")} catch (_: Exception){}

                } catch (_: Exception){}

                main_textView.text = answer

                //get answer to db
                val entity = Entity(
                    0,
                    answer
                )
                mUserViewModel.addAnswer(entity)

            },
            {
                Log.d("MyLog", "Volley error: $it")
            }
        )
        queue.add(stringRequest)
    }
}
