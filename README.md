# 2. Pratkikumsaufgabe Software-Architektur Sommer 2017 

## Allgemein
Teammitglieder:  
- Michael Fischer
- Christian Keller

Heroku: <https://shareit-summer-2017-team-x.herokuapp.com>

# API-Beschreibung
## Struktur des REST-URIs
Diese REST-API stellt den Zugriff auf Ressourcen über URI-Pfade bereit. Diese Pfade folgen dabei dem Muster:  

	http://host:port/shareit/media/ressourcentyp 

Der Ressourcentyp kann die Werte *books* und *disc* annehmen. Als Ergebnis erhält man immer ein JSON-Objekt. Entweder mit Fehlerinformationen oder dem angefragten Objekt.

## Ressourcen
### Buch (book)
Eigenschaft | Beschreibung
----------- | ------------
isbn | Eine [ISBN-13](https://de.wikipedia.org/wiki/Internationale_Standardbuchnummer#ISBN-13), die das Buch eindeutig identifiziert.
title | Titel des Buches.
author | Autor des Buches.

### CD (disc)
Eigenschaft | Beschreibung
----------- | ------------
barcode | Ein [Barcode](https://de.wikipedia.org/wiki/European_Article_Number) identifiziert eine CD eindeutig.
title | Titel der CD.
director | Direktor des Inhaltes auf der CD.
fsk | Die angegebene [FSK](https://de.wikipedia.org/wiki/Freiwillige_Selbstkontrolle_der_Filmwirtschaft) für den Inhalt.


## Übersicht
Hier folgt die genauere Beschreibung aller Schnittstellen des REST API:

- books
	- [http://host:port/shareit/media/books](https://github.com/abcshmedu/shareit-summer-2017-team-x#shareitmediabookspost) \[POST\] - neues Buch anlegen.
	- http://host:port/shareit/media/books \[GET\] - alle Bücher im System.
	- http://host:port/shareit/media/books/\{isbn\} \[GET\] -  Buch mit angegebener ISBN.
	- http://host:port/shareit/media/books/\{isbn\} \[PUT\] - Buch mit angegebener ISBN aktualisieren. 
- discs
	- [http://host:port/shareit/media/discs](https://github.com/abcshmedu/shareit-summer-2017-team-x#shareitmediadiscspost) \[POST\] - neue CD anlegen.
	- http://host:port/shareit/media/discs \[GET\] - alle CDs im System.
	- http://host:port/shareit/media/discs/\{barcode\} \[GET\] -  CD mit angegebenem Barcode.
	- http://host:port/shareit/media/discs/\{barcode\} \[PUT\] - CD mit angegebenem Barcode aktualisieren. 

### /shareit/media/books **POST**
##### Method: **POST**
Erstellt ein Buch mit den angegebenen Daten.
##### Beispiel: 
###### Request
```
POST /shareit/media/books HTTP/1.1
Content-Type: application/json; charset=utf-8
Host: shareit-summer-2017-team-x.herokuapp.com
Connection: close
Content-Length: 66
  
{"title":"testbook1","author":"TestAuthor","isbn":"9783836217798"}
```
###### Response
```
HTTP/1.1 201 Created
Connection: close
Server: Apache-Coyote/1.1
Content-Type: application/json; charset=utf-8
Content-Length: 75
Via: 1.1 vegur
  
{"code":201,"status":"CREATED","additionalMsg":"Ressource wurde erstellt!"}
```
Wird versucht ein Buch zu erstellen, das bereits existiert, so wird der Request mit folgendem Response beantwortet:
 ```
 HTTP/1.1 400 Bad Request
 Connection: close
 Server: Apache-Coyote/1.1
 Content-Type: application/json
 Content-Length: 82
 Via: 1.1 vegur
   
 {"code":400,"status":"BAD_REQUEST","additionalMsg":"Ressource existiert bereits!"}
 ```
 
 Eine Anfrage wird immer mit einem JSON-Objekt beantwortet:
 
 Eigenschaft | Erklärung
 ----------- | ---------
 code | Liefert den HTTP-Status-Code
 status | Liefert den HTTP-Status-Text
 additionalMsg | Liefert eine nähere Beschreibung
 
 ###### Mögliche Fehler
 
 Fehlerbeschreibung | HTTP-Status | additionalMsg
 ------------------ | ----------- | -------------
  Ungültige ISBN | 400 Bad Request | *ISBN ungültig!*
  ISBN bereits vorhanden | 400 Bad Request | *Ressource existiert bereits!*
  Autor oder Titel fehlt | 400 Bad Request | *Autor fehlt!* oder *Titel fehlt!*
 
 
### /shareit/media/discs **POST**
##### Method: **POST**
Erstellt eine CD mit den angegebenen Daten.
##### Beispiel: 
###### Request
```
POST /shareit/media/discs HTTP/1.1
Content-Type: application/json; charset=utf-8
Host: shareit-summer-2017-team-x.herokuapp.com
Connection: close
Content-Length: 66
  
{"title":"testdisc","director":"TestDirector","barcode":"0123456789","fsk":16}
```
###### Response
```
HTTP/1.1 201 Created
Connection: close
Server: Apache-Coyote/1.1
Content-Type: application/json; charset=utf-8
Content-Length: 75
Via: 1.1 vegur
  
{"code":201,"status":"CREATED","additionalMsg":"Ressource wurde erstellt!"}
```
Wird versucht eine CD zu erstellen, die bereits existiert, so wird der Request mit folgendem Response beantwortet:
 ```
 HTTP/1.1 400 Bad Request
 Connection: close
 Server: Apache-Coyote/1.1
 Content-Type: application/json
 Content-Length: 82
 Via: 1.1 vegur
   
 {"code":400,"status":"BAD_REQUEST","additionalMsg":"Ressource existiert bereits!"}
 ```
 
 Eine Anfrage wird immer mit einem JSON-Objekt beantwortet:
 
 Eigenschaft | Erklärung
 ----------- | ---------
 code | Liefert den HTTP-Status-Code
 status | Liefert den HTTP-Status-Text
 additionalMsg | Liefert eine nähere Beschreibung
 
 ###### Mögliche Fehler
 
 Fehlerbeschreibung | HTTP-Status | additionalMsg
 ------------------ | ----------- | -------------
  Ungültiger Barcode | 400 Bad Request | *Barcode ungültig!*
  Barcode bereits vorhanden | 400 Bad Request | *Ressource existiert bereits!*
  Direktor oder FSK oder Titel fehlt | 400 Bad Request | *Direktor fehlt!*, *FSK fehlt!* oder *Titel fehlt!*
 
 