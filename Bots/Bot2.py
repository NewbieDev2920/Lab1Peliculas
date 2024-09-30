import os
import io
from google.oauth2 import service_account
from googleapiclient.discovery import build
from googleapiclient.http import MediaFileUpload

CREDENTIALS_FILE = r'C:\Users\Juanpi\Desktop\Bot\laboratorio1-437200-3176f7c3c565.json'  # Cambia esto a la ruta de tu archivo JSON
SCOPES = ['https://www.googleapis.com/auth/drive.file']

credentials = service_account.Credentials.from_service_account_file(CREDENTIALS_FILE, scopes=SCOPES)
service = build('drive', 'v3', credentials=credentials)

def find_file_id(file_name, folder_id):
    
    query = f"name='{file_name}' and '{folder_id}' in parents"
    results = service.files().list(q=query, fields="files(id)").execute()
    files = results.get('files', [])
    if files:
        return files[0]['id']
    return None  

def delete_file(file_id):
    
    try:
        service.files().delete(fileId=file_id).execute()
        print(f'Archivo con ID {file_id} eliminado.')
    except Exception as e:
        print(f'Ocurrió un error al eliminar el archivo: {e}')

def upload_file(file_name, mime_type, folder_id):

    file_id = find_file_id(os.path.basename(file_name), folder_id)
    if file_id:
        delete_file(file_id)  

    file_metadata = {
        'name': os.path.basename(file_name),
        'parents': [folder_id]
    }
    media = MediaFileUpload(file_name, mimetype=mime_type)

    try:
        file = service.files().create(body=file_metadata, media_body=media, fields='id').execute()
        print(f'Archivo subido con ID: {file.get("id")}')
    except Exception as e:
        print(f'Ocurrió un error al subir el archivo: {e}')

if __name__ == "__main__":
    
    file_to_upload = r'C:\Users\Juanpi\Downloads\peliculas.json'
    mime_type = 'application/json' 
    folder_id = '156Fnklzk8-sgQwjOj0yLhKWrSQd9rXy5'

    upload_file(file_to_upload, mime_type, folder_id)