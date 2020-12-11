# Aplicación android para el control de un LED RGB.

## Autores
- Jeaustin Sirias Chacón. Carné B66861
- Juan Cortés Rentería. Carné B52250

## Características
Este repositorio muestra la implementación de una aplicación android para el control de un dispostivo LED de tipo RGB a través de un canal de comunicación por Bluetooth, disponiendo de las siguiente funcionalidades:

* Posibilidad de controlar la escala de colores, modificando los canales RGB de un LED desde la aplicación
* Posibilidad de apagar y encender el dispositivo de forma remota
* Posibilidad de controlar la intensidad luminínica del dispositivo LED

## El hardware
Con el fin de simular un "bombillo" controlado por bluetooth se ha ensamblado un breve proyecto a través de la plataforma Arduino como unidad de control local para el LED RGB que reciba las señales enviadas desde la aplicación android a través del módulo bluetooth [HC-05](https://www.microjpm.com/products/interface-base-board-serial-transceiver-bluetooth-module-for-hc-05-hc06/) bajo la topología del siguiente esquema:

![bash_terminal](https://i1.wp.com/randomnerdtutorials.com/wp-content/uploads/2017/04/rgb_led_controller.png?resize=455%2C595&quality=100&strip=all&ssl=1)

### Materiales
* Un módulo bluetooth receptor equivakente al modelo HC-05
* Una tarjeta prototipo
* Tres resistores de 220 ohm
* Una tarjeta Arduino UNO
* 15 cables conductores
* Un LED RGB 

Un acercamiento a la implementación, elaborado para esta actividad se muestra en la siguiente figura:
![bt](https://i.imgur.com/Tjyosu8.jpg)

## El software
Para esta ocasión se ha empleado el entorno de desarrollo [Android Studio](https://developer.android.com/studio) para implementar la aplicación propuesta. A continuación se muestra un pantallazo de la interfaz diseñada para cotrolar el dispositivo LED a distancia:

![gui](https://i.imgur.com/r7tVMV2.jpeg)
