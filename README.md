# ProyectoFinal

## Análisis de Proceso de Proyectos DPO

**Gabriel Padilla, Juan Ernesto Pinto & María Calle**

### Introducción
Tras varias entregas de distintos proyectos a lo largo del semestre, hemos aprendido mucho sobre el proceso de diseño, análisis e implementación de software. En el primer proyecto, teníamos un entendimiento muy elemental del diseño; principalmente nos preocupamos por el cumplimiento de los requerimientos funcionales únicamente, sin considerar aspectos como la mantenibilidad, escalabilidad y facilidad de editar nuestro código. Con el tiempo y la experiencia adquirida, hemos priorizado estos aspectos, facilitando el desarrollo de los proyectos tanto grupal como individualmente.

### Problemas Iniciales
En la primera versión del proyecto, nuestro principal problema tenía que ver con el principio de Single Responsibility, que dice que una clase debería tener una única funcionalidad o responsabilidad. Nuestro primer programa implementaba clases y objetos con múltiples responsabilidades. Por ejemplo, la clase Reserva actuaba simultáneamente como information holder, coordinator y controller, lo cual hacía complicado hacer cualquier cambio. Esta situación infringía también el Open-Closed principle de los principios SOLID. La decisión de no asignar responsabilidades únicas a las clases tuvo varias repercusiones, ya que al no poder identificar los errores con facilidad, empezamos a editar y modificar el código en lugar de simplemente añadir nuevas funcionalidades.

### Replanteamiento del Diseño
Decidimos replantear nuestro código para tener clases con funcionalidades únicas, evitando así la escalabilidad de los problemas al modificar el programa. Sabíamos que a medida que se nos pidieran más requerimientos y funcionalidades, las implicaciones de un diseño que no respetara el Single Responsibility Principle serían mayores.

### Segunda Fase del Proyecto
Entramos a la segunda fase con paquetes y clases más organizados, facilitando tanto el entendimiento general del programa como su implementación y edición. Debíamos implementar interfaces para correr el backend de nuestra aplicación de alquiler de carros de una forma amigable para el usuario. Esta entrega fue retadora, ya que fue nuestro primer acercamiento a la creación de un programa útil para el mundo real, considerando que ningún programa se corre en la terminal, como estábamos acostumbrados.

Diseñamos nuestras interfaces poniéndonos en los zapatos del cliente. Sin embargo, tras la segunda entrega, recibimos comentarios sobre el flujo de nuestra aplicación que no habíamos considerado. Por ejemplo, no incluimos botones de "salir" o "retroceder", lo que obligaba al usuario a reiniciar el programa completo. La entrega 2 fue útil para entender que la mejor forma de probar una interfaz es dándosela a un usuario externo para ver su interacción y hacer cambios significativos para la experiencia de uso.

### Interface Segregation Principle
Inicialmente, queríamos crear solo tres menús: cliente, empleado y administrador. Sin embargo, nos dimos cuenta de que estábamos violando el Interface Segregation Principle al consolidar muchas funcionalidades distintas en una sola ventana. Decidimos entonces separar las interfaces iniciales en distintos pasos y ventanas, asegurando que cada usuario accediera solo a las funcionalidades de su interés.

### Reflexión y Mejora Continua
La causa de la mayoría de nuestros problemas fue la falta de conocimiento de un debido proceso de diseño, lo que llevó a un análisis inicial del dominio muy pobre. A medida que entendimos el valor de un buen diseño, empezamos a implementar buenas prácticas, facilitando el trabajo en grupo y expandiendo nuestro conocimiento del dominio. Nos familiarizamos con Eclipse y la adopción de diversas librerías y frameworks, lo cual facilitó el desarrollo de esta última fase del proyecto.

### Conclusión
Esta última entrega del proyecto nos demostró la importancia de un proceso de desarrollo integral, donde un buen diseño permite un mejor análisis del dominio y prevé problemas. El diseño de pruebas y excepciones reforzó la relación entre un buen diseño, implementación y potencial de reutilización, representando en conjunto la calidad general de nuestro código. La experiencia de trabajo en grupo fue enriquecedora, demostrando que un programa de alta calidad se refleja en su capacidad de ser reutilizado y modificado fácilmente.
