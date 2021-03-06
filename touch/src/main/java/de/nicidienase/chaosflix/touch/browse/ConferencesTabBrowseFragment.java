package de.nicidienase.chaosflix.touch.browse;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.nicidienase.chaosflix.R;
import de.nicidienase.chaosflix.databinding.FragmentTabPagerLayoutBinding;
import de.nicidienase.chaosflix.touch.browse.adapters.ConferenceGroupsFragmentPager;


public class ConferencesTabBrowseFragment extends BrowseFragment {

	private static final String TAG = ConferencesTabBrowseFragment.class.getSimpleName();

	private static final String ARG_COLUMN_COUNT = "column-count";
	private static final String CURRENTTAB_KEY   = "current_tab";
	private static final String VIEWPAGER_STATE  = "viewpager_state";
	private              int    mColumnCount     = 1;
	private OnInteractionListener         listener;
	private FragmentTabPagerLayoutBinding binding;

	public static ConferencesTabBrowseFragment newInstance(int columnCount) {
		ConferencesTabBrowseFragment fragment = new ConferencesTabBrowseFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_COLUMN_COUNT, columnCount);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnInteractionListener) {
			listener = (OnInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement OnInteractionListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		if (getArguments() != null) {
			mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
		}
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = FragmentTabPagerLayoutBinding.inflate(inflater, container, false);

		setupToolbar(binding.incToolbar.toolbar, R.string.app_name);
		setOverlay(binding.incOverlay.loadingOverlay);

		getViewModel().getConferenceGroups().observe(this, conferenceGroups -> {
			ConferenceGroupsFragmentPager fragmentPager = new ConferenceGroupsFragmentPager(this.getContext(), getChildFragmentManager());
			fragmentPager.setContent(conferenceGroups);
			binding.viewpager.setAdapter(fragmentPager);
			binding.viewpager.onRestoreInstanceState(getArguments().getParcelable(VIEWPAGER_STATE));

			binding.slidingTabs.setupWithViewPager(binding.viewpager);
			if (conferenceGroups.size() > 0) {
				setLoadingOverlayVisibility(false);
			}
		});
		getViewModel().updateConferences().observe(this, loadingFinished -> setLoadingOverlayVisibility(!loadingFinished));
		return binding.getRoot();
	}

	@Override
	public void onPause() {
		super.onPause();
		getArguments().putParcelable(VIEWPAGER_STATE, binding.viewpager.onSaveInstanceState());
	}

	@Override
	public void onDetach() {
		super.onDetach();
		listener = null;
	}

	public interface OnInteractionListener {
		void onConferenceSelected(long conferenceId);
	}
}
