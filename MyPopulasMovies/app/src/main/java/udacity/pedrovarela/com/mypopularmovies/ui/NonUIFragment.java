package udacity.pedrovarela.com.mypopularmovies.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import udacity.pedrovarela.com.mypopularmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NonUIFragment extends Fragment {


    public NonUIFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      return null;
    }


}
