from flask import Flask, request, jsonify
import whisper
import tempfile
import os
from pyngrok import ngrok

app = Flask(__name__)
model = whisper.load_model("base")

@app.route('/api/transcribe', methods=['POST'])
def transcribe():
    file = request.files['file']
    temp = tempfile.NamedTemporaryFile(delete=False)
    file.save(temp.name)

    result = model.transcribe(
        temp.name,
        task=request.form.get('task', 'transcribe'),
        language=request.form.get('language', 'en')
    )

    os.unlink(temp.name)

    return jsonify({
        "text": result["text"],
        "language": result["language"],
        "duration": result["segments"][-1]["end"] if result["segments"] else 0
    })

if __name__ == '__main__':
    public_url = ngrok.connect(5000)
    print("Ngrok tunnel: ", public_url)
    app.run()
