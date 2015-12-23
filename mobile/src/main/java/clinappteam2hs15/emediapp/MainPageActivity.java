package clinappteam2hs15.emediapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
 *  MainMediplanTab ->Mediplantab
 *  ToDo -> Kontaktetab
*
* Startseiten layout:
*   activity_main_page.xml (Grundsaetzlicher Aufbau: Collapsing Toolbar, Floating Actionbutton, Tabbar für die 3 Tabs der Seite) und
*   Tab 1: content_overview_main_page.xml (Inhalt Tab Uebersicht)
*   Tab 2: content_mediplan_main_page.xml (Inhalt Tab Mediplan)
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
        setTitle("eMedikationsApp");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /*Wechsel zwischen den Fragementen/Tabs
            getItem wird aufgerufen um die Instanz des einzelnen Fragementes zu erstellen
            return: Rückgabe des entsprechenden Tabs (Übersicht, Mediplan)
            ToDo: KontaktTab hinzufügen
         */
        public Fragment getItem(int position) {

            if (position == 0)
                return new MainOverviewTab();
            if (position == 1)
                return new MainMediplanTab();

            return new MainOverviewTab();
        }

        //ToDo: 3.Seite hinzufügen
        public int getCount() {
            // zeige total 2 Seiten.
            return 2;
        }

        // Die Namen der beiden Tabs werden hier definiert: ÜBERSICHT UND MEDIPPLAN
        // TODO: Tab Kontakte hinzufügen
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Übersicht";
                case 1:
                    return "Mediplan";
            }
            return null;
        }
    }

    /*Manuel Pfister*/
    public void ZweitViewWechselTextButton (View view){
        setContentView(R.layout.layout2);
    }

}
