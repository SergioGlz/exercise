package dev.baires.exercise.core.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import dev.baires.exercise.R
import dev.baires.exercise.buttons.ButtonsFragment
import dev.baires.exercise.github.GithubFragment
import dev.baires.exercise.google.GoogleFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity(){

    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        drawerToggle = setupDrawerToggle()!!
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()
        mDrawer.addDrawerListener(drawerToggle)

        setupDrawerContent(nvDrawer)

        init(nvDrawer)
    }

    fun init(navigationView: NavigationView) {
        navigationView.setCheckedItem(R.id.nav_google_fragment)
        selectDrawerItem(navigationView.checkedItem!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item))  return true
        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    private fun selectDrawerItem(menuItem: MenuItem) {
        var fragment: Fragment? = null
        val fragmentClass: Class<*> = when (menuItem.itemId) {
            R.id.nav_google_fragment -> GoogleFragment::class.java
            R.id.nav_buttons_fragment -> ButtonsFragment::class.java
            R.id.nav_github_fragment -> GithubFragment::class.java
            else -> GoogleFragment::class.java
        }
        try {
            fragment = fragmentClass.newInstance() as Fragment
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment!!).commit()

        menuItem.isChecked = true
        title = menuItem.title
        mDrawer.closeDrawers()
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle? {
        return ActionBarDrawerToggle(
            this,
            mDrawer,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
    }
}