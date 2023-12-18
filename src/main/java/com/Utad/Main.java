package com.Utad;

import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Veterinaria");
    private static EntityManager em = entityManagerFactory.createEntityManager();
    public static void main(String[] args) {


        // Lógica para la gestión del menú
        mostrarMenu();
    }

    private static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("***** Menú Clínica Veterinaria *****");
            System.out.println("1. Agregar Dueño");
            System.out.println("2. Agregar Mascota");
            System.out.println("3. Agregar Visita");
            System.out.println("4. Mostrar todas las Mascotas");
            System.out.println("5. Mostrar todos los Dueños");
            System.out.println("6. Mostrar todas las Visitas");
            System.out.println("7. Editar Mascota");
            System.out.println("8. Eliminar Mascota");
            System.out.println("9. Editar Visita");
            System.out.println("10. Eliminar Visita");
            System.out.println("11. Editar Dueño");
            System.out.println("12. Eliminar Dueño");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción (0-6): ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de leer el número

            switch (opcion) {
                case 1:
                    // Lógica para agregar dueño
                    System.out.print("Nombre: ");
                    String nombreD = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String direccion = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    Integer telefono = scanner.nextInt();

                    agregarDueno(new Dueno(nombreD, direccion, telefono));
                    break;
                case 2:
                    // Lógica para agregar mascota
                    System.out.print("Nombre: ");
                    String nombreM = scanner.nextLine();
                    System.out.print("Tipo: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Raza: ");
                    String raza = scanner.nextLine();

                    // Obtén la lista de dueños para que el usuario elija uno
                    List<Dueno> duenos = obtenerTodosLosDuenos();
                    if (!duenos.isEmpty()) {
                        System.out.println("Seleccione el número del dueño:");
                        for (int i = 0; i < duenos.size(); i++) {
                            System.out.println(i + 1 + ". " + duenos.get(i).getNombre());
                        }
                        int opcionDueno = scanner.nextInt();
                        if (opcionDueno > 0 && opcionDueno <= duenos.size()) {
                            // Agrega la mascota asociada al dueño seleccionado
                            agregarMascota(new Mascota(nombreM, tipo, raza, duenos.get(opcionDueno - 1)));
                        } else {
                            System.out.println("Opción de dueño no válida.");
                        }
                    } else {
                        System.out.println("No hay dueños registrados. Agregue un dueño primero.");
                    }
                    break;
                case 3:
                    // Lógica para agregar visita
                    System.out.print("Fecha: ");
                    String fecha = scanner.nextLine();
                    System.out.print("Motivo de la consulta: ");
                    String motivoConsulta = scanner.nextLine();
                    System.out.print("Diagnóstico: ");
                    String diagnostico = scanner.nextLine();

                    // Obtén la lista de mascotas para que el usuario elija una
                    List<Mascota> mascotas = obtenerTodasLasMascotas();
                    if (!mascotas.isEmpty()) {
                        System.out.println("Seleccione el número de la mascota:");
                        for (int i = 0; i < mascotas.size(); i++) {
                            System.out.println(i + 1 + ". " + mascotas.get(i).getNombre());
                        }
                        int opcionMascota = scanner.nextInt();
                        if (opcionMascota > 0 && opcionMascota <= mascotas.size()) {
                            // Agrega la visita asociada a la mascota seleccionada
                            agregarVisita(new Visita(fecha, motivoConsulta, diagnostico, mascotas.get(opcionMascota - 1)));
                        } else {
                            System.out.println("Opción de mascota no válida.");
                        }
                    } else {
                        System.out.println("No hay mascotas registradas. Agregue una mascota primero.");
                    }
                    break;
                case 4:
                    // Lógica para mostrar todas las mascotas
                    List<Mascota> todasLasMascotas = obtenerTodasLasMascotas();
                    for (Mascota mascota : todasLasMascotas) {
                        System.out.println("Nombre: " + mascota.getNombre() + ", Tipo: " + mascota.getTipo() + ", Raza: " + mascota.getRaza());
                    }
                    break;
                case 5:
                    // Lógica para mostrar todos los dueños
                    List<Dueno> todosLosDuenos = obtenerTodosLosDuenos();
                    for (Dueno dueno : todosLosDuenos) {
                        System.out.println("Nombre: " + dueno.getNombre() + ", Dirección: " + dueno.getDireccion());
                    }
                    break;
                case 6:
                    // Lógica para mostrar todas las visitas
                    List<Visita> todasLasVisitas = obtenerTodasLasVisitas();
                    for (Visita visita : todasLasVisitas) {
                        System.out.println("Fecha: " + visita.getFecha() + ", Motivo: " + visita.getMotivoConsulta() + ", Diagnóstico: " + visita.getDiagnostico());
                    }
                    break;
                case 7:
                    // Lógica para editar mascota
                    System.out.println("Seleccione el número de la mascota que desea editar:");
                    List<Mascota> mascotasEditar = obtenerTodasLasMascotas();
                    for (int i = 0; i < mascotasEditar.size(); i++) {
                        System.out.println(i + 1 + ". " + mascotasEditar.get(i).getNombre());
                    }
                    int opcionMascotaEditar = scanner.nextInt();
                    if (opcionMascotaEditar > 0 && opcionMascotaEditar <= mascotasEditar.size()) {
                        scanner.nextLine(); // Consumir la nueva línea después de leer el número

                        Mascota mascotaEditar = mascotasEditar.get(opcionMascotaEditar - 1);
                        System.out.print("Nuevo nombre: ");
                        mascotaEditar.setNombre(scanner.nextLine());
                        System.out.print("Nuevo tipo: ");
                        mascotaEditar.setTipo(scanner.nextLine());
                        System.out.print("Nueva raza: ");
                        mascotaEditar.setRaza(scanner.nextLine());

                        actualizarMascota(mascotaEditar);
                        System.out.println("Mascota editada correctamente.");
                    } else {
                        System.out.println("Opción de mascota no válida.");
                    }
                    break;
                case 8:
                    // Lógica para eliminar mascota
                    System.out.println("Seleccione el número de la mascota que desea eliminar:");
                    List<Mascota> mascotasEliminar = obtenerTodasLasMascotas();
                    for (int i = 0; i < mascotasEliminar.size(); i++) {
                        System.out.println(i + 1 + ". " + mascotasEliminar.get(i).getNombre());
                    }
                    int opcionMascotaEliminar = scanner.nextInt();
                    if (opcionMascotaEliminar > 0 && opcionMascotaEliminar <= mascotasEliminar.size()) {
                        Mascota mascotaEliminar = mascotasEliminar.get(opcionMascotaEliminar - 1);

                        eliminarMascota(mascotaEliminar.getId());
                        System.out.println("Mascota eliminada correctamente.");
                    } else {
                        System.out.println("Opción de mascota no válida.");
                    }
                    break;
                case 9:
                    // Lógica para editar visita
                    System.out.println("Seleccione el número de la visita que desea editar:");
                    List<Visita> visitasEditar = obtenerTodasLasVisitas();
                    for (int i = 0; i < visitasEditar.size(); i++) {
                        System.out.println(i + 1 + ". " + visitasEditar.get(i).getFecha());
                    }
                    int opcionVisitaEditar = scanner.nextInt();
                    if (opcionVisitaEditar > 0 && opcionVisitaEditar <= visitasEditar.size()) {
                        scanner.nextLine(); // Consumir la nueva línea después de leer el número

                        Visita visitaEditar = visitasEditar.get(opcionVisitaEditar - 1);
                        System.out.print("Nueva fecha: ");
                        visitaEditar.setFecha(scanner.nextLine());
                        System.out.print("Nuevo motivo de consulta: ");
                        visitaEditar.setMotivoConsulta(scanner.nextLine());
                        System.out.print("Nuevo diagnóstico: ");
                        visitaEditar.setDiagnostico(scanner.nextLine());

                        actualizarVisita(visitaEditar);
                        System.out.println("Visita editada correctamente.");
                    } else {
                        System.out.println("Opción de visita no válida.");
                    }
                    break;
                case 10:
                    // Lógica para eliminar visita
                    System.out.println("Seleccione el número de la visita que desea eliminar:");
                    List<Visita> visitasEliminar = obtenerTodasLasVisitas();
                    for (int i = 0; i < visitasEliminar.size(); i++) {
                        System.out.println(i + 1 + ". " + visitasEliminar.get(i).getFecha());
                    }
                    int opcionVisitaEliminar = scanner.nextInt();
                    if (opcionVisitaEliminar > 0 && opcionVisitaEliminar <= visitasEliminar.size()) {
                        Visita visitaEliminar = visitasEliminar.get(opcionVisitaEliminar - 1);

                        eliminarVisita(visitaEliminar.getId());
                        System.out.println("Visita eliminada correctamente.");
                    } else {
                        System.out.println("Opción de visita no válida.");
                    }
                    break;
                case 11:
                    // Lógica para editar dueño
                    System.out.println("Seleccione el número del dueño que desea editar:");
                    List<Dueno> duenosEditar = obtenerTodosLosDuenos();
                    for (int i = 0; i < duenosEditar.size(); i++) {
                        System.out.println(i + 1 + ". " + duenosEditar.get(i).getNombre());
                    }
                    int opcionDuenoEditar = scanner.nextInt();
                    if (opcionDuenoEditar > 0 && opcionDuenoEditar <= duenosEditar.size()) {
                        scanner.nextLine(); // Consumir la nueva línea después de leer el número

                        Dueno duenoEditar = duenosEditar.get(opcionDuenoEditar - 1);
                        System.out.print("Nuevo nombre: ");
                        duenoEditar.setNombre(scanner.nextLine());
                        System.out.print("Nueva dirección: ");
                        duenoEditar.setDireccion(scanner.nextLine());
                        System.out.print("Nuevo teléfono: ");
                        duenoEditar.setTelefono(scanner.nextInt());

                        actualizarDueno(duenoEditar);
                        System.out.println("Dueño editado correctamente.");
                    } else {
                        System.out.println("Opción de dueño no válida.");
                    }
                    break;
                case 12:
                    // Lógica para eliminar dueño
                    System.out.println("Seleccione el número del dueño que desea eliminar:");
                    List<Dueno> duenosEliminar = obtenerTodosLosDuenos();
                    for (int i = 0; i < duenosEliminar.size(); i++) {
                        System.out.println(i + 1 + ". " + duenosEliminar.get(i).getNombre());
                    }
                    int opcionDuenoEliminar = scanner.nextInt();
                    if (opcionDuenoEliminar > 0 && opcionDuenoEliminar <= duenosEliminar.size()) {
                        Dueno duenoEliminar = duenosEliminar.get(opcionDuenoEliminar - 1);

                        eliminarDueno(duenoEliminar.getId());
                        System.out.println("Dueño eliminado correctamente.");
                    } else {
                        System.out.println("Opción de dueño no válida.");
                    }
                    break;
                case 0:
                    salir = true;
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
            }
        }

    }
    // ********************** METODOS DUEÑO ********************************

    /**
     * Agrega un nuevo dueño.
     *
     * @param dueno Dueño a agregar.
     */
    public static void agregarDueno(Dueno dueno) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(dueno);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Obtiene todos los dueños.
     *
     * @return Lista de todos los dueños.
     */
    public static List<Dueno> obtenerTodosLosDuenos() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Dueno> query = entityManager.createQuery("SELECT d FROM Dueno d", Dueno.class);
            return query.getResultList();
        }
    }

    /**
     * Actualiza la información de un dueño.
     *
     * @param dueno Dueño a actualizar.
     */
    public static void actualizarDueno(Dueno dueno) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(dueno);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Elimina un dueño por su ID.
     *
     * @param duenoId ID del dueño a eliminar.
     */
    public static void eliminarDueno(Long duenoId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Dueno dueno = entityManager.find(Dueno.class, duenoId);
            if (dueno != null) {
                entityManager.remove(dueno);
            }
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Obtiene un dueño por su ID.
     *
     * @param duenoId ID del dueño a obtener.
     * @return El dueño encontrado o null si no se encuentra.
     */
    public Dueno obtenerDuenoPorId(Long duenoId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.find(Dueno.class, duenoId);
        }
    }

    // ********************** METODOS MASCOTA ********************************

    /**
     * Agrega una nueva mascota.
     *
     * @param mascota Mascota a agregar.
     */
    public static void agregarMascota(Mascota mascota) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(mascota);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Obtiene todas las mascotas.
     *
     * @return Lista de todas las mascotas.
     */
    public static List<Mascota> obtenerTodasLasMascotas() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Mascota> query = entityManager.createQuery("SELECT m FROM Mascota m", Mascota.class);
            return query.getResultList();
        }
    }

    /**
     * Actualiza la información de una mascota.
     *
     * @param mascota Mascota a actualizar.
     */
    public static void actualizarMascota(Mascota mascota) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(mascota);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Elimina una mascota por su ID.
     *
     * @param mascotaId ID de la mascota a eliminar.
     */
    public static void eliminarMascota(Long mascotaId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Mascota mascota = entityManager.find(Mascota.class, mascotaId);
            if (mascota != null) {
                entityManager.remove(mascota);
            }
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Obtiene una mascota por su ID.
     *
     * @param mascotaId ID de la mascota a obtener.
     * @return La mascota encontrada o null si no se encuentra.
     */
    public Mascota obtenerMascotaPorId(Long mascotaId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.find(Mascota.class, mascotaId);
        }
    }

    // ********************** METODOS VISITA ********************************


    /**
     * Agrega una nueva visita.
     *
     * @param visita Visita a agregar.
     */
    public static void agregarVisita(Visita visita) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(visita);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Obtiene todas las visitas.
     *
     * @return Lista de todas las visitas.
     */
    public static List<Visita> obtenerTodasLasVisitas() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Visita> query = entityManager.createQuery("SELECT v FROM Visita v", Visita.class);
            return query.getResultList();
        }
    }

    /**
     * Actualiza la información de una visita.
     *
     * @param visita Visita a actualizar.
     */
    public static void actualizarVisita(Visita visita) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(visita);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Elimina una visita por su ID.
     *
     * @param visitaId ID de la visita a eliminar.
     */
    public static void eliminarVisita(Long visitaId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Visita visita = entityManager.find(Visita.class, visitaId);
            if (visita != null) {
                entityManager.remove(visita);
            }
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Obtiene una visita por su ID.
     *
     * @param visitaId ID de la visita a obtener.
     * @return La visita encontrada o null si no se encuentra.
     */
    public Visita obtenerVisitaPorId(Long visitaId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.find(Visita.class, visitaId);
        }
    }
}
