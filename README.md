# Appium Test [![Build Status](https://travis-ci.com/nreymundo/appium-test.svg?branch=browserstacks%2Bappium%2Btravis)](https://travis-ci.com/nreymundo/appium-test) 

I have never used Appium or done much mobile testing so wanted to see how
hard it would be to install and get all the dependencies ready to be able 
to run tests on a remote service like Browserstacks and get some semblance 
of CI/CD running with Travis.   

This branch is set to run using Browserstacks for remote devices and Travis as the CI/CD server. 
Keeping it separated from the main branch as a quick and dirty way to make the code simpler for now. 

If you want to run this example on locally emulated or even actual physical devices, 
or also wnat to look at the Karate config, check the main `master` branch. 


### Known issues: 
Session doesn't seem to end in Browserstack after the tests finish and it keeps going until it eventually timesout 
or gets manually cancelled. I could put in a listener to check the status of the tests and call the BS API to terminate
the session with the corresponding result but... TBH that's a bit outside of what I wanted to try with this. It's pretty
easy to do so I might get around to it eventually.  