package dev.baires.exercise.github

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.baires.exercise.R
import dev.baires.exercise.core.repository.models.Project

class ProjectAdapter(private val projectList: ArrayList<Project>): RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    private val limit = 10

    lateinit var clickListener: ClickListener

    constructor(projectList: ArrayList<Project>, clickListener: ClickListener): this(projectList) {
        this.clickListener = clickListener
    }

    class ProjectViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nameTv: TextView = view.findViewById(R.id.githubName)
        val descTv: TextView = view.findViewById(R.id.githubDescription)

        fun setContent(project: Project) {
            nameTv.text = project.full_name
            descTv.text = project.description
        }

        fun setOnClickListener(clickListener: View.OnClickListener) {
            view.setOnClickListener(clickListener)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_github, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.setContent(projectList[position])
        holder.setOnClickListener(View.OnClickListener {
            clickListener.onItemClick(projectList[position].html_url)
        })
    }

    override fun getItemCount(): Int {
        return if (projectList.size > limit) {
            limit
        } else {
            projectList.size
        }
    }

    interface ClickListener {
        fun onItemClick(url: String)
    }
}