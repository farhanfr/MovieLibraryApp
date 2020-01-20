package com.farhanfr.movielibraryapp.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.farhanfr.movielibraryapp.R
import android.view.WindowManager
import android.os.Build
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.farhanfr.movielibraryapp.ui.favourite.fragment.ChooseFavourite
import com.farhanfr.movielibraryapp.ui.movie.fragment.MovieListFragment
import com.farhanfr.movielibraryapp.ui.tv.fragment.TvFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object{
        val STATE_FRAGMENT = "state";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
//            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.statusBarColor = Color.TRANSPARENT
        }

        //Bottom Nav
        bottom_nav.setOnNavigationItemSelectedListener { item ->
            selectedMenu(item)
            false
        }

        if (savedInstanceState != null){
            supportFragmentManager.findFragmentByTag(STATE_FRAGMENT)
        }
        else{
            val menu:Menu =bottom_nav.menu
            selectedMenu(menu.getItem(0))
        }

    }

    private fun selectedMenu(item: MenuItem) {
        item.isChecked=true
        when(item.itemId){
            R.id.movielist->{
                replaceFragment(MovieListFragment.newInstance())
            }
            R.id.tvlist->{
                replaceFragment(TvFragment.newInstance())
            }
            R.id.favourite->{
                replaceFragment(ChooseFavourite.newInstance())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        var ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flContainer,fragment,STATE_FRAGMENT)
        ft.commit()
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}
