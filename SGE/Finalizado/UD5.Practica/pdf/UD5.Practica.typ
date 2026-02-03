#import "plantilla.typ": proyecto

#show: proyecto.with(
  titulo: "Desarrollo de Componentes en Odoo",
  asignatura: "Sistemas de Gestión Empresarial",
  imagen: image("img/odoo.png")
)


= Ejercicio 1

#figure(
  image("img/image3.png", width: 80%),
  caption: [Muestra del módulo _Práctica 1_ instalado correctamente.]
)

#pagebreak()

= Ejercicio 2

#figure(
  image("img/image4.png", width: 80%),
  caption: [Muestra del módulo _ListaTareas_ modificado.]
)

#pagebreak()

= Ejercicio 3

== Carpeta _models_

Para analizar el módulo _Contactos_ vamos a comenzar buscando en la carpeta _models_, en la que encontramos dos archivos.

#v(0.5cm)

El primero este _res\_users.py_ que le añade funcionalidad al módulo de _Usuarios (res.users)_, el cuál hereda. La función _\_get\_activity\_groups_ busca si hay actividades relacionadas con el módulo de _Contactos (res.partner)_ y si los encuentra sustituye el icono genérico de Odoo por el específico del módulo _Contactos_ y lo sitúa en la barra superior de Odoo, donde se ven las actividades pendientes.

#figure(
  image("img/image5.png", width: 80%),
  caption: [Contenido del archivo _res\_users.py_.]
)

#v(0.5cm)

El segundo es este _res\_partner.py_ que hereda de _res.partner_ para añadirle funcionalidad. La función _\_get\_backend\_root\_menu\_ids_ se autollama y concatena el ID de la aplicación de Contactos para mantenerla seleccionada en la barra superior.

#figure(
  image("img/image6.png", width: 80%),
  caption: [Contenido del archivo _res\_partner_.]
)

#pagebreak()

== Carpeta _views_

Ahora continuaremos analizando la carpeta _views_ en la que encontramos el archivo _contact\_views.xml_

#v(0.5cm)

En esta primera parte del contenido del archivo vemos que se define la configuración básica de la vista del módulo y las vistas principales que tendrán, en este caso, _list > kanban > form_, priorizadas en este orden. 

Además se ve que tiene un mensaje de ayuda para principiantes sin contactos en la aplicación, que motiva a crear uno nuevo.

#figure(
  image("img/image7.png", width: 80%),
  caption: [1ra parte del contenido del archivo _contact\_views.xml_.]
)

#pagebreak()

En esta segunda parte del contenido del archivo se establece la jerarquía de menús con cada _menuitem_, sus prioridades con sus _sequence_, las limitaciones de cada menú según el tipo de usuario y las acciones a ejecutar al clicar cada menú.

Por ejemplo, el acceso al menú _Configuración_ solo está permitido a los usuarios con permisos de sistema.

#figure(
  image("img/image8.png", width: 80%),
  caption: [2da parte del contenido del archivo _contact\_views.xml_.]
)
