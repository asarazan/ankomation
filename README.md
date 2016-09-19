# ankomation

### Usage
```kotlin
ankomate {

  duration = 200L
  interpolator = AccelerateDecelerateInterpolator()

  scale(view1) {
    xFrom = 0.1f
    xTo = 1f
  }

  alpha(view1) {
    from = 0f
    to = 1f
  }

  rotate(view2) {
    from = 270
    to = 180
  }
  
  translate(view3) {
    toPercent = 100f
  }

  then {

    duration = 400L
    interpolator = AccelerateInterpolator()

    rotate(view1) {
      from = 180
      to = 360
    }

    then {
      gone(view1)
    }
  }
}
```
