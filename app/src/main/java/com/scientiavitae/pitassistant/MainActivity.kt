package com.scientiavitae.pitassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View.*
import android.widget.Toast
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.scientiavitae.pitassistant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private var currentCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener(this)
        binding.bottomNav.selectedItemId =
            R.id.nav_eight_deck  // Set the default tab selection when app is opened
        binding.textviewCount.setOnLongClickListener {
            binding.textviewCount.text = ""
            currentCount = 0
            true
        }
        binding.textviewCount.setOnClickListener {
            Toast.makeText(this, "Click Detected. The status was: " + binding.textviewCount.visibility, Toast.LENGTH_SHORT).show()
            when (binding.textviewCount.visibility) {
                VISIBLE -> {
                    binding.textviewCount.visibility = INVISIBLE
                    Toast.makeText(this, "Changed status to INVISIBLE.", Toast.LENGTH_SHORT).show()
                }
                INVISIBLE, GONE -> {
                    binding.textviewCount.visibility = VISIBLE
                    Toast.makeText(this, "Changed status to VISIBLE." + binding.textviewCount.visibility, Toast.LENGTH_SHORT).show()
                }
            }
        }
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


    private fun onCalculatorClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RouletteCalcFragment())
        }
        return true
    }

    private fun onTwoDeckClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, TwoDeckFragment())
        }
        return true
    }

    private fun onEightDeckClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, EightDeckFragment())
        }
        return true
    }

    private fun onFreebetClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, FreebetFragment())
        }
        return true
    }

    private fun onCarnivalClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CarnivalFragment())
        }
        return true
    }

//    private fun onNewGameClicked(): Boolean {
//        supportFragmentManager.commit {
//            replace(R.id.frame_content, NewGameFragment())
//        }
//        return true
//    }


    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.nav_two_deck -> onTwoDeckClicked()
        R.id.nav_eight_deck -> onEightDeckClicked()
        R.id.nav_freebet -> onFreebetClicked()
        R.id.nav_carnival -> onCarnivalClicked()
//        R.id.nav_new_game -> onNewGameClicked()
        R.id.nav_calculator -> onCalculatorClicked()
        else -> false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                currentCount++
                binding.textviewCount.text = "Count: " + currentCount
                binding.textviewCount.visibility = VISIBLE
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                currentCount--
                binding.textviewCount.text = "Count: " + currentCount
                binding.textviewCount.visibility = VISIBLE
            }
            KeyEvent.KEYCODE_BACK -> binding.textviewCount.visibility = INVISIBLE
        }
        return true
    }
}