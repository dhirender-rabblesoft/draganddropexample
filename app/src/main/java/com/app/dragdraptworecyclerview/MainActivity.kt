package com.app.dragdraptworecyclerview


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Listener,CheckListner {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTopRecyclerView()
        initBottomRecyclerView()
        tvEmptyListTop!!.visibility = View.GONE
        tvEmptyListBottom!!.visibility = View.GONE
    }

    private fun initTopRecyclerView() {
        rvTop!!.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        val topList: MutableList<ModelClass> = ArrayList()
        topList.add(ModelClass('A'))
        topList.add(ModelClass('B'))
        topList.add(ModelClass('C'))
        topList.add(ModelClass('D'))
        val topListAdapter = ListAdapter(topList, this,this,this)
        rvTop!!.adapter = topListAdapter

        tvEmptyListTop!!.setOnDragListener(topListAdapter.dragInstance)
        rvTop!!.setOnDragListener(topListAdapter.dragInstance)
    }

    private fun initBottomRecyclerView() {
        rvBottom!!.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        val bottomList: MutableList<ModelClass> = ArrayList()
        bottomList.add(ModelClass('A'))
        bottomList.add(ModelClass('B'))
        bottomList.add(ModelClass('C'))
        bottomList.add(ModelClass('D'))


        val bottomListAdapter = ListAdapter2(bottomList, this,this,this)
        rvBottom!!.adapter = bottomListAdapter
    }

    override fun setEmptyListTop(visibility: Boolean) {
        tvEmptyListTop?.setVisibility(if (visibility) View.VISIBLE else View.GONE)
        rvTop?.setVisibility(if (visibility) View.GONE else View.VISIBLE)
    }

    override fun setEmptyListBottom(visibility: Boolean) {
        tvEmptyListBottom?.setVisibility(if (visibility) View.VISIBLE else View.GONE)
        rvBottom?.setVisibility(if (visibility) View.GONE else View.VISIBLE)
    }

    override fun setpostion(position: Boolean) {
        if (position){

        }else{

        }

    }

    override fun isMatch(isture: Boolean) {

    }
}