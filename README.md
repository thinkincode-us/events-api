# events-api

## Authentication

### Get a token
```
curl -v host:port/login \
  --data '{"username": "user", "password": "password"}' \ 
  -H "Content-Type: application/json"
```

### Use a token
Just pass `Authorization: Bearer token_value` header to the request.

## OApi spec
host:8080/swagger/events-api-0.1.yml