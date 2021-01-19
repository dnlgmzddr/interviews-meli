# SatCom

Solution to locate messages point of origin for our constellation of satellite:

- KENOBI
- SKYWALKER
- SATO

the service is available to all rebels in this URL:
- https://satcom.herokuapp.com

## Resources used for the solution

- [Calculate the intersection points of two Circles](https://www.xarg.org/2016/07/calculate-the-intersection-points-of-two-circles/)
- [C implementation](http://paulbourke.net/geometry/circlesphere/tvoght.c)

## Sample requests

Valid `topsecret`:

```
curl --location --request POST 'https://satcom.herokuapp.com/topsecret' \
--header 'Content-Type: application/json' \
--data-raw '{
    "satellites": [
        {
            "message": [
                "este",
                "",
                "",
                "mensaje",
                ""
            ],
            "distance": 538.516,
            "name": "kenobi"
        },
        {
            "message": [
                "",
                "es",
                "",
                "",
                "secreto"
            ],
            "distance": 141.421,
            "name": "skywalker"
        },
        {
            "message": [
                "este",
                "",
                "un",
                "",
                ""
            ],
            "distance": 509.901,
            "name": "SATO"
        }
    ]
}'
```

