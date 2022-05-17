package com.app.dragdraptworecyclerview


import android.content.Context
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DragListener internal constructor(
    listener: Listener,
    checkListner: CheckListner,
    val context: Context
) : View.OnDragListener {
    private var isDropped = false
    private val listener: Listener = listener
    private val checkListner: CheckListner = checkListner
    override fun onDrag(v: View, event: DragEvent): Boolean {
        when (event.action) {
            DragEvent.ACTION_DROP -> {
                isDropped = true
                var positionTarget = -1
                val viewSource: View = event.localState as View
                val viewId: Int = v.getId()
                val flItem: Int = R.id.frame_layout_item
                val tvEmptyListTop: Int = R.id.tvEmptyListTop
                val tvEmptyListBottom: Int = R.id.tvEmptyListBottom
                val rvTop: Int = R.id.rvTop
                val rvBottom: Int = R.id.rvBottom
                when (viewId) {
                    flItem, tvEmptyListTop, tvEmptyListBottom, rvTop, rvBottom -> {
                        var target: RecyclerView
                        when (viewId) {
                            tvEmptyListTop, rvTop -> target = v.getRootView().findViewById(rvTop)
                            tvEmptyListBottom, rvBottom -> target =
                                v.getRootView().findViewById(rvBottom)
                            else -> {

                                target = v.getParent() as RecyclerView
                                positionTarget = v.getTag() as Int
                            }
                        }
                        if (viewSource != null) {
                            val source = viewSource.getParent() as RecyclerView
                            val adapterSource: ListAdapter? = source.adapter as ListAdapter?
                            val positionSource = viewSource.getTag() as Int
                            val sourceId = source.id
                            val list: ModelClass = adapterSource?.list?.get(positionSource)!!
                            val listSource: ArrayList<ModelClass> =
                                adapterSource.list as ArrayList<ModelClass>
                            listSource[positionSource].isdrag = false
                            Keys.postion = positionSource

                            Log.e("fistruncode", "111111111111111111")

//                            listSource.removeAt(positionSource)

//                            adapterSource.updateList(listSource)
                            Log.e("adaptersource-- ", adapterSource.toString())
                            adapterSource.notifyDataSetChanged()

                            val adapterTarget: ListAdapter2? = target.adapter as ListAdapter2?
                            val customListTarget: MutableList<ModelClass> =
                                adapterTarget?.list as MutableList<ModelClass>


                            if (positionTarget >= 0) {
                                when (list.name) {
                                    'A' -> {
                                        if (positionTarget.equals(0)) {
                                            val toast = Toast.makeText(
                                                context,
                                                "Hello Javatpoint",
                                                Toast.LENGTH_LONG
                                            )
                                            toast.show()
                                        }

                                    }

                                    'B' -> {
                                        if (positionTarget.equals(1)) {
                                            val toast = Toast.makeText(
                                                context,
                                                "Hello Javatpoint",
                                                Toast.LENGTH_LONG
                                            )
                                            toast.show()
//                                               customListTarget.add(positionTarget, list)
                                        }

                                    }

                                    'C' -> {
                                        if (positionTarget.equals(2)) {
                                            val toast = Toast.makeText(
                                                context,
                                                "Hello Javatpoint",
                                                Toast.LENGTH_LONG
                                            )
                                            toast.show()
//                                               customListTarget.add(positionTarget, list)
                                        }

                                    }
                                    'D' -> {
                                        if (positionTarget.equals(3)) {
                                            Keys.postion = positionSource
                                            val toast = Toast.makeText(
                                                context,
                                                "Hello Javatpoint",
                                                Toast.LENGTH_LONG
                                            )
                                            toast.show()
                                            listSource[positionSource].isdrag = false
                                            checkListner.isMatch(true)
                                            customListTarget[positionTarget].isclick = true
                                            Keys.isclick = true
//                                               customListTarget.add(positionTarget, list)
                                        } else {
//
                                            listSource[positionSource].isdrag = false
                                            Keys.postion = positionSource
                                            listSource.add(list)
                                            adapterSource.updateList(listSource)
                                            listener.setpostion(true)
                                            checkListner.isMatch(false)
                                            customListTarget[positionTarget].isclick = false
                                            Keys.isclick = false
                                            Log.e(
                                                "cutomlisttart-- ",
                                                "erererewrwerewrwerwfcewfdsfds"
                                            )

                                        }

                                    }
                                    else -> {

                                        listSource.add(list)
                                        adapterSource.updateList(listSource)
                                        Log.e("cutomlisttart-- ", "erererewrwerewrwerwfcewfdsfds")
                                    }
                                }
                            } else {
                                customListTarget.add(list)
                            }
                            adapterTarget.updateList(customListTarget)
                            adapterTarget.notifyDataSetChanged()




                            if (sourceId == rvBottom && adapterSource.getItemCount() < 1) {
                                listener.setEmptyListBottom(true)
                            }
                            if (viewId == tvEmptyListBottom) {
                                listener.setEmptyListBottom(false)
                            }
                            if (sourceId == rvTop && adapterSource.getItemCount() < 1) {
                                listener.setEmptyListTop(true)
                            }
                            if (viewId == tvEmptyListTop) {
                                listener.setEmptyListTop(false)
                            }
                        }
                    }
                }
            }
        }
        if (!isDropped && event.localState != null) {
            (event.localState as View).setVisibility(View.VISIBLE)
        }
        return true
    }

}