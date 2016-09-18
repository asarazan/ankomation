package net.sarazan.ankomation.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.sarazan.ankomation.Ankomation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        button.setOnClickListener {
            toggleFab()
        }
    }

    private fun toggleFab() {
        val shouldShow = fab5.visibility != View.VISIBLE
        Ankomation.start {
            if (shouldShow) {
                fab5.visible()
            }
            fab5.alpha {
                from = if (shouldShow) 0f else 1f
                to = if (shouldShow) 1f else 0f
            }
            then {
                if (!shouldShow) {
                    fab5.gone()
                }
            }
        }
    }
}
