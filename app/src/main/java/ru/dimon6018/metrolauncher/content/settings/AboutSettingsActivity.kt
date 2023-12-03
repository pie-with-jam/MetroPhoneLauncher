package ru.dimon6018.metrolauncher.content.settings

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import ru.dimon6018.metrolauncher.Application
import ru.dimon6018.metrolauncher.BuildConfig
import ru.dimon6018.metrolauncher.R

class AboutSettingsActivity : AppCompatActivity() {

    private lateinit var coord: CoordinatorLayout

    private val mplVersionName: String = BuildConfig.VERSION_NAME
    private val mplVersionCode: Int = BuildConfig.VERSION_CODE
    private val model: String = Build.MODEL
    private val brand: String = Build.BRAND
    private val device: String = Build.DEVICE
    private val product: String = Build.PRODUCT
    private val hardware: String = Build.HARDWARE
    private val display: String = Build.DISPLAY
    private val time: Long = Build.TIME

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(Application.getLauncherAccentTheme())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_settings_about)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val moreInfo = findViewById<TextView>(R.id.phoneinfo_more)
        coord = findViewById(R.id.coordinator)
        val moreInfoBtn = findViewById<MaterialButton>(R.id.moreInfobtn)
        val moreInfoLayout = findViewById<LinearLayout>(R.id.moreinfoLayout)
        moreInfo.text = getString(R.string.phone_moreinfo, mplVersionName, mplVersionCode, device, brand, model, product, hardware, display, time)
        moreInfoBtn.setOnClickListener {
            moreInfoBtn.visibility = View.GONE
            moreInfoLayout.visibility = View.VISIBLE
        }
        ViewCompat.setOnApplyWindowInsetsListener(coord) { v: View, insets: WindowInsetsCompat ->
            val pB = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
            val tB = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            v.setPadding(0, tB, 0, pB)
            WindowInsetsCompat.CONSUMED
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        window.decorView.findViewById<View>(android.R.id.content).startAnimation(
                AnimationUtils.loadAnimation(this, R.anim.back_flip_anim)
        )
        finish()
    }
}
