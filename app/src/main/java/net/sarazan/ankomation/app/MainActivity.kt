package net.sarazan.ankomation.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.BounceInterpolator
import kotlinx.android.synthetic.main.activity_main.*
import net.sarazan.ankomation.Ankomation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        fab1.setOnClickListener {
            scaleFab(it)
        }
        fab2.setOnClickListener {
            translateFab(it)
        }
        fab3.setOnClickListener {
            complexFab(it)
        }
        fab4.setOnClickListener {
            // TODO
//            multipleViews()
        }
        fab5.setOnClickListener {
            toggleFab(it)
        }
    }

    private fun complexFab(fab: View) {
        val comeBack = !fab.translationX.isAbout(0f)

        val scale = if (comeBack) 1f else 1.5f
        val rotation = if (comeBack) 0f else 45f
        val translate = if (comeBack) 0f else 100f

        Ankomation.start {

            and {
                translate(fab) {
                    yTo = -translate
                }
                rotate(fab) {
                    to = -rotation
                }
                scale(fab) {
                    xTo = 1 / scale
                    yTo = 1 / scale
                }
            }

            then {
                translate(fab) {
                    xTo = translate
                }
                rotate(fab) {
                    to = rotation
                }
                scale(fab) {
                    xTo = scale
                    yTo = scale
                }
            }

        }
    }

    private fun toggleFab(fab: View) {
        Ankomation.start {

            alpha(fab) {
                to = 0f
            }

            scale(fab) {
                xTo = 0.2f
                yTo = xTo
            }

            then {

                alpha(fab) {
                    to = 1f
                }

                scale(fab) {
                    xTo = 1f
                    yTo = xTo
                }
            }
        }
    }

    private fun scaleFab(fab: View) {
        val shouldShow = !fab.scaleX.isAbout(1f)
        Ankomation.start {
            interpolator = BounceInterpolator()
            scale(fab) {
                xFrom = if (shouldShow) 0.2f else 1f
                yFrom = xFrom
                xTo = if (shouldShow) 1f else 0.2f
                yTo = xTo
            }
        }
    }

    private fun translateFab(fab: View) {
        val shouldShow = fab.translationX != 0f
        Ankomation.start {
            translateFraction(fab) {
                xFrom = if (shouldShow) 1f else 0f
                xTo = if (shouldShow) 0f else 1f
            }
        }
    }
}
