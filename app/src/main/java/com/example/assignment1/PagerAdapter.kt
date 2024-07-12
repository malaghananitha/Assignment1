package com.example.assignment1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class MyPagerAdapter(private val context: Context, private val dataList: List<PageData>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.pager_item, null)


        val productName = view.findViewById<TextView>(R.id.product_name)
        val threeLinesTextView = view.findViewById<TextView>(R.id.copy_three_lines_here)
        val twoLinesTextView = view.findViewById<TextView>(R.id.copy_two_lines_here)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val button = view.findViewById<Button>(R.id.button)

        val pageData = dataList[position]


        productName.text = pageData.prodName
        threeLinesTextView.text = pageData.prodDesc
        twoLinesTextView.text = pageData.prodSubDesc
        imageView.setImageResource(pageData.imageRes)
        button.text = pageData.buttonText

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

data class PageData(val prodName:String, val prodDesc: String, val prodSubDesc: String,
                    val imageRes: Int,  val buttonText: String)
