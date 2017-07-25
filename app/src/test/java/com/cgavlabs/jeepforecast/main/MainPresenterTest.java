package com.cgavlabs.jeepforecast.main;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    private static final Double LATITUDE = 5.7;
    private static final Double LONGITUDE = 5.8;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    MainContract.Interactor interactor;
    MainContract.Presenter mainPresenter;

    @Before
    public void setup() {
        mainPresenter = new MainPresenter(interactor);
    }

    @Test
    public void callWeather() throws Exception {
        mainPresenter.callWeather(LATITUDE, LONGITUDE);
        verify(interactor).callWeather(LATITUDE, LONGITUDE);
    }
}