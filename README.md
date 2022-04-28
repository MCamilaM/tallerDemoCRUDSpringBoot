# Actividad Spring Boot 
En el presente repositorio se aloja la solución a la actividad planteada del curso de desarrollo web 2.0 para asimilar los conocimientos adquiridos sobre Spring Boot.


### EndPoint de actualización de usuario.
```sh
put usuario
```
- Insertamos un nuevo usuario.
![actualizacion-usuario1](https://user-images.githubusercontent.com/88548284/165698046-43efd544-afbe-4622-b2e4-40358d9e1490.jpg)
- Modificamos los campos al usuario anteriormente agregado.
![actualizacion-usuario2](https://user-images.githubusercontent.com/88548284/165698124-04bc0c39-7dd2-4b7c-be23-a3ea52c18ef0.jpg)


### Relación (Usuario - UsuarioRol)

Opté por la cardinalidad @OneToMany para realizar lo que se denomina propagación de clave, descarte por el momento la relación @ManyToMany por qué este tipo de relación se transforma en una tabla más.

```sh
post usuario Rol
```
![post-usuarioRol](https://user-images.githubusercontent.com/88548284/165700180-56905b4f-d628-4ca6-8674-e0bf399cb4de.jpg)

```sh
get usuario Rol
```
![get-usuarioRol](https://user-images.githubusercontent.com/88548284/165700244-458ce281-f8a2-48c0-aaef-60378dfa3280.jpg)

```sh
put usuario Rol
```
![put-usuarioRol](https://user-images.githubusercontent.com/88548284/165700359-93072ca4-7e33-451d-8317-b4ef03acb7a6.jpg)

```sh
delete usuario Rol
```
![delete-usuarioRol](https://user-images.githubusercontent.com/88548284/165700472-6a8ca615-d308-412f-9fb2-46f5011eb265.jpg)

```sh
get usuario
```
- Finalmente se puede visualizar la relación @OneToMany en la entidad Usuario, ya que un usuario puede tener en su momento mas de un rol.

![get-usuario](https://user-images.githubusercontent.com/88548284/165701475-a6500fc0-6c3e-44e1-a411-0e29dfda9858.jpg)


### Pruebas Unitarias
```sh
test mockito
```
![test-mockito](https://user-images.githubusercontent.com/88548284/165702770-6485fd0a-037f-4cb0-8c06-e516f2e9043a.jpg)

```sh
test JUnit
```
![test-jUnit](https://user-images.githubusercontent.com/88548284/165705808-22779fd9-f5df-4769-9c19-006eb8c4a9ca.jpg)




