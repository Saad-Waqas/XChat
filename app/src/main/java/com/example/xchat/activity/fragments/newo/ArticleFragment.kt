package com.example.xchat.activity.fragments.newo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xchat.R
import com.example.xchat.activity.MainActivity
import com.example.xchat.activity.adapter.ReadAdapter
import com.example.xchat.activity.extention.BottomPaddingItemDecoration
import com.example.xchat.activity.extention.ReadData
import com.example.xchat.databinding.FragmentArticleBinding
import com.example.xchat.databinding.FragmentArticleTestBinding
import kotlin.text.toInt
class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setReadingAdapter()

    }

    private fun setReadingAdapter(){

        val paddingPx = (100 * resources.displayMetrics.density).toInt()

        val adapter = ReadAdapter()

        binding.reArticle.layoutManager = LinearLayoutManager(requireContext())
        binding.reArticle.adapter = adapter
        binding.reArticle.addItemDecoration(
            BottomPaddingItemDecoration(paddingPx)
        )

        val listRead = listOf(

            ReadData(
                "Hypotension",
                "Hypotension refers to low blood pressure, usually considered lower than 90/60 mmHg. Some people naturally have low blood pressure without symptoms, while others may experience dizziness, fainting, blurred vision, fatigue, nausea, or difficulty concentrating. It can happen because of dehydration, blood loss, certain medications, heart conditions, or long periods of standing. Maintaining proper hydration, eating balanced meals, and standing up slowly may help reduce symptoms. In severe cases, hypotension can limit blood flow to vital organs and require medical attention. Monitoring blood pressure regularly can help identify unusual patterns and support better overall cardiovascular health."
            ),

            ReadData(
                "Normal Ranges",
                "A normal blood pressure range for most adults is generally around 120/80 mmHg. The top number, called systolic pressure, measures pressure when the heart beats, while the bottom number, diastolic pressure, measures pressure when the heart rests between beats. Healthy blood pressure supports proper circulation and lowers the risk of heart disease, stroke, and kidney problems. Regular physical activity, balanced nutrition, reduced salt intake, proper sleep, and stress management all contribute to maintaining healthy readings. Blood pressure may naturally vary throughout the day depending on activity, emotions, hydration, and overall physical condition."
            ),

            ReadData(
                "Elevated blood pressure reading",
                "An elevated blood pressure reading means the pressure inside the arteries is higher than normal but not yet classified as hypertension. This stage is often considered a warning sign that lifestyle changes may be needed to prevent future cardiovascular problems. Elevated readings can be influenced by stress, excessive salt intake, lack of exercise, smoking, alcohol consumption, or poor sleep habits. People with elevated blood pressure may not notice any symptoms, making regular monitoring important. Improving diet, increasing physical activity, managing stress, and maintaining a healthy weight can help lower blood pressure and reduce long-term health risks."
            )

        )
        adapter.submitList(listRead)

    }

    override fun onResume() {
        super.onResume()

        (activity as? MainActivity)?.hideSystemBars()
    }

    override fun onPause() {
        super.onPause()

        (activity as? MainActivity)?.showSystemBars()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}