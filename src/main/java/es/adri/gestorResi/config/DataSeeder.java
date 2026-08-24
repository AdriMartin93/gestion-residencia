package es.adri.gestorResi.config;

import es.adri.gestorResi.entidades.enums.AccionEnfermeria;
import es.adri.gestorResi.entidades.enums.ActividadFisio;
import es.adri.gestorResi.entidades.enums.Caidas.Calzado;
import es.adri.gestorResi.entidades.enums.Caidas.ConsecuenciaCaida;
import es.adri.gestorResi.entidades.enums.EstadoTarea;
import es.adri.gestorResi.entidades.enums.Incidencias;
import es.adri.gestorResi.entidades.enums.Posiciones;
import es.adri.gestorResi.entidades.enums.Roles;
import es.adri.gestorResi.entidades.enums.evacuaciones.CantidadDepo;
import es.adri.gestorResi.entidades.enums.evacuaciones.CantidadOrina;
import es.adri.gestorResi.entidades.enums.evacuaciones.TipoDepo;
import es.adri.gestorResi.entidades.enums.evacuaciones.TipoOrina;
import es.adri.gestorResi.entidades.enums.psicologia.CategoriaActividad;
import es.adri.gestorResi.entidades.enums.psicologia.TipoRegistro;
import es.adri.gestorResi.entidades.enums.trabajoSocial.CategoriaSocial;
import es.adri.gestorResi.entidades.enums.trabajoSocial.EstadoTramite;
import es.adri.gestorResi.entidades.enums.trabajoSocial.TipoIntervFamiliar;
import es.adri.gestorResi.entidades.enums.trabajoSocial.TipoRecurso;
import es.adri.gestorResi.entidades.personas.Contacto;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Empresa;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.registros.*;
import es.adri.gestorResi.entidades.registros.diariosRes.CambioPostural;
import es.adri.gestorResi.entidades.registros.diariosRes.Evacuaciones;
import es.adri.gestorResi.entidades.registros.diariosRes.Higiene;
import es.adri.gestorResi.entidades.registros.diariosRes.RegistroMedicacion;
import es.adri.gestorResi.entidades.registros.limpieza.LimpiezaClin;
import es.adri.gestorResi.entidades.registros.limpieza.LimpiezaComun;
import es.adri.gestorResi.entidades.registros.limpieza.LimpiezaHab;
import es.adri.gestorResi.entidades.registros.limpieza.LimpiezaRopa;
import es.adri.gestorResi.entidades.salud.HistorialMedico;
import es.adri.gestorResi.entidades.salud.PautaMedica;
import es.adri.gestorResi.repositorio.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    private final EmpresaRepository empresaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ResidenteRepository residenteRepository;
    private final HistorialMedicoRepository historialMedicoRepository;
    private final PautaMedicaRepository pautaMedicaRepository;
    private final CambiosPosturalesRepository cambiosPosturalesRepository;
    private final EvacuacionesRepository evacuacionesRepository;
    private final HigieneRepository higieneRepository;
    private final RegistroMedicacionRepository registroMedicacionRepository;
    private final LimpiezaBaseRepository limpiezaBaseRepository;
    private final CaidasRepository caidasRepository;
    private final ControlCocinaRepository controlCocinaRepository;
    private final FichajeRepository fichajeRepository;
    private final IncidenciaRepository incidenciaRepository;
    private final ParteDiarioRepository parteDiarioRepository;
    private final RegistroAnimacionRepository registroAnimacionRepository;
    private final RegistroEnfermeriaRepository registroEnfermeriaRepository;
    private final RegistroFisioRepository registroFisioRepository;
    private final RegistroPsicologiaRepository registroPsicologiaRepository;
    private final RegistroSocialRepository registroSocialRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(EmpresaRepository empresaRepository,
                      EmpleadoRepository empleadoRepository,
                      ResidenteRepository residenteRepository,
                      HistorialMedicoRepository historialMedicoRepository,
                      PautaMedicaRepository pautaMedicaRepository,
                      CambiosPosturalesRepository cambiosPosturalesRepository,
                      EvacuacionesRepository evacuacionesRepository,
                      HigieneRepository higieneRepository,
                      RegistroMedicacionRepository registroMedicacionRepository,
                      LimpiezaBaseRepository limpiezaBaseRepository,
                      CaidasRepository caidasRepository,
                      ControlCocinaRepository controlCocinaRepository,
                      FichajeRepository fichajeRepository,
                      IncidenciaRepository incidenciaRepository,
                      ParteDiarioRepository parteDiarioRepository,
                      RegistroAnimacionRepository registroAnimacionRepository,
                      RegistroEnfermeriaRepository registroEnfermeriaRepository,
                      RegistroFisioRepository registroFisioRepository,
                      RegistroPsicologiaRepository registroPsicologiaRepository,
                      RegistroSocialRepository registroSocialRepository,
                      PasswordEncoder passwordEncoder) {
        this.empresaRepository = empresaRepository;
        this.empleadoRepository = empleadoRepository;
        this.residenteRepository = residenteRepository;
        this.historialMedicoRepository = historialMedicoRepository;
        this.pautaMedicaRepository = pautaMedicaRepository;
        this.cambiosPosturalesRepository = cambiosPosturalesRepository;
        this.evacuacionesRepository = evacuacionesRepository;
        this.higieneRepository = higieneRepository;
        this.registroMedicacionRepository = registroMedicacionRepository;
        this.limpiezaBaseRepository = limpiezaBaseRepository;
        this.caidasRepository = caidasRepository;
        this.controlCocinaRepository = controlCocinaRepository;
        this.fichajeRepository = fichajeRepository;
        this.incidenciaRepository = incidenciaRepository;
        this.parteDiarioRepository = parteDiarioRepository;
        this.registroAnimacionRepository = registroAnimacionRepository;
        this.registroEnfermeriaRepository = registroEnfermeriaRepository;
        this.registroFisioRepository = registroFisioRepository;
        this.registroPsicologiaRepository = registroPsicologiaRepository;
        this.registroSocialRepository = registroSocialRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>> INICIANDO EJECUCIÓN DE DATA SEEDER <<<<<<<<<<");

        try {
            // 1. Obtener o crear Empresa Demo
            String cifDemo = "B12345678";
            Empresa empresaDemo = empresaRepository.findAll().stream()
                    .filter(e -> cifDemo.equals(e.getCif()))
                    .findFirst()
                    .orElseGet(() -> {
                        Empresa nueva = new Empresa();
                        nueva.setCif(cifDemo);
                        nueva.setNombreComercial("Residencia Los Olivos (Demo)");
                        nueva.setEmail("contacto@losolivosdemo.com");
                        return empresaRepository.save(nueva);
                    });

            // 2. Obtener o crear Empleado Admin Demo
            String usuarioDemo = "adminDemo";
            Empleado admin = empleadoRepository.findByNombreUsuario(usuarioDemo)
                    .orElseGet(() -> {
                        Empleado nuevo = new Empleado();
                        nuevo.setNombreUsuario(usuarioDemo);
                        nuevo.setDni("12345678Z");
                        nuevo.setNombre("Administrador");
                        nuevo.setApellidos("Principal Demo");
                        nuevo.setEmail("admin@losolivosdemo.com");
                        nuevo.setTelefono("600123456");
                        nuevo.setPassword(passwordEncoder.encode("admin123"));
                        nuevo.setRoles(Set.of(Roles.ROLE_DIRECTOR));
                        nuevo.setEmpresa(empresaDemo);
                        return empleadoRepository.save(nuevo);
                    });

            // 3. Comprobar si ya existen residentes; si no existen, sembramos todo el resto de datos
            if (residenteRepository.count() > 0) {
                System.out.println("ℹ️ Los datos de prueba ya estaban sembrados. Omitiendo.");
                return;
            }

            // ==========================================
            // RESIDENTES & HISTORIAL MÉDICO & CONTACTOS
            // ==========================================
            Residente res1 = new Residente();
            res1.setDni("00112233A");
            res1.setTis("TIS100001");
            res1.setNombre("Manuel");
            res1.setApellidos("García Fernández");
            res1.setFechaNacimiento(LocalDate.of(1942, 5, 14));
            res1.setHabitacion("101");
            res1.setActivo(true);
            res1.getContactos().add(new Contacto("Rosa García", "Hija", "633445566", "rosa@mail.com"));
            res1.getContactos().add(new Contacto("Carlos García", "Hijo", "644556677", "carlos@mail.com"));

            HistorialMedico hm1 = new HistorialMedico();
            hm1.setGrupoSanguineo("A+");
            hm1.setAntecedentesClinicos("Hipertensión crónica, Diabetes tipo II");
            hm1.setAlergias(Set.of("Penicilina", "Polen"));
            hm1.setDieta("Baja en sodio y azúcares");
            hm1.setMovilidad("Reducida, precisa andador");
            res1.setHistorialMedico(hm1);
            res1 = residenteRepository.save(res1);

            Residente res2 = new Residente();
            res2.setDni("99887766C");
            res2.setTis("TIS100002");
            res2.setNombre("Dolores");
            res2.setApellidos("Martínez López");
            res2.setFechaNacimiento(LocalDate.of(1938, 11, 20));
            res2.setHabitacion("102");
            res2.setActivo(true);
            res2.getContactos().add(new Contacto("Antonio Martínez", "Hermano", "655667788", "antonio@mail.com"));
            res2.getContactos().add(new Contacto("Elena Soto", "Sobrina", "666778899", "elena@mail.com"));

            HistorialMedico hm2 = new HistorialMedico();
            hm2.setGrupoSanguineo("0-");
            hm2.setAntecedentesClinicos("Demencia senil leve, Artrosis");
            hm2.setAlergias(Set.of("Ácaros"));
            hm2.setDieta("Túrmix basal");
            hm2.setMovilidad("Dependiente para traslados, silla de ruedas");
            res2.setHistorialMedico(hm2);
            res2 = residenteRepository.save(res2);

            // ==========================================
            // PAUTAS MÉDICAS
            // ==========================================
            PautaMedica pm1 = new PautaMedica();
            pm1.setResidente(res1);
            pm1.setHistorialMedico(res1.getHistorialMedico());
            pm1.setMedicamento("Enalapril 20mg");
            pm1.setDosis("1 comprimido en el desayuno");
            pm1.setFechaHora(LocalDateTime.now().minusMonths(2));
            pm1.setDuracion("Crónico");
            pm1.setObservaciones("Controlar tensión antes de administrar");
            pautaMedicaRepository.save(pm1);

            PautaMedica pm2 = new PautaMedica();
            pm2.setResidente(res2);
            pm2.setHistorialMedico(res2.getHistorialMedico());
            pm2.setMedicamento("Paracetamol 1g");
            pm2.setDosis("1 sobre cada 8 horas si hay dolor");
            pm2.setFechaHora(LocalDateTime.now().minusMonths(1));
            pm2.setDuracion("30 días");
            pm2.setObservaciones("Diluir en agua o zumo");
            pautaMedicaRepository.save(pm2);

            // ==========================================
            // CAMBIOS POSTURALES
            // ==========================================
            CambioPostural cp1 = new CambioPostural();
            cp1.setResidente(res1);
            cp1.setEmpleado(admin);
            cp1.setFechaHora(LocalDateTime.now().minusHours(4));
            cp1.setPosicion(Posiciones.DECUBITO_LATERAL_DER);
            cp1.setObservaciones("Se coloca cojín antiescaras.");
            cambiosPosturalesRepository.save(cp1);

            CambioPostural cp2 = new CambioPostural();
            cp2.setResidente(res2);
            cp2.setEmpleado(admin);
            cp2.setFechaHora(LocalDateTime.now().minusHours(1));
            cp2.setPosicion(Posiciones.DECUBITO_SUPINO);
            cp2.setObservaciones("Sin molestias aparentes.");
            cambiosPosturalesRepository.save(cp2);

            // ==========================================
            // EVACUACIONES
            // ==========================================
            Evacuaciones ev1 = new Evacuaciones();
            ev1.setResidente(res1);
            ev1.setEmpleado(admin);
            ev1.setFechaHora(LocalDateTime.now().minusHours(6));
            ev1.setOrina(TipoOrina.NORMAL);
            ev1.setCantOrina(CantidadOrina.NORMAL);
            ev1.setDepo(TipoDepo.NORMAL);
            ev1.setCantDepo(CantidadDepo.NORMAL);
            evacuacionesRepository.save(ev1);

            Evacuaciones ev2 = new Evacuaciones();
            ev2.setResidente(res2);
            ev2.setEmpleado(admin);
            ev2.setFechaHora(LocalDateTime.now().minusHours(2));
            ev2.setOrina(TipoOrina.CONCENTRADA);
            ev2.setCantOrina(CantidadOrina.POCA);
            ev2.setDepo(TipoDepo.PASTOSA);
            ev2.setCantDepo(CantidadDepo.MUCHA);
            evacuacionesRepository.save(ev2);

            // ==========================================
            // HIGIENE
            // ==========================================
            Higiene hg1 = new Higiene();
            hg1.setResidente(res1);
            hg1.setEmpleado(admin);
            hg1.setFechaHora(LocalDateTime.now().minusHours(8));
            hg1.setHigieneIntima(EstadoTarea.SI);
            hg1.setDucha(EstadoTarea.SI);
            hg1.setCorteUnas(EstadoTarea.NO);
            hg1.setHigieneBucal(EstadoTarea.SI);
            hg1.setAfeitado(EstadoTarea.SI);
            hg1.setHidratacionPiel(EstadoTarea.SI);
            hg1.setLevantarResidente(EstadoTarea.SI);
            higieneRepository.save(hg1);

            Higiene hg2 = new Higiene();
            hg2.setResidente(res2);
            hg2.setEmpleado(admin);
            hg2.setFechaHora(LocalDateTime.now().minusHours(7));
            hg2.setHigieneIntima(EstadoTarea.SI);
            hg2.setDucha(EstadoTarea.NO);
            hg2.setCorteUnas(EstadoTarea.SI);
            hg2.setHigieneBucal(EstadoTarea.SI);
            hg2.setAfeitado(EstadoTarea.NA);
            hg2.setHidratacionPiel(EstadoTarea.SI);
            hg2.setLevantarResidente(EstadoTarea.SI);
            higieneRepository.save(hg2);

            // ==========================================
            // REGISTRO DE MEDICACIÓN
            // ==========================================
            RegistroMedicacion rm1 = new RegistroMedicacion();
            rm1.setResidente(res1);
            rm1.setEmpleado(admin);
            rm1.setPautaMedica(pm1);
            rm1.setFechaHoraReal(LocalDateTime.now().minusHours(6));
            rm1.setEstadoTarea(EstadoTarea.SI);
            rm1.setObservaciones("Toma la medicación sin problemas tras desayunar.");
            registroMedicacionRepository.save(rm1);

            RegistroMedicacion rm2 = new RegistroMedicacion();
            rm2.setResidente(res2);
            rm2.setEmpleado(admin);
            rm2.setPautaMedica(pm2);
            rm2.setFechaHoraReal(LocalDateTime.now().minusHours(3));
            rm2.setEstadoTarea(EstadoTarea.SI);
            rm2.setObservaciones("Administrado por dolor lumbar.");
            registroMedicacionRepository.save(rm2);

            // ==========================================
            // LIMPIEZA
            // ==========================================
            LimpiezaHab lhab1 = new LimpiezaHab();
            lhab1.setEmpleado(admin);
            lhab1.setFecha(LocalDate.now());
            lhab1.setObservaciones("Habitación 101 desinfectada");
            lhab1.setCambioSabanas(EstadoTarea.SI);
            lhab1.setLimpiezaSuperficies(EstadoTarea.SI);
            lhab1.setLimpiezaLavabo(EstadoTarea.SI);
            lhab1.setReposicion(EstadoTarea.SI);
            limpiezaBaseRepository.save(lhab1);

            LimpiezaHab lhab2 = new LimpiezaHab();
            lhab2.setEmpleado(admin);
            lhab2.setFecha(LocalDate.now());
            lhab2.setObservaciones("Habitación 102 completa");
            lhab2.setCambioSabanas(EstadoTarea.SI);
            lhab2.setLimpiezaSuperficies(EstadoTarea.SI);
            lhab2.setLimpiezaLavabo(EstadoTarea.SI);
            lhab2.setReposicion(EstadoTarea.SI);
            limpiezaBaseRepository.save(lhab2);

            LimpiezaComun lcom1 = new LimpiezaComun();
            lcom1.setEmpleado(admin);
            lcom1.setFecha(LocalDate.now().minusDays(1));
            lcom1.setObservaciones("Comedor y pasillos planta 1");
            lcom1.setLimpiezaAscensores(EstadoTarea.SI);
            lcom1.setLimpiezaSillas(EstadoTarea.SI);
            lcom1.setLimpiezaSuperficies(EstadoTarea.SI);
            limpiezaBaseRepository.save(lcom1);

            LimpiezaComun lcom2 = new LimpiezaComun();
            lcom2.setEmpleado(admin);
            lcom2.setFecha(LocalDate.now());
            lcom2.setObservaciones("Sala polivalente");
            lcom2.setLimpiezaAscensores(EstadoTarea.SI);
            lcom2.setLimpiezaSillas(EstadoTarea.SI);
            lcom2.setLimpiezaSuperficies(EstadoTarea.SI);
            limpiezaBaseRepository.save(lcom2);

            LimpiezaClin lclin1 = new LimpiezaClin();
            lclin1.setEmpleado(admin);
            lclin1.setFecha(LocalDate.now().minusDays(1));
            lclin1.setObservaciones("Enfermería planta baja");
            lclin1.setDesinfecCamillas(EstadoTarea.SI);
            lclin1.setRetiradaResBio(EstadoTarea.SI);
            lclin1.setLimpiezaSuperficies(EstadoTarea.SI);
            limpiezaBaseRepository.save(lclin1);

            LimpiezaClin lclin2 = new LimpiezaClin();
            lclin2.setEmpleado(admin);
            lclin2.setFecha(LocalDate.now());
            lclin2.setObservaciones("Sala de curas");
            lclin2.setDesinfecCamillas(EstadoTarea.SI);
            lclin2.setRetiradaResBio(EstadoTarea.SI);
            lclin2.setLimpiezaSuperficies(EstadoTarea.SI);
            limpiezaBaseRepository.save(lclin2);

            LimpiezaRopa lropa1 = new LimpiezaRopa();
            lropa1.setEmpleado(admin);
            lropa1.setFecha(LocalDate.now().minusDays(1));
            lropa1.setObservaciones("Lote toallas y sábanas");
            lropa1.setLavado(EstadoTarea.SI);
            lropa1.setSecado(EstadoTarea.SI);
            lropa1.setPlanchado(EstadoTarea.SI);
            lropa1.setEntrega(EstadoTarea.SI);
            limpiezaBaseRepository.save(lropa1);

            LimpiezaRopa lropa2 = new LimpiezaRopa();
            lropa2.setEmpleado(admin);
            lropa2.setFecha(LocalDate.now());
            lropa2.setObservaciones("Ropa personal residentes");
            lropa2.setLavado(EstadoTarea.SI);
            lropa2.setSecado(EstadoTarea.SI);
            lropa2.setPlanchado(EstadoTarea.SI);
            lropa2.setEntrega(EstadoTarea.SI);
            limpiezaBaseRepository.save(lropa2);

            // ==========================================
            // CAÍDAS
            // ==========================================
            Caidas caida1 = new Caidas();
            caida1.setResidente(res1);
            caida1.setEmpleado(admin);
            caida1.setFechaHora(LocalDateTime.now().minusDays(3));
            caida1.setLugar("Pasillo planta 1");
            caida1.setActividad("Deambulación hacia el comedor");
            caida1.setDescripcionCaida("Tropiezo con la pata de una silla");
            caida1.setCalzado(Calzado.ZAPATILLAS);
            caida1.setConsciente(true);
            caida1.setConsecuencias(Set.of(ConsecuenciaCaida.EROSION, ConsecuenciaCaida.HEMATOMA));
            caida1.setAcciones("Se aplica hielo y se realiza cura local.");
            caidasRepository.save(caida1);

            Caidas caida2 = new Caidas();
            caida2.setResidente(res2);
            caida2.setEmpleado(admin);
            caida2.setFechaHora(LocalDateTime.now().minusDays(1));
            caida2.setLugar("Habitación 102");
            caida2.setActividad("Intento de levantarse de la cama");
            caida2.setDescripcionCaida("Pérdida de equilibrio al sentarse al borde de la cama");
            caida2.setCalzado(Calzado.DESCALZO);
            caida2.setConsciente(true);
            caida2.setConsecuencias(Set.of(ConsecuenciaCaida.SIN_LESION));
            caida2.setAcciones("Valoración de constantes y recolocación en cama con barandilla.");
            caidasRepository.save(caida2);

            // ==========================================
            // CONTROL COCINA
            // ==========================================
            ControlCocina cc1 = new ControlCocina();
            cc1.setFecha(LocalDate.now().minusDays(1));
            cc1.setEmpleado(admin);
            cc1.setSuperficiesLimpias(EstadoTarea.SI);
            cc1.setUniformeCorrecto(EstadoTarea.SI);
            cc1.setTemperaturaCamaras(EstadoTarea.SI);
            cc1.setEtiquetado(EstadoTarea.SI);
            cc1.setMuestrasTestigo(EstadoTarea.SI);
            cc1.setBasuraRetirada(EstadoTarea.SI);
            cc1.setLavadoPlatos(EstadoTarea.SI);
            cc1.setLimpiezaCocina(EstadoTarea.SI);
            cc1.setObservaciones("Revisión matutina satisfactoria.");
            controlCocinaRepository.save(cc1);

            ControlCocina cc2 = new ControlCocina();
            cc2.setFecha(LocalDate.now());
            cc2.setEmpleado(admin);
            cc2.setSuperficiesLimpias(EstadoTarea.SI);
            cc2.setUniformeCorrecto(EstadoTarea.SI);
            cc2.setTemperaturaCamaras(EstadoTarea.SI);
            cc2.setEtiquetado(EstadoTarea.SI);
            cc2.setMuestrasTestigo(EstadoTarea.SI);
            cc2.setBasuraRetirada(EstadoTarea.SI);
            cc2.setLavadoPlatos(EstadoTarea.SI);
            cc2.setLimpiezaCocina(EstadoTarea.SI);
            cc2.setObservaciones("Todo en orden, cámaras a 3.5 ºC.");
            controlCocinaRepository.save(cc2);

            // ==========================================
            // FICHAJES
            // ==========================================
            Fichaje f1 = new Fichaje();
            f1.setEmpleado(admin);
            f1.setEntrada(LocalDateTime.now().minusHours(8));
            f1.setSalida(LocalDateTime.now().minusHours(1));
            fichajeRepository.save(f1);

            Fichaje f2 = new Fichaje();
            f2.setEmpleado(admin);
            f2.setEntrada(LocalDateTime.now().minusHours(1));
            f2.setSalida(null);
            fichajeRepository.save(f2);

            // ==========================================
            // INCIDENCIAS
            // ==========================================
            Incidencia inc1 = new Incidencia();
            inc1.setResidente(res1);
            inc1.setEmpleado(admin);
            inc1.setFechaHora(LocalDateTime.now().minusHours(5));
            inc1.setTipo(Incidencias.CAMBIO_EMOCIONAL);
            inc1.setDescripcion("El residente se muestra algo agitado y rechaza entrar al comedor.");
            incidenciaRepository.save(inc1);

            Incidencia inc2 = new Incidencia();
            inc2.setResidente(res2);
            inc2.setEmpleado(admin);
            inc2.setFechaHora(LocalDateTime.now().minusHours(3));
            inc2.setTipo(Incidencias.URGENCIA_MEDICA);
            inc2.setDescripcion("Pico febril de 38.2 ºC detectado durante la tarde.");
            incidenciaRepository.save(inc2);

            // ==========================================
            // PARTE DIARIO
            // ==========================================
            ParteDiario pd1 = new ParteDiario();
            pd1.setFecha(LocalDate.now().minusDays(1));
            pd1.setCreador(admin);
            pd1.setContenido("Jornada sin incidencias graves. Se realizó simulacro de evacuación parcial.");
            parteDiarioRepository.save(pd1);

            ParteDiario pd2 = new ParteDiario();
            pd2.setFecha(LocalDate.now());
            pd2.setCreador(admin);
            pd2.setContenido("Revisión general completada en la primera planta.");
            parteDiarioRepository.save(pd2);

            // ==========================================
            // REGISTRO ANIMACIÓN
            // ==========================================
            RegistroAnimacion ra1 = new RegistroAnimacion();
            ra1.setEmpleado(admin);
            ra1.setFechaHora(LocalDateTime.now().minusDays(2).withHour(11).withMinute(0));
            ra1.setParticipantes(new ArrayList<>(List.of(res1, res2)));
            ra1.setActividadRealizada("Taller de musicoterapia y estimulación sensorial");
            ra1.setObservaciones("Buena receptividad y participación activa de ambos.");
            registroAnimacionRepository.save(ra1);

            RegistroAnimacion ra2 = new RegistroAnimacion();
            ra2.setEmpleado(admin);
            ra2.setFechaHora(LocalDateTime.now().minusDays(1).withHour(16).withMinute(30));
            ra2.setParticipantes(new ArrayList<>(List.of(res1)));
            ra2.setActividadRealizada("Juegos de mesa y memoria");
            ra2.setObservaciones("Manuel demuestra muy buena agilidad cognitiva.");
            registroAnimacionRepository.save(ra2);

            // ==========================================
            // REGISTRO ENFERMERÍA
            // ==========================================
            RegistroEnfermeria re1 = new RegistroEnfermeria();
            re1.setResidente(res1);
            re1.setEnfermero(admin);
            re1.setFechaHora(LocalDateTime.now().minusHours(10));
            re1.setTipoAccion(AccionEnfermeria.CURAS);
            re1.setObservacion("Cura plana en talón izquierdo. Evolución favorable sin signos de infección.");
            registroEnfermeriaRepository.save(re1);

            RegistroEnfermeria re2 = new RegistroEnfermeria();
            re2.setResidente(res2);
            re2.setEnfermero(admin);
            re2.setFechaHora(LocalDateTime.now().minusHours(4));
            re2.setTipoAccion(AccionEnfermeria.CONSTANTES);
            re2.setObservacion("TA: 120/75, FC: 72 lpm, SatO2: 97%, Temperatura: 36.6 ºC.");
            registroEnfermeriaRepository.save(re2);

            // ==========================================
            // REGISTRO FISIOTERAPIA
            // ==========================================
            RegistroFisio rf1 = new RegistroFisio();
            rf1.setEmpleado(admin);
            rf1.setActividadFisio(ActividadFisio.GIMNASIA_GRUPAL);
            rf1.setFechaRegistro(LocalDate.now().minusDays(1));
            rf1.setResidentes(new ArrayList<>(List.of(res1, res2)));
            rf1.setObservaciones("Sesión orientada a movilidad articular y tono muscular.");
            registroFisioRepository.save(rf1);

            RegistroFisio rf2 = new RegistroFisio();
            rf2.setEmpleado(admin);
            rf2.setActividadFisio(ActividadFisio.REEDUCACION_MARCHA);
            rf2.setFechaRegistro(LocalDate.now());
            rf2.setResidentes(new ArrayList<>(List.of(res1)));
            rf2.setObservaciones("Marcha en paralelas con corrección de postura y apoyo.");
            registroFisioRepository.save(rf2);

            // ==========================================
            // REGISTRO PSICOLOGÍA
            // ==========================================
            RegistroPsicologia rps1 = new RegistroPsicologia();
            rps1.setEmpleado(admin);
            rps1.setFecha(LocalDateTime.now().minusDays(3));
            rps1.setTipoRegistro(TipoRegistro.TALLER_GRUPAL);
            rps1.setCategoriaActividad(CategoriaActividad.ESTIMULACION_COGNITIVA);
            rps1.setResidentes(new ArrayList<>(List.of(res1, res2)));
            rps1.setDescripcion("Sesión grupal de orientación a la realidad, cálculo y lenguaje.");
            registroPsicologiaRepository.save(rps1);

            RegistroPsicologia rps2 = new RegistroPsicologia();
            rps2.setEmpleado(admin);
            rps2.setFecha(LocalDateTime.now().minusDays(1));
            rps2.setTipoRegistro(TipoRegistro.INDIVIDUAL);
            rps2.setCategoriaActividad(CategoriaActividad.APOYO_EMOCIONAL);
            rps2.setResidentes(new ArrayList<>(List.of(res2)));
            rps2.setDescripcion("Intervención individual por sintomatología ansiosa leve.");
            registroPsicologiaRepository.save(rps2);

            // ==========================================
            // REGISTRO SOCIAL
            // ==========================================
            RegistroSocial rs1 = new RegistroSocial();
            rs1.setResidente(res1);
            rs1.setTrabajadorSocial(admin);
            rs1.setFechaRegistro(LocalDateTime.now().minusDays(5));
            rs1.setCategoria(CategoriaSocial.RECURSO_PUBLICO);
            rs1.setRecursoDetalle(TipoRecurso.GRADO_DEPENDENCIA);
            rs1.setIntervencionDetalle(null);
            rs1.setEstado(EstadoTramite.EN_TRAMITE);
            rs1.setNumeroExpediente("EXP-2026-0091");
            rs1.setFechapresentacion(LocalDate.of(2026, 2, 1));
            rs1.setFechaVencimiento(LocalDate.of(2026, 8, 1));
            rs1.setAlertaSocial(false);
            rs1.setGestionesRealizadas("Presentación de solicitud de revisión de Grado de Dependencia.");
            registroSocialRepository.save(rs1);

            RegistroSocial rs2 = new RegistroSocial();
            rs2.setResidente(res2);
            rs2.setTrabajadorSocial(admin);
            rs2.setFechaRegistro(LocalDateTime.now().minusDays(2));
            rs2.setCategoria(CategoriaSocial.INTERVENCION_FAMILIAR);
            rs2.setRecursoDetalle(null);
            rs2.setIntervencionDetalle(TipoIntervFamiliar.ENTREVISTA_SEGUIMIENTO);
            rs2.setEstado(EstadoTramite.CONCEDIDO);
            rs2.setNumeroExpediente("EXP-2026-0104");
            rs2.setFechapresentacion(LocalDate.of(2026, 3, 10));
            rs2.setFechaVencimiento(null);
            rs2.setAlertaSocial(true);
            rs2.setGestionesRealizadas("Entrevista con familiares para seguimiento de adaptación al centro.");
            registroSocialRepository.save(rs2);

            System.out.println("=================================================");
            System.out.println("✅ Resto de datos de prueba sembrados con éxito.");
            System.out.println("🏢 Empresa: " + empresaDemo.getNombreComercial());
            System.out.println("👤 Empleado asignado: " + admin.getNombreUsuario());
            System.out.println("👥 Residentes creados: Manuel García, Dolores Martínez");
            System.out.println("=================================================");

        } catch (Exception e) {
            System.err.println("❌ ERROR AL EJECUTAR EL DATA SEEDER: " + e.getMessage());
            e.printStackTrace();
        }
    }
}