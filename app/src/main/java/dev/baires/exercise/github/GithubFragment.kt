package dev.baires.exercise.github

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import dev.baires.exercise.R
import dev.baires.exercise.core.repository.models.Projects
import dev.baires.exercise.core.view.Initializer
import kotlinx.android.synthetic.main.fragment_github.*
import javax.inject.Inject

class GithubFragment: Fragment(), ProjectAdapter.ClickListener, Initializer {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    lateinit var githubViewModel: GithubViewModel

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_github, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    override fun references() {
        this.githubViewModel = ViewModelProvider(this, viewmodelFactory).get(GithubViewModel::class.java)
        this.viewManager = LinearLayoutManager(context)
    }

    override fun actions() {
        githubViewModel.getProjects()
    }

    override fun observables() {
        githubViewModel.getProjectsData().observe(this, Observer { projects ->
            if (projects != null) {
                createList(projects)
            }
        })

        githubViewModel.error().observe(this, Observer {
            showDialogErrorMessage(context!!.resources.getString(it))
        })

        githubViewModel.loading().observe(this, Observer { loading ->
            if (loading != null) {
                showProgress(loading)
            }
        })
    }

    override fun onItemClick(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    fun createList(projects: Projects) {
        viewAdapter = ProjectAdapter(projects.items, this)
        projectList.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter =viewAdapter
        }
    }

    private fun showProgress(show: Boolean) {
        progress.visibility = if (show){
            View.VISIBLE
        }else {
            View.GONE
        }
    }

    private fun showDialogErrorMessage(message: String) {
        val dialogBuilder = AlertDialog.Builder(activity!!)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(R.string.accept) { dialog, _ ->
                dialog.dismiss()
                dialog.cancel()
                return@setPositiveButton
            }
            .create()

        dialogBuilder.show()
    }
}