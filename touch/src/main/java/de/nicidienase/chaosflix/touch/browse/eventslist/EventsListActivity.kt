package de.nicidienase.chaosflix.touch.browse.eventslist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import de.nicidienase.chaosflix.R
import de.nicidienase.chaosflix.common.entities.recording.persistence.PersistentEvent
import de.nicidienase.chaosflix.touch.OnEventSelectedListener
import de.nicidienase.chaosflix.touch.eventdetails.EventDetailsActivity

class EventsListActivity : AppCompatActivity(), OnEventSelectedListener {

	protected val numColumns: Int
		get() = resources.getInteger(R.integer.num_columns)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_events_list)

		val conferenceId = intent.getLongExtra(CONFERENCE_ID_KEY, 0)

		if (savedInstanceState == null) {
			val eventsListFragment = EventsListFragment.newInstance(conferenceId, numColumns)

			supportFragmentManager.beginTransaction()
					.replace(R.id.fragment_container, eventsListFragment)
					.commit();
		}
	}

	override fun onEventSelected(event: PersistentEvent) {
		EventDetailsActivity.launch(this, event.eventId)
	}

	companion object {
		val CONFERENCE_ID_KEY = "conference_id"

		fun start(context: Context, conferenceId: Long) {
			val i = Intent(context, EventsListActivity::class.java)
			i.putExtra(CONFERENCE_ID_KEY, conferenceId)
			context.startActivity(i)
		}
	}
}
