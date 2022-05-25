package com.client.controller.observer;

import java.awt.*;

public interface IObservable {

    // observables interface for the observer pattern. we dont use java observables since we already use abstract classes so we can not implements java.Observables.

    void notifyObservers();

    void  addUserInformation(IObserverText observerText);
}
