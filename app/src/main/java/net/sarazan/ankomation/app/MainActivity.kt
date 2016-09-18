package net.sarazan.ankomation.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
            run()
        }
    }

    private fun run() {
        Ankomation.start {
            fab5.visible()
            then {
                then {
                    fab5.alpha {
                        from = 0f
                        to = 1f
                    }
                    then {
                        fab5.gone()
                    }
                }
            }
        }
    }
}
