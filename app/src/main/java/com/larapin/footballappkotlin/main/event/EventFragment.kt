package com.larapin.footballappkotlin.main.event


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.larapin.footballappkotlin.R
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.model.event.Event
import com.larapin.footballappkotlin.detail.event.EventDetailActivity
import com.larapin.footballappkotlin.util.invisible
import com.larapin.footballappkotlin.util.visible
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class EventFragment : Fragment(), EventView {

    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: EventPresenter
    private lateinit var adapter: EventAdapter
    private lateinit var searchView: SearchView
    private lateinit var spinner: Spinner
    var event: String? = ""
    var currentPosition: Int = 0
    var leagueId = emptyArray<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_event, container, false)
        setHasOptionsMenu(true)
        listEvent = view.find(R.id.list_event)
        progressBar = view.find(R.id.progress_bar)
        swipeRefresh = view.find(R.id.swipe_refresh)
        spinner = view.find(R.id.spinner_event)
        event = arguments?.getString("event")
        val spinnerNewId = arguments!!.getInt("spinner")
        val listEventNewId = arguments!!.getInt("list")
        spinner.id = spinnerNewId
        listEvent.id = listEventNewId

        
        val leagueName = resources.getStringArray(R.array.league_name)
        leagueId = resources.getStringArray(R.array.league_id)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, leagueName)
        spinner.adapter = spinnerAdapter

        adapter = EventAdapter(ctx, events){
            startActivity<EventDetailActivity>(
                    "id" to "${it.eventId}",
                    "idhome" to "${it.idHome}",
                    "idaway" to "${it.idAway}"
            )
        }
        listEvent.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = EventPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getEventList(leagueId[position], event)
                currentPosition = position
            }
        }

        swipeRefresh.onRefresh {
            presenter.getEventList(leagueId[currentPosition], event)
        }
        return view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(event: String?, spinnerId: Int, listId: Int): EventFragment {
            val fragment = EventFragment()
            val args = Bundle()
            args.putString("event",event)
            args.putInt("spinner",spinnerId)
            args.putInt("list", listId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search Match"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                if (TextUtils.isEmpty(newText)){
                    presenter.getEventList(leagueId[currentPosition], event)
                }else{
                    presenter.getEventSearch(newText?.replace(" ", "_"))
                }
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

        })
    }
}
