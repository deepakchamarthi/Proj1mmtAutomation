# Proj1mmtAutomation
Proj1mmtAutomation is a Testautomation framework that helps to automate hotel booking in popular website "makemytrip.com"


### What is this?
mmt automation is built on Test Driven methodology. Makemytrip.com/hotels url taken as an example.


### Features
-------------

- **Complete Flow** Book Hotel in *configurable* city with *configurable* checkin/checkout dates and with *configurable* guests count
- Automtically filters hotels with *configurable* start rating
- Assertion Flow checks if the booking is happening correctly with the right number of guests
- **Compatability** works with any browser. Currently it is user configurable between Chrome and FireFox
- **Highly efficient** and **Fastest** way to boook the hotel. Neither*Thread.sleep* nor *implicit wait* is used. 
- **Flexible** with lot of configurable parameters. Complete List is given below.


#### How to use this?
-------------
1. Run test/java/testcases/CompleteFlowTest
OR
2. Run test/resources/TestSuiteHotelBooking.xml

https://github.com/deepakchamarthi/Proj1mmtAutomation/blob/main/src/test/java/testcases/CompleteFlowTest.java 
	  
![](https://github.com/deepakchamarthi/Proj1mmtAutomation/blob/main/TestSuite.gif)	  





### Contents
-------------

### Folder main/java/constants: Files and Purpose
                    
Files         | Purpose       
------------- | -------------
 StarRating  | Enum: to preserve Hotel Rating from 1 to 5
 
### Folder main/java/bo: Files and Purpose
                    
Files         | Purpose       
------------- | -------------
FillGuestDetailsBO  | fill the guest details randomly using faker
                 
				 
				 
 
### Folder main/java/PageFlow: Files and Purpose
                    
Files         | Purpose       
------------- | -------------
HomePageFlow1 | Flow to interact with elements using methods from HomePage.java 
SearchPageFlow | Flow to interact with elements using methods from SearchPage elements.java
RoomPageFlow | Flow to interact with elements using methods from RoomSelectionPage elements.java
TestReviewPageFlow | Flow to interact with elements using methods from ReviewPage elements.java
PaymentPageFlow | Flow to interact with elements using methods from PaymentPage elements.java


### Folder main/java/drivermanager,DriverFactory: Files and Purpose
                    
Files         | Purpose       
------------- | -------------
All the files   | Helps to select driver based on the configuration and init.

### Folder main/java/Pages: Files and Purpose
                    
Files         | Purpose       
------------- | -------------
BasePage | Contains all useful functions for basic element interactions.
HomePage | Methods to interact with HomePage of makemytrip/hotels url
SearchPage | Methods to interact with SearchPage of makemytrip/hotels url.
RoomSelectionPage | Methods to interact with RoomSelectionPage Page of makemytrip/hotels url
ReviewPage | Methods to interact with ReviewPage of makemytrip/hotels url
PaymentPage | Methods to interact with PaymentPage of makemytrip/hotels url



### Folder main/java/Utilities: Files and Purpose
                    
Files         | Purpose       
------------- | -------------
ScreenshotUtil | Methods to take screenshots with configurable path and name.
TestDataGenerator | Generates random test data. Example: this is used in filling Guest details.

### Folder /test/java/testcases: Files and Purpose
                    
Files         | Purpose       
------------- | -------------
CompleteFlowTest | To Test the complete hotel booking flow. **This doesnt include assertinons" 
CheckRoomAllocation | Assertions to check booking summary is matching with input data or not.


### Folder /test/resouces: Files and Purpose
                    
Files         | Purpose       
------------- | -------------
TestSuiteHotelBooking | 1. CompleteFlowTest 2.AssertBookingSumm  ary 
CheckRoomAllocation | Assertions to check booking summary is matching with input data or not.

### TestNG Configuration to run
-------------

```html
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="testsuitehotelbooking">


	<test name="Tests">
		<classes>
			<class name="testcases.CompleteFlowTest">

			</class>
		</classes>
	</test>

	<suite-files>
		<suite-file
			path="mmtAssertions.xml" />

	</suite-files>
</suite>
</html>
```

### TestData
- Tested makemytrip/hotel multiletimes with Chrome and Firefox during and after completion of the framework. No open issues , but there is a scope of improvement in configrability and handling false scenarios.

### Pending
- Have to change access modifiers in some classes 
- Clean up imports, some unused imports are present in the classes
- Data provider can be added



### End
