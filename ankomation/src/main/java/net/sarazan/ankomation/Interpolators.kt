package net.sarazan.ankomation

import android.view.animation.*

object Interpolators {
    val linear = LinearInterpolator()
    val accelerate = AccelerateInterpolator()
    val decelerate = DecelerateInterpolator()
    val accelerateDecelerate = AccelerateDecelerateInterpolator()
    val overshoot = OvershootInterpolator()
    val anticipate = AnticipateInterpolator()
    val anticipateOvershoot = AnticipateOvershootInterpolator()
    val bounce = BounceInterpolator()
}