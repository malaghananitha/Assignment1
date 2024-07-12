package com.example.assignment1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter


class MotorPagerAdapter (private val context: Context, private val dataList: List<MotorPageData>) : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.common_cardview, null)


           /* val productName = view.findViewById<TextView>(R.id.product_name)
            val threeLinesTextView = view.findViewById<TextView>(R.id.copy_three_lines_here)
            val twoLinesTextView = view.findViewById<TextView>(R.id.copy_two_lines_here)
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            val button = view.findViewById<Button>(R.id.button)*/

            val m_fiscalYearTextView: TextView = view.findViewById(R.id.fiscalYearTextView)
            val m_dateTextView: TextView = view.findViewById(R.id.dateTextView)
            val m_camgaign_headerline: TextView = view.findViewById(R.id.camgaign_headerline)
            val m_targetPremiumTextView: TextView = view.findViewById(R.id.targetPremiumTextView)
            val m_earnedPremiumTextView: TextView = view.findViewById(R.id.earnedPremiumTextView)
            val m_targetAmountTextView: TextView = view.findViewById(R.id.targetAmountTextView)
            val m_earnedAmountTextView: TextView = view.findViewById(R.id.earnedAmountTextView)
            val m_secondDottedLineInfoIcon: ImageView = view.findViewById(R.id.secondDottedLineInfoIcon)
            val m_eligibilityStatusTextView: TextView = view.findViewById(R.id.eligibilityStatusTextView)
            val m_circularProgress: ProgressBar = view.findViewById(R.id.circularProgressBar)
            val m_d_header_name: TextView = view.findViewById<View>(R.id.diamondClubTextView).findViewById(R.id.header_name)
            val m_d_info_header_name: ImageView = view.findViewById<View>(R.id.diamondClubTextView).findViewById(R.id.info_icon)
            val m_lastAmountTextView: TextView = view.findViewById(R.id.lastAmountTextView)




            val pageData = dataList[position]


            m_fiscalYearTextView.text = pageData.m_fiscalYearString
            m_dateTextView.text = pageData.m_dateString
            m_camgaign_headerline.text = pageData.m_camgaign_headerline
            m_targetPremiumTextView.text = pageData.m_targetPremiumString
            m_earnedPremiumTextView.text = pageData.m_earnedPremiumString
            m_targetAmountTextView.text = pageData.m_targetAmountString
            m_earnedAmountTextView.text = pageData.m_earnedAmountString
            m_circularProgress.progress = pageData.m_circularProgress
            m_circularProgress.max = 100
            m_d_header_name.text = pageData.m_d_info_header_name
            m_lastAmountTextView.text = pageData.m_lastAmountString
            if(  pageData.m_isEligible ){
                m_eligibilityStatusTextView.text ="Eligible"
                m_secondDottedLineInfoIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_eligible))

            }else{
                m_eligibilityStatusTextView.text ="Not Eligible"
                m_secondDottedLineInfoIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_ineligible))

            }

            container.addView(view)

            return view
        }

        override fun getCount(): Int {
            return dataList.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

    }




data class MotorPageData(
    val m_fiscalYearString: String,
    val m_dateString: String,
    val m_camgaign_headerline: String,
    val m_targetPremiumString: String,
    val m_earnedPremiumString: String,
    val m_targetAmountString: String,
    val m_earnedAmountString: String,
    val m_isEligible: Boolean,
    val m_circularProgress: Int,
    val m_d_info_header_name: String,
    val m_lastAmountString: String,

)
