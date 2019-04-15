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
import com.wizy.android.student.model.Student
import com.wizy.android.student.model.StudentSubject

class SubjectAdapter(
    internal val context: Context,
    private val subjects: MutableList<StudentSubject>,
    private val listener: NextClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var selectedSubjects: MutableList<Student.Subject> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        System.out.println(viewType)
        return when (viewType) {
            0 -> SubjectViewHolder(
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
        return subjects.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            subjects.size -> {
                1// for next button
            }
            else -> {
                0//for class item
            }
        }
    }

    override fun onBindViewHolder(recyclerViewHolder: RecyclerView.ViewHolder, position: Int) {
        when {
            position < (subjects.size) -> {
                val holder: SubjectViewHolder = recyclerViewHolder as SubjectViewHolder
                val subject: StudentSubject = subjects[position]
                holder.background.setCardBackgroundColor(Color.parseColor(subject.colorString))
                holder.name.text = subject.name
                holder.image.setImageResource(subject.image)
                holder.background.setOnClickListener {
                    val sub: Student.Subject = getSubject(subject.name)
                    if (selectedSubjects.contains(sub)) {
                        selectedSubjects.remove(sub)
                        holder.image.setColorFilter(Color.WHITE)
                    } else {
                        selectedSubjects.add(sub)
                        holder.image.setColorFilter(Color.GREEN)
                    }
                }

            }
            position == (subjects.size) -> {
                val btnHolder: ButtonViewHolder = recyclerViewHolder as ButtonViewHolder
                btnHolder.btnNext.setOnClickListener {
                    if (selectedSubjects.size>0) {
                        listener.onClickNext(selectedSubjects)
                    } else {
                        selectSubjectFirst(btnHolder.btnNext)
                    }
                }
            }
        }
    }

    private fun getSubject(string: String): Student.Subject {
        return Student.Subject.valueOf(string)

    }

    private fun selectSubjectFirst(btnNext: MaterialButton) {
        Snackbar.make(btnNext, context.getString(R.string.select_atleast_one_subject), Snackbar.LENGTH_SHORT).show()
    }
    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val background: CardView = itemView.findViewById(R.id.background)
        val image: ImageView = itemView.findViewById(R.id.ivClass)
        val name: TextView = itemView.findViewById(R.id.tvClass)

    }

    class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnNext: MaterialButton = itemView.findViewById(R.id.btnNext)
    }

    @FunctionalInterface
    interface NextClickListener {
        fun onClickNext(subjects: MutableList<Student.Subject>)
    }
}