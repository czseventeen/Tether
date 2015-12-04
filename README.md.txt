12/3/2015:
1. Added GSON jar, and used it to convert Home/MyCar/MyStat class objects to a string in JSON fashion.
2. Populated ListView for MyStat page, The random generated stats are appearing in the list correctly.

TODO:
1.Populate ListView for other pages.
2.Make design look prettier.


12/2/2015:
1.Added default constructors in Home and MyCar
2.Added a custom constructor that takes an int as input para. When int is -1, all values are randomly generated.
3.Added toString method to Home and MyCar and Coordinate
4.Added getJSONArray() to Home And MyCar Classes, so we can convert the data into Json Array and store then in Parse.com
5.By using the ParseUser.put(Key,JSONArray) method, random generated data was able to store in cloud.

Todo:
1. Consider removing Coordinate class, b/c JSONArray doesn't take custom classes.
2. Finish up with MyStat page
3. Display the randomly generated data inside the list view.

12/1/2015
1. Fixed when going back to Homescreen, openning the app again would stop unexpectedly
2. Now the ActionBar title displays correctly for each page
3. Added some background color and pictures for each page.
4. try added an icon in the action bar.
To-Do:
1. Try make Navigation Drawer on top of ActionBar.
2. Start adding data to each page.
3. Try storing data on Parse.com



11/30/2015
Features:
1. Signup/ Signin using Parse.com
2. A HomeActivity screen with Navigation Drawer
3. Logging off functionality.
4. imported the Model classes from the other class.