package com.example.componentsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Spinner
import android.widget.Toast
import com.example.componentsapp.R
import com.example.componentsapp.adapters.MoviesAdapter
import com.example.componentsapp.models.MoviesItem

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JuanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JuanFragment : Fragment(), AdapterView.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var arrayList: ArrayList<MoviesItem> ? = null
    private var gridView: GridView ? = null
    private var moviesAdapter: MoviesAdapter ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_juan, container, false)

        ///Para usar el findViewById en fragments es necesesario implementar
        //el view?
        gridView = view.findViewById(R.id.gridView)
        arrayList = ArrayList()
        arrayList = setDataList()
        moviesAdapter = MoviesAdapter(requireContext(), arrayList!!)
        gridView?.adapter = moviesAdapter
        gridView?.onItemClickListener = this

        // Inflate the layout for this fragment
        return view

    }

    //Funcion para llenar los ImageView y TextView
    private fun setDataList() : ArrayList<MoviesItem>{

        var arrayList: ArrayList<MoviesItem> = ArrayList()

        arrayList.add(MoviesItem(R.drawable.creyente, name = "Creyente 2 (2023)") )
        arrayList.add(MoviesItem(R.drawable.down, name = "Down Low (2023)") )
        arrayList.add(MoviesItem(R.drawable.elsindicato, name = "El Sindicato del c (2019)") )
        arrayList.add(MoviesItem(R.drawable.elplan, name = "El plan de millones (2021)") )
        arrayList.add(MoviesItem(R.drawable.eve, name = "Eve (2020)") )
        arrayList.add(MoviesItem(R.drawable.kanibaru, name = "Kanibarú (2019)") )

        return arrayList
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JuanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JuanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        var item: MoviesItem = arrayList!!.get(position)
        Toast.makeText(requireContext(), item.name, Toast.LENGTH_SHORT).show()
    }
}