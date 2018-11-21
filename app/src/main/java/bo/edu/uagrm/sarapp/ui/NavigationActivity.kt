package bo.edu.uagrm.sarapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import bo.edu.uagrm.sarapp.R
import com.google.android.material.navigation.NavigationView

class NavigationActivity : AppCompatActivity() {
    private var drawerLayout: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        // Set up Action Bar
        navController = host.navController
        setupActionBar(navController as NavController)
        setupNavigationMenu(navController as NavController)

    }


    private fun setupActionBar(navController: NavController) {
        // TODO STEP 9.5 - Have NavigationUI handle what your ActionBar displays
//        // This allows NavigationUI to decide what label to show in the action bar
//        // And, since we are passing in drawerLayout, it will also determine whether to
//        // show the up arrow or drawer menu icon
        drawerLayout = findViewById(R.id.drawer_layout)
        setupActionBarWithNavController(navController, drawerLayout)
        // TODO END STEP 9.5
    }


    private fun setupNavigationMenu(navController: NavController) {
        val sideNavView = findViewById<NavigationView>(R.id.nav_view)
        sideNavView?.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return drawerLayout.navigateUp(findNavController(R.id.nav_host_fragment))
    }

}
