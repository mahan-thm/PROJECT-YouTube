
**bold**   
*itialic* 

<span style="color:#AADCE2">
item_type in the table has three values : "channel" "video" "user"
type in saved_videos has three values : "liked" , "wathched",""
we have nsfk boolean that filters the contents for kids
add_date in able tags means  

# <span style="color: #00CED1"> tags
<span style="color:red">recently_frequent tag</span> shows that what is user's recent preferrence based on the last videos 
when user 1 shas subscrobed 3 channels , and user 2 subscribed 
we check the similarity of two users or check the 
# <span style="color: #00CED1"> recommendation

if user doesn't like a tag , score will be !under zero!
# presquities
you need to make your javaFX sdk directory on Project Structure like the image below
![image](srs/main/resources/Report_Images/javaFX_Guide.png)
## <span style="color: #00CED1">how the tags of the vide will be assigned?
when you upload a video , you assign some tags to it
<span style="color:red"> if the similarity of the user and video was more than a number . we increase the similarity of both a little . </span> <span style="color:green"> for example when user have good score on action</span>

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
# <span style="color: #00CED1">notifiction_table
when user ar admin sees the notif, the notif will be removed
my inbox 
# <span style="color: #00CED1">search
we used levenstein distance which which takes into account the number of insertion, deletion and substitution operations needed to transform one string into the other. 
the problem of levenstein distance of the words "hey" and "abc" is 3. but the distance of the words"hey" and "heyman" is 6. to solve this  first sort by  regex, then I went to levenstein distance