# QR-Code-Generation
Simple application that shows how to generate QR in Java
### API

#### Description

Return QR code as BASE64.

#### HTTP request

````
GET /api/v1/generate/qr
````

#### HTTP body request params
````
JSON
{
  "message":"some text or URL"
}
````
#### Sample success response
````
{
   "message":"success",
   "image":"bytes in base64"
}
````
#### Sample fail response
````
{
   "message":"fail"
}
````
