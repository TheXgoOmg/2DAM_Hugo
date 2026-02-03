#import "plantilla.typ": proyecto

#show: proyecto.with(
  titulo: "Sistema de Gestión Hospitalaria",
  asignatura: "Sistemas de Gestión Empresarial y Digitalización",
  imagen: "img/odoo.png",
)


= Módulo SGE

#figure(
  image("img/img1.png", width: 80%),
  caption: [Prueba 1 de registro de diversas pruebas médicas]
)

#figure(
  image("img/img2.png", width: 80%),
  caption: [Prueba 2 de registro de diversas pruebas médicas]
)

#figure(
  image("img/img3.png", width: 80%),
  caption: [Prueba de registro de atención sanitaria]
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

+ #subseccion[Protección de Datos y Privacidad: RGPD]

  El *Reglamento (UE) 2016/679 (RGPD)* es el estándar de referencia. Su aplicación en un hospital es de máximo rigor debido a que los datos clínicos son considerados *categorías especiales de datos* (Art. 9).
  - *Principio de Responsabilidad Proactiva:* El hospital debe ser capaz de demostrar que cumple con la norma, no solo cumplirla de forma pasiva.
  - *Gestión de Riesgos:* Es obligatoria la realización de una *Evaluación de Impacto en la Protección de Datos (EIPD)* antes de la implementación del SGH, identificando posibles brechas en el flujo de información del paciente.

  - *Derechos del Interesado:* El sistema debe facilitar técnicamente el ejercicio de derechos como la portabilidad de la historia clínica entre centros sanitarios.


+ #subseccion[Resiliencia y Ciberseguridad: Directiva NIS2]

  La *Directiva (UE) 2022/2555 (NIS2)* refuerza la seguridad de las infraestructuras críticas. Los hospitales son clasificados como *entidades esenciales*.

  - *Continuidad del Servicio:* La digitalización exige que el SGH tenga protocolos de recuperación ante desastres. Un hospital no puede permitirse un "tiempo de inactividad" que afecte a la asistencia vital.

  - *Seguridad en la Cadena de Suministro:* El hospital es legalmente responsable de asegurar que el proveedor del software (SGH) cumpla con estándares de seguridad rigurosos.

  - *Obligación de Notificación:* Establece canales formales para informar sobre incidentes de ciberseguridad a las autoridades nacionales (como el INCIBE en España) en tiempos definidos.


+ #subseccion[Calidad y Seguridad Técnica: Reglamento MDR]

  El *Reglamento (UE) 2017/745 sobre Productos Sanitarios (Medical Device Regulation)* aplica directamente al software de gestión si este realiza funciones más allá del mero almacenamiento.

  - *Software como Dispositivo Médico (SaMD):* Si el SGH incorpora algoritmos para el triaje, diagnóstico o cálculo de medicación, debe pasar por un proceso de certificación para obtener el *Marcado CE*.

  - *Vigilancia Post-comercialización:* Obliga a los desarrolladores a realizar un seguimiento del rendimiento del software para detectar errores que puedan comprometer la salud del paciente.

#pagebreak()

#seccion[2.1.2 Marco Legal Nacional (España)]

+ #subseccion[Protección de Datos y Garantía de Derechos Digitales: LOPDGDD 3/2018]

  Adapta el RGPD al sistema jurídico español, detallando las responsabilidades de los profesionales y técnicos que operan el sistema.

  - *Deber de Confidencialidad:* Refuerza el secreto profesional estricto para cualquier persona que, por sus funciones, tenga acceso a la información contenida en el SGH.
  
  - *Delegado de Protección de Datos (DPD):* Es obligatoria la designación de un supervisor que vele por la integridad del flujo de datos dentro del hospital.

+ #subseccion[Autonomía del Paciente y Documentación Clínica: Ley 41/2002]

  Regula la gestión de la historia clínica digital, estableciendo los estándares de propiedad y custodia de la información.

  - *Conservación de la Información:* El sistema debe garantizar la custodia de los expedientes clínicos durante un mínimo legal de cinco años, asegurando su recuperación inmediata.
  
  - *Trazabilidad de Accesos:* Obliga a que el SGH registre de forma inalterable quién accede a cada dato clínico, cuándo y con qué justificación.

+ #subseccion[Seguridad en las Administraciones Públicas: Esquema Nacional de Seguridad (ENS)]

  El *Real Decreto 311/2022* define las medidas de seguridad técnicas necesarias para los sistemas que gestionan servicios públicos o datos sensibles.

  - *Categoría de Seguridad Alta:* Debido a la sensibilidad de los datos de salud, el SGH debe cumplir con un catálogo reforzado de medidas de cifrado, auditoría y control perimetral.
  
  - *Certificación de Conformidad:* El software y su infraestructura deben estar auditados para garantizar que el sistema es resiliente frente a ciberamenazas modernas.

#pagebreak()

#seccion[2.1.3 Marco Legal Local (Comunidad Valenciana)]

+ #subseccion[Ordenación de la Salud: Ley 10/2014 de la Generalitat]

  La *Ley de Salud de la Comunitat Valenciana* establece el marco para la historia clínica compartida y la integración de sistemas digitales en el territorio regional.

  - *Continuidad Asistencial:* El sistema debe permitir el flujo de datos entre atención primaria y especializada dentro del ecosistema de la red pública valenciana.
  
  - *Identidad Digital:* Obliga a que el SGH sea compatible con los sistemas de identificación ciudadana y profesional de la Generalitat para el acceso a carpetas de salud.

+ #subseccion[Regulación de la Historia Clínica: Decreto 137/2003]

  Este decreto regula la utilización de la historia clínica en la Comunidad Valenciana, detallando los requisitos técnicos para los soportes informáticos.

  - *Identificación y Firma:* Exige que el SGH garantice la identificación inequívoca del personal sanitario mediante sistemas de firma electrónica homologados por la Generalitat.
  
  - *Calidad Documental:* El sistema debe seguir las directrices de los comités de documentación clínica locales para asegurar que la digitalización no degrada la validez legal de las pruebas médicas.

+ #subseccion[Interoperabilidad Regional: Ecosistema Abucasis]

  En la Comunidad Valenciana, el cumplimiento normativo operativo pasa por la integración con los servicios centrales de la Conselleria de Sanitat.

  - *Receta Electrónica:* El SGH debe cumplir con los protocolos técnicos de la Generalitat para la prescripción farmacéutica digital y su posterior dispensación.
  
  - *Seguridad TIC (CSIRT-CV):* Todo software de gestión que se conecte a la red sanitaria valenciana debe seguir las guías de seguridad dictadas por el Centro de Seguridad TIC de la Comunitat Valenciana.

+ #subseccion[Protección de Datos en el DOGV: Instrucciones de la Conselleria]

  A través del *Diari Oficial de la Generalitat Valenciana (DOGV)*, se publican instrucciones específicas sobre el uso de tecnologías de la información en el ámbito sanitario.

  - *Acceso Remoto y Movilidad:* Regula las condiciones de seguridad que debe cumplir el SGH cuando se accede a él desde fuera de la red física del hospital, exigiendo canales cifrados y auditorías de acceso locales.





#pagebreak()





== Informe de viabilidad del uso de apps cloud

En los entornos sanitarios, la infraestructura tecnológica desempeña un papel crítico, ya que soporta aplicaciones y sistemas directamente relacionados con la atención al paciente.

A diferencia de otros sectores, un fallo tecnológico en un hospital puede tener consecuencias graves, tanto a nivel operativo como legal y humano. Por ello, no cualquier modelo de infraestructura es válido, siendo imprescindible garantizar altos niveles de *seguridad*, *disponibilidad* y *cumplimiento normativo*.

+ #subseccion[Seguridad]

  La seguridad constituye uno de los pilares fundamentales de la infraestructura tecnológica en sanidad, debido al carácter altamente sensible de la información que se gestiona en los sistemas hospitalarios.

  - *Datos clínicos:* Los hospitales manejan datos clínicos y personales de los pacientes, como historiales médicos, diagnósticos, tratamientos o resultados de pruebas. Este tipo de información está considerada como *dato especialmente protegido*, por lo que debe garantizarse su confidencialidad, integridad y disponibilidad. Una brecha de seguridad puede derivar en sanciones legales y en una pérdida de confianza por parte de los pacientes.

  - *Accesos y cifrado:* Es imprescindible implementar mecanismos de control de acceso basados en roles profesionales, asegurando que cada usuario solo pueda acceder a la información necesaria para el desempeño de su función. Además, los datos deben estar protegidos mediante técnicas de cifrado tanto en reposo como en tránsito, evitando que puedan ser interpretados en caso de acceso no autorizado o interceptación.

+ #subseccion[Disponibilidad]

  La disponibilidad de los sistemas informáticos es un requisito esencial en el ámbito hospitalario, donde muchas aplicaciones son consideradas servicios críticos.

  - *Servicios críticos 24/7:* Sistemas como los Sistemas de Información Hospitalaria (HIS), los sistemas de imágenes médicas (PACS) o las plataformas de gestión de urgencias deben estar operativos de forma continua, las 24 horas del día y los 7 días de la semana. Cualquier interrupción del servicio puede afectar directamente a la calidad asistencial.

  - *Impacto real de una caída:* Una caída del sistema puede provocar retrasos en diagnósticos, imposibilidad de acceder a historiales médicos o errores en la gestión de pacientes. En situaciones críticas, como urgencias o intervenciones quirúrgicas, estos fallos pueden comprometer la seguridad del paciente, lo que hace imprescindible contar con infraestructuras altamente disponibles y tolerantes a fallos.

+ #subseccion[Cumplimiento normativo]

  Además de los aspectos técnicos, la infraestructura tecnológica en sanidad debe cumplir con un marco normativo estricto que regula el tratamiento y la protección de la información sanitaria.

  - *RGPD:* El Reglamento General de Protección de Datos (RGPD) establece obligaciones específicas para el tratamiento de datos personales, especialmente los relacionados con la salud. Las infraestructuras deben garantizar la confidencialidad, integridad y disponibilidad de los datos, así como permitir la trazabilidad de accesos y aplicar el principio de protección de datos desde el diseño y por defecto.

  - *Legislación sanitaria:* La normativa sanitaria nacional y autonómica regula la gestión de la información clínica y puede imponer requisitos adicionales, como la localización geográfica de los datos o la realización de auditorías de seguridad. Estas exigencias condicionan directamente la elección del modelo de infraestructura tecnológica a utilizar en un hospital.

#line(length: 100%)

== Viabilidad del uso de Cloud en entornos hospitalarios

  El uso de infraestructuras cloud en el ámbito sanitario ha experimentado un crecimiento significativo en los últimos años, impulsado por la necesidad de mejorar la eficiencia, la escalabilidad y la digitalización de los servicios de salud. No obstante, debido a la criticidad de los sistemas hospitalarios y a la sensibilidad de los datos gestionados, el despliegue de aplicaciones en la nube debe evaluarse cuidadosamente. En este contexto, el cloud es una opción viable, pero únicamente cuando se cumplen una serie de requisitos técnicos, organizativos y legales.

  La viabilidad del uso de cloud en hospitales depende de que la infraestructura elegida sea capaz de ofrecer niveles de seguridad, disponibilidad y control equivalentes o superiores a los modelos tradicionales, garantizando en todo momento la continuidad del servicio y la protección de la información clínica.

+ #subseccion[Requisitos mínimos para el uso de cloud en sanidad]

  Para que una aplicación hospitalaria pueda desplegarse en un entorno cloud de forma segura y conforme a la normativa, es imprescindible que se cumplan una serie de requisitos mínimos.

  - *Seguridad:* El proveedor cloud debe ofrecer mecanismos avanzados de seguridad, incluyendo cifrado de datos, control de accesos, monitorización continua y protección frente a ciberataques. Además, el hospital debe mantener la responsabilidad sobre la correcta configuración de estos mecanismos, evitando configuraciones inseguras que puedan derivar en brechas de seguridad.

  - *Control de datos:* Aunque la infraestructura esté externalizada, el hospital debe conservar el control sobre los datos clínicos. Esto implica conocer dónde se almacenan los datos, quién puede acceder a ellos y cómo se gestionan las copias de seguridad. Además, debe garantizarse la posibilidad de recuperar la información y migrarla a otro proveedor si fuera necesario, evitando situaciones de dependencia excesiva del proveedor cloud.

  - *Cumplimiento legal:* El uso de cloud debe ajustarse estrictamente al RGPD y a la legislación sanitaria vigente. El proveedor debe ofrecer garantías contractuales sobre el tratamiento de los datos, incluyendo acuerdos de encargo del tratamiento, certificaciones de seguridad y cumplimiento normativo. Además, es fundamental que los datos se alojen en centros de datos ubicados en regiones que cumplan con los requisitos legales establecidos.

+ #subseccion[Modelos de infraestructura aplicables al entorno hospitalario]

  Existen diferentes modelos de infraestructura tecnológica que pueden emplearse en el ámbito hospitalario, cada uno con características específicas en cuanto a control, costes y flexibilidad.

  - *Infraestructura on-premise:* En este modelo, los sistemas y aplicaciones se alojan en servidores propios del hospital. Ofrece un alto nivel de control sobre los datos y la infraestructura, pero implica elevados costes iniciales, menor escalabilidad y una mayor carga de mantenimiento.

  - *Cloud pública:* Las aplicaciones se despliegan en infraestructuras compartidas ofrecidas por proveedores como AWS, Azure o Google Cloud. Este modelo destaca por su escalabilidad, flexibilidad y reducción de costes iniciales, aunque requiere una correcta gestión de la seguridad y del cumplimiento normativo para garantizar la protección de los datos sanitarios.

  - *Cloud privada:* La infraestructura cloud es de uso exclusivo para una organización sanitaria, ya sea gestionada internamente o por un proveedor externo. Combina ventajas del cloud, como la flexibilidad, con un mayor control sobre la seguridad y los datos, aunque con costes superiores a la cloud pública.

  - *Modelo híbrido:* Este modelo combina infraestructuras on-premise y cloud, permitiendo alojar los sistemas más críticos o sensibles en entornos propios y utilizar la nube para servicios menos críticos o para escalado de recursos. Es una de las opciones más adoptadas en sanidad, ya que permite equilibrar seguridad, cumplimiento legal y flexibilidad.

---

  En conclusión, el uso del cloud en entornos hospitalarios es viable y cada vez más habitual, siempre que se adopte un enfoque controlado y se seleccione el modelo de infraestructura más adecuado en función de los requisitos técnicos y legales del hospital.

#line(length: 100%)

== Análisis y comparación de modelos de infraestructura

#seccion[Infraestructura On-Premise (servidores propios del hospital)]

    La infraestructura *on-premise* se basa en el uso de servidores y sistemas alojados físicamente en las instalaciones del propio hospital o en centros de datos de su propiedad. Este modelo ha sido tradicionalmente el más utilizado en el sector sanitario, ya que permite un control total sobre la infraestructura y los datos gestionados.

    - *Costes iniciales y recurrentes:* El modelo on-premise implica elevados costes iniciales, derivados de la adquisición de servidores, sistemas de almacenamiento, licencias de software y la adecuación de espacios físicos. Además, existen costes recurrentes asociados al mantenimiento del hardware, consumo energético, personal técnico especializado y renovación periódica de los equipos.

    - *Escalabilidad:* La escalabilidad es limitada, ya que aumentar la capacidad del sistema requiere la compra e instalación de nuevo hardware. Esto dificulta la adaptación rápida a incrementos de demanda, como picos de uso en situaciones de emergencia sanitaria.

    - *Seguridad:* Al tratarse de una infraestructura controlada directamente por el hospital, se dispone de un alto nivel de control sobre la seguridad física y lógica. No obstante, la seguridad depende en gran medida de los recursos y conocimientos del equipo técnico interno, lo que puede suponer un riesgo si no se aplican medidas actualizadas de ciberseguridad.

    - *Disponibilidad:* Garantizar una alta disponibilidad en entornos on-premise requiere inversiones adicionales en sistemas redundantes, copias de seguridad y planes de recuperación ante desastres. Sin estas medidas, una avería física puede provocar interrupciones prolongadas del servicio.

    - *Mantenimiento:* El mantenimiento recae íntegramente en el hospital, que debe encargarse de actualizaciones, parches de seguridad, monitorización y resolución de incidencias. Esto supone una carga operativa considerable y una dependencia directa del personal técnico interno.

    - *Flexibilidad:* La flexibilidad es reducida, ya que cualquier cambio en la infraestructura implica procesos lentos de adquisición e instalación. Esto dificulta la adaptación a nuevas aplicaciones o a cambios tecnológicos rápidos.

    - *Adecuación al entorno hospitalario:* El modelo on-premise se adapta bien a entornos hospitalarios que requieren un control absoluto de los datos y cumplen estrictamente con la normativa vigente. Sin embargo, su elevado coste, menor escalabilidad y complejidad de mantenimiento hacen que, en muchos casos, resulte menos eficiente frente a modelos más modernos.

