package com.example.maru;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.example.maru.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.maru.ui.MainActivity;
import com.example.maru.utils.CreateMeetingUtils;
import com.example.maru.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final int ITEMS_COUNT = 10;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        MainActivity activity = mActivityRule.getActivity();
        assertThat(activity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myMeetingsList_shouldNotBeEmpty() {
        onView(ViewMatchers.withId(R.id.recyclerView)).check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myMeetingsList_deleteAction_shouldRemoveItem() {
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When we click on an item it navigates to the detail view
     */
    @Test
    public void myMeetingsList_meetingAction_shouldShowMeetingDetail() {
        onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(ViewMatchers.withId(R.id.detailMeeting)).check(matches(isDisplayed()));
    }

    /**
     * We ensure that the name corresponding to the item is displayed
     */
    @Test
    public void myMeetingDetail_textViewShouldDisplayTheMeetingTopic() {
        onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(ViewMatchers.withId(R.id.topic)).check(ViewAssertions.matches(ViewMatchers.withText("Réunion B")));
    }

    /**
     * we ensure that the state corresponding to the item is displayed
     */
    @Test
    public void myMeetingDetail_textViewShouldDisplayTheMeetingStateDependingOnTheDate() {
        //case expected tomorrow
        onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.stateMeeting)).check(ViewAssertions.matches(ViewMatchers.withText("Réunion prévue demain à "+ LocalTime.of(10,0))));
        pressBack();
        //case on going
        onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(ViewMatchers.withId(R.id.stateMeeting)).check(ViewAssertions.matches(ViewMatchers.withText("Réunion en cours…")));
        pressBack();
        //case done
        onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(ViewMatchers.withId(R.id.stateMeeting)).check(ViewAssertions.matches(ViewMatchers.withText("Réunion terminée")));
        pressBack();
        //case expected at least after tomorrow
        onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(ViewMatchers.withId(R.id.stateMeeting)).check(ViewAssertions.matches(ViewMatchers.withText("Réunion prévue le "+ LocalDate.now().plusDays(2).getDayOfMonth()
                +"/0"+LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear()+" à " +LocalTime.of(8,0))));
        pressBack();
        //case expected today but not Started yet
        onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));
        onView(ViewMatchers.withId(R.id.stateMeeting)).check(ViewAssertions.matches(ViewMatchers.withText("Réunion prévue aujourd'hui à "+LocalTime.of(23,59))));
    }

    /**
     * When we click on the AddButton it navigates to createMeeting
     */
    @Test
    public void myMeetingsList_addButtonAction_shouldShowCreateMeeting() {
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());
        onView(ViewMatchers.withId(R.id.addMeeting)).check(matches(isDisplayed()));
    }

    /**
     * When we click on the FilterButton the FilterDialog is displayed
     */
    @Test
    public void myMeetingsList_filterButtonAction_shouldShowFilter() {
        onView(ViewMatchers.withId(R.id.filter)).perform(click());
        onView(ViewMatchers.withId(R.id.filterDialog)).check(matches(isDisplayed()));
    }

    /**
     * We ensure that when we create meeting is added to the meeting list
     */
    @Test
    public void CreateMeetingWithSuccess() {
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        CreateMeetingUtils.createMeeting("test", 1,1,2000,10,10,"akali", "test test");
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT+1));
    }

    /**
     * We ensure the meeting won't be created if a information of the meeting is missing
     */
    @Test
    public void CreateMeetingShouldNotBeCreatedIfInputIsMissing() {
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        CreateMeetingUtils.createMeeting(null, 1,1,2000,10,10,"akali", "test test");
        onView(ViewMatchers.withId(R.id.addMeeting)).check(matches(isDisplayed()));
        pressBack();
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
    }

    /**
     * When we do a filter by date it return meetings expected at this date
     */
    @Test
    public void filterByDateShouldShowMeetingAtThisDate() {
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        CreateMeetingUtils.createMeeting("test", 1,1,2000,10,10,"akali", "test test");
        onView(ViewMatchers.withId(R.id.filter)).perform(click());
        onView(ViewMatchers.withId(R.id.datePicker)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2000, 1, 1));
        onView(ViewMatchers.withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(1));
    }

    /**
     * When we do a filter by time it return meetings expected at this time
     */
    @Test
    public void filterByTimeShouldShowMeetingAtThisTime() {
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        CreateMeetingUtils.createMeeting("test", 1,1,2000,10,10,"akali", "test test");
        onView(ViewMatchers.withId(R.id.filter)).perform(click());
        onView(ViewMatchers.withId(R.id.timePicker)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(10, 10));
        onView(ViewMatchers.withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(1));
    }

    /**
     * When we do a filter by room it return meetings expected in this room
     */
    @Test
    public void filterByRoomShouldShowMeetingAtThisRoom() {
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        onView(ViewMatchers.withId(R.id.filter)).perform(click());
        onView(ViewMatchers.withId(R.id.room_list)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("akali"))).inRoot(isPlatformPopup()).perform(click());
        onView(ViewMatchers.withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(1));
    }

    /**
     * When we click on reset filter it cancel previous filter
     */
    @Test
    public void filterResetShouldCancelFilter() {
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        onView(ViewMatchers.withId(R.id.filter)).perform(click());
        onView(ViewMatchers.withId(R.id.room_list)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("akali"))).inRoot(isPlatformPopup()).perform(click());
        onView(ViewMatchers.withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(1));
        onView(ViewMatchers.withId(R.id.reset_filter)).perform(click());
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
    }
}