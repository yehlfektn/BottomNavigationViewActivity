package com.csi.bottomnavigationactivity.views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import com.csi.bottomnavigationactivity.R
import com.csi.bottomnavigationactivity.databinding.AnimatedButtonLayoutBinding

class AnimatedButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var _binding: AnimatedButtonLayoutBinding? = null
    private val binding get() = _binding!!

    init {
        _binding = AnimatedButtonLayoutBinding.inflate(
            LayoutInflater.from(context), this, true
        )
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.AnimatedButtonView,
                0,
                0
            )
            typedArray.use {
                if (it.hasValue(R.styleable.AnimatedButtonView_btn_color)) {
                    setColor(it.getColorStateList(R.styleable.AnimatedButtonView_btn_color))
                }
                if (it.hasValue(R.styleable.AnimatedButtonView_btn_text)){
                    setText(it.getString(R.styleable.AnimatedButtonView_btn_text))
                }
                if (it.hasValue(R.styleable.AnimatedButtonView_btn_style)) {
                    setStyle()
                }
            }
        }
        binding.btn.setOnClickListener {
            animateMyButton()
        }
    }

    private fun setText(value: String?) {
        binding.btn.text = value
    }

    private fun setColor(stateList: ColorStateList?) {
        binding.btn.backgroundTintList = stateList
    }

    private fun setStyle() {
        //your code
    }

    private fun animateMyButton() {
        val animatorSet = AnimatorSet()
        val scaleX: ValueAnimator = ObjectAnimator.ofFloat(binding.btn, View.SCALE_X, 0.6f)
        scaleX.repeatCount = ValueAnimator.INFINITE
        scaleX.repeatMode = ValueAnimator.REVERSE
        scaleX.duration = 1000
        val scaleY: ValueAnimator = ObjectAnimator.ofFloat(binding.btn, View.SCALE_Y, 0.6f)
        scaleY.repeatCount = ValueAnimator.INFINITE
        scaleY.repeatMode = ValueAnimator.REVERSE
        scaleY.duration = 1000
        animatorSet.playTogether(scaleX, scaleY)
        animatorSet.start()
    }
}
