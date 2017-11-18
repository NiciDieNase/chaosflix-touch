package de.nicidienase.chaosflix.touch.browse.streaming

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.nicidienase.chaosflix.common.entities.streaming.LiveConference
import de.nicidienase.chaosflix.common.entities.streaming.Stream
import de.nicidienase.chaosflix.touch.browse.BrowseFragment

class LivestreamListFragment : BrowseFragment(){

    private lateinit var listener: InteractionListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is InteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement LivestreamListFragment.InteractionListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    interface InteractionListener{
        fun onStreamSelected(conference: LiveConference, stream: Stream)
    }
}