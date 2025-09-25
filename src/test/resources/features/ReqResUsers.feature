@AllUsers
Feature: Pruebas de API con JSONPlaceholder

  @GetAllUsers
  Scenario: Obtener todos los usuarios
    Given el actor establece el endpoint de jsonplaceholder
    When el actor obtiene todos los usuarios
    Then el codigo de respuesta debe ser 200

  @PostUser
  Scenario: Crear un post
    Given el actor establece el endpoint de jsonplaceholder
    When el actor crea un post con "mi titulo" "mi contenido"
    Then el codigo de respuesta debe ser 201

  @PutUser
  Scenario: Actualizar un post
    Given el actor establece el endpoint de jsonplaceholder
    And el actor crea un post con "titulo inicial" "contenido inicial"
    When el actor actualiza el post con "titulo modificado" "contenido modificado"
    Then el codigo de respuesta debe ser 200

  @DeleteUser
  Scenario: Eliminar un post
    Given el actor establece el endpoint de jsonplaceholder
    And el actor crea un post con "titulo borrar" "contenido borrar"
    When el actor elimina el post
    Then el codigo de respuesta debe ser 200
