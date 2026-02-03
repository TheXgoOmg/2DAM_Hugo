#import "plantilla.typ": proyecto

#show: proyecto.with(
  titulo: "Desarrollo de Componentes en Odoo",
  asignatura: "Sistemas de Gestión Empresarial",
  imagen: image("img/odoo.png")
)

#show raw.where(block: true): it => pad(left: 2em, it)


= Ejercicio 1
== Módulo Práctica0 con `scaffold`

Para la creación del componente _'Practica0'_ con el comando `scaffold` debemos ejecutar los siguientes comandos.

```bash
docker exec -it -u root /bin/bash
```
Para ejecutar comandos en la terminal dentro del contenedor.

```bash
odoo scaffold Practica0 /mnt/extra-addons
```
Ahora ejecutamos el comando `scaffold` para crear el módulo _practica0_ (ya que crea el módulo en minúsculas automáticamente) en el directorio /mnt/extra-addons.

```bash
chmod 777 -R /mnt/extra-addons/practica0
```
Finalmente le añadimos permisos recursivamente al módulo creado.

#figure(
  image("img/image1.png", width: 80%),
  caption: [Muestra de la creación del módulo _practica0_ siguiendo los pasos anteriores.]
)

#v(2cm)

== Módulo Práctica1 manualmente

Para crear este segundo módulo manualmente debemos crear la carpeta _practica_1_ en el directorio addons correspondiente.
```bash
mkdir /usr/lib/python3/dist-packages/odoo/addons/practica_1
```

_ñpene_
