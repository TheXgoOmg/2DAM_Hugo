// ============================================================
// DOCUMENTO DE EJEMPLO — Memoria Proyecto Final 2º DAM
// ============================================================
// 1. Copia plantilla-dam.typ a la misma carpeta que este archivo
// 2. Sustituye los valores de configuración por los tuyos
// 3. Si tienes logo: pon la ruta, ej: logo: "logo.png"
// 4. Escribe tu memoria sustituyendo el contenido de ejemplo
// ============================================================

#import "plantilla.typ": proyecto, figura, tabla

#show: proyecto.with(
  titulo: "Baldur's Bones",
  alumno: "Hugo Tarín González",
  tutor: "Borja Aparisi Navarro",
  curso: "2025/2026",
  ciclo: "Desarrollo de Aplicaciones Multiplataforma",
  centro: "CEIPFP Cheste",
  logo: "img/img_cipfpcheste.png"   // Cambia a: logo: "logo.png"  cuando tengas el logo
)

// ============================================================
// A partir de aquí escribe tu memoria normalmente
// Usa = para capítulos, == para apartados, === para subapartados
// ============================================================

= Introducción

  Este proyecto consiste en un videojuego multiplataforma y multimedia, desarrollado en Unity, que contiene sistemas de movimiento basado en vectores, de gestión de escenas y de sonidos, además de scripts dedicados a detalles para que sea lo más cómodo y visual para el usuario.

== Justificación

Al comienzo del curso iba a desarrollar junto con otro alumno una plataforma de gestión de flujos de trabajo, pero con el paso de las semanas decidimos cambiar y hacer proyectos por separado, yo me decanté por llevar más allá el videojuego de PMDM, ya que ya había tenido la ilusión de programar un videojuego, y decidí que era el momento idóneo.

== Objetivos

Los objetivos principales del proyecto son los siguientes:
- Desarrollar una UI/UX satisfactoria para el usuario.
- Implementar un sistema de guardado de progreso para los datos del videojuego.
- Crear un flujo de juego equilibrado y con avances en el tiempo.
- Generar una ambientación y entorno inmersivo.
- Tener la opción de jugar en PC, móvil y con mando.

= Tecnologías

== Unity

La principal herramienta de desarrollo es Unity, ya que facilita infinitamente la tarea de desarrollar un videojuego, con muchas facilidades que incorpora que ha hecho viable el desarrollo y el cumplimiento de  de este proyecto.

Algunas de estas tecnologías que ofrece Unity y he implementado en el proyecto son las siguientes:

=== NavMesh

Es un sistema basado en IA que genera una malla sobre un objeto con una capa específica que utilizaremos como terreno (NavMesh), para permitir que nuestros enemigos (NavMeshAgent), lleguen a nuestro personaje (`agent.SetDestination(personaje)`), usando algoritmos de IA para encontrar el camino más rápido evitando obstaculos (NavMeshObstacle) o simplemente objetos demasiado grandes como para superarlos (configuración de los Agents). Además de crear links (OffMeshLink) para ir de un lado del NavhMesh a otro atajando.

En esta imagen se puede observar perfectamente la malla que crea NavMesh y como afectan los demás elementos del sistema.

#figura(
  image("img/img_navmesh.svg"),
  "Imagen visual de los elementos del NavMesh",
  width: 55%
)

Esto ha facilitado el apartado de la jugabilidad, que afecta directamente al objetivo de una UX satisfactoria.

#pagebreak()

=== UINavegation

Es el sistema que controla la selección de elementos en la pantalla que pueden ser accionados (Interactable) en la escena, teniendo la opción de establecer los cambios a elementoss vecinos de manera automática o manual.

#figura(
  image("img/img_uinavigation.png"),
  "Imagen real del flujo entre botones generados por UI Navigation del proyecto"
)

Esta función me hizo extremadamente fácil añadir la funcionalidad de los menús con el mando.

=== InputSystem

Es el último sistema para detectar entradas de cualquier dispositivo de Unity. Funciona con un concepto de acciones, las cuales se usan para realizar acciones en el código.

#figura(
  image("img/img_inputsystem.png"),
  "Imagen explicativa del uso de InputSystem"
)

En esta imagen se explican los conceptos de Maps, que agrupan una serie de Actions, las cuales tienen asociadas unos Bindings, que son las entradas de diferentes dispositivos que activan esa Action.

Se puede llamar al valor que devuelve cada acción por la siguiente estructura:
```csharp
  InputSystem_Actions.<Map>.<Action>.<Method>
```
Pudiendo ser Method un `isPressed()` o `ReadValue()`, según el tipo de dato que devuelva esa acción.

Esta tecnología me permitió implementar la funcionalidad de multiplataforma de una manera mucho más sencilla, ya que para implementar el ataque de mi personaje con el mando, por ejemplo, simplemente tenia que agregar el Binding correspondiente del botón que quiero que active la Action de Ataque.


=== Blend Tree

Es una tecnología que vive dentro del Animator, que es el sistema que gestiona las animaciones de los objetos. Específicamente Blend Tree se encarga de mezclar animaciones según unos parámetros. Si solo se utiliza un parámetro es un Blend Tree 1D, si utiliza 2 es un Blend Tree 2D.

Un ejemplo de esto puede ser el cambio de animación entre una animación Caminar y Correr, basándose en parámetro Velocidad. Por defecto es proporcional el aumento del uso de una animación u otra, en 0.5 de Velocidad utilizará el 50% de los datos de cada animación, pero esto puede cambiarse.

#figura(
  image("img/img_blendtree.png"),
  "Imagen sobre otro ejemplo de uso de Blend Tree 1D"
)

Esta tecnología me ayudo a crear una UX mucho más amigable y visual para el usuario, que de otra manera habría sido imposible, sabiendo mi poca experiencia en este sector.

=== PlayerPrefs

Este es el sistema más simple que ofrece Unity para guardar y cargar datos de forma persistente en la memoria del dispositivo. Tiene un formato clave-valor, aunque solo cuenta con tres tipos de datos, `Int`, `Float` y `String`.

Así se guarda un valor en la memoria del dispositivo:
```csharp
PlayerPrefs.SetInt("monedas", 150);
// Asina a la clave "monedas" el valor de 150 y la guarda en el sistema
```
Y así se carga un valor de la memoria al código:
```
int monedas = PlayerPrefs.GetInt("monedas", 0); // Devuelve 0 por defecto
// Busca el valor de la clave "monedas" y si no la encuentra devuelve el valor por defecto
```

Esta funcionalidad fue vital para poder hacer de manera simple y eficaz uno de los grandes objetivos del proyecto, un sistema de guardado de datos para mantener el progreso del usuario.

#pagebreak()

== Relación con asignaturas

=== Desarrollo de Interfaces

Las interfaces están en cada parte del juego, sinceramente les he puesto esmero, sobretodo en el apartado de multiplataforma, quería que la versión para ordenadores fuese jugable y cómoda, sin elementos que estorben, a la vez de que la de móviles fuese simple e intuitiva, sin dificultades.

Por lo que decidí hacer una versión única para cada dispositivo únicamente por esta funcionalidad, y mejorar la UX.

En cada una de las escenas del videojuego hay una estructura de interfaces, las cuales os mostraré más adelante, que se adapta dinámicamente a la pantalla desde la que se ejecuta, para que no hubiesen cambios imprevistos entre unas y otras.

=== Acceso a Datos

La aplicación del módulo de Acesso a Datos iba a ser más tedioso y complejo en mi cabeza de lo que acabó siendo, únicamente gracias a la funcionalidad de PlayerPrefs todos los problemas se fueron.

Como ya he explicado sobre esta tecnología, Unity guarda y carga los datos en los archivos del sistema mediante un formato clave-valor de la manera más simple posible, y únicamente con dos comandos puedes acceder y actualizar la información del progreso del usuario.

Así cumpliendo con los requisitos de este módulo.

=== Programación Multimedia y Dispositivos Móviles

Este es el módulo que más presente está en el proyecto, todos los conocimientos de scripts, programación, lógica de Unity, conceptos de desarrollo de videojuegos...

Todo esto lo he tenido que volcar en este proyecto. Uniendo todos los componentes que hacen de un videojuego algo multiplataforma y multimedia, además de mi búsqueda de hacerlo cómodo y entretenido, no únicamente un museo de buenas prácticas y código limpio y funcional.

=== Programación de Servicios y Procesos

La funcionalidad de este módulo en el proyecto es para ir al detalle con la elegancia y lo visual, ya que los hilos principales que hacen que el videojuego funcione los ejecuta únicamente Unity.

Aún así Unity también facilita la creación de hilos, usando Coroutinas.

El mayor uso que le he dado a estos hilos han sido como temporizador, que el sistema espere 3 segundos de manera paralela para ejecutar una acción.

Por ejemplo:

```csharp
  private IEnumerator MostrarGameOver(string escena, float velocidad)
  {
    yield return new WaitForSeconds(1.3f);
    
    SceneManager.LoadScene(escena, LoadSceneMode.Additive);
    CambiarVelocidad(velocidad);
  }
```
Aquí la función del hilo es hacer que cuando se ejecute MostrarGameOver espere 1.3 segundos antes de ejecutar el código después del `yield return`, lo que hace que quede más estético. Otro uso que le doy es hacer un `yield return` es hacer que espere 3 segundos antes de destruir el objeto de un enemigo, con un código que genera una desaparición muy visual.

= Desarrollo del proyecto

Ahora vamos a hablar más profundamente de la estructura y la implementación de todo lo que hace que el videojuego funcione correctamente.

== Ambientación

Al comienzo tenía muchas ideas que me ilusionaban para un videojuego, muchos enemigos, consumibles, objetos, equipaciones... Rápidamente me di cuenta de que no era realista, por lo que decidí comenzar por un solo enemigo, el cual casualmente fue el esqueleto que me dió la idea de la ambientación del entorno de mi videojuego... un cementerio.

#figura(
  image("img/img_ambientacion.png"),
  "Imagen sobre la ambientación del mapa en la que se desarrolla el videojuego.",
  width: 70%
)

Un cementerio, un gran cementerio avallado por grandes verjas, sin escapatoria. Dentro de él podemos ver un par de edificios con la misma temática tenebrosa y antigua, además de unos caminos de piedra y unas verjas más pequeñas destrozadas por 'algo'. Resaltando todo, hay cuatro estructuras de piedra, demasiado simétricas y estratégicamente colocadas como para ser casualidad ¿verdad?

Más adelante veremos su función.

#pagebreak()

== Sonido

El sonido también es una muy buena fuente de ambientación, tanto de sonidos naturales, generados por el entorno, como generados por otros objetos.

*Antes destacar* \
Para ambos tipos de sonido he creado un script que hace más fácil gestionar las pistas de audio de cada tipo de sonido, con la información necesaria para reproducirlas correctamente y asignándoles la pista de audio original. Además de añadirlas como una opción en Unity del apartado Create, para crear un objeto de estos scripts.

=== MusicManager.cs

Este es el encargado de gestionar la música de fondo, que concuerde con la situación en la que estás. Cada menú tiene su música predeterminada, por lo que al cambiar de menú la música cambia.

Al estar presente en todas las escenas del juego decidí apli:car el patrón de diseño Singleton, lo cual es crear una instancia estática, muy llamada Instance en el mundo de C\#, para que solo existiese un objeto con este script en todo el proyecto, el cuál será el encargado de gestionar toda la música de fondo de todo el videojuego.

Para reproducir estos sonidos se usa un AudioSource general de toda la escena, no es un sonido 3D.

=== SoundManager.cs

Este es el script que se encarga de gestionar efectos de sonido, una única vez por ejecución. Por la forma en el que lo he diseñado cada escena que tendrá la posibilidad de generar este tipo de sonidos tiene este script asignado a un objeto, además de un AudioSource que tendrá cada objeto con la capacidad de ejecutar estos sonidos.

He modificado los valores del Spatial Blend en todos los AudioSource de objetos externos al personaje de la escena de Juego, lo cual hace que tenga un cierto porcentaje de sonido 3D, para que tenga cierto punto de realismo e inmersión. Manteniendo los sonidos de menú completamente 2D.

#pagebreak()

== Escenas

Para el flujo del videojuego he decidido crear cuatro escenas, Menu, Mercado, Juego y GameOver con las que el usuario puede moverse y tener la experiencia total del videojuego.

=== Flujo entre escenas

El flujo entre escenas se puede ver claramente en la siguiente imagen:

#figura(
  image("img/img_flujo_escenas.png"),
  "Imágen sobre el flujo de escenas del proyecto."
)

Como puedes ver, todas las escenas se mueven entre sí por medio de un botón que se acciona... Todas, menos una.

*SceneAdditive*
Este término es el que se utiliza para indicar que quieres añadir una escena como aditiva, osea que se sobreponga a la actual.

Y este es el modo que he usado para cargar la escena de GameOver.

#figura(
  image("img/img_sceneadditive.png"),
  "Muestra de la sobreposición de la escena GameOver sobre la escena Juego."
)

Utilizando una coroutina como las que he mencionado antes para que tarde 3 segundos en aparecer, además de aparecer con un FadeOn, quedando muy estético.

Algo que aprendí mientras implementaba esta funcionalidad es que al cargar una escena sobre otra el EventSystem que usa es el de la escena que había antes, por lo que no hace falta añadirla, entre otros elementos y funciones que actúan parecido.

== MenuSystem.cs

Antes de continuar, creo que es importante explicar la funcionalidad del script MenuSystem, ya que ayuda a que la navegación entre escenas funcione facilmente.

Este script tiene un método para cargar cada una de las escenas, además de uno para cerrar el videojuego, que se activa con el botón 'Salir' del menú.

Cada método tiene 3 partes:

+ *Activación o desactivación del ratón*: Esta parte desactiva el ratón al comienzo del método que activa la escena 'Juego' y lo activa en el resto de escenas, ya que es necesario.

+ *StartCoroutine*: Ejecuta una coroutina para cambiar a la escena especificada con retraso, para un efecto de carga.

+ *MusicManager*: Finalmente se ejecuta la música de fondo de esa escena.

Cada uno de estos métodos se activan especificando la acción a ejecutar en el apartado de 'On Click' de los botones.

#pagebreak()

== Escena 'Menu'

Esta es la escena más simple de todas.

=== Diseño

- Con un diseño de un fondo con un Aspect Ratio Filter que hace que no se achate la imagen, sino que amplíe sobre la zona de la imagen más significante cuando la resolución de la pantalla lo requiera.

- El título del nombre del videojuego es un elemento aparte para no afectar a su resolución y que siempre sea visible con calidad. Siempre fijo a cierta distancia del borde superior izquierdo.

- Finalmente pasamos a los botones, con un diseño basado en una distancia fija entre ellos y hacia el borde inferior izquierdo de la pantalla.

=== Implementación

La única implementación de esta escena es el uso del script de MenuSystem para activar el cambio de escena mediante clicar el botón adecuado.

Además del botón 'Salir', que utiliza el mismo script para ejecutar su función.

#figura(
  image("img/img_menu.png"),
  "Imagen de la escena del 'Menu' del proyecto."
)

#pagebreak()

== Escena 'Mercado'

Esta escena es pura interfaz dinámica.

Pero antes, es necesario explicar dos script:

=== CoinsSystem.cs

- Este script es el que cambia el valor numérico de un panel de monedas. Al cual hay que pasarle el texto que muestra el valor del panel y la cantidad a asignar. Esta cantidad se utiliza únicamente al cargar el objeto en la escena.

#figura(
  image("img/img_coinssystem.png"),
  "Imagen del panel de CoinsSystem."
)

Todos los objetos que muestren un panel como este llevan un `CoinsSystem.cs` asignado.

- También contiene los métodos `AddCoins` y `SubtractCoins`, que se utilizan para añadir y restar X cantidad de monedas al valor del panel de monedas asignado al script, una vez ya se ha cargado por primera vez el objeto.

=== UIManager.cs

Este script es muy importante en el apartado de la UI de todo el proyecto. Aunque a su vez es muy sencillo.

Se compone de dos partes fundamentales:

1. *Asignación de los objetos*: A este script se le asignan los objetos a gestionar desde el programa de Unity. Tiene una interfaz embellecida para que se dividan las asignaciones por apartados, así es más intuitivo.

2. *Cambio de estado o valores*: En este segundo apartado ya solo queda modificar cada objeto según requiere, la idea es que todos los demás scripts puedas modificar cualquier apartado de la UI a partir de este script:
  - Si es un panel visual de la escena 'Juego' se le asignará el estado que se le pase como parámetro, para que se pueda visualizar en la ejecución del proyecto o no.
  - Si es un texto a modificar se aplicarán los textos que se le pasen como parámetro de la forma predefinida en su método de modificación.

=== Diseño

- Lo que más destaca de esta escena es el panel de madera que hace de soporte para las Cards de dentro, siendo responsive a cualquier pantalla.

- En la esquina superior derecha tenemos un panel que muestra la cantidad de monedas que tenemos acumuladas, ajustada al tamaño de la pantalla.

- Las tres Cards de mejora de recursos, que tienen:
  - Texto de nivel del recurso.
  - Imagen representativa dinámica al tamaño de la Card y sin achatamientos.
  - Texto de muestra de la mejora de cada estadística del recurso.
  - Panel de muestra del coste de mejora.
  - Botón de mejora del recurso.

- Botón para volver a la escena 'Menu' en el centro inferior.

=== Implementación (MarketSystem.cs)

Sabiendo la distribución de elementos y su agrupación por la escena 'Mercado' ya os puedo explicar este script.

Este script se usa únicamente para la mejora de los recursos del usuario, y se dispone en dos grandes bloques:

+ *Plasmar datos al panel* \
  Este paso es importante, ya que lo que hace es usar PlayerPrefs para buscar y asignar, valores encontrados o por defecto, a todos los textos dinámicos de la escena a través del UIManager para que todo sea visual y preciso.
  
  Esto incluye: el nivel del recurso, valores de las estadísticas y coste de mejora.

+ *Mejora y actualización* \
  Esta parte tiene un método para la mejora y actualización de cada recurso, cada cual se acciona por el 'On Click' de cada botón a su respectivo recurso.
  
  La estructura del funcionamiento de estos métodos es:
  1. Asignar el valor de coste a una variable, por medio del PlayerPrefs.
  2. Ejecutar el método SubtractCoins del CoinsSystem y almacenar el booleano de exito en una variable.
  3. Reproducir el sonido correspondiente, si se mejoro exitosamente o no, mediante el SoundManager.
  4. Aplicar los cálculos de incremento de nivel a cada estadística, al valor del nivel mismo y ejecutar un método de `RecalcularCoste()` que modifica y guarda el siguiente coste de mejora de forma exponencial.
  5. Almacenar todos estos cambios en la clave correspondiente del PlayerPrefs


#figura(
  image("img/img_market.png"),
  "Imagen de la escena 'Mercado' del proyecto."
)

== Escena 'Juego'

Esta es la escena en la que más tiempo pasarás, por ello le he dedicado tiempo a que sea cómoda y no agobien los elementos de la interfaz.

=== Diseño

En el primer vistazo podremos observar:

- En la parte superior izquierda la barra de salud de nuestro personaje.
- En la parte superior opuesta el panel de monedas que tenemos.
- En el caso de que estemos jugando la versión móvil veremos que en la mayor parte de las esquinas inferiores tendremos botones y un joystick para adecuar la jugabilidad en estos ddispositivos.

Conforme vayamos jugando aparecerán otros elementos de la interfaz, como pueden ser:

- El contador de oleadas que aparecerá en la zona superior central.
- Además de las interfaces de interacción con las armas que aparecerán cuando nos acerquemos a ellas.

=== Implementación

Todos estos elementos están sujetos al script UIManager que afecta a esta escena, tomando el control de cuando aparecen y desaparecen en pantalla, además de cambiar sus valores a tiempo real.

- Comenzando por la barra de vida, tiene su propio script que actualiza la barra verde dependiendo del porcentaje de vida respecto a su vida máxima que le queda.
- En cuanto al panel de monedas, utiliza el script CoinsSystem explicado anteriormente, siendo activada la actualización del valor cuando se detecta la muerte de un enemigo.
- La interfaz móvil se incluye si detecta una pantalla táctil al iniciar la escena.
- Con el texto del contador de oleadas lo maneja otro script llamado WaveManager a través del UIManager, más adelante explicaremos la funcionalidad interna de este.
- Igual ocurre con los textos de interactuar con las armas, pero de manera más compleja. Lo maneja el script Coger_Arma del personaje, y según las entradas de datos que detecte muestra un texto u otro. Ya que la interfaz de cambio de arma es diferente para móvil, móvil PC o mando. Más adelante se explicará en profundidad. 

#figura(
  image("img/img_juego.png"),
  "Imagen de la escena 'Juego' del proyecto."
)

=== DayNightCycle.cs

Para una mayor ambientación y un poco de sensación tétrica quería implementar un sistema de noche, y para que no fuese estático decidí hacer un ciclo de día y noche, ahí nace este script.

Este script funciona así:

- Tienes dos objetos que generan luz, sol y luna, uno emite luz amarilla/blanca y el otro azul oscura, posicionados opuestamente.
- El script va variando la intensidad de cada objeto conforme se acerca al horizonte, usando parámetros de máximas intensidades y tiempo que tarda en hacer un ciclo, para que sea personalizable. Además de que el mismo genera la rotación de los objetos, de manera sincronizada.

#figura(
  image("img/img_daynightcycle.png"),
  "Imagen del estado de noche del proyecto,"
)

== Escena 'GameOver'

Esta es la escena especial, ya que no tiene implementación, es gestionada en su totalidad por los scripts existentes en la escena 'Juego'. Únicamente que aparece con un difuminado, por el script MenuSystem.cs, pero sigue sin ser una funcionalidad de la escena.

=== Diseño

El diseño de esta interfaz es simple y estática en si misma, en la ejecución del juego adopta la funcionalidad al igual que cualquier otra escena:

- Una imagen que hace como marco, ocupando toda la pantalla.
- Una imagen bien acomodada que da la noticia del GameOver.
- Y los dos botones para continuar con el flujo de juego tras perder la partida.

Es simple pero hace su función tan bien como las otras.

#figura(
  image("img/img_gameover.png"),
  "Imagen de la escena 'GameOver' del proyecto"
)

#text(size: 9.5pt)[*Esta imagen no está sacada de la ejecución completa del proyecto, en la ejecución se vería de fondo la escena 'Juego', ya que la escena 'GameOver' ha sido cargada sobre esta.*]

= Personaje

Continuamos con el personaje, el objeto que más scripts y trabajo interno tiene.

== Movimiento

El movimiento va atado a dos scripts, uno controla el movimiento sobre la superficie (`Moviment_Cub.cs`) y otro el movimiento del salto (`Moviment_vertical.cs`).

El funcionamiento es bastante complejo, en término de matemáticas y física, pero simplificado, utiliza vectores y fuerzas para darle movimiento al objeto.

Lo interesante está en el uso del `InputSystem` que detecta cuando quieres que se mueva o salte, y ejecuta fuerzas según cada acción.

== Vida/Muerte

Esta funcionalidad va ligada al script `PlayerHealthSystem.cs`, lo que hace es:

- Acceder al valor de la clave que contiene el nivel y la vida máxima dentro de los PlayerPrefs.
- Además de tener el método de `TakeDamage`, que resta a la vida actual el valor pasado por defecto. Esta función es pública para que los enemigos puedan accionarla. Además actualiza la barra de vida de la interfaz (`Healthbar.cs`) cada vez que se ejecuta.

Si la vida es igual o inferior a cero se ejecuta el método `Morir`:

- Esto activa el booleano `IsDead` y la animación de muerte.
- Desactiva los scripts de movimiento y combate.
- Desactiva toda interfaz de usuario que esté activa.
- Además de activar la música de la escena `GameOver`, la cual se muestra gracias al booleano `IsDead` en estado `True`.

=== Healtbar.cs (prefab externo)
  
El script `Healthbar.cs` fue desarrolla por otro compañero, Marcos Sancho, y lo reutilicé en mi proyecto, es muy útil para mostrar y actualizar la barra de vida de mi personaje.

== Mejora

El sistema de mejora del personaje, al igual que el de sus armas está ya definido en el script `MarketSystem.cs`, ahí están los valores de incremento establecidos.

- Los valores son que cada mejora al personaje incrementa su vida máxima (MaxHealth) en 10 puntos. Siendo capaz de resistir más ataque enemigos.

== Animaciones

El sistema de animaciones del personaje se gestiona desde este único script, `Animaciones.cs`:

- Guarda hashes de todos los parámetros que se pueden modificar en el `Animator` del personaje.
- Actualiza constantemente para mostrar las animaciones de movimiento correctas, según el parámetro de velocidad en el eje X e Y. En este apartado es donde usé ambos parámetros para fusionar las cuatro animaciones de movimiento y la animación `Idle` (quieto).

#figura(
  image("img/img_mapablendtree.png"),
  "Imagen sobre el mapa de animaciones 2D de BlendTree utilizado."
)

- Tiene métodos públicos que activan animaciones, siendo accionadas por otros scripts, como la animación de ataque, siendo accionada por el script `Player_combat.cs`.

== Equipar arma

Esta funcionalidad está gestionada por el script `Coger_Arma.cs` que es el que se encarga de poner el arma en las manos del personaje y sea usable, se aplica a un objeto que hace de avisador cuando entra en contacto con un objeto con el tag 'Weapon'.

Entonces:

- Comprueba si llevas un arma equipada, si llevas, muestra la interfaz de interactuar con el arma de 'Cambiar', sino muestra la de 'Coger'. Si clicas el botón adecuado según la situación sigue con estos pasos.
- Ejecuta el método 'colocar_Arma', que le asigna el tag 'CurrentWeapon', la coloca en la posición correcta (posición y rotación), según el arma, le asigna como padre del objeto a nuestro personaje y finalmente asigna a la variable 'currentWeapon' del script este objeto.
- Si tienes un arma equipada, previamente al método 'colocar_Arma' ejecuta 'soltar_Arma', que deshace todo lo realizado por 'colocar_Arma' sobre el 'currentWeapon' del script.

== Atacar

El script `Player_combat` es el que se encarga de esta funcionalidad.

- Detecta la entrada desde el InputSystem y activa la animación de ataque, pasando como parámetro el cooldown de ataque de cada arma, para no reproducir la animación cuando no es necesario.
- También tiene los métodos `EnableDamage` y `DisableDamage`, que activan y desactivan la función de colisión del arma equipada, estos métodos son ejecutados por la animación de ataque durante los frames en los que se quiere provocar el daño a los enemigos.
- Según la naturaleza del arma hará uso de esta funcionalidad o no (que como veremos en el caso del Báculo no lo hará).

== Sonidos

Los sonidos reproducidos por el `SoundManager.cs` sobre este personaje es únicamente el sonido de ataque, que se ejecuta desde la animación de ataque, en el punto más álgido del golpe, golpee o no a un enemigo se reproducirá  .

= Enemigos

Estos enemigos, por su naturaleza de ser vivo (o no tan vivos), se parece mucho a nuestro personaje principal.

== Movimiento

El sistema de movimiento de nuestros enemigos se basa enormemente en la tecnología del NavMesh, usando NavMeshAgent sobre estos enemigos.

- Mediante el script `EnemyMovement.cs` actualiza en cada frame el punto al que debe ir, mediante el método `SetDestination()` que incluye NavMeshAgent. 
- Además de tener mucha personalización, en cuanto a velocidad, cambios de animación de correr y caminar basados en distancia del objetivo y su distancia de detención.
- También tienen una función de evitar el giro automático y el movimiento hacia el objetivo cuando realiza ciertas acciones y animaciones, para que sea más realista.

Todas estas acciones de bloqueo se ejecutan desde cada animación del enemigo, de manera individual.

== Recompensa

Este es el script más simple, se llama `InfoEnemy.cs` y únicamente tiene un valor que se puede modificar para personalizarlo por tipo de enemigo, etc y lo devuelve cuando lo solicitan.

Es únicamente utilizado para actualizar la cantidad de monedas por el script `CoinsSystem.cs` cuando se elimina a un enemigo.

== Vida/Muerte

Este script `EnemyHealthSystem.cs` se parece mucho a la versión del personaje, tienen sus propias barras de vida (no hay diferencia en este script respecto a la versión del personaje), que se actualizan por la misma función `TakeDamage()`, la única diferencia es:

- Que reproduce un sonido cada vez que recibe daño por medio de `TakeDamage()`.
- Cuando muere:
  - Reproduce un sonido de muerte.
  - Actualiza la cantidad de monedas basándose en su `InfoEnemy.cs` y el objeto e CoinsSystem de la escena.
  - Además de desactivar el script de animaciones, su barra de vida y su función de movimiento del NavMeshAgent.

== Animaciones

La funcionalidad de este script `EnemyAnimationController.cs` es igual a la del personaje, cuando llaman a cada método reproduce la animación asignada. Las animaciones con funciones especiales son:

- Las animaciones 'TakeDamage' y 'Scream' reproducen un sonido específico y bloquean el movimiento del enemigo.
- La animación de muerte detiene todas las coroutinas activas y activa otra coroutina para calcular el tiempo que tarda en destruir el objeto después de su muerte.

#text(size: 9.5pt)[*Estas coroutinas son usadas para marcar el tiempo que tardan los enemigos en volver a atacar (2 segundos), este tiempo se calcula en paralelo con estos hilos.*]

== Arma y Ataque

=== Arma

El arma tiene su propio script `DealDamage.cs` que al igual que el personaje:

- Activa la colisión entre dos frames específicos y activa el método `TakeDamage()` del personaje cuando colisiona con él.
- Tiene su propio atributo de daño, basado en los atributos del enemigo.
- Al golpear al personaje con alguno de sus ataques emitirá un sonido de filo a través del Audio Source del enemigo.

=== Ataque

El ataque del enemigo se decide por el script `EnemyAttack.cs`, este se encarga de:

- Si la distancia es menor a la distancia de ataque y puede atacar (osea que han pasado más de dos segundos desde el anterior ataque) activará `DecidirAtaque()`.
- Este método decidirá entre cuatro animaciones de ataque, la ejecutará y activará la coroutina de `EsperarParaAtacar()`. Las animaciones son:
  - Slash01: Que hace un daño alrededor suyo con un 100% del daño del enemigo.
  - Slash02: Hace un daño alrededor suyo en sentido contrario al Slash01 y hace un 150% del daño del enemigo.
  - Stab: Embiste con una estocada hacia el último punto del enemigo antes de realizar la animación y realiza un 200% de daño del enemigo. Es difícil de dar, pero si te pone en su blanco, prepárate.

== Sonidos

Todos los sonidos del enemigo son reproducidos con la funcionalidad de Spatial Blend del Audio Source que tiene el enemigo activada en algún valor, siendo variado este valor según el tipo de enemigo. Esto aumenta que tan 3D se escuchan los sonidos emitidos por ese Audio Source.

Recapitulemos los sonidos del enemigo:
- Scream: Se emite al ejecutar el ataque 'Scream' del enemigo, es un grito monstruoso.
- Hit_enemy: Se reproduce al reproducir el método `TakeDamage()` del enemigo, siendo un quejido al daño recibido.
- Hit_player: Suena un sonido de cuchillo afilado al reproducir el método `TakeDamage()` del personaje, representando el daño que te han causado.
- Enemy_death: Al ejecutar el método `Morir()` del enemigo se reproduce este sonido, siendo un sonido que trata de recrear unos huesos cayendo unos encima de otros.

== Tipos

Estos son los tipos de enemigos que hay, y las diferencias entre ellos.

Contamos con 2 tipos de enemigos, siendo:

#tabla(
  caption: "Comparativa de enemigos",
  columns: (auto, 2cm, 3cm, 1fr),
  align: (col, row) => if col == 0 { horizon } else { center + horizon },
  stroke: 0.5pt,
  fill: (col, row) => if row == 0 or col == 0 { rgb("#1a3a5c") } else if calc.odd(row) { rgb("#f0f4f8") } else { white },
  table.header(
    text(fill: white, weight: "bold")[],
    text(fill: white, weight: "bold")[Skeleton],
    text(fill: white, weight: "bold")[BigSkeleton],
    text(fill: white, weight: "bold")[Explicación],
  ),
  text(fill: white, weight: "bold")[Tamaño], [x1.25], [x2.5], [El BigSkeleton es el doble de grande, teniendo mayor alcance de ataque y mayor área para recibir golpes.],
  text(fill: white, weight: "bold")[Velocidad], [2 y 5], [3 y 7], [Los BigSkeleton corren más que los normales.],
  text(fill: white, weight: "bold")[Vida], [100], [200], [Los BigSkeleton tienen el doble de vida que los normales.],
  text(fill: white, weight: "bold")[Daño], [10], [20], [El daño base de los BigSkeleton es el doble que el de los normales.],
  text(fill: white, weight: "bold")[Recompensa], [2], [10], [La recompensa de monedas de los BigSkeleton es notablemente superior a la de los normales.],
  text(fill: white, weight: "bold")[Spatial Blend], [0.7], [0.6], [El Spatial Blend cuanto más alto es más 3D es, por lo que la distacia con el enemigo afecta más, al ser menos da un efecto de que el sonido es más fuerte.],
)

= Armas

En este proyecto tenemos un sistema de doble arma, pudiendo intercambiar entre una y otra cuando queramos, siempre que estemos cerca del arma.

La idea del sistema de doble arma es que da mucha variedad al tipo de juego, una siendo a melé con mucho daño y otra a larga distancia más segura, aunque da igual la distancia, porque como ya hemos visto... de los grandes no te escapas.

#figura(
  image("img/img_armas.png"),
  "Imagen de las dos armas del proyecto"
)

== Daga

La daga tiene un sistema de combate cerrado, teniendo que acercarte mucho al enemigo para combatir, pero tiene sus beneficios:

- Daño muy alto: Tiene un daño superior al báculo, ideal para acabar rápido con los enemigos.
- Daño en área: Si ya era bueno hacer más daño además, ¡puedes golpear a varios enemigos a la vez!

Es un sistema de combate lineal, no cambia la jugabilidad conforme lo mejoras, es la opción que no falla.

== Báculo

A diferencia de la daga, el báculo te permite mantener el control de la situación desde lejos, ralentizar a los enemigos con los golpes y mantenerte seguro, además de que es muy divertida de jugar:

- Daño único: No tiene funcionalidad de daño en área, al menos no fácilmente, con suerte podrás dar a dos enemigos a la vez.
- Mejora rentable: Este arma está pensada para disfrutar a nivel alto, conforme la mejoras aumenta el daño, velocidad y distancia del proyectil.

Comienza con un proyectil muy lento, casi como dejar una trampa en el suelo, pero después de unas mejoras...

== Mejora de estadísticas

Al ser un sistema de doble arma, cada una tiene sus propias estadísticas que mejoran su funcionamiento, vamos a ver a continuación cuales son y como mejoran con el paso de los niveles, además incluiremos la mejora del personaje.

#tabla(
  caption: "Comparativa de estadísticas",
  columns: (auto, 1fr, 1fr, 1fr),
  align: (col, row) => if col == 0 { horizon } else { center + horizon },
  stroke: 0.5pt,
  fill: (col, row) => if row == 0 or col == 0 { rgb("#1a3a5c") } else if calc.odd(row) { rgb("#f0f4f8") } else { white },
  table.header(
    text(fill: white, weight: "bold")[],
    text(fill: white, weight: "bold")[Personaje],
    text(fill: white, weight: "bold")[Daga],
    text(fill: white, weight: "bold")[Báculo],
  ),
  text(fill: white, weight: "bold")[Nivel 1 \ Coste: 5],
    [Vida máxima: 100], [Daño: 25 \ Cooldown: 2.0s], [Daño: 15 \ Distancia: 10 \ Velocidad: 1.0 \ Cooldown: 2.0s],
  text(fill: white, weight: "bold")[Nivel 2 \ Coste: 6],
    [Vida máxima: 110], [Daño: 27 \ Cooldown: 1.9s], [Daño: 17 \ Distancia: 10.5 \ Velocidad: 1.4 \ Cooldown: 1.9s],
  text(fill: white, weight: "bold")[Nivel 5 \ Coste: 19],
    [Vida máxima: 140], [Daño: 33 \ Cooldown: 1.6s], [Daño: 23 \ Distancia: 12 \ Velocidad: 2.6 \ Cooldown: 1.6s],
  text(fill: white, weight: "bold")[Nivel 10 \ Coste: 103],
    [Vida máxima: 190], [Daño: 43 \ Cooldown: 1.1s], [Daño: 33 \ Distancia: 14.5 \ Velocidad: 4.6 \ Cooldown: 1.1s],
  text(fill: white, weight: "bold")[Nivel 15 \ Coste: 555],
    [Vida máxima: 240], [Daño: 53 \ Cooldown: 0.5s], [Daño: 43 \ Distancia: 17 \ Velocidad: 6.6 \ Cooldown: 0.5s],
  text(fill: white, weight: "bold")[Nivel 20 \ Coste: 2988],
    [Vida máxima: 290], [Daño: 63 \ Cooldown: 0.5s], [Daño: 53 \ Distancia: 19.5 \ Velocidad: 8.6 \ Cooldown: 0.5s],
)


= Recursos externos

El uso de recursos externos ha sido de infinita ayuda para el proyecto. El uso de Assets gratuitos de Unity Store y otras webs ha sido vital.

== Assets utilizados y atribuciones

#tabla(
  caption: "Assets externos utilizados en el proyecto",
  columns: (1fr, auto, auto, auto),
  align: (col, row) => if row == 0 { center + horizon } else { left + horizon },
  stroke: 0.5pt,
  fill: (col, row) => if row == 0 { rgb("#1a3a5c") } else if calc.odd(row) { rgb("#f0f4f8") } else { white },
  table.header(
    text(fill: white, weight: "bold")[Nombre del Asset],
    text(fill: white, weight: "bold")[Autor],
    text(fill: white, weight: "bold")[Fuente],
    text(fill: white, weight: "bold")[Licencia],
  ),
  [Stylized Cementery Pack], [Valentine Kurakin], [Unity Asset Store], [Gratuito],
  [Stylized Low Poly Skeleton], [SazenGames], [Unity Asset Store], [Gratuito],
  [UJoystick], [Lovatto Studio], [Unity Asset Store], [Gratuito],
  [Fatality FPS Gaming Font], [Fontier], [Unity Asset Store], [Gratuito],
  [3D Items - Free Wand Pack], [Frost Forged], [Unity Asset Store], [Gratuito],
  [Lowpoly Environment - Nature Free - MEDIEVAL FANTASY SERIES], [Polytope Studio], [Unity Asset Store], [Gratuito],
  [Magic Effects FREE], [Hovl Studio], [Unity Asset Store], [Gratuito],
  [Red ruby dagger], [Gavalatorx], [Unity Asset Store], [Gratuito],
  [Simple Input System], [yasirkulam], [Unity Asset Store], [Gratuito],
  [Magic Effects FREE], [Hovl Studio], [Unity Asset Store], [Gratuito],
)



= Bugs y soluciones

Antes recalcar que estas pruebas y el desarrollo completo ha sido testeado únicamente en Android y Windows 11.

== SceneAdditive

- El SceneAdditive me ocasionó un problema con el MusicManager, ya que solamente puede haber uno por escena, y al hacer aditiva la escena de GameOver y tener su propio MusicManager además del existente de la escena Juego me detectaba un error, el cual era que existían dos instancias de un objeto que debe ser único.

Por lo que tuve que borrar el MusicManager de la escena GameOver, ya que reutilizaba el de la escena Juego.

- Además la escena GameOver dependía de que la escena Juego se hubiese ejecutado previamente, sino no tendría los elementos funcionales cuando los requería.

Así que solo podía hacer pruebas basándome en el flujo natural de juego, no solo en esa escena.

== RaycastHit

- Este elemento es una linea recta que es la encargada de hacer el contacto con el suelo, antes que el mismo collider del personaje, el problema que me ocasionó fue que desde bordes  o cuestas empinadas el collider no permitía al personaje caer y el raycastHit se quedaba flotando, anulando cualquier posibilidad de moverse.

Para solucionarlo cambié el raycastHit que era una simple línea por un SphereCollider, que tiene una forma redondeada que evitaba que el collider del personaje tocase ningún elemento, y en una situación como la anterior simplemente se deslizaba hacia los lados.

- Para este mismo problema en la situación de colinas me seguía dando el mismo tipo de error.

Para ello decidí eliminar cualquier tipo de colina, dejando un espacio simple y efectivo para jugar.

== Límites de juego

- El tema de los límites por los que te puedes mover no me gustaba como era, eran unos muros invisibles que no te dejaban pasar, poco realistas y muy forzados.

Así que decidí tematizarlo a lo grande, con unas grandes verjas de cementerio, poco realistas, pero eran el efecto de magnitud y fantasía que quería lograr, más creíble que el otro.

== Obstáculos y colliders

- Algunos elementos de los assets de temática de cementerio como las baldosas y piedras que forman caminos tanto al personaje como a los enemigos.

A las baldosas y piedras les quité los colliders, ya que las baldosas hacían que mi personaje dejase de moverse, no tenían funcionalidad. Además las piedras del camino eran colliders muy pequeños pero que hacían que el personaje diese saltitos y persieses el control de su movimiento en momentos vitales.

- También obstaculizaban los elementos derruidos, más a los enemigos que al personaje.

Para el personaje conseguí que pudiese sobrepasarlos sin mucha dificultad, haciendo que quedase realista y bonito, pero para los enemigos tuve que eliminarles el NavMeshObstacle a los elementos, la solución que le dí al personaje no sirvió tanto en los enemigos.

- El gran arco de piedra que marca la entrada al cementerio también me dio problemas, el collider que tenía no dejaba atravesarla a través.

La única solución viable fue quitarle el collider.

== Cambio de armas

- Con el cambio de armas al cambiar el tag de 'CurrentWeapon' a 'Weapon' al soltar un arma de vez en cuando detectaba el arma que acababas de soltar como el arma que querías coger, por lo que no cambiaba de arma, simplemente movía de sitio el arma que querías coger.

La solución fue quitarle el collider que detectaba que estabas cerca de un arma durante los siguientes 0.2 segundos después de soltarla, así no se podría agarrar un arma sin collider que detectar, y por si acaso tampoco le asignaría el tag 'Weapon' hasta pasados esos 0.2 segundos.

== Orientación enemigos

- En la primera implementación de los enemigos al proyecto estos siempre se orientaban hacia mi personaje, caminando marcha atrás al alejarse, esto era horrible.

La solución que le di fue que mirasen hacia la dirección que caminaban siempre.

- Esta solución dio a otro fallo, cuando estaban dentro de la distancia de detención no se movían, por lo que la rotación no se actualizaba, y si girabas alrededor de ellos sin salir del area de detención ellos se quedaban atacando al punto en el que el personaje estaba cuando lo alcanzó, que puede ser perfectamente el lado contrario.

La solución fue añadirle la función de rotar constantemente hacia el personaje si está dentro de la distancia de detención.

- Nuevamente dio nuevos problemas, en medio de los ataques también rotaba, siendo el ataque un Stab, siempre te daba y era imposible ganar.

La solución fue añadir el booleano `puedeMover` que permitía rotar o caminar al enemigo o negárselo, hasta que hubiese acabado la animación que se lo negaba.

== Golpear con la daga

- Si la daga tenía un collider de acuerdo con su tamaño tenías que estar literalmente tocando al enemigo para poder darle, muy complicado y muy tosco para un videojuego.

Por lo que le aumente bastante el tamaño del collider, lo suficiente para que fuese más dinámico sin pasarse de facilidad y poco realismo.


= Conclusión

== Aprendizajes

Con este proyecto he aprendido muchísimo y he hecho algo que me hacía verdadera ilusión, mi propio videojuego

De no entender para nada el entorno de Unity y asustarme de verlo a sentirme cómodo en él y saber usar sus ventajas con fluidez para el desarrollo de todas las partes del proyecto.

No solo he mejorado enormemente en el uso de la herramienta, sino también en la programación, entender la lógica de un videojuego. 

He pasado de asustarme con los vectores y las fuerzas, sin saber cómo o donde crear un script a entender las bases de como funcionan, como obtienen lo que necesitan para funcionar, como acceder a otros objetos.

E incluso hasta a detectar algunas mejoras de optimización a nivel de código y estructura que podría haber utilizado, pero que por falta de tiempo no he podido llevar a cabo.

== Conclusión

Me ha fascinado el desarrollo de este proyecto, aunque también me han causado algunos días de mucho trabajo por finalizarlo lo mejor posible, con la estética que quería.

He descubierto la dificultad de hacer un videojuego bien hecho, el esfuerzo que lleva. Tenía las expectativas altísimas, un juego dinámico, con muchas animaciones, combos de ataque, ataques desde el aire, enemigos saltando por los aires.

Todo eran fantasías de un inexperto, este desarrollo me ha puesto los pies en la tierra y he descubierto otro apartado de la informática y la programación que me ha gustado mucho.

= Bibliografía

#show link: it => underline(text(fill: rgb("#2c5f8a"), it))

== Documentación oficial

Unity Technologies. (2024). *Unity Documentation*. Unity.
#link("https://docs.unity3d.com")

Unity Technologies. (2024). *Unity Scripting API*. Unity.
#link("https://docs.unity3d.com/ScriptReference")

Microsoft. (2024). *C\# Documentation*. Microsoft Learn.
#link("https://learn.microsoft.com/es-es/dotnet/csharp")

== Recursos utilizados

Adobe. (2024). *Mixamo — Animaciones y personajes 3D*. Adobe Inc.
#link("https://www.mixamo.com")

Unity Technologies. (2024). *Unity Asset Store*. Unity.
#link("https://assetstore.unity.com")

Orange Free Sounds. (2024). *Orange Free Sounds — Efectos de sonido gratuitos*.
#link("https://orangefreesounds.com")

FreeSound. (2024). *Freesound — Biblioteca de sonidos colaborativa*.
#link("https://freesound.org")
