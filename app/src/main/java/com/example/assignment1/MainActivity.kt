package com.example.assignment1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: MyPagerAdapter
    private lateinit var dataList: List<PageData>  // Define your data list here



    private lateinit var  motorViewPager: ViewPager
    private lateinit var motorPagerAdapter: MotorPagerAdapter
    private lateinit var motorDataList: List<MotorPageData>  // Define your data list here

    private lateinit var dotsLayout: LinearLayout
    private lateinit var dots: Array<ImageView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_icon)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        // For hamburger icon in toolbar to open/close drawer
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        val quickAction = findViewById<View>(R.id.header_quick_action)
        val quickActionHeader = quickAction.findViewById<TextView>(R.id.header_name)
        quickActionHeader.text = "QUICK ACTION"

        // Set up the renewals quick action item
        val renewalsView = findViewById<View>(R.id.quick_action_renewals)
        val renewalsNumber = renewalsView.findViewById<TextView>(R.id.tv_number)
        val renewalsLabel = renewalsView.findViewById<TextView>(R.id.tv_label)
        val renewalsDaysLeft = renewalsView.findViewById<TextView>(R.id.tv_days_left)

        renewalsNumber.text = "12"
        renewalsLabel.text = "Renewals"
        renewalsDaysLeft.text = "07 Days"
        renewalsDaysLeft.visibility = View.VISIBLE

        // Set up the claims quick action item
        val claimsView = findViewById<View>(R.id.quick_action_claims)
        val claimsNumber = claimsView.findViewById<TextView>(R.id.tv_number)
        val claimsLabel = claimsView.findViewById<TextView>(R.id.tv_label)
        val claimsDaysLeft = claimsView.findViewById<TextView>(R.id.tv_days_left)

        claimsNumber.text = "02"
        claimsLabel.text = "Claims"
        claimsDaysLeft.visibility = View.INVISIBLE

        // Set up the payments quick action item
        val paymentsView = findViewById<View>(R.id.quick_action_payments)
        val paymentsNumber = paymentsView.findViewById<TextView>(R.id.tv_number)
        val paymentsLabel = paymentsView.findViewById<TextView>(R.id.tv_label)
        val paymentsDaysLeft = paymentsView.findViewById<TextView>(R.id.tv_days_left)

        paymentsNumber.text = "08"
        paymentsLabel.text = "Payments"
        paymentsDaysLeft.visibility = View.INVISIBLE

        // Assuming healthView is the ConstraintLayout containing the ImageView and TextView for each include

// Health
        val healthView = findViewById<View>(R.id.quick_quote_health)
        val healthImage = healthView.findViewById<ImageView>(R.id.quick_quote_image)
        val healthText = healthView.findViewById<TextView>(R.id.quick_quote_text)
        healthText.text = "Health"
        val healthDrawable = ContextCompat.getDrawable(this, R.drawable.health_icon)
        healthImage.setImageDrawable(healthDrawable)

// Motor
        val motorView = findViewById<View>(R.id.quick_quote_motor)
        val motorImage = motorView.findViewById<ImageView>(R.id.quick_quote_image)
        val motorText = motorView.findViewById<TextView>(R.id.quick_quote_text)
        motorText.text = "Motor"
        val motorDrawable = ContextCompat.getDrawable(this, R.drawable.motor_icon)
        motorImage.setImageDrawable(motorDrawable)

// Travel
        val travelView = findViewById<View>(R.id.quick_quote_travel)
        val travelImage = travelView.findViewById<ImageView>(R.id.quick_quote_image)
        val travelText = travelView.findViewById<TextView>(R.id.quick_quote_text)
        travelText.text = "Travel"
        val travelDrawable = ContextCompat.getDrawable(this, R.drawable.plane_icon)
        travelImage.setImageDrawable(travelDrawable)

// Comm.lines
        val commlinesView = findViewById<View>(R.id.quick_quote_comm_lines)
        val commlinesImage = commlinesView.findViewById<ImageView>(R.id.quick_quote_image)
        val commlinesText = commlinesView.findViewById<TextView>(R.id.quick_quote_text)
        commlinesText.text = "Comm.lines"
        val commlinesDrawable = ContextCompat.getDrawable(this, R.drawable.comm_lines)
        commlinesImage.setImageDrawable(commlinesDrawable)

        //set up for view pager
        viewPager = findViewById(R.id.viewPager)
        dataList = createDummyData() // Replace with your actual data

        pagerAdapter = MyPagerAdapter(this, dataList)
        viewPager.adapter = pagerAdapter

        val gaintSteps = findViewById<View>(R.id.gaint_steps)
        val gaintStepsHeader = gaintSteps.findViewById<TextView>(R.id.header_name)
        val gaintStepsIcon = gaintSteps.findViewById<ImageView>(R.id.info_icon)
        gaintStepsHeader.text = "GAINT STEPS"
        gaintStepsIcon.visibility = View.VISIBLE

        //for Gaint steps cardview steps
        val gaintStepsCardView =  findViewById<View>(R.id.cardViewGiantSteps)
        val gs_fiscalYearTextView: TextView =gaintStepsCardView. findViewById(R.id.fiscalYearTextView)
        val gs_dateTextView: TextView = gaintStepsCardView.findViewById(R.id.dateTextView)
        val gs_camgaign_headerline: TextView = gaintStepsCardView.findViewById(R.id.camgaign_headerline)
        val gs_targetPremiumTextView: TextView = gaintStepsCardView.findViewById(R.id.targetPremiumTextView)
        val gs_wtd_gwpLeftTextView: TextView = gaintStepsCardView.findViewById(R.id.wtd_gwpLeft)
        val gs_earnedPremiumTextView: TextView = gaintStepsCardView.findViewById(R.id.earnedPremiumTextView)
        val gs_wtd_gwpRightTextView: TextView = gaintStepsCardView.findViewById(R.id.wtd_gwpRight)
        val gs_targetAmountTextView: TextView = gaintStepsCardView.findViewById(R.id.targetAmountTextView)
        val gs_earnedAmountTextView: TextView = gaintStepsCardView.findViewById(R.id.earnedAmountTextView)
        val gs_eligibilityStatusTextView: TextView = gaintStepsCardView.findViewById(R.id.eligibilityStatusTextView)
        val gs_circularProgressBar: ProgressBar = gaintStepsCardView.findViewById(R.id.circularProgressBar)
        val gs_straightProgressBar: ProgressBar = gaintStepsCardView.findViewById(R.id.strightProgressBar)
        val gs_earnedTextView: TextView = gaintStepsCardView.findViewById(R.id.earnedTextView)
        val gs_wtdGwpTextView: TextView = gaintStepsCardView.findViewById(R.id.wtdGwpTextView)
        val gs_targetTextView: TextView = gaintStepsCardView.findViewById(R.id.targetTextView)
        val gs_earnedAmountText: TextView = gaintStepsCardView.findViewById(R.id.earnedAmountText)
        val gs_targetAmountText: TextView = gaintStepsCardView.findViewById(R.id.targetAmountText)
        val gs_diamondClubTextView = gaintStepsCardView.findViewById<View>(R.id.diamondClubTextView)
        val gs_d_bottomHeaderName: TextView = gs_diamondClubTextView.findViewById(R.id.header_name)
        val gs_lastAmountTextView: TextView = gaintStepsCardView.findViewById(R.id.lastAmountTextView)
        val gs_viewIncentiveButton: Button = gaintStepsCardView.findViewById(R.id.viewIncentiveButton)

// Set data to Gaint steps cardview views
        gs_fiscalYearTextView.text = "FY 24-25"
        gs_dateTextView.text = "As on 12 Jun'24"
        gs_camgaign_headerline.visibility = View.GONE
        gs_targetPremiumTextView.text = "Target Premium"
        gs_wtd_gwpLeftTextView.visibility = View.GONE
        gs_earnedPremiumTextView.text = "Earned Premium"
        gs_wtd_gwpRightTextView.visibility = View.GONE
        gs_targetAmountTextView.text = "₹1.1Cr"
        gs_earnedAmountTextView.text = "₹12.5L"
        gs_eligibilityStatusTextView.text = "Not Eligible"
        gs_circularProgressBar.progress = 75
        gs_circularProgressBar.max = 100
        gs_straightProgressBar.progress = 25
        gs_earnedTextView.visibility = View.VISIBLE
        gs_wtdGwpTextView.text = "WTD:GWP"
        gs_wtdGwpTextView.visibility = View.VISIBLE
        gs_targetTextView.text = "Target"
        gs_targetTextView.visibility = View.VISIBLE
        gs_straightProgressBar.visibility = View.VISIBLE
        gs_earnedAmountText.text = "50,000"
        gs_earnedAmountText.visibility = View.VISIBLE
        gs_targetAmountText.text = "2Cr"
        gs_targetAmountText.visibility = View.VISIBLE
        gs_d_bottomHeaderName.text = "1.95cr Away From Diamond Club"
        gs_lastAmountTextView.visibility = View.GONE
        gs_viewIncentiveButton.text = "View Incentive Details"

        val campaign = findViewById<View>(R.id.campaign_header)
        val campaignHeader = campaign.findViewById<TextView>(R.id.header_name)
        campaignHeader.text = "CAMPAIGNS"

        val healthCardViewHeaderView = findViewById<View>(R.id.health_cardview_header)
        val healthCardViewHeader= healthCardViewHeaderView.findViewById<TextView>(R.id.header_name)
        val healthCardIcon = healthCardViewHeaderView.findViewById<ImageView>(R.id.info_icon)
        healthCardViewHeader.text = "Health"
        healthCardIcon.visibility = View.VISIBLE

    // for health card view
        val healthCardView =  findViewById<View>(R.id.cardViewHealth)
        val h_fiscalYearTextView: TextView = healthCardView.findViewById(R.id.fiscalYearTextView)
        val h_dateTextView: TextView = healthCardView.findViewById(R.id.dateTextView)
        val h_camgaign_headerline: TextView =healthCardView.findViewById(R.id.camgaign_headerline)
        val h_targetPremiumTextView: TextView = healthCardView.findViewById(R.id.targetPremiumTextView)
        val h_earnedPremiumTextView: TextView = healthCardView.findViewById(R.id.earnedPremiumTextView)
        val h_targetAmountTextView: TextView = healthCardView.findViewById(R.id.targetAmountTextView)
        val h_earnedAmountTextView: TextView = healthCardView.findViewById(R.id.earnedAmountTextView)
        val h_secondDottedLineInfoIcon: ImageView = healthCardView.findViewById(R.id.secondDottedLineInfoIcon)
        val h_eligibilityStatusTextView: TextView = healthCardView.findViewById(R.id.eligibilityStatusTextView)
        val h_circularProgressBar: ProgressBar = healthCardView.findViewById(R.id.circularProgressBar)
        val h_diamondClubTextView = healthCardView.findViewById<View>(R.id.diamondClubTextView)
        val h_d_header_name: TextView = h_diamondClubTextView.findViewById(R.id.header_name)
        val h_d_info_icon: ImageView = h_diamondClubTextView.findViewById(R.id.info_icon)
        val h_lastAmountTextView: TextView = healthCardView.findViewById(R.id.lastAmountTextView)
        val h_viewIncentiveButton: Button = healthCardView.findViewById(R.id.viewIncentiveButton)

        h_fiscalYearTextView.text = "02 May - 02 Aug'24"
        h_dateTextView.text = "As on 12 Jun'24"
        h_camgaign_headerline.text = "Health Quarterly Campaign"
        h_targetPremiumTextView.text = "Slab Target"
        h_earnedPremiumTextView.text = "Achieved"
        h_targetAmountTextView.text = "75K"
        h_earnedAmountTextView.text = "20.5K"
        h_secondDottedLineInfoIcon.setImageResource(R.drawable.eligible_icon)
        h_eligibilityStatusTextView.text = "Eligible"
        h_circularProgressBar.progress = 90
        h_circularProgressBar.max = 100
        h_d_header_name.text = "Upcoming Slab Target (Wtd.GWP)"
        h_d_info_icon.visibility = View.VISIBLE
        h_lastAmountTextView.text = "1.5L"
        h_viewIncentiveButton.text = "View Campaign"


        val motorCardViewHeaderView = findViewById<View>(R.id.motor_cardview_header)
        val motorCardViewHeader= motorCardViewHeaderView.findViewById<TextView>(R.id.header_name)
        val motorCardIcon = motorCardViewHeaderView.findViewById<ImageView>(R.id.info_icon)
        motorCardViewHeader.text = "Motor"
        motorCardIcon.visibility = View.VISIBLE

        //set up for view pager
        motorViewPager = findViewById(R.id.motorViewPager)
        motorDataList = createDummyDataMotor() // Replace with your actual data

        motorPagerAdapter = MotorPagerAdapter(this, motorDataList)
        motorViewPager.adapter = motorPagerAdapter

        dotsLayout = findViewById(R.id.dotsLayout)

        val numDots = motorPagerAdapter.count
        dots = arrayOfNulls(numDots)

        for (i in 0 until numDots) {
            dots[i] = ImageView(this).apply {
                setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.default_dot))
                setPadding(8, 0, 8, 0)
                dotsLayout.addView(this)
            }
        }
        // Set the first dot as selected
        dots[0]?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selected_dot))

        motorViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                for (i in dots.indices) {
                    dots[i]?.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.default_dot))
                }
                dots[position]?.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.selected_dot))
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })



        val travelCardViewHeaderView = findViewById<View>(R.id.travel_cardview_header)
        val travelCardViewHeader= travelCardViewHeaderView.findViewById<TextView>(R.id.header_name)
        val travelCardIcon = travelCardViewHeaderView.findViewById<ImageView>(R.id.info_icon)
        travelCardViewHeader.text = "Travel"
        travelCardIcon.visibility = View.VISIBLE

        val travelCardView =  findViewById<View>(R.id.cardViewTravel)
        val t_fiscalYearTextView: TextView = travelCardView.findViewById(R.id.fiscalYearTextView)
        val t_dateTextView: TextView = travelCardView.findViewById(R.id.dateTextView)
        val t_camgaign_headerline: TextView = travelCardView.findViewById(R.id.camgaign_headerline)
        val t_targetPremiumTextView: TextView = travelCardView.findViewById(R.id.targetPremiumTextView)
        val t_earnedPremiumTextView: TextView = travelCardView.findViewById(R.id.earnedPremiumTextView)
        val t_targetAmountTextView: TextView = travelCardView.findViewById(R.id.targetAmountTextView)
        val t_earnedAmountTextView: TextView = travelCardView.findViewById(R.id.earnedAmountTextView)
        val t_secondDottedLineInfoIcon: ImageView = travelCardView.findViewById(R.id.secondDottedLineInfoIcon)
        val t_eligibilityStatusTextView: TextView = travelCardView.findViewById(R.id.eligibilityStatusTextView)
        val t_circularProgressBar: ProgressBar = travelCardView.findViewById(R.id.circularProgressBar)
        val t_diamondClubTextView = travelCardView.findViewById<View>(R.id.diamondClubTextView)
        val t_d_header_name: TextView = t_diamondClubTextView.findViewById(R.id.header_name)
        val t_d_info_icon: ImageView = t_diamondClubTextView.findViewById(R.id.info_icon)
        val t_lastAmountTextView: TextView = travelCardView.findViewById(R.id.lastAmountTextView)
        val t_viewIncentiveButton: Button = travelCardView.findViewById(R.id.viewIncentiveButton)

        t_fiscalYearTextView.text = "02 May - 02 Aug'24"
        t_dateTextView.text = "As on 12 Jun'24"
        t_camgaign_headerline.text = "Travel 24 Campaign"
        t_targetPremiumTextView.text = "Slab Target"
        t_earnedPremiumTextView.text = "Achieved"
        t_targetAmountTextView.text = "75K"
        t_earnedAmountTextView.text = "20.5K"
        t_secondDottedLineInfoIcon.setImageResource(R.drawable.eligible_icon)
        t_eligibilityStatusTextView.text = "Eligible"
        t_circularProgressBar.progress = 60
        t_circularProgressBar.max = 100
        t_d_header_name.text = "Upcoming Slab Target (Wtd.GWP)"
        t_d_info_icon.visibility = View.VISIBLE
        t_lastAmountTextView.text = "1.5L"
        t_viewIncentiveButton.text = "View Campaign"



        val commLinesCardViewHeaderView = findViewById<View>(R.id.comm_lines_cardview_header)
        val commLinesCardViewHeader= commLinesCardViewHeaderView.findViewById<TextView>(R.id.header_name)
        val commLinesCardIcon = commLinesCardViewHeaderView.findViewById<ImageView>(R.id.info_icon)
        commLinesCardViewHeader.text = "Comm.Lines"
        commLinesCardIcon.visibility = View.VISIBLE

        val commLinesCardView =  findViewById<View>(R.id.cardViewComm_Lines)
        val cl_fiscalYearTextView: TextView = commLinesCardView.findViewById(R.id.fiscalYearTextView)
        val cl_dateTextView: TextView = commLinesCardView.findViewById(R.id.dateTextView)
        val cl_camgaign_headerline: TextView = commLinesCardView.findViewById(R.id.camgaign_headerline)
        val cl_targetPremiumTextView: TextView = commLinesCardView.findViewById(R.id.targetPremiumTextView)
        val cl_earnedPremiumTextView: TextView = commLinesCardView.findViewById(R.id.earnedPremiumTextView)
        val cl_targetAmountTextView: TextView = commLinesCardView.findViewById(R.id.targetAmountTextView)
        val cl_earnedAmountTextView: TextView = commLinesCardView.findViewById(R.id.earnedAmountTextView)
        val cl_secondDottedLineInfoIcon: ImageView = commLinesCardView.findViewById(R.id.secondDottedLineInfoIcon)
        val cl_eligibilityStatusTextView: TextView = commLinesCardView.findViewById(R.id.eligibilityStatusTextView)
        val cl_circularProgressBar: ProgressBar = commLinesCardView.findViewById(R.id.circularProgressBar)
        val cl_diamondClubTextView = commLinesCardView.findViewById<View>(R.id.diamondClubTextView)
        val cl_d_header_name: TextView = cl_diamondClubTextView.findViewById(R.id.header_name)
        val cl_d_info_icon: ImageView = cl_diamondClubTextView.findViewById(R.id.info_icon)
        val cl_lastAmountTextView: TextView = commLinesCardView.findViewById(R.id.lastAmountTextView)
        val cl_viewIncentiveButton: Button = commLinesCardView.findViewById(R.id.viewIncentiveButton)

        cl_fiscalYearTextView.text = "02 May - 02 Aug'24"
        cl_dateTextView.text = "As on 12 Jun'24"
        cl_camgaign_headerline.text = "Comm Lines Quarterly Campaign"
        cl_targetPremiumTextView.text = "Slab Target"
        cl_earnedPremiumTextView.text = "Achieved"
        cl_targetAmountTextView.text = "₹75K"
        cl_earnedAmountTextView.text = "₹55K"
        cl_secondDottedLineInfoIcon.setImageResource(R.drawable.eligible_icon)
        cl_eligibilityStatusTextView.text = "Eligible"
        cl_circularProgressBar.progress = 75
        cl_circularProgressBar.max = 100
        cl_d_header_name.text = "Upcoming Slab Target (Wtd.GWP)"
        cl_d_info_icon.visibility = View.VISIBLE
        cl_lastAmountTextView.text = "₹25L"
        cl_viewIncentiveButton.text = "View Campaign"




    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val newItem = menu?.add(Menu.NONE, Menu.NONE, 3, "New Item")



        var drawable = getCircleDrawableWithText(
            context = this,
            string = "AB",
            textColor = android.R.color.black,
            circleColor = R.color.circle_bg,
            textSize = 34f
        )
        newItem?.setIcon(drawable)
        newItem?.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // Handle search action
                true
            }
            R.id.action_notifications -> {
                // Handle notifications action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getCircleDrawableWithText(context: Context, string: String, textColor: Int, circleColor: Int, textSize: Float): Drawable {
        // Create a drawable for the circle background
        val circleDrawable = ShapeDrawable(OvalShape())
        circleDrawable.paint.color = ContextCompat.getColor(context, R.color.circle_bg) // Circle background color
       //circleDrawable.paint.color = R.color.circle_bg
        // Create a paint for the text
        val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = ContextCompat.getColor(context, textColor) // Text color
            textAlign = Paint.Align.CENTER
            //textSize = textSize // Text size
        }
       textPaint.textSize = textSize


        // Calculate text dimensions and position
        val textWidth = textPaint.measureText(string)
        val textHeight = textPaint.descent() - textPaint.ascent()
        val textX = circleDrawable.intrinsicWidth / 2f
        val textY = circleDrawable.intrinsicHeight / 2f - textHeight / 2 - textPaint.ascent()

        // Create a custom drawable that combines the circle background and text
        val drawable = object : Drawable() {
            override fun draw(canvas: Canvas) {
                // Draw circle background
                circleDrawable.setBounds(bounds)
                circleDrawable.draw(canvas)

                // Draw text
                canvas.drawText(string, textX, textY, textPaint)
            }

            override fun setAlpha(alpha: Int) {
                // Not implemented
            }

            override fun setColorFilter(colorFilter: android.graphics.ColorFilter?) {
                // Not implemented
            }

            override fun getOpacity(): Int {
                return android.graphics.PixelFormat.OPAQUE
            }
        }

        // Set intrinsic dimensions for the drawable
        drawable.setBounds(0, 0, 100, 100) // Adjust as needed for circle size

        return drawable
    }

    private fun createDummyData(): List<PageData> {
        // Replace with your actual data creation logic
        return listOf(
            PageData( "Product 1","Product 1 Description", "Product 1 SubDescription",
                R.drawable.health_icon,"Button 1"),
            PageData("Product 2","Product 2 Description", "Product 2 SubDescription",
                R.drawable.motor_icon,  "Button 2"),
            PageData( "Product 3","Product 3 Description", "Product 3 SubDescription",
                R.drawable.plane_icon,"Button 3")
        )
    }

    private fun createDummyDataMotor(): List<MotorPageData> {

        val motorData1 = MotorPageData(  "01 Jan - 02 Mar'24",
                "As on 12 Jun'24",
               "Motor Quarterly Campaign",
                "Slab Target",
               "Achieved",
               "75K",
                 "20.5K",
                false,
                 40,
            "Upcoming Slab Target (Wtd.GWP)",
                "₹1.5L"

        )
        val motorData2 = MotorPageData(  "02 Apr - 02 Jun'24",
            "As on 12 Jun'24",
            "Motor Quarterly Campaign",
            "Slab Target",
            "Achieved",
            "75K",
            "20.5K",
            true,
            40,
            "Upcoming Slab Target (Wtd.GWP)",
            "₹1.5L"

        )
        val motorData3 = MotorPageData(  "02 Jul - 02 Sep'24",
            "As on 12 Jun'24",
            "Motor Quarterly Campaign",
            "Slab Target",
            "Achieved",
            "75K",
            "20.5K",
            false,
            70,
            "Upcoming Slab Target (Wtd.GWP)",
            "₹1.5L"

        )
        val motorData4 = MotorPageData(  "02 Oct - 02 Dec'24",
            "As on 12 Jun'24",
            "Motor Quarterly Campaign",
            "Slab Target",
            "Achieved",
            "75K",
            "20.5K",
            true,
            90,
            "Upcoming Slab Target (Wtd.GWP)",
            "₹2.5L"

        )
        return listOf(motorData1,motorData2,motorData3,motorData4)



    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

}
