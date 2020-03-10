
# Reservation Rest API details
Note: http://maiello-local.k8s.io is the API Gateway (Zuul) where acts as a proxy for all microservices

### Get availables dates

| Http Method | URL |
| ------ | ------ |
| GET | http://host:port/reservation/avail/{arrivalDate}/{departureDate} |

Sample URL: http://maiello-local.k8s.io/reservation/avail/20190126/20190202

Sample response:
```Json
{
    "dates": [
        {
            "date": "2019-01-26T03:00:00.000+0000",
            "available": true
        },
        {
            "date": "2019-01-27T03:00:00.000+0000",
            "available": true
        },
        {
            "date": "2019-01-28T03:00:00.000+0000",
            "available": false
        },
        {
            "date": "2019-01-29T03:00:00.000+0000",
            "available": false
        },
        {
            "date": "2019-01-30T03:00:00.000+0000",
            "available": true
        },
        {
            "date": "2019-01-31T03:00:00.000+0000",
            "available": true
        },
        {
            "date": "2019-02-01T03:00:00.000+0000",
            "available": true
        }
    ]
}
```

### Add new reserve
| Http Method | URL |
| ------ | ------ |
| POST | http://host:port/reservation/add |

Sample URL: http://maiello-local.k8s.io/reservation/add

Body request (application/json):
```Json
{
"user": {"firstname":"Mauricio", "lastname":"Aiello", "email":"mauricio.aiello@gmail.com"},
"campsite":{"id":"1"},
"arrivalDate":"2019-02-01",
"departureDate":"2019-02-04"
}
```
Sample response:
```Json
{
    "reservationId": 1,
    "userId": 2,
    "campsiteId": 1,
    "arrivalDate": "2019-02-01T00:00:00.000+0000",
    "departureDate": "2019-02-04T00:00:00.000+0000",
    "status": "RESERVED"
}
```

### Update a reserve
| Http Method | URL |
| ------ | ------ |
| PUT | http://host:port/reservation/update |

Sample URL: http://maiello-local.k8s.io/reservation/update

Body request (application/json):
```Json
{
"campsite":{"id":"1"},
"arrivalDate":"2019-02-03",
"departureDate":"2019-02-06"
}
```
Sample response:
```Json
{
    "id": 1,
    "userId": 2,
    "campsiteId": 1,
    "arrivalDate": "2019-02-03T00:00:00.000+0000",
    "departureDate": "2019-02-06T00:00:00.000+0000",
    "status": {"reserved"}
}
```

### Cancel a reserve
| Http Method | URL |
| ------ | ------ |
| DELETE | http://host:port/reservation/cancel/{reservationId} |

Sample URL: http://maiello-local.k8s.io/reservation/cancel/1

Sample response:
```Json
{
    "reservationId": 1,
    "userId": 9,
    "campsiteId": 1,
    "arrivalDate": "2019-02-01T00:00:00.000+0000",
    "departureDate": "2019-02-04T00:00:00.000+0000",
    "status": "CANCELLED"
}
```

