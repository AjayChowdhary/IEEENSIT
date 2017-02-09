
<p align="center">
<img src="https://cloud.githubusercontent.com/assets/19619541/22681193/ee45b5fa-ed31-11e6-9c62-d41a4f67b201.jpg" width="100" hieght="100"/>
</p>

# IEEENSIT
IEEE-NSIT Student Branch works to create an atmosphere of technical excellence for the students. It aims at helping students in building an aptitude towards applying engineering in daily life by learning ways to use the latest technology on offer. Through this app, students can remain informed about events and news pertaining to IEEE-NSIT.
	

<p align="center">
<img src="https://cloud.githubusercontent.com/assets/19619541/22680399/b67f20c4-ed2d-11e6-8742-427994c7639d.jpg" width="150" hieght="200"/>
<img src="https://cloud.githubusercontent.com/assets/19619541/22775694/cd9d95be-eed2-11e6-820e-40c90fdda873.png" width="150" hieght="200"/>
</p>



Features
-------------
###1. Home 
This is the home page of the app shows all the latest feeds and news which are pulled and shown in an aesthetic UI. You can go through the description and browse/download photos of the posts in the news feed.
The MainActivity also uses sliding drawer to provide different menu options.
<p align="center">
<img src="https://cloud.githubusercontent.com/assets/19619541/22679498/aa1db278-ed28-11e6-949e-3a1b96640c8a.jpg" width="150" hieght="200"/>
<img src="https://cloud.githubusercontent.com/assets/19619541/22775694/cd9d95be-eed2-11e6-820e-40c90fdda873.png" width="150" hieght="200"/>
</p>     
	

###2. Gallery
Shows images posted on IEEE.org\'s Facebook page. The gallery items are shown in thumbnails with a floating effect.
<p align="center">
<img src="https://cloud.githubusercontent.com/assets/19619541/22679492/a9f9686e-ed28-11e6-8f3f-bf8750758fa5.jpg" width="150" hieght="200"/>
</p>
###3.  IEEE ID

Acts as a substitute for payment slips. Instead of carrying worn out slips which can easily be misplaced, IEEE members can log in with their registered mobile number, and present the generated ID at events. Users not registered with IEEE can send an email to ieeensit2016@gmail.com.
<p align="center">
<img src="https://cloud.githubusercontent.com/assets/19619541/22775701/d049ede4-eed2-11e6-9fc5-1b015880e54e.png" width="150" hieght="200"/>
<img src="https://cloud.githubusercontent.com/assets/19619541/22775698/cf6d5334-eed2-11e6-91cb-66fe411d3288.png" width="150" hieght="200"/>
</p>



###4. Register User
To register the user in our database with a unique Email id and phone number. 
###5. Push Notification
  Support for push notifications has been added. When a user installs the app for the first time and register, the device token which is uploaded to our database.

<p align="center">
<img src="https://cloud.githubusercontent.com/assets/19619541/22775703/d1c03e26-eed2-11e6-9a94-4b3f23aed4a7.png" width="150" hieght="200"/>

</p>
	
###6. Floating Button 
     Provides three options to the user:
        1. To provide feedback through feedback form.
	      2. To volunteer for IEEE-NSIT.
	      3. To get involved in the mentorship project.
   <p align="center">
<img src="https://cloud.githubusercontent.com/assets/19619541/22679496/a9fbeb70-ed28-11e6-8f89-20b731465631.jpg" width="150" hieght="200"/>
</p>     
	   
Contribution
------------

It's very easy to contribute to IEEE-NSIT app. Just follow these steps:

<p align="center">
<img src="https://cloud.githubusercontent.com/assets/19619541/22679493/a9fbb510-ed28-11e6-9748-06771ed772f3.jpg" width="150" hieght="200"/>
</p>

 - Get the prerequisites ready. (learn Java and set up Android Studio)
 - Fork this project into your own repo.
 - Make your changes to that repo, and create a pull request.

	

That's all there is to be a contributor of IEEENSIT app. We are looking forward to working together with lots of aspiring developers and to maintain this project by collaborating with the open community.




Building
--------
To build, use the gradle wrapper scripts provided in the top level directory of the project. The following will compile the application and run all unit tests:

>GNU/Linux, OSX, UNIX:

>`./gradlew build`

>Windows:

>`./gradlew.bat build`


APIs used
---------
### Graph API
```
https://graph.facebook.com/(Insert Page ID )/posts?limit=20&fields=id,full_picture,picture,from,shares,attachments,message,object_id,link,created_time,comments.limit(0).summary(true),likes.limit(0).summary(true)&access_token=(Insert API Key)
```
To fetch JSON data of a particular page, plug in the page id in the format shown above and you're good to go! For example, the page ID of IEEE NSIT is 278952135548721

```
https://graph.facebook.com/v2.5/176108879110422/photos?fields=name,source,likes.summary(true)&access_token=1079162565526730|X2XCCdxnMXpvWifJVGNn1iqcSl8
```
To fetch Image source links and number of likes and posts.

Other Repositories
-----------
[iOS](https://github.com/Swapnil52/ieeeNSIT)


Project Maintainers
---
This project is founded and actively maintained by [Ajay Chowdhary](ajaychowdhary.github.io) and Swapnil Dhanwal. For any sort of queries feel free to either mail at archh1096@gmail.com or swapnildhanwal@hotmail.com. 


