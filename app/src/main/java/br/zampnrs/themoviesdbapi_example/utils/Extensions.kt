package br.zampnrs.themoviesdbapi_example.utils

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity?.applicationContext,
        text,
        duration
    ).show()
}

fun Double.getRating() : Float {
    return (this / (Constants.RATING_MAX / Constants.RATING_STARS)).toFloat()
}
