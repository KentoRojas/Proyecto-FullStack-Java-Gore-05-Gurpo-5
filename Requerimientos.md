-Objetivo Especifico: Aplicación de registros de gastos/Contabilidad
-Proyecto: Gestor de gastos
-Tecnologías usadas:Html.java.JavaScript,Css
-Problemática: Mala gestión de gastos estudiantiles,familiares,empresariales
-tipos de Usuario: Comun

Pag-Pagina de vista
    MU- Muestra 
        PI- Dentro (Opciones a seleccionar)
            PI2- Interno

Paginas e interacciones internas 
PAG-Inicio (Presentacion explicacion basica de la pagina)
PAG-Registro (Nombre,Apodo,correo,Contraseña,Confirmarcontraseña)
PAG-Login (Correo,Contraseña)
PAG-Principal 
    MU-Sueldo
        PI-ingreso de Sueldo
    MU-Priorización de Gastos x Tipo
        PI-Definición de Tipos y Prioridad
    MU-Limite de gastos (Cerca del sueldo, tornarse rojo al sobrepasar sueldo)
        PI-Delimitar gasto máximo
PAG-Gastos Presentados en un grafico
    MU-Historial de gastos 
        PI-Detalle del gasto (Fecha,tipo,monto,boleta)
            PI2-Generación de cartola de gastos mensuales 
PAG-Ingreso de Gastos (Fecha,tipo,monto,boleta)
PAG-Galería de Boletas
    MU-Fotos de boletas guardadas con fecha Por orden
        PI-Agregar
        PI-Eliminar
PAG-Sector Grupal/Familiar 
    MU-Grupo, Sueldo/Presupuesto total del grupo, Miembros
        PI-Crear grupo
            Pi2-Invitar Miembros al grupo 
            PI2-Eliminar Miembros 


Porpuestas a Futuro
-Calendario de pagos + Notificación de cercanía
-Recomendaciones financieras
-Noticias sobre descuentos 
-Noticias sobre inversiones
