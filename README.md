[![N|Solid](https://www.vectorlogo.zone/logos/springio/springio-icon.svg)](https://nodesource.com/products/nsolid)
### the project was cancelled
# <del> Adkan </del>
## _Administrador de Proyectos Kanban_
[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

## Let's start
### Installation

ADKAN requires [OpenJDK11](https://openjdk.java.net/projects/jdk/11/) to run.

Install the docker file

```sh
$ sudo docker-compose -f docker-compose.yml build
```
#### basic commands

```sh
$ sudo docker-compose -f docker-compose.yml up
$ sudo docker-compose -f docker-compose.yml down
$ sudo docker-compose -f docker-compose.yml ps
```
#### Pro tip
Export a temporal variable to manage the docker-compose  ```$ export COMPOSE_FILE=docker-compose.yml```
```sh
$ sudo docker-compose build
$ sudo docker-compose up
$ sudo docker-compose down
$ sudo docker-compose ps
```
#### Remember!
```sh
$ docker container
$ docker images
$ docker volume
docker network
```
Those commands have the common options
* **ls** - *list*
* **rm** - *remove*
* **prune** - *quit*
* **-a**- *show all*
* **-q**- *show ID*