package org.apps.githubuser.ui.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.githubuser.R
import com.apps.githubuser.databinding.ActivityUserBinding
import org.apps.core.data.Resource
import org.apps.githubuser.ui.detail.DetailActivity
import org.apps.githubuser.ui.setting.SettingActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private val viewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userAdapter = org.apps.core.adapter.UserAdapter()
        userAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.user.observe(this) { user ->
            if (user != null) {
                when (user) {
                    is Resource.Loading -> binding.progressBar.visibility =
                        View.VISIBLE

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        userAdapter.setData(user.data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this,"Data Kosong",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite -> {
                val uri = Uri.parse("githubuser://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
            R.id.setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

