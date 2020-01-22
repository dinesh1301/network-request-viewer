# network-request-viewer(Mindtickle)
(This repository has been slightly modified for custom usecase of Mindtickle) <br />

It's time to say goodbye to Charles. You can view all the network requests **directly in your app** by using this library. Here are the steps

### Instruction For users
- **Press the Volume Button Down twice** to Open the list of requests screen
- **Click** on any of the requests to see the response
- **Long Click** on any of the request to share the request ID of this particular request on Slack/Email etc.
- **Click on Delete** option on top right corner of the screen to delete all of the requests. (Keep doing so frequently to avoid lag)


### Instruction For Developers


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
	        implementation 'com.github.dinesh-mindtickle:network-request-viewer:{version}'
	}
  ```
  
  Init the Viewer in the ```onCreate``` method of your application by writing this line
  ```NetworkLoggerProvider.init(this)```
  
Add following interceptor to your ```okHttpClient``` like this: 
```
val httpClient = OkHttpClient
            .Builder()
            .addInterceptor(NetworkLoggerInterceptor())
```
  
Finally, when you want to show the requests screen

```NetworkLoggerProvider.showNetworkCalls(this)```

**Features to be developed. Welcoming PRs for following features**
1. UI Enhancements
2. Gesture based trigger for opening the requests screen
3. Test cases
4. Pagination in RecyclerView


