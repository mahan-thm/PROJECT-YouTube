
 # YouTube Project
This is a YouTube_like  java project at course Advanced programming on Faculty of Computer Science
in Shahid Beheshti University , Tehran, Iran.
## Main Tasks
The server is designed to handle multiple clients (using multi threading) . we used OOP(Object-Oriented Programming)
The hardest part of the project was sending and streaming video for the user.
filie strea sizesh ro byte 4 klo byte package  ya besoorate byte array baraye aksa.  mire too file vaye ja khonde mishe.
levenestein 
user video upload 
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
* DBM Methods <br> <br>
  <img src="src/main/resources/Report_images/DBM_Methods1.png" alt="finalDiagram" width="1500">
  <img src="src/main/resources/Report_images/DBM_Methods2.png" alt="finalDiagram" width="1500">
 
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
our recommendation algorithm has designed to reach the most compatibility between user preferences and its home feed. We tried to personalize user's home feed based on
which contents has watched more before and which videos were liked by the user.To reach this goal we assign every user and every video some tags such as "action" , "romance"
, "nature", "tech" ,etc.For each tag we have a score between 0 and 100. The more you like and watch videos with "action" tag, your score on "action" tag get nearer to 100. And you 
will get more videos with this tag in your home feed.More score on "action" tag  of a video means the video have more related action scenes in there.  . very tag has  , when a user
likes a video or watch it, the user's tags increase based on the scores of the user's tag.
when subscribes a channel , it means there is a more powerful relation between the user preferences and the channel's video's content. So it must affect  o the user's tags score 
more than the time user just likes a video .
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
we used levenstein distance which  takes into account the number of insertion, deletion and substitution operations needed to transform one string into the other. 
the problem of levenstein distance of the words "hey" and "abc" is 3. but the distance of the words"hey" and "heyman" is 6. to solve this  first sort by  regex, then I went to levenstein distance
## Contributers 
Mentor [telegram](https://t.me/moodygr) || [GitHub](https://github.com/Grmamobin)
Mahan Tahmasebi [telegram](https://t.me/mahan_thm) || [GitHub](https://github.com/mahan-thm)  
Mohammad Hosain Jaafari [telegram](https://t.me/MmdHo3ain) || [GitHub](https://github.com/MmdHosain)  
Kourosh Mojdehi[telegram](https://t.me/kouroshmojdehi) || [GitHub](https://github.com/kourosh-mojdehi)
