package de.nicidienase.chaosflix.touch.resolver

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import de.nicidienase.chaosflix.touch.SplashActivity
import de.nicidienase.chaosflix.touch.browse.BrowseActivity
import de.nicidienase.chaosflix.touch.eventdetails.EventDetailsActivity

class BrowseFilter: AppCompatActivity(){

	val TAG = BrowseFilter::class.simpleName

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		Log.d(TAG, intent.data.toString())
		if(intent.data != null){

			when(intent.data.host){
				"media.ccc.de" -> decodeMediaUri(intent.data)
				"streaming.ccc.de" -> showStream(intent.data)
				else -> startApp()
			}
		} else {
			finish()
		}
	}

	private fun startApp() {
		startActivity(Intent(this,SplashActivity::class.java))
	}

	private fun showStream(data: Uri) {

	}

	private fun decodeMediaUri(data: Uri) {
			EventDetailsActivity.launch(this, data)
	}
}