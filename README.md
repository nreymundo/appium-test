# Appium Test

I have never used Appium or done much mobile testing so wanted to see how
hard it would be to install and get all the dependencies ready to be able 
to run a couple simple test cases on both an emulated and a physical device. 

Spoiler Alert: Not hard at all. 

### Dependencies
* Java 8 (Ancient, yes, I know). 
* Maven. 
* Node+npm to install Appium. 
* Appium (`npm install -g appium`)
* Android stuff (SDK, emulator, ADB, etc). 

**Pro Tip:** There's a tool called `appium-doctor` that runs a check and 
reports back if all dependencies to run Appium are met. 

```
npm install -g appium-doctor

➜ appium-doctor
info AppiumDoctor Appium Doctor v.1.15.4
info AppiumDoctor ### Diagnostic for necessary dependencies starting ###
info AppiumDoctor  ✔ The Node.js binary was found at: /home/nico/.nvm/versions/node/v14.15.0/bin/node
info AppiumDoctor  ✔ Node version is 14.15.0
info AppiumDoctor  ✔ ANDROID_HOME is set to: /home/nico/Android/Sdk
info AppiumDoctor  ✔ JAVA_HOME is set to: /home/nico/.jabba/jdk/adopt-openj9@1.8.0-272
info AppiumDoctor    Checking adb, android, emulator
info AppiumDoctor      'adb' is in /home/nico/Android/Sdk/platform-tools/adb
info AppiumDoctor      'android' is in /home/nico/Android/Sdk/tools/android
info AppiumDoctor      'emulator' is in /home/nico/Android/Sdk/emulator/emulator
info AppiumDoctor  ✔ adb, android, emulator exist: /home/nico/Android/Sdk
info AppiumDoctor  ✔ 'bin' subfolder exists under '/home/nico/.jabba/jdk/adopt-openj9@1.8.0-272'
info AppiumDoctor ### Diagnostic for necessary dependencies completed, no fix needed. ###
```


### How to Run

Download all the dependencies using Maven
* `mvn dependency:resolve`

Start Appium server
* `appium` to leave it in the foreground or `appium &` to send it to the background. 

Run the tests: 
* Straight from the command line with `mvn test`
* Import the project into an IDE and run it through there. 

Run the tests with Karate:
* Make sure the Appium server is started. 
* I had some issues if I didn't run `mvn clean verify` beforehand. Weird. Note: this will run the TestNG tests. 
* Using Intellij look up and install the plugin `Cucumber for Java`. That will also install the `Gherkin` plugin. 
* Right click on the feature file -> `Run Feature...`