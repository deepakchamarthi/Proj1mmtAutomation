# Proj1mmtAutomation
Proj1mmtAutomation is a Testautomation framework that helps to automate hotel booking in popular website "makemytrip.com"


###What is this?
mmt automation is built on Test Driven methodology. Makemytrip.com/hotels url taken as an example.


###Features

- ***Complete Flow*** Book Hotel in *configurable* city with *configurable* checkin/checkout dates and with *configurable* guests count
- Automtically filters hotels with *configurable* start rating
- Assertion Flow checks if the booking is happening correctly with the right number of guests
- Works on any browser. Currently it is user configurable between Chrome and FireFox
- ***Highly efficient*** and ***Fastest*** way to boook the hotel. Neither*Thread.sleep* nor *implicit wait* is used. 
- ***Flexible*** with lot of configurable parameters. Complete List is given below.




###Tables
                    
First Header  | Second Header
------------- | -------------
Content Cell  | Content Cell
Content Cell  | Content Cell 



```flow
st=>start: Login
op=>operation: Login operation
cond=>condition: Successful Yes or No?
e=>end: To admin

st->op->cond
cond(yes)->e
cond(no)->op
```



####HTML code

```html
<!DOCTYPE html>
<html>
    <head>
        <mate charest="utf-8" />
        <title>Hello world!</title>
    </head>
    <body>
        <h1>Hello world!</h1>
    </body>
</html>
```



###End
