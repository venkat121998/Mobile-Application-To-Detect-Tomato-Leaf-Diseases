from flask import Flask,jsonify,request,render_template
import werkzeug
import os
from model import predict_img


app = Flask(__name__)


UPLOAD_FOLDER = 'SaveFolder'
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER


@app.route('/')
def index(): 
    return render_template('index.html')

@app.route('/api/v02/image',methods=['POST'])
def save_img():
    file = request.files['image']
    file.save(os.path.join(app.config['UPLOAD_FOLDER'],file.filename))
    result = predict_img(file.filename)
    print(result)
    return result

if __name__ == '__main__':
    app.run(debug=True)

#curl request command
#curl -i -X POST -H "Content-type: multipart/form-data" -F "image=@Desktop\\img2.jpg" http://localhost:5000/api/v01/image

#To access from mobile phone use this url
#flask run --host=0.0.0.0 --port=5000