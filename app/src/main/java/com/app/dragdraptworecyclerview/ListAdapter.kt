package com.app.dragdraptworecyclerview


import android.content.ClipData
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_row.view.*


internal class ListAdapter(
    var list: List<ModelClass>,
    private val listener: Listener?,
    private val checkListner: CheckListner?,
    val context: Context
) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>(), View.OnTouchListener, CheckListner {
    var ischeck = false
    var pos = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.list_row, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.text!!.text = list[position].name.toString()
        holder.frameLayout!!.tag = position



        Log.e("fistruncode", list.toString())


        holder.frameLayout!!.setOnTouchListener(this)
        holder.frameLayout!!.setOnDragListener(DragListener(listener!!, checkListner!!, context))


//        if (list[position].isdrag){
//
//        }


        if (list[position].isclick) {
            holder.frameLayout!!.setBackgroundColor(Color.GREEN)

        } else {

            Handler(Looper.getMainLooper()).postDelayed({
                if (!list[position].isdrag){
                    holder.frameLayout!!.setBackgroundColor(Color.GRAY)
                }else{
                    holder.frameLayout!!.setBackgroundColor(context.resources.getColor(R.color.purple_700))
                }

            }, 500)
            holder.frameLayout!!.setBackgroundColor(context.resources.getColor(R.color.red))

        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {


        val tag = v.tag
        if (list[tag as Int].isdrag) {
            pos = tag
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val data = ClipData.newPlainText("", "")
                    val shadowBuilder = View.DragShadowBuilder(v)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        v.startDragAndDrop(data, shadowBuilder, v, 0)
                    } else {
                        v.startDrag(data, shadowBuilder, v, 0)
                    }
                    return true
                }

            }
        }


        return false
    }

    fun updateList(list: List<ModelClass>) {
        this.list = list
    }

    val dragInstance: DragListener?
        get() = if (listener != null) {
            DragListener(listener, checkListner!!, context)
        } else {
            Log.e("ListAdapter", "Listener wasn't initialized!")
            null
        }

    internal inner class ListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var text: TextView? = itemView?.findViewById(R.id.text)
        var frameLayout: FrameLayout? = itemView?.findViewById(R.id.frame_layout_item)
    }

    override fun isMatch(isture: Boolean) {
        ischeck = isture
    }
}