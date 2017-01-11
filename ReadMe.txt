Read Me

This is a project done for Rutgers CS 213(Software Methodology), it is a destop app designed to organize the users photos with ablums and custom tags. This project is a an example of MVC(model, view, controller), using javaFX. All FXML files were generated using scene builder to make the screens.

Photo album has 3 packages. The model, which is the back end, view, which is controllers and fxml pages, and application, which holds a class called PhotoAlbum. PhotoAlbum, inside application package, is what is used to run the program. So use that to start.

From there you will enter the login page. There will be no users. This is due to the fact that our program uses absolute paths for the source of the photo. If we hard coded paths, we would not be able to know whether or not the person testing this program was on a windows or a mac. If we hardcoded mac, it wouldn’t work on windows, and vice versa. 
To get to the screen where you can create users, enter “admin” as username, and “admin” as password. Now you will get to the page where you can add, delete, and see all users.
Add some users, and log in with them. 

You will now go to the main album screen. This is where you will see all existing albums. You can rename, add, delete, and create new albums from this page. You can also search by tags or dates from this page.
Enter or create a new album.

In here you can add pictures to your album. Pictures will be sorted from earliest to latest modified date. You can tag and caption all photos in here. You can also move, copy, delete, and add photos from here. New tag types are also created in here.
Do not touch the dat folder! This contains serilized information. If touched or deleted, our program will not run
