package com.example.soundvista

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.soundvista.fragments.InicioFragment
import com.example.soundvista.fragments.RegistroFragment
import com.google.android.material.tabs.TabLayout

/**
 * The number of pages (wizard steps) to show in this demo.
 */
private const val NUM_PAGES = 2
class InicioActivity : FragmentActivity() {

    /**
     * The pager widget, which handles animation and allows swiping horizontally
     * to access previous and next wizard steps.
     */
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: ScreenSlidePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_inicio)

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.loginViewPager)
        tabLayout = findViewById(R.id.indicador)

        // The pager adapter, which provides the pages to the view pager widget.
        adapter = ScreenSlidePagerAdapter(supportFragmentManager, lifecycle)

        viewPager.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager.currentItem = tab.position
                    updateViewPagerHeight(tab.position)
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }

        })

    }

    private fun updateViewPagerHeight(tabPosition: Int) {

        // Puedes obtener el índice de la pestaña seleccionada
        val selectedIndex = tabPosition

        // Dependiendo del índice, puedes cambiar el tamaño del ViewPager
        // Obtener el `LayoutParams` actual del ViewPager
        val params: ViewGroup.LayoutParams = viewPager.layoutParams

        // Modificar el ancho y alto según sea necesario
        //params.width = newWidthInPixels // Puedes usar ViewGroup.LayoutParams.MATCH_PARENT o un valor específico en píxeles
        //params.height = newHeightInPixels // Puedes usar ViewGroup.LayoutParams.WRAP_CONTENT o un valor específico en píxeles




        val newHeight = // Dependiendo del índice, puedes cambiar las cualidades del ViewPager
            /*when (selectedIndex) {
                0 -> {
                    // Código para la primera pestaña
                    // Por ejemplo, cambiar el color del fondo del ViewPager
                    //viewPager.setBackgroundColor(ContextCompat.getColor(this@InicioActivity, R.color.azulClaro))
                    // Código para la primera pestaña
                    // Por ejemplo, ajustar el tamaño del ViewPager
                    //params.width = ViewGroup.LayoutParams.MATCH_PARENT

                }
                1 -> {
                    // Código para la segunda pestaña
                    // Por ejemplo, cambiar el color del fondo del ViewPager
                    //viewPager.setBackgroundColor(ContextCompat.getColor(this@InicioActivity, R.color.azulOscuro))
                    // Código para la primera pestaña
                    // Por ejemplo, ajustar el tamaño del ViewPager
                    //params.width = ViewGroup.LayoutParams.MATCH_PARENT

                }
                // Agrega más casos según sea necesario
                else ->{

                }
            }*/

            if (tabPosition == 0) {
            // Tamaño deseado para el fragmento de inicio
            //params.height = 100
            resources.getDimensionPixelSize(R.dimen.viewPagerAlto2)
        } else {
            // Tamaño deseado para el fragmento de registro
            resources.getDimensionPixelSize(R.dimen.viewPagerAlto1)
            //params.height = 500
        }

        adapter.setViewPagerHeight(newHeight)
        //viewPager.layoutParams.height = params.height

        // Aplicar los cambios al ViewPager
        viewPager.layoutParams = params

        //viewPager.requestLayout()
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle
            // the Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fa: FragmentManager, li: Lifecycle) :
        FragmentStateAdapter(fa, li) {

        private var viewPagerHeight: Int = ViewGroup.LayoutParams.WRAP_CONTENT

        fun setViewPagerHeight(height: Int) {
            //notifyDataSetChanged()
            viewPagerHeight = height

        }

        override fun getItemCount(): Int {
            return NUM_PAGES
        }

        override fun createFragment(position: Int): Fragment {
            return if (position == 0)
                InicioFragment()
            else
                RegistroFragment()
        }

        override fun getItemId(position: Int): Long {
            // Cambiar el ID según la posición para forzar la recreación del fragmento
            return super.getItemId(position) + viewPagerHeight
        }
    }

}