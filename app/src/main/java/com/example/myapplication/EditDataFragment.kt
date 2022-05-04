package com.example.myapplication

import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.JsonObject
import com.project.digicard.retrofit.RetrofitRestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class EditDataFragment : Fragment() {

    lateinit var submit_btn: Button
   lateinit var name_age: EditText
    lateinit  var add_address:EditText
    lateinit  var add_age:EditText
    lateinit  var add_phone:EditText
    lateinit  var add_shopid:EditText
    lateinit   var add_password:EditText
    var hour = 0
    var min:Int = 0
    lateinit  var starttime: TextView
    lateinit  var endtime:TextView
    lateinit var id: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View= inflater.inflate(R.layout.fragment_edit_data, container, false)

        name_age = view.findViewById(R.id.name_age)
        starttime = view.findViewById(R.id.start_date_season)
        endtime = view.findViewById(R.id.end_date_season)

        add_address = view.findViewById(R.id.add_address)
        add_age = view.findViewById(R.id.add_age)
        add_phone = view.findViewById(R.id.add_phone)
        add_shopid = view.findViewById(R.id.add_shopid)
        add_password = view.findViewById(R.id.add_password)
        submit_btn = view.findViewById(R.id.submit_btn)

        val bundle = this.arguments
        if (bundle != null) {
            if (bundle.getString("YourKey") != null) {
                id = bundle.getString("YourKey")!!
            }
            if (bundle.getString("YourKey1") != null) {
                name_age.setText(bundle.getString("YourKey1"))
            }
            if (bundle.getString("YourKey2") != null) {
                add_age.setText(bundle.getString("YourKey2"))
            }
            if (bundle.getString("YourKey3") != null) {
                add_phone.setText(bundle.getString("YourKey3"))
            }
            if (bundle.getString("YourKey4") != null) {
                add_password.setText(bundle.getString("YourKey4"))
            }
            if (bundle.getString("YourKey5") != null) {
                starttime.text = bundle.getString("YourKey5")
            }
            if (bundle.getString("YourKey6") != null) {
                endtime.text = bundle.getString("YourKey6")
            }
            if (bundle.getString("YourKey7") != null) {
                add_address.setText(bundle.getString("YourKey6"))
            }
        }


        starttime.setOnClickListener(View.OnClickListener {


            val mcurrentTime = Calendar.getInstance()
            hour = mcurrentTime[Calendar.HOUR_OF_DAY]
            min = mcurrentTime[Calendar.MINUTE]
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(context,
                { timePicker, selectedHour, selectedMinute -> //starttime.setText( selectedHour + ":" + selectedMinute);
                    if (selectedHour < 10) {
                        if (selectedMinute < 10) {
                            Log.e("min", "less")
                            starttime.text = "0$selectedHour.0$selectedMinute"
                        } else {
                            Log.e("min", "more$selectedMinute")
                            starttime.text = "0$selectedHour.$selectedMinute"
                        }
                    } else {
                        if (selectedMinute < 10) {
                            starttime.text = "$selectedHour.0$selectedMinute"
                        } else {
                            starttime.text = "$selectedHour.$selectedMinute"
                        }
                    }
                    hour = selectedHour
                    min = selectedMinute
                }, hour, min, true
            ) //Yes 24 hour time

            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        })

        endtime.setOnClickListener(View.OnClickListener {
            val mcurrentTime = Calendar.getInstance()
            hour = mcurrentTime[Calendar.HOUR_OF_DAY]
            min = mcurrentTime[Calendar.MINUTE]
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(context,
                { timePicker, selectedHour, selectedMinute -> //endtime.setText( selectedHour + ":" + selectedMinute);
                    if (selectedHour < 10) {
                        if (selectedMinute < 10) {
                            endtime.text = "0$selectedHour.0$selectedMinute"
                        } else {
                            endtime.text = "0$selectedHour.$selectedMinute"
                        }
                    } else {
                        if (selectedMinute < 10) {
                            endtime.text = "$selectedHour.0$selectedMinute"
                        } else {
                            endtime.text = "$selectedHour.$selectedMinute"
                        }
                    }
                    hour = selectedHour
                    min = selectedMinute
                }, hour, min, true
            ) //Yes 24 hour time

            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        })

        submit_btn.setOnClickListener(View.OnClickListener {

            calleditapi()
        })
    return view
    }

    private fun calleditapi() {
        if (AppUtils.isConnectedToInternet(requireContext())) {
            val nDialog: ProgressDialog
            nDialog = ProgressDialog(context)
            nDialog.setMessage("Loading..")
            nDialog.isIndeterminate = false
            nDialog.setCancelable(false)
            nDialog.show()
            val paramObject = JsonObject()
            paramObject.addProperty("Address", add_address.text.toString())
            paramObject.addProperty("Age", add_age.text.toString())
            paramObject.addProperty("CloseTiming", endtime.text.toString())
            paramObject.addProperty("Name", name_age.text.toString())
            paramObject.addProperty("PhoneNumber", add_phone.text.toString())
            paramObject.addProperty("StartTiming", starttime.text.toString())

            paramObject.addProperty("Password", add_password.text.toString())
            paramObject.addProperty("StringerID", id)
            paramObject.addProperty("UpdatedBy", "1")
            val call: Call<GeneralResponce>
            call = RetrofitRestClient.getInstance()!!.edituser(paramObject)
            call.enqueue(object :Callback<GeneralResponce>{
                override fun onResponse(
                    call: Call<GeneralResponce>,
                    response: Response<GeneralResponce>
                ) {
                    nDialog.dismiss()
                    val generalResponce:GeneralResponce
                    if (response.isSuccessful)
                    {
                        generalResponce= response.body()!!
                        Toast.makeText(context, "Udated Successfully", Toast.LENGTH_LONG).show()
                        val handler = Handler(Looper.getMainLooper())
                        handler.postDelayed({
                            val seasonedit = ShowDataFragment()
                            (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                                .replace(R.id.fragContainer, seasonedit).commit()
                        }, 1000)
                    }
                    else{
                        Toast.makeText(context,"Exception Fired",Toast.LENGTH_LONG).show();
                    }
                }

                override fun onFailure(call: Call<GeneralResponce>, t: Throwable) {
                    nDialog.dismiss()
                }

            })
        }
    }


}