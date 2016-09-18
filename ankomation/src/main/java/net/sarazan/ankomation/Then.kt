package net.sarazan.ankomation

/**
 * Created by Aaron Sarazan on 9/18/16
 */
class Then : AnkomationSet {
    constructor(parent: AnkomationSet) : super(parent)

    override fun onStart(pass: Int): Boolean {
        return pass == 1 && super.onStart(pass)
    }
}