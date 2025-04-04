from flask import Flask, request, jsonify
import whisper
import tempfile
import os
from pyngrok import ngrok
from functools import wraps

app = Flask(__name__)
model = whisper.load_model("base")

API_KEY = os.environ.get("API_KEY")

def require_api_key(f):
    @wraps(f)
    def decorated_function(*args, **kwargs):
        if request.headers.get('x-api-key') != API_KEY:
            return jsonify({"error": "Unauthorized"}), 401
        return f(*args, **kwargs)
    return decorated_function

@app.route('/api/transcribe', methods=['POST'])
@require_api_key
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
