package com.csi.bottomnavigationactivity.ui.dashboard

import android.animation.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.csi.bottomnavigationactivity.R
import com.csi.bottomnavigationactivity.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            btn0.setOnClickListener {
                if (btn01.visibility == View.VISIBLE){
                    btn01.visibility = View.GONE
                    btn02.visibility = View.GONE
                }else{
                    btn01.visibility = View.VISIBLE
                    btn02.visibility = View.VISIBLE
                }
            }

            btn1.setOnClickListener {
                val animator = ValueAnimator.ofFloat(0f, 100f)
                animator.duration = 1000
                animator.start()

                animator.addUpdateListener { animation ->
                    val animatedValue = animation.animatedValue as Float
                    btn1.translationX = animatedValue
                }
            }

            btn2.setOnClickListener {
                val objectAnimator = ObjectAnimator.ofFloat(btn2, "translationX", 100f)
                objectAnimator.duration = 1000
                objectAnimator.start()
            }

            btn3.setOnClickListener {
                val animatorSet = AnimatorSet()
                val scaleX: ValueAnimator = ObjectAnimator.ofFloat(btn3, View.SCALE_X, 0.6f)
                scaleX.repeatCount = ValueAnimator.INFINITE
                scaleX.repeatMode = ValueAnimator.REVERSE
                scaleX.duration = 1000
                val scaleY: ValueAnimator = ObjectAnimator.ofFloat(btn3, View.SCALE_Y, 0.6f)
                scaleY.repeatCount = ValueAnimator.INFINITE
                scaleY.repeatMode = ValueAnimator.REVERSE
                scaleY.duration = 1000
                animatorSet.playTogether(scaleX, scaleY)
                animatorSet.start()
            }

            btn4.setOnClickListener {
                val animatorSet = AnimatorSet()
                val scaleX: ValueAnimator = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.6f)
                scaleX.repeatCount = ValueAnimator.INFINITE
                scaleX.repeatMode = ValueAnimator.REVERSE
                scaleX.duration = 1000
                val scaleY: ValueAnimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.6f)
                scaleY.repeatCount = ValueAnimator.INFINITE
                scaleY.repeatMode = ValueAnimator.REVERSE
                scaleY.duration = 1000
                animatorSet.playTogether(scaleX, scaleY)
                animatorSet.start()
            }
            btn5.setOnClickListener {
                ViewCompat.animate(btn5)
                    .translationX(50f)
                    .translationY(100f)
                    .setDuration(1000)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .setStartDelay(50)
                    .rotation(50f)
                    .withEndAction {

                    }
                    .setListener(object :  ViewPropertyAnimatorListener {
                        override fun onAnimationStart(view: View) {

                        }

                        override fun onAnimationEnd(view: View) {

                        }

                        override fun onAnimationCancel(view: View) {

                        }
                    })
            }

            btn6.setOnClickListener {
                (AnimatorInflater.loadAnimator(context, R.animator.test_animator) as AnimatorSet).apply {
                    setTarget(btn6)
                    start()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}