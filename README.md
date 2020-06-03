# Mobile-Application-To-Detect-Tomato-Leaf-Diseases
Accounting to almost 6.0% of the total tomato production in the world. Tomato is the third most significant vegetable of India by sharing 8.5% of all out vegetable creation.This shows The importance of tomatoes in India. In this section we discuss a solution wherein farmers can take pictures of diseased tomato leaves , the image taken is sent to the flask server, the flask server has a pre-trained CNN model. The image is fed as input to the CNN model. The CNN model is already trained on diseased tomato leaf images consisting of the 9 different types of tomato leaf diseases,the model can also predict if the leaf is healthy.The Dataset used here is the plant village dataset. Each tomato leaf disease consists of around 700 images. The output of the model is the type of disease the plant is affected with or if the leaf is healthy.

### Follow the below steps to run the project

1. First install the softwares mentioned below as these are essential for running the project
  * Android Studio
  * Visual Studio Code
  * Python 3.7
  
2. The below mentioned python packages are required for running the project
  * Flask
  * Numpy
  * Pandas 
  * Matplotlib
  * Keras
  * Tensorflow
  
3. Once the softwares and packages are installed in the system, clone the current reposiory into a folder in the local machine.

4. Open the **Rest_Api** folder in the vs code editor.

5. Open the terminal in the vs code editor and type the following command (This will start the flask server)
  ```
    flask run --host=0.0.0.0 --port=5000
  ```
  
6. Open the command prompt and type the following command to get the ipv4 address of the system that's running the flask server
```
    ipconfig
```

7. Replace the ipv4 addresss in the following files
_Mobile-Application-To-Detect-Tomato-Leaf-Diseases/tomatoleaf/app/src/main/res/xml/network_security_config.xml_
_Mobile-Application-To-Detect-Tomato-Leaf-Diseases/tomatoleaf/app/src/main/java/com/example/tomatoleafdisease/home.java_

8. Open the android project tomatoleaf using android studio and run the application using the emulator.

9. An application by name _Pestilence_ will be installed, Open the installed android application.

10. A User can either register using his/her email & password and login with the registered credentials to the application or click on skip ,to skip the registration.

11. A demo will be showcased for the user on how to use the app for the purpose of understanding the application.

12. Load a saved tomato leaf image or capture a tomato leaf image and the application will detect the disease the tomato plant is infected with and provide remedies to treat the same.

>_Note : If running the application in a mobile phone rather than emulator then make sure that the mobile phone is connected to the same network as the system hosting the flask server._
 
