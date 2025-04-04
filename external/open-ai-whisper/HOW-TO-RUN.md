
# How to Run and Test the Whisper API in Google Colab

Follow these steps to set up and test the Whisper-based transcription API using Google Colab and ngrok:

## 1. Open a New Colab Notebook
1. Go to [Google Colab](https://colab.research.google.com/).
2. Create a new notebook.

## 2. Install Required Packages
Copy the commands from `collab-commands.txt` into a Colab cell and run them one by one or all at once.
Replace `$TOKEN` with your actual ngrok authtoken.

**Explanation:**
- `openai-whisper` is the Whisper speech recognition model from OpenAI.
- `flask` is the web framework used to serve the transcription API.
- `flask-ngrok` or `pyngrok` is used to expose the local Flask server to the internet.
- `python-dotenv` can be used to manage environment variables if needed.
- `ffmpeg` is required for Whisper to process various audio formats.

## 3. Create the `whisper-api.py` File
Make sure your Colab environment has a file named **[whisper-api.py](./whisper-api.py)**

## 4. Run the API
Finally, run the Python script in a Colab cell:

```bash
!API_KEY=$X-API-KEY python whisper-api.py
```
Replace `$X-API-KEY` with your own generated key.

**Explanation:**
- We load the base Whisper model.
- We define a single endpoint (`/api/transcribe`) that accepts a `POST` request with an audio file in form-data.
- We transcribe the file using Whisper and return the result as JSON.
- `ngrok.connect(5000)` creates a public URL that forwards to `localhost:5000`.

You should see output similar to:
```
Ngrok tunnel:  NgrokTunnel: "http://<random-string>.ngrok-free.app" -> "http://localhost:5000"
 * Serving Flask app 'whisper-api'
 * Running on http://127.0.0.1:5000
```

The `Ngrok tunnel: ...` line contains the public URL that you can use to send requests to your Flask server.

## 5. Test the API
You can now use any HTTP client (such as Postman or IntelliJ HTTP Client) to send a `POST` request to:
```
POST https://<random-string>.ngrok-free.app/api/transcribe
```
with the following form-data:
- **Key**: `file`
- **Type**: `File`
- **Value**: [Your audio file, e.g., `example.mp3`]

If successful, you should receive a JSON response containing the transcription text, language, and audio duration.
