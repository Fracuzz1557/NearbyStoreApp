# Aplicativo Móvil Android para buscar y encontrar tiendas cercanas

### RESUMEN  
La presente aplicación móvil, desarrollada en Android Studio utilizando Kotlin Jetpack Compose,  Firebase, claudinary, Google Maps, permite al usuario visualizar tiendas cercanas en un mapa interactivo. El sistema accede a la ubicación en tiempo real de los locales comerciales, permite buscar direcciones, visualizar resultados y navegar hacia puntos de interés. También permite una navegación intuitiva por categorías (foods, grocery, electronics, etc.), ofreciendo una experiencia moderna, visualmente amigable y útil para el día a día.

============================================================
### OBJETIVOS  
#### General:  
Desarrollar una aplicación móvil que permita visualizar tiendas cercanas desde el mapa usando 
ubicación en tiempo real.  
#### Específicos:  
1. Implementar la visualización de mapas con Google Maps. 
2. Obtener y mostrar la ubicación actual de los locales comerciales. 
3. Integrar búsqueda de direcciones y selección de puntos de interés en el mapa. 
4. Mostrar categorías comerciales como alimentos, electrónica, salud, etc. 
5. Guardar en Firebase los datos sobre búsquedas y visitas de los usuarios.

============================================================

### DEFINICIÓN Y ALCANCE  
#### Definición del Proyecto  
Este proyecto consiste en el desarrollo de una aplicación Android denominada“Tiendas  Cercanas, que permite localizar tiendas o negocios cercanos en un mapa y clasificarlos según categoría. Está diseñada con Kotlin Jetpack Compose, Firabase y hace uso de Google Maps, con lógica de permisos, ubicación y servicios en segundo plano, además, almacena todos todos los datos en Firebase y las imágenes en Claudinary. 

#### Alcance del Proyecto  
• Para el usuario final:  
• Visualización de mapa con negocios cercanos.  
• Solicitud de permisos de ubicación.  
• Visualización de categorías de tiendas.  
• Acceso a perfil y lista de favoritos.  
• Búsqueda de direcciones y puntos en el mapa. 

============================================================

### TECNOLOGÍAS UTILIZADAS  
▪ Jetpack Compose   
▪ Google Maps Compose   
▪ Foreground Service   
▪ Accompanist Permissions   
▪ Kotlin   
▪ Android Studio   
▪ Firebase  
▪ Claudinary  

============================================================

#### ESTRUCTURA DEL PROYECTO  
NearbyStoreApp/  
├── screens/  
│   ├── BasicMapScreen.kt  
│   ├── PermissionMapScreen.kt  
│   ├── RealLocationMapScreen.kt  
│   ├── SearchMapScreen.kt  
│   └── CategoryScreen.kt  
├── services/  
│   └── LocationService.kt  
├── models/  
│   └── StoreCategory.kt  
├── ui/  
│   ├── components/  
│   │   ├── StoreCard.kt  
│   │   └── CategoryButton.kt  
├── MainActivity.kt  
└── AndroidManifest.xml 

============================================================

### CAPTURAS

#### PANTALLA DE INICIO

![Image](https://github.com/user-attachments/assets/728ec47e-e573-43b6-8690-006160bb002d)

--------------------------------------------------------------------------------------------
#### PANTALLA SECUNDARIA
![Image](https://github.com/user-attachments/assets/2b79cacb-b684-4f35-98c6-d16b049dbc88)

--------------------------------------------------------------------------------------------

#### MAPA DEL LOCAL Y NUMERO CELULAR

###### **NOTA: ERROR AL MOMENTO DE VISUALIZAR EL MAPA DE GOOGLE MAPS
![Image](https://github.com/user-attachments/assets/9b47c4a4-2045-46d6-8e3a-79ebcc11abd7)

---------------------------------------------------------------------------------------------

#### FIREBASE

![Image](https://github.com/user-attachments/assets/af65b474-5946-4905-ad23-9985aa7fb634)

---------------------------------------------------------------------------------------------

#### CLAUDINARY

![Image](https://github.com/user-attachments/assets/7889c4f1-12a2-4cd3-8069-eb1044cd4fdc)

-----------------------------------------------------------------------------------------------

#### ESTRUCTURA VISUAL DEL PROYECTO

![Image](https://github.com/user-attachments/assets/30ccc51d-ef9b-41a3-bc04-270a6527bc71)

------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
