package com.scientiavitae.pitassistant

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.navigation.NavigationBarView
import com.scientiavitae.pitassistant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener(this)
        // Set the default tab selection when app is opened
        binding.bottomNav.selectedItemId = R.id.nav_eight_deck
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu1 -> {
            Toast.makeText(this, "Clicked the Menu 1 menu item", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu2 -> {
            Toast.makeText(this, "Clicked the Menu 2 menu item", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu3 -> {
            Toast.makeText(this, "Clicked the Menu 3 menu item", Toast.LENGTH_SHORT).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }


    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.nav_calculator -> onCalculatorClicked()
        R.id.nav_two_deck -> onTwoDeckClicked()
        R.id.nav_eight_deck -> onEightDeckClicked()
        R.id.nav_freebet -> onFreebetClicked()
        R.id.nav_carnival -> onCarnivalClicked()
        else -> false
    }


    private fun onCalculatorClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RouletteCalcFragment(), "Calculator")
        }
        return true
    }


    private fun onTwoDeckClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, TwoDeckFragment(), "TwoDeck")
        }
        return true
    }


    private fun onEightDeckClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, EightDeckFragment(), "EightDeck")
        }
        return true
    }


    private fun onFreebetClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, FreebetFragment(), "FreeBet")
        }
        return true
    }


    private fun onCarnivalClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CarnivalFragment(), "Carnival")
        }
        return true
    }


    //  TODO: How do I get the currentCount (from Main) to the appropriate Fragment that's being
    //   displayed, in order to change the chart based on the currentCount?
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        var countDirection = "RESET"
        var intent = Intent("com.scientiavitae.pitassistant.COUNT_DIRECTION")
        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> countDirection = "UP"
            KeyEvent.KEYCODE_VOLUME_DOWN -> countDirection = "DOWN"
            //KeyEvent.KEYCODE_BACK -> countDirection = "RESET"
        }
        intent.putExtra("COUNT_DIRECTION", countDirection)
        sendBroadcast(intent)
        Log.d("Broadcast", "Broadcast sent: " + countDirection + " sent.")
        return true
    }
}