package clinappteam2hs15.emediapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/** @author: Corina von Kaenel
 *
* Activity der Startseite
 *
 * Die Activity ist aufgebaut mit einer ActionBar, die wenn nötig durch scrollen verkleinert wird. Die ActionBar ist das "Header"-Element
 * Sie enthält eine Tabbar für die Navigation zwischen den Startseiten Tabs: ÜBERSICHT, MEDIPLAN UND KONTAKTE
*
* Dazu benötigt werden Informationen/Instruktionen in folgenden files:
* mobile Manifest:
*   aktuell keine Aenderungen zum Default

* Startseiten java-klassen (Fragment mit den Inhalten der Tabs)
*   MainOverviewTab -> Übersichtstab
 *  ToDo -> Mediplantab
 *  ToDo -> Kontaktetab
*
* Startseiten layout:
*   activity_main_page.xml (Grundsaetzlicher Aufbau: Collapsing Toolbar, Floating Actionbutton, Tabbar für die 3 Tabs der Seite) und
*   Tab 1: overview_main_page.xml (Inhalt Tab Uebersicht)
*   Tab 2: mediplan_main_page.xml (Inhalt Tab Mediplan)
 *  Tab 3: contacts_main_page.xml (Inhalt Tab Kontakte)
 *
*   (plus values/ Strings)
*
*/


public class MainPageActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. The
     * {@link FragmentPagerAdapter} derivative will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * ViewPager beinhalten die Inhalte der einzelnen Fragmente (Tabs)
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Den Adapter kreiern, welcher die Fragmente der drei Startseitentabs
        // dieser Starseitenaktivität "Activity = MainPageActivityCreate" enthält
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Den ViewPager mit dem Sectionsadapter erstellen
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //Das Layout für die 3 Fragmente bietet das Tablayout. Dieses enthält die Viewpager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Das Menu "infaten": hierdurch werden die Items in die Action Bar hinzugefügt.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        /*Manuel Pfister*/
        if (id==R.id.navigate){
            startActivity(new Intent (this, SubActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.content_mediplan_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0)
                return new MainOverviewTab();

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Übersicht";
                case 1:
                    return "Mediplan";
                case 2:
                    return "Kontakte";
            }
            return null;
        }
    }

    /*Manuel Pfister*/
    public void ZweitViewWechselTextButton (View view){
        setContentView(R.layout.layout2);
    }

}
