package com.example.maru;

import static com.example.maru.model.Employee.DUMMY_EMPLOYEE;
import static com.example.maru.model.MeetingRoom.AKALI;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;
import com.example.maru.service.DummyMeetingGenerator;
import com.example.maru.service.MeetingApiService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class ExampleUnitTest {

    private MeetingApiService service;

    @Before
    public  void setUp() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting meeting = new Meeting("test", LocalTime.of(1,0),
                LocalDate.now().plusDays(10), MeetingRoom.AHRI, Arrays.asList("t", "test"));
        service.createMeeting(meeting);
        assertTrue(service.getMeetings().contains(meeting));
    }

    @Test
    public void getMeetingByTimeWithSuccess() {
        Meeting meetingExpected = new Meeting("test", LocalTime.of(1,0),
                LocalDate.now().plusDays(10), MeetingRoom.AHRI, Arrays.asList("t", "test"));
        Meeting meetingUnexpected = new Meeting("test", LocalTime.of(10,0),
                LocalDate.now().plusDays(11), MeetingRoom.DARIUS, Arrays.asList("t", "test"));
        service.createMeeting(meetingExpected);
        service.createMeeting(meetingUnexpected);
        LocalTime time = LocalTime.of(1,0);

        List<Meeting> meetings = new ArrayList<>(service.getMeetingsByTime(time));
        assertTrue(meetings.contains(meetingExpected));
        assertFalse(meetings.contains(meetingUnexpected));
    }

    @Test
    public void getMeetingsByDateWithSuccess() {
        Meeting meetingExpected = new Meeting("test", LocalTime.of(1,0),
                LocalDate.now().plusDays(10), MeetingRoom.AHRI, Arrays.asList("t", "test"));
        Meeting meetingUnexpected = new Meeting("test", LocalTime.of(10,0),
                LocalDate.now().plusDays(11), MeetingRoom.DARIUS, Arrays.asList("t", "test"));
        service.createMeeting(meetingExpected);
        service.createMeeting(meetingUnexpected);
        LocalDate date = LocalDate.now().plusDays(10);

        List<Meeting> meetings = new ArrayList<>(service.getMeetingsByDate(date));
        assertTrue(meetings.contains(meetingExpected));
        assertFalse(meetings.contains(meetingUnexpected));
    }

    @Test
    public void getMeetingByRoomWithSuccess() {
        Meeting meetingExpected = new Meeting("test", LocalTime.of(1,0),
                LocalDate.now().plusDays(10), MeetingRoom.AHRI, Arrays.asList("t", "test"));
        Meeting meetingUnexpected = new Meeting("test", LocalTime.of(10,0),
                LocalDate.now().plusDays(11), MeetingRoom.DARIUS, Arrays.asList("t", "test"));
        service.createMeeting(meetingExpected);
        service.createMeeting(meetingUnexpected);

        List<Meeting> meetings = new ArrayList<>(service.getMeetingByRoom(MeetingRoom.AHRI));
        assertTrue(meetings.contains(meetingExpected));
        assertFalse(meetings.contains(meetingUnexpected));
    }

    @Test
    public void getMeetingRoomByNameWithSuccess() {
        assertEquals(MeetingRoom.AHRI, service.getMeetingRoomByName("ahri"));
    }

    @Test
    public void getMeetingByFilterWithSuccess() {
        Meeting meetingExpected = new Meeting("test", LocalTime.of(1,0),
                LocalDate.now().plusDays(10), MeetingRoom.AHRI, Arrays.asList("t", "test"));
        Meeting meetingUnexpected = new Meeting("test", LocalTime.of(10,0),
                LocalDate.now().plusDays(11), MeetingRoom.DARIUS, Arrays.asList("t", "test"));
        service.createMeeting(meetingExpected);
        service.createMeeting(meetingUnexpected);

        List<Meeting> meetingsByRoom = new ArrayList<>(service.getMeetingByFilter(MeetingRoom.AHRI, null, null));
        assertTrue(meetingsByRoom.contains(meetingExpected));
        assertFalse(meetingsByRoom.contains(meetingUnexpected));

        List<Meeting> meetingsByTime = new ArrayList<>(service.getMeetingByFilter(null, LocalTime.of(1,0), null));
        assertTrue(meetingsByTime.contains(meetingExpected));
        assertFalse(meetingsByTime.contains(meetingUnexpected));

        List<Meeting> meetingsByDate = new ArrayList<>(service.getMeetingByFilter(null, null, LocalDate.now().plusDays(10)));
        assertTrue(meetingsByDate.contains(meetingExpected));
        assertFalse(meetingsByDate.contains(meetingUnexpected));

        List<Meeting> meetingsByRoomTime = new ArrayList<>(service.getMeetingByFilter(MeetingRoom.AHRI, LocalTime.of(1,0), null));
        assertTrue(meetingsByRoomTime.contains(meetingExpected));
        assertFalse(meetingsByRoomTime.contains(meetingUnexpected));

        List<Meeting> meetingsByRoomDate = new ArrayList<>(service.getMeetingByFilter(MeetingRoom.AHRI, null, LocalDate.now().plusDays(10)));
        assertTrue(meetingsByRoomDate.contains(meetingExpected));
        assertFalse(meetingsByRoomDate.contains(meetingUnexpected));

        List<Meeting> meetingsByDateTime = new ArrayList<>(service.getMeetingByFilter(null, LocalTime.of(1,0), LocalDate.now().plusDays(10)));
        assertTrue(meetingsByDateTime.contains(meetingExpected));
        assertFalse(meetingsByDateTime.contains(meetingUnexpected));

        List<Meeting> meetingsByAllFilter = new ArrayList<>(service.getMeetingByFilter(MeetingRoom.AHRI, LocalTime.of(1,0), LocalDate.now().plusDays(10)));
        assertTrue(meetingsByAllFilter.contains(meetingExpected));
        assertFalse(meetingsByAllFilter.contains(meetingUnexpected));

        List<Meeting> meetingsNoFilter = new ArrayList<>(service.getMeetingByFilter(null, null, null));
        assertTrue(meetingsNoFilter.isEmpty());

    }

    @Test
    public void getMeetingByDateTimeWithSuccess() {
        Meeting meetingExpected = new Meeting("test", LocalTime.of(1,0),
                LocalDate.now().plusDays(10), MeetingRoom.AHRI, Arrays.asList("t", "test"));
        Meeting meetingUnexpected = new Meeting("test", LocalTime.of(10,0),
                LocalDate.now().plusDays(11), MeetingRoom.DARIUS, Arrays.asList("t", "test"));
        service.createMeeting(meetingExpected);
        service.createMeeting(meetingUnexpected);

        List<Meeting> meetings = new ArrayList<>(service.getMeetingByDateTime(LocalDateTime.of(LocalDate.now().plusDays(10), LocalTime.of(1,0))));
        assertTrue(meetings.contains(meetingExpected));
        assertFalse(meetings.contains(meetingUnexpected));
    }

    @Test
    public void getMeetingByDateTimeRoomWithSuccess() {
        Meeting meetingExpected = new Meeting("test", LocalTime.of(1,0),
                LocalDate.now().plusDays(10), MeetingRoom.AHRI, Arrays.asList("t", "test"));
        Meeting meetingUnexpected = new Meeting("test", LocalTime.of(10,0),
                LocalDate.now().plusDays(11), MeetingRoom.DARIUS, Arrays.asList("t", "test"));
        service.createMeeting(meetingExpected);
        service.createMeeting(meetingUnexpected);
    }

    @Test
    public void getMeetingByDateRoomWithSuccess() {
        Meeting meetingExpected = new Meeting("test", LocalTime.of(1,0),
                LocalDate.now().plusDays(10), MeetingRoom.AHRI, Arrays.asList("t", "test"));
        Meeting meetingUnexpected = new Meeting("test", LocalTime.of(10,0),
                LocalDate.now().plusDays(11), MeetingRoom.DARIUS, Arrays.asList("t", "test"));
        service.createMeeting(meetingExpected);
        service.createMeeting(meetingUnexpected);

        List<Meeting> meetings = new ArrayList<>(service.getMeetingByRoom(MeetingRoom.AHRI));
        assertTrue(meetings.contains(meetingExpected));
        assertFalse(meetings.contains(meetingUnexpected));

        List<Meeting> noRoom = new ArrayList<>(service.getMeetingByRoom(null));
        assertTrue(noRoom.isEmpty());
    }

    @Test
    public void getMeetingByTimeRoomWithSuccess() {
        Meeting meetingExpected = new Meeting("test", LocalTime.of(1,0),
                LocalDate.now().plusDays(10), MeetingRoom.AHRI, Arrays.asList("t", "test"));
        Meeting meetingUnexpected = new Meeting("test", LocalTime.of(10,0),
                LocalDate.now().plusDays(11), MeetingRoom.DARIUS, Arrays.asList("t", "test"));
        service.createMeeting(meetingExpected);
        service.createMeeting(meetingUnexpected);

        List<Meeting> meetings = new ArrayList<>(service.getMeetingByTimeRoom(LocalTime.of(1,0), MeetingRoom.AHRI));
        assertTrue(meetings.contains(meetingExpected));
        assertFalse(meetings.contains(meetingUnexpected));
    }

    @Test
    public void checkMeetingRoomIsAvailableWithSuccess() {
        Meeting meeting = new Meeting("Réunion B" , LocalTime.of(8,  0), LocalDate.now(),
                AKALI , Arrays.asList(DUMMY_EMPLOYEE.get(1).getMail(), DUMMY_EMPLOYEE.get(2).getMail()));
        Meeting newMeeting = new Meeting("test", LocalTime.of(1,0),
                LocalDate.now().plusDays(10), MeetingRoom.AHRI, Arrays.asList("t", "test"));
        boolean isNotAvailable = service.checkMeetingRoomIsAvailable(meeting);
        boolean isAvailable = service.checkMeetingRoomIsAvailable(newMeeting);
        assertFalse(isNotAvailable);
        assertTrue(isAvailable);
    }
}