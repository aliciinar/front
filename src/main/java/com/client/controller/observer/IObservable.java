package com.client.controller.observer;

import java.awt.*;

/**
 * observables interface for the observer pattern. we dont use java observables since we already use abstract classes so we can not implements java.Observables.
 */
public interface IObservable {



    void notifyObservers();

    void  addUserInformation(IObserverText observerText);
}
