package com.example.boardgameapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.boardgameapp.databinding.ActivityMainBinding

//TODO: Exception & success handling,
//TODO: Update Profile Screen
//TODO: Implement all Dialogs
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        /*Initialize*/
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.findNavController()
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)

        // place view into the activities ViewHirarchy
        setContentView(binding.root)
    }

    /**OptionsMenu implementation
    * documentation https://developer.android.com/develop/ui/views/components/appbar
    * Tutorial https://www.youtube.com/watch?v=yLOsaR_nDrU&list=PLrnPJCHvNZuCamMFswP597mUF-whXoAA6&index=5
    * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        //This method is called when the up button is pressed. Just the pop back stack.

        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}