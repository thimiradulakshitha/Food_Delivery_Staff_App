package com.development.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.app.adapter.MyShippingOrderAdapter
import com.development.app.Common.Common
import com.development.app.Model.ShippingOrderModel
import com.development.app.R

class HomeFragment : Fragment() {

    var recycler_order:RecyclerView?=null
   // var layoutAnimationController: LayoutAnimationController? = null
    var adapter: MyShippingOrderAdapter? = null

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initViews(root)
        homeViewModel!!.messageError.observe(this, Observer { s:String -> Toast.makeText(context, s, Toast.LENGTH_SHORT).show() })
        homeViewModel!!.getOrderModelMutableLiveData(Common.currentShipperUser!!.phone!!)
            .observe(this, Observer { shippingOrderModels:List<ShippingOrderModel> ->
                adapter = MyShippingOrderAdapter(context!!, shippingOrderModels)
                recycler_order!!.adapter = adapter
                //recycler_order!!.layoutAnimation = layoutAnimationController
            })
        return root
    }

    private fun initViews(root: View?) {
        recycler_order = root!!.findViewById(R.id.recycler_order) as RecyclerView
        recycler_order!!.setHasFixedSize(true)
        recycler_order!!.layoutManager = LinearLayoutManager(context)
      //  layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.nav_default_enter_anim)
    }
}