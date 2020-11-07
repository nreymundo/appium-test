function fn() {
  var config = {}
  var android = {}
  android["desiredConfig"] = {
//   "app" : "https://github.com/afollestad/material-dialogs/raw/main/sample/sample.apk",
   "app" : "https://github.com/babusekaran/droidAction/raw/master/build/UiDemo.apk",
   "newCommandTimeout" : 300,
   "platformVersion" : "11.0",
   "platformName" : "Android",
   "connectHardwareKeyboard" : true,
   "deviceName" : "emulator-5554",
   "avd" : "Pixel_4_XL_API_30",
   "automationName" : "Appium",
   "allowTestPackages": true
  }
  config["android"] = android
  return config;
}