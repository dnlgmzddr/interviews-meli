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
- [LibreCAD](https://librecad.org/), quite helpful to create the tests cases.

## Sample requests

`topsecret`:

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


`topsecret_split`:

```
curl --location --request POST 'https://satcom.herokuapp.com/topsecret_split/kenobi' \
--header 'Content-Type: application/json' \
--data-raw '{
"message": [
"este",
"",
"",
"mensaje",
""
],
"distance": 538.516
}'
```

## What's missing

1. More tests
    1. `topsecret_split` is not covered.
    2. They are no performance tests (I like locust)
2. Instead of a quick curl, a swagger will be nice to show how the API works.
3. For it to be production ready
    1. Proper use of github ci/cd pipelines.
    2. Actuators
    3. Logger / Metrics
    4. `topsecret_split`, is using a good old static map, It would be better to use the `SpringCache` abstraction, it would give us change to grow and span de service across more than one instance.
4. Make the initial position of the satellites available in the `application.properties` as well as the precision.
