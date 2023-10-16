package org.apps.githubuser.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.apps.githubuser.R
import com.apps.githubuser.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import org.apps.core.domain.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private var statusFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.detail_user)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailUser = intent.getParcelableExtra<User>(EXTRA_DATA)
        if (detailUser != null) {
            statusFavorite = viewModel.getFavoriteStatus(detailUser)
        }

        showDetailUser(detailUser)
        updateFavoriteButtonStatus()

        binding.fabFavorite.setOnClickListener {
            statusFavorite = !statusFavorite
            updateFavoriteButtonStatus()
            if (detailUser != null) {
                viewModel.setFavoriteUser(detailUser, statusFavorite)
            }
        }
    }

    private fun showDetailUser(detailUser: User?) {
        detailUser?.let {
            binding.apply {
                tvUsername.text = detailUser.login
                link.text = detailUser.html_url

                link.text = HtmlCompat.fromHtml("<a href='${detailUser.html_url}'>${detailUser.html_url}</a>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                link.movementMethod = LinkMovementMethod.getInstance()

                Glide.with(this@DetailActivity)
                    .load(detailUser.avatarUrl)
                    .centerCrop()
                    .into(imgDetailUser)
            }
        }
    }

    private fun updateFavoriteButtonStatus() {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        this.onBackPressed()
        return true
    }
}