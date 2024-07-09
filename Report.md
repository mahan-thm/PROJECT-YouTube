
**bold**   
*itialic* 
###### dggverfve

 # YouTube Project
This is a youtube_like  java project at course Advanced progrmming on Faculty of Computer Science
in Shahid Beheshti Uiversity , Tehran, Iran.
## Main Tasks
The server is designed to handle multiple clients (using multy threading) . we used OOP(Object Oriented Programming)
the videos 
The hardest part of the project was sending and streaming video for the user.
frf
## UML Design
### Client 
* Controllers <br>
  * Methods <br> <br>
    <img src="src/main/resources/Report_images/ControllersMethods.png" alt="finalDiagram" width="1500">
  * Fields <br><br>
    <img src="src/main/resources/Report_images/ControllersFields.png" alt="finalDiagram" width="1500">

* Models <br> 
  * Methods <br> <br>
    <img src="src/main/resources/Report_images/ModelsMethods.png" alt="finalDiagram" width="1500">
  * Fields <br><br>
    <img src="src/main/resources/Report_images/ModelsFields.png" alt="finalDiagram" width="1500">
[//]: # (todo) dashtam 
### server 
* DBM  
* models
## GUI 
The main tool for designing graphical user interface for this project is JavaFX.
The stages ,scenes an all other javafx stuffs created by [Scene Builder](https://www.oracle.com/java/technologies/javase/javafxscenebuilder-info.html).
Of course we used some html and css codes in designing our interface.
## Objectives
Here is a list of concepts which where used throughout the project:
- Using OOP concepts
- Database design
- Multithreading concepts
- Socket Programming
- Hashing User's password
- Designing graphical user interface with JavaFX
- 
## implementation
The project has 3 main parts:<br>
1. `YouTubeClient` <br>
2. `YouTubeServer`<br>
3. `Sockets`
4. `DBM`
- `YouTubeClient`
  This is the package that is considered to be the interface/app layer of the project. It's repsonsible for getting input from a client, sending it through a socket to the server and waiting for a proper response.
- `YouTubeServer`
  The server is responsible for reading the requests from each client, either respond directly or access the database using `DBM` class methods and then, responding back to the client. It's worth noting that the server is multithreaded, which means it can handle dealing with multiple reqeusets as well. Also a server log a printed to the terminal to show what the server is doint at the moment.
- `Sockets`
  All the data passed between each client and the server is done through the use of sockets. In fact, each request from the client is sent to the socket, then server receives it and responds properly **through the same socket** for each client.
- `Database`   
  We stored data in a SQL database (except videos ,profile images, channel images that are stored in the resources ), using `MySQLServer` and `MySqlWorkbench` .All the methods which read, write , change and delete data on the database, are in DBM(stands for Database Manager).   
some notes about storing data in database:
  - Don't ever use comma(,) to seperate data and put multiple data in one cell.
  - Don't forget to close every connection at the end of method , it prevents probably bugs.
  So you mustn't define a static Connection and use it for the whole methods of the class. 
## Database Diagrams
- Database Diagram V1 <br><br>
  <img src="src/main/resources/Report_images/diagramV1.png" alt="DiagramV1" width="1500">

- Database Diagram V2 <br><br>
  <img src="src/main/resources/Report_images/diagramV2.png" alt="DiagramV2" width="1500">

- Database Diagram Final version <br><br>
  <img src="src/main/resources/Report_images/finalDiagram.png" alt="finalDiagram" width="1500">


item_type in the table has three values : "channel" "video" "user"
type in saved_videos has three values : "liked" , "wathched",""
we have nsfk boolean that filters the contents for kids
add_date in able tags means  

#  tags
recently_frequent tag shows that what is user's recent preferrence based on the last videos 
when user 1 shas subscrobed 3 channels , and user 2 subscribed 
we check the similarity of two users or check the 
##  Recommendation

if user doesn't like a tag , score will be !under zero!
## Pre Requirements
you need to make your javaFX sdk directory on Project Structure like the image below  
<img src="src/main/resources/Report_images/javaFX_Guide.png" alt="javaFxGuide" width="800">   


## how the tags of the vide will be assigned?
when you upload a video , you assign some tags to it
 if the similarity of the user and video was more than a number . we increase the similarity of both a little .  for example when user have good score on action

when user subscribes a new channel, we search  in those channel subscribers to find another users who may has similar favorite tags 
commented and liked the video +subscribed channel + notif is on --> so much high score to tag

commented 
we consoder the date that user subscribed the channel. how umch soon the time is will encrease probablity of showing videos with relative tags in user's home feed.
we r
asks user if likes the recommendation algorithm .if doesn't like , reask him to change the tags. or show
when you sign up . we 
if user comments , it means he likes so much or like the content so much.so how we can undrstand the purose? if comments and liked 
!! youtube gets feedback 
when  
# notifiction_table
when user ar admin sees the notif, the notif will be removed
my inbox 
# search
we used levenstein distance which which takes into account the number of insertion, deletion and substitution operations needed to transform one string into the other. 
the problem of levenstein distance of the words "hey" and "abc" is 3. but the distance of the words"hey" and "heyman" is 6. to solve this  first sort by  regex, then I went to levenstein distance