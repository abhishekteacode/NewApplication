package com.example.myapplication

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.project.digicard.retrofit.RetrofitRestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowDataFragment : Fragment() {

    lateinit var showAdapter: ShowAdapter
    lateinit var recyclerView: RecyclerView
    var showData: ArrayList<ShowDatalist> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view:View= inflater.inflate(R.layout.fragment_show_data, container, false)
        recyclerView = view.findViewById(R.id.recycler_layout)

        getdataApi()
        return view
    }
    fun deletedata(id: String?, position: Int)
    {
        val nDialog: ProgressDialog
        nDialog = ProgressDialog(context)
        nDialog.setMessage("Loading..")
        nDialog.isIndeterminate = false
        nDialog.setCancelable(false)
        nDialog.show()
        if (AppUtils.isConnectedToInternet(requireContext())) {
            val paramObject = JsonObject()
            paramObject.addProperty("StringerID", id!!.toInt())
            val call: Call<GeneralResponce>
            call = RetrofitRestClient.getInstance()!!.deleteuser(paramObject)
            call.enqueue(object :Callback<GeneralResponce>{
                override fun onResponse(
                    call: Call<GeneralResponce>,
                    response: Response<GeneralResponce>
                ) {

                    val generalResponce: GeneralResponce?
                    nDialog.dismiss()
                    if (response.isSuccessful) {
                        showAdapter.notifyItemRemoved(position)
                        generalResponce = response.body()
                        Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_LONG).show()
                        Log.e("responce", "" + generalResponce!!.getStringerID())
                    } else {
                        Log.e("responce", "" + response.message())
                    }
                }

                override fun onFailure(call: Call<GeneralResponce>, t: Throwable) {

                }

            })
        }
    }

    private fun getdataApi() {

        if (AppUtils.isConnectedToInternet(requireContext())) {
            val nDialog: ProgressDialog
            nDialog = ProgressDialog(context)
            nDialog.setMessage("Loading..")
            nDialog.isIndeterminate = false
            nDialog.setCancelable(false)
            nDialog.show()
            showData.clear()
            val call: Call<List<ShowDatalist>>
            call = RetrofitRestClient.getInstance()!!.showdata()
            call.enqueue(object :Callback<List<ShowDatalist>>{
                override fun onResponse(call: Call<List<ShowDatalist>>, response: Response<List<ShowDatalist>>) {
                  val showresponce:List<ShowDatalist>
                  nDialog.dismiss()

                    showresponce= response.body()!!

                    if (response.isSuccessful)
                    {
                        showData.addAll(showresponce)
                        val linearLayoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        recyclerView.layoutManager = linearLayoutManager
                        showAdapter = ShowAdapter( requireContext(), showData, this@ShowDataFragment)
                        recyclerView.adapter = showAdapter
                    }


                }

                override fun onFailure(call: Call<List<ShowDatalist>>, t: Throwable) {

                }

            })
        }
    }

}