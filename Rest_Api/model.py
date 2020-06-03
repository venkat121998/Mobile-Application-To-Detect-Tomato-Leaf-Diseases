# -*- coding: utf-8 -*-
"""
Created on Mon Mar  9 17:18:34 2020

@author: Anirudh
"""
import numpy as np
from keras.applications.imagenet_utils import preprocess_input, decode_predictions
from keras.preprocessing import image
import tensorflow as tf 


#creating a resnet model
#from keras.applications.resnet50 import ResNet50
#model = ResNet50(weights='imagenet')
#model.save('path where u want to save the model')

#function to preprocess the image and predict the image
def model_predict(img_path, model):
    img = image.load_img(img_path, target_size=(150, 150))

    # Preprocessing the image
    x = image.img_to_array(img)
    # x = np.true_divide(x, 255)
    x = np.expand_dims(x, axis=0)
    x= np.divide(x,255)
    pred = model_new.predict_classes(x)
    class_dict = {
        0:'Bacterial Spot',
        1:'Early Blight',
        2:'Late Blight',
        3:'Leaf Mold',
        4:'Septoria Leaf Spot',
        5:'Two Spotted Spider Mite',
        6:'Target Spot',
        7:'Yellow Leaf Curl Virus',
        8:'Mosaic Virus',
        9:'Healthy',
    }

    # # Be careful how your trained model deals with the input
    # # otherwise, it won't make correct prediction!
    # x = preprocess_input(x, mode='caffe')

    # preds = model.predict(x)
    # pred_class = decode_predictions(preds, top=1)   # ImageNet Decode
    # result = str(pred_class[0][0][1])   
    return str(class_dict[pred[0]])


#loading the saved model
model_new = tf.keras.models.load_model('advanced_model.h5',compile = False )
model_new.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])
model_new._make_predict_function()


#make a sample prediction
def predict_img(filename):
    image_path = 'SaveFolder\\' + filename
    return model_predict(image_path,model_new)
