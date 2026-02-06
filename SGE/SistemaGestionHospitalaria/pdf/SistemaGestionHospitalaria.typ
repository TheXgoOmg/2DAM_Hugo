#import "plantilla.typ": proyecto

#show: proyecto.with(
  titulo: "Sistema de Gestión Hospitalaria",
  asignatura: "Sistemas de Gestión Empresarial y Digitalización",
  imagen: image("img/odoo.png")
)


= Módulo SGE

== Altas y bajas de pacientes

#figure(
  image("img/img4.png", width: 80%),
  caption: [Prueba 1 de altas y bajas de pacientes]
)

#figure(
  image("img/img5.png", width: 80%),
  caption: [Prueba 2 de altas y bajas de pacientes]
)

== Altas y bajas de empleados

#figure(
  image("img/img6.png", width: 80%),
  caption: [Prueba 1 de altas y bajas de empleados]
)

#figure(
  image("img/img7.png", width: 80%),
  caption: [Prueba 2 de altas y bajas de empleados]
)

== Registro de atención sanitaria

#figure(
  image("img/img3.png", width: 80%),
  caption: [Prueba de registro de atención sanitaria]
)

== Registro de pruebas médicas diversas

#figure(
  image("img/img1.png", width: 80%),
  caption: [Prueba 1 de registro de diversas pruebas médicas]
)

#figure(
  image("img/img2.png", width: 80%),
  caption: [Prueba 2 de registro de diversas pruebas médicas]
)

#pagebreak()

= Módulo DIG

== Informe de cumplimiento normativo

#set enum(indent: 10pt, body-indent: 5pt)
#set par(justify: true)
#let seccion(titulo) = text(size: 1.3em, weight: "semibold", titulo)
#let subseccion(titulo) = text(size: 1.2em, weight: "bold", titulo)
#show line: it => {
  v(2em)
  it
  v(2em)
}

#seccion[2.1.1 Pilares de la Regulación Comunitaria (nivel UE)]

+ #subseccion[Protección de Datos y Privacidad: RGPD 2016/679]

  Es el estándar de referencia. Su aplicación en un hospital es de máximo rigor debido a que los datos clínicos son considerados *categorías especiales de datos* (Art. 9).
  - *Principio de Responsabilidad Proactiva:* El hospital debe ser capaz de demostrar que cumple con la norma, no solo cumplirla de forma pasiva.
  - *Gestión de Riesgos:* Es obligatoria la realización de una *Evaluación de Impacto en la Protección de Datos (EIPD)* antes de la implementación del SGH, identificando posibles brechas en el flujo de información del paciente.

  - *Derechos del Interesado:* El sistema debe facilitar técnicamente el ejercicio de derechos como la portabilidad de la historia clínica entre centros sanitarios.


+ #subseccion[Resiliencia y Ciberseguridad: Directiva NIS2 2022/2555]

  Refuerza la seguridad de las infraestructuras críticas. Los hospitales son clasificados como *entidades esenciales*.

  - *Continuidad del Servicio:* La digitalización exige que el SGH tenga protocolos de recuperación ante desastres. Un hospital no puede permitirse un "tiempo de inactividad" que afecte a la asistencia vital.

  - *Seguridad en la Cadena de Suministro:* El hospital es legalmente responsable de asegurar que el proveedor del software (SGH) cumpla con estándares de seguridad rigurosos.

  - *Obligación de Notificación:* Establece canales formales para informar sobre incidentes de ciberseguridad a las autoridades nacionales (como el INCIBE en España) en tiempos definidos.


+ #subseccion[Calidad y Seguridad Técnica: Reglamento MDR 2017/745]

  El *Reglamento sobre Productos Sanitarios (Medical Device Regulation)* aplica directamente al software de gestión si este realiza funciones más allá del mero almacenamiento.

  - *Software como Dispositivo Médico (SaMD):* Si el SGH incorpora algoritmos para el triaje, diagnóstico o cálculo de medicación, debe pasar por un proceso de certificación para obtener el *Marcado CE*.

  - *Vigilancia Post-comercialización:* Obliga a los desarrolladores a realizar un seguimiento del rendimiento del software para detectar errores que puedan comprometer la salud del paciente.

+ #subseccion[Inteligencia Artificial: Reglamento 2024/1689]

  Es el marco legal que regula el uso de la IA en Europa. En entornos críticos como el sanitario, garantiza que la tecnología sea segura, ética y no genere discriminación.

  - *Clasificación por Riesgo:* Clasifica los sistemas según su peligro; las herramientas de diagnóstico médico se consideran de *alto riesgo* y deben superar controles estrictos.
  - *Calidad de los Datos:* Exige que la IA se entrene con datos representativos y sin sesgos para evitar diagnósticos erróneos o injustos según el perfil del paciente.
  - *Supervisión Humana:* El sistema debe permitir que los profesionales sanitarios supervisen y comprendan las decisiones de la IA, manteniendo siempre la responsabilidad clínica.
  - *Transparencia y Registro:* Es obligatorio informar sobre el uso de la IA y mantener una documentación técnica (logs) que permita rastrear el comportamiento del sistema.


  #v(2em)

#seccion[2.1.2 Marco Legal Nacional (España)]

+ #subseccion[Protección de Datos y Garantía de Derechos Digitales: LOPDGDD 3/2018]

  Adapta el RGPD al sistema jurídico español, detallando las responsabilidades de los profesionales y técnicos que operan el sistema.

  - *Deber de Confidencialidad:* Refuerza el secreto profesional estricto para cualquier persona que, por sus funciones, tenga acceso a la información contenida en el SGH.
  
  - *Delegado de Protección de Datos (DPD):* Es obligatoria la designación de un supervisor que vele por la integridad del flujo de datos dentro del hospital.

+ #subseccion[Autonomía del Paciente y Documentación Clínica: Ley 41/2002]

  Regula la gestión de la historia clínica digital, estableciendo los estándares de propiedad y custodia de la información.

  - *Conservación de la Información:* El sistema debe garantizar la custodia de los expedientes clínicos durante un mínimo legal de cinco años, asegurando su recuperación inmediata.
  
  - *Trazabilidad de Accesos:* Obliga a que el SGH registre de forma inalterable quién accede a cada dato clínico, cuándo y con qué justificación.

+ #subseccion[Esquema Nacional de Seguridad (ENS): Real Decreto 311/2022]

  Define las medidas de seguridad técnicas necesarias para los sistemas que gestionan servicios públicos o datos sensibles.

  - *Categoría de Seguridad Alta:* Debido a la sensibilidad de los datos de salud, el SGH debe cumplir con un catálogo reforzado de medidas de cifrado, auditoría y control perimetral.
  
  - *Certificación de Conformidad:* El software y su infraestructura deben estar auditados para garantizar que el sistema es resiliente frente a ciberamenazas modernas.

+ #subseccion[Interoperabilidad Regional: Real Decreto 4/2010]

  En la Comunidad Valenciana, el cumplimiento normativo operativo pasa por la integración con los servicios centrales de la Conselleria de Sanitat.

  - *Receta Electrónica:* El SGH debe cumplir con los protocolos técnicos de la Generalitat para la prescripción farmacéutica digital y su posterior dispensación.
  
  - *Seguridad TIC (CSIRT-CV):* Todo software de gestión que se conecte a la red sanitaria valenciana debe seguir las guías de seguridad dictadas por el Centro de Seguridad TIC de la Comunitat Valenciana.



#pagebreak()

#seccion[2.1.3 Marco Legal Local (Comunidad Valenciana)]

+ #subseccion[Ordenación de la Salud: Ley 10/2014 de la Generalitat]

  La *Ley de Salud de la Comunitat Valenciana* establece el marco para la historia clínica compartida y la integración de sistemas digitales en el territorio regional.

  - *Continuidad Asistencial:* El sistema debe permitir el flujo de datos entre atención primaria y especializada dentro del ecosistema de la red pública valenciana.
  
  - *Identidad Digital:* Obliga a que el SGH sea compatible con los sistemas de identificación ciudadana y profesional de la Generalitat para el acceso a carpetas de salud.

+ #subseccion[Regulación de la Historia Clínica: Ley 1/2003]

  Este decreto regula la utilización de la historia clínica en la Comunidad Valenciana, detallando los requisitos técnicos para los soportes informáticos.

  - *Identificación y Firma:* Exige que el SGH garantice la identificación inequívoca del personal sanitario mediante sistemas de firma electrónica homologados por la Generalitat.
  
  - *Calidad Documental:* El sistema debe seguir las directrices de los comités de documentación clínica locales para asegurar que la digitalización no degrada la validez legal de las pruebas médicas.

+ #subseccion[Protección de Datos en el DOGV: Instrucciones de la Conselleria]

  A través del *Diari Oficial de la Generalitat Valenciana (DOGV)*, se publican instrucciones específicas sobre el uso de tecnologías de la información en el ámbito sanitario.

  - *Acceso Remoto y Movilidad:* Regula las condiciones de seguridad que debe cumplir el SGH cuando se accede a él desde fuera de la red física del hospital, exigiendo canales cifrados y auditorías de acceso locales.





#pagebreak()


== Informe de viabilidad del uso de apps cloud

  La infraestructura tecnológica en sanidad es crítica: un fallo no solo es un problema técnico, sino un riesgo para la vida humana. Por ello, cualquier solución cloud debe garantizar niveles máximos de *seguridad*, *disponibilidad* y *cumplimiento*.


+ #subseccion[Seguridad]

  Es el pilar fundamental debido a la extrema sensibilidad de los datos gestionados.

  - *Protección de datos:* Los historiales y diagnósticos requieren confidencialidad absoluta; cualquier brecha implica graves sanciones legales y pérdida de confianza.
  - *Control de acceso:* Es obligatorio el cifrado de datos y el acceso restringido por roles, asegurando que cada profesional vea solo lo estrictamente necesario.

+ #subseccion[Disponibilidad]

  Los sistemas hospitalarios no pueden permitirse tiempos de inactividad.

  - *Continuidad asistencial:* Servicios como urgencias o historiales clínicos (HIS) deben operar 24/7 sin interrupciones.
  - *Riesgo operativo:* Una caída del sistema bloquea diagnósticos y tratamientos, comprometiendo directamente la seguridad del paciente.

+ #subseccion[Cumplimiento normativo]

  La infraestructura debe seguir estrictamente el marco legal vigente.

  - *RGPD:* Exige trazabilidad total de los accesos y protección de datos desde el diseño técnico.
  - *Leyes sanitarias:* La normativa obliga a cumplir con auditorías periódicas y, en muchos casos, a requisitos específicos sobre la ubicación física de los servidores.


#pagebreak()

== Viabilidad del uso de Cloud en entornos hospitalarios

  La adopción de la nube en sanidad mejora la eficiencia y la escalabilidad, pero exige cautela. Su viabilidad depende de garantizar que la migración no comprometa la seguridad de los datos clínicos ni la continuidad del servicio médico.

+ #subseccion[Requisitos mínimos para el uso de cloud en sanidad]

  - *Seguridad y Control:* El proveedor debe garantizar cifrado y protección ante ataques, pero el hospital mantiene la responsabilidad de supervisar los accesos y la ubicación de los datos.
  - *Cumplimiento Legal:* Es obligatorio que la infraestructura cumpla con el RGPD y que los centros de datos estén en regiones permitidas, respaldado por certificaciones oficiales.

+ #subseccion[Modelos de infraestructura aplicables]

  - *On-premise:* Servidores locales. Máximo control y privacidad, pero con costes de mantenimiento muy altos y nula escalabilidad.
  - *Cloud pública:* Servicios externos (como AWS o Azure). Económica y flexible, pero requiere una configuración de seguridad muy estricta para cumplir la ley.
  - *Cloud privada:* Uso exclusivo para el hospital. Equilibra el control del entorno local con las ventajas tecnológicas de la nube, aunque a mayor coste.
  - *Modelo híbrido:* La opción más equilibrada. Mantiene los sistemas críticos en local (on-premise) y utiliza la nube para servicios de apoyo o almacenamiento masivo.

---

  En conclusión, el cloud es viable si se elige un modelo que priorice la seguridad técnica y el cumplimiento legal sobre el ahorro de costes.


#pagebreak()

== Análisis y comparación de modelos de infraestructura

#seccion[Infraestructura On-Premise]

    La infraestructura on-premise se basa en servidores y sistemas instalados físicamente en el propio hospital. Este modelo ha sido tradicionalmente el más utilizado en el sector sanitario, ya que ofrece un control total sobre la infraestructura y los datos clínicos.

    - *Costes iniciales y recurrentes:* Requiere una elevada inversión inicial en hardware, licencias y espacios físicos. Además, genera costes recurrentes de mantenimiento, consumo energético, personal técnico y renovación de equipos.

    - *Escalabilidad:* La capacidad de crecimiento es limitada, ya que aumentar recursos implica adquirir e instalar nuevo hardware, lo que dificulta la respuesta rápida ante picos de demanda.

    - *Seguridad:* Ofrece un alto nivel de control sobre la seguridad, aunque depende en gran medida de los recursos y conocimientos del equipo técnico interno.

    - *Disponibilidad:* Para garantizar una alta disponibilidad es necesario invertir en sistemas redundantes y planes de recuperación, ya que una avería puede afectar gravemente al servicio.

    - *Mantenimiento:* El hospital asume toda la gestión de actualizaciones, parches y resolución de incidencias, lo que supone una carga operativa elevada.

    - *Flexibilidad:* Presenta poca flexibilidad, ya que los cambios en la infraestructura suelen ser lentos y costosos.

    - *Adecuación al entorno hospitalario:* Es adecuado para centros que necesitan un control absoluto de los datos, aunque su coste y menor agilidad lo hacen menos eficiente frente a modelos cloud.


#v(1.5em)


#seccion[Cloud pública]

    La cloud pública utiliza infraestructuras compartidas ofrecidas por proveedores externos como AWS, Azure o Google Cloud. Este modelo permite desplegar aplicaciones hospitalarias sin necesidad de gestionar servidores propios, accediendo a los recursos a través de Internet.

    - *Costes iniciales y recurrentes:* Presenta bajos costes iniciales, ya que no requiere inversión en hardware. El gasto se basa en un modelo de pago por uso, lo que permite ajustar los costes a la demanda real.

    - *Escalabilidad:* Permite escalar los recursos de forma rápida y flexible, adaptándose fácilmente a picos de demanda en situaciones excepcionales o periodos de alta actividad.

    - *Seguridad:* Los proveedores ofrecen medidas de seguridad avanzadas, aunque es necesario que el hospital configure correctamente los accesos y la protección de los datos.

    - *Disponibilidad:* Ofrece altos niveles de disponibilidad gracias a infraestructuras redundantes y distribuidas geográficamente.

    - *Mantenimiento:* El mantenimiento de la infraestructura física recae en el proveedor, reduciendo la carga técnica del hospital.

    - *Flexibilidad:* Facilita la incorporación de nuevos servicios y tecnologías de forma ágil.

    - *Adecuación al entorno hospitalario:* Es adecuada para servicios no críticos o con alta demanda de recursos, siempre que se garantice el cumplimiento normativo y la protección de los datos clínicos.


#seccion[Cloud privada]

    La cloud privada utiliza una infraestructura en la nube de uso exclusivo para una organización sanitaria, ya sea gestionada internamente o por un proveedor externo, sin compartir recursos con otras entidades.

    - *Costes iniciales y recurrentes:* Requiere menos inversión inicial que el modelo on-premise, aunque presenta costes recurrentes superiores a la cloud pública debido al uso de recursos dedicados.

    - *Escalabilidad:* Permite escalar los recursos con mayor facilidad que en entornos tradicionales, aunque con más limitaciones que la cloud pública.

    - *Seguridad:* Ofrece un alto nivel de seguridad y control sobre los datos, al tratarse de un entorno exclusivo para el hospital.

    - *Disponibilidad:* Puede garantizar una alta disponibilidad mediante infraestructuras redundantes y planes de recuperación ante fallos.

    - *Mantenimiento:* El mantenimiento puede delegarse en un proveedor, reduciendo la carga técnica interna respecto al modelo on-premise.

    - *Flexibilidad:* Facilita la adaptación a nuevas aplicaciones y cambios tecnológicos de forma más ágil.

    - *Adecuación al entorno hospitalario:* Es adecuada para hospitales que necesitan un elevado control de los datos y cumplir estrictamente la normativa sanitaria.


#seccion[Modelo híbrido]

    El modelo híbrido combina infraestructuras on-premise y cloud, permitiendo al hospital decidir qué sistemas se alojan en servidores propios y cuáles se despliegan en la nube, en función de su criticidad y requisitos de seguridad.

    - *Costes iniciales y recurrentes:* Permite reducir los costes iniciales al aprovechar recursos cloud, manteniendo únicamente en on-premise los sistemas críticos. Los costes recurrentes se optimizan al usar la nube solo cuando es necesario.

    - *Escalabilidad:* Ofrece una alta capacidad de escalado, ya que los recursos cloud pueden ampliarse rápidamente para absorber picos de demanda sin necesidad de adquirir nuevo hardware.

    - *Seguridad:* Facilita un alto nivel de seguridad al permitir que los datos más sensibles permanezcan en infraestructuras propias, mientras se aplican medidas avanzadas de seguridad en la nube.

    - *Disponibilidad:* Mejora la disponibilidad del sistema al combinar infraestructuras redundantes locales y cloud, reduciendo el impacto de posibles fallos.

    - *Mantenimiento:* Reduce la carga de mantenimiento del hospital, ya que parte de la infraestructura es gestionada por el proveedor cloud.

    - *Flexibilidad:* Ofrece una gran flexibilidad, permitiendo adaptar la infraestructura a nuevas necesidades tecnológicas y asistenciales.

    - *Adecuación al entorno hospitalario:* Es uno de los modelos más adecuados para entornos hospitalarios, ya que equilibra control, seguridad, cumplimiento normativo y aprovechamiento de las ventajas del cloud.


#pagebreak()

#seccion[Resumen]

#table(
  columns: 5,

  [*Criterio*], [*On-Premise*], [*Cloud pública*], [*Cloud privada*], [*Modelo híbrido*],

  [Costes iniciales y recurrentes],
  [Altos costes iniciales y de mantenimiento],
  [Bajos costes iniciales, pago por uso],
  [Costes iniciales moderados y recurrentes más elevados],
  [Costes optimizados combinando ambos modelos],

  [Escalabilidad],
  [Limitada, requiere compra de hardware],
  [Muy alta, escalado rápido y flexible],
  [Media-alta, con ciertas limitaciones],
  [Alta, uso de cloud para picos de demanda],

  [Seguridad],
  [Alto control, depende del equipo técnico interno],
  [Seguridad avanzada del proveedor, requiere buena configuración],
  [Muy alta, entorno exclusivo y mayor control],
  [Muy alta, datos críticos en infraestructuras propias],

  [Disponibilidad],
  [Alta solo con inversiones adicionales],
  [Muy alta gracias a infraestructuras distribuidas],
  [Alta mediante sistemas redundantes],
  [Muy alta al combinar entornos locales y cloud],

  [Mantenimiento],
  [Totalmente a cargo del hospital],
  [Gestionado por el proveedor cloud],
  [Compartido entre hospital y proveedor],
  [Parcialmente delegado al proveedor cloud],

  [Flexibilidad],
  [Baja, cambios lentos y costosos],
  [Muy alta, rápida adopción de nuevos servicios],
  [Alta, más ágil que on-premise],
  [Muy alta, adaptación continua a nuevas necesidades],

  [Adecuación al entorno hospitalario],
  [Adecuado para control total, pero poco eficiente],
  [Adecuado para servicios no críticos],
  [Muy adecuado para sistemas sensibles],
  [El modelo más adecuado por equilibrio y versatilidad],
)


#seccion[Conclusión]

La opción más indicada para un entorno hospitalario es el modelo híbrido.

Este modelo permite mantener los sistemas más críticos y los datos clínicos sensibles en infraestructuras propias, garantizando un alto nivel de control, seguridad y cumplimiento normativo, con una vigilancia activa por parte de los trabajadores, mientras que aprovecha las ventajas del cloud para servicios menos críticos, escalado de recursos y mejora de la disponibilidad.

Además, el modelo híbrido ofrece mayor flexibilidad y escalabilidad, reduce costes frente a un entorno completamente on-premise y permite una mejor continuidad de la actividad, que es vital en un hospital donde la disponibilidad y la protección de la información son prioritarias.


#pagebreak()

== Viabilidad del uso de la IA en entornos hospitalarios


