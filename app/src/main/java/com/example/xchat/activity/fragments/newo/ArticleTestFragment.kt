package com.example.xchat.activity.fragments.newo

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.xchat.R
import com.example.xchat.activity.adapter.AdvicePagerAdapter
import com.example.xchat.activity.adapter.ArticleAdapter
import com.example.xchat.activity.extention.AdviceData
import com.example.xchat.databinding.FragmentArticleTestBinding

class ArticleTestFragment : Fragment() {

    private var _binding: FragmentArticleTestBinding? = null
    private val binding get() = _binding!!

    private var autoScrollHandler: Handler? = null
    private var autoScrollRunnable: Runnable? = null
    private var currentPage = 0
    private val scrollDelay = 3000L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setPagerView()
        setupRecyclerView()

        binding.articleAll.setOnClickListener {
            findNavController().navigate(R.id.action_article_home_to_article)
        }

    }
    @SuppressLint("ClickableViewAccessibility")
    private fun setPagerView() {

        val adviceList = listOf(
            AdviceData(R.drawable.advice_1,R.drawable.post_1, "Measure your heart rate at the same time each day for consistent results. Stay calm for a minute before measuring for better accuracy."),
            AdviceData(R.drawable.advice_3,R.drawable.post_2, "Check your blood pressure at the same time each day for accurate results. Keeping track helps you notice trends before they become risks."),
            AdviceData(R.drawable.advice_2,R.drawable.post_3, "Eat slowly and mindfully it helps control your portions naturally. Add more fiber-rich foods like fruits and whole grains to your meals."),
            AdviceData(R.drawable.advice_4,R.drawable.post_4, "Monitor your oxygen levels to ensure your body is getting enough air. Healthy oxygen means better focus, energy, and endurance."),
            AdviceData(R.drawable.advice_5,R.drawable.post_5, "Take short breaks during work to stretch and breathe deeply. Try a 5-minute relaxation session to lower your heart rate and stress."),
            AdviceData(R.drawable.advice_6,R.drawable.post_6, "Explore your mental well-being through quick, science-based tests. Gain insights into stress, burnout, and emotional balance. Understanding your mind is the first step to a healthier life.")
        )

        binding.adviceRecycler.adapter = AdvicePagerAdapter(adviceList)
        binding.adviceRecycler.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // ✨ Smooth animation effect
        binding.adviceRecycler.setPageTransformer { page, position ->
            page.alpha = 0.5f + (1 - kotlin.math.abs(position))
            page.scaleY = 0.85f + (1 - kotlin.math.abs(position)) * 0.15f
        }

        setupAutoScroll(adviceList)
    }

    private fun setupAutoScroll(adviceList: List<AdviceData>) {
        autoScrollHandler = Handler(Looper.getMainLooper())

        autoScrollRunnable = object : Runnable {
            override fun run() {
                val itemCount = adviceList.size
                currentPage = (currentPage + 1) % itemCount

                val recyclerView = binding.adviceRecycler.getChildAt(0) as RecyclerView
                val layoutManager = recyclerView.layoutManager ?: return

                val smoothScroller = object : LinearSmoothScroller(requireContext()) {
                    override fun calculateTimeForScrolling(dx: Int): Int {
                        return 800 // 👈 control speed (higher = slower)
                    }

                    override fun calculateTimeForDeceleration(dx: Int): Int {
                        return 800
                    }

                    override fun getHorizontalSnapPreference(): Int {
                        return SNAP_TO_START
                    }
                }

                smoothScroller.targetPosition = currentPage
                layoutManager.startSmoothScroll(smoothScroller)

                autoScrollHandler?.postDelayed(this, scrollDelay)
            }
        }
    }

    private fun setupRecyclerView() {

        val list = listOf(
            AdviceData(R.drawable.advice_1,0, "Heart Rate Tips Heart Rate Tips Heart Rate Tips Heart Rate Tips"),
            AdviceData(R.drawable.advice_2,0, "Blood Pressure Guide Heart Rate Tips Heart Rate Tips Heart Rate Tips"),
            AdviceData(R.drawable.advice_3,0, "Healthy Eating Heart Rate Tips Heart Rate Tips Heart Rate Tips"),
            AdviceData(R.drawable.advice_4,0, "Mental Wellness Heart Rate Tips Heart Rate Tips Heart Rate Tips"),
            AdviceData(R.drawable.advice_5,0, "Healthy Eating Heart Rate Tips Heart Rate Tips Heart Rate Tips"),
            AdviceData(R.drawable.advice_6,0, "Mental Wellness Heart Rate Tips Heart Rate Tips Heart Rate Tips"),
        )

        val adapter = ArticleAdapter(list)

        val gridLayoutManager = androidx.recyclerview.widget.GridLayoutManager(
            requireContext(),
            2,
            RecyclerView.VERTICAL,
            false
        )

        binding.articleRecycler.apply {
            layoutManager = gridLayoutManager
            this.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        autoScrollRunnable?.let { runnable ->
            autoScrollHandler?.postDelayed(runnable, scrollDelay)
        }
    }

    override fun onPause() {
        super.onPause()
        autoScrollRunnable?.let { runnable ->
            autoScrollHandler?.removeCallbacks(runnable)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        autoScrollRunnable?.let { runnable ->
            autoScrollHandler?.removeCallbacks(runnable)
        }

        autoScrollHandler = null
        autoScrollRunnable = null
        _binding = null
    }

}