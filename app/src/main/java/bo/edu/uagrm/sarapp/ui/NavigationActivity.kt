package bo.edu.uagrm.sarapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import bo.edu.uagrm.sarapp.R
import bo.edu.uagrm.sarapp.databinding.ActivityNavigationBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.FirebaseApp

class NavigationActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_navigation)
        val binding: ActivityNavigationBinding = DataBindingUtil.setContentView(this,R.layout.activity_navigation)
        FirebaseApp.initializeApp(this)

        drawerLayout = binding.drawerLayout
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController,appBarConfiguration)
        binding.navView.setupWithNavController(navController)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
