# network-request-viewer
It's time to say goodbye to Charles. You can view all the network requests of your app by using this library. Here are the steps


Add it in your root build.gradle at the end of repositories:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  Then, add the dependency
  
  ```
  dependencies {
	        implementation 'com.github.dinesh-mindtickle:network-request-viewer:0.0.3'
	}
  ```
  
  Init the Viewer in the ```onCreate``` method of your application by writing this line
  ```NetworkLoggerProvider.init(this)```
  
Finally, when you want to show the requests screen

```NetworkLoggerProvider.showNetworkCalls(this)```
