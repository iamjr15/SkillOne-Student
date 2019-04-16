package com.wizy.android.student.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.wizy.android.student.R
import com.wizy.android.student.model.Choice
import com.wizy.android.student.model.Student


class SingleSelectionAdapter(
    internal val context: Context,
    private val classes: MutableList<Choice>,
    private val listener: NextClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var selectedPosition: Int? = null
    private var selectedClass: Student.Standard? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        System.out.println(viewType)
        return when (viewType) {
            0 -> ClassViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_class, parent,
                    false
                )
            )
            else -> ButtonViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_next, parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return classes.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            classes.size -> {
                1// for next button
            }
            else -> {
                0//for class item
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            position < (classes.size) -> {
                val myClass: Choice = classes[position]
                val myClassViewHolder: ClassViewHolder = holder as ClassViewHolder
                myClassViewHolder.background.setCardBackgroundColor(Color.parseColor(myClass.colorString))
                myClassViewHolder.name.text = myClass.name
                myClassViewHolder.image.setImageResource(myClass.image)
                selectedPosition?.let {
                    if (position == it) {
                        myClassViewHolder.image.setColorFilter(Color.GREEN)
                    } else {
                        myClassViewHolder.image.setColorFilter(Color.WHITE)
                    }
                }
                myClassViewHolder.background.setOnClickListener {
                    selectedPosition = position
                    selectedClass = getStandard(myClass.name)
                    notifyDataSetChanged()
                }

            }
            position == (classes.size) -> {
                val btnHolder: ButtonViewHolder = holder as ButtonViewHolder
                btnHolder.btnNext.setOnClickListener {
                    if (selectedClass != null) {
                        listener.onClickNext(selectedClass!!)
                    } else {
                        showSelectClassFirst(btnHolder.btnNext)
                    }
                }
            }
        }
    }

    private fun showSelectClassFirst(btnNext: MaterialButton) {
        Snackbar.make(btnNext, context.getString(R.string.select_class_first), Snackbar.LENGTH_SHORT).show()
    }

    private fun getStandard(string: String): Student.Standard {
        return Student.Standard.valueOf(string)
    }


    class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val background: CardView = itemView.findViewById(R.id.background)
        val image: ImageView = itemView.findViewById(R.id.ivClass)
        val name: TextView = itemView.findViewById(R.id.tvClass)

    }

    class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnNext: MaterialButton = itemView.findViewById(R.id.btnNext)
    }

    @FunctionalInterface
    interface NextClickListener {
        fun onClickNext(standard: Student.Standard)
    }
}